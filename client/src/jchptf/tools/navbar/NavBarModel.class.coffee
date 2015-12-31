'use strict'

module.exports = class NavBarModel
  brandName: ''
  tabModels: []
  refreshPromise: null

  constructor: (params) ->
    # Jetbrains WebStorm's CoffeeScript plugin is unaware of this somewhat recent language feature
    # {@brandName, @tabModels, @refreshPromise} = params

    # TODO: Type validation
    @brandName = params.brandName or ''
    @tabModels = params.tabModels or []
    @refreshPromise = params.refreshPromise or null

    Object.freeze(@tabModels)
    return Object.freeze(this)
