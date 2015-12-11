'use strict';

var pkg = require('./utils/pkg');
module.exports = function (grunt, options) {
  return {
    html: ['<%= appConfig.dist %>/client/**/*.html'],
    css: ['<%= appConfig.dist %>/client/**/*.css'],
    options: {
      assetsDirs: ['<%= appConfig.dist %>/client']
    }
  };
};
