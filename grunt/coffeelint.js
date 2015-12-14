'use strict';

module.exports = function coffeelint(grunt, options) {
  var appConfig = options.appConfig;

  return {
    options: {
      configFile: 'coffeelint.json',
      reporter: require('jshint-stylish')
    },
    build: {
      src: ['grunt/**/*.coffee']
    },
    source: {
      src: [
        'client/src/' + appConfig.app + '/**/*.coffee',
        '{common,server}/**/*.coffee',
        '!{common,server}/{test}/**/*.coffee']
    },
    'client-source': {
      src: ['client/src/' + appConfig.app + '/**/*.coffee']
    },
    'common-source': {
      src: ['common/**/*.coffee', '!common/test/**/*.coffee']
    },
    'server-source': {
      src: ['server/**/*.coffee', '!server/test/**/*.coffee']
    },
    test: {
      src: ['client/test/{spec,e2e}/**/*.coffee', '{common,server}/test/**/*.coffee']
    }
  };
};
