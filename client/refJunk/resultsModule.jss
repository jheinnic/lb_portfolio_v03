(function(angular) {
  'use strict';

  module.exports = 'jchptf.crosswords.results';

  /**
   * @ngdoc overview
   * @name jchptf.crosswords.result
   * @description
   * An module encapsulating the artifact that provides an experience
   * supporting the collection of resolution data from a crossword puzzle
   * ticket previously published from the definition workflow.
   */
  angular.module(
    module.exports,
    [
      'cgnotify', 'ui.router', 'tree.control', 'drahak.hotkeys', 'angularModalService',
      require('jchptf.context'),
      require('jchptf.authenticate'),
      require('jchptf.tools.iconPanel')
    ]
  )
    .service('XWInventoryCanvas', require('./XWInventoryCanvas.service'))
    .service('PrizeCalculator', require('./PrizeCalculator.service.coffee'))
    .controller('XWInventoryController', require('./XWInventoryController.controller'))
    .controller('ResultController', require('./ResultController.controller'))
    .controller('BonusPrizeModalController', require('./BonusPrizeModalController.controller'))
  ;
}).call(window, angular);
