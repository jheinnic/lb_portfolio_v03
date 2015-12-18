_ = require('lodash')
url = require('url')

class TabModel
  # @::displayLabel = 'Index'
  # @::clickRoute = '/'
  # @::matchRoute = '^/$'

  # TODO: Enforce clickRoute path syntax. (Done)
  constructor: (params) ->
    {@displayLabel, @clickRoute, @matchRoute} = params

    parsedRoute = url.parse(@clickRoute)
    switch
      when @clickRoute != parsedRoute.path
        throw new Error "clickRoute, #{@clickRoute}, does not match parsed route, #{parsedRoute.path}"
      when parsedRoute.query.match(/\?/)?
        throw new Error "clickRoute, #{@clickRoute}, has more than one query separator"
      when @clickRoute.match(/:/)?
        throw new Error "clickRoute, #{@clickRoute}, must supply path variable values, not bindings."

    if @matchRoute?
      @matchRoute = new RegEx(@matchRoute)
      unless @clickRoute.match(matchRegex)?
        throw new Error "If given, matchRoute, #{@matchRoute}, must be a regex match to clickRoute, #{@clickRoute}"
    else
      @matchRoute = new RegEx("^#{@clickRoute}$")

    Object.seal(@)
