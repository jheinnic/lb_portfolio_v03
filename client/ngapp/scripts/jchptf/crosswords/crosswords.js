(function () {
  'use strict';

  module.exports = 'jchptf.crosswords';

  /**
   * @ngdoc overview
   * @name jchptf.crosswords
   * @description
   *
   * Root module for the "Crosswords" feature set.
   */
  angular.module(
    'jchptf.crosswords',
    [
      require('jchptf.crosswords.browse'),
      require('jchptf.crosswords.tickets')
      // require('jchptf.crosswords.results')
    ]
  );
}).call(window);
