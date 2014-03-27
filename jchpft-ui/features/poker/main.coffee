define [
  'angular'
  'angular-strap/button'
  'cs!jch-ui-blocks/main'
], () ->
  xwModule = angular.module('poker', ['ng', 'mgcrea.ngStrap.button', 'jch-ui-blocks']);

  xwModule.config ['$routeProvider', ($routeProvider) ->
    $routeProvider.when '/poker',
      templateUrl: '/app/poker/partials/view.html'
      controller: 'PokerCtrl'
  ]

  xwModule.directive 'xwTicket', [
    'xwModelFactory', 'valueImages', 'borderImages', 'fillImages', (xwModelFactory, valueImages, borderImages, fillImages) ->
      restrict: 'E'
      replace: true
      scope:
        ticketDocument: '='

  ]

  xwModule.controller 'CrosswordCtrl', ['$scope', ($scope) ->
    $scope.outerModel = [
      {
        name: 'First',
        value: 1
      },
      {
        name: 'Second',
        value: 2
      },
      {
        name: 'Third',
        value: 3
      }
    ]
  ]
