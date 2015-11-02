'use strict';

var pkg = require('./pkg');
module.exports = function (grunt, options) {
  return {
    wiredep: {
      dev: {
        src: ['<%= yeoman.staging %>/index.html'],
        ignorePath: /^\.\.\/client\/ngapp\//
      },
      dist: {
        src: ['<%= yeoman.dist %>/index.html'],
        ignorePath: /^\.\.\/ngapp\//
      }
    }
  };
};
