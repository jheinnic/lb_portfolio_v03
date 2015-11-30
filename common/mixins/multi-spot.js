module.exports = function(MultiSpot, options) {
  // MultiSpot is the model class
  // options is an object containing the config properties from model definition
  var _ = require('lodash');

  MultiSpot.defineProperty('multiValue', {type: integer, default: 0, min: 0, max: (options.maxMultiplier || 5)});
};


