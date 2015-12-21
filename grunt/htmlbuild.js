(function () {
  'use strict';

  module.exports = function htmlbuild(grunt, options) {
    var appConfig = options.appConfig;

    // NOTE: dev and dist only differ insofar as dist leaves its file in temp so usemin can
    //       process it where it will find the relevant CSS files.
    // NOTE: Not as visible a difference is a conditional <!--remove--> block that flips when
    //       the target changes from 'dev' to 'dist'.
    return {
      dev: {
        src: appConfig.temp.client + '/index.html',
        dest: appConfig.dev.client + '/index.html',
        options: {
          relative: true,
          parseTag: 'htmlbuild',
          styles: {
            app: {
              cwd: appConfig.dev.client + '/' + appConfig.app,
              files: ['**/*.css', '!**/_*.css']
            }
          }
        }
      },
      dist: {
        src: appConfig.dev.client + '/index.html',
        dest: appConfig.dist.client + '/index.html',
        options: {
          parseTag: 'distbuild'
        }
      }
    };
  };
}).call();
