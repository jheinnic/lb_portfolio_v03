'use strict';

var pkg = require('./utils/pkg');
module.exports = function (grunt, options) {
  return {
    dev: {
      cwd: '<%= appConfig.dev %>/client',
      src: 'index.html',
      dest: '<%= appConfig.dev %>/client',
      ignorePath: /^\.\.\/\.\.\/\.\.\/client\//
    },
    dist: {
      cwd: '<%= appConfig.dist %>/client',
      src: 'index.html',
      dest: '<%= appConfig.dist %>/client',
      ignorePath: /^\.\.\/\.\.\/\.\.\/client\//
    }
  };
};
