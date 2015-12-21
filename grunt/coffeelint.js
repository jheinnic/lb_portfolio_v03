'use strict';

module.exports = function coffeelint(grunt, options) {
  var appConfig = options.appConfig;

  return {
    options: {
      configFile: 'coffeelint.json',
      reporter: require('jshint-stylish')
    },
    build: [appConfig.source.build + '/**/*.coffee'],
    source: [
      appConfig.source.client + '/**/*.coffee',
      appConfig.source.common + '/**/*.coffee',
      appConfig.source.server + '/**/*.coffee'
    ],
    'client-source': [appConfig.source.client + '/**/*.coffee'],
    'common-source': [appConfig.source.common + '/**/*.coffee'],
    'server-source': [appConfig.source.server + '/**/*.coffee'],
    test: [
      appConfig.test.client + '/**/*.coffee',
      appConfig.test.common + '/**/*.coffee',
      appConfig.test.server + '/**/*.coffee'
    ]
  };
};
