define ['angular'], ->
  xwModule = angular.module('jch-ui-blocks', ['ng']);

  xwModule.directive 'jchXyPos', ['$parse', ($parse) ->
    attrRegEx = /^\s*(?:\(\s*([\S]+)\s*,\s*([\S]+)\s*\)\s+at\s+)?([\d]+)\s+by\s+([\d]+)\s*$/

    return {
    restrict: 'A'
    replace: false
    scope: false
    link: ($scope, $elem, $attr) ->
      rowIdExpr =     'cellModel.rowId'
      cellHeight =    null
      colIdExpr =     'cellModel.colId'
      cellWidth =     null

      watchTopFn =    null
      watchLeftFn =   null
      watchWidthFn =  null
      watchHeightFn = null

      handleLeftFn = () ->
        if watchLeftFn? then watchLeftFn()
        leftFn = $parse colIdExpr + ' * ' + cellWidth + ' + "px"'
        if( !leftFn.constant )
          watchLeftFn = $scope.$watch leftFn, (value) -> $elem.css 'left', value
        else
          watchLeftFn = null
          $elem.css 'left', leftFn($scope)
        return

      handleWidthFn = () ->
        if watchWidthFn? then watchWidthFn()
        widthFn = $parse cellWidth + ' + "px"'
        if( !widthFn.constant )
          watchWidthFn = $scope.$watch widthFn, (value) -> $elem.css 'width', value
        else
          watchWidthFn = null
          $elem.css 'width', widthFn($scope)
        handleLeftFn()
        return

      handleTopFn = () ->
        if watchTopFn? then watchTopFn()
        topFn = $parse rowIdExpr + ' * ' + cellHeight + ' + "px"'
        if( !topFn.constant )
          watchTopFn = $scope.$watch topFn, (value) -> $elem.css 'top', value
        else
          watchTopFn = null
          $elem.css 'top', topFn($scope)
        return

      handleHeightFn = () ->
        if watchHeightFn? then watchHeightFn()
        heightFn = $parse cellHeight + ' + "px"'
        if( !heightFn.constant )
          watchHeightFn = $scope.$watch heightFn, (value) -> $elem.css 'height', value
        else
          watchHeightFn = null
          $elem.css 'height', heightFn($scope)
        handleTopFn()
        return

      $attr.$observe 'jchXyPos', (expression) ->
        match = expression.match attrRegEx
        if (!match)
          initState()
        else
          if angular.isUndefined(match[1]) then match[1] = 'cellModel.rowId'
          if angular.isUndefined(match[2]) then match[2] = 'cellModel.colId'

          if cellWidth != match[3]
            colIdExpr  = match[2]
            cellWidth  = match[3]
            handleWidthFn()
          else if colIdExpr != match[2]
            colIdExpr  = match[2]
            handleLeftFn()

          if cellHeight != match[4]
            cellHeight = match[4]
            rowIdExpr  = match[1]
            handleHeightFn()
          else if rowIdExpr != match[1]
            rowIdExpr  = match[1]
            handleTopFn()
        return
      return
    }
  ]

  # TODO: Verify how this behaves if map and/or key really are interpolated and dynamic!  Does the
  #       template update, or, more likely, does it freeze with the value at the instant link() returns?
  xwModule.directive 'jchImgMap', ['$interpolate', ($interpolate) ->
    restrict: 'E'
    replace: true,
    scope: false,
    template: (tElem, tAttr) ->
      return '<img ng-src="' + tAttr.actualValue + '">'
    link: ($scope, $elem, $attr) ->
      actualMap  = $scope.$eval $attr.map
      actualKey   = $scope.$eval $attr.key
      if (angular.isDefined actualMap && angular.isDefined actualKey)
        actualValue = actualMap[actualKey]
        $attr.$set 'actualValue', actualValue

      $attr.$observe 'map', (map) ->
        actualMap  = $scope.$eval map
        if (angular.isDefined actualMap && angular.isDefined actualKey)
          actualValue = actualMap[actualKey]
          $attr.$set 'actualValue', actualValue

      $attr.$observe 'key', (key) ->
        actualKey   = $scope.$eval(key)
        if (angular.isDefined actualMap && angular.isDefined actualKey)
          actualValue = actualMap[actualKey]
          $attr.$set 'actualValue', actualValue
  ]

  return xwModule