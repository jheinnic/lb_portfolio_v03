'use strict';

module.exports = function mkdir(grunt, options) {
  var appConfig = options.appConfig;
  var path = require('path');

  return {
    build: {
      options: {
        mode: '755',
        create: [
          appConfig.vendor, appConfig.node, 'bower_components', 'node_modules',
          appConfig.dist.client, appConfig.dist.common, appConfig.dist.server,
          appConfig.dev.client, appConfig.dev.common, appConfig.temp.server,
	  appConfig.temp.client, path.join(appConfig.temp.client, appConfig.app, 'lbServices')
        ]
      }
    }
  };
};
