'use strict';

module.exports = function(grunt, options) {
  function onEventFn(event) {
    console.log(event.colour, event);
  }

  function onErrorFn(err) {
    if (err.code === 'EADDRINUSE') {
      grunt.fatal('Port ' + PORT + ' is already in use by another process.', err);
    } else {
      grunt.fatal('Unexpected error with code ' + err.code, err);
    }
  }

  var lastAssetReload, PORT = 3000,
      openToRootPageFn = _.partial(
        setTimeout,
        _.partial(require('open'), 'http://localhost:' + PORT + '/'),
        3750    // Delay before browser opens navigation to root page
      ),
      afterRestartFn = _.partial(
        setTimeout,
        function onServerRestart() {
          var isDone = this.async();
          _.exists(
            'build/.reloadAssets',
            function checkForAssetsReloadRequest(exists) {
              if (exists) {
                _.stat(
                  'build/.reloadAssets',
                  function signalIfAssetsReloadRequested(stat) {
                    if (lastAssetReload !== stat.mtime) {
                      lastAssetReload = stat.mtime;
                      fs.writeFile('build/.rebooted', lastAssetReload, isDone);
                    } else {
                      isDone();
                    }
                  }
                )
              } else {
                isDone();
              }
            }
          );
        },
        1750   // Delay before signal file to grunt watch is touched
      );

  return {
    serve: {
      script: 'server/server.js',
      options: {
        cwd: __dirname,
        nodeArgs: ['--debug'],
        env: { PORT: PORT, LIVE_RELOAD: '<%= connect.options.livereload %>' },
        delay: 1250, // omit delay if you aren't serving HTML files and don't want to open a browser tab on start
        watch: ['build/.restartServer', 'build/.refreshAssets'],
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
