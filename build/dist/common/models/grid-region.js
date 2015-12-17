(
  function () {
    'use strict';

    module.exports = function (GridRegion) {
      GridRegion.doInit = function doInit(Constructor, orientationModel, alignedCoordinate, crossCoordinate, runLength) {
        var cellArray = new Array(runLength);
        var head = orientationModel.alignedCellAt(alignedCoordinate, crossCoordinate);
        var tail = orientationModel.alignedCellAt(alignedCoordinate, crossCoordinate + runLength - 1);

        // Construct with empty cell array so we can establish a reference to region while filling the
        // array in while loop to follow.
        var region = new Constructor(
          {
            orientation: orientationModel.orientation(),
            head: head,
            tail: tail,
            cells: cellArray
          }
        );

        var nextCell = head, nextIndex = 0;
        while (nextCell !== tail) {
          cellArray[nextIndex++] = nextCell;
          nextCell.region(region);
          nextCell = nextCell.next();
        }
        cellArray[nextIndex] = tail;
        tail.region(region);

        // TODO: This could be overkill if the cells constructor argument works as hoped.  It likely won't.
        //function assignRegion(nextCell) {
        //  nextCell.region(region);
        //}
        //cellArray.forEach(assignRegion);

        return region;
      };
    };
  }
).call();
