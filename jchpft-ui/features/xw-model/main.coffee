## TODO: Make this work!  Need to import 'amdefine'?
## if (typeof define != 'function')
##   define = require('amdefine')(module);

## TODO:  Eliminate replication of this code in jchpft-backend/lib/xwprovider.js

define ['angular'], () ->
  appModule = angular.module('xw-model', ['ng'])

  ##
  ## Factory Service
  ##
  class DocumentFactory
    constructor: () -> return @

    newTripleNoTwentyTicketDocument: (ticketId) ->
      retVal = new V1TriplingTicketDocument(
        ticketId
        'DESCRIBE'
        '_________________________________________________________________________________________________________________________'
        '__________________'
        -1
        '_____'
        -1
      )

      return retVal

    newTripleWithSpotTicketDocument: (ticketId) ->
      retVal = new V2TriplingTicketDocument(
        ticketId
        'DESCRIBE'
        '_________________________________________________________________________________________________________________________'
        '__________________'
        -1
        '_____'
        -1
        -1
      )

      return retVal

    newFiveXTicketDocument: (ticketId) ->
      retVal = new FiveXTicketDocument(
        ticketId
        'DESCRIBE'
        '_________________________________________________________________________________________________________________________'
        '__________________'
        -1
        -1
        -1
      )

      return retVal

    loadTicketDocument: (obj) ->
      if obj.hasOwnProperty('bonusWord')
        if obj.hasOwnProperty('spotValue')
          return loadTripleWithSpotTicketDocument(
            obj.ticketId, obj.lifeStage, obj.content, obj.yourLetters, obj.payoutValue, obj.bonusWord, obj.bonusValue, obj.spotValue
          )
        else
          return loadTripleNoTwentyTicketDocument(
            obj.ticketId, obj.lifeStage, obj.content, obj.yourLetters, obj.payoutValue, obj.bonusWord, obj.bonusValue
          )
      else if obj.hasOwnProperty('multiplier')
        return loadFiveXTicketDocument(
          obj.ticketId, obj.lifeStage, obj.content, obj.yourLetters, obj.payoutValue, obj.multiplier, obj.spotValue
        )

      return null


    loadTripleNoTwentyTicketDocument: (ticketId, lifeStage, content, yourLetters, payoutValue, bonusWord, bonusValue) ->
      return new V1TriplingTicketDocument(
        ticketId, lifeStage, content, yourLetters, payoutValue, bonusWord, bonusValue
      )

    loadTripleWithSpotTicketDocument: (ticketId, lifeStage, content, yourLetters, payoutValue, bonusWord, bonusValue, spotValue) ->
      return new V2TriplingTicketDocument(
        ticketId, lifeStage, content, yourLetters, payoutValue, bonusWord, bonusValue, spotValue
      )

    loadFiveXTicketDocument: (ticketId, lifeStage, content, yourLetters, payoutValue, multiplier, spotValue) ->
      return new FiveXTicketDocument(
        ticketId, lifeStage, content, yourLetters, payoutValue, multiplier, spotValue
      )

  appModule.service('xwDocumentFactory', [DocumentFactory])

  ##
  ## Ticket Document Model
  ##

  ## Constructors must only throw exceptions for invariant violations.  Object states that are not usable but possible
  ## to reach by editor use must be accepted and flagged in the UI as having correctable errors.

  class AbstractTicketDocument
    @identityRegex: /\d{3,4}:\d{6,7}\-\d{3}/

    constructor: (@ticketId, @lifeStage, @content, @yourLetters, @payoutValue) ->
      if ! AbstractTicketDocument.identityRegex.test(ticketId)
        throw 'InvalidArgumentException: ticketId = ' + ticketId + ' is malformed!'
      if lifeStage != 'DESCRIBE' && lifeStage != 'PLAY' && lifeStage != 'FINAL'
        throw 'InvalidArgumentException: lifeStage must be "DESCRIBE", "PLAY", or "FINAL".  lifeStage = ' + lifeStage
      if lifeStage == 'FINAL' && yourLetters.search('_') > -1
        throw 'InvalidArgumentException.  Cannot be Final with unresolved values in yourLetters.  yourLetters = ' + yourLetters
      if lifeStage == 'DESCRIBE' && yourLetters.search(/[^_]/) > -1
        throw 'InvalidArgumentException.  Cannot be have anything resolved in yourLetters while still describing ticket.  yourLetters = ' + yourLetters

      if lifeStage == 'FINAL' && payoutValue == -1
        throw 'InvalidArgumentException.  Cannot be Final unresolved payoutValue.  payoutValue = ' + payoutValue
      # TODO: Confirm max payout value (excluding INVALID)
      if lifeStage == 'FINAL' && payoutValue > 16
        throw 'InvalidArgumentException.  Final payoutValue cannot be out-of-bounds.  payoutValue = ' + payoutValue
      if lifeStage != 'FINAL' && payoutValue != -1
        throw 'InvalidArgumentException.  Final payoutValue cannot set until ticket is fully resolved.  payoutValue = ' + payoutValue

      # TODO: Regex validation of @content -- word count suitable for lifeStage

      return

  class V1TriplingTicketDocument extends AbstractTicketDocument
    constructor: (ticketId, lifeStage, content, yourLetters, payoutValue, @bonusWord, @bonusValue) ->
      super(ticketId, lifeStage, content, yourLetters, payoutValue)

      if lifeStage != 'DESCRIBE' && bonusWord.search(/[^_]/) > -1
        throw 'InvalidArgumentException.  Cannot be have anything resolved in bonusWord after describing ticket.  bonusWord = ' + bonusWord
      # TODO: Bonus word letter uniqueness

  class V2TriplingTicketDocument extends AbstractTicketDocument
    constructor: (ticketId, lifeStage, content, yourLetters, payoutValue, @bonusWord, @bonusValue, @spotValue) ->
      super(ticketId, lifeStage, content, yourLetters, payoutValue)

  class FiveXTicketDocument extends AbstractTicketDocument
    constructor: (ticketId, lifeStage, content, yourLetters, payoutValue, @mutiplier, @spotValue) ->
      super(ticketId, lifeStage, content, yourLetters, payoutValue)

  return appModule
