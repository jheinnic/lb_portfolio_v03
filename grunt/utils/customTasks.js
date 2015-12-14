(
  function () {
    'use strict';

    module.exports = function (grunt, appConfig) {
      var fs = require('fs');

      grunt.registerTask(
        'bundle-client',
        'Build browserify bundle including loopback client and angular application code',
        function callBuildNgAppBundle(a, b, c, d, e) {
          console.log('buildBundleArgs', a, b, c, d, e);
          require('./buildBundle')(grunt, appConfig, this.async());
        }
      );

      grunt.registerTask(
        'restart-server',
        'Signal nodemon through its watch file that a change in the server code base has occurred',
        function signalRestartServer(a, b, c, d, e) {
          console.log('signalRestartServer', a, b, c, d, e);
          fs.writeFile(appConfig.temp.server + '/.restartServer', '', this.async());
        }
      );

      grunt.registerTask(
        'reload-assets',
        'Signal nodemon through its watch file that a change in the assets directory has occurred',
        function signalReloadAssets(a, b, c, d, e) {
          console.log('signalReloadAssets', a, b, c, d, e);
          fs.writeFile(appConfig.temp.server + '/.reloadAssets', '', this.async());
        }
      );
    };
  }
).call();
