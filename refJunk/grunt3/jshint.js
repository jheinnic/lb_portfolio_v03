'use strict';

module.exports = function jshint(/*grunt, options*/) {
  return {
    options: {
      jshintrc: '.jshintrc',
      reporter: require('jshint-stylish')
    },
    build: {
      src: ['Gruntfile.js', 'grunt/**/*.js', 'client/build.js']
    },
    source: {
      src: [ ]
      // src: ['<%= appConfig.app %>/**/*.js', '{common,server}/**/*.js', '!{common,server}/test/**/*.js']
    },
    'client-source': {
      src: [ ]
      // src: ['<%= appConfig.app %>/**/*.js']
    },
    'common-source': {
      src: [ ]
      // src: ['common/**/*.js', '!common/test/**/*.js']
    },
    'server-source': {
      src: [ ]
      // src: ['server/**/*.js', '!server/test/**/*.js']
    },
    test: {
      options: {
        jshintrc: 'client/test/.jshintrc'
        // reporter: require('jshint-stylish')
      },
      src: ['client/test/{e2e,spec}/**/*.js', '{common,server}/test/**/*.js']
    }
  };
};
