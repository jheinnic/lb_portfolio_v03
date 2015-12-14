'use strict';

module.exports = function angularArchitectureGraph(/*grunt, options*/) {
  return {
    diagram: {
      files: {
        'dist/architecture': ['dist/<%= pkg.name %>.js']
      }
    }
  };
};
