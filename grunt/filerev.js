(function() {
  'use strict';

  module.exports = function filerev(grunt, options) {
    var appConfig = options.appConfig;

    return {
      dist: {
        src: [
          // appConfig.dist.client + '/{app,vendor}.js',
          // TODO: This could break templates from within Angular scripts and/or template files...  Check both!!!
          appConfig.dist.client + '/**/*.{js,html,css,bmp,webp,png,jpg,jpeg,gif,svg,eot,ttf,woff,woff2}'
        ]
      }
    };
  };
}).call();
