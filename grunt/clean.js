'use strict';

var pkg = require('./pkg');
module.exports = function (grunt, options) {
  return {
    clean: {
      dist: {
        files: [
          {
            dot: true,
            src: [
              '<%= yeoman.dist %>/'
          }
      }
    }
  };
};
