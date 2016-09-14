(function (module) {
  'use strict';

  module.exports = function exec(/*grunt, options*/) {
    return {
      watch: {
        cmd: 'grunt watch'
      }
    };
  };
}).call(this, module);
