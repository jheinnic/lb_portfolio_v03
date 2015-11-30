'use strict';

module.exports = function(grunt, options) {
  return {
    less: {
      options: {
        paths: [
          '<%= appConfig.app %>/styles',
          '<%= appConfig.vendor %>/bootstrap/less'
        ]
      },
      dev: {
        ext: '.css',
        extDot: 'last',
        expand: true,
        cwd: '<%= appConfig.app %>/styles',
        src: ['**/*.less'],
        dest: '<%= appConfig.app %>/styles'
      }
    }
  };
};

