
define ['angular', 'ui-utils'], ->
  xwModule = angular.module('jch-ui-blocks', ['ng', 'ui.keypress']);

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
          if (match)
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

  xwModule.directive 'jhCanvas', ['keypressHelper', '$parse', (keypressHelper, $parse) ->
    return {
      restrict: 'A'
      replace: false
      # require: '^jhCanvas'
#      template: ($tElm) ->
#        clone = $tElm.clone()
#        clone.removeAttr('jhCanvas')
#        clone.attr('ngKeyDown', 'jhCanvasKeyDown($event)')
#        return angular.element('<div></div>').append(clone).html()
      scope: false
      controller: ($scope, $elm, $attr) ->
        controlStates = {}
        defaultControlState =
          onEnter: angular.noop
          onExit: angular.noop
          onKeyDown: angular.noop
          scope: $scope
        currentControlState = defaultControlState

        this.addControlState = (controlModelKey, controlScope, keyBindings, onEnterFn, onExitFn) ->
          if angular.isDefined(controlStates[controlModelKey])
            throw 'IllegalArgument Exception: Key bindings are already registered for controlModelKey = ' + controlModelKey

          controlState = {
            onEnter: onEnterFn
            onExit: onExitFn
            onKeyDown: null
          }
          controlStates[controlModelKey] = controlState

          # Watch the enable/disable expr on the grid/cell scope
          # When enabled, connect eventCallbackFn to ngKeyPress/ngKeyDown/ngTextInput as appropriate, using the
          # prepared $keypressHelper methods as filters.
          if angular.isDefined(keyBindings)
             controlState.onKeyDown = keypressHelper(controlScope, 'keydown', keyBindings)
#            branchOnKeyFn = keypressHelper(controlScope, 'keydown', keyBindings)
#            controlState.onKeyDown = ($event) ->
#              $event.preventDefault()
#              branchOnKeyFn($event)
#              return

        $scope.watch $attr.jhCanvas, (newKeyValue) ->
          oldState = currentControlState
          newState = controlStates[newKeyValue] || defaultControlState

          if oldState != newState
            if oldState.onKeyDown?
              $elm.unbind 'keydown', oldState.onKeyDown
            oldState.onExit()
            currentControlState = newState
            newState.onEnter()
            if newState.onKeyDown?
              # TODO: What does jqLite do w.r.t. preventDefault() ?
              $elm.on 'keydown', newState.onKeyDown

          return

        return this
    }
  ]

  xwModule.directive 'jhGrid', [() ->
    sizeAttrRegEx = /^\s*([\d]+)\s*x\s*([\d]+)\s*$/
    gridCellOrgRegEx = /^\s*(?:(1D|2D)\s*,\s*(RowCol|ColRow))|(1D|2D)|(RowCol|ColRow)\s*$/

    return {
      restrict: 'A'
      require: 'jhGrid'
      replace: false
      scope: false
      controller: ['$compile', ($compile) ->
        imageLayers = []
        this.addImageLayer = (layerName, imageHashExpr, keyExpr, zIndex = 0) ->
          if !keyExpr? || !imageHashExpr?
            throw "IllegalArgumentException.  keyExpr and imageHashExpr arguments are mandatory.  keyExpr = " + keyExpr + ", imageHashExpr = " + imageHashExpr
          imageLayers.push
            layerName: layerName
            keyExpr: keyExpr
            imageHashExpr: imageHashExpr
            zIndex: zIndex
          return

        this.getCellTemplateFn = () ->
          pseudoElem = angular.element('<div></div>')
          rootElem = angular.element('<div class="xw-cell" style="position: absolute;"></div>')
          imageLayers.forEach (layer) ->
            if layer.layerName?
              rootElem.append(
                '<img class="' + layer.layerName + '" ng-show="' + layer.keyExpr + ' != \'blank\'" ng-src="{{' + layer.imageHashExpr + '[' + layer.keyExpr + ']}}" style="z-index=' + layer.zIndex + ';">'
              )
            else
              rootElem.append(
                '<img ng-show="' + layer.keyExpr + ' != \'blank\'" ng-src="{{' + layer.imageHashExpr + '[' + layer.keyExpr + ']}}" style="z-index=' + layer.zIndex + ';">'
              )
          pseudoElem.append(rootElem)
          return $compile(pseudoElem.html())

        cellArray = []
        this.setCellArray = (cellElementArray, numRows, numCols) ->
          cellArray = cellElementArray

        return @
      ]
      link: ($scope, $elm, $attr, gridCtrl) ->
        match = $attr.gridCellOrder.match(gridCellOrgRegEx)
        if match
          gridDimensions = match[1] || match[3] || '2D'
          dimensionOrder = match[2] || match[4] || 'RowMajor'

          if gridDimensions != '1D' && gridDimensions != '2D'
            throw 'IllegalArgumentException: gridDimensions must be 1D or 2D.  gridDimesnions = ' + gridDimensions
          if dimensionOrder != 'RowMajor' && dimensionOrder != 'ColMajor'
            throw 'IllegalArgumentException: dimensionOrder must be RowMajor or ColMajor.  dimesnionOrder = ' + dimensionOrder
        else
          gridDimensions = '2D'
          dimensionOrder = 'RowMajor'


        derivedAttr = 'none'
        match = $attr.gridSize.match(sizeAttrRegEx)
        if match
          gridWidth  = parseInt(match[1] || "-1")
          gridHeight = parseInt(match[2] || "-1")
        else
          derivedAttr = 'gridSize'

        match = $attr.cellSize.match(sizeAttrRegEx)
        if match
          cellWidth  = parseInt(match[1] || "-1")
          cellHeight = parseInt(match[2] || "-1")
        else if derivedAttr == 'none'
          derivedAttr = 'cellSize'
        else
          throw 'IllegalArgumentException cannot ommit both cellSize and ' + derivedAttr

        match = $attr.cellCount.match(sizeAttrRegEx)
        if match
          numRows    = parseInt(match[1] || "-1")
          numCols    = parseInt(match[2] || "-1")
        else if derivedAttr == 'none'
          derivedAttr = 'cellCount'
        else
          throw 'IllegalArgumentException cannot ommit both cellCount and ' + derivedAttr

        switch derivedAttr
          when 'gridSize'
            gridWidth  = cellWidth  * numCols
            gridHeight = cellHeight * numRows
          when 'cellSize'
            cellWidth  = gridWidth  / numCols
            cellHeight = gridHeight / numRows
          when 'cellCount'
            numCols    = gridWidth  / cellWidth
            numRows    = gridHeight / cellHeight
          else

        cellArray = $scope.$eval $attr.jhGrid
        if !cellArray?
          throw 'IllegalArgumentException: jhGrid attribute (jhGrid = "' + $attr.jhGrid + '") does not resolve.  cellArray = ' + cellArray
        if ! angular.isArray(cellArray)
          throw 'IllegalArgumentException: jhGrid attribute (jhGrid = "' + $attr.jhGrid + '") does not resolve to an array.  cellArray is ' + cellArray
        if gridDimensions = '1D'
          numCells = numRows * numCols
          if cellArray.length != numCells
            throw 'IllegalArgumentException: jhGrid must have length = ' + numCells + ' but jhGrid.length = ' + cellArray.length
        else if dimensionOrder = 'RowMajor'
          if cellArray.length != numRows
            throw 'IllegalArgumentException: jhGrid must have a first dimension length = ' + numRows + ' but jhGrid.length = ' + cellArray.length
          checkRow = (ii) ->
            if cellArray[ii].length != numCols
              throw 'IllegalArgumentException: jhGrid must have a second dimension length = ' + numCols + ' but jhGrid[' + ii + '].length = ' + cellArray[ii].length
          checkRow(ii) for ii in [0...numRows]
        else
          if cellArray.length != numCols
            throw 'IllegalArgumentException: jhGrid must have a first dimension length = ' + numCols + ' but jhGrid.length = ' + cellArray.length
          checkCol = (ii) ->
            if cellArray[ii].length != numRows
              throw 'IllegalArgumentException: jhGrid must have a second dimension length = ' + numRows + ' but jhGrid[' + ii + '].length = ' + cellArray[ii].length
          checkCol(ii) for ii in [0...numCols]

        cellTemplateFn = gridCtrl.getCellTemplateFn()
        cellHeightStr = cellHeight + 'px'
        cellWidthStr = cellWidth + 'px'
        linkCellFn = (ii,jj,cellModel) ->
          scope = $scope.$new()
          scope.cellModel = cellModel
          scope.rowId = ii
          scope.colId = jj
          retVal = null
          cellTemplateFn(scope, (cloneCellElm) ->
            $elm.append(cloneCellElm)
            cloneCellElm.on('$destroy', scope.$destroy())
            cloneCellElm.css( {
              top: (ii * cellHeight) + 'px'
              left: (jj * cellWidth) + 'px'
              height: cellHeightStr
              width: cellWidthStr
            })
            retVal = cloneCellElm
          )
          return retVal

        if gridDimensions == '2D'
          if dimensionOrder == 'RowMajor'
            gridCtrl.setCellArray (linkCellFn ii, jj, cellArray[ii][jj] for jj in [0...numCols] for ii in [0...numRows])
          else
            gridCtrl.setCellArray (linkCellFn ii, jj, cellArray[jj][ii] for jj in [0...numCols] for ii in [0...numRows])
        else
          if dimensionOrder == 'RowMajor'
            # gridCtrl.setCellArray (linkCellFn (kk-kk%numCols)/numRows, kk%numCols, cellArray[kk] for kk in [0...numRows*numCols])
            gridCtrl.setCellArray (linkCellFn ii, jj, cellArray[(ii*numRows)+jj] for jj in [0...numCols] for ii in [0...numRows])
          else
            # gridCtrl.setCellArray (linkCellFn kk%numRows, (kk-kk%numRows)/numCols, cellArray[kk] for kk in [0...numRows*numCols])
            gridCtrl.setCellArray (linkCellFn ii, jj, cellArray[ii+(jj*numCols)] for jj in [0...numCols] for ii in [0...numRows])

        return
    }
  ]

