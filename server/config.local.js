var GLOBAL_CONFIG = require('../global-config');

var process = require('process');
var GlobSync = require('glob').GlobSync;
var isDevEnv = GLOBAL_CONFIG.isDevEnv;

module.exports = {
  restApiRoot: GLOBAL_CONFIG.restApiRoot,
  livereload: isDevEnv ? process.env.LIVE_RELOAD : undefined,
  // isDevEnv: isDevEnv,

  // TODO: Paths below should come from grunt's appConfig module.
  // TODO: Index File needs to be fileRev-aware for production server use.
  indexFile: isDevEnv ? 'build/dev/client/index.html' : new GlobSync('build/dist/client/index.*.html')[0]
};
