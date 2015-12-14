'use strict';

module.exports = function(BonusWord, options) {
  // BonusWord is the model class
  // options is an object containing the config properties from model definition
  var _ = require('lodash');
  var numPrizes = (_.isArray(options.prizeValues)) ? options.prizeValues.length : 0;
  var bonusWordLength =
    (options.bonusWordLength > 0 && options.bonusWordLength <= 26) ? options.bonusWordLength : 5;

  BonusWord.defineProperty('bonusWord', {type: 'string', default: '', length: bonusWordLength});
  BonusWord.defineProperty('prizeValue', {type: 'number', default: -1, min: -1, max: numPrizes});
  BonusWord.defineProperty('scratchSpace', {type: 'array', persistent: false, hidden: true});

  BonusWord.validate(function(obj, a, b) {
    console.log( 'Validate: ', obj, a, b );
    var uniqueLetters = {};
    this.bonusWord.split('').forEach(
      function trackUniqueLetters(reqdLetter) {
        uniqueLetters[reqdLetter] = 1;
      }
    );

    return (_.keys(uniqueLetters).length === bonusWordLength);
  });

  BonusWord.prototype.isPrizeValueKnown = function isPrizeValueKnown() {
    var retVal;
    if (this.isValid()) {
      retVal = (this.prizeValue > 0);
    }

    return retVal;
  };

  BonusWord.prototype.isWordCovered = function isWordCovered(yourLettersSet) {
    var retVal;
    if (this.isValid()) {
      retVal = this.bonusWord.split('').every(
        function isLetterCovered(reqdLetter) {
          return ! _.isUndefined(yourLettersSet[reqdLetter]);
        }
      );

      if ((retVal === false) && (_.keys(yourLettersSet).length < options.yourLettersCount)) {
        retVal = undefined;
      }
    }

    return retVal;
  };

  /**
   * @return
   */
  BonusWord.prototype.isPayoutKnown = function isPayoutKnown(yourLettersSet) {
    var retVal;
    if (this.isValid()) {
      retVal = (this.isPrizeValueKnown() && this.isWordCovered(yourLettersSet));
    }

    return retVal;
  };
};

