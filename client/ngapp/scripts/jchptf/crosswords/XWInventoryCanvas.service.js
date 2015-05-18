(function() {
  'use strict';

  XWInventoryCanvas.$inject = [];
  module.exports = XWInventoryCanvas;

  var _ = require('lodash');
  var crosswordsModel = require('./xwModel.modelPkg.coffee');

  function XWInventoryCanvas() {
    this.isCanvas = function isCanvas() {
      return true;
    };

    this.getCrosswordsModel = function() {
      return _.clone(crosswordsModel);
    };
  }
})(window);
