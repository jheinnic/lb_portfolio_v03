module.exports = class JchNavData # @$inject = ['$q', 'NavBarModel', 'TabModel', 'NavBarBuilder']
  @$inject = ['$q']

  ###*
  # @type {lodash}
  ###
  _ = require('lodash')

  ###*
  # @type {$q}
  ###
  $q = undefined

  ###*
  # @constructor
  ###
  TabModel = require('./TabModel.class')

  ###*
  # @constructor
  ###
  NavBarModel = require('./NavBarModel.class')

  ###*
  # @constructor
  ###
  NavBarBuilder = require('./NavBarBuilder.class')


  ###*
  # JchNavData is a service singleton and so uses class-level variables (e.g. var foo; outside of
  # constructor) in lieu of either instance fields (e.g. this.foo; inside of constructor) or
  # hidden instance variables (e.g. var foo; inside of constructor with Object.defineProperties to
  # create getter/setter methods.
  #
  # @constructor
  ###
  constructor: (_$q) ->
    $q = _$q
    NavBarModel = require('./NavBarModel.class')
    TabModel = require('./TabModel.class')
    NavBarBuilder = require('./NavBarBuilder.class')

    updateHandle = $q.defer()

    nbDataModel = new NavBarBuilder(
      undefined
    ).brandName(
      'John Heinnickel'
    ).appendTab(
      'Home', '/home', /^\/home$/
    ).appendTab(
      'Crosswords', '/crosswords', /^\/crosswords$/
    ).appendTab(
      'Poker', '/poker/odds', /^\/poker\/odds$/
    ).build(updateHandle.promise)
#    nbDataModel = new NavBarModel(
#      refreshPromise: updateHandle.promise
#      brandName: 'John Heinnickel'
#      tabModels: [
#        new TabModel(
#          displayLabel: 'Home'
#          matchRoute: /^\/home$/
#          clickRoute: '/home'
#        )
#        new TabModel(
#          displayLabel: 'Crosswords'
#          matchRoute: /^\/crosswords$/
#          clickRoute: '/crosswords'
#        )
#        new TabModel(
#          displayLabel: 'Poker'
#          matchRoute: /^\/poker\/odds$/
#          clickRoute: '/poker/odds'
#        )
#        new TabModel(
#          displayLabel: 'Videos'
#          matchRoute: /^\/videos$/
#          clickRoute: '/videos'
#        )
#      ]
#    )

    Object.defineProperties(
      this,
      _nbDataModel:
        get: -> nbDataModel
        set: (newModel) -> nbDataModel = newModel
      _updateHandle:
        get: -> updateHandle
        set: (newHandle) -> updateHandle = newHandle
    )
    Object.freeze(this)

  getNavBarModel: ->
    console.log('getNBModel', this)
    @_nbDataModel

  changeNavBarModel: (changeDirector) ->
    unless (_.isFunction(changeDirector))
      msg = ("changeDirector argument must be a function, but got #{changeDirector} instead")
      console.err(msg)
      throw new Error(msg)

    navBarBuilder = new NavBarBuilder(@_nbDataModel)
    changeDirector(navBarBuilder)

    if navBarBuilder.hasChanges()
      lastDefer = @_updateHandle
      @_updateHandle = $q.defer()
      @_nbDataModel = navBarBuilder.build(@_updateHandle.promise)
      lastDefer.resolve(@_nbDataModel)
