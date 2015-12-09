module.exports = function(OrientationModel) {
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

  var CELLS_PER_ROW = 11;
  var CELLS_PER_COL = 11;

  OrientationModel.init = function init(orientation) {
    var ii, jj,
        retVal = new OrientationModel({ orientation: orientation }),
        CellOrientation = OrientationModel.app.models.CellOrientation,
        cellArray = new Array(CELLS_PER_ROW * CELLS_PER_COL),
        nextCellId = { index: 0, colId: 0, rowId: 0, orientation: orientation},
        previousCell, nextCell;

    function addNextCell() {
      nextCell = new CellOrientation(nextCellId);

      if (previousCell !== undefined) {
        nextCell.previous(previousCell);
        previousCell.next(nextCell);
      }

      retVal.cells[nextCellId.index++] = nextCell;
      previousCell = nextCell;
    }

    switch(orientation) {
      case 'horizontal': {
        for (nextCellId.colId=0; nextCellId.colId<CELLS_PER_ROW; nextCellId.colId++) {
          previousCell = undefined;
          for(nextCellId.rowId=0; nextCellId.rowId<CELLS_PER_COL; nextCellId.rowId++) {
            addNextCell();
          }
        }
        break;
      }
      case 'vertical': {
        for(nextCellId.rowId=0; nextCellId.rowId<CELLS_PER_COL; nextCellId.rowId++) {
          previousCell = undefined;
          for (nextCellId.colId=0; nextCellId.colId<CELLS_PER_ROW; nextCellId.colId++) {
            addNextCell();
          }
        }
        break;
      }
      default: {
        throw new Error('Orientation model cannot be initialized with unknown orientation of ' + orientation);
      }
    }

    console.log("Sample of cell #30: ", retVal.cells[30]);
    return retVal;
  }

  OrientationModel.prototype.alignedCellAt = function alignedCellAt(alignedId, crossId) {
    var cellIndex = crossId;
    if (this.orientation === 'horizontal') {
      cellIndex += alignedId * CELLS_PER_ROW;
    }
    else
      if (this.orientation === 'vertical') {
        cellIndex += alignedId * CELLS_PER_COL;
      }
      else {
        throw new Error('Orientation model orientation has not been initialized');
      }

    return this.cells[cellIndex];
  }

  OrientationModel.prototype.fixedCellAt = function fixedCellAt(colId, rowId) {
    var cellIndex;
    if (this.orientation === 'horizontal') {
      cellIndex = (rowId * CELLS_PER_ROW) + colId;
    } else if(this.orientation === 'vertical') {
      cellIndex = (colId * CELLS_PER_COL) + rowId;
    } else {
      throw new Error('Orientation model orientation has not been initialized');
    }

    return this.cells[cellIndex];
  }

  OrientationModel.prototype.alignedLength = function alignedLength() {
    var retVal;
    switch(this.orientation) {
      case 'horizontal': {
        retVal = CELLS_PER_ROW;
        break;
      }
      case 'vertical': {
        retVal = CELLS_PER_COL;
        break;
      }
      default: {
        throw new Error('Orientation model orientation has not been initialized');
      }
    }

    return retVal;
  }

  OrientationModel.prototype.crossLength = function crossLength() {
    var retVal;
    switch(this.orientation) {
      case 'horizontal': {
        retVal = CELLS_PER_COL;
        break;
      }
      case 'vertical': {
        retVal = CELLS_PER_ROW;
        break;
      }
      default: {
        throw new Error('Orientation model orientation has not been initialized');
      }
    }

    return retVal;
  }
};
