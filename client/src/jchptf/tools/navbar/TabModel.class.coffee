'use strict'

module.exports = class TabModel
  url = require('url')
  _ = require('lodash')

  constructor: (params) ->
    # Jetbrains WebStorm's CoffeeScript plugin is unaware of this somewhat recent language feature
    # @displayLabel, @clickRoute, @matchRoute} = params

    @displayLabel = params.displayLabel
    @clickRoute = params.clickRoute
    @clickState = params.clickState
    @matchRoute = params.matchRoute

    switch
      when not _.isString(@displayLabel)
        errorMsg = 'displayLabel is a mandatory string parameter'
      when @clickState? and @clickRoute?
        errorMsg = 'Either clickState or clickRoute must be defined, but not both.'
      when _.isString(@clickRoute)
        parsedRoute not url.parse(@clickRoute)
        switch
          when not _.isObject(parsedRoute)
            errorMsg = 'clickRoute must be a url string parameter when provided'
          when parsedRoute.query?.match /\?/
            errorMsg = "clickRoute (#{@clickRoute}) has multiple query string separators"
          when parsedRoute.hash?.match /^#.*#/
            errorMsg = "clickRoute (#{@clickRoute}) has multiple hash tags"
          when @clickRoute.match /:/
            errorMsg = "clickRoute (#{@clickRoute}) must supply path variable values, not ':' prefixed bindings."
          when @clickRoute isnt parsedRoute.href
            errorMsg = "clickRoute (#{@clickRoute}) does not match parsed href (#{parsedRoute.href})"
          else
            @matchRoute ?= new RegExp("^#{@clickRoute}$")
            unless @matchRoute.test @clickRoute
              errorMsg = "When given, matchRoute (#{@matchRoute}) must match clickRoute (#{@clickRoute})"
      when _isString(@clickState)
        # TODO: Lookup the actual URL associated with clickState and perform parallel checks as with clickRoute.
      else
        errorMsg = 'Either clickState or clickRoute must be provided a suitable string value'

    throw new Error(errorMsg) if (_.isString(errorMsg))

    Object.freeze(this)

