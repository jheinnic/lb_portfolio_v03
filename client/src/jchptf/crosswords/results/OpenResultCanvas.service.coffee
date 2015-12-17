'use strict'

module.exports = OpenResultCanvas
OpenResultCanvas.$inject = ['XwResultModelPackage']

class OpenResultCanvas
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
