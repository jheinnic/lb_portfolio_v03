'use strict';

module.exports = function clean(/*grunt, options*/) {
  return {
    options: {
      force: false,
      'no-write': false
    },
    all: {
      dot: true,
      src: [
        '<%= appConfig.dev %>/*',
        '<%= appConfig.dist %>/*',
        '<%= appConfig.temp %>/*',
        '<%= appConfig.stage %>/*',
        '<%= appConfig.vendor %>/*'
      ]
    },
    build: {
      dot: true,
      src: [
        '<%= appConfig.dev %>/*',
        '<%= appConfig.dist %>/*',
        '<%= appConfig.temp %>/*',
        '<%= appConfig.stage %>/*'
      ]
    },
    dist: {
      dot: true,
      src: '<%= appConfig.dist %>/*'
    },
    dev: {
      dot: true,
      src: '<%= appConfig.dev %>/*'
    },
    temp: {
      dot: true,
      src: '<%= appConfig.temp %>/*'
    },
    vendor: {
      dot: true,
      src: '<%= appConfig.vendor %>/*'
    }
  };
};
