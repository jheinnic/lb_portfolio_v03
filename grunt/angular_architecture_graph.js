(function () {
  'use strict';

  module.exports = function angularArchitectureGraph(grunt, options) {
    var appConfig = options.appConfig;

    var retVal = {
      diagram: {
        files: {
        }
      }
    };
    retVal.diagram.files[appConfig.dist.client + '/architecture'] = [appConfig.dist.client + '/app.js'];

    return retVal;
  };
}).call();
