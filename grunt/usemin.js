'use strict';

var pkg = require('./utils/pkg');
module.exports = function (grunt, options) {
  return {
    html: [
      '<%= appConfig.dist %>/client/index.html',
      '<%= appConfig.dist %>/client/assets/views/**/*.html'
    ],
    // css: ['<%= appConfig.staging %>/styles/**/*.css'],
    css: ['<%= appConfig.dist %>/client/assets/styles/**/*.css'],
    options: {
      assetsDirs: [
        '<%= appConfig.dist %>/client/',
        '<%= appConfig.dist %>/client/assets',
        '<%= appConfig.dist %>/client/assets/views/',
        '<%= appConfig.dist %>/client/assets/fonts/',
        '<%= appConfig.dist %>/client/assets/images/',
        '<%= appConfig.dist %>/client/assets/scripts/',
        '<%= appConfig.dist %>/client/assets/styles/',
        '<%= appConfig.dist %>/client/assets/styles/fonts/'
      ]
    }
  };
};
