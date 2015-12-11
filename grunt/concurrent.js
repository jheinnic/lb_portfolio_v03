'use strict';

module.exports = function (grunt, options) {
    return {
      options: { logConcurrentOutput: true },
      watchServe: {
        tasks: ['connect:serve', 'nodemon:serve', 'node-inspector:serve', 'watch']
      },

      // Inherited targets:
      watchConnect: {
        tasks: ['watch:fast', 'connect:webserver'],
      },
      chrome: {
        tasks: ['karma:unit-chrome', 'watch:chrome'],
      },
      unit: {
        tasks: ['karma:unit', 'watch:unit'],
      },
      'unit-mocha': {
        tasks: ['karma:unit-mocha', 'watch:unit-mocha'],
      }
  };
};
