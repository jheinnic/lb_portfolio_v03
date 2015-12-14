'use strict';

module.exports = function wiredep(/*grunt, options*/) {
  var bowerJson = require('../bower.json');

  return {
    dev: {
      bowerJson: bowerJson,
      directory: '<%= appConfig.vendor %>',
      ignorePath: /^\.\.\/\.\.\/\.\.\/client\//,
      src: '<%= appConfig.dev %>/client/index.html'
    },
    dist: {
      bowerJson: bowerJson,
      directory: '<%= appConfig.vendor %>',
      ignorePath: /^\.\.\/\.\.\/\.\.\/client\//,
      src: '<%= appConfig.dist %>/client/index.html'
    }
  };
};
