(function() {
  'use strict';

  console.log('Loading jchptf config');
  module.exports = portfolioModuleConfig;

  portfolioModuleConfig.$inject=['$locationProvider', '$urlRouterProvider'];

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
