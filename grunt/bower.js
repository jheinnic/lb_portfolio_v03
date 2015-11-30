'use strict';

module.exports = function (grunt, options) {
  return {
    install: {
      options: {
        targetDir: path.resolve(__dirname, appConfig.vendor),
        cleanup: true
      }
    }
  };
};
