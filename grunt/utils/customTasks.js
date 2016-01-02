(
  function () {
    'use strict';

    module.exports = function (grunt, appConfig) {
      var fs = require('fs');
      var path = require('path');

      grunt.registerTask(
        'bundleClient', 'Build browserify bundle including loopback client and angular application code',
        function callBuildNgAppBundle(a, b, c, d, e) {
          console.log('buildBundleArgs', a, b, c, d, e);
          require('./buildBundle')(grunt, appConfig, this.async());
        }
      );

      grunt.registerTask(
        'restartServer', 'Signal nodemon through its watch file that a change in the server code base has occurred',
        function signalRestartServer(a, b, c, d, e) {
          console.log('signalRestartServer', a, b, c, d, e);
          fs.writeFile(appConfig.temp.server + '/.restartServer', '', this.async());
        }
      );

      grunt.registerTask(
        'reloadAssets', 'Signal nodemon through its watch file that a change in the assets directory has occurred',
        function signalReloadAssets(a, b, c, d, e) {
          console.log('signalReloadAssets', a, b, c, d, e);
          fs.writeFile(appConfig.temp.server + '/.reloadAssets', '', this.async());
        }
      );

      /**
       * Normalizes linefeeds for htmlbuild by ensuring linefeeds are injected such that HTML
       * comment tags begin and end on distinct lines and do not share those lines with any
       * real document hypertext.
       */
      grunt.registerTask(
        'fixIndexHtml', function fixIndexHtml() {
          var indexHtml = path.join(process.cwd(), appConfig.temp.client, 'index.html');
          var done = this.async();

          fs.readFile(
            indexHtml, function onReadData(err, data) {
              if (err) {
                console.error(err);
                throw err;
              }

              fs.writeFile(
                indexHtml, grunt.util.normalizelf(
                  data.utf8Slice(0)
                ).replace(
                  /><!--/g, '>' + grunt.util.linefeed + '<!--'
                ).replace(
                  /--></g, '-->' + grunt.util.linefeed + '<'
                ), done
              );
            }
          );
        }
      );
    };
  }
).call();
