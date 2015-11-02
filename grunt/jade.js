'use strict';

var pkg = require('./pkg');
module.exports = function (grunt, options) {
  return {
    jade: {
      dev: {
        options: {
          data: function () {
            return {
              isDevelopment: true,
              // stagedScripts: stagedScripts,
              stagedStyles: stagedStyles
            };
          }
        },
        files: {
          '<%= yeoman.staging %>/index.html': '<%= yeoman.app %>/index.jade'
        }
      },
      dist: {
        options: {
          data: function () {
            return {
              isDevelopment: false,
              // stagedScripts: stagedScripts,
              stagedStyles: stagedStyles
            };
          }
        },
        files: {
          '<%= yeoman.dist %>/index.html': '<%= yeoman.app %>/index.jade'
        }
      }
    }
  };
};
