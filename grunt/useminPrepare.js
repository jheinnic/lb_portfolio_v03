'use strict';

var pkg = require('./pkg');
module.exports = function (grunt, options) {
  return {
    useminPrepare: {
      html: [
        '<%= yeoman.dist %>/index.html',
        '<%= yeoman.dist %>/views/**/*.html'
      ],
      options: {
        root: '<%= yeoman.dist %>/',
        staging: '<%= yeoman.staging %>/',
        dest: '<%= yeoman.dist %>/',
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
