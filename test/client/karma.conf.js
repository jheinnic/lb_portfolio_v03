// Karma configuration
// http://karma-runner.github.io/0.12/config/configuration-file.html
// Generated on 2014-06-23 using
// generator-karma 0.8.2

module.exports = function(config) {
  var appConfig      = require('../../grunt/utils/appConfig');
  var testPrefix     = path.relative(appConfig.clientSrc, appConfig.test.client).replace(/\\/g, '\/');
  var vendorPrefix   = path.relative(appConfig.clientSrc, appConfig.vendor).replace(/\\/g, '\/');
  var buildDevPrefix = path.relative(appConfig.clientSrc, appConfig.dev.client).replace(/\\/g, '\/');

  config.set({
    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,

    // base path, that will be used to resolve files and exclude
    basePath: appConfig.clientSrc,

    // testing framework to use (jasmine/mocha/qunit/...)
    frameworks: ['jasmine'],

    // list of files / patterns to load in the browsers
    files: [
      buildDevPrefix + '/vendor.js',
      // vendorPrefix + '/es5-shim/es5-shim.js',
      // vendorPrefix + '/lodash/lodash.js',
      // vendorPrefix + '/angular/angular.js',
      vendorPrefix + '/angular-mocks/angular-mocks.js',
      vendorPrefix + '/angular-route/angular-route.js',
      buildDevPrefix + '/app.js',
      testPrefix + '/mock/**/*.js',
      testPrefix + '/spec/**/*.js',
      '**/*.js'
    ],

    // list of files / patterns to exclude
    exclude: [],

    // web server port
    port: 8080,

    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: [ 'Chrome', 'PhantomJS' ],

    // Which plugins to enable
    plugins: [
      'karma-chrome-launcher',
      'karma-phantomjs-launcher',
      'karma-jasmine'
    ],

    // TODO: Does setting a reporter muck with singleRun: false ?
    reporters: ['mocha'],

    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: false,

    colors: true,

    // level of logging
    // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
    logLevel: config.LOG_INFO,

    // Uncomment the following lines if you are using grunt's server to run the tests
    // proxies: {
    //   '/': 'http://localhost:9000/'
    // },
    // URL root prevent conflicts with the site root
    // urlRoot: '_karma_'
  });
};
