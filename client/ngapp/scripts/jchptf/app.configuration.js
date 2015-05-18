(function() {
  'use strict';

  module.exports = portfolioGlobalConfig;
  portfolioGlobalConfig.$inject=['$locationProvider'];

  // require('jchptf').config(portfolioGlobalConfig);
  // angular.module('jchptf.app').config(portfolioGlobalConfig);

  altPortfolioGlobalConfig.$inject=['$locationProvider', '$urlRouterProvider'];

  function portfolioGlobalConfig($locationProvider) {
    $locationProvider.html5Mode(true);
  }

  function altPortfolioGlobalConfig($locationProvider, $urlRouterProvider) {
    $locationProvider.html5Mode(true);

    // Prevent $urlRouter from automatically intercepting URL changes;
    // this allows you to configure custom behavior in between
    // location changes and route synchronization:
    $urlRouterProvider.deferIntercept();
  }
}).call(window);
