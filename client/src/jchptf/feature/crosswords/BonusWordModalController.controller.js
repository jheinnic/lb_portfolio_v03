(function() {
  'use strict';

  module.exports = BonusWordModalController;

  BonusWordModalController.$inject = ['OpenTicketCanvas'];

  function BonusWordModalController($scope, OpenTicketCanvas) {
    this.activeTicketModel = OpenTicketCanvas;
    if ($scope === this.activeTicketModel) {
      return;
    }
  }
}).call(window);

