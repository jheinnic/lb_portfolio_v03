(function() {
  'use strict';

  module.exports = portfolioModuleConfig;
  portfolioModuleConfig.$inject=['$locationProvider'];
  // portfolioModuleConfig.$inject=['$locationProvider', '$repositoryProvider'];

  // require('jchptf').config(portfolioModuleConfig);
  // angular.module('jchptf.app').config(portfolioModuleConfig);

  // altPortfolioModuleConfig.$inject=['$locationProvider', '$repositoryProvider', '$urlRouterProvider'];
  altPortfolioModuleConfig.$inject=['$locationProvider', '$urlRouterProvider'];

  function portfolioModuleConfig($locationProvider, $repositoryProvider) {
    // Enable HTML5 URL mode
    $locationProvider.html5Mode(true);

    // $repositoryProvider.setLocalRootDirectory('D:\\DevProj\\Git\\lb_express_sandbox\\junk\\repository\\');
    // TBD
    // console.log($repositoryProvider);
    if ($repositoryProvider) { $repositoryProvider = undefined; }
  }

  function altPortfolioModuleConfig($locationProvider, $repositoryProvider, $urlRouterProvider) {
    // Enable HTML5 URL mode
    $locationProvider.html5Mode(true);

    // TBD
    console.log($repositoryProvider);

    // Prevent $urlRouter from automatically intercepting URL changes;
    // this allows you to configure custom behavior in between
    // location changes and route synchronization:
    $urlRouterProvider.deferIntercept();
  }
}).call(window);
