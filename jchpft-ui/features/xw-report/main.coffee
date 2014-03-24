define ['require', 'angular', 'ui-bootstrap-tpls', 'cs!xw-dynamic'], (require) ->
  xwModule = angular.module('xw-report', ['xw-dynamic']);

  xwModule.directive 'xwProgressReport', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.registerReport('progress')
  ]

  xwModule.directive 'xwPayoutReport', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.registerReport('payout')
  ]

  class XwReportService
    constructor: ($q) ->
      @$q = $q
      @progressReportDeferal = $q.defer()
      @progressReportObject = new ProgressReportModel( null, null, @progressReportDeferal.promise )
      @payoutReportDeferal = $q.defer()
      @payoutReportObject = new PayoutReportModel( null, null, @payoutReportDeferal.promise )

    getProgressReport: () ->
      return @progressReportObject

    updateProgressReport: (ticketHref, detail) ->
      tempDeferal = @progressReportDeferal
      @progressReportDeferal = $q.defer()
      @progressReportObject = new ProgressReportModel( ticketHref, detail, @progressReportDeferal.promise )
      tempDeferal.resolve(@progressReportObject)

    getPayoutReport: () ->
      return @payoutReportObject

    updatePayoutReport: (ticketHref, detail) ->
      tempDeferal = @payoutReportDeferal
      @payoutReportDeferal = $q.defer()
      @payoutReportObject = new PayoutReportModel( ticketHref, detail, @payoutReportDeferal.promise )
      tempDeferal.resolve(@payoutReportObject)

  xwModule.service 'xwReportSvc', ['$q', XwReportService]


  ##
  ## Model Classes
  ##

  class ProgressAnalysisModel
    constructor: (@ticketHref) ->
      @purgeWordLists()
      @purgeLettersHash()

    purgeWordLists: () =>
      @purgedWordLists = true
      @bonusWord       = null
      @allWords        = new Array()
      @purgeAnalysis()

    purgeLettersHash: () =>
      @purgedLettersHash    = true
      @revealedLettersHash = {}
      @purgeAnalysis()

    purgeAnalysis: () =>
      @allWords.forEach( (wordAnalysis) -> wordAnalysis.reset() )
      @incompleteTripleWords = null
      @incompleteBasicWords  = null
      @completeTripleWords   = null
      @completeBasicWords    = null
      @incompleteWords    = null
      @completeWords      = null
      @tripleWords        = null
      @basicWords         = null

    setBonusWord: (rawWordStr) =>
      if (@purgedWordLists == false)
        throw "IllegalStateException"
      else if (@bonusWord?)
        throw "IllegalStateException"

      @bonusWord = new WordAnalysis(rawWordStr,true)
      @allWords.push(@bonusWord)

    addWord: (rawWordStr) =>
      if (@purgedWordLists == false)
        throw "IllegalStateException"

      @allWords.push(new WordAnalysis(rawWordStr,false))

    addRevealedLetter: (revealedLetter) =>
      if (@purgedLettersHash == false)
        throw "IllegalStateException"

      @revealedLettersHash[revealedLetter] = 1

    refreshAnalysis: () =>
      if (@purgedLettersHash == true && @purgedWordLists == true)
        throw "IllegalStateException"

      @incompleteTripleWords = new Array()
      @incompleteBasicWords  = new Array()
      @completeTripleWords   = new Array()
      @completeBasicWords    = new Array()
      bonusWords             = new Array()

      @allWords.forEach( (wordReport) ->
        wordReport.doAnalysis(@revealedLettersHash, @incompleteBasicWords, @completeBasicWords, @incompleteTripleWords, @completeTripleWords, bonusWords)
      )

      @bonusWord = bonusWords[0]
      @completeWords   = @completeBasicWords.concat(@completeTripleWords)
      @basicWords      = @incompleteBasicWords.concat(@completeBasicWords)
      @incompleteWords = @incompleteBasicWords.concat(@incompleteTripleWords)
      @tripleWords     = @incompleteTripleWords.concat(@completeTripleWords)

      @purgedLettersHash = false
      @purgedWordLists = false

    @hasTripleBonus: () =>
      if (@purgedLettersHash == false || @purgedWordLists == false)
        throw "IllegalStateException"
      return @completeTripleWords.length > 0

  class WordAnalysis
    constructor: (@rawWordStr, @isBonus) ->
      @reset()

    reset: () =>
      @isTriple = null
      @isComplete = null
      @displayWordStr = null
      @missingLetterHash = {}
      @missingLetterString = null
      @missingLetterArray = new Array()

    doAnalysis: (revealedLetters, incompleteBasicWords, completeBasicWords, incompleteTripleWords, completeTripleWords, bonusWords) =>
      @isTriple = false
      @isComplete = true

      @displayWordStr =
        @rawWordStr.split('').map(
          (nextChar) ->
            retVal = nextChar
            lowerChar = nextChar.toLowerCase()

            if (revealedLetters[lowerChar] == 1)
              if (nextChar != lowerChar)
                retVal = '#'
                @isTriple = true
              else
                retVal = '*'
            else
              @isComplete = false
              if (@missingLetterHash[lowerChar] != 1)
                @missingLetterHash[lowerChar] = 1
                @missingLetterArray.push(lowerChar);

              if (nextChar != lowerChar)
                @isTriple = true

            return retVal
        ).join('')

      @missingLetterArray = @missingLetterArray.sort()
      @missingLetterString = @missingLetterArray.join('')

      if(@isBonus)
        bonusWords.push(@)
      else if (@isComplete)
        if (@isTriple)
          completeTripleWords.push(@)
        else
          completeBasicWords.push(@)
      else
        if (@isTriple)
          incompleteTripleWords.push(@)
        else
          incompleteBasicWords.push(@)


  class ProgressReportModel
    constructor: (@ticketHref, analysisModel, @updatePromise) ->
      if (analysisModel?)
        tempWordMap = {}
        buildWordMapFn = (wordAnalysis) -> tempWordMap[wordAnalysis.rawWordStr] = new WordReport(wordAnalysis)
        analysisModel.allWords.forEach buildWordMapFn

        @bonusWord = tempWordMap
        deRefWordMapFn = (wordAnalysis) -> return tempWordMap[wordAnalysis.rawWordStr]
        @allCompleteBasicWords = analysisModel.allCompleteBasicWords.map(deRefWordMapFn)
        @allCompleteTripleWords = analysisModel.allCompleteTripleWords.map(deRefWordMapFn)
        @allIncompleteBasicWords = analysisModel.allIncompleteBasicWords.map(deRefWordMapFn)
        @allIncompleteTripleWords = analysisModel.allIncompleteTripleWords.map(deRefWordMapFn)

  class WordReport
    constructor: (wordAnalysis) ->
      @rawStr = wordAnalysis.rawWordStr
      @displayStr = wordAnalysis.displayWordStr
      @missingLetterHash = wordAnalysis.missingLetterHash
      @missingLetterArray = wordAnalysis.missingLetterArray
