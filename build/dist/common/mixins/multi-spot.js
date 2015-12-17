'use strict';

module.exports = function(MultiSpot, options) {
  // MultiSpot is the model class
  // options is an object containing the config properties from model definition
  MultiSpot.defineProperty('multiValue', {type: 'number', default: 0, min: 0, max: (options.maxMultiplier || 5)});
};


