module.exports = class JchNavData
  @$inject = ['$q', 'NavBarModel', 'TabModel', 'NavBarBuilder']

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
  TabModel = undefined

  ###*
  # @constructor
  ###
  NavBarModel = undefined

  ###*
  # @constructor
  ###
  NavBarBuilder = undefined

  ###*
  # @type {Defer}
  ###
  updateHandle = undefined

  ###*
  # @type {NavBarModel}
i ###
  nbDataModel = undefined


  ###*
  # JchNavData is a service singleton and so uses class-level variables (e.g. var foo; outside of
  # constructor) in lieu of either instance fields (e.g. this.foo; inside of constructor) or
  # hidden instance variables (e.g. var foo; inside of constructor with Object.defineProperties to
  # create getter/setter methods.
  #
  # @constructor
  ###
  constructor: (_$q, _NavBarModel, _TabModel, _NavBarBuilder) ->
    $q = _$q
    NavBarModel = _NavBarModel
    TabModel = _TabModel
    NavBarBuilder = _NavBarBuilder

    updateHandle = $q.defer()
    nbDataModel = new NavBarModel(
      brandName: 'John Heinnickel'
      tabModels: [
        new TabModel(
          displayLabel: 'Home'
          matchRoute: /^\/home$/
          clickRoute: '/home'
        )
        new TabModel(
          displayLabel: 'Crosswords'
          matchRoute: /^\/crosswords$/
          clickRoute: '/crosswords'
        )
        new TabModel(
          displayLabel: 'Poker'
          matchRoute: /^\/poker\/odds$/
          clickRoute: '/poker/odds'
        )
        new TabModel(
          displayLabel: 'Videos'
          matchRoute: /^\/videos$/
          clickRoute: '/videos'
        )
      ]
    )

    Object.defineProperties(
      this,
      _nbDataModel:
        getter: -> nbDataModel
        setter: (newModel) -> nbDataModel = newModel
      _updateHandle:
        getter: -> updateHandle
        setter: (newHandle) -> updateHandle = newHandle
    )
    Object.seal(this)

  getNavBarModel: ->
    console.log("getNBModel", this)
    this._nbDataModel

  changeNavBarModel: (changeDirector) ->
    navBarBuilder = new NavBarBuilder(this._nbDataModel)
    changeDirector(navBarBuilder)

    if navBarBuilder.hasChanges?
      lastDefer = this._updateHandle
      this._updateHandle = $q.defer()

      this._nbDataModel = navBarBuilder.build(this._updateHandle.promise)
      lastDefer.resolve(this._nbDataModel)
