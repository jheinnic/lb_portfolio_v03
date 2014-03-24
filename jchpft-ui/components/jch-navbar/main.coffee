define ['angular', 'ui-bootstrap-tpls', 'angular-strap/aside', 'angular-strap/button', 'angular-strap/navbar', 'cs!xw-ticket'], ->
  appModule =  angular.module 'jch-portfolio', ['ng', 'ngRoute', 'ngCookies', 'ui.router', 'ui.bootstrap', 'mgcrea.ngStrap.aside', 'mgcrea.ngStrap.navbar', 'crosswords']

  appModule.config ['$routeProvider', ($routeProvider) ->
    $routeProvider.when '/',
      templateUrl: 'public/partials/portfolio/view.html',
      controller: 'HomeCtrl'
    $routeProvider.when '/crosswords',
      templateUrl: 'public/partials/crosswords/view.html'
      controller: 'CrosswordCtrl'
  ]

  appModule.controller 'HomeCtrl', [
    '$scope',
    ($scope) ->
      $scope.message = 'Hello world!'
  ]

  appModule.directive 'jchNavBar', [
    'jchNbDataSvc',
    (jchNbDataSvc) ->
      restrict: 'E'
      replace: true
      scope: true
      templateUrl: 'jch-navbar/partials/jchNavBar.html'
      link: ($scope) ->
        updateModel = () ->
          $scope.nbDataModel = jchNbDataSvc.getDataModel()
          $scope.nbDataModel.refreshPromise.then(updateModel)
        updateModel()
  ]

  appModule.factory 'jchNbDataSvc', [
    '$q',
    ($q) ->
      updateHandle = $q.defer()
      nbDataModel =
        brandName: 'John Heinnickel',
        tabModels: [
          { matchRoute: '/$'
            clickRoute: '/'
            displayLabel: 'Home' }
          { matchRoute: '/crosswords$'
            clickRoute: '/crosswords'
            displayLabel: 'Crosswords' }
          { matchRoute: '/videos$'
            clickRoute: '/videos'
            displayLabel: 'Videos' }
          { matchRoute: '/pokerodds$'
            clickRoute: '/pokerodds'
            displayLabel: 'Poker' }
        ]
        refreshPromise: updateHandle.promise

      getMatchRoute = () ->
        return (angular.isString(this.matchRoute) && this.matchRoute > '') ? this.matchRoute : this.clickRoute
      nbDataModel.tabModels.forEach (tabInst) -> tabInst.getMatchRoute = angular.bind(tabInst, getMatchRoute)

      return {
        getDataModel: () -> return nbDataModel
      }
  ]

