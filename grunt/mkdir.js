'use strict';

module.exports = function mkdir(grunt, options) {
  var appConfig = options.appConfig;

  return {
    build: {
      options: {
        mode: 755,
        create: [
          appConfig.dev.client, appConfig.dist.client, appConfig.temp.client,
          appConfig.dev.common, appConfig.dist.common, appConfig.temp.common,
          appConfig.dev.server, appConfig.dist.server, appConfig.temp.server,
          appConfig.vendor, appConfig.node, 'bower_components', 'node_modules'
        ]
      }
    }
  };
};
