var GLOBAL_CONFIG = require('../global-config');

var process = require('process');
var isDevEnv = process.env.NODE_ENV ? process.env.NODE_ENV === 'development' : GLOBAL_CONFIG.isDevEnv;

module.exports = {
  restApiRoot: GLOBAL_CONFIG.restApiRoot,
  livereload: process.env.LIVE_RELOAD || GLOBAL_CONFIG.liveReload,
  isDevEnv: isDevEnv,

  // TODO: Paths below should come from grunt's appConfig module.
  // TODO: Index File needs to be fileRev-aware for production server use.
  indexFile: isDevEnv ? 'build/dev/client/index.html' : 'build/dist/client/index.711e378d.html'
};
