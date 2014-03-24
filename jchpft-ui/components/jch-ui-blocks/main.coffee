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

        topFn  = $interpolate '{{' + rowIdExpr + ' * ' + cellHeight + '}}px'
        leftFn = $interpolate '{{' + colIdExpr + ' * ' + cellWidth + '}}px'

        $scope.$watch topFn, (value) -> $elem.css 'top', value
        $scope.$watch leftFn, (value) -> $elem.css 'left', value

        return $elem
    }
  ]

  return xwModule