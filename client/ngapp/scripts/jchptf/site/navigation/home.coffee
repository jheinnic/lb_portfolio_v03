  appModule = angular.module 'jchptf.site.navigation'
#     [
#       'ng'
#       'ngRoute'
#       'ngCookies'
#       'ui.bootstrap'
#       'mgcrea.ngStrap.aside'
#       'jch-navbar'
#       'xw-ticket'
#       'poker'
#     ]

  appModule.config [
    '$stateProvider'
    ($stateProvider) ->
      $stateProvider.state 'home',
        templateUrl: '/views/jchptf/site/navigation/home.view.html'
        controller: 'HomeCtrl'
        controllerAs: 'home'
        abstract: false
        authenticate: true
  ]

  appModule.controller 'HomeCtrl', [
    '$scope'
    ($scope) -> $scope.message = 'Hello world!'
  ]

  appModule.directive 'dynatest',
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
