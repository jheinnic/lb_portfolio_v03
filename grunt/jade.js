'use strict';

module.exports = function jade(grunt, options) {
  return {
    dev: {
      options: {
        data: { isDev: true }
      },
      files: {
        cwd: '<%= appConfig.app %>',
        src: ['**/*.jade'],
        expand: true, ext: '.html', extDot: 'last',
        dest: '<%= appConfig.dev %>/client'
      }
    },
    dist: {
      options: {
        data: { isDev: false }
      },
      files: {
        cwd: '<%= appConfig.app %>',
        src: ['**/*.jade'],
        expand: true, ext: '.html', extDot: 'last',
        dest: '<%= appConfig.dist %>/client'
      }
    }
  };
};
