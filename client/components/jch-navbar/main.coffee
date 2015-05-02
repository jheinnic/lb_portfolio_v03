define [
  'angular'
  'angular-strap/navbar'
], ->
  appModule =  angular.module 'jch-navbar', ['ng', 'mgcrea.ngStrap.navbar']

  appModule.directive 'jchNavBar', [
    'jchNbDataSvc',
    (jchNbDataSvc) ->
      restrict: 'E'
      replace: true
      scope: true
      templateUrl: '/app/jch-navbar/partials/jchNavbar.html'
      link: ($scope) ->
        updateModel = (data) ->
          refreshPromise = data.refreshPromise
          delete data.refreshPromise
          $scope.nbDataModel = data
          refreshPromise.then(updateModel)
        updateModel(jchNbDataSvc.getDataModel())
  ]

  appModule.factory 'jchNbDataSvc', [
    '$q',
    ($q) ->
      updateHandle = $q.defer()
      nbDataModel =
        brandName: 'John Heinnickel',
        tabModels: [
          new TabModel('Home', '/$', '/')
          new TabModel('Crosswords', '/crosswords$', '/crosswords')
          new TabModel('Poker', '/poker/odds$', '/poker/odds')
          new TabModel('Videos', '/videos$', '/videos')
        ]
        refreshPromise: updateHandle.promise

      return {
        getDataModel: () -> return angular.copy nbDataModel
      }
  ]

  class TabModel
    @::matchRoute = '^/$'
    @::clickRoute = '/'
    @::displayLabel = 'Index'

    constructor: (@displayLabel, @matchRoute, @clickRoute) -> return

    getMatchRoute: () ->
      if (angular.isString(@matchRoute) && @matchRoute > '')
        retVal = @matchRoute
      else
        retVal = @clickRoute + '$'
      return retVal

  return appModule
