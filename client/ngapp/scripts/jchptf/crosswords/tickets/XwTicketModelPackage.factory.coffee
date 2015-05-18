## TODO:  Eliminate replication of this code in jchpft-backend/lib/xwprovider.js

module.exports = XwModelPackage

XwModelPackage.$inject = ['CoreModelPackage', 'DocumentModelPackage']

XwModelPackage = (CoreModelPackage, DocumentModelPackage) ->
  Enum = CoreModelPackage.Enum
  {AbstractDocument, DocumentKind, ExportRoleKind, ModelObject} = DocumentModelPackage

  class LifeStageKind extends Enum
  new LifeStageKind('DESCRIBE')
  new LifeStageKind('PLAY')
  new LifeStageKind('FINAL')
  LifeStageKind.finalize()

  class GameVariantKind extends Enum
    constructor: (name, @hasBonusWord, @hasTwentySpot, @hasMultiplier) ->
      super(name)

    hasBonusWord: => @hasBonusWord
    hasMultiplier: => @hasMultiplier
    hasTwentySpot: => @hasTwentySpot

  new GameVariantKind('TRIPLE_NO_TWENTY', true, false, false)
  new GameVariantKind('TRIPLE_WITH_SPOT', true, true, false)
  new GameVariantKind('FIVE_X', false, true, true)

  ##
  ## ModelFactory Service
  ##
  class XwModelFactory
    constructor: () ->

    getClassForVariant: (variantKind) ->
      if typeof variantKind == 'string'
        lookupKind = GameVariantKind.valueOf(variantKind)
        if lookupKind?
          variantKind = lookupKind
        else
          throw new Error "#{variantKind} does not name a GameVariantKind value"

      switch variantKind
        when GameVariantKind.TRIPLE_NO_TWENTY
          retVal = TripleNoTwentyTicket
        when GameVariantKind.TRIPLE_WITH_SPOT
          retVal = TripleWithSpotTicket
        when GameVariantKind.FIVE_X
          retVal = FiveXTicket
        else
          throw new Error "#{variantKind} is not a GameVariantKind value."

    newTripleNoTwentyTicket: (ticketId) ->
      new TripleNoTwentyTicket(
        ticketId: ticketId
        lifeStage: LifeStageKind.DESCRIBE
        content: '_________________________________________________________________________________________________________________________'
        yourLetters: '__________________'
        payoutValue: -1
        bonusWord: '_____'
        bonusValue: -1
      )

    newTripleWithSpotTicket: (ticketId) ->
      new TripleWithSpotTicket(
        ticketId: ticketId
        lifeStage: LifeStageKind.DESCRIBE
        content: '_________________________________________________________________________________________________________________________'
        yourLetters: '__________________'
        payoutValue: -1
        bonusWord: '_____'
        bonusValue: -1
        spotValue: -1
      )

    newFiveXTicket: (ticketId) ->
      new FiveXTicket(
        ticketId: ticketId
        lifeStage: LifeStageKind.DESCRIBE
        content: '_________________________________________________________________________________________________________________________'
        yourLetters: '__________________'
        payoutValue: -1
        multiplier: -1
        spotValue: -1
      )

    loadTicket: (obj) ->
      if obj.hasOwnProperty('bonusWord')
        if obj.hasOwnProperty('spotValue')
          return new TripleWithSpotTicket(obj)
        else
          return new TripleNoTwentyTicket(obj)
      else if obj.hasOwnProperty('multiplier')
        return new FiveXTicket(obj)

      return null

  # appModule.service('xwXwModelFactory', [XwModelFactory])

  ##
  ## Ticket  Model
  ##

  ## Constructors must only throw exceptions for invariant violations.  Object states that are not usable but possible
  ## to reach by editor use must be accepted and flagged in the UI as having correctable errors.

  class AbstractTicket extends ModelObject
    @identityRegex: /\d{3,4}:\d{6,7}\-\d{3}/

    constructor: (params, variant) ->
      {@ticketId, @lifeStage, @content, @yourLetters, @payoutValue} = params

      if ! AbstractTicket.identityRegex.test(@ticketId)
        throw new Error "ticketId = #{@ticketId} has a malformed identifier!"
      if @lifeStage != LifeStageKind.DESCRIBE && @lifeStage != LifeStageKind.PLAY && @lifeStage != LifeStageKind.FINAL
        throw new Error "@lifeStage must be \"DESCRIBE\", \"PLAY\", or \"FINAL\", but @lifeStage = #{@lifeStage}"
      if @lifeStage == LifeStageKind.FINAL && @yourLetters.search('_') > -1
        throw new Error "@lifeStage cannot be \"FINAL\" with unresolved values in @yourLetters, but @yourLetters = #{@yourLetters}."
      if @lifeStage == LifeStageKind.DESCRIBE && @yourLetters.search(/[^_]/) > -1
        throw new Error "Cannot have any resolved letters while still describing ticket, but @yourLetters = #{@yourLetters}."

      if @lifeStage == LifeStageKind.FINAL && @payoutValue == -1
        throw new Error "Lifestage cannot be \"FINAL\" with an unresolved @payoutValue, but @payoutValue = #{@payoutValue}."
      # TODO: Confirm max payout value (excluding INVALID)
      if @lifeStage != LifeStageKind.FINAL && @payoutValue != -1
        throw new Error "Final @payoutValue cannot set until ticket is fully revealed, but @payoutValue = #{@payoutValue}."
      if @payoutValue > 16
        throw new Error "Final @payoutValue cannot be out-of-bounds, but @payoutValue = #{@payoutValue}."

      # TODO: Regex validation of @content -- word count suitable for @lifeStage

    getGameVariantKind: () -> throw new Error "Concrete Ticket subtypes must override getGameVariantKind()"

  class TripleNoTwentyTicket extends AbstractTicket
    constructor: (params) ->
      super(params)
      {@bonusWord, @bonusValue} = params

      if @lifeStage != LifeStageKind.DESCRIBE && @bonusWord.search(/[^_]/) > -1
        throw new Error "Cannot have anything resolved in bonusWord while still describing ticket, but bonusWord = #{@bonusWord}"
      # TODO: Bonus word letter uniqueness

    getGameVariantKind: () -> GameVariantKind.TRIPLE_NO_TWENTY

  class TripleWithSpotTicket extends AbstractTicket
    constructor: (params) ->
      super(params)
      {@bonusWord, @bonusValue, @spotValue} = params

    getGameVariantKind: () -> GameVariantKind.TRIPLE_WITH_SPOT

  class FiveXTicket extends AbstractTicket
    constructor: (params) ->
      super(params)
      {@multiplier, @spotValue} = params

    getGameVariantKind: () -> GameVariantKind.FIVE_X

  XwTicketDocument extends AbstractDocument

  XwTicketExportRoleKind extends ExportRoleKind
    @_ABSTRACT = false

  new XwTicketExportRoleKind('forResults', AbstractTicket)
  XwTicketExportRoleKind.finalize()

  new DocumentKind('XW_TICKET', 'xwt', XwTicketDocument, XwTicketExportRoleKind)

  return
    XwModelFactory: XwModelFactory
    TripleNoTwentyTicket: TripleNoTwentyTicket
    TripleWithSpotTicket: TripleWithSpotTicket
    FiveXTicket: FiveXTicket
    GameVariantKind: GameVariantKind
    LifeStageKind: LifeStageKind
