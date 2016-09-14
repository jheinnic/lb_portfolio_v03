(function (angular) {
  'use strict';

  module.exports = XwInventoryController;
  XwInventoryController.$inject = ['$scope', '$state', 'XwInventoryCanvas', 'Studio', 'eventAggregator'];

  function XwInventoryController($scope, $state, XwInventoryCanvas, Studio, eventAggregator) {
    this.scope = $scope;
    this.$state = $state;
    this.XwInventoryCanvas = XwInventoryCanvas;
    this.Studio = Studio;
    this.eventAggregator = eventAggregator;

    $scope.treeOptions = {
      nodeChildren: 'contents',
      dirSelectable: false,
      injectClasses: {
        ul: 'a1',
        li: 'a2',
        liSelected: 'a7',
        iExpanded: 'a3',
        iCollapsed: 'a4',
        iLeaf: 'a5',
        label: 'a6',
        labelSelected: 'a8'
      }
    };

    $scope.dataForTheTree = [
      {
        'name': 'Joe',
        'age': '21',
        'children': [
          {
            'name': 'Smith',
            'age': '42',
            'children': []
          }, {
            'name': 'Gary',
            'age': '21',
            'children': [
              {
                'name': 'Jenifer',
                'age': '23',
                'children': [
                  {
                    'name': 'Dani',
                    'age': '32',
                    'children': []
                  }, {
                    'name': 'Max',
                    'age': '34',
                    'children': []
                  }
                ]
              }
            ]
          }
        ]
      }, {
        'name': 'Albert',
        'age': '33',
        'children': []
      }, {
        'name': 'Ron',
        'age': '29',
        'children': []
      }
    ];
  }

  angular.extend(
    XwInventoryController.prototype, {
      openSelected: function openSelected() {
        var docId = this.XwInventoryCanvas.getSelectedDocumentId();
        this.Studio.openDocument(docId).then(
          function onPass() {
            this.eventAggregator.trigger('org.jchptf.events.openDocumentPassed', {docId: docId});
            this.$state.to('crosswords.tickets', {docId: docId});
          }
        ).catch(
          function onErr(error) {
            console.error('Failed to open and cache document', error);
            this.eventAggregator.trigger('org.jchptf.events.failedToOpenDocument', {docId: docId});
          }
        ).done();
      },

      computeNodeLabelCss: function computeNodeLabelCss(node) {
        var retVal;
        switch (node.getDocumentKind()) {
          case this.DocumentKind.FOLDER:
            retVal = 'badge-info node-folder';
            break;
          case this.DocumentKind.XW_TICKET:
            retVal = 'badge-ok node-ticket';
            break;
          case this.DocumentKind.XW_RESULT:
            retVal = 'badge-warning node-result';
            break;
          default:
            retVal = 'badge-danger node-unknown';
            break;
        }

        return retVal;
      },

      onSelectionChange: function onSelectionChanged(node, selected) {
        if (selected) {
          this.Studio.select(node);
        } else {
          this.Studio.deselect(node);
        }
      },

      onExpansionChange: function onExpansionChanged(node, expanded) {
        if (expanded) {
          this.Studio.select(node);
        } else {
          this.Studio.deselect(node);
        }
      }
    }
  );
}).call(window, window.angular);

