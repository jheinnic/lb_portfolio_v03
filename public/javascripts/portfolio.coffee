window.App = angular.module 'jch-portfolio', ['ngResource', 'ngRoute', 'ngCookies', 'ui.bootstrap', 'mgcrea.ngStrap']

appModule = window.App

appModule.controller 'NavBarCtrl', ['$scope', '$location', ($scope, $location) ->
    $scope.section = 'home';
  
    paths =
      home : '/'
      crosswords : '/crosswords',
      poker : '/poker',
      video : '/video'
  
    $scope.changeSection = (newSection) ->
      $scope.section = newSection
      $location.path paths[newSection] 
]

appModule.controller 'HomeCtrl', [
  '$scope',
  ($scope) ->
    $scope.message = 'Hello world!'
]

appModule.config ['$routeProvider', ($routeProvider) ->
  $routeProvider.when '/', 
    templateUrl: 'public/partials/portfolio/view.html',
    controller: 'HomeCtrl'
  $routeProvider.when '/crosswords', 
    templateUrl: 'public/partials/crosswords/view.html'
    controller: 'CrosswordsCtrl'
]
appModule.directive 'navBarPlacement', [
  'navBarDataSvc',
  (jchNbSvc) ->
    restrict: 'E',
    templateUrl: 'public/partials/portfolio/navBar.html',
    link: (scope, element, attrs) ->
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
        {matchRoute: '/'
        clickRoute: '/'
        displayLabel: 'Home' }
        { clickRoute: '/crosswords'
        displayLabel: 'Crosswords' }
        { matchRoute: '/videos'
        clickRoute: '/nowhere'
        displayLabel: 'Videos' }
        { matchRoute: '/poker'
        clickRoute: '/.*'
        displayLabel: 'Poker' }
      ],
      refreshPromise: updateHandle.promise

    return { getDataModel: () -> return nbDataModel }
]
