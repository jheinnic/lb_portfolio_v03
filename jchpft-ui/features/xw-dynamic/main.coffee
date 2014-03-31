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
#            $scope.$watch( 'ticketModel.' + discriminator, (gridModelValue) ->
#              $scope.gridModel = gridModelValue
#            )
#            $scope.$on 'xw.gridEvent.cellClicked', ($event, gridKind, gridModel, cellModel) ->
#              if (! ticketCtrl.isEditableAt gridKind)
#                $event.cancel();
#              else if (gridModel.isActive())
#                # If this grid is already active, and represents a valid move within the cursor, abort
#                # the event propagation and handle it within this cursor.
#                moveCursorCell = gridModel.testForMoveCursor cellModel
#                if (moveCursorCell?)
#                  gridModel.moveCursor $scope.ticketModel.activeGrid.cursor, moveCursorCell, false
#                  $event.cancel()
#              return
        }

      # TODO: Set the top/left/position styles and onClick handler programatically to make the template lighter
      cellDirective: (forGridKind) ->
        discriminator = forGridKind + 'Cell'

        return {
          restrict: 'E'
          replace: true
          require: '^xwTicket'
          scope: false
          templateUrl: templateDirPath + discriminator + '.html'
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
      restrict: 'C'
      replace: true
      scope: false
      priority: 1200
      template: ($element) ->
        # Add new directives to a clone of the present element
        #
        # Replacing the original element with a clone is necessary for Angular to re-compile and
        # "discover" the newly attached directives.  This link fn gets called after Angular has already
        # matched up directives on the original element.  Attaching new directive attrs to the pre-existing
        # DOM element is therefore insufficient for attaching any new directives at the same DOM location
        # because that bus has already left the station.
        clone = $element.clone()
        clone.attr('ng-click', "onCellClick(cellModel, $event)")
        clone.attr('ng-repeat', "cellModel in gridModel.cells track by cellModel.coordinates")
        clone.attr('jch-xy-pos', "(cellModel.rowId,cellModel.colId) at 28 by 28")

        # Replace the triggering directive before the recusive compilation, lest we end up in an
        # infinite loop with ourselves.
        clone.removeClass('xw-proto-cell')
        clone.addClass('xw-cell')

        # Templates are disconnected from the document DOM when accessed.  The angular framework
        # takes responsibility for compiling the template, cloning it if necessary, then attaching
        # elements of the real runtime document to prepare for link phase methods.
        return angular.element('<div></div>').append(clone).html()
    }
  ])
