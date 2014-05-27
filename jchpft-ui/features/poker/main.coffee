define [
  'angular'
  'angular-strap/button'
  'cs!jch-ui-blocks/main'
], () ->
  xwModule = angular.module('poker', ['ng', 'mgcrea.ngStrap.button', 'jch-ui-blocks']);

  xwModule.config ['$routeProvider', ($routeProvider) ->
    $routeProvider.when '/poker/odds',
      templateUrl: '/app/poker/partials/view.html'
      controller: 'PokerCtrl'
  ]

  xwModule.directive 'pokerCanvas', [ () ->
    restrict: 'E'
    replace: true
    templateUrl: '/app/poker/partials/pokerCanvas.html'
    scope:
      tableParams: '@'
      predictedOdds: '&'
      playerInputs: '='
    controler: ($scope, $elm, $attr) ->
  ]

  xwModule.directive 'testGridOne', [() ->
    restrict: 'E'
    replace: true
    require: 'jhGrid'
    scope: false
    templateUrl: '/app/poker/partials/testGridOne.html'
    link: ( $scope, $element, $attr, gridCtrl ) ->
      changeContent = (clickScope, $event) ->
        if clickScope.cellModel.content == 'x'
          clickScope.controlModel.lifeStage = 'frozen'
        clickScope.cellModel.content = 'x'
        return

      gridCtrl.addDynamicImageLayer 'pkr-content', 'contentImage'
      gridCtrl.addClickHandler 'default', changeContent
      gridCtrl.addClickHandler 'frozen', null
      gridCtrl.populateGrid 2, 13, 28, 28, $scope.alphabetCells, '1D'
      return
  ]

#        this.beginEdit: (scope, inputOptions) ->
#          return
#
#        this.commitEdit: (withCommit) ->
#          return
#
#        this.rollback: (withCommit) ->
#          return
#
#        this.markDirty: (rollbackMemento) ->

  xwModule.controller 'PokerCtrl', ['$scope', ($scope) ->
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
    $scope.controlModel = {
      lifeStage: 'default'
    }
    $scope.alphabetCells = [
      {content: 'a'}
      {content: 'b'}
      {content: 'c'}
      {content: 'd'}
      {content: 'e'}
      {content: 'f'}
      {content: 'g'}
      {content: 'h'}
      {content: 'i'}
      {content: 'j'}
      {content: 'k'}
      {content: 'l'}
      {content: 'm'}
      {content: 'n'}
      {content: 'o'}
      {content: 'p'}
      {content: 'q'}
      {content: 'r'}
      {content: 's'}
      {content: 't'}
      {content: 'u'}
      {content: 'v'}
      {content: 'w'}
      {content: 'x'}
      {content: 'y'}
      {content: 'z'}
    ]

    $scope.contentImage = {
      a: '/app/xw-ticket/images/val/A.png'
      b: '/app/xw-ticket/images/val/B.png'
      c: '/app/xw-ticket/images/val/C.png'
      d: '/app/xw-ticket/images/val/D.png'
      e: '/app/xw-ticket/images/val/E.png'
      f: '/app/xw-ticket/images/val/F.png'
      g: '/app/xw-ticket/images/val/G.png'
      h: '/app/xw-ticket/images/val/H.png'
      i: '/app/xw-ticket/images/val/I.png'
      j: '/app/xw-ticket/images/val/J.png'
      k: '/app/xw-ticket/images/val/K.png'
      l: '/app/xw-ticket/images/val/L.png'
      m: '/app/xw-ticket/images/val/M.png'
      n: '/app/xw-ticket/images/val/N.png'
      o: '/app/xw-ticket/images/val/O.png'
      p: '/app/xw-ticket/images/val/P.png'
      w: '/app/xw-ticket/images/val/Q.png'
      r: '/app/xw-ticket/images/val/R.png'
      s: '/app/xw-ticket/images/val/S.png'
      t: '/app/xw-ticket/images/val/T.png'
      u: '/app/xw-ticket/images/val/U.png'
      v: '/app/xw-ticket/images/val/V.png'
      w: '/app/xw-ticket/images/val/W.png'
      x: '/app/xw-ticket/images/val/X.png'
      y: '/app/xw-ticket/images/val/Y.png'
      z: '/app/xw-ticket/images/val/Z.png'
    }
  ]

  xwModule.filter('contentImage', [() ->
    contentImageHash = {
      a: '/app/xw-ticket/images/val/A.png'
      b: '/app/xw-ticket/images/val/B.png'
      c: '/app/xw-ticket/images/val/C.png'
      d: '/app/xw-ticket/images/val/D.png'
      e: '/app/xw-ticket/images/val/E.png'
      f: '/app/xw-ticket/images/val/F.png'
      g: '/app/xw-ticket/images/val/G.png'
      h: '/app/xw-ticket/images/val/H.png'
      i: '/app/xw-ticket/images/val/I.png'
      j: '/app/xw-ticket/images/val/J.png'
      k: '/app/xw-ticket/images/val/K.png'
      l: '/app/xw-ticket/images/val/L.png'
      m: '/app/xw-ticket/images/val/M.png'
      n: '/app/xw-ticket/images/val/N.png'
      o: '/app/xw-ticket/images/val/O.png'
      p: '/app/xw-ticket/images/val/P.png'
      w: '/app/xw-ticket/images/val/Q.png'
      r: '/app/xw-ticket/images/val/R.png'
      s: '/app/xw-ticket/images/val/S.png'
      t: '/app/xw-ticket/images/val/T.png'
      u: '/app/xw-ticket/images/val/U.png'
      v: '/app/xw-ticket/images/val/V.png'
      w: '/app/xw-ticket/images/val/W.png'
      x: '/app/xw-ticket/images/val/X.png'
      y: '/app/xw-ticket/images/val/Y.png'
      z: '/app/xw-ticket/images/val/Z.png'
      _: '/app/xw-ticket/images/val/blank.png'
    }

    return (cellModel) ->
      contentKey = cellModel.content || '_'
      return contentImageHash[contentKey] || contentImageHash._
  ])
