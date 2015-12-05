'use strict';

module.exports = function (grunt, options) {
  // var fs =  require('fs');

  return {
    options: JSON.parse(fs.readFileSync('.jshintrc')),
    source: {
      src: ['<%= appConfig.app %>/ngapp/**/*.js']
    },
    tests: {
      src: ['<%= appConfig.app %>/test/unit/**/*.js', '<%= appConfig.app %>/test/e2e/**/*.js']
    //},
    //build: {
    //  src: ['Gruntfile.js', 'grunt/**/*.js']
    }
  };
};
