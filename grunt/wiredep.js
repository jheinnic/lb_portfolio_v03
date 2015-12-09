'use strict';

var pkg = require('./utils/pkg');
module.exports = function (grunt, options) {
  return {
    dev: {
      src: ['<%= appConfig.dev %>/client/index.html'],
      ignorePath: /^\.\.\/\.\.\/\.\.\/client\//
    }
  };
};
