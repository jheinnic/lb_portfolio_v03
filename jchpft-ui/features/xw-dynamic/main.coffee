define ['angular'], () ->
  xwModule = angular.module('xw-dynamic', []);

  xwModule.factory 'xwDirectiveFactory', () ->
    templateDirPath = '/app/xw-ticket/partials/'

    return {
      gridDirective: (forGridKind) ->
        discriminator = forGridKind + 'Grid'

        return {
          restrict: 'E'
          replace: true
          require: '^xwTicket'
          scope: true
          templateUrl: templateDirPath + discriminator + '.html'
          link: ($scope, $elem, $attrs, ticketCtrl) ->
            $scope.gridModel = $scope.ticketModel[discriminator]
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
      cellDirective: (forGridKind) ->
        discriminator = forGridKind + 'Cell'

        return {
          restrict: 'E'
          replace: true
          require: '^xwTicket'
          scope: true
          templateUrl: templateDirPath + discriminator + '.html'
          link: ($scope, $elem, $attrs, ticketCtrl) ->
            # $scope.cellModel = ticketCtrl.getCellModel forGridKind, parseInt($attrs.rowid), parseInt($attrs.colid)
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
      reportDirective: (reportTypeName) ->
        scopeObj = {}
        scopeObj[reportTypeName + 'Model'] = '='

        return {
          restrict: 'E'
          replace: true
          scope: scopeObj
          templateUrl: '/app/xw-report/partials/' + reportTypeName + 'Report.html'
        }
    }

  xwModule.directive('xwProtoCell', ['$compile', ($compile) ->
    return {
      restrict: 'CA'
      replace: false
      scope: false
      priority: 1200
      terminal: true
      compile: ($element, $attr) ->
        clone = $element.clone()
        clone.attr('ng-repeat', "cellModel in gridModel.cells track by cellModel.coordinates")
        clone.attr('jch-xy-pos', "(cellModel.rowId,cellModel.colId) at 28 by 28")
        clone.attr('ng-click', "onCellClick(cellModel)")
        clone.removeClass('xw-proto-cell')
        clone.addClass('xw-cell')
        cloneLinkFn = $compile(clone)
        return ($scope, $element, $attr) ->
          cloneLinkFn($scope, (linkedClone) ->
            $element.replaceWith(linkedClone)
          )
    }
  ])
