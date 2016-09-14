(function() {
  'use strict';

  module.exports = siteNavigationConfig;
  siteNavigationConfig.$inject=['$stateProvider'];

  console.log('Loading navigation config for jchptf.site.navigation');

  /**
   * @ngdoc method
   * @name jchptf.site.navigation.siteNavigation:config
   *
   * @description
   * Routing configuration for portfolio application's siteNavigation module.
   * Defines the landing page, /home.
   */
  function siteNavigationConfig ($stateProvider) {
    console.log('Running navigation config for jchptf.site.navigation');
    $stateProvider.state(
      'site.home', {
        url: '/home',
        abstract: false,
        views: {
          'appCanvas@defaultLayout': {
            templateUrl: 'jchptf/site/navigation/_home.view.html',
            controller: 'HomeController as vm'
          }
        }
      }
    );
  }
}).call(window);
