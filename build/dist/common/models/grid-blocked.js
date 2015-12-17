(
  function () {
    'use strict';

    module.exports = function (GridBlocked) {
      GridBlocked.init = function init(orientationModel, alignedCoordinate, crossCoordinate, runLength) {
        var GridRegion = GridBlocked.app.models.GridRegion;

        return GridRegion.doInit(GridBlocked, orientationModel, alignedCoordinate, crossCoordinate, runLength);
      };

      // TODO: Figure out of there are better interceptors for fixing 'kind'.
      GridBlocked.beforeSave = function(next, model) {
        model.kind = 'blocked';
      };
    };
  }
).call();
