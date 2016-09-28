(function () {
  'use strict';

  module.exports = crosswordsRoutes;
  crosswordsRoutes.$inject = ['$stateProvider'];

  // TODO: Make sure IdentityContext is wired in to the authenticate check
  function crosswordsRoutes($stateProvider) {
    $stateProvider.state(
      'site.crosswords', {
        url: '/crosswords',
        abstract: true,
        resolve: {
          initWorkbench: ['CrosswordsWorkbench', function(CrosswordsWorkbench) {
            return CrosswordsWorkbench.initWorkbench();
          }],
          '$state': '$state'
        },
        views: {
          'content@site': {
            templateUrl: 'jchptf/crosswords/_feature.view.html',
            controller: 'CrosswordController as vm'
          }
        },
        onEnter: ['$state', 'initWorkbench', function onEnter($state, initWorkbench) {
          // Peek at and return to top frame of the context stack.  If the stack is empty,
          // this routine will initialize it using the logged in user's account prefernces
          // to pick a default, falling back to an application-defined default if necessary.
          var latestContext = initWorkbench.peekContextStack();
          $state.go(latestContext.state, latestContext.params);
        }]
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
        data: {
          jchptfGlobalNav: {
            tabId: 'xw',
            rankOrder: 2,
            displayName: 'Crosswords'
          }
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
