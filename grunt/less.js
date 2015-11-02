'use strict';

module.exports = function(grunt, options) {
  return {
    less: {
      options: {
        paths: [
          '<%= yeoman.app %>/styles',
          '<%= yeoman.vendor %>/bootstrap/less'
        ]
      },
      dist: {
        ext: '.css',
        extDot: 'last',
        expand: true,
        cwd: '<%= yeoman.app %>/styles',
        src: ['**/*.less'],
        dest: '<%= yeoman.staging %>/less/'
      }
    }
  };
};

