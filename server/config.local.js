var GLOBAL_CONFIG = require('../global-config');

var process = require('process');
var path = require('path');
var p = require('../package.json');
var version = p.version.split('.').shift();
var isDevEnv = (process.env.NODE_ENV || 'development') === 'development';

// TODO: Index File needs to be fixed once server is running from the dist directory too.
// TODO: Verify above TODO has been closed
module.exports = {
  restApiRoot: GLOBAL_CONFIG.restApiRoot + (version > 0 ? '/v' + version : ''),
  livereload: process.env.LIVE_RELOAD,
  isDevEnv: isDevEnv,

  // TODO: For dev, the 'build/dev/client' should come from grunt-defined enviornment!
  // NOTE: For production, we can rely on an assumed directory structure created by the build.
  indexFile: isDevEnv ? 'build/dev/client/index.html' : 'build/dist/client/index.711e378d.html'
};
