(function() {
  'use strict';

  module.exports = portfolioModuleConfig;
  portfolioModuleConfig.$inject=['$locationProvider', '$urlRouterProvider'];

  console.log('Loading jchptf config');

  /**
   * @ngdoc method
   * @name jchptf.site.navigation.siteNavigation:config
   *
   * @description
   * Routing configuration for portfolio application's siteNavigation module.
   * Defines the landing page, /home.
   */
  function portfolioModuleConfig($locationProvider, $urlRouterProvider) {
    console.log('Running jchptf config');

    // Enable HTML5 URL mode
    $locationProvider.html5Mode(true);

    // Prevent $urlRouter from automatically intercepting URL changes;
    // this allows you to configure custom behavior in between
    // location changes and route synchronization:
    $urlRouterProvider.deferIntercept(true);
  }
}).call(window);
