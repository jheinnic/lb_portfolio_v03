(function () {
  'use strict';

  module.exports = function (app) {
    var restApiRoot = app.get('restApiRoot');
    var explorer = require('loopback-explorer');

    explorer(
      app, {
        mountPath: '/explorer',
        basePath: restApiRoot
      }
    );

    app.once(
      'started', function () {
        var baseUrl = app.get('url').replace(/\/$/, '');
        console.log('Browse your REST API at %s%s', baseUrl, '/explorer');
      }
    );
  };
}).call();
