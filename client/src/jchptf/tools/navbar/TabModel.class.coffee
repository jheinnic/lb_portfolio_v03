'use strict'

module.exports = class TabModel
  url = require('url')
  _ = require('lodash')

  constructor: (params) ->
    # Jetbrains WebStorm's CoffeeScript plugin is unaware of this somewhat recent language feature
    # @displayLabel, @clickRoute, @matchRoute} = params

    @displayLabel = params.displayLabel
    @clickRoute = params.clickRoute
    @matchRoute = params.matchRoute

    unless @displayLabel? and _.isString(@displayLabel)
      throw new Error('displayLabel is a mandatory string parameter')
    unless @clickRoute? and _.isString(@clickRoute) and parsedRoute = url.parse(@clickRoute)
      throw new Error('clickRoute is a mandatory url string parameter')

    if parsedRoute.query?.match /\?/
      throw new Error("clickRoute (#{@clickRoute}) has multiple query string separators")
    if parsedRoute.hash?.match /^#.*#/
      throw new Error("clickRoute (#{@clickRoute}) has multiple hash tags")
    if @clickRoute.match /:/
      throw new Error("clickRoute (#{@clickRoute}) must supply path variable values, not ':' prefixed bindings.")
    if @clickRoute isnt parsedRoute.href
      throw new Error("clickRoute (#{@clickRoute}) does not match parsed href (#{parsedRoute.href})")

    @matchRoute ?= new RegExp("^#{@clickRoute}$")
    unless @matchRoute.test @clickRoute
      throw new Error("When given, matchRoute (#{@matchRoute}) must match clickRoute (#{@clickRoute})")

    Object.freeze(this)

