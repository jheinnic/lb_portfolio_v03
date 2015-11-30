'use strict';

global._ = require('lodash');

// Filesystem access
global.fs = require('fs');

// For building filesystem paths by concatenation.
global.path = require('path');

module.exports = function (grunt) {
  // Configurable paths for the application
  var bowerJson;
  try {
    bowerJson = require('./bower.json');
  } catch(err) {
    // No bower.json?  This'll probably be a problem, but let it go for now...
  }

  var bowerRc;
  try {
    bowerRc = JSON.parse(fs.readFileSync('./.bowerrc'), 'utf-8');
  } catch(err) {
    // No .bowerrc.  Not a problem--it's optional!
  }

  global.appConfig = {
    app: (bowerJson && bowerJson.appPath) ? bowerJson.appPath : 'client/ngapp',
    appMain: (bowerJson && bowerJson.appMain)
      ? bowerJson.appMain
      : (bowerJson && bowerJson.appPath)
        ? bowerJson.appPath + '/ngApp/main.js'
        : 'client/ngapp/ngApp/main.js',
    lbClient: (bowerJson && bowerJson.lbClient) ? bowerJson.lbClient : 'client/lbclient',
    vendor: (bowerRc && bowerRc.directory) ? bowerRc.directory : 'bower_components',
    stage: 'build/stage',
    temp: 'build/temp',
    dist: 'build/dist',
    dev: 'build/dev'
  };

  // For plugins whose configuration blocks depend on traversing the client source tree.
  global.sourceBlocks = require('./grunt/utils/traverseSource')(appConfig);

  // Time how long tasks take. Can help when optimizing build times
  require('time-grunt')(grunt);

  // Load grunt config from per-plugin files under 'grunt' subdirectory
  require('load-grunt-config')(grunt, {
    configPath: path.join(process.cwd(), 'grunt'),
    init: true,
    data: {
      appConfig: appConfig
    },
    loadGruntTasks: {
      pattern: 'grunt-*',
      config: require('./package.json'),
      scope: 'devDependencies'
    },
    postProcess: function postProcess(config) {
      console.log('postProcess: ', config.appConfig);
      return config;
    },
    preMerge: function preMerge(config, data) {
      console.log('preMerge data: ', data.appConfig);
      config.appConfig = data.appConfig;
      config.package = data.package
    }
  });
};
