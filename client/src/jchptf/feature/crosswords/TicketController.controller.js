(function(angular) {
  'use strict';

  TicketController.$inject = ['$scope', 'OpenTicketCanvas', 'ModalService'];
  module.exports = TicketController;

  function TicketController($scope, OpenTicketCanvas, ModalService) {
    this.openTicketCanvas = OpenTicketCanvas;
    this.ModalService = ModalService;
    this.$scope = $scope;
  }

  angular.extend(
    TicketController.prototype, {
      onClickBonusWord: function onClickBonusWord() {
        // TODO: How do I inject pre-modal state in for display?
        this.ModalService.showModal({
          templateUrl: 'views/jchptf/crosswords/tickets/bonus_word_modal.html',
          controller: 'BonusWordModalController'
        }).then(function onModalReady(modal) {
          // The modal object has the element built, if this is a bootstrap modal
          // you can call 'modal' to show it, if it's a custom modal just show or
          // hide it as you need to.
          modal.element.modal();
          modal.close.then(function onModalClosed(word) {
            this.$scope.message = 'Modal changed the bonus word to ' + word;
            this.openTicketCanvas.setBonusWord(word);
          }.bind(this));
        }.bind(this));
      }
    }
  );
}).call(window, window.angular);

