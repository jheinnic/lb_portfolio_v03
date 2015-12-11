'use strict';

module.exports = function open(grunt, options) {
  return {
    dev: {
      path: 'http://localhost:8888'
    },
    coverage: {
      path: 'http://localhost:5555'
    }
  };
};
