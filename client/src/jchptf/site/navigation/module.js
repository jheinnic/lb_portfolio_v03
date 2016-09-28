(function (angular) {
  'use strict';

  module.exports = 'jchptf.site.navigation';

  /**
   * @ngdoc overview
   * @name jchptf.site.navigation
   * @description
   * A reusable component for creating navigation bars with data-driven content that
   * allows application to change navigation content over time.
   */
  angular.module(
    module.exports,
    ['ui.router', 'ui.router.login', 'ngMaterial'],
    require('./config')
  )
    .provider('NavbarData', require('./NavbarData.provider'))
    .controller('GlobalNavController', require('./GlobalNav.controller'))
  ;
}).call(window, window.angular);
