(function() {
  'use strict';

  XWInventoryController.$inject = ['$state', 'XWInventoryCanvas', 'Studio', 'eventAggregator'];
  module.exports = XWInventoryController;

  function XWInventoryController($state, XWInventoryCanvas, Studio, eventAggregator) {
    this.openSelected = function() {
      DocumentCacheManager.openDocument(
        XWInventoryCanvas.getSelectedDocumentID()
      ).then(
        function onPass(value) {
          eventAggregator.trigger('org.jchptf.events.openDocumentPassed', {user: '...', docId: value});
          $state.to('crosswords.tickets.' + XWInventoryCanvas.getSelectedDocumentID());
        },
        function onErr(error) {
          console.error('Failed to open and cache document', error);
          eventAggregator.trigger('org.jchptf.events.failedToOpenDocument', {user: '...', docId: '...'});
        }
      );
    };
  }
}).call(window);

