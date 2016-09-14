(function (module) {
  'use strict';

  module.exports = function concurrent(/*grunt, options*/) {
    return {
      options: {logConcurrentOutput: true},
      watchServe: {
        tasks: ['watch', 'nodemon:serve', 'node-inspector:serve']
      },
      temp1: {
        tasks: ['nodemon:serve', 'node-inspector:serve']
      },
      temp2: {
        tasks: ['nodemon:serve', 'watch']
      },
      temp3: {
        tasks: ['exec:watch', 'node-inspector:serve']
      },

      // Inherited targets:
      // TODO: Inherited watch subset targets do not yet exist
      watchConnect: {
        tasks: ['watch:fast', 'connect:serve']
      },
      chrome: {
        tasks: ['karma:unit-chrome', 'watch:chrome']
      },
      unit: {
        tasks: ['karma:unit', 'watch:unit']
      },
      'unit-mocha': {
        tasks: ['karma:unit-mocha', 'watch:unit-mocha']
      }
    };
  };
}).call(this, module);
