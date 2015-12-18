'use strict'

module.exports = class OpenResultCanvas
  @$inject = ['XwResultModelPackage']

  @AbstractResult: null

  constructor: (XwResultModelPackage) ->
    OpenResultCanvas.AbstractResult = XwResultModelPackage.AbstractResult
    @openDocument = undefined
    @resultModel = undefined

  setTargetDocument: (resultDocument) =>
    @openDocument = resultDocument
    @resultModel  = resultDocument.getDocumentRoot()

  setBonusValue: (bonusValue) =>
    @resultModel.setBonusValue(bonusValue)