#  xwModule.directive 'jhGridPanel', ['jchAliasDirective', (jchAliasDirective) ->
#    return jchAliasDirective('jchImgMap', 'jchCellImg', 'E')
#  ]
#
#  xwModule.directive 'jhGridCell', [() ->
#    return {
#    transclude: element
#    require: 'jhGrid'
#    }
#  ]
#
#  xwModule.directive 'jhCellImg', ['jchAliasDirective', (jchAliasDirective) ->
#    return jchAliasDirective('jchImgMap', 'jchCellImg', 'E')
#  ]
#
#  # TODO: Verify how this behaves if map and/or key really are interpolated and dynamic!  Does the
#  #       template update, or, more likely, does it freeze with the value at the instant link() returns?
#  xwModule.directive 'jchImgMap', ['$interpolate', ($interpolate) ->
#    restrict: 'E'
#    replace: true,
#    scope: false,
#    template: (tElem, tAttr) ->
#      return '<img ng-src="' + tAttr.actualValue + '">'
#    link: ($scope, $elem, $attr) ->
#      actualMap  = $scope.$eval $attr.map
#      actualKey   = $scope.$eval $attr.key
#      if (angular.isDefined actualMap && angular.isDefined actualKey)
#        actualValue = actualMap[actualKey]
#        $attr.$set 'actualValue', actualValue
#
#      $attr.$observe 'map', (map) ->
#        actualMap  = $scope.$eval map
#        if (angular.isDefined actualMap && angular.isDefined actualKey)
#          actualValue = actualMap[actualKey]
#          $attr.$set 'actualValue', actualValue
#
#      $attr.$observe 'key', (key) ->
#        actualKey   = $scope.$eval(key)
#        if (angular.isDefined actualMap && angular.isDefined actualKey)
#          actualValue = actualMap[actualKey]
#          $attr.$set 'actualValue', actualValue
#  ]
#
#
#  xwModule.directive 'jhCanvas', [ () ->
#    # TODO!  The event model!
#  ]
#
#  xwModule.directive 'jhCanvasEvents', [() ->
#    match =
#  ]
#
#  xwModule.directive 'jhCanvasTyped', ['keypressHelper', (keypressHelper) ->
#  ]
#
#  xwModule.directive 'jhCanvasClicked', [() ->
#  ]


  return xwModule