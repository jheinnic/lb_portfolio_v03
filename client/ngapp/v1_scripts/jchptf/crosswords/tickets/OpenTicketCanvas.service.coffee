'use strict'

module.exports = OpenTicketCanvas
OpenTicketCanvas.$inject = ['XwTicketModelPackage']

class OpenTicketCanvas
  @AbstractTicket: null

  constructor: (XwTicketModelPackage) ->
    OpenTicketCanvas.AbstractTicket = XwTicketModelPackage.AbstractTicket

  setTargetDocument: (ticketDocument) =>
    @openDocument = ticketDocument
    @ticketModel  = ticketDocument.getDocumentRoot()

  setBonusWord: (bonusWord) =>
    @ticketModel.setBonusWord(bonusWord)
