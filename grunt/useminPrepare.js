(function () {
  'use strict';

  module.exports = function useminPrepare(grunt, options) {
    var appConfig = options.appConfig;

    return {
      index: appConfig.dist.client + '/index.html',
      views: appConfig.dev.client + '/' + appConfig.app + '/**/*.html',
      options: {
        root: appConfig.dist.client,
        staging: appConfig.temp.client,
        dest: appConfig.dist.client,
        flow: {
          index: {
            steps: {
              js: ['concat', 'uglifyjs'],
              css: ['cssmin']
            },
            post: {}
          },
          views: {
            steps: {},
            post: {}
          }
        }
      }
    };
  };
}).call();
