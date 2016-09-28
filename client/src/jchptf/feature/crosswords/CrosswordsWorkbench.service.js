/**
 * @ngdoc service
 * @name jchptf.feature.crosswords:CrosswordsWorkbench
 *
 * @description
 *
 *
 * */
(function() {
  'use strict';

  module.exports = CrosswordsWorkbench;
  CrosswordsWorkbench.$injet = ['$rootScope', '$q', 'TicketPlayState'];


  function CrosswordsWorkbench($rootScope, $q, TicketPlayState) {
    var vm = this;

    var deferBootstrapHandle = null;
    var userId = null;
    var ticketCatalog = null;
    var stateBackStack = [];
    var topFrame = null;

    $rootScope.$on('com.jchptf.site.authentication.sessionChangeEvent', function onSessionChange($event) {
      if ($event.eventType.isAuthenticated()) {
        deferBootstrapHandle = $q.defer();
        userId = $event.userId;
        TicketPlayState.find({}, function(data) {
          ticketCatalog = data;
          deferBootstrapHandle.resolve(this);
        }, function(err) {
          console.error(err);
        });
      }
    });

    vm.initWorkbench = function initWorkbench() {
      if (deferBootstrapHandle === null) {
        throw Error('Workbench cannot initialize until someone logs in');
      }
    };

    vm.pushBackStack = function pushBackStack(currentState, stateParams) {
      stateBackStack.push(topFrame);
      topFrame = {state: currentState, params: stateParams};
    };

    vm.peekContextStack = function peekContextStack() {
      return topFrame;
    };
  }
}).call(window);

