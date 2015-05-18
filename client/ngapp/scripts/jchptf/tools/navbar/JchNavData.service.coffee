_ = require('lodash')

module.exports = JchNavData

{TabModel, NavBarModel, NavBarBuilder} = require('./NavBar.modelPkg')
# TabModel = require('./TabModel.model')
# NavBarModel = require('./NavBarModel.model')
# NavBarBuilder = require('./NavBarBuilder.model')

class JchNavData
  @$inject: ['$q']

  # This is a singleton
  constructor: ($q) ->
    @$q = $q
    @updateHandle = $q.defer()
    @nbDataModel = new NavBarModel(
      brandName: 'John Heinnickel',
      tabModels: [
        new TabModel displayLabel: 'Home', matchRoute: '/$', clickRoute: '/'
        new TabModel displayLavel: 'Crosswords', matchRoute: '/crosswords$', clickRoute: '/crosswords'
        new TabModel displayLabel: 'Poker', matchRoute: '/poker/odds$', clickRoute: '/poker/odds'
        new TabModel displayLabel: 'Videos', matchRoute: '/videos$', clickRoute: '/videos'
      ]
      refreshPromise: updateHandle.promise
    )

  getNavBarModel: () =>
    retVal = _.deepCopy @nbDataModel
    Object.seal retVal
    return retVal

  changeNavBarModel: (changeDirector) =>
    updatedModel  = _.deepCopy @nbDataModel
    navBarBuilder = new NavBarBuilder(updatedModel)
    changeDirector(navBarBuilder)

    if navBarBuilder.hasUpdatedModel?
      lastDefer = @updateHandle

      @updateHandle = @$q.defer()
      @nbDataModel  = updatedModel
      @nbDataModel.nextPromise = @updateHandle.promise

      lastDefer.resolve(
        Object.seal(
          _.deepCopy updatedModel
        )
      )

