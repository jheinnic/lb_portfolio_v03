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
      # scope: {
      #   controlModel: '=jhCanvas'
      # }
      controller: ['$scope', '$element', '$attrs', '$parse', ($scope, $element, $attrs, $parse) ->
        defaultKeyboardControlState =
          stateName: 'default'
          onEnter: angular.noop
          onExit: angular.noop
          eventHandler: null
        currentKeyboardControlState = defaultKeyboardControlState

        eventControlStates = {
          keyboard: {
            default: defaultKeyboardControlState
          }
          click: { }
        }
        eventControlers = {
          keyboard: this
        }

        this.parseElementControlState = (controlElement, controlStateDef) ->
          thisKeyboardControlState =
            eventName: controlElement.id
            onEnter: $parse(controlStateDef.onEnter)
            onExit: $parse(controlStateDef.onExit)
            eventHandler: null

          thisElementScope = controlElement.scope()

          # See discussion under updateControlState() regarding the following $watchCollection().
          thisKeyBindingWatchRemove = thisElementScope.$watchCollection controlStateDef, (newKeyBindings, oldKeyBindings) ->
            newKeyDownFn = keypressHelper thisElementScope, 'keydown', newKeyBindings
            if currentKeyboardControlState.eventName == thisKeyboardControlState.eventName
              controlElement.unbind 'keydown', thisKeyboardControlState.eventHandler
              controlElement.on 'keydown', newKeyDownFn
            thisKeyboardControlState.eventHandler = newKeyDownFn
            # TODO: Handle enterExpr/exitExpr changes too

          # Remove the watch handler we've created from the argument scope's watch list when it gets destroyed.
          thisElementScope.$on '$destroy', thisKeyBindingWatchRemove

          return thisKeyboardControlState

        # This won't get a value unless and until the controlStateDef argument to parseElementControlState comes from
        # an expression whose value may change over time.  As of now, its expected that a controller wiring its element
        # for keyboard control is constructing an object directly, and mutating that object if necessary rather than
        # providing an expression whose return value changes to express a change in control dynamics (e.g. an alternate
        # keyboard binding or a relinquishment of the keyboard).
        this.updateElementControlState = null

        this.addKeyboardControlElement = (element, controlStateDef) ->
          elementId = element.id
          if angular.isDefined(eventControlStates[elementId])
            throw 'IllegalArgument Exception: Key binding, onEnter, and onExit expressions are already registered for elementId = ' + elementId

          thisCanvasStateData = eventControlers.keyboard.parseControlState(element, controlStateDef)
          eventControlStates.keyboard[elementId] = thisCanvasStateData

          # Watch the enable/disable expr on the grid/cell scope.
          # When enabled, connect eventCallbackFn to ngKeyPress/ngKeyDown/ngTextInput as appropriate, using the
          # prepared $keypressHelper methods as filters.
          # if angular.isObject keyBindings
          #   thisCanvasStateData.eventHandler = keypressHelper(thisElementScope, 'keydown', keyBindings)
          # else if angular.isString keyBindings
            # If key binding is given as an expression itself, parse and watch it.

        keyActiveWatch = $scope.$watch 'controlModel.activeCanvasElement', (newElementId) ->
          oldStateData = currentKeyboardControlState
          newStateData = eventControlStates[newElementId] || defaultKeyboardControlState

          if newStateData == defaultKeyboardControlState
            console.warning 'Active canvas element set to unregistered ID of ' + newElementId + '.  Reverting to initial state.'

          if oldStateData != newStateData
            if oldStateData.onKeyDown?
              $element.unbind 'keydown', oldStateData.onKeyDown
            oldStateData.onExit()
            currentKeyboardControlState = newStateData
            newStateData.onEnter()
            if newStateData.onKeyDown?
              # TODO: What does jqLite do w.r.t. preventDefault() ?
              $element.on 'keydown', newStateData.onKeyDown
          return

        this.activateKeyboardControl = (element) ->
          # The watch handler will cause the change of control to take effect.
          # Done this way intentionally to permit external control of canvas artifacts via the two-way data binding
          # on controlModel.
          $scope.controlModel.activeCanvasElement = element.id
          return

        this.watchEventControl = (element, eventType, handlerStates, eventCtrl) ->

        $element.on '$destroy', () ->
          if currentKeyboardControlState.onKeyDown?
            $element.unbind 'keydown', currentKeyboardControlState.onKeyDown
          return
        $scope.$on '$destroy', keyActiveWatch

        return this
      ]
    }
  ]

  sizeAttrRegEx = /^\s*([\d]+)\s*x\s*([\d]+)\s*$/
  gridCellOrgRegEx = /^\s*(?:(1D|2D)\s*,\s*(RowCol|ColRow))|(1D|2D)|(RowCol|ColRow)\s*$/

  xwModule.directive 'jhGrid', ['parse', ($parse) ->
    domElementGrid = null
    clickHandlerStates = {}
    currentClickHandler = null

    numRows = -1
    numCols = -1
    canvasCtrl = null

    return {
      restrict: 'E'
      require: [ 'jhGrid', 'ngModel' ]
      replace: false
      scope: {
        sourceGridModel: '=gridModel'
        clickStates: '@clickStates'
        numRows: '@numRows'
        numCols: '@numCols'
        cellWidth: '@cellWidth'
        cellHeight: '@cellHeight'
        srcDimCount: '@srcDimCount'
        srcDimOrder: '@srcDimOrder'
      }
      transclude: 'element'
      controller: ['$scope', '$element', '$parse', ($scope, $element, $parse) ->
        retVal = {}

        retVal.parseControlStates = (controlStatesDef) ->
          eventHandlerStates = {}
          Object.keys(controlStatesDef).forEach (stateName) ->
            controlState =
              stateName: stateName
              onEnter: $parse controlStatesDef[stateName].onEnter || null
              onExit: $parse controlStatesDef[stateName].onExit || null

            if controlStatesDef[stateName].eventHandler?
              parsedEventHandler = $parse controlStatesDef[stateName].eventHandler
              controlState.eventHandler = ($event) ->
                $scope.$apply () -> parsedEventHandler($scope, {'$event': $event})
            else
              controlState.eventHandler = null

            eventHandlerStates[stateName] = controlState
          return eventHandlerStates

        retVal.handleStateChange = (newState, oldState) ->
          if oldState != newState
            if oldState != null && newState == null
              if oldState.onExit?
                $scope.$apply oldState.onExit
              domElementGrid[ii][jj].unbind('click', oldState) for jj in [0...numCols] for ii in [0...numRows]
            else if oldState == null && newState != null
              domElementGrid[ii][jj].on('click', newState) for jj in [0...numCols] for ii in [0...numRows]
              if newState.onEnter?
                $scope.$apply newState.onEnter
            else
              if oldState.onExit?
                $scope.$apply oldState.onExit
              domElementGrid[ii][jj].unbind('click', oldState) && domElementGrid[ii][jj].on('click', newState) for jj in [0...numCols] for ii in [0...numRows]
              if newState.onEnter?
                $scope.$apply newState.onEnter

        retVal.getScopeByCoordinates = (rowId, colId) ->
          if domElementGrid == null
            throw 'IllegalStateException: cannot retrieve cells by coordinates until after grid has been populated'
          if rowId < 0 || rowId >= numRows
            throw 'IllegalArgumentException: rowId must be a value between 0 and ' + (numRows-1) + '.  rowId = ' + rowId
          if colId < 0 || colId >= numCols
            throw 'IllegalArgumentException: rowId must be a value between 0 and ' + (numCols-1) + '.  colId = ' + colId

          retElement = domElementGrid[rowId][colId]
          return retElement.scope()

        retVal.setClickState = (nextStateName, rowId = -1. colId = -1) ->
          canvasCtrl.setElementControlState($element, 'click', nextStateName)

        retVal.moveCursorTo = (rowId, colId) ->
          console.log('tbd')

        return retVal
      ]

      link: ($scope, $element, $attrs, ctrls, $transcludeFn) ->
        if $scope.srcDimCount != '2D' && $scope.srcDimCount != '1D' && angular.isDefined $scope.srcDimCount
          throw 'IllegalArgumentException: srcDims must be "2D" or "1D".  srcDimCount = ' + $scope.srcDimCount
        if $scope.srcDimOrder != 'RowMajor' && $scope.srcDimOrder != 'ColMajor' && angular.isDefined $scope.srcDimOrder
          throw 'IllegalArgumentException: srcDimOrder must be "RowMajor" or "ColMajor".  srcDimOrder = ' + srcDimOrder
        if $scope.numRows < 1
          throw 'IllegalArgumentException: numRows must be a positive value.  numRows = ' + $scope.numRows
        if $scope.numCols < 1
          throw 'IllegalArgumentException: numCols must be a positive value.  numCols = ' + $scope.numRows
        if $scope.cellWidth < 1
          throw 'IllegalArgumentException: cellWidth must be a positive value.  cellWidth = ' + $scope.cellWidth
        if $scope.cellHeight < 1
          throw 'IllegalArgumentException: cellHeight must be a positive value.  cellHeight = ' + $scope.cellHeight

        sourceGridModel = $scope.sourceGridModel
        numCells = $scope.numRows * $scope.numCols
        srcDimCount = $scope.srcDimCount
        srcDimOrder = $scope.srcDimOrder
        cellHeight = $scope.cellHeight
        cellWidth = $scope.cellWidth
        numRows = $scope.numRows
        numCols = $scope.numCols

        if !sourceGridModel || !angular.isArray sourceGridModel
          throw 'IllegalArgumentException: sourceGridModel argument must be an array.  sourceGridModel == ' + sourceGridModel
        if srcDims = '1D'
          if sourceGridModel.length != numCells
            throw 'IllegalArgumentException: sourceGridModel must have length = ' + numCells + ' but sourceGridModel.length = ' + sourceGridModel.length
        else if srcDimOrder = 'RowMajor'
          if sourceGridModel.length != numRows
            throw 'IllegalArgumentException: sourceGridModel must have a first dimension length = ' + numRows + ' but sourceGridModel.length = ' + sourceGridModel.length
          checkRow = (ii) ->
            if sourceGridModel[ii].length != numCols
              throw 'IllegalArgumentException: sourceGridModel must have a second dimension length = ' + numCols + ' but sourceGridModel[' + ii + '].length = ' + sourceGridModel[ii].length
          checkRow(ii) for ii in [0...numRows]
        else
          if sourceGridModel.length != numCols
            throw 'IllegalArgumentException: sourceGridModel must have a first dimension length = ' + numCols + ' but sourceGridModel.length = ' + sourceGridModel.length
          checkCol = (ii) ->
            if sourceGridModel[ii].length != numRows
              throw 'IllegalArgumentException: sourceGridModel must have a second dimension length = ' + numRows + ' but sourceGridModel[' + ii + '].length = ' + sourceGridModel[ii].length
          checkCol(ii) for ii in [0...numCols]

        # cellTemplateFn = $compile pseudoElem.html()
        cellHeightStr = cellHeight + 'px'
        cellWidthStr = cellWidth + 'px'
        linkCellFn = (ii,jj,cellModel) ->
          scope = $scope.$new false
          scope.cellModel = cellModel
          scope.rowId = ii
          scope.colId = jj
          return $transcludeFn scope, (cloneCellElm) ->
            $element.append cloneCellElm

            cloneCellElm.on '$destroy', () ->
              if currentClickHandler != null
                domElementGrid[ii][jj].unbind('click', currentClickHandler) for jj in [0...numCols] for ii in [0...numRows]
              # Does DOM destruction ever happen without associated scope destruction?
              # scope.$destroy()
              return

            cloneCellElm.css {
              top: (ii * cellHeight) + 'px'
              left: (jj * cellWidth) + 'px'
              height: cellHeightStr
              width: cellWidthStr
            }

            return cloneCellElm

        if srcDims == '2D'
          if srcDimOrder == 'RowMajor'
            domElementGrid = (linkCellFn ii, jj, sourceGridModel[ii][jj] for jj in [0...numCols] for ii in [0...numRows])
          else
            domElementGrid = (linkCellFn ii, jj, sourceGridModel[jj][ii] for jj in [0...numCols] for ii in [0...numRows])
        else
          if srcDimOrder == 'RowMajor'
            domElementGrid = (linkCellFn ii, jj, sourceGridModel[(ii*numCols)+jj] for jj in [0...numCols] for ii in [0...numRows])
          else
            domElementGrid = (linkCellFn ii, jj, sourceGridModel[ii+(jj*numRows)] for jj in [0...numCols] for ii in [0...numRows])
          clickStatesFn = $parse($scope.clickStates)
          if clickStatesFn.constant
            clickHandlerStates = parseClickStates(clickStatesFn($scope), $scope)
          else
            $scope.$watchCollection clickStatesFn, (newClickHandlerStates) ->
              oldClickHandlerStates = clickHandlerStates
              clickHandlerStates = parseClickStates(newClickHandlerStates($scope), $scope)

        gridCtrl = ctrls[0]
        canvasCtrl = ctrls[1]
        canvasCtrl.watchElementControl $element, 'click', clickHandlerStates, gridCtrl
    }
  ]

  xwModule.service 'jhDirtyTracker', [() ->
    workspaceHash = { }

    _markDirty = (workspace, dirtyObject) ->
      if !dirtyObject[workspace.dirtyTagProp]
        workspace.mementoHelper.storeMemento(dirtyObject)
        dirtyObject[workspace.dirtyTagProp] = true
        workspace.content.push(dirtyObject)
      return

    self = {
      trackChanges: (workspaceId, dirtyTagProp, mementoHelper) ->
        workspace = workspaceHash[workspaceId]
        if workspace?
          throw "IllegalArgumentException: Workspace already exists with workspaceId = " + workspaceId
        workspaceHash[workspaceId] =
          dirtyTagProp: dirtyTagProp,
          mementoHelper: mementoHelper
          content: []
        return

      markDirty: (workspaceId, dirtyObject) ->
        workspace = workspaceHash[workspaceId]
        if !workspace?
          throw "IllegalArgumentException: No workspace found with workspaceId = " + workspaceId
        if angular.isArray(dirtyObject)
          dirtyObject.forEach( (v) -> _markDirty(workspace, v) )
        else
          _markDirty(workspace, dirtyObject)
        return

      clearDirtyList: (workspaceId, onPurgeFnName) ->
        workspace = workspaceHash[workspaceId]
        if !workspace?
          throw "IllegalArgumentException: No workspace found with workspaceId = " + workspaceId
        workspace.content.forEach( (v) ->
          purgeFn = v[onPurgeFnName]
          purgeFn.call(v)
          Object.delete(v, workspace.dirtyTagProp)
          return
        )
        Object.delete(workspaceHash, workspaceId)
        return retVal
    }

    return self
  ]


  return xwModule
