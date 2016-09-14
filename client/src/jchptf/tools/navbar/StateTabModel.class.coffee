'use strict'

TabModel = require('./TabModel.class')

module.exports = class StateTabModel extends TabModel

  _ = require('lodash')

  constructor: (params) ->
    # Jetbrains WebStorm's CoffeeScript plugin is unaware of this somewhat recent language feature
    # @displayLabel, @clickRoute, @matchRoute} = params
    super(params)
    $state = params.$state
    @clickState = params.clickState

    if not _.isString(@clickState)
      throw new Error('clickState is a mandatory string parameter')
    if _.isUndefined($state.get(@clickState))
      throw new Error('clickState must be a valid state name')

    @setMatchRoute(
      $state.href(@clickState))
    Object.freeze(this)

  isRouteBased: ->
    return false

  isStateBased: ->
    return true
