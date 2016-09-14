'use strict';

// TODO(bajtos) Move the bi-di replication to loopback core,
// add model settings to enable the replication.
// Example:
//  LocalTodo: { options: {
//    base: 'Todo',
//    replicate: {
//      target: 'Todo',
//      mode: 'push' | 'pull' | 'bidi'
// Copyright IBM Corp. 2015. All Rights Reserved.
// Node module: loopback-example-isomorphic
// This file is licensed under the MIT License.
// License text available at https://opensource.org/licenses/MIT

module.exports = function(app) {
  var LocalTodo = app.models.LocalTodo;
  var RemoteTodo = app.models.RemoteTodo;

  app.network = {
    _isConnected: true,
    get isConnected() {
      console.log('isConnected?', this._isConnected);
      return this._isConnected;
    },
    set isConnected(value) {
      this._isConnected = value;
    }
  };

  var since = {push: -1, pull: -1};
  function sync(cb) {
    console.log('sync triggered');
    LocalTodo.replicate(
      RemoteTodo,
      since.push,
      function pushed(err, conflicts, cps) {
        console.log('LR', arguments);
        since.push = cps;
        RemoteTodo.replicate(
          LocalTodo,
          since.pull,
          function pulled(err, conflicts, cps) {
            console.log('RL', arguments);
            since.pull = cps;
          });
      });
  }

  // sync local changes if connected
  function legacyChangedObserver(arg) {
    console.log('Fired on changed');
  });
  LocalTodo.on('changed', legacyChangedObserver);
  function legacyDeletedObserver(arg) {
    if (arg )
    console.log('Fired on changed');
  });
  LocalTodo.on('deleted', legacyDeletedObserver);

  LocalTodo.observe('after save', function(ctx, next) {
    next();
    sync();
  });

  LocalTodo.observe('after delete', function(ctx, next) {
    next();
    sync();
  });

  app.sync = sync;
};
