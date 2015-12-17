'use strict';

module.exports = function (grunt, options) {
  return {
    options: {
      sourceMap: true
    },
    dist: {
      files: [
        {
          expand: true,
          cwd: appConfig.app,
          src: ['toy.js'],
          dest: appConfig.dev + 'client/scripts'
        }
      ]
    }
  }
};
