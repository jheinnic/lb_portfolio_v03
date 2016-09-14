'use strict'

TabModel = require('./TabModel.class')
module.exports = class RouteTabModel extends TabModel

  url = require('url')
  _ = require('lodash')

  constructor: (params) ->
    # Jetbrains WebStorm's CoffeeScript plugin is unaware of this somewhat recent language feature
    # @displayLabel, @clickRoute, @matchRoute} = params
    super(params)
    @clickRoute = params.clickRoute

    if not _.isString(@clickRoute)
      throw new Error('displayLabel is a mandatory string parameter')

    parsedRoute = url.parse(@clickRoute)
    switch
      when not _.isObject(parsedRoute)
        throw new Error('clickRoute must be a url string parameter when provided')
      when parsedRoute.query?.match(/\?/)
        throw new Error("clickRoute (#{@clickRoute}) has multiple query string separators")
      when parsedRoute.hash?.match(/^#.*#/)
        throw new Error("clickRoute (#{@clickRoute}) has multiple hash tags")
      when @clickRoute.match(/:/)
        throw new Error("clickRoute (#{@clickRoute}) must supply path variable values, not ':' prefixed bindings.")
      when @clickRoute isnt parsedRoute.href
        throw new Error("clickRoute (#{@clickRoute}) does not match parsed href (#{parsedRoute.href})")
      else
        @matchRoute ?= new RegExp("^#{@clickRoute}$")

    if not @matchRoute.test(@clickRoute)
      throw new Error("When given, matchRoute (#{@matchRoute}) must match clickRoute (#{@clickRoute})")

    Object.freeze(this)

  isRouteBased: ->
    return true

  isStateBased: ->
    return false

  getMatchRoute: ->
    return @matchRoute.source
