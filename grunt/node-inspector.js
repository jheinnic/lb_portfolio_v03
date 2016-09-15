(function () {
  'use strict';

  module.exports = function ngInspector(/*grunt, options*/) {
    return {
      serve: {
        'web-host': 'localhost',
        'web-port': 8080,
        'debug-port': 5858,
        'save-live-edit': true,
        'preload': true
      }
    };
  };
}).call();
