(function() {
  'use strict';

  module.exports = ResultController;

  ResultController.$inject = ['$scope', 'PrizeCalculator'];

  function ResultController($scope, PrizeCalculator) {
    $scope.openResultCanvas = {results: 'fancy'};
    $scope.prizeCalculator = PrizeCalculator;
  }
}).call(window);

