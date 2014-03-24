define ['angular', 'angular-route', 'angular-cookies', 'ui-bootstrap-tpls', 'angular-strap/aside', 'angular-strap/button', 'angular-strap/navbar', 'cs!public/crosswords_angular'], ->
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

  appModule.directive 'navBar', [
    'jchNbDataSvc',
    (jchNbDataSvc) ->
      restrict: 'E',
      replace: true,
      templateUrl: 'public/partials/portfolio/navBar.html',
      link: (scope) ->
        updateModel = () ->
          scope.nbDataModel = jchNbDataSvc.getDataModel();
          scope.nbDataModel.refreshPromise.then( updateModel )
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

      return {
        getDataModel: () -> return nbDataModel
      }
  ]

  appModule.directive 'dynatest', {
    restrict: 'E'
    replace: true
    link: ($scope, $elem, $attr) ->
      $scope.children = [
        { type: 'bold', value: 'one' }
        { type: 'link', value: 'two', dest: '#' }
        { type: 'h1', value: 'three' }
        { type: 'bold', value: 'four' }
      ]
    template: ($elem, $attr) -> ''
  }

  return appModule