var GLOBAL_CONFIG = require('../global-config');

var isDevEnv = (process.env.NODE_ENV || 'development') === 'development';

var p = require('../package.json');
var version = p.version.split('.').shift();

// TODO: Index File needs to be fixed once server is running from the dist directory too.
module.exports = {
  restApiRoot: GLOBAL_CONFIG.restApiRoot + (version > 0 ? '/v' + version : ''),
  livereload: process.env.LIVE_RELOAD,
  isDevEnv: isDevEnv,
  indexFile: require.resolve(isDevEnv ?
    '../build/dev/client/index.html' : '../build/dist/client/index.html'),
};
