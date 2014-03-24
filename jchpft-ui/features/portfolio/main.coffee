define [
  'angular'
  'angular-route'
  'angular-cookies'
  'ui-bootstrap-tpls'
  'angular-strap/aside'
  'angular-strap/button'
  'angular-strap/navbar'
  'cs!xw-ticket'
], () ->
  appModule = angular.module(
    'jch-portfolio'
    ['ng', 'ngRoute', 'ngCookies', 'ui.router', 'ui.bootstrap', 'mgcrea.ngStrap.aside', 'mgcrea.ngStrap.navbar', 'xw-ticket']
  )

  appModule.config ['$routeProvider', ($routeProvider) ->
    $routeProvider.when '/',
      templateUrl: 'portfolio/partials/view.html',
      controller: 'HomeCtrl'
  ]

  appModule.controller 'HomeCtrl', [
    '$scope',
    ($scope) ->
      $scope.message = 'Hello world!'
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