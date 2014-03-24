define ['angular'], ->
  xwModule = angular.module('jch-ui-blocks', ['ng']);

  xwModule.directive 'jchXyPos', ['$interpolate', ($interpolate) ->
    attrRegEx = /^\s*(\(\s*([\S]+)\s*,\s*([\S]+)\s*\)\s+at\s+)?([\d]+)\s+by\s+([\d]+)\s*$/

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

        topFn  = $interpolate '{{' + rowIdExpr + '*' + cellHeight + '}}px'
        leftFn = $interpolate '{{' + colIdExpr + '*' + cellWidth + '}}px'

        $scope.watch topFn, (value) -> $elem.css 'top', value
        $scope.watch leftFn, (value) -> $elem.css 'left', value

        return $elem
    }
  ]

  xwModule.directive 'TODOjchXyRepeat', [() ->
    # TODO: Make sure priority places this after any custom directive descriptor returned by
    #       xwDirectiveFactory.cellDirective(gridTypeName)
    return {
      transclude: 'element',
      priority: 1000,
      terminal: true,
      link: ($scope, $element, $attr, ctrl, $transclude) ->
        expression = $attr.jchXyRepeat
        match = expression.match /^\s*([\S]+?)(?:\(([\S]+),([\S]+)\))?(?:\s+(rows|col(?:umn)?s)\s+first)?\s+from\s+([\S]+?)\(([\d]+),([\d]+)\)(?:\s+of\s+(1d|2d))?\s*$/

        if (!match)
          throw "Invalid argument: " + expression + " is not formatted correctly"

        cellPropName = match[1]
        rowPropName = match[2] || 'rowId'
        colPropName = match[3] || 'colId'
        orientation = match[4] || 'rows'
        gridPropName = match[5]
        numRows = match[6]
        numCols = match[7]
        accessStyle = match[8] || '2d'
    }
  ]

  xwModule.directive 'TODOxwField', ['$interpolate', ($interpolate) ->
    # TODO: Make sure priority places this after any custom directive descriptor returned by
    #       xwDirectiveFactory.cellDirective(gridTypeName)
    return {
      restrict: 'CA'
      replace: false
      scope: false
      controller: ($scope, $elem, $attr) ->
        this.registerGridModel = (gridTypeName, gridModel) ->
          @grids[gridTypeName] = gridModel

        this.lookupGridModel = (gridTypeName) ->
          return @grids[gridTypeName]

        ##
        ## Cursor Activation and Location Management
        # isActive: () -> return @cursor?

        this.markCursorDirty = () ->
          if (!@cursorCell?)
            throw 'IllegalStateException: @cursor is null'
        # TODO: Ensure this is always called after committing to the decision to change a cell in a way
        #       protected by cursor rollback but before modifying any state.  Alternately, make a deep
        #       copy of the grid and cell models during openCursor() and restore or discard same during
        #       calls to closeCursor().
        this.markDirty = (dirtyCell) ->
          if (! AbstractGridModel.isLegalValueRegex.test(dirtyCell?.content))
            throw 'IllegalArgument: dirtyCell?.content = ' + dirtyCell?.content
          if (! dirtyCell.dirty)
            # TODO: Save a memory for rollback
            @dirtyCells.push(dirtyCell)
            dirtyCell.dirty = true

        # The caller is responsible for confirming that cursorCell is a valid cell for opening a cursor
        this.openCursor = (cursorCell) =>
          if (cursorCell == null)
            throw 'IllegalArgument: cursorCell = null'
          if (@cursor? || @ticketModel.hasOpenCursor())
            throw 'IllegalState: ticket already has an open cursor'

          @cursorCell = cursorCell

        this.closeCursor = (withCommit = true) =>
          if (@cursorCell == null)
            throw 'IllegalState: @cursorCell is null'

          if (withCommit)
            dirtyFn = (dirtyCell) ->
              dirtyCell.commit()
              dirtyCell.dirty = false
          else
            dirtyFn = (dirtyCell) ->
              dirtyCell.rollback()
              dirtyCell.dirty = false

          @dirtyCells.forEach dirtyFn
          @dirtyCells.length = 0
          @cursorCell = null

        # The caller is responsible for confirming that cursorCell is a valid cell for moving the open cursor to
        this.moveCursor = (fromCell, toCell) =>
          if (toCell == null)
            throw 'IllegalArgument: toCell = null'
          if (@cursorCell != fromCell)
            throw 'IllegalArgument: this.cursor is at (' + @cursorCell.rowId + ',' + @cursorCell.colId +
            ', but fromCell is at (' + fromCell + ',' + toCell + ')'

          @cursorCell = toCell

        return this
    }
  ]

  xwModule.directive 'xwGrid', ['$interpolate', ($interpolate) ->
    # These functions are always the same no matter how many times this directive links.
    topPxValFn    = $interpolate '{{modelName}}.{{rowIdName}} * {{cellHeight}}'
    leftPxValFn   = $interpolate '{{modelName}}.{{colIdName}} * {{cellWidth}}'
    topExprFn     = $interpolate '{{modelName}}.{{rowIdName}}'
    leftExprFn    = $interpolate '{{modelName}}.{{colIdName}}'

    # TODO: Make sure priority places this after any custom directive descriptor returned by
    #       xwDirectiveFactory.cellDirective(gridTypeName)
    return {
      restrict: 'CA'
      replace: false
      require: ['xwGrid', '^xwField']
      scope: false
      transclude: true
      controller: ($scope, $elem, $attr) ->
        # TODO: To treat a set of related cells the same way more efficiently, consider putting
        #       these attributes on the directive for xw-layered and using its controller here
        #       to access one shared copy of the metadata.
        mergeFn = overrideWith (source, overrides) ->
          return {
            gridName:   overrides?.gridName   || source?.gridName   || 'grid'
            cellsProp:  overrides?.cellsProp  || source?.cellsProp  || 'cells'
            iterStyle:  overrides?.iterStyle  || source?.iterStyle  || 'rows'
            cellAccess: overrides?.cellAccess || source?.cellAccess || '1d'
            cellWidth:  overrides?.cellWidth  || source?.cellWidth  || 28
            cellHeight: overrides?.cellHeight || source?.cellHeight || 28

          # overrideWith cannot be assigned a function bound to this object as it is
          # being constructed, but it can be assigned a function that lazily defers
          # the bind call and repairs the referene on-first-use to avoid redundantly
          # repeating the same bind call if the same source object's overrideWith is
          # gets called more than once.
          overrideWith: lazyOverrideWith (overrides) ->
            this.overrideWith = angular.bind(this, mergeFn, this)
            return this.overrideWith.apply(overrides)
          }

        # Start with the defaults so they may be overriden as $observe handlers are added.
        this.metaAttrs     = mergeFn()
        return this
    link: ($scope, $elem, $attr, ctrls) ->
        # Accumulate meta changes in this object.  If multiple attr observations are queued
        # within a single apply cycle, they should all get populated here before the next
        # $digest() call occurs and notices the new object created by the first $observe
        # handler in the batch that was called.
        metaChanges   = { hasChanges: false }

        gridCtrl = ctrls[0]
        fieldCtrl = ctrls[1]

        # Initialize these with a wrapper method that overwrites itself the first time the
        # interpolation behavior is needed.  This faciliates lazy generation of interpolation
        # methods without testing for null at each use.  Reuse the wrappers to set up for
        # regenerating new interpolation methods when a meta-attribute (e.g. cell size,
        # cell model name, row property name, and/or column property name) change causes the
        # interpolation logic to change with it.
        initTopFn  = initTopFn($elem) ->
          topFn  = $interpolate '{{' + topPxValFn gridCtrl.metaAttrs  + '}}px'
          return topFn($elem)
        initLeftFn = initLeftFn($elem) ->
          leftFn = $interpolate '{{' + leftPxValFn gridCtrl.metaAttrs + '}}px'
          return leftFn($elem)

        topFn      = initTopFn
        leftFn     = initLeftFn

        $attr.$observe 'modelName', updateModelNameAttr (attrVal) ->
          if (metaChanges.hasChanges)
            metaChanges.modelName = attrVal
          else
            metaChanges =
              hasChanges: true
              modelName: attrVal
          topFn  = initTopFn
          leftFn = initLeftFn

        $attr.$observe 'rowIdName', updateRowIdNameAttr (attrVal) ->
          if (metaChanges.hasChanges)
            metaChanges.rowIdName = attrVal
          else
            metaChanges =
              hasChanges: true
              rowIdName: attrVal
          topFn  = initTopFn

        $attr.$observe 'cellHeight', updateCellSizeAttr (attrVal) ->
          if (metaChanges.hasChanges)
            metaChanges.cellHeight = attrVal
          else
            metaChanges =
              hasChanges: true
              cellHeight: attrVal
          topFn  = initTopFn

        $attr.$observe 'colIdName', updateColIdNameAttr (attrVal) ->
          if (metaChanges.hasChanges)
            metaChanges.colIdName = attrVal
          else
            metaChanges =
              hasChanges: true
              colIdName: attrVal
          leftFn = initLeftFn

        $attr.$observe 'cellWidth', updateCellSizeAttr (attrVal) ->
          if (metaChanges.hasChanges)
            metaChanges.cellWidth = attrVal
          else
            metaChanges =
              hasChanges: true
              cellWidth: attrVal
          leftFn  = initLeftFn

        rowWatchFn      = () -> $elem.css 'top', topFn.apply($scope)
        colWatchFn      = () -> $elem.css 'col', leftFn.apply($scope)
        deregRowWatchFn = $scope.watch topExprFn.apply(gridCtrl.metaAttrs), rowWatchFn
        deregColWatchFn = $scope.watch leftExprFn.apply(gridCtrl.metaAttrs), colWatchFn

        $scope.watch(
          ($scope) -> metaChanges
          (newVal, oldVal) ->
            # Absorb the overriden attribute changes together.  Afterwards, set metaChanges.hasChanges false
            # rather than replacing or nulling out he artifact to avoid triggering a redundant call into this
            # handler.  The first $observe handler for any batch of meta-attribute changes needs the attribute
            # toggle so it can arrange for this handler to be called at the end of its batch's $digest cycle.
            metaAttrs = metaAttrs.overrideWith(metaChanges)
            metaChanges.hasChanges = false

            if (newVal == oldVal)
              # Leverage the no-change initialization call to be avoid redundantly setting position to absolute.
              $elem.css position, 'absolute'
            else
              # In all other cases, re-register the row and column watches, which will cause their watch handlers
              # to get invoked and refresh the style tags as desired.
              deregRowWatchFn.call();
              deregColWatchFn.call();

              deregRowWatchFn = $scope.watch  topExprFn.apply(metaAttrs), rowWatchFn
              deregColWatchFn = $scope.watch leftExprFn.apply(metaAttrs), colWatchFn
        )

        $scope.watch()
    }
  ]

  xwModule.directive 'TODOxwCell', [() ->
    # TODO: Make sure priority places this after any custom directive descriptor returned by
    #       xwDirectiveFactory.cellDirective(gridTypeName)
    return {
      restrict: 'CA'
      replace: false
      scope: false
      link: ($scope, $elem, $attr) ->
        return
    }
  ]

  return xwModule