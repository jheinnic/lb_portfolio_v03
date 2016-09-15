'use strict';

// TODO: This is inherited and not really used yet.  It's related to using connect as
//       a webserver and the Coveralls coverage plugin.
module.exports = function open(/*grunt, options*/) {
  return {
    dev: { path: 'http://localhost:8888' },
    serve: { path: 'http://localhost:8080?port=5858' },
    coverage: { path: 'http://localhost:5555' }
  };
};
