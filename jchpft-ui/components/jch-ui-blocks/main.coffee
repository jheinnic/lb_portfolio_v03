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

        this.addControlState = (stateKey, controlScope, keyBindings, onEnterFn, onExitFn) ->
          if angular.isDefined(controlStates[stateKey])
            throw 'IllegalArgument Exception: Key bindings are already registered for stateKey = ' + stateKey

          controlState = {
            onEnter: onEnterFn
            onExit: onExitFn
            onKeyDown: null
          }
          controlStates[stateKey] = controlState

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

        $scope.$watch $attr.jhCanvas, (newStateKey) ->
          oldState = currentControlState
          newState = controlStates[newStateKey] || defaultControlState

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
      controller: ['$scope', '$element', '$attrs', '$compile', ($scope, $element, $attrs, $compile) ->
        zIndex = 1
        rootElem   = angular.element '<div style="position: absolute;"></div>'
        pseudoElem = angular.element '<div></div>'
        pseudoElem.append rootElem

        this.addDynamicImageLayer = (layerName, cellImageFilter) ->
          if !layerName?
            throw "IllegalArgumentException: layerName argument is mandatory.  layerName = " + layerName
          if !cellImageFilter?
            throw "IllegalArgumentException: cellImageFilter argument is mandatory.  cellImageFilter = " + cellImageFilter
          rootElem.append(
            '<img class="' + layerName + '" ng-src="{{cellModel | ' + cellImageFilter + '}}" style="z-index=' + zIndex++ + ';">'
          )

        this.addFixedImageLayer = (layerName, cellImageSource) ->
          if !layerName?
            throw "IllegalArgumentException: layerName argument is mandatory.  layerName = " + layerName
          if !cellImageSource?
            throw "IllegalArgumentException: cellImageSource argument is mandatory.  cellImageSource = " + cellImageSource
          rootElem.append(
            '<img class="' + layerName + '" src="' + cellImageSource + '" style="z-index=' + zIndex++ + ';">'
          )


        clickHandlerStates = {}
        currentClickHandler = null

        this.addClickHandler = (stateKey, clickHandlerFn) ->
          if angular.isDefined clickHandlerStates[stateKey]
            throw "IllegalArgumentException: stateKey has already been defined.  stateKey = " + stateKey
          if !angular.isFunction(clickHandlerFn) && clickHandlerFn != null
            throw "IllegalArgumentException: clickHandlerFn must either be null or a function.  clickHandlerFn = " + clickHandlerFn

          if angular.isFunction clickHandlerFn
            clickHandlerStates[stateKey] = ($event) ->
              cellScope = angular.element(this).scope()
              cellScope.$apply( () -> clickHandlerFn(cellScope, $event) )
          else
            clickHandlerStates[stateKey] = null

        cellArray = []
        gridRows = -1
        gridCols = -1

        this.populateGrid = (numRows, numCols, cellWidth, cellHeight, cellObjects, srcDimensions = '2D', srcCellOrder = 'RowMajor') ->
          if srcDimensions != '2D' && srcDimensions != '1D'
            throw 'IllegalArgumentException: srcDimensions must be "2D" or "1D".  srcDimensions = ' + srcDimensions
          if srcCellOrder != 'RowMajor' && srcCellOrder != 'ColMajor'
            throw 'IllegalArgumentException: srcCellOrder must be "RowMajor" or "ColMajor".  srcCellOrder = ' + srcCellOrder
          if numRows < 1
            throw 'IllegalArgumentException: numRows must be a positive value.  numRows = ' + numRows
          if numCols < 1
            throw 'IllegalArgumentException: numCols must be a positive value.  numCols = ' + numRows
          if cellWidth < 1
            throw 'IllegalArgumentException: cellWidth must be a positive value.  cellWidth = ' + cellWidth
          if cellHeight < 1
            throw 'IllegalArgumentException: cellHeight must be a positive value.  cellHeight = ' + cellHeight

          numCells = numRows * numCols
          gridRows = numRows
          gridCols = numCols

          if !cellObjects? || !angular.isArray(cellObjects)
            throw 'IllegalArgumentException: cellObjects argument must be an array.  cellObjects == ' + cellObjects
          if srcDimensions = '1D'
            if cellObjects.length != numCells
              throw 'IllegalArgumentException: cellObjects must have length = ' + numCells + ' but cellObjects.length = ' + cellObjects.length
          else if srcCellOrder = 'RowMajor'
            if cellObjects.length != numRows
              throw 'IllegalArgumentException: cellObjects must have a first dimension length = ' + numRows + ' but cellObjects.length = ' + cellObjects.length
            checkRow = (ii) ->
              if cellObjects[ii].length != numCols
                throw 'IllegalArgumentException: cellObjects must have a second dimension length = ' + numCols + ' but cellObjects[' + ii + '].length = ' + cellObjects[ii].length
            checkRow(ii) for ii in [0...numRows]
          else
            if cellObjects.length != numCols
              throw 'IllegalArgumentException: cellObjects must have a first dimension length = ' + numCols + ' but cellObjects.length = ' + cellObjects.length
            checkCol = (ii) ->
              if cellObjects[ii].length != numRows
                throw 'IllegalArgumentException: cellObjects must have a second dimension length = ' + numRows + ' but cellObjects[' + ii + '].length = ' + cellObjects[ii].length
            checkCol(ii) for ii in [0...numCols]

          cellTemplateFn = $compile pseudoElem.html()
          cellHeightStr = cellHeight + 'px'
          cellWidthStr = cellWidth + 'px'
          linkCellFn = (ii,jj,cellModel) ->
            scope = $scope.$new(false)
            scope.cellModel = cellModel
            scope.rowId = ii
            scope.colId = jj
            retVal = null
            cellTemplateFn(scope, (cloneCellElm) ->
              $element.append(cloneCellElm)
              cloneCellElm.on('$destroy', () -> scope.$destroy())
              cloneCellElm.css( {
                top: (ii * cellHeight) + 'px'
                left: (jj * cellWidth) + 'px'
                height: cellHeightStr
                width: cellWidthStr
              })
              retVal = cloneCellElm
            )
            return retVal

          if srcDimensions == '2D'
            if srcCellOrder == 'RowMajor'
              cellArray = (linkCellFn ii, jj, cellObjects[ii][jj] for jj in [0...numCols] for ii in [0...numRows])
            else
              cellArray = (linkCellFn ii, jj, cellObjects[jj][ii] for jj in [0...numCols] for ii in [0...numRows])
          else
            if srcCellOrder == 'RowMajor'
              # cellArray = (linkCellFn (kk-kk%numCols)/numRows, kk%numCols, cellObjects[kk] for kk in [0...numRows*numCols])
              cellArray = (linkCellFn ii, jj, cellObjects[(ii*numCols)+jj] for jj in [0...numCols] for ii in [0...numRows])
            else
              # cellArray = (linkCellFn kk%numRows, (kk-kk%numRows)/numCols, cellObjects[kk] for kk in [0...numRows*numCols])
              cellArray = (linkCellFn ii, jj, cellObjects[ii+(jj*numRows)] for jj in [0...numCols] for ii in [0...numRows])

        $scope.$watch $attrs.jhGrid, (newKeyValue) ->
          oldState = currentClickHandler
          newState = clickHandlerStates[newKeyValue] || null

          if oldState != newState
            if oldState != null && newState == null
              cellArray[ii][jj].unbind('click', oldState) && console.log('Ok1') for jj in [0...gridCols] for ii in [0...gridRows]
            else if oldState == null && newState != null
              cellArray[ii][jj].on('click', newState) && console.log('Ok2') for jj in [0...gridCols] for ii in [0...gridRows]
            else
              cellArray[ii][jj].unbind('click', oldState) && cellArray[ii][jj].on('click', newState) && console.log('Ok3') for jj in [0...gridCols] for ii in [0...gridRows]

            currentClickHandler = newState

          return

        return @
      ]
      link: ($scope, $element) ->
        $element.css('position', 'relative')
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