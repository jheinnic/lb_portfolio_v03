window.App = angular.module 'jch-portfolio', ['ngResource', 'ngRoute', 'ngCookies', 'ui.bootstrap', 'mgcrea.ngStrap.helpers.dimensions', 'mgcrea.ngStrap']

appModule = window.App

appModule.config ['$routeProvider', ($routeProvider) ->
  $routeProvider.when '/',
    templateUrl: 'public/partials/portfolio/view.html',
    controller: 'HomeCtrl'
  $routeProvider.when '/crosswords',
    templateUrl: 'public/partials/crosswords/view.html'
    controller: 'CrosswordsCtrl'
]

appModule.constant(
  'xwIndices',
  wordGridIndices: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
  bonusWordIndices: [0, 1, 2, 3, 4]
  letterRowIndices: [0, 1, 2],
  letterColIndices: [0, 1, 2, 3, 4, 5]
)

appModule.controller 'HomeCtrl', [
  '$scope',
  ($scope) ->
    $scope.message = 'Hello world!'
]

appModule.controller 'CrosswordsCtrl', [
  '$scope', 'xwIndices'
  ($scope, xwIndices) ->
    $scope.message = 'Hello world!'
    $scope.tm =
      rpts: [
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
      ]
    $scope.xwFixed = xwIndices
]

appModule.directive 'navBar', [
  'navBarDataSvc',
  (jchNbSvc) ->
    restrict: 'E',
    replace: true,
    templateUrl: 'public/partials/portfolio/navBar.html',
    link: (scope) ->
      updateModel = () ->
        scope.nbDataModel = jchNbSvc.getDataModel();
        scope.nbDataModel.refreshPromise.then( updateModel )
      updateModel()
]

appModule.factory 'navBarDataSvc', [
  '$q',
  ($q) ->
    updateHandle = $q.defer()
    nbDataModel =
      brandName: 'John Heinnickel',
      tabModels: [
        { matchRoute: '/$'
        clickRoute: '/'
        displayLabel: 'Home' }
        { matchRoute: '/crosswords'
        clickRoute: '/crosswords'
        displayLabel: 'Crosswords' }
        { matchRoute: '/videos'
        clickRoute: '/videos'
        displayLabel: 'Videos' }
        { matchRoute: '/pokerodds'
        clickRoute: '/pokerodds'
        displayLabel: 'Poker' }
      ]
      refreshPromise: updateHandle.promise

    return { getDataModel: () -> return nbDataModel }
]

appModule.directive 'dynatest', {
  restrict: 'E'
  replace: true
  link: (scope, elem, attr) ->
    scope.children = [
      { type: 'bold', value: 'one' }
      { type: 'link', value: 'two', dest: '#' }
      { type: 'h1', value: 'three' }
      { type: 'bold', value: 'four' }
    ]
  template: (elem, attr) ->

}