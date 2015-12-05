'use strict';

// var pkg = require('./utils/pkg');
module.exports = function (grunt, options) {
  return {
    dist: {
      src: [
        // This would likely break Angular code references to templates...
        appConfig.dist + '/client/*.js',
        appConfig.dist + '/client/**/*.{html,css,bmp,webp,png,jpg,jpeg,gif,svg,eot,ttf,woff,woff2}'
      ]
    }
  };
};
