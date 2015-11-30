'use strict';

var pkg = require('./utils/pkg');
module.exports = function (grunt, options) {
  return {
    dist: {
      src: [
        // This would likely break Angular code references to templates...
        appConfig.dist + '/client/*.js',
        appConfig.dist + '/client/styles/**/*.css',
        appConfig.dist + '/client/styles/fonts/**/*',
        appConfig.dist + '/client/fonts/**/*',
        appConfig.dist + '/client/views/**/*.html',
        appConfig.dist + '/client/images/**/*.{png,jpg,jpeg,gif,webp,svg}'
      ]
    }
  };
};
