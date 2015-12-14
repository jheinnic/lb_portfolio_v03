'use strict';

module.exports = function clean(grunt, options) {
  var appConfig = options.appConfig;

  return {
    options: { force: false, 'no-write': false },
    build: [ appConfig.temp.root + '/*', appConfig.dist.root + '/*', appConfig.dev.root + '/*' ],
    vendor: [ appConfig.vendor + '/*' ],
    node: [ appConfig.nodeModules + '/*' ],
    dependencies: [appConfig.vendor + '/*', appConfig.nodeModules + '/*'],
    fullWipe: [
      appConfig.temp.root + '/*',
      appConfig.dist.root + '/*', appConfig.dev.root + '/*',
      appConfig.vendor + '/*', appConfig.nodeModules + '/*'
    ]
  };
};
