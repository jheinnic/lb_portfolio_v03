(
  function () {
    'use strict';

    module.exports = function (grunt, appConfig) {
      var fs = require('fs');
      var path = require('path');

      grunt.registerTask(
        'bundle-client', 'Build browserify bundle including loopback client and angular application code',
        function callBuildNgAppBundle(a, b, c, d, e) {
          console.log('buildBundleArgs', a, b, c, d, e);
          require('./buildBundle')(grunt, appConfig, this.async());
        }
      );

      grunt.registerTask(
        'restart-server', 'Signal nodemon through its watch file that a change in the server code base has occurred',
        function signalRestartServer(a, b, c, d, e) {
          console.log('signalRestartServer', a, b, c, d, e);
          fs.writeFile(appConfig.temp.server + '/.restartServer', '', this.async());
        }
      );

      grunt.registerTask(
        'reload-assets', 'Signal nodemon through its watch file that a change in the assets directory has occurred',
        function signalReloadAssets(a, b, c, d, e) {
          console.log('signalReloadAssets', a, b, c, d, e);
          fs.writeFile(appConfig.temp.server + '/.reloadAssets', '', this.async());
        }
      );

      grunt.registerTask(
        'fixIndexHtml', function fixIndexHtml() {
          var indexHtml = path.join(process.cwd(), appConfig.dev.client, 'index.html');
          var done = this.async();

          fs.readFile(
            indexHtml, function onReadData(err, data) {
              if (err) {
                console.error(err);
                throw err;
              }

              console.error(err);
              console.log(data.utf8Slice(0));
              fs.writeFile(
                indexHtml,
                grunt.util.normalizelf(data.utf8Slice(0)
                ).replace(/><!--/g, '>' + grunt.util.linefeed + '<!--'
                ).replace(/--></g, '-->' + grunt.util.linefeed + '<'
                ),
                done
              );
            }
          );
        }
      );
    };
  }
).call();
