'use strict';

var pkg = require('./pkg');
module.exports = function (grunt, options) {
  return {
    filerev: {
      dist: {
        src: [
          // This would likely break Angular code references to templates...
          '<%= yeoman.dist %>/*.js',
          '<%= yeoman.dist %>/views/**/*.html',
          '<%= yeoman.dist %>/styles/**/*.css',
          '<%= yeoman.dist %>/images/**/*.{png,jpg,jpeg,gif,webp,svg}',
          '<%= yeoman.dist %>/styles/fonts/**/*',
          '<%= yeoman.dist %>/fonts/**/*'
        ]
      }
    }
  };
};
