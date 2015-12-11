'use strict';

module.exports = function less(grunt, options) {
  return {
    options: {
      paths: [
        '<%= appConfig.app %>',
        '<%= appConfig.vendor %>/bootstrap/less'
      ]
    },
    build: {
      ext: '.css',
      extDot: 'last',
      expand: true,
      cwd: '<%= appConfig.app %>',
      src: ['**/*.less'],
      dest: '<%= appConfig.temp %>/client'
    }
  };
};

