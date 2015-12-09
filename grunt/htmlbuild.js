'use strict';

module.exports = function (grunt, options) {
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
    }
  };
};
