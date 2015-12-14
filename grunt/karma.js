(function () {
  'use strict';

  module.exports = function karma(grunt, options) {
    var appConfig = options.appConfig;

    return {
      unit: {
        configFile: appConfig.test.client + '/karma-unit.conf.js',
        autoWatch: false,
        singleRun: true
      },
      'unit-mocha': {
        configFile: appConfig.test.client + '/karma-unit.conf.js',
        autoWatch: false,
        singleRun: true,
        reporters: ['mocha']
      },
      'unit-dots': {
        configFile: appConfig.test.client + '/karma-unit.conf.js',
        autoWatch: false,
        singleRun: true,
        reporters: ['dots']
      },
      'unit-chrome': {
        configFile: appConfig.test.client + '/karma-unit.conf.js',
        browsers: ['Chrome'],
        autoWatch: true,
        singleRun: false
      },
      'unit-chrome-mocha': {
        configFile: appConfig.test.client + '/karma-unit.conf.js',
        browsers: ['Chrome'],
        autoWatch: true,
        singleRun: false,
        reporters: ['mocha']
      },
      'unit-chrome-once': {
        configFile: appConfig.test.client + '/karma-unit.conf.js',
        browsers: ['Chrome'],
        autoWatch: true,
        singleRun: true
      },
      'unit-coverage': {
        configFile: appConfig.test.client + '/karma-unit.conf.js',
        autoWatch: false,
        singleRun: true, //logLevel: 'DEBUG',
        reporters: ['progress', 'coverage'], // TODO: Use appConfig here too!!!
        preprocessors: {
          '{client,common,server}/**/*.coffee': ['coffee'],
          '<%= appConfig.dist %>/app.js': ['coverage'],
          '<%= appConfig.app %>/**/*.js': ['coverage']
        },
        coverageReporter: {
          reporters: [
            {
              type: 'lcov',
              dir: 'coverage/',
              subdir: 'report'
            }, {
              type: 'text-summary',
              dir: 'coverage/',
              subdir: 'report'
            }
          ]
        }
      }
    };
  };
}).call();
