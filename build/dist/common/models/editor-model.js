(
  function () {
    'use strict';

    module.exports = function (EditorModel) {
      //var colsFn = function colsFn(cb) {
      //  console.log(this.name);
      //  console.log(this);
      //  // `this` refers to the RelationDefinition  - `images` (relation name)
      //  // Do some reordering here & save cb(null, [3, 2, 1]); };
      //  // Manually declare remoting params
      //  cb(null, [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
      //}
      //colsFn.shared = true;
      //colsFn.accepts = { };
      //colsFn.returns = { arg: 'ids', type: 'array', root: true };
      //colsFn.http = { verb: 'get', path: '/editorModels/cols' };

      var _ = require('lodash');

      var CELLS_PER_ROW = 11, CELLS_PER_COL = 11, MIN_WORD_SIZE = 3, MAX_WORD_SIZE = 9;

      // TBD
      // var TOTAL_WORD_COUNT = 22;
      // var MIN_ALIGNED_WORDS = 10;
      // var MAX_ALIGNED_WORDS = 12;

      function validateCoordinates(colId, rowId) {
        if (colId < 0 || colId > CELLS_PER_ROW) {
          throw new Error('Illegal colId: ' + colId);
        } else if (rowId < 0 || rowId > CELLS_PER_COL) {
          throw new Error('Illegal rowId: ' + rowId);
        }
      }

      EditorModel.prototype.contentAt = function contentAt(colId, rowId, value) {
        validateCoordinates(colId, rowId);
        var cellIndex = (rowId * CELLS_PER_ROW) + colId;

        if (value !== undefined) {
          if (/[a-zA-Z_]/.test(value)) {
            this.cellContents()[cellIndex] = value;

            // TODO: Perform an incremental computation and/or wait till editting is over!
          } else {
            throw new Error('Bad value assignment: ' + value);
          }
        } else {
          return this.cellContents()[cellIndex];
        }
      };

      EditorModel.init = function init(xwTicket) {
        var contentString = xwTicket.gridContent;
        if (contentString.length !== 121) {
          throw new Error('Bad string length');
        } else if (/[a-zA-Z_]{121}/.test(contentString) === false) {
          throw new Error('Bad string content');
        }

        // TODO: Is this right to link edit model to source ticket?
        this._sourceTicket(xwTicket);

        // Only do this inside the method since EditorModel.app is not initialized yet
        // outside of any function body defined in this file.
        var OrientationModel = EditorModel.app.models.OrientationModel;
        var horizontalModel = OrientationModel.init('horizontal');
        var verticalModel = OrientationModel.init('vertical');
        var contentArray = contentString.split(''), ii, jj;

        // Weave 'sibling' navigability between equivalent cells from the two orientation models.
        for (ii = 0; ii < CELLS_PER_ROW; ii++) {
          for (jj = 0; jj < CELLS_PER_COL; jj++) {
            var horizontalCell = horizontalModel.fixedCellAt(ii, jj);
            var verticalCell = verticalModel.fixedCellAt(ii, jj);
            horizontalCell.mirror(verticalCell);
            verticalCell.mirror(horizontalCell);
          }
        }
        var retVal = new EditorModel(
          {
            horizontalModel: horizontalModel,
            verticalModel: verticalModel,
            cellContents: contentArray, // cursor: {
            //   active: false,
            //   row: -1,
            //   col: -1
            // },
            isCursorActive: false,
            cursorRow: -1,
            cursorColumn: -1
          }
        );

        retVal.doAnalysis();
        console.log('\n\n\n');
        console.log('Complete:');
        console.log(JSON.stringify(retVal));

        return retVal;
      };

      // Helper object for collecting regions by type and then later concatenating those collections
      function RegionAnalysis() {
        this.lines = [];
        this.open = [];
        this.short = [];
        this.word = [];
        this.blocked = [];
      }
      RegionAnalysis.prototype.merge =
        function merge() {
          return this.short.concat(this.word.concat(this.open)).concat(this.blocked);
        };

      EditorModel.prototype.doAnalysis = function doAnalysis() {
        var GridWord    = EditorModel.app.models.GridWord;
        var GridShort   = EditorModel.app.models.GridShort;
        var GridBlocked = EditorModel.app.models.GridBlocked;

        function takeWord(orientationModel, regionObj, lineIndex, srcLine, word) {
          var wordLen = word.length, srcIdx = srcLine.indexOf(word);

          if (wordLen < MIN_WORD_SIZE) {
            if (wordLen > 1) {
              regionObj.short.push(
                GridShort.init(orientationModel, lineIndex, srcIdx, wordLen)
              );
            }
          } else if (wordLen <= MAX_WORD_SIZE) {
            regionObj.word.push(
              GridWord.init(orientationModel, lineIndex, srcIdx, wordLen)
            );
            if (lineIndex > 0) {
              regionObj.blocked.push(
                GridBlocked.init(orientationModel, lineIndex - 1, srcIdx + 1, wordLen - 2)
              );
            }
            if (lineIndex < orientationModel.crossLength() - 1) {
              regionObj.blocked.push(
                GridBlocked.init(orientationModel, 'blocked', lineIndex + 1, srcIdx + 1, wordLen - 2)
              );
            }
          } else {
            throw new Error(word + ' exceeds maximum word wordLength of ' + MAX_WORD_SIZE);
          }

          return srcLine.slice(wordLen + srcIdx);
        }

        function iterateOnGrid(orientationModel, regex, wholeGrid, regionObj) {
          var lineIdx = regionObj.lines.length;
          var nextLine = regex.exec(wholeGrid)[0];
          regionObj.lines.push(nextLine);

          var wordFn = _.partial(takeWord, orientationModel, regionObj, lineIdx);
          console.log(
            _.words(nextLine).reduce(wordFn, nextLine)
          );
        }
        var horizontalAnalysis = new RegionAnalysis();
        var verticalAnalysis   = new RegionAnalysis();

        // TODO: Consider reading this.sourceModel.gridContent iff we manage to keep it consistent
        //       between each analysis, but I suspect we will only re-sync the two onSave.
        // TODO: Revise this logic to work on arrays instead of requiring join('').
        var rowOriented = this.cellContents.join('');
        var colOriented = this.cellContents.map(
          function buildRotation(value, index, origGrid) {
            var colId = index % CELLS_PER_COL;
            var rowId = (index - colId) / CELLS_PER_ROW;
            return origGrid[(colId * CELLS_PER_ROW) + rowId];
          }
        ).join('');

        // Parse short runs and words from horizontal perspective
        var rowsRegex = new RegExp('.{' + CELLS_PER_ROW + ',' + CELLS_PER_ROW + '}');
        var rowIterFn = _.partial(
          iterateOnGrid, this.horizontalModel, rowsRegex, rowOriented, horizontalAnalysis
        );
        for (var ii = 0; ii < CELLS_PER_COL; ii++) {
          rowIterFn();
        }

        // Parse short runs and words from vertical perspective
        var colsRegex = new RegExp('.{' + CELLS_PER_COL + ',' + CELLS_PER_COL + '}');
        var colIterFn = _.partial(
          iterateOnGrid, this.verticalModel, colsRegex, colOriented, verticalAnalysis
        );
        for (ii = 0; ii < CELLS_PER_ROW; ii++) {
          colIterFn();
        }

        //for (ii=0; ii<CELLS_PER_COL; ii++) {
        //  var rowRegion = new GridRegion();
        //  for (jj=0; jj<CELLS_PER_ROW; jj++) {
        //    var nextCell = new CellOrientation(ii, jj);
        //    if (rowRegion.head() === undefined) {
        //      rowRegion.head(nextCell);
        //    }
        //  }
        //}

        var allGridWords = horizontalAnalysis.words.concat(verticalAnalysis.word);
        var allTriplingWords = [ ];
        var allBasicWords = [ ];
        _.forEach(
          allGridWords,
          function classifyWord(word) {
            var classList = /[A-Z]/.test(word) ? allTriplingWords : allBasicWords;
            classList.push(word);
          }
        );

        // TODO: Mark blocked cells that would:
        // -- Extend a word's length beyond 9
        // -- Merge two established parallel words that already have sufficient length to stand alone.
        // -- Merge an established word with a parallel short word such that the result would exceed MAX_WORD_SIZE.

        //$scope.conditionModel.horizontalAnalysisWords = horizontalAnalysisWords
        //$scope.conditionModel.verticalAnalysisWords = verticalAnalysisWords
        //$scope.conditionModel.allTriplingWords = allTriplingWords
        //$scope.conditionModel.allBasicWords = allBasicWords
        //$scope.conditionModel.allGridWords = allGridWords


        this.horizontalModel.regions(horizontalAnalysis.merge());
        this.verticalModel.regions(verticalAnalysis.merge());

        console.log('Horizontal:');
        console.log(JSON.stringify(this.horizontalModel));
        console.log('\n\n\n');
        console.log('Vertical:');
        console.log(JSON.stringify(this.verticalModel));
      };

      // TODO: Fill in the logic
      EditorModel.prototype.isDefinitionComplete = function isDefinitionComplete() {
        return false;
      };
    };
  }
).call();
