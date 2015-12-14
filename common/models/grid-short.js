(
  function () {
    'use strict';

    module.exports = function (GridShort) {
      GridShort.init = function init(orientationModel, alignedCoordinate, crossCoordinate, runLength) {
        var GridRegion = GridShort.app.models.GridRegion;

        return GridRegion.doInit(GridShort, orientationModel, alignedCoordinate, crossCoordinate, runLength);
      };

      // TODO: Figure out of there are better interceptors for fixing 'kind'.
      GridShort.beforeSave = function(next, model) {
        model.kind = 'short';
      };
    };
  }
).call();
