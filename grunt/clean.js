'use strict';

module.exports = function (grunt, options) {
  return {
    options: {
      force: false,
      "no-write": false
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
    }
  }
};
