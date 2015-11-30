'use strict';

module.exports = function (grunt, options) {
  // var process = require('process');

  return {
    options: {
      keepAlive: false,
      configFile: 'test/protractor.conf.js',
      args: {
        specs: 'test/e2e/**/*.js'
      }
    },
    run: {},
    firefox: {},
    saucelabs: {
      options: {
        args: {
          baseUrl: "http://jheinnic.github.io/lb_express_sandbox/examples/",
          sauceUser: process.env.SAUCE_USERNAME,
          sauceKey: process.env.SAUCE_ACCESS_KEY
        }
      }
    }
  };
};
