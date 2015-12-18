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
      'ui.router',
      'drahak.hotkeys',
      'angularModalService',
      require('jchptf.context'),
      require('jchptf.authenticate'),
      require('jchptf.tools.iconPanel'),
      require('jchptf.site.notification'),
      require('jchptf.crosswords.tickets')
    ]
  )
    .controller('ResultController', require('./ResultController.controller'))
    .service('PrizeCalculator', require('./PrizeCalculator.service.coffee'))
  ;
}).call(window, angular);
