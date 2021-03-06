(function () {
  'use strict';

  module.exports = function nodemon(grunt, options) {
    var appConfig = options.appConfig;
    var _ = require('lodash');
    var fs = require('fs');

    // console.log(setTimeout);

    function onEventFn(event) {
      console.log(event.colour);
    }

    function onErrorFn(err) {
      if (err.code === 'EADDRINUSE') {
        grunt.fatal('Port ' + appConfig.ports.restApi + ' is already in use by another process.', err);
      } else {
        grunt.fatal('Unexpected error with code ' + err.code, err);
      }
    }

    var openToRootPageFn = _.partial(
      setTimeout,
      _.partial(
        require('open'),
        'http://localhost:' + appConfig.ports.restApi + '/explorer',
        function(err) {
          if (err) {
            console.error('Failed to open http://localhost:' + appConfig.ports.restApi + '/', err);
          } else {
            require('open')('http://localhost:8080?port=5858');
          }
        }
      ), 8000
    );

    // Signal .rebooted, but only if the reason for restart was because .reloadAssets was new, not
    // .restartServer.
    var lastAssetReload = -1;
    var reloadAssetsFile = appConfig.temp.server + '/.reloadAssets';
    var afterRestartFn = _.partial(
      setTimeout,
      function onServerRestart() {
        console.log(reloadAssetsFile);
        fs.exists(
          reloadAssetsFile,
          function checkForAssetsReloadRequest(exists) {
            if (exists) {
              fs.stat(
                reloadAssetsFile,
                function signalIfAssetsReloadRequested(stat) {
                  if (_.isObject(stat)) {
                    if (_.isUndefined(lastAssetReload) || (lastAssetReload !== stat.mtime)) {
                      lastAssetReload = stat.mtime;
                      fs.writeFile(appConfig.temp.server + '/.rebooted', lastAssetReload);
                      console.log('Server signals it restarted for an asset reload');
                    } else {
                      console.log(
                        'Server was restarted without need for asset reload.  (lastReload=%s, mtime=%s)', lastAssetReload, stat.mtim
                      );
                    }
                  } else {
                    console.log(
                      'Server was restarted, asset reload request file exists but its last modification could not be determined', stat
                    );
                  }
                }
              );
            } else {
              console.log('Server was restarted, but asset reload request file does not even exist yet.');
            }
          }
        );
      }, 1750   // Delay before signal file to grunt watch is touched
    );

    var retVal = {
      serve: {
        script: appConfig.source.server + '/server.js',
        options: {
          cwd: process.cwd(),
          nodeArgs: ['--debug'],
          env: {
            REST_API_PORT: Number(appConfig.ports.restApi),
            LIVE_RELOAD: Number(appConfig.ports.liveReload)
          },
          delay: 3250, // omit delay if you aren't serving HTML files and don't want to open a browser tab on start
          watch: [
            appConfig.temp.server + '/.restartServer',
            appConfig.temp.server + '/.reloadAssets',
            appConfig.source.server + '/**/*'
          ],
          // ignore: [''],
          // ext: 'js,coffee,json,css,bmp,png,jpg,gif,svg,ttf,woff,woff2',
          callback: function (nodemon) {
            // opens browser on initial server start
            nodemon.on('config:update', openToRootPageFn);

            // refreshes browser when server reboots, by signaling grunt:watch to signal connect:liveReload.
            nodemon.on('restart', afterRestartFn);

            // Logs a signaled event to the console
            nodemon.on('log', onEventFn);

            // Logs a trapped error to the console
            nodemon.on('error', onErrorFn);
          }
        }
      }
    };
    // console.log('Nodemon dynamic configuration block is: ');
    // console.log(retVal);
    // console.log(retVal.serve.options.nodeArgs);
    // console.log(retVal.serve.options.env);
    // console.log(retVal.serve.options.watch);
    return retVal;
  };
}).call();
