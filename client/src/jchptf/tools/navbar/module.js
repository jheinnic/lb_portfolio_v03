(function () {
  'use strict';

  module.exports = 'jchptf.tools.navbar';

  /**
   * @ngdoc overview
   * @name jchptf.tools.navbar
   * @description
   * A reusable component for creating navigation bars with data-driven content that
   * allows application to change navigation content over time.
   */
  angular.module('jchptf.tools.navbar', ['ng', 'mgcrea.ngStrap.navbar'])
    .value('NavBarModel', require('./NavBarModel.class'))
    .value('TabModel', require('./TabModel.class'))
    .value('NavBarBuilder', require('./NavBarBuilder.class'))
    .service('JchNavData', require('./JchNavData.service'))
    .directive('jchNavBar', require('./jchNavBar.directive'))
  ;
}).call(window);
