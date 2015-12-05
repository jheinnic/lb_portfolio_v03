'use strict';

var pkg = require('./utils/pkg');
module.exports = function (grunt, options) {
  return {
    index: '<%= appConfig.dist %>/client/index.html',
    views: '<%= appConfig.dist %>/client/views/**/*.html',
    options: {
      root: '<%= appConfig.dist %>/',
      staging: '<%= appConfig.temp %>/',
      dest: '<%= appConfig.dist %>/',
      flow: {
        index: {
          steps: {
            js: ['concat', 'uglifyjs'],
            css: ['cssmin']
          },
          post: {}
        },
        views: {
          steps: {},
          post: {}
        }
      }
    }
  };
};
