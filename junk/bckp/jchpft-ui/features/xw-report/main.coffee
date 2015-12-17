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

  class TicketReportState
    constructor: (
      ticketId, description, discovery, progressReport, nextProgressDeferal, payoutReport, nextPayoutDeferal
    ) ->
      @ticketId = ticketId
      @description = description
      @discovery = discovery
      @progressReport = progressReport
      @nextProgressDeferal = nextProgressDeferal
      @payoutReport = payoutReport
      @nextPayoutDeferal = nextPayoutDeferal
      # TODO: If we use $interval to analyze payouts client-side and incrementally, we may want a handle for
      #       cancelation when new information deprecates an ongoing calculation.

  class XwReportService
    ticketReportCache: {}

    constructor: ($q) ->
      @$q = $q
      @payoutReportDeferal = $q.defer()
      @payoutReportObject = new PayoutReportModel(null, null, @payoutReportDeferal.promise)
      return @

    # TODO: Remove this from the callable public interface while still dealing with @$q needing
    #       'this' to be set correctly.
    getTicketReportState: (ticketId) ->
      ticketState = @ticketReportCache[ticketId]
      if !ticketState?
        progressDeferal = @$q.defer()
        payoutDeferal = @$q.defer()
        ticketState =
          new TicketReportState(
            ticketId, null, null, null, progressDeferal, null, payoutDeferal
          )
        @ticketReportCache[ticketId] = ticketState

      return ticketState

    getProgressReport: (ticketId) ->
      ticketState = @getTicketReportState(ticketId)

      if ticketState.progressReport?
        retVal = ticketState.progressReport
      else
        retVal = ticketState.nextProgressDeferal.promise
      return retVal

    updateV1TriplingDescription: (ticketId, basicWords, tripleWords, bonusWord) ->
      ticketState = @getTicketReportState(ticketId)
      ticketState.description = new V1TriplingDescriptionContent(ticketId, basicWords, tripleWords, bonusWord)
      if !ticketState.discovery?
        ticketState.discovery = new V1TriplingDiscoveryContent(ticketId, {}, -1)
      analyzeV1TriplingProgress( ticketState.description, ticketState.discovery )
      return

    updateV2TriplingDescription: (ticketId, basicWords, tripleWords, bonusWord) ->
      ticketState = @getTicketReportState(ticketId)
      ticketState.description = new V2TriplingDescriptionContent(ticketId, basicWords, tripleWords, bonusWord)
      if !ticketState.discovery?
        ticketState.discovery = new V2TriplingDiscoveryContent(ticketId, {}, -1, -1)
      analyzeV2TriplingProgress( ticketState.description, ticketState.discovery )
      return

    updateFiveXDescription: (ticketId, basicWords) ->
      ticketState = @getTicketReportState(ticketId)
      ticketState.description = new FiveXDescriptionContent(ticketId, basicWords)
      if !ticketState.discovery?
        ticketState.discovery = new FiveXDiscoveryContent(ticketId, {}, -1, -1)
      analyzeFiveXProgress( ticketState.description, ticketState.discovery )
      return

    analyzeV1TriplingProgress = (description, discovery) ->
      satisfyLastProgressPromise new V1TriplingProgressReport(
        description.ticketId
        discovery.yourLetters
        description.basicWords
        description.tripleWords
        description.bonusWord
        discovery.bonusValue
      )
      return

    analyzeV2TriplingProgress = (description, discovery) ->
      satisfyLastProgressPromise new V2TriplingProgressReport(
        description.ticketId
        discovery.yourLetters
        description.basicWords
        description.tripleWords
        description.bonusWord
        discovery.bonusValue
        discovery.spotValue
      )
      return

    analyzeFiveXProgress = (description, discovery) ->
      satisfyLastProgressPromise new FiveXProgressReport(
        description.ticketId
        discovery.yourLetters
        description.basicWords
        discovery.multiplier
        discovery.spotValue
      )
      return

    satisfyLastProgressPromise: (progressReport) ->
      nextDeferal = @$q.defer()
      progressReport.promiseNextUpdate nextDeferal.promise

      ticketState = getTicketReportState(progressReport.ticketId)
      ticketState.nextProgressDeferal.resolve progressReport
      ticketState.nextProgressDeferal = nextDeferal
      ticketState.progressReport = progressReport

    getPayoutReport: (ticketId) ->
      ticketState = @getTicketReportState(ticketId)

      if ticketState.payoutReport?
        retVal = ticketState.payoutReport
      else
        retVal = ticketState.nextPayoutDeferal.promise
      return retVal

    updatePayoutReport: (analysisModel) ->
      tempDeferal = @progressReportDeferal
      @progressReportDeferal = @$q.defer()
      @progressReportModel = new ProgressReportModel(analysisModel, @progressReportDeferal.promise)
      tempDeferal.resolve(@progressReportModel)
      return

    doofoo: (analysisModel) ->
      tempDeferal = @payoutReportDeferal
      @payoutReportDeferal = @$q.defer()
      @payoutReportObject = new PayoutReportModel(analysisModel, @payoutReportDeferal.promise)
      tempDeferal.resolve(@payoutReportObject)
      return

  ##
  ## Description Phase Models
  ##

  class AbstractDescriptionContent
    constructor: (@ticketId, @basicWords) ->
      return

  class V1TriplingDescriptionContent extends AbstractDescriptionContent
    constructor: (ticketId, basicWords, @tripleWords, @bonusWord) ->
      super(ticketId, basicWords)
      return

    getTicketKind: () -> return 'TRIPLINGV1'

  class V2TriplingDescriptionContent extends AbstractDescriptionContent
    constructor: (ticketId, basicWords, @tripleWords, @bonusWord) ->
      super(ticketId, basicWords)
      return

    getTicketKind: () -> return 'TRIPLINGV2'

  class FiveXDescriptionContent extends AbstractDescriptionContent
    constructor: (ticketId, basicWords) ->
      super(ticketId, basicWords)
      return

    getTicketKind: () -> return 'FIVE_X'

  ##
  ## Discovery Phase Models
  ##

  class AbstractDiscoveryContent
    constructor: (@ticketId, @yourLetters) ->
      return

  class V1TriplingDiscoveryContent extends AbstractDiscoveryContent
    constructor: (ticketId, yourLetters, @bonusValue) ->
      super(ticketId, yourLetters)
      return

  class V2TriplingDiscoveryContent extends AbstractDiscoveryContent
    constructor: (ticketId, yourLetters, @bonusValue, @spotValue) ->
      super(ticketId, yourLetters)
      return

  class FiveXDiscoveryContent extends AbstractDiscoveryContent
    constructor: (ticketId, yourLetters, @multiplier, @spotValue) ->
      super(ticketId, yourLetters)
      return

  ##
  ## Analysis Models
  ##

  class AbstractProgressReport
    constructor: (@ticketId, yourLetters, basicWords) ->
      @allBasicWords = basicWords.map( (basicWord) -> new BasicWordAnalysis(basicWord, yourLetters) )
      @completeBasicWords = new Array(@allBasicWords.length)
      @incompleteBasicWords = new Array(@allBasicWords.length)
      @allBasicWords.forEach( (analyzedWord) ->
        if analyzedWord.isComplete
          @completeBasicWords.push(analyzedWord)
        else
          @incompleteBasicWords.push(analyzedWord)
      )
      return

    promiseNextUpdate: (updatePromise) ->
      @updatePromise = updatePromise
      return


  class V1TriplingProgressReport extends AbstractProgressReport
    constructor: (ticketId, yourLetters, basicWords, triplingWords, bonusWord, @bonusValue) ->
      super(ticketId, yourLetters, basicWords)
      @allTriplingWords = triplingWords.map((triplingWord) -> new TriplingWordAnalysis(triplingWord, yourLetters))
      @bonusWord = new BasicWordAnalysis(bonusWord, yourLetters)
      return

  class V2TriplingProgressReport extends AbstractProgressReport
    constructor: (@ticketId, yourLetters, basicWords, triplingWords, bonusWord, @bonusValue, @spotValue) ->
      super(ticketId, yourLetters, basicWords)
      @allTriplingWords = triplingWords.map((triplingWord) -> new TriplingWordAnalysis(triplingWord, yourLetters))
      @bonusWord = new BasicWordAnalysis(bonusWord, yourLetters)
      return

  class FiveXProgressReport extends AbstractProgressReport
    constructor: (@ticketId, basicWords, yourLetters, @multiplier, @spotValue) ->
      super(ticketId, basicWords, yourLetters)
      return

  ##
  ## Word Analysis V2 -- Basic and Tripling varieties
  ##

  class AbstractWordAnalysis
    constructor: (rawWordStr, revealedLetters) ->
      missingLetterArray = new Array(rawWordStr.length)
      @missingLetterHash = {}
      @rawWordStr = rawWordStr
      @isComplete = true

      deriveDisplayWord(missingLetterArray, revealedLetters)

      @missingLetterString = missingLetterArray.sort().join('')
      return

    deriveDisplayWord: (missingLetterArray, revealedLetters) ->
      @displayWordStr =
        @rawWordStr.split('').map(
          (nextChar) ->
            if (revealedLetters[nextChar] > 0)
              nextChar = '*'
            else
              @isComplete = false
              if @missingLetterHash[nextChar] < 1
                @missingLetterHash[nextChar] = 1
                missingLetterArray.push(nextChar)
            return nextChar
        ).join('')
      return

  class BasicWordAnalysis extends AbstractWordAnalysis
    constructor: (rawWordStr, revealedLetters) ->
      super(rawWordStr, revealedLetters)
      return

  class TriplingWordAnalysis
    constructor: (rawWordStr, revealedLetters) ->
      super(rawWordStr, revealedLetters)
      return

    deriveDisplayWord: (missingLetterArray, revealedLetters) ->
      @displayWordStr =
        @rawWordStr.split('').map(
          (nextChar) ->
            if (revealedLetters[nextChar] > 0)
              nextChar = '*'
            else
              nextChar = nextChar.toLowerCase()
              if(revealedLetters[nextChar] > 0)
                nextChar = '#'
              else
                @isComplete = false
                if @missingLetterHash[nextChar] < 1
                  @missingLetterHash[nextChar] = 1
                  missingLetterArray.push(nextChar)
              return nextChar
        ).join('')
      return

  xwModule.service 'xwReportService', ['$q', XwReportService]


  class PayoutReportModel
    constructor: (@ticketId, @prizeCounts, @updatePromise) -> return @
