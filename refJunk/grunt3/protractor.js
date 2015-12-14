'use strict';

module.exports = function protractor(/*grunt, options*/) {
  var process = require('process');

  return {
    options: {
      keepAlive: false,
      configFile: 'client/test/protractor.conf.js',
      args: {
        specs: 'client/test/e2e/**/*.js'
      }
    },
    run: {},
    firefox: {},
    saucelabs: {
      // TODO: This is inherited and not particularly used yet.
      options: {
        args: {
          baseUrl: 'http://jheinnic.github.io/lb_express_sandbox/examples/',
          sauceUser: process.env.SAUCE_USERNAME,
          sauceKey: process.env.SAUCE_ACCESS_KEY
        }
      }
    }
  };
};
