(function() {
  'use strict';

  module.exports = crosswordRoutes;
  crosswordRoutes.$inject=['$stateProvider'];
  traverseRoot.$inject=['Repository'];

  // TODO: Make sure IdentityContext is wired in to the authenticate check
  // TODO: Add the project query to repository service.
  function crosswordRoutes ($stateProvider) {
    $stateProvider.state(
      'crosswords', {
        templateUrl: 'views/jchptf/crosswords/browse_crosswords.view.html',
        controller: 'CrosswordsCtrl',
        controllerAs: 'crosswords',
        resolve: {
          traverseRoot: traverseRoot
        },
        abstract: false,
        authenticate: true
      }
    );

    $stateProvider.state(
      'xw_ticket', {
        templateUrl: 'views/jchptf/crosswords/browse_crosswords.view.html',
        controller: 'TicketController',
        controllerAs: 'ticket',
        resolve: {
            documentCanvas: documentCanvas
        }
      }
    );
  }

  function traverseRoot(repository) {
    // Name without suffix gets the suffix.  There will be traverse() and traverseSync(), such that
    // we want traverse() here.
    return repository.rootFolder.traverse();
  }

  function documentCanvas(studio) {
    return 'TBD: ' + studio;
  }
}).call(window);
