(function () {
  'use strict';

  module.exports = function ngAnnotate(grunt, options) {
    var appConfig = options.appConfig;

    var retVal = {
      options: {},
      build: {
        files: {}
      }
    };
    retVal.build.files[appConfig.dist.client + '/app.js'] = appConfig.dev.client + '/app.js';

    return retVal;
  };
}).call();
