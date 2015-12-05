module.exports = function(XwTicket) {
  XwTicket.getMultiplierCount = function getMultiplierCount() {
    // throw new Error('Abstract implementation');
    return 0;
  }

  XwTicket.getPayoutMatrix = function getPayoutMatrix() {

  }

  XwTicket.isDefinitionComplete = function getPayoutMatrix() {

  }

  XwTicket.getPayoutIndices = function getPayoutMatrix() {

  }

  //function AbstractWordAnalysis(rawWordStr, revealedLetters) {
  //    var missingLetterArray;
  //    missingLetterArray = new Array(rawWordStr.length);
  //    this.missingLetterHash = {};
  //    this.rawWordStr = rawWordStr;
  //    this.isComplete = true;
  //    deriveDisplayWord(missingLetterArray, revealedLetters);
  //    this.missingLetterString = missingLetterArray.sort().join('');
  //    return;
  //  }
  //
  //  AbstractWordAnalysis.prototype.deriveDisplayWord = function(missingLetterArray, revealedLetters) {
  //    this.displayWordStr = this.rawWordStr.split('').map(function(nextChar) {
  //      if (revealedLetters[nextChar] > 0) {
  //        nextChar = '*';
  //      } else {
  //        this.isComplete = false;
  //        if (this.missingLetterHash[nextChar] < 1) {
  //          this.missingLetterHash[nextChar] = 1;
  //          missingLetterArray.push(nextChar);
  //        }
  //      }
  //      return nextChar;
  //    }).join('');
  //  };
  //
  //  return AbstractWordAnalysis;
  //
  //})();
  //
  //BasicWordAnalysis = (function(_super) {
  //  __extends(BasicWordAnalysis, _super);
  //
  //  function BasicWordAnalysis(rawWordStr, revealedLetters) {
  //    BasicWordAnalysis.__super__.constructor.call(this, rawWordStr, revealedLetters);
  //    return;
  //  }
  //
  //  return BasicWordAnalysis;
  //
  //})(AbstractWordAnalysis);
  //
  //TriplingWordAnalysis = (function() {
  //  function TriplingWordAnalysis(rawWordStr, revealedLetters) {
  //    TriplingWordAnalysis.__super__.constructor.call(this, rawWordStr, revealedLetters);
  //    return;
  //  }
  //
  //  TriplingWordAnalysis.prototype.deriveDisplayWord = function(missingLetterArray, revealedLetters) {
  //    this.displayWordStr = this.rawWordStr.split('').map(function(nextChar) {
  //      if (revealedLetters[nextChar] > 0) {
  //        return nextChar = '*';
  //      } else {
  //        nextChar = nextChar.toLowerCase();
  //        if (revealedLetters[nextChar] > 0) {
  //          nextChar = '#';
  //        } else {
  //          this.isComplete = false;
  //          if (this.missingLetterHash[nextChar] < 1) {
  //            this.missingLetterHash[nextChar] = 1;
  //            missingLetterArray.push(nextChar);
  //          }
  //        }
  //        return nextChar;
  //      }
  //    }).join('');
  //  };
  //
  //  return TriplingWordAnalysis;
  //
  //})();
  //XwTicket.prototype.analyzeProgress =
  // function AbstractProgressReport(ticketId, yourLetters, basicWords) {
  // this.ticketId = ticketId;
  // this.allBasicWords = basicWords.map(function(basicWord) {
  // return new BasicWordAnalysis(basicWord, yourLetters);
  // });
  // this.completeBasicWords = new Array(this.allBasicWords.length);
  // this.incompleteBasicWords = new Array(this.allBasicWords.length);
  // this.allBasicWords.forEach(function(analyzedWord) {
  // if (analyzedWord.isComplete) {
  // return this.completeBasicWords.push(analyzedWord);
  // } else {
  // return this.incompleteBasicWords.push(analyzedWord);
  // }
  // });
  // return;
  // }
  //
  // AbstractProgressReport.prototype.promiseNextUpdate = function(updatePromise) {
  // this.updatePromise = updatePromise;
  // };
  //
  // return AbstractProgressReport;
  //
  // })();
  //
  // V1TriplingProgressReport = (function(_super) {
  // __extends(V1TriplingProgressReport, _super);
  //
  // function V1TriplingProgressReport(ticketId, yourLetters, basicWords, triplingWords, bonusWord, bonusValue) {
  // this.bonusValue = bonusValue;
  // V1TriplingProgressReport.__super__.constructor.call(this, ticketId, yourLetters, basicWords);
  // this.allTriplingWords = triplingWords.map(function(triplingWord) {
  // return new TriplingWordAnalysis(triplingWord, yourLetters);
  // });
  // this.bonusWord = new BasicWordAnalysis(bonusWord, yourLetters);
  // return;
  // }
  //
  // return V1TriplingProgressReport;
  //
  // })(AbstractProgressReport);
  //
  // V2TriplingProgressReport = (function(_super) {
  // __extends(V2TriplingProgressReport, _super);
  //
  // function V2TriplingProgressReport(ticketId, yourLetters, basicWords, triplingWords, bonusWord, bonusValue, spotValue) {
  // this.ticketId = ticketId;
  // this.bonusValue = bonusValue;
  // this.spotValue = spotValue;
  // V2TriplingProgressReport.__super__.constructor.call(this, ticketId, yourLetters, basicWords);
  // this.allTriplingWords = triplingWords.map(function(triplingWord) {
  // return new TriplingWordAnalysis(triplingWord, yourLetters);
  // });
  // this.bonusWord = new BasicWordAnalysis(bonusWord, yourLetters);
  // return;
  // }
  //
  // return V2TriplingProgressReport;
  //
  // })(AbstractProgressReport);
  //
  // FiveXProgressReport = (function(_super) {
  // __extends(FiveXProgressReport, _super);
  //
  // function FiveXProgressReport(ticketId, basicWords, yourLetters, multiplier, spotValue) {
  // this.ticketId = ticketId;
  // this.multiplier = multiplier;
  // this.spotValue = spotValue;
  // FiveXProgressReport.__super__.constructor.call(this, ticketId, basicWords, yourLetters);
  // return;
  // }
  //
  // return FiveXProgressReport;
  //
  // })(AbstractProgressReport);
  //
  //
  // xwModule.service('xwReportService', ['$q', XwReportService]);
  //
  // PayoutReportModel = (function() {
  // function PayoutReportModel(ticketId, prizeCounts, updatePromise) {
  // this.ticketId = ticketId;
  // this.prizeCounts = prizeCounts;
  // this.updatePromise = updatePromise;
  // return this;
  // }

};
