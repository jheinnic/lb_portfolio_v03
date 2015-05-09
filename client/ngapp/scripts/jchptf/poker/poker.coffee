xwModule = angular.module 'poker', [
  'ng'
  'mgcrea.ngStrap.button'
  'jch-ui-blocks'
]

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
  templateUrl: 'view/jchpft/poker/testGridOne.html'
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

xwModule.controller(
  'PokerCtrl',
  [
    '$scope'
    ($scope) ->
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
      a: 'images/jchptf/crosswords/val/A.png'
      b: 'images/jchptf/crosswords/val/B.png'
      c: 'images/jchptf/crosswords/val/C.png'
      d: 'images/jchptf/crosswords/val/D.png'
      e: 'images/jchptf/crosswords/val/E.png'
      f: 'images/jchptf/crosswords/val/F.png'
      g: 'images/jchptf/crosswords/val/G.png'
      h: 'images/jchptf/crosswords/val/H.png'
      i: 'images/jchptf/crosswords/val/I.png'
      j: 'images/jchptf/crosswords/val/J.png'
      k: 'images/jchptf/crosswords/val/K.png'
      l: 'images/jchptf/crosswords/val/L.png'
      m: 'images/jchptf/crosswords/val/M.png'
      n: 'images/jchptf/crosswords/val/N.png'
      o: 'images/jchptf/crosswords/val/O.png'
      p: 'images/jchptf/crosswords/val/P.png'
      q: 'images/jchptf/crosswords/val/Q.png'
      r: 'images/jchptf/crosswords/val/R.png'
      s: 'images/jchptf/crosswords/val/S.png'
      t: 'images/jchptf/crosswords/val/T.png'
      u: 'images/jchptf/crosswords/val/U.png'
      v: 'images/jchptf/crosswords/val/V.png'
      w: 'images/jchptf/crosswords/val/W.png'
      x: 'images/jchptf/crosswords/val/X.png'
      y: 'images/jchptf/crosswords/val/Y.png'
      z: 'images/jchptf/crosswords/val/Z.png'
    }
  ]
)

xwModule.filter(
  'contentImage',
  [
    () ->
      contentImageHash = {
        a: 'images/jchptf/crosswords/val/A.png'
        b: 'images/jchptf/crosswords/val/B.png'
        c: 'images/jchptf/crosswords/val/C.png'
        d: 'images/jchptf/crosswords/val/D.png'
        e: 'images/jchptf/crosswords/val/E.png'
        f: 'images/jchptf/crosswords/val/F.png'
        g: 'images/jchptf/crosswords/val/G.png'
        h: 'images/jchptf/crosswords/val/H.png'
        i: 'images/jchptf/crosswords/val/I.png'
        j: 'images/jchptf/crosswords/val/J.png'
        k: 'images/jchptf/crosswords/val/K.png'
        l: 'images/jchptf/crosswords/val/L.png'
        m: 'images/jchptf/crosswords/val/M.png'
        n: 'images/jchptf/crosswords/val/N.png'
        o: 'images/jchptf/crosswords/val/O.png'
        p: 'images/jchptf/crosswords/val/P.png'
        q: 'images/jchptf/crosswords/val/Q.png'
        r: 'images/jchptf/crosswords/val/R.png'
        s: 'images/jchptf/crosswords/val/S.png'
        t: 'images/jchptf/crosswords/val/T.png'
        u: 'images/jchptf/crosswords/val/U.png'
        v: 'images/jchptf/crosswords/val/V.png'
        w: 'images/jchptf/crosswords/val/W.png'
        x: 'images/jchptf/crosswords/val/X.png'
        y: 'images/jchptf/crosswords/val/Y.png'
        z: 'images/jchptf/crosswords/val/Z.png'
        _: 'images/jchptf/crosswords/val/blank.png'
      }

      return (cellModel) ->
        contentKey = cellModel.content || '_'
        return contentImageHash[contentKey] || contentImageHash._
  ]
)
