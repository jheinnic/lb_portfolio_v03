module.exports = [
  '$parse', ($parse) ->
    domElementGrid = null
    clickHandlerStates = {}
    currentClickHandler = null

    numRows = -1
    numCols = -1
    canvasCtrl = undefined
    gridCtrl = undefined

    sizeAttrRegEx = /^\s*([\d]+)\s*x\s*([\d]+)\s*$/
    gridCellOrgRegEx = /^\s*(?:(1D|2D)\s*,\s*(RowCol|ColRow))|(1D|2D)|(RowCol|ColRow)\s*$/

    return { restrict: 'E'
    require: ['jhGrid', 'ngModel']
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
      # TODO: Turn this into the constructor for an equivalent Controller Class.
      '$scope', '$element', '$parse', ($scope, $element, $parse) ->
        @ = {}

        @parseControlStates = (controlStatesDef) ->
          controlStates = {}
          Object.keys(controlStatesDef).forEach(
            (stateName) ->
              controlState =
                stateName: stateName
                onEnter: $parse(controlStatesDef[stateName].onEnter or null)
                onExit: $parse(controlStatesDef[stateName].onExit or null)

              if controlStatesDef[stateName].eventHandler?
                parsedEventHandler = $parse(controlStatesDef[stateName].eventHandler)
                controlState.eventHandler = ($event) ->
                  $scope.$apply () -> parsedEventHandler($scope, { '$event': $event })
              else
                controlState.eventHandler = undefined

              controlStates[stateName] = controlState
          )

          controlStates

        @handleStateChange = (newState, oldState) ->
          if oldState?.onExit
            $scope.$apply(oldState.onExit)

          if oldState?.eventHandler
            if newState?.eventHandler
              cellEventFn = (domElementCell) ->
                domElementCell.unbind('click', oldState.eventHandler)
                domElementCell.on('click', newState.eventHandler)
            else
              cellEventFn = (domElementCell) ->
                domElementCell.unbind('click', oldState.eventHandler)
          else if newState?.eventHandler
            cellEventFn = (domElementCell) ->
              domElementCell.on('click', newState.eventHandler)
          else
            cellEventFn = null

          if cellEventFn isnt null
            for ii in [0...numRows]
              domElementRow = domElementGrid[ii]
              for jj in [0...numCols]
                cellEventFn(domElementRow[jj])

          if newState?.onEnter?
            $scope.$apply newState.onEnter

        @getScopeByCoordinates = (rowId, colId) ->
          if domElementGrid is null
            throw new Error("cannot retrieve cells by coordinates until after grid has been populated")
          if rowId < 0 or rowId >= numRows
            throw new Error("rowId must be a value between 0 and #{numRows - 1}.  rowId = #{rowId}")
          if colId < 0 or colId >= numCols
            throw new Error("rowId must be a value between 0 and #{numCols - 1}.  colId = #{colId}")

          return domElementGrid[rowId][colId].scope()

        @setClickState = (nextStateName, rowId = -1, colId = -1) ->
          console.log(nextStateName, 'click', rowId, colId) # canvasCtrl.setElementControlState($element, 'click', nextStateName)

        @moveCursorTo = (rowId, colId) -> console.log('tbd', rowId, colId)

        return this
    ]
    link: ($scope, $element, $attrs, controllers, $transcludeFn) ->
      if $scope.srcDimCount != '2D' && $scope.srcDimCount != '1D' && angular.isDefined $scope.srcDimCount
        throw new Error("srcDimCount must be '2D' or '1D', not #{$scope.srcDimCount}")
      if $scope.srcDimOrder != 'RowMajor' && $scope.srcDimOrder != 'ColMajor' && angular.isDefined $scope.srcDimOrder
        throw new Error("srcDimOrder must be 'RowMajor' or 'ColMajor', not #{srcDimOrder}")
      if $scope.numRows < 1
        throw new Error("numRows must be a positive value.  numRows = #{$scope.numRows}")
      if $scope.numCols < 1
        throw new Error("numCols must be a positive value.  numCols = #{$scope.numRows}")
      if $scope.cellWidth < 1
        throw new Error("cellWidth must be a positive value.  cellWidth = #{$scope.cellWidth}")
      if $scope.cellHeight < 1
        throw new Error("cellHeight must be a positive value.  cellHeight = #{$scope.cellHeight}")

      sourceGridModel = $scope.sourceGridModel
      numCells = $scope.numRows * $scope.numCols
      srcDimCount = $scope.srcDimCount
      srcDimOrder = $scope.srcDimOrder
      cellHeight = $scope.cellHeight
      cellWidth = $scope.cellWidth
      numRows = $scope.numRows
      numCols = $scope.numCols

      switch
        when not (sourceGridModel and angular.isArray sourceGridModel)
          throw new Error("sourceGridModel must be an array, not #{sourceGridModel}")
        when srcDimCount is '1D'
          unless sourceGridModel.length is numCells
            throw new Error("sourceGridModel length must be #{numCells}, but was #{sourceGridModel.length}")
        when srcDimOrder = 'RowMajor'
          unless sourceGridModel.length is numRows
            throw new Error("sourceGridModel's first dimension must be #{numRows}, but was #{srcDimOrder.length}")
          unless _.every(sourceGridModel, 'length', numCols)
            throw new Error("sourceGridModel's 2nd dimension must be #{numCols}, but was not so for every row")
        else
          unless sourceGridModel.length is numCols
            throw new Error("sourceGridModel's first dimension must be #{numCols}, but was #{srcDimOrder.length}")
          unless _.every(sourceGridModel, 'length', numRows)
            throw new Error("sourceGridModel's 2nd dimension must be #{numRows}, but was not so for every row")

      # cellTemplateFn = $compile pseudoElem.html()
      cellHeightStr = cellHeight + 'px'
      cellWidthStr = cellWidth + 'px'
      linkCellFn = (ii, jj, cellModel) ->
        scope = $scope.$new(false)
        scope.cellModel = cellModel
        scope.rowId = ii
        scope.colId = jj

        return $transcludeFn scope, (cloneCellElm) ->
          $element.append(cloneCellElm)

          cloneCellElm.on(
            '$destroy', ->
              if (currentClickHandler?)
                for ii in [0...numRows]
                  domElementRow = domElementGrid[ii]
                  for jj in [0...numCols]
                    domElementRow[jj].unbind('click', currentClickHandler)
          );

          cloneCellElm.css(
            top: (ii * cellHeight) + 'px'
            left: (jj * cellWidth) + 'px'
            height: cellHeightStr
            width: cellWidthStr
          );

          return cloneCellElm

      switch
        when srcDimCount is '2D' && srcDimOrder is 'RowMajor'
          domElementGrid = for ii in [0...numRows]
            for jj in [0...numCols]
              linkCellFn(ii, jj, sourceGridModel[ii][jj])
        when srcDimCount is '2D' && srcDimOrder is 'ColMajor'
          domElementGrid = for ii in [0...numRows]
            for jj in [0...numCols]
              linkCellFn(ii, jj, sourceGridModel[jj][ii])
        when srcDimCount is '1D' && srcDimOrder is 'RowMajor'
          domElementGrid = for ii in [0...numRows]
            rowOffset = ii * numCols
            for jj in [0...numCols]
              linkCellFn(ii, jj, sourceGridModel[rowOffset + jj])
        when srcDimCount is '1D' && srcDimOrder is 'ColMajor'
          domElementGrid = for ii in [0...numRows]
            colOffset = 0
            for jj in [0...numCols]
              linkCellFn(ii, jj, sourceGridModel[colOffset + ii])
              colOffset = colOffset + numRows
        else
          throw new Error(
            "srcDimCount (#{srcDimCount} must be '1D' or '2D', and srcDimOrder (#{srcDimOrder} must be 'RowMajor' or 'ColMajor'"
          );


#      clickStatesFn = $parse($scope.clickStates)
#      if clickStatesFn.constant
#        clickHandlerStates = @parseControlStates(clickStatesFn($scope), $scope)
#      else
#        $scope.$watchCollection(clickStatesFn, (newClickHandlerStates) ->
#          clickHandlerStates = @parseControlStates(newClickHandlerStates($scope), $scope);
#          # oldClickHandlerStates = clickHandlerStates
#
#      [gridCtrl, canvasCtrl] = controllers
#      canvasCtrl.watchEventControl($element, 'click', clickHandlerStates, gridCtrl)
    }
  ]
