(function () {
  'use strict';

  module.exports = function (GridWord) {
    GridWord.init = function init(orientationModel, alignedCoordinate, crossCoordinate, runLength) {
      var GridRegion = GridWord.app.models.GridRegion;

      return GridRegion.doInit(GridWord, orientationModel, alignedCoordinate, crossCoordinate, runLength);
    };

    // TODO: Figure out of there are better interceptors for fixing 'kind'.
    GridWord.beforeSave = function(next, model) {
      model.kind = 'word';
    };
  };
}).call();
