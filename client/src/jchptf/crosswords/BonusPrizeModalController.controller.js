(function(angular) {
  'use strict';

  module.exports = BonusPrizeModalController;
  BonusPrizeModalController.$inject = ['$scope', 'OpenResultCanvas'];

  var openResultCanvas;

  function BonusPrizeModalController($scope, OpenResultCanvas) {
    openResultCanvas = OpenResultCanvas;
    if ($scope === openResultCanvas) {
      return;
    }
  }

  angular.extend(
    BonusPrizeModalController.prototype, {}
  );
}).call(window, window.angular);
