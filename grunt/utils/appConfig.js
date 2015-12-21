(function () {
  'use strict';

  // TODO: Get the source directories from bower.json and package.json or leave them hard coded?
  //       Tending to prefer convention over configuration.  Comparing confusion versus restriction,
  //       configuration is more confusing than convention is restrictive.  There are better fights
  //       to take on, but revisit this anyhow just to be sure.
  module.exports = function createAppConfig(grunt) {
    var bowerRc = grunt.file.readJSON('.bowerrc') || {};
    var bowerJson = grunt.file.readJSON('bower.json') || {};
    var buildDirs = (bowerJson && bowerJson.build) ? bowerJson.build : {};
    var packageJson = grunt.file.readJSON('package.json') || {};

    buildDirs.temp = buildDirs.temp ? buildDirs.temp : 'build/temp';
    buildDirs.dist = buildDirs.dist ? buildDirs.dist : 'build/dist';
    buildDirs.dev = buildDirs.dev ? buildDirs.dev : 'build/dev';
    bowerRc.directory = bowerRc.directory ? bowerRc.directory : 'bower_components';
    bowerJson.appModule = bowerJson.appModule ? bowerJson.appModule : 'app';
    bowerJson.appModuleSource = 'client/src/' + bowerJson.appModule;

    checkNameConflicts(grunt, /\.(css|less)$/, '@(css|less)');
    checkNameConflicts(grunt, /\.(html|jade)$/, '@(html|jade)');

    // Allocate and remember ports for assets, REST API, and LiveReload protocol (dev only).
    var process = require('process');
    var getAvailPort = require('./getAvailPort');

    // TODO: Store with "grunt.task.config(key,value);" instead of "process.env.key = value;"
    var lrPort = process.env.LIVE_RELOAD || getAvailPort(35729);
    process.env.LIVE_RELOAD = lrPort;

    var restApiPort = process.env.REST_API_PORT || getAvailPort(3000);
    process.env.REST_API_PORT = restApiPort;

    var assetsPort = process.env.ASSETS_PORT || getAvailPort(8888);
    process.env.ASSETS_PORT = assetsPort;

    return Object.freeze(
      {
        bowerJson: bowerJson,
        packageJson: packageJson,
        nodeEnv: process.env.NODE_ENV || 'development',
        ports: {
          liveReload: lrPort,
          restApi: restApiPort,
          assets: assetsPort
        },
        app: bowerJson.appModule,
        cwd: process.cwd(),
        vendor: bowerRc.directory,
        node: 'node_modules',
        source: {
          build: 'grunt',
          client: 'client/src',
          common: 'common',
          server: 'server'
        },
        test: {
          build: 'test/grunt',
          client: 'test/client',
          common: 'test/common',
          server: 'test/server'
        },
        temp: {
          root: buildDirs.temp,
          client: buildDirs.temp + '/client',
          common: buildDirs.temp + '/common',
          server: buildDirs.temp + '/server'
        },
        dist: {
          root: buildDirs.dist,
          client: buildDirs.dist + '/client',
          common: buildDirs.dist + '/common',
          server: buildDirs.dist + '/server'
        },
        dev: {
          root: buildDirs.dev,
          client: buildDirs.dev + '/client',
          common: buildDirs.dev + '/common',
          server: buildDirs.dev + '/server'
        }
      }
    );
  };

  var _ = require('lodash');

  function scanForDuplicates(extRegExp, lastObj, nextMatch) {
    var baseName = nextMatch.replace(extRegExp, '');

    if (baseName === lastObj.baseName) {
      console.error('!!! File name conflict detected between %s and %s', lastObj.fileMatch, nextMatch);
    }

    return { fileMatch: nextMatch, baseName: baseName };
  }

  function checkNameConflicts(grunt, extRegExp, extGlobSuffix) {
    _.reduce(
      grunt.file.glob(
        'client/src/**/*.' + extGlobSuffix,
        { dot: true, sync:true, strict: true, stat: false, nonull: false, nodir: true, nosort: false, nounique: false }
      ),
      _.partial(scanForDuplicates, extRegExp),
      { fileMatch: undefined, baseName: undefined }
    );
  }
}).call();
