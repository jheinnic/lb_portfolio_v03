'use strict'

module.exports = jchXyPosDirective = [
  '$parse', ($parse) ->
    retVal = { restrict: 'A'
      scope: false
      link: ($scope, $elem, $attr) ->
        attrRegEx = /^\s*(?:\(\s*([\S]+)\s*,\s*([\S]+)\s*\)\s+at\s+)?([\d]+)\s+by\s+([\d]+)\s*$/

        rowIdExpr = 'cellModel.rowId'
        cellHeight = null
        colIdExpr = 'cellModel.colId'
        cellWidth = null

        watchTopFn = null
        watchLeftFn = null
        watchWidthFn = null
        watchHeightFn = null

        handleLeftFn = ->
          watchLeftFn() if watchLeftFn?
          leftFn = $parse(colIdExpr + ' * ' + cellWidth + ' + "px"')
          if !leftFn.constant
            watchLeftFn = $scope.$watch(leftFn, (value) -> $elem.css 'left', value)
          else
            watchLeftFn = null
            $elem.css('left', leftFn($scope))

        handleWidthFn = ->
          watchWidthFn() if watchWidthFn?
          widthFn = $parse(cellWidth + ' + "px"')
          if !widthFn.constant
            watchWidthFn = $scope.$watch(widthFn, (value) -> $elem.css 'width', value)
          else
            watchWidthFn = null
            $elem.css('width', widthFn($scope))
          handleLeftFn()

        handleTopFn = ->
          watchTopFn() if watchTopFn?
          topFn = $parse(rowIdExpr + ' * ' + cellHeight + ' + "px"')
          if !topFn.constant
            watchTopFn = $scope.$watch(topFn, (value) -> $elem.css 'top', value)
          else
            watchTopFn = null
            $elem.css('top', topFn($scope))

        handleHeightFn = ->
          watchHeightFn() if watchHeightFn?
          heightFn = $parse(cellHeight + ' + "px"')
          if !heightFn.constant
            watchHeightFn = $scope.$watch(heightFn, (value) -> $elem.css 'height', value)
          else
            watchHeightFn = null
            $elem.css('height', heightFn($scope))
          handleTopFn()

        $attr.$observe(
          'jchXyPos', (expression) ->
          match = expression.match(attrRegEx)
          if match
            match[1] = 'cellModel.rowId' if angular.isUndefined(match[1])
            match[2] = 'cellModel.colId' if angular.isUndefined(match[2])

            if (colIdExpr isnt match[2])
              colIdExpr = match[2]
              cellWidth = match[3]
              handleLeftFn()
              handleWidthFn()
            else if (cellWidth isnt match[3])
              cellWidth = match[3]
              handleWidthFn()

            if (rowIdExpr isnt match[1])
              rowIdExpr = match[1]
              cellHeight = match[4]
              handleTopFn()
              handleHeightFn()
            else if (cellHeight isnt match[4])
              cellHeight = match[4]
              handleHeightFn()
        )
    }

    return retVal
]
