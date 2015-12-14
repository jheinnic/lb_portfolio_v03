'use strict';

module.exports = function wiredep(grunt, options) {
  var path = require('path');
  var appConfig = options.appConfig;
  var bowerJson = appConfig.bowerJson;
  var viewFromDir = path.join(appConfig.vendor, '..');

  function deriveIgnorePath(srcDir) {
    var retVal =
      new RegExp(
        '^' + path.relative(srcDir, viewFromDir).replace(/\\/g, '\\\/').replace(/\./g, '\\.') + '\\\/'
      );
    // console.log(retVal);

    return retVal;
  }

  // TODO: We have enough to derive the regular expression, its just a little tricky
  //       due to all the escaping gymnastics required!
  // console.log('Provisional devIgnorePath: ', deriveIgnorePath(appConfig.dev.client));

  return {
    dev: {
      bowerJson: bowerJson,
      directory: appConfig.vendor,
      // ignorePath: /^\.\.\/\.\.\/\.\.\/client\//,
      ignorePath: deriveIgnorePath(appConfig.dev.client),
      src: appConfig.dev.client + '/index.html'
    },
    dist: {
      bowerJson: bowerJson,
      directory: appConfig.vendor,
      // ignorePath: /^\.\.\/\.\.\/\.\.\/client\//,
      ignorePath: deriveIgnorePath(appConfig.dist.client),
      src: appConfig.dist.client + '/index.html'
    }
  };
};
