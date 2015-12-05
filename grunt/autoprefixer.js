'use strict';

module.exports = function(grunt, options) {
  return {
    options: {
      browsers: ['last 2 version']
    },
    dist: {
      files: [
        { expand: true, cwd: '<%= appConfig.app %>', src: ['**/*.css'], dest: '<%= appConfig.dev %>/client' },
        { expand: true, cwd: '<%= appConfig.temp %>/less', src: ['**/*.css'], dest: '<%= appConfig.dev %>/client' }
      ]
    }
  };
};

