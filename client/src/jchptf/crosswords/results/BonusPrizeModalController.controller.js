(function() {
  'use strict';

  module.exports = BonusPrizeModalController;

  BonusPrizeModalController.$inject = ['OpenResultCanvas'];

  function BonusPrizeModalController(OpenResultCanvas) {
    this.openResultCanvas = OpenResultCanvas;
  }
}).call(window);

