(function () {
  'use strict';

  // Mount static files like ngapp
  // All static middleware should be registered at the end, as all requests
  // passing the static middleware are hitting the file system
  module.exports = function bootDevAssets(app) {
    // TODO: Use a config property that defines whether to serve assets or to
    //       run as API-only and use same to help loopback-angular-sdk locate
    //       rootApiUrl.
    var path = require('path');
    var indexFile = app.get('indexFile');
    var assetDir = path.normalize(
        path.join(
            process.cwd(),
            path.dirname(indexFile)
        )
    );

    console.log('** Assets are in ', assetDir);

    app.use(app.loopback.static(assetDir));
  };
}).call();
