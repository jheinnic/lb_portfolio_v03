'use strict';

module.exports = function autoprefixer(grunt, options) {
  var appConfig = options.appConfig;
  var path = require('path');

  return {
    options: {
      browsers: ['last 2 version']
    },
    dev: {
      files: [
        {
          expand: true,
          cwd: 'client/src',
          src: [path.join(appConfig.app, '**/*.css')],
          dest: appConfig.dev.client
        }, {
          expand: true,
          cwd: appConfig.temp.client,
          src: [path.join(appConfig.app, '**/*.css')],
          dest: appConfig.dev.client
        }
      ]
    },
    dist: {
      files: [
        {
          expand: true,
          cwd: 'client/src',
          src: [path.join(appConfig.app, '**/*.css')],
          dest: appConfig.dist.client
        },
        {
          expand: true,
          cwd: appConfig.temp.client,
          src: [path.join(appConfig.app, '**/*.css')],
          dest: appConfig.dist.client
        }
      ]
    }
  };
};
