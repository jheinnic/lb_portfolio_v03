'use strict';

module.exports = function(grunt, options) {
  return {
    options: {
      paths: [
        '<%= appConfig.app %>',
        '<%= appConfig.vendor %>/bootstrap/less'
      ]
    },
    dev: {
      ext: '.css',
      extDot: 'last',
      expand: true,
      cwd: '<%= appConfig.app %>',
      src: ['**/*.less'],
      dest: '<%= appConfig.temp %>/less'
    }
  };
};

