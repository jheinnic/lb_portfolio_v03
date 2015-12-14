'use strict';

module.exports = function karma(/*grunt, options*/) {
  return {
    unit: {
      configFile: 'client/test/karma-unit.conf.js',
      autoWatch: false,
      singleRun: true
    },
    'unit-mocha': {
      configFile: 'client/test/karma-unit.conf.js',
      autoWatch: false,
      singleRun: true,
      reporters: ['mocha']
    },
    'unit-dots': {
      configFile: 'client/test/karma-unit.conf.js',
      autoWatch: false,
      singleRun: true,
      reporters: ['dots']
    },
    'unit-chrome': {
      configFile: 'client/test/karma-unit.conf.js',
      browsers: ['Chrome'],
      autoWatch: true,
      singleRun: false
    },
    'unit-chrome-mocha': {
      configFile: 'client/test/karma-unit.conf.js',
      browsers: ['Chrome'],
      autoWatch: true,
      singleRun: false,
      reporters: ['mocha']
    },
    'unit-chrome-once': {
      configFile: 'client/test/karma-unit.conf.js',
      browsers: ['Chrome'],
      autoWatch: true,
      singleRun: true
    },
    'unit-coverage': {
      configFile: 'client/test/karma-unit.conf.js',
      autoWatch: false,
      singleRun: true,
      //logLevel: 'DEBUG',
      reporters: ['progress', 'coverage'],
      preprocessors: {
        '{client,common,server}/**/*.coffee': ['coffee'],
        '<%= appConfig.dist %>/app.js': ['coverage'],
        '<%= appConfig.app %>/**/*.js': ['coverage']
      },
      coverageReporter: {
        reporters:[
          {type: 'lcov', dir:'coverage/', subdir:'report'},
          {type: 'text-summary', dir:'coverage/', subdir:'report'}
        ]
      }
    }
  };
};
