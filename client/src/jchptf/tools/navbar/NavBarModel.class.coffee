'use strict'

module.exports = class NavBarModel
  brandName: ''
  tabModels: []
  refreshPromise: null

  constructor: (params) ->
    # Jetbrains WebStorm's CoffeeScript plugin is unaware of this somewhat recent language feature
    # {@brandName, @tabModels, @refreshPromise} = params

    # TODO: Type validation
    @brandName = params.brandName || ''
    @tabModels = params.tabModels || []
    @refreshPromise = params.refreshPromise || null

    Object.freeze(@tabModels)
    Object.freeze(@)
