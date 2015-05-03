(function(angular) {
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
    angular.module('jchptf.main').config(portfolioAppGlobalConfiguration);

    portfolioAppGlobalConfiguration.$inject=['$locationProvider'];

    function portfolioAppGlobalConfiguration ($locationProvider) {
        $locationProvider.html5Mode(true);
    }
}(window, window.angular));
