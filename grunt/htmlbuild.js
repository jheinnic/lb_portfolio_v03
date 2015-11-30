'use strict';

var pkg = require('./utils/pkg');
module.exports = function (grunt, options) {
  return {
    useminPrepare: {
      html: [
        '<%= appConfig.dist %>/index.html',
        '<%= appConfig.dist %>/views/**/*.html'
      ],
      options: {
        root: '<%= appConfig.dist %>/',
        staging: '<%= appConfig.temp %>/',
        dest: '<%= appConfig.dist %>/',
        flow: {
          html: {
            steps: {
              js: ['concat', 'uglifyjs'],
              css: ['concat', 'cssmin']
            },
            post: {}
          }
        }
      }
    }
  };
};
