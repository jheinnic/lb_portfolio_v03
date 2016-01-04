(function (angular) {
  'use strict';

  module.exports = 'jchptf.tools.navbar';

  /**
   * @ngdoc overview
   * @name jchptf.tools.navbar
   * @description
   * A reusable component for creating navigation bars with data-driven content that
   * allows application to change navigation content over time.
   */
  angular.module(
    module.exports,
    ['ng', 'mgcrea.ngStrap.navbar', 'ui.router']
  )
    .service('JchNavData', require('./JchNavData.service'))
    .directive('jchNavBar', require('./jchNavBar.directive'))
  ;
}).call(window, angular);
