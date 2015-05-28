(function() {
  'use strict';

  module.exports = ticketsRoutes;
  ticketsRoutes.$inject = ['$stateProvider'];
  documentCanvas.$inject = ['Studio'];

  // TODO: Make sure IdentityContext is wired in to the authenticate check
  // TODO: Add the project query to repository service.
  function ticketsRoutes ($stateProvider) {
    $stateProvider.state(
      'xw_ticket', {
        templateUrl: 'views/jchptf/crosswords/ticket/xwTicket.view.html',
        controller: 'TicketController',
        controllerAs: 'ticket',
        resolve: {
            documentCanvas: documentCanvas
        }
      }
    );
  }

  function documentCanvas(Studio) {
    return 'TBD: ' + Studio;
  }
}).call(window);
