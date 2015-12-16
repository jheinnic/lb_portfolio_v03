(function () {
  'use strict';

  module.exports = function usemin(grunt, options) {
    var appConfig = options.appConfig;

    return {
      html: [appConfig.dist.client + '/**/*.html'],
      css: [appConfig.dist.client + '/**/*.css'],
      options: {
        assetsDirs: [appConfig.dist.client],
        patterns: []
      }
    };
  };
}).call();
