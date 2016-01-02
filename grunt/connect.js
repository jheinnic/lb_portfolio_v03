(function () {
  'use strict';

  module.exports = function connect(grunt, options) {
    var appConfig = options.appConfig;

    // TODO: See that vendor gets copied to appConfig.dev and then drop the
    //       connect.use() calls for '/vendor'.
    return {
      livereload: {
        options: {
          livereload: appConfig.ports.liveReload,
          keepalive: true
        }
      },
      serve: {
        options: {
          // hostname: 'localhost',
          // host: 'localhost',
          base: '/',
          port: appConfig.ports.assets,
          livereload: appConfig.ports.liveReload,
          keepalive: true,
          directory: appConfig.dev.client,
          environment: {
            REST_API_URL: 'http://localhost:3000/api'
          }
          //middleware: function (connect) {
          //  return [
          //    connect.static(appConfig.dev.client),
          //    connect().use('/vendor', connect.static(appConfig.vendor))
          //  ];
          //}
        }
      },
      test: {
        options: {
          base: '/',
          port: 9999,
          directory: appConfig.dev.client
          //middleware: function (connect) {
          //  return [
          //    connect.static(appConfig.dev.client),
          //    connect().use('/vendor', appConfig.vendor)
          //  ];
          //}
        }
      },
      coverage: {
        options: {
          port: 5555,
          keepalive: true,
          base: 'coverage/',
          directory: 'coverage/'
        }
      }
    };
  };
}).call();
