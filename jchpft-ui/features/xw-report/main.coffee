define [
  'angular'
  'ui-bootstrap-tpls'
  'cs!xw-dynamic/main'
], () ->
  xwModule = angular.module('xw-report', ['xw-dynamic']);

  xwModule.directive 'xwProgressReport', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.reportDirective('progress')
  ]

  xwModule.directive 'xwPayoutReport', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.reportDirective('payout')
  ]

  class XwReportService
    constructor: ($q) ->
      @$q = $q
      @progressReportDeferal = $q.defer()
      @progressReportObject = new ProgressReportModel(null, @progressReportDeferal.promise)
      @payoutReportDeferal = $q.defer()
      @payoutReportObject = new PayoutReportModel(null, null, @payoutReportDeferal.promise)
      return @

    getProgressReport: () ->
      return @progressReportObject

    updateProgressReport: (analysisModel) ->
      tempDeferal = @progressReportDeferal
      @progressReportDeferal = $q.defer()
      @progressReportObject = new ProgressReportModel(analysisModel, @progressReportDeferal.promise)
      tempDeferal.resolve(@progressReportObject)
      return

    getPayoutReport: () ->
      return @payoutReportObject

    updatePayoutReport: (analysisModel) ->
      tempDeferal = @payoutReportDeferal
      @payoutReportDeferal = $q.defer()
      @payoutReportObject = new PayoutReportModel(analysisModel, @payoutReportDeferal.promise)
      tempDeferal.resolve(@payoutReportObject)
      return

  class XwAnalysisFactory
    constructor: () -> return @

    createProgressAnalysisModel: (ticketId) ->
      return new ProgressAnalysisModel(ticketId)

  xwModule.service 'xwReportSvc', ['$q', XwReportService]

  xwModule.service 'xwAnalysisFactory', ['$q', XwAnalysisFactory]


  ##
  ## Model Classes
  ##

  ##
  ## Ticket Anaylsis Models
  ##

  class ProgressAnalysisModel
    constructor: (@ticketId) ->
      @purgeWordLists()
      @purgeLettersHash()
      return @

    purgeWordLists: () =>
      @purgedWordLists = true
      @bonusWord       = null
      @allWords        = new Array()
      @purgeAnalysis()
      return

    purgeLettersHash: () =>
      @purgedLettersHash    = true
      @revealedLettersHash = {}
      @purgeAnalysis()
      return

    purgeAnalysis: () =>
      @allWords.forEach( (wordAnalysis) -> wordAnalysis.reset() )
      @incompleteTripleWords = null
      @incompleteBasicWords  = null
      @completeTripleWords   = null
      @completeBasicWords    = null
      @incompleteWords       = null
      @completeWords         = null
      @tripleWords           = null
      @basicWords            = null
      return

    setBonusWord: (rawWordStr) =>
      if (@purgedWordLists == false)
        throw 'IllegalStateException'
      else if (@bonusWord?)
        throw 'IllegalStateException'

      @bonusWord = new WordAnalysis(rawWordStr,true)
      @allWords.push(@bonusWord)
      return

    addWord: (rawWordStr) =>
      if (@purgedWordLists == false)
        throw 'IllegalStateException'

      @allWords.push(new WordAnalysis(rawWordStr,false))
      return

    addRevealedLetter: (revealedLetter) =>
      if (@purgedLettersHash == false)
        throw 'IllegalStateException'

      @revealedLettersHash[revealedLetter] = 1
      return

    refreshAnalysis: () =>
      if (@purgedLettersHash == true && @purgedWordLists == true)
        throw 'IllegalStateException'

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
      return

    hasTripleBonus: () =>
      if (@purgedLettersHash == false || @purgedWordLists == false)
        throw 'IllegalStateException'
      return @completeTripleWords.length > 0

  class WordAnalysis
    constructor: (@rawWordStr, @isBonus) ->
      @reset()
      return @

    reset: () =>
      @isTriple = null
      @isComplete = null
      @displayWordStr = null
      @missingLetterHash = {}
      @missingLetterString = null
      @missingLetterArray = new Array()
      return

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
      return


  class ProgressReportModel
    constructor: (analysisModel, @updatePromise) ->
      if (analysisModel?)
        @ticketId = analysisModel.ticketId

        tempWordMap = {}
        buildWordMapFn = (wordAnalysis) -> tempWordMap[wordAnalysis.rawWordStr] = new WordReport(wordAnalysis)
        analysisModel.allWords.forEach buildWordMapFn

        @bonusWord = tempWordMap
        deRefWordMapFn = (wordAnalysis) -> return tempWordMap[wordAnalysis.rawWordStr]
        @allCompleteBasicWords = analysisModel.allCompleteBasicWords.map(deRefWordMapFn)
        @allCompleteTripleWords = analysisModel.allCompleteTripleWords.map(deRefWordMapFn)
        @allIncompleteBasicWords = analysisModel.allIncompleteBasicWords.map(deRefWordMapFn)
        @allIncompleteTripleWords = analysisModel.allIncompleteTripleWords.map(deRefWordMapFn)
      return @

  class WordReport
    constructor: (wordAnalysis) ->
      @rawStr = wordAnalysis.rawWordStr
      @displayStr = wordAnalysis.displayWordStr
      @missingLetterHash = wordAnalysis.missingLetterHash
      @missingLetterArray = wordAnalysis.missingLetterArray
      return @

  class PayoutReportModel
    constructor: (@ticketId, @prizeCounts, @updatePromise) -> return @
