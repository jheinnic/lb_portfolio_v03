(function () {
  'use strict';

  module.exports = crosswordsRoutes;
  crosswordsRoutes.$inject = ['$stateProvider'];

  // TODO: Make sure IdentityContext is wired in to the authenticate check
  function crosswordsRoutes($stateProvider) {
    $stateProvider.state(
      'site.crosswords', {
         url: '/crosswords',
         abstract: false,
         views: {
           'sidePanel@defaultLayout': {
             templateUrl: 'jchptf/crosswords/catalogTree.html',
             controller: 'CrosswordController as vm'
           },
           'appCanvas@defaultLayout': {
             templateUrl: 'jchptf/crosswords/xwTicket.view.html',
             controller: 'CrosswordController as vm'
           }
         }
      }
    );

    $stateProvider.state(
      'site.crosswords.catalog', {
        url: '/browse',
        templateUrl: 'jchptf/crosswords/catalogTree.view.html',
        controller: 'XwInventoryController as inventory',
        resolve: {
          traverseRoot: traverseRoot
        },
        abstract: false,
      }
    );

    $stateProvider.state(
      'site.crosswords.ticket', {
        url: '/tickets/:ticketId',
        controller: 'TicketController',
        resolve: {
          documentCanvas: documentCanvas
        },
        abstract: false,
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
