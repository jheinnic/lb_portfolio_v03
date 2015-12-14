'use strict';

module.exports = function protractor(grunt, options) {
  var appConfig = options.appConfig;
  var process = require('process');

  return {
    options: {
      keepAlive: false,
      configFile: appConfig.test.client + '/protractor.conf.js',
      args: {
        specs: appConfig.test.client + '/e2e/**/*.js'
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
