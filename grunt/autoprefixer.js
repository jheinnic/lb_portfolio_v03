(function(module) {
  'use strict';
  
  module.exports = function autoprefixer(grunt, options) {
    var appConfig = options.appConfig;
  
    return {
      options: {
        browsers: ['last 2 version']
      },
      build: {
        files: [
          {
            expand: true,
            cwd: appConfig.source.client,
            src: ['**/*.css'],
            dest: appConfig.temp.client
          }, {
            expand: true,
            cwd: appConfig.temp.client,
            src: ['**/*.css'],
            dest: appConfig.temp.client
          }
        ]
      }
    };
  };
}).call(this, module);
