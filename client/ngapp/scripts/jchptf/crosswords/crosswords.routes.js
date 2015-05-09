
(function() {
  'use strict';

  /**
   * @ngdoc overview
   * @name jchptf.app
   * @description Module encapsulating portfolio site's "Crosswords" feature set.
   */
  angular.module('jchptf.crossword').config(crosswordRoutes);

  crosswordRoutes.$inject=['$stateProvider'];

  // TODO: Make sure IdentityContext is wired in to the authenticate check
  // TODO: Add the project query to repository service.
  function crosswordRoutes ($stateProvider) {
    $stateProvider.state(
      'crosswords', {
        templateUrl: '/views/jchptf/crosswords/browseRepository.view.html',
        controller: 'CrosswordsCtrl',
        controllerAs: 'crosswords',
        resolve: { },
        abstract: false,
        authenticate: true
      }
    );
  }
}).call(window);