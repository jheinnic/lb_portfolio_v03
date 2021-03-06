'use strict';

module.exports = function jshint(grunt, options) {
  var appConfig = options.appConfig;

  return {
    options: {
      jshintrc: '.jshintrc',
      reporter: require('jshint-stylish')
    },
    build: {
      src: ['Gruntfile.js', appConfig.source.build + '/**/*.js', appConfig.test.build + '/**/*.js']
    },
    source: {
      src: [ appConfig.source.client + '/**/*.js', appConfig.source.common + '/**/*.js', appConfig.source.server + '/**/*.js' ]
    },
    'client-source': {
      options: {
        jshintrc: appConfig.source.client + '/.jshintrc'
      },
      src: appConfig.source.client + '/**/*.js'
    },
    'common-source': {
      src: appConfig.source.common + '/**/*.js'
    },
    'server-source': {
      options: {
        jshintrc: appConfig.source.server + '/.jshintrc'
      },
      src: [ appConfig.source.server + '/**/*.js', '!' + appConfig.source.server + '/node_modules/**/*.js' ]
    },
    test: {
      options: {
        // TODO: Get this from appConfig
        jshintrc: 'test/.jshintrc'
        // reporter: require('jshint-stylish')
      },
      src: [ appConfig.test.client + '/**/*.js', appConfig.test.common + '/**/*.js', appConfig.test.server + '/**/*.js' ]
    }
  };
};
