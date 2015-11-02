'use strict';

global._ = require('lodash');

// Filesystem access
global.fs = require('fs');

// For building filesystem paths by concatenation.
global.path = require('path');

module.exports = function (grunt) {
  // Configurable paths for the application
  var bowerJson = require('./bower.json');
  var bowerRc;
  try {
    bowerRc = JSON.parse(fs.readFileSync('./.bowerrc'), 'utf-8');
  } catch(err) {
    // No .bowerrc.  Not a problem--it's optional!
  }
  global.appConfig = {
    app: (bowerJson && bowerJson.appPath) ? bowerJson.appPath : 'client/ngapp',
    lbclient: (bowerJson && bowerJson.lbClient) ? bowerJson.lbClient : 'client/lbclient',
    vendor: (bowerRc && bowerRc.directory) ? bowerRc.directory : 'bower_components',
    dev: 'build/dev',
    dist: 'build/dist',
    temp: 'build/temp'
  };

  // Time how long tasks take. Can help when optimizing build times
  require('time-grunt')(grunt);

  // Load grunt config from per-plugin files under 'grunt' subdirectory
  require('load-grunt-config')(grunt);
};
