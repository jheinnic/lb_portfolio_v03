define ['angular'], ->
  xwModule = angular.module('jch-ui-blocks', ['ng']);

  xwModule.directive 'jchXyPos', ['$interpolate', ($interpolate) ->
    attrRegEx = /^\s*(?:\(\s*([\S]+)\s*,\s*([\S]+)\s*\)\s+at\s+)?([\d]+)\s+by\s+([\d]+)\s*$/

    return {
      restrict: 'A'
      replace: false
      scope: false
      link: ($scope, $elem, $attr) ->
        expression = $attr.jchXyPos
        match = expression.match attrRegEx

        if (!match)
          throw "Invalid argument: " + expression + " is not formatted correctly"

        rowIdExpr  = match[1] || 'cellModel.rowId'
        colIdExpr  = match[2] || 'cellModel.colId'
        cellWidth  = match[3]
        cellHeight = match[4]

        topFn    = $interpolate '{{' + rowIdExpr + ' * ' + cellHeight + '}}px'
        leftFn   = $interpolate '{{' + colIdExpr + ' * ' + cellWidth + '}}px'
        heightFn = $interpolate '{{' + cellHeight + '}}px'
        widthFn  = $interpolate '{{' + cellWidth + '}}px'

        $scope.$watch topFn,    (value) -> $elem.css 'top', value
        $scope.$watch leftFn,   (value) -> $elem.css 'left', value
        $scope.$watch heightFn, (value) -> $elem.css 'height', value
        $scope.$watch widthFn,  (value) -> $elem.css 'width', value

        return $elem
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