define [
  'angular'
  'angular-route'
  'angular-cookies'
  'ui-bootstrap-tpls'
  'ui-bootstrap'
  'angular-strap/aside'
  'angular-strap/navbar'
  'xw-ticket/main'
], () ->
  appModule = angular.module(
    'jch-portfolio'
    ['ng', 'ngRoute', 'ngCookies', 'ui.bootstrap', 'mgcrea.ngStrap.aside', 'mgcrea.ngStrap.navbar', 'xw-ticket']
  )

  appModule.config ['$routeProvider', ($routeProvider) ->
    $routeProvider.when '/',
      templateUrl: '/app/portfolio/partials/view.html',
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