angular.module('jchpft.tools.iconPanel').directive('jhGrid', jhGridDirective)

jhGridDirective.$inject = ['$parse']

jhGridDirective = ($parse) ->
  domElementGrid = null
  clickHandlerStates = {}
  currentClickHandler = null

  numRows = -1
  numCols = -1
  canvasCtrl = null

  sizeAttrRegEx = /^\s*([\d]+)\s*x\s*([\d]+)\s*$/
  gridCellOrgRegEx = /^\s*(?:(1D|2D)\s*,\s*(RowCol|ColRow))|(1D|2D)|(RowCol|ColRow)\s*$/

  return {
    restrict: 'E'
    require: [ 'jhGrid', 'ngModel' ]
    replace: false
    scope:
      sourceGridModel: '=gridModel'
      clickStates: '@clickStates'
      numRows: '@numRows'
      numCols: '@numCols'
      cellWidth: '@cellWidth'
      cellHeight: '@cellHeight'
      srcDimCount: '@srcDimCount'
      srcDimOrder: '@srcDimOrder'
    transclude: 'element'
    controller: [
      '$scope',
      '$element',
      '$parse',
      ($scope, $element, $parse) ->
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

              domElementGrid[ii][jj].unbind('click', oldState) && domElementGrid[ii][jj].on('click', newState) \
              foo jj in [0...numCols] for ii in [0...numRows]

              if newState.onEnter?
                $scope.$apply newState.onEnter

        retVal.getScopeByCoordinates = (rowId, colId) ->
          if domElementGrid == null
            throw new Error "cannot retrieve cells by coordinates until after grid has been populated"
          if rowId < 0 || rowId >= numRows
            throw new Error "rowId must be a value between 0 and #{numRows-1}.  rowId = #{rowId}"
          if colId < 0 || colId >= numCols
            throw new Error "rowId must be a value between 0 and #{numCols-1}.  colId = #{colId}"

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
        throw new Error "srcDims must be '2D' or '1D', not #{$scope.srcDimCount}"
      if $scope.srcDimOrder != 'RowMajor' && $scope.srcDimOrder != 'ColMajor' && angular.isDefined $scope.srcDimOrder
        throw new Error "srcDimOrder must be 'RowMajor' or 'ColMajor', not #{srcDimOrder}"
      if $scope.numRows < 1
        throw new Error "numRows must be a positive value.  numRows = #{$scope.numRows}"
      if $scope.numCols < 1
        throw new Error "numCols must be a positive value.  numCols = #{$scope.numRows}"
      if $scope.cellWidth < 1
        throw new Error "cellWidth must be a positive value.  cellWidth = #{$scope.cellWidth}"
      if $scope.cellHeight < 1
        throw new Error "cellHeight must be a positive value.  cellHeight = #{$scope.cellHeight}"

      sourceGridModel = $scope.sourceGridModel
      numCells = $scope.numRows * $scope.numCols
      srcDimCount = $scope.srcDimCount
      srcDimOrder = $scope.srcDimOrder
      cellHeight = $scope.cellHeight
      cellWidth = $scope.cellWidth
      numRows = $scope.numRows
      numCols = $scope.numCols

      if !sourceGridModel || !angular.isArray sourceGridModel
        throw new Error "sourceGridModel must be an array, not #{sourceGridModel}."
      if srcDims = '1D'
        if sourceGridModel.length != numCells
          throw new Error "sourceGridModel must have length = #{numCells} but has #{sourceGridModel.length}."
      else if srcDimOrder = 'RowMajor'
        if sourceGridModel.length != numRows
          throw new Error "sourceGridModel's first dimmension must be #{numRows}, not #{srcDimOrder.length}."
        checkRow = (ii) ->
          if sourceGridModel[ii].length != numCols
            throw new Error "sourceGridModel's 2nd dimension must be #{numCols}, not #{sourceGridModel[ii].length}"
        checkRow(ii) for ii in [0...numRows]
      else
        if sourceGridModel.length != numCols
          throw new Error "sourceGridModel's first dimmension must be #{numCols}, not #{srcDimOrder.length}."
        checkCol = (ii) ->
          if sourceGridModel[ii].length != numRows
            throw new Error "sourceGridModel's 2nd dimension must be #{numRows}, not #{sourceGridModel[ii].length}"
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
              domElementGrid[ii][jj].unbind('click', currentClickHandler) \
              for jj in [0...numCols] for ii in [0...numRows]
            return

          cloneCellElm.css
            top: (ii * cellHeight) + 'px'
            left: (jj * cellWidth) + 'px'
            height: cellHeightStr
            width: cellWidthStr

          return cloneCellElm

      if srcDims == '2D'
        if srcDimOrder == 'RowMajor'
          domElementGrid = (linkCellFn ii, jj, sourceGridModel[ii][jj] for jj in [0...numCols] for ii in [0...numRows])
        else
          domElementGrid = (linkCellFn ii, jj, sourceGridModel[jj][ii] for jj in [0...numCols] for ii in [0...numRows])
      else
        if srcDimOrder == 'RowMajor'
          domElementGrid = (linkCellFn ii, jj, sourceGridModel[(ii*numCols)+jj] \
          for jj in [0...numCols] for ii in [0...numRows])
        else
          domElementGrid = (linkCellFn ii, jj, sourceGridModel[ii+(jj*numRows)] \
          for jj in [0...numCols] for ii in [0...numRows])
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
