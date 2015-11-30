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
    .value('sync', client.sync)
    .value('network', client.network);
  ;
}).call(window.angular);
