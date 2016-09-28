module.exports = function(config) {

  var SRC = [
    'src/myApp/**/*.js',
    'test/myApp/**/*.spec.js'
  ];

  var LIBS = [
    'node_modules/angular/angular.js',
    'node_modules/angular-animate/angular-animate.js',
    'node_modules/angular-aria/angular-aria.js',
    'node_modules/angular-material/angular-material.js',

    'node_modules/angular-mocks/angular-mocks.js',
    'node_modules/angular-material/angular-material-mocks.js'
  ];

  config.set({

    basePath: __dirname + '/..',
    frameworks: ['jasmine'],

    files: LIBS.concat(SRC),

    port: 9876,
    reporters: ['progress'],
    colors: true,

    autoWatch: false,
    singleRun: true,
    browsers: ['PhantomJS,Chrome']

  });

};
