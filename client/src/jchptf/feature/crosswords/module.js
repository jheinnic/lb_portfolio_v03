(function (angular) {
  'use strict';

  module.exports = 'jchptf.feature.crosswords';

  console.log('Loading module definition for jchptf.feature.crosswords');

  /**
   * @ngdoc overview
   * @name "jchptf.crosswords"
   * @description
   * A module encapsulating the artifact that provides an experience
   * supporting the collection of at-face ticket information against which
   * a subsequent result reporting artifact will be created to collect the
   * hidden information that yields the ticket's prize value.
   */
  angular.module(
    module.exports,
    [
      'ng', 'ui.router', 'ls.LiveSet', 'ls.ChangeStream', 'ngMaterial',
      require('jchptf.lbclient'), 'jchptf.lbServices', require('jchptf.site.navigation'),
      require('jchptf.site.authentication'), require('jchptf.feature.crosswords.import')
    ],
    require('./config')
  )
    .service('PrizeCalculator', require('./PrizeCalculator.service.coffee'))
    .controller('CrosswordController', require('./CrosswordController.controller'))
    .controller('TicketController', require('./TicketController.controller'))
    .controller('ResultController', require('./ResultController.controller'))
    .controller('BonusPrizeModalController', require('./BonusPrizeModalController.controller'))
    .controller('BonusWordModalController', require('./BonusWordModalController.controller'))
    // .run(require('./run'))
  ;
}).call(window, window.angular);
