'use strict';

module.exports = function(grunt, options) {
  return {
    dev: {
      script: 'server/server.js', options: {
        nodeArgs: ['--debug'], watch: ['<%= appConfig.dev %>/**/*'], env: {
          PORT: 3000, LIVE_RELOAD: '<%= connect.options.livereload %>'
        }, cwd: __dirname, ignore: [''], ext: 'js,coffee,json,css,png,jpg,gif,svg,ttf', delay: 1250, // omit this property if you aren't serving HTML files and
        // don't want to open a browser tab on start
        callback: function (nodemon) {
          nodemon.on(
            'log', function (event) {
              // console.log(event.colour);
              console.log(event);
            }
          );

          // opens browser on initial server start
          nodemon.on(
            'config:update', function () {
              // Delay before browser opens navigation to root page
              // var portConfig = grunt.config.get().nodemon.dev.options.env.PORT;
              var portConfig = 3000;
              setTimeout(
                function () {
                  require('open')('http://localhost:' + portConfig);
                }, 3500
              );
            }
          );

          // refreshes browser when server reboots
          nodemon.on(
            'restart', function () {
              // Delay before server listens on port by triggering a liveReload signal.
              setTimeout(
                function () {
                  require('fs').writeFileSync('.rebooted', 'rebooted');
                }, 1800
              );
            }
          );

          nodemon.on(
            'error', function (err) {
              if (err.code === 'EADDRINUSE') {
                var portConfig = '<%= nodemon.dev.options.env.PORT %>';
                grunt.fatal('Port ' + portConfig + ' is already in use by another process.', err);
              }
              else {
                grunt.fatal(err);
              }
            }
          );
        }
      }
    }
  };
};
