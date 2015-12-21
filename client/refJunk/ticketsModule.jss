(function(angular) {
  'use strict';

  module.exports = 'jchptf.crosswords.tickets';

  /**
   * @ngdoc overview
   * @name jchptf.crosswords.tickets
   * @description
   * A module encapsulating the artifact that provides an experience
   * supporting the collection of at-face ticket information against which
   * a subsequent result reporting artifact will be created to collect the
   * hidden information that yields the ticket's prize value.
   */
  angular.module(
    module.exports,
    [
      'cgnotify', 'ui.router', 'tree.control', 'drahak.hotkeys', 'angularModalService',
      require('jchptf.context'),
      require('jchptf.authenticate'),
      require('jchptf.tools.iconPanel')
    ],
    require('./config')
  )
    .service('XWInventoryCanvas', require('./XWInventoryCanvas.service'))
    //.service('OpenResultCanvas', require('./OpenResultCanvas.service.coffee'))
    //.service('OpenTicketCanvas', require('./OpenTicketCanvas.service'))
    .service('PrizeCalculator', require('./PrizeCalculator.service.coffee'))
    .controller('XWInventoryController', require('./XWInventoryController.controller'))
    .controller('TicketController', require('./TicketController.controller'))
    .controller('ResultController', require('./ResultController.controller'))
    .controller('BonusPrizeModalController', require('./BonusPrizeModalController.controller'))
    .controller('BonusWordModalController', require('./BonusWordModalController.controller'))
  ;
}).call(window, angular);
