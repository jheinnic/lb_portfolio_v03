_ = require('lodash')

module.exports = JchNavData

class JchNavData
  @$inject: ['$q', 'NavBarModelPackage']

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

  # This is a singleton
  constructor: ($q, NavBarModelPackage) ->
    TabModel = NavBarModelPackage.TabModel
    NavBarModel = NavBarModelPackage.NavBarModel
    NavBarBuilder = NavBarModelPackage.NavBarBuilder

    @$q = $q
    @updateHandle = $q.defer()
    @nbDataModel = new NavBarModel(
      brandName: 'John Heinnickel',
      tabModels: [
        new TabModel displayLabel: 'Home', matchRoute: '/$', clickRoute: '/'
        new TabModel displayLabel: 'Crosswords', matchRoute: '/crosswords$', clickRoute: '/crosswords'
        new TabModel displayLabel: 'Poker', matchRoute: '/poker/odds$', clickRoute: '/poker/odds'
        new TabModel displayLabel: 'Videos', matchRoute: '/videos$', clickRoute: '/videos'
      ]
      refreshPromise: updateHandle.promise
    )

  getNavBarModel: () =>
    return Object.freeze _.deepCopy @nbDataModel

  changeNavBarModel: (changeDirector) =>
    updatedModel  = _.deepCopy @nbDataModel
    navBarBuilder = new NavBarBuilder(updatedModel)
    changeDirector(navBarBuilder)

    if navBarBuilder.hasUpdatedModel?
      lastDefer = @updateHandle
      @updateHandle = @$q.defer()
      @nbDataModel  = updatedModel
      @nbDataModel.nextPromise = @updateHandle.promise

      lastDefer.resolve( @getNavBarModel() )
