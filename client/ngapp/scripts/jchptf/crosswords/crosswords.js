(function () {
  'use strict';

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
      'ui.router', 'jchptf.context', 'jchptf.authenticate', 'jchptf.site.notification',
      'jchptf.crosswords.tickets', 'jchptf.crosswords.results'
    ]
  );
}).call(window);
