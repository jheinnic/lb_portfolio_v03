(function () {
  'use strict';

  module.exports = function nodemon(grunt, options) {
    var appConfig = options.appConfig;
    var _ = require('lodash');
    var fs = require('fs');

    function onEventFn(event) {
      console.log(event.colour);
    }

    function onErrorFn(err) {
      if (err.code === 'EADDRINUSE') {
        grunt.fatal('Port ' + PORT + ' is already in use by another process.', err);
      } else {
        grunt.fatal('Unexpected error with code ' + err.code, err);
      }
    }

    var lastAssetReload, PORT = 3000;
    var openToRootPageFn = _.partial(
      setTimeout,
      _.partial(
        require('open'),
        'http://localhost:' + PORT + '/',
        function(err) {
          if (err) {
            console.error('Failed to open http://localhost:' + PORT + '/', err);
          }
        }
      ), 3750
    );

    // Signal .rebooted, but only if the reason for restart was because .reloadAssets was new, not
    // .restartServer.
    var afterRestartFn = _.partial(
      setTimeout,
      function onServerRestart() {
        console.log('Handling after restart');
        fs.exists(
          'build/.reloadAssets',
          function checkForAssetsReloadRequest(exists) {
            if (exists) {
              fs.stat(
                'build/.reloadAssets',
                function signalIfAssetsReloadRequested(stat) {
                  if (lastAssetReload !== stat.mtime) {
                    lastAssetReload = stat.mtime;
                    fs.writeFile(appConfig.temp.server + '/.rebooted', lastAssetReload);
                  }
                }
              );
            }
          }
        );
      }, 1750   // Delay before signal file to grunt watch is touched
    );

    return {
      serve: {
        script: appConfig.source.server + '/server.js',
        options: {
          cwd: process.cwd(),
          nodeArgs: ['--debug'],
          env: {
            PORT: PORT,
            LIVE_RELOAD: appConfig.ports.liveReload
          },
          delay: 1250, // omit delay if you aren't serving HTML files and don't want to open a browser tab on start
          watch: [appConfig.temp.server + '/.restartServer', appConfig.temp.server + '/.reloadAssets'],
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
  };
}).call();
