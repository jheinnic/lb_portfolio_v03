'use strict';

module.exports = function(grunt, options) {
  return {
    autoprefixer: {
      options: {
        browsers: ['last 1 version']
      },
      dist: {
        files: [
          { expand: true, cwd: '<%= yeoman.app %>/styles/', src: ['**/*.css'], dest: '<%= yeoman.staging %>/styles/' },
          { expand: true, cwd: '<%= yeoman.staging %>/less/', src: ['**/*.css'], dest: '<%= yeoman.staging %>/styles/' }
        ]
      }
    }
  };
};

