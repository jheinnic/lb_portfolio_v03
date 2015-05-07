(function () {
  'use strict';

  /**
   * @ngdoc overview
   * @name jchptf.site.navigation
   * @description
   *
   * TODO
   */
  angular.module(
    'jchptf.site.navigation',
    [
      'ui.router', 'jchptf.context', 'jchptf.authenticate', 'jchptf.site.notification', 'jchptf.crosswords'
    ]
  );
}).call(window);
