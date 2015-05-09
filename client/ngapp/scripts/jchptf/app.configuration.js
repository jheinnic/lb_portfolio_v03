(function() {
  'use strict';

  /**
   * @ngdoc overview
   * @name jchpft.app
   * @description
   * # jchPortfolioApp
   *
   * Main module of the author's portfolio application.  Responsible for establishing global
   * configuration and bootstrapping the application's entry point for its run() method, which
   * Angular executes once after all modules loading has completed.
   */
  angular.module('jchptf.app').config(portfolioGlobalConfig);

  portfolioGlobalConfig.$inject=['$locationProvider'];
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
