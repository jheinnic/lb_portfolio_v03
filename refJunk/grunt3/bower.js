'use strict';

module.exports = function bower(/*grunt, options*/) {
  // var path = require('path');

  return {
    install: {
      options: {
        // targetDir: path.resolve(__dirname, '<%= appConfig.vendor %>'),
        targetDir: '<%= appConfig.vendor %>',
        cleanup: true
      }
    },
    update: {
      options: {
        // targetDir: path.resolve(__dirname, '<%= appConfig.vendor %>'),
        targetDir: '<%= appConfig.vendor %>',
        cleanup: true
      }
    }
  };
};
