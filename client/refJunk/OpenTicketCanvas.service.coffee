'use strict'

module.exports = class OpenTicketCanvas
  @$inject = ['TicketPlayStateModelPackage']

  @AbstractTicket: null

  constructor: (TicketPlayStateModelPackage) ->
    OpenTicketCanvas.AbstractTicket = TicketPlayStateModelPackage.AbstractTicket

  setTargetDocument: (ticketDocument) =>
    @openDocument = ticketDocument
    @ticketModel  = ticketDocument.getDocumentRoot()

  setBonusWord: (bonusWord) =>
    @ticketModel.setBonusWord(bonusWord)

