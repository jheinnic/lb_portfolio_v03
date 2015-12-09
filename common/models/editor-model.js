module.exports = function(EditorModel) {
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
  //
  //var rowsFn = function rowsFn(cb) {
  //  console.log(this.name);
  //  console.log(this);
  //  // `this` refers to the RelationDefinition  - `images` (relation name)
  //  // Do some reordering here & save cb(null, [3, 2, 1]); };
  //  // Manually declare remoting params
  //  cb(null, [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
  //}
  //rowsFn.shared = true;
  //rowsFn.accepts = { };
  //rowsFn.returns = { arg: 'ids', type: 'array', root: true };
  //rowsFn.http = { verb: 'get', path: '/editorModels/rows' };

  var _ = require('lodash');

  var CELLS_PER_ROW = 11;
  var CELLS_PER_COL = 11;
  var MIN_WORD_SIZE = 3;
  var MAX_WORD_SIZE = 9;
  var TOTAL_WORD_COUNT = 22;
  var MIN_ALIGNED_WORDS = 10;
  var MAX_ALIGNED_WORDS = 12;

  function validateCoordinates(colId, rowId) {
    if ((colId <0) || (colId>CELLS_PER_ROW)) {
      throw new Error("Illegal colId: " + colId);
    } else if((rowId<0) || (rowId>CELLS_PER_COL)) {
      throw new Error("Illegal rowId: " + rowId);
    }
  }

  EditorModel.prototype.contentAt = function contentAt(colId, rowId, value) {
    validateCoordinates(colId, rowId);
    cellIndex = (rowId * CELLS_PER_ROW) + colId;

    if (value !== undefined) {
      if (/[a-zA-Z_]/.test(value)) {
        this.cellContents()[cellIndex] = value;

        // TODO: Perform an incremental computation and/or wait till editting is over!
      } else {
        throw new Error("Bad value assignment: " + value);
      }
    } else {
      return this.cellContents()[cellIndex];
    }
  }

  EditorModel.init = function init(contentString) {
    if (contentString.length != 121) {
      throw new Error("Bad string length");
    } else if (/[a-zA-Z_]{121}/.test(contentString) === false) {
      throw new Error("Bad string content");
    }

    // Only do this inside the method since EditorModel.app is not initialized yet
    // outside of any function body defined in this file.
    var OrientationModel = EditorModel.app.models.OrientationModel,
        horizontalModel = OrientationModel.init('horizontal'),
        verticalModel = OrientationModel.init('vertical'),
        contentArray = contentString.split('');

    // Weave 'sibling' navigability between equivalent cells from the two orientation models.
    for(ii=0; ii<CELLS_PER_ROW; ii++) {
      for(jj=0; jj<CELLS_PER_COL; jj++) {
        var horizontalCell = horizontalModel.fixedCellAt(ii, jj);
        var verticalCell = verticalModel.fixedCellAt(ii, jj);
        horizontalCell.mirror(verticalCell);
        verticalCell.mirror(horizontalCell);
      }
    }
    var retVal = new EditorModel({
      horizontalModel: horizontalModel,
      verticalModel: verticalModel,
      cellContents: contentArray,
      // cursor: {
      //   active: false,
      //   row: -1,
      //   col: -1
      // },
      isCursorActive: false,
      cursorRow: -1,
      cursorColumn: -1
    });

    //
    // Begin repeatable analysis section
    //
    var ii, jj,
        GridRegion = EditorModel.app.models.GridRegion,
        allHorizontal = { lines: [], open: [], short: [], word: [], blocked: [] },
        allVertical = { lines: [], open: [], short: [], word: [], blocked: []};

    var rowOriented = contentString,
        colOriented = contentArray.map(
          function buildRotation(value, index, board) {
            var colId = index % CELLS_PER_COL;
            var rowId = (index - colId) / CELLS_PER_ROW;
            return board[(colId * CELLS_PER_ROW) + rowId];
          }
        ).join('');

    function isWordOrShort(subStr) {
      var retVal = false;
      if ((typeof subStr) === 'string') {
        retVal = subStr.length > 1
      }

      return retVal;
    }

    function takeWord(orientationModel, regionObj, lineIndex, srcLine, word) {
      var wordLen = word.length,
          srcIdx  = srcLine.indexOf(word);

      if (wordLen < MIN_WORD_SIZE) {
        regionObj.short.push(
          GridRegion.init(orientationModel, 'short', lineIndex, srcIdx, wordLen)
        );
      } else if (wordLen <= MAX_WORD_SIZE) {
        regionObj.word.push(
          GridRegion.init(orientationModel, 'word', lineIndex, srcIdx, wordLen)
        );
        if (lineIndex > 0) {
          regionObj.blocked.push(
            GridRegion.init(orientationModel, 'blocked', lineIndex-1, srcIdx+1, wordLen-2)
          );
        }
        if (lineIndex < orientationModel.crossLength() - 1) {
          regionObj.blocked.push(
            GridRegion.init(orientationModel, 'blocked', lineIndex+1, srcIdx+1, wordLen-2)
          );
        }
      } else {
        throw new Error(word + ' exceeds maximum word wordLength of ' + MAX_WORD_LENGTH);
      }

      return srcLine.slice(wordLen + srcIdx);
    }

    function iterateOnGrid(orientationModel, regex, wholeGrid, regionObj) {
      var lineIdx = regionObj.lines.length;
      var nextLine = regex.exec(wholeGrid)[0];
      regionObj.lines.push(nextLine);

      var wordFn = _.partial(takeWord, orientationModel, regionObj, lineIdx);
      console.log(nextLine);
      console.log(isWordOrShort);
      console.log(wordFn);
      console.log(nextLine.split(/_+/));
      console.log(nextLine.split(/_+/).filter(isWordOrShort));
      console.log(nextLine.split(/_+/).filter(isWordOrShort).reduce(wordFn, nextLine));
    }

    // Parse short runs and words from horizontal perspective
    var rowsRegex = new RegExp('.{' + CELLS_PER_ROW + ',' + CELLS_PER_ROW + '}'),
        rowIterFn =
          _.partial(iterateOnGrid, horizontalModel, rowsRegex, rowOriented, allHorizontal);
    for (ii=0; ii<CELLS_PER_COL; ii++) {
      rowIterFn();
    }

    // Parse short runs and words from vertical perspective
    var colsRegex = new RegExp('.{' + CELLS_PER_COL + ',' + CELLS_PER_COL + '}'),
        colIterFn =
          _.partial(iterateOnGrid, verticalModel, colsRegex, colOriented, allVertical);
    for (ii=0; ii<CELLS_PER_ROW; ii++) {
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

    var allGridWords = allHorizontalWords.concat(allVerticalWords),
        allTriplingWords = allGridWords.filter(
          function hasTriplingMark(word) { return /[A-Z]/.test(word); }
        ),
        allBasicWords = allGridWords.filter(
          function excludeTriplingWords(word) { return allTriplingWords.indexOf(word) == -1; }
        );

    // TODO: Mark blocked cells that would:
    // -- Extend a word's length beyond 9
    // -- Merge two established parallel words that already have sufficient length to stand alone.
    // -- Merge an established word with a parallel short word such that the result would exceed MAX_WORD_LENGTH.

    //$scope.conditionModel.allHorizontalWords = allHorizontalWords
    //$scope.conditionModel.allVerticalWords = allVerticalWords
    //$scope.conditionModel.allTriplingWords = allTriplingWords
    //$scope.conditionModel.allBasicWords = allBasicWords
    //$scope.conditionModel.allGridWords = allGridWords

    // Concatenate a collection of all regions from the sub-type specific collections.
    function mergeRegions(regionObj) {
      return regionObj.word.concat(regionObj.open).concat(regionObj.blocked).concat(regionObj.short);
    }
    horizontalModel.regions( mergeRegions(allHorizontal) );
    verticalModel.regions( mergeRegions(allVertical) );

    console.log("Horizontal:");
    console.log(JSON.stringify(horizontalModel));
    console.log("\n\n\n");
    console.log("Vertical:");
    console.log(JSON.stringify(verticalModel));
    console.log("\n\n\n");
    console.log("Complete:");
    console.log(JSON.stringify(retVal));

    return retVal;
  }

  // TODO: Fill in the logic
  EditorModel.prototype.isDefinitionComplete = function isDefinitionComplete() {
    return false;
  }

};
