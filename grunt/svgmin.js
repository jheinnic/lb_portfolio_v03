(function () {
  'use strict';

  module.exports = function svgmin(grunt, options) {
    var appConfig = options.appConfig;

    return {
      dist: {
        files: [
          {
            expand: true,
            dot: true,
            cwd: appConfig.source.client,
            src: '**/*.svg',
            dest: appConfig.dist.client
          }
        ]
      }
    };
  };
}).call();
