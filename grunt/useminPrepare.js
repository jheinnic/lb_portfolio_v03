(function () {
  'use strict';

  module.exports = function useminPrepare(grunt, options) {
    var appConfig = options.appConfig;

    return {
      html: appConfig.dist.client + '/**/*.html',
      options: {
        root: appConfig.source.client,
        staging: appConfig.temp.client,
        dest: appConfig.dist.client,
        flow: {
          steps: {
            js: ['concat', 'uglifyjs'],
            css: ['cssmin']
          },
          post: {}
        }
      }
    };
  };
}).call();
