(function (console) {
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

  module.exports = function replication(app) {
    var LocalTodo = app.models.LocalTodo;
    var RemoteTodo = app.models.RemoteTodo;

    var modelPairs = [
      {app: app, local: LocalTodo, remote: RemoteTodo}
    ];

    modelPairs.forEach(handleModelPairing);
  };

  function handleModelPairing(modelPair) {
    wireModelPair(modelPair.app, modelPair.local, modelPair.remote);
  }

  function legacyChangedObserver(arg) {
    if (!arg) {
      arg = 'legacy';
    }
    console.log('Fired on changed for ' + arg);
  }

  function legacyDeletedObserver(arg) {
    if (!arg) {
      arg = 'legacy';
    }
    console.log('Fired on deleted for ' + arg);
  }

  function wireModelPair(modelPair) {
    var app = modelPair.app;
    var localModel = modelPair.localModel;
    var remoteModel = modelPair.remoteModel;

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

    // TODO: What to do with cb??
    // function sync(cb) {
    function sync() {
      console.log('sync triggered');
      localModel.replicate(
        remoteModel,
        since.push,
        function pushed(err, conflicts, cps) {
          console.log('LR', arguments);
          since.push = cps;
          remoteModel.replicate(
            localModel,
            since.pull,
            function pulled(err, conflicts, cps) {
              console.log('RL', arguments);
              since.pull = cps;
            });
        });
    }

    // sync local changes if connected

    localModel.on('changed', legacyChangedObserver);
    localModel.on('deleted', legacyDeletedObserver);

    localModel.observe('after save', function (ctx, next) {
      legacyChangedObserver('observer');
      next();
      sync();
    });

    localModel.observe('after delete', function (ctx, next) {
      legacyDeletedObserver('observer');
      next();
      sync();
    });

    app.sync = sync;
  };
}).call(window, window.console);
