(function () {
  'use strict';

  module.exports = function (app) {
    // TODO: Use a config property that defines whether to serve assets or to run as API-only
    // if (app.get('isDevEnv') === false) return;

    var path = require('path');
    var serveDir = app.loopback.static;

    // Build configuration can allow dev/dist builds to happen anywhere, but it always leaves:
    // 1) 'client' a sibling of 'server'
    // 2) 'boot' a child of 'server'
    // So the path from a script in boot to the content root in client is always ../../client.
    app.use(
      serveDir(
        path.resolve(__dirname, '../../client')
      )
    );
  };
}).call();
