'use strict'

module.exports = class NavBarModel
  constructor: (params) ->
    # Jetbrains WebStorm's CoffeeScript plugin is unaware of this somewhat recent language feature
    # {@brandName, @tabModels, @refreshPromise} = params

    # TODO: Type validation
    @brandName = params.brandName or ''
    @tabModels = Object.freeze(params.tabModels or [])
    @refreshPromise = params.refreshPromise or null

    return Object.freeze(this)

