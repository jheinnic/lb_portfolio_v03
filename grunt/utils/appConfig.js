(function() {
  'use strict';

  module.exports = function createAppConfig(grunt) {
    var process     = require('process');
    var bowerRc     = grunt.file.readJSON('.bowerrc') || {};
    var bowerJson   = grunt.file.readJSON('bower.json') || {};
    var buildDirs   = (bowerJson && bowerJson.build) ? bowerJson.build : {};
    var packageJson = grunt.file.readJSON('package.json') || {};
    var getAvailPort = require('./getAvailPort');

    buildDirs.temp  = buildDirs.temp ? buildDirs.temp : 'build/temp';
    buildDirs.dist  = buildDirs.dist ? buildDirs.dist : 'build/dist';
    buildDirs.dev   = buildDirs.dev  ? buildDirs.dev  : 'build/dev';
    bowerRc.directory = bowerRc.directory ? bowerRc.directory : 'bower_components';
    bowerJson.appModule = bowerJson.appModule ? bowerJson.appModule : 'app';

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
}).call();
