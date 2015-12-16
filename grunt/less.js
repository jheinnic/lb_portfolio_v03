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
      src: '**/*.less',
      dest: appConfig.dev.client,
      ext: '.css',
      extDot: 'last'
    }
  };
};

