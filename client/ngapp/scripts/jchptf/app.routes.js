(function(angular) {
    'use strict';
    
    /**
     * @ngdoc overview
     * @name jchpft.app
     * @description
     * # jchPortfolioApp
     *
     * Main module of the author's portfolio application.
     */
    angular
      .module('jchpft.app')
      .config(mainAppRoutes);
    
    mainAppRoutes.$inject=['$stateProvider', '$locationProvider'];

    function mainAppRoutes ($stateProvider, $locationProvider) {
        $stateProvider
            .state('signin', {
                url: '/views/jchpft/
          .forEach(function(route) {
            var routeDef = window.CONFIG.routes[route];
            $routeProvider.when(route, routeDef);
          });
    
        $routeProvider
          .otherwise({
            redirectTo: '/'
          });
    
        $locationProvider.html5Mode(true);
      });
}(window, window.angular));
