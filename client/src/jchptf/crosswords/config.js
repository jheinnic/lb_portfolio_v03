(function () {
  'use strict';

  module.exports = crosswordsRoutes;
  crosswordsRoutes.$inject = ['$stateProvider'];

  // TODO: Make sure IdentityContext is wired in to the authenticate check
  function crosswordsRoutes($stateProvider) {
    $stateProvider.state(
      'crosswords', {
        templateUrl: 'jchptf/crosswords/crosswordsFeature.view.html',
        controllerAs: 'crossword',
        abstract: false,
        authenticate: true
      }
    );

    $stateProvider.state(
      'crosswords.catalog', {
        url: '/crosswords/browse',
        templateUrl: 'jchptf/crosswords/catalogTree.view.html',
        controller: 'XwInventoryController',
        controllerAs: 'inventory',
        resolve: {
          traverseRoot: traverseRoot
        },
        abstract: false,
        authenticate: true
      }
    );

    $stateProvider.state(
      'crossword.ticket', {
        url: '/crosswords/tickets/:ticketId',
        controller: 'TicketController',
        resolve: {
          documentCanvas: documentCanvas
        },
        abstract: false,
        authenticate: true
      }
    );
  }

  documentCanvas.$inject = ['xwInventoryCanvas'];
  function documentCanvas(Studio) {
    return 'TBD: ' + Studio;
  }

  traverseRoot.$inject = ['xwInventoryCanvas'];
  function traverseRoot(xwInventoryCanvas) {
    // Name without suffix gets the suffix.  There will be traverse() and traverseSync(), such that
    // we want traverse() here.
    return xwInventoryCanvas.rootFolder.traverse();
  }
}).call(window);
