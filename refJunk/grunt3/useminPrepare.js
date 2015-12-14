'use strict';

module.exports = function useminPrepare(/*grunt, options*/) {
  return {
    index: '<%= appConfig.dist %>/client/index.html',
    views: '<%= appConfig.dev %>/client/jchptf/**/*.html',
    options: {
      root: '<%= appConfig.dist %>/',
      staging: '<%= appConfig.temp %>/',
      dest: '<%= appConfig.dist %>/',
      flow: {
        index: {
          steps: {
            js: ['concat', 'uglifyjs'],
            css: ['cssmin']
          },
          post: {}
        },
        views: {
          steps: {},
          post: {}
        }
      }
    }
  };
};
