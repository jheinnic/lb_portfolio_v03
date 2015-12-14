'use strict';

module.exports = function less(/*grunt, options*/) {
  return {
    options: {
      paths: ['<%= appConfig.app %>', '<%= appConfig.vendor %>/bootstrap/less']
    },
    build: {
      expand: true,
      cwd: '<%= appConfig.app %>',
      src: '**/*.less',
      dest: '<%= appConfig.temp %>/client',
      ext: '.css',
      extDot: 'last'
    }
  };
};

