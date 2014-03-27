# TODO: Make this work!  Need to import 'amdefine'
# if (typeof define != 'function')
#   define = require('amdefine')(module);

define ['angular'], () ->
  appModule = angular.module('xw-model', ['ng'])

  ##
  ## Factory Service
  ##
  class DocumentFactory
    # TODO: Figure out why we wanted $q!?
    constructor: () -> return @

    newTripleNoTwentyTicketDocument: (ticketId) ->
      retVal = new TicketDocument(
        ticketId
        '_________________________________________________________________________________________________________________________'
        '__________________'
        -1
          variantKind: TicketVariantKind.TRIPLING_NO_TWENTY
          tripleNoTwenty:
            bonusWord: '_____'
            bonusValue: -1
          tripleWithSpot: null
          fiveX: null
      )

      return retVal

    loadTripleNoTwentyTicketDocument: (ticketId, content, yourLetters, payoutValue, bonusWord, bonusValue) ->
      return new TicketDocument(
        ticketId
        content
        yourLetters
        payoutValue
          variantKind: TicketVariantKind.TRIPLING_NO_TWENTY
          tripleNoTwenty:
            bonusWord: bonusWord
            bonusValue: bonusValue
          tripleWithSpot: null
          fiveX: null
      )

    loadTripleWithSpotTicketDocument: (ticketId, content, yourLetters, payoutValue, bonusWord, bonusValue, spotValue) ->
      return new TicketDocument(
        ticketId
        content
        yourLetters
        payoutValue
          variantKind: TicketVariantKind.TRIPLING_NO_TWENTY
          tripleNoTwenty: null
          tripleWithSpot:
            bonusWord: bonusWord
            bonusValue: bonusValue
            spotValue: spotValue
          fiveX: null
      )

    loadTripleFiveXTicketDocument: (ticketId, content, yourLetters, payoutValue, multiplier, spotValue) ->
      return new TicketDocument(
        ticketId
        content
        yourLetters
        payoutValue
          variantKind: TicketVariantKind.FIVE_X
          tripleNoTwenty: null
          tripleWithSpot: null
          fiveX:
            multiplier: multiplier
            spotValue: spotValue
      )

  appModule.service('xwDocumentFactory', [DocumentFactory])

  ##
  ## Ticket Document Model
  ##

  class TicketDocument
    @identityRegex: /\d{3}:\d{6,7}\-\d{3}/

    constructor: (@ticketId, @content, @yourLetters, @payoutValue, @variantData) ->
      if (TicketDocument.identityRegex.test(ticketId) == false)
        throw 'Invalid Argument: ticketId = ' + ticketId + ' is malformed!'

    changeVariantKind: (variantKind) ->
      origVariantKind = @variantData?.variantKind || TicketVariantKind.UNKNOWN
      if (origVariantKind != variantKind)
        if (origVariantKind != TicketVariantKind.UNKNOWN)
          origVariantData = @variantData[origVariantKind]

        switch variantKind
          when TicketVariantKind.TRIPLING_NO_TWENTY
            @variantData =
              variantKind: variantKind
              tripleNoTwenty:
                bonusWord: origVariantData?.bonusWord || '_____'
                bonusValue: origVariantData?.bonusValue || -1
              tripleWithSpot: null
              fiveX: null
          when TicketVariantKind.TRIPLING_WITH_SPOT
            @variantData =
              variantKind: variantKind
              tripleNoTwenty: null
              tripleWithSpot:
                bonusWord: origVariantData?.bonusWord || '_____'
                bonusValue: origVariantData?.bonusValue || -1
                spotValue: origVariantData?.spotValue || -1
              fiveX: null
          when TicketVariantKind.FIVE_X
            @variantData =
              variantKind: variantKind
              tripleNoTwenty: null
              tripleWithSpot: null
              fiveX:
                multiplier: -1
                spotValue: origVariantData?.spotValue || -1
          else
            @variantData =
              variantKind: TicketVariantKind.UNKNOWN
              tripleNoTwenty: null
              tripleWithSpot: null
              fiveX: null
      return

  class TicketVariantKind
    @UNKNOWN: 'unknown'
    @TRIPLING_NO_TWENTY: 'tripleNoTwenty'
    @TRIPLING_WITH_SPOT: 'tripleWithSpot'
    @FIVE_X: 'fiveX'

  return appModule
