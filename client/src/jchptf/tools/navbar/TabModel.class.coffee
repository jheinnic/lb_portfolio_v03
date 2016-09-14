'use strict'

module.exports = class TabModel
  _ = require('lodash')

  constructor: (params) ->
    # Jetbrains WebStorm's CoffeeScript plugin is unaware of this somewhat recent language feature
    # @displayLabel, @clickRoute, @matchRoute} = params
    @displayLabel = params.displayLabel
    @matchRoute = params.matchRoute
    if not _.isString(@displayLabel)
      throw new Error('displayLabel is a mandatory string parameter')

  isRouteBased: ->
    return undefined

  isStateBased: ->
    return undefined

  setMatchRoute: (url) ->
    @matchRoute = new RegExp("^#{url}")

  getMatchRoute: ->
    return @matchRoute.source
