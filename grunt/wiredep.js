'use strict';

module.exports = function wiredep(grunt, options) {
  var appConfig = options.appConfig;

  function deriveIgnorePath(srcDir, vendorDir) {
    var path = require('path');
    var viewFromDir = path.join(vendorDir, '..');

    return new RegExp(
      '^' + path.relative(srcDir, viewFromDir).replace(/\\/g, '\\\/').replace(/\./g, '\\.') + '\\\/'
    );
  }

  return {
    build: {
      bowerJson: appConfig.bowerJson,
      directory: appConfig.vendor,
      src: appConfig.dev.client + '/index.html',
      ignorePath: deriveIgnorePath(appConfig.dev.client, appConfig.vendor)
    }
  };
};
