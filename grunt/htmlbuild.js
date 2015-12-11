'use strict';

module.exports = function htmlbuild(grunt, options) {
  return {
    dev: {
      src: '<%= appConfig.dev %>/client/index.html',
      dest: '<%= appConfig.dev %>/client',
      options: {
        parseTag: 'htmlbuild',
        relative: true,
        styles: {
          bundle: ['<%= appConfig.dev %>/client/**/*.css', '!<%= appConfig.dev %>/client/**/_*.css']
        }
      }
    },
    dist: {
      src: '<%= appConfig.dist %>/client/index.html',
      dest: '<%= appConfig.dist %>/client',
      options: {
        parseTag: 'htmlbuild',
        relative: true,
        styles: {
          bundle: ['<%= appConfig.dist %>/client/**/*.css', '!<%= appConfig.dist %>/client/**/_*.css']
        }
      }
    }
  };
};
