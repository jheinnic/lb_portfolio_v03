(function () {
  'use strict';

  // Mount static files like ngapp
  // All static middleware should be registered at the end, as all requests
  // passing the static middleware are hitting the file system
  module.exports = function bootDevAssets(app) {
    // TODO: Use a config property that defines whether to serve assets or to run as API-only
    //       and use same to help loopback-angular-sdk locate apiUrlRoot.
    var path = require('path');
    var indexFile = app.get('indexFile');
    console.log('** Index file is at ' + indexFile);
    var assetDir = path.dirname(indexFile);
    console.log( '** Assets are in ', assetDir );
    app.use( app.loopback.static(assetDir) );
    app.use( '/', app.loopback.static(indexFile);
    app.use( '/index.html', app.loopback.static(indexFile);

    console.log('boot 60 routes set');
  };
}).call();
