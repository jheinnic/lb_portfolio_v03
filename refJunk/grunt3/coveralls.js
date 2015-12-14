'use strict';

module.exports = function coveralls(/*grunt, options*/) {
  return {
    options: {
      debug: true,
      'coverage_dir': 'coverage'
    }
  };
};
