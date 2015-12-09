module.exports = function(GridRegion) {
  GridRegion.init = function init(orientationModel, regionKind, alignedCoordinate, crossCoordinate, runLength) {
    var head = orientationModel.alignedCellAt(alignedCoordinate, crossCoordinate),
        tail = orientationModel.alignedCellAt(alignedCoordinate, crossCoordinate+runLength-1),
        nextCell = head,
        nextIndex = 0,
        cellArray = new CellOrientation[runLength];

    while(nextCell != tail) {
      cellArray[nextIndex++] = nextCell;
      nextCell = nextCell.next();
    }
    cellArray[nextIndex] = nextCell;

    var region = new GridRegion({
      orientation: orientationModel.orientation(),
      kind: regionKind,
      head: head,
      tail: tail,
      cells: cellArray
    });

    // TODO: This should be overkill if the cells constructor argument works as hoped.
    cellArray.forEach(function assignRegion(nextCell) {
      nextCell.region(region);
    });

    return region;
  }
};
