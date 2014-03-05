window.App = angular.module 'jch-portfolio', ['ngResource', 'ngRoute']

app = window.App

app.controller 'NavBarCtrl', ['$scope', '$location', ($scope, $location) ->
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

app.controller 'HomeCtrl', [
  '$scope',
  ($scope) ->
    $scope.message = 'Hello world!'
]

app.config ['$routeProvider', ($routeProvider) ->
  $routeProvider.when '/', 
    templateUrl: 'partials/portfolio/view.html',
    controller: 'HomeCtrl'
  $routeProvider.when '/crosswords', 
    templateUrl: 'partials/crosswords/view.html'
    controller: 'CrosswordsCtrl'
]
