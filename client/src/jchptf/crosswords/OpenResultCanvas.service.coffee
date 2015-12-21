'use strict'

module.exports = class OpenResultCanvas
  @$inject = []

  resultModel = undefined

  # constructor: ->

  setResultModel: (_resultModel) -> resultModel  = _resultModel

  setBonusValue: (bonusValue) -> resultModel?.setBonusValue(bonusValue)

