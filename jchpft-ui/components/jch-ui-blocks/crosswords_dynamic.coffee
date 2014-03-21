define ['angular'], ->
  xwModule = angular.module('jch-ng-fsm', []);

  xwModule.factory 'xwDirectiveFactory', () ->
    templateDirPath = '/public/partials/crosswords/'

    return {
      gridDirective: gridDirective (forGridKind) ->
        discriminator = forGridKind.getGridTypeName()

        return {
          restrict: 'E'
          replace: true
          require: '^xwTicket'
          scope: true
          templateUrl: templateDirPath + discriminator + '.html'
          link: ($scope, $elem, $attrs, ticketCtrl) ->
            $scope.gridModel = ticketCtrl.getGridModel forGridKind
            $scope.$on 'xw.gridEvent.cellClicked', ($event, gridKind, gridModel, cellModel) ->
              if (! ticketCtrl.isEditableAt gridKind)
                $event.cancel();
              else if (gridModel.isActive())
                # If this grid is already active, and represents a valid move within the cursor, abort
                # the event propagation and handle it within this cursor.
                moveCursorCell = gridModel.testForMoveCursor cellModel
                if (moveCursorCell?)
                  gridModel.moveCursor $scope.ticketModel.activeGrid.cursor, moveCursorCell, false
                  $event.cancel()
              return
        }

      # TODO: Set the top/left/position styles and onClick handler programatically to make the template lighter
      cellDirective: cellDirective (forGridKind) ->
        discriminator = forGridKind.getCellTypeName()

        return {
          restrict: 'E'
          replace: true
          require: '^xwTicket'
          scope: true
          templateUrl: templateDirPath + discriminator + '.html'
          link: ($scope, $elem, $attrs, ticketCtrl) ->
            $scope.cellModel = ticketCtrl.getCellModel forGridKind, parseInt($attrs.rowid), parseInt($attrs.colid)
            $scope.onCellClick = () ->
              cellModel = $scope.cellModel
              $scope.$emit 'xw.gridEvent.cellClicked', forGridKind, cellModel.parentGrid, cellModel
            $scope.$on 'xw.gridEvent.closeCursor', ($event, withCommit) ->
              $scope.cellModel.onCloseCursor(withCommit)
            $scope.$on 'xw.gridEvent.openCursor', ($event, cursorCell) ->
              if ($scope.cellModel == cursorCell)
                cursorCell.onOpenCursor()
            return
        }
      # TODO: Make 'xwTicket' less involved by using scope: true and using xwDataSvc in a link function.
      reportDirective: reportDirective (reportTypeName) ->
        scopeObj = {}
        scopeObj[reportTypeName + 'Model'] = '@'

        return {
          restrict: 'E'
          replace: true
          scope: scopeObj
          templateUrl: templateDirPath + reportTypeName + 'Report.html'
        }
    }

  xwModule.directive 'xwCell', ['$interpolate', ($interpolate) ->
    # These functions are always the same no matter how many times this directive links.
    topPxValFn    = $interpolate '{{modelName}}.{{rowIdName}} * {{cellSize}}'
    leftPxValFn   = $interpolate '{{modelName}}.{{colIdName}} * {{cellSize}}'
    topExprFn     = $interpolate '{{modelName}}.{{rowIdName}}'
    leftExprFn    = $interpolate '{{modelName}}.{{colIdName}}'

    # TODO: Make sure priority places this after any custom directive descriptor returned by
    #       xwDirectiveFactory.cellDirective(gridTypeName)
    return {
      restrict: 'CA'
      replace: false
      scope: false
      link: ($scope, $elem, $attr) ->
        # TODO: To treat a set of related cells the same way more efficiently, consider putting
        #       these attributes on the directive for xw-layered and using its controller here
        #       to access one shared copy of the metadata.
        mergeFn = overrideWith (source, overrides) ->
          return {
            modelName: overrides?.modelName || source?.modelName || 'cellModel'
            rowIdName: overrides?.rowIdName || source?.rowIdName || 'rowId'
            colIdName: overrides?.colIdName || source?.colIdName || 'colId'
            cellSize:  overrides?.cellSize  || source?.cellSize  || 28

          # overrideWith cannot be assigned a function bound to this object as it is
          # being constructed, but it can be assigned a function that lazily defers
          # the bind call and repairs the referene on-first-use to avoid redundantly
          # repeating the same bind call if the same source object's overrideWith is
          # gets called more than once.
            overrideWith: lazyOverrideWith (overrides) ->
              this.overrideWith = angular.bind(this, mergeFn, this)
              return this.overrideWith(overrides)
          }

        # Start with the defaults so they may be overriden as $observe handlers are added.
        metaAttrs     = mergeFn()

        # Accumulate meta changes in this object.  If multiple attr observations are queued
        # within a single apply cycle, they should all get populated here before the next
        # $digest() call occurs and notices the new object created by the first $observe
        # handler in the batch that was called.
        metaChanges   = { hasChanges: false }

        # Initialize these with a wrapper method that overwrites itself the first time the
        # interpolation behavior is needed.  This faciliates lazy generation of interpolation
        # methods without testing for null at each use.  Reuse the wrappers to set up for
        # regenerating new interpolation methods when a meta-attribute (e.g. cell size,
        # cell model name, row property name, and/or column property name) change causes the
        # interpolation logic to change with it.
        initTopFn  = initTopFn($elem) ->
          topFn  = $interpolate '{{' + topPxValFn metaAttrs  + '}}px'
          return topFn($elem)
        initLeftFn = initLeftFn($elem) ->
          leftFn = $interpolate '{{' + leftPxValFn metaAttrs + '}}px'
          return leftFn($elem)
        topFn      = initTopFn
        leftFn     = initLeftFn

        $attr.$observe 'cellSize', updateCellSizeAttr (attrVal) ->
          if (metaChanges.hasChanges)
            metaChanges.cellSize = attrVal
          else
            metaChanges =
              hasChanges: true
              cellSize: attrVal
          topFn  = initTopFn
          leftFn = initLeftFn

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

        $attr.$observe 'colIdName', updateColIdNameAttr (attrVal) ->
          if (metaChanges.hasChanges)
            metaChanges.colIdName = attrVal
          else
            metaChanges =
              hasChanges: true
              colIdName: attrVal
          leftFn = initLeftFn

        rowWatchFn      = () -> $elem.css 'top', topFn.apply($scope)
        colWatchFn      = () -> $elem.css 'col', leftFn.apply($scope)
        deregRowWatchFn = $scope.watch topExprFn(metaAttrs), rowWatchFn
        deregColWatchFn = $scope.watch leftExprFn(metaAttrs), colWatchFn

        $scope.watch(
          ($scope) -> metaChanges
          (newVal, oldVal) ->
            # TODO: Explore if this belongs in the else block below by examining how the $observe handlers trigger
            #       when defaults (omitted), fixed (immutable), and interpolated (data-bound) DOM attrs are used.
            #       The goal is to avoid redundant overrideWith calls(), $elem.css() calls, and $watch handle
            #       deregister/register cycles unless necessary to avoid missing any such required actions in
            #       some other case.
            #
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

  xwModule.directive 'xwCell', ['$interpolate', ($interpolate) ->
    # These functions are always the same no matter how many times this directive links.
    topPxValFn    = $interpolate '{{modelName}}.{{rowIdName}} * {{cellSize}}'
    leftPxValFn   = $interpolate '{{modelName}}.{{colIdName}} * {{cellSize}}'
    topExprFn     = $interpolate '{{modelName}}.{{rowIdName}}'
    leftExprFn    = $interpolate '{{modelName}}.{{colIdName}}'

    # TODO: Make sure priority places this after any custom directive descriptor returned by
    #       xwDirectiveFactory.cellDirective(gridTypeName)
    return {
    restrict: 'CA'
    replace: false
    scope: false
    link: ($scope, $elem, $attr) ->
      # TODO: To treat a set of related cells the same way more efficiently, consider putting
      #       these attributes on the directive for xw-layered and using its controller here
      #       to access one shared copy of the metadata.
      mergeFn = overrideWith (source, overrides) ->
        return {
        modelName: overrides?.modelName || source?.modelName || 'cellModel'
        rowIdName: overrides?.rowIdName || source?.rowIdName || 'rowId'
        colIdName: overrides?.colIdName || source?.colIdName || 'colId'
        cellSize:  overrides?.cellSize  || source?.cellSize  || 28

        # overrideWith cannot be assigned a function bound to this object as it is
        # being constructed, but it can be assigned a function that lazily defers
        # the bind call and repairs the referene on-first-use to avoid redundantly
        # repeating the same bind call if the same source object's overrideWith is
        # gets called more than once.
        overrideWith: lazyOverrideWith (overrides) ->
          this.overrideWith = angular.bind(this, mergeFn, this)
          return this.overrideWith(overrides)
        }

      # Start with the defaults so they may be overriden as $observe handlers are added.
      metaAttrs     = mergeFn()

      # Accumulate meta changes in this object.  If multiple attr observations are queued
      # within a single apply cycle, they should all get populated here before the next
      # $digest() call occurs and notices the new object created by the first $observe
      # handler in the batch that was called.
      metaChanges   = { hasChanges: false }

      # Initialize these with a wrapper method that overwrites itself the first time the
      # interpolation behavior is needed.  This faciliates lazy generation of interpolation
      # methods without testing for null at each use.  Reuse the wrappers to set up for
      # regenerating new interpolation methods when a meta-attribute (e.g. cell size,
      # cell model name, row property name, and/or column property name) change causes the
      # interpolation logic to change with it.
      initTopFn  = initTopFn($elem) ->
        topFn  = $interpolate '{{' + topPxValFn metaAttrs  + '}}px'
        return topFn($elem)
      initLeftFn = initLeftFn($elem) ->
        leftFn = $interpolate '{{' + leftPxValFn metaAttrs + '}}px'
        return leftFn($elem)
      topFn      = initTopFn
      leftFn     = initLeftFn

      $attr.$observe 'cellSize', updateCellSizeAttr (attrVal) ->
        if (metaChanges.hasChanges)
          metaChanges.cellSize = attrVal
        else
          metaChanges =
            hasChanges: true
            cellSize: attrVal
        topFn  = initTopFn
        leftFn = initLeftFn

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

      $attr.$observe 'colIdName', updateColIdNameAttr (attrVal) ->
        if (metaChanges.hasChanges)
          metaChanges.colIdName = attrVal
        else
          metaChanges =
            hasChanges: true
            colIdName: attrVal
        leftFn = initLeftFn

      rowWatchFn      = () -> $elem.css 'top', topFn.apply($scope)
      colWatchFn      = () -> $elem.css 'col', leftFn.apply($scope)
      deregRowWatchFn = $scope.watch topExprFn(metaAttrs), rowWatchFn
      deregColWatchFn = $scope.watch leftExprFn(metaAttrs), colWatchFn

      $scope.watch(
        ($scope) -> metaChanges
        (newVal, oldVal) ->
          # TODO: Explore if this belongs in the else block below by examining how the $observe handlers trigger
          #       when defaults (omitted), fixed (immutable), and interpolated (data-bound) DOM attrs are used.
          #       The goal is to avoid redundant overrideWith calls(), $elem.css() calls, and $watch handle
          #       deregister/register cycles unless necessary to avoid missing any such required actions in
          #       some other case.
          #
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

  return xwModule