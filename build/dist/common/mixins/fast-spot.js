'use strict';

module.exports = function(FastSpot, options) {
  // FastSpot is the model class
  // options is an object containing the config properties from model definition
  FastSpot.defineProperty('fastValue', {type: 'number', default: 0, min: 0});

  FastSpot.prototype.hasWinningValue = function hasWinningValue() {
    var retVal;

    if (this.fastValue > 0) {
      retVal = (this.fastValue === options.winningValue);
    }

    return retVal;
  };
};


