module.exports = NavBarModel

TabModel = require('./TabModel.model')

class NavBarModel
  constructor: (params) ->
    {@brandName, @tabModels, @refreshPromise} = params

    unless @brandName? then @brandName = ''
    unless @tabModels? then @tabModels = []

    console.log(typeof @refreshPromise)
    console.log(typeof @tabModels)


