'use strict';

module.exports = function (grunt, options) {
  return {
    options: {
      configFile: 'coffeelint.json',
      reporter: require('jshint-stylish')
    },
    source: {
      src: ['/{client,common,server}/**/*.coffee', '!{client,common,server}/{test,vendor}/**/*.coffee']
    },
    'client-source': {
      src: ['/client/**/*.coffee', '!client/{test,vendor}/**/*.coffee']
    },
    'common-source': {
      src: ['/common/**/*.coffee', '!common/test/**/*.coffee']
    },
    'server-source': {
      src: ['/server/**/*.coffee', '!server/test/**/*.coffee']
    },
    test: {
      src: ['/client/test/{spec,e2e}/**/*.coffee', '/{common,server}/test/**/*.coffee']
    }
  };
};
