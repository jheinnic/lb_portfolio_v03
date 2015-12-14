(function(angular) {
  'use strict';

  module.exports = 'jchptf.lbclient';

  // load lbclient via browserify's require
  var client = (function() {
    /*global require:true*/
    return require('lbclient');
  })();

  /**
   * @ngdoc overview
   * @name jchptf.lbclient
   * @description
   * # jchptf.lbclient
   *
   * Module encapsulating the client-side Loopback application used to access
   * the API for server-side application features.
   */
  angular.module(
    module.exports,
    []
  )
    .value('Todo', client.models.LocalTodo)
    .value('RemoteTodo', client.models.Todo)
    .value('FiveXTicket', client.models.LocalFiveXTicket)
    .value('RemoteFiveXTicket', client.models.FiveXTicket)
    .value('TripOnlyTicket', client.models.LocalTripOnlyTicket)
    .value('RemoteTripOnlyTicket', client.models.TripOnlyTicket)
    .value('TripSpotTicket', client.models.LocalTripSpotTicket)
    .value('RemoteTripSpotTicket', client.models.TripSpotTicket)
    .value('sync', client.sync)
    .value('network', client.network)
  ;
}).call(window, angular);
