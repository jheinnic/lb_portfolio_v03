'use strict';

module.exports = function autoprefixer(/*grunt, options*/) {
  return {
    options: {
      browsers: ['last 2 version']
    },
    dev: {
      files: [
        { expand: true, cwd: '<%= appConfig.app %>', src: ['**/*.css'], dest: '<%= appConfig.dev %>/client' },
        { expand: true, cwd: '<%= appConfig.temp %>/client', src: ['**/*.css'], dest: '<%= appConfig.dev %>/client' }
      ]
    }
  };
};

