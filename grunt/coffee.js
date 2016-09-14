(function(module) {
  'use strict';
  
  module.exports = function less(grunt, options) {
    var appConfig = options.appConfig;
  
    return {
      options: {
        paths: [
          appConfig.source.client,
          appConfig.vendor + '/bootstrap/less'
        ]
      },
      build: {
        expand: true,
        cwd: appConfig.source.client,
        src: '**/*.coffee',
        dest: appConfig.dev.client,
        extDot: 'last',
        ext: '.js'
      }
    };
  };
}).call(this, module); 
