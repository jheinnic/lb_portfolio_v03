'use strict'

module.exports = class NavBarModel
  brandName: ''
  tabModels: []
  refreshPromise: null

  constructor: (params) ->
    # Jetbrains WebStorm's CoffeeScript plugin is unaware of this somewhat recent language feature
    # {@brandName, @tabModels, @refreshPromise} = params

    # TODO: Type validation
    this.brandName = params.brandName or ''
    this.tabModels = params.tabModels or []
    this.refreshPromise = params.refreshPromise or null

    Object.freeze(this.tabModels)
    return Object.freeze(this)
