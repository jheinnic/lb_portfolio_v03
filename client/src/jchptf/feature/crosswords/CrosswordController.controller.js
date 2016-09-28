(function() {
  'use strict';

  module.exports = CrosswordController;
  CrosswordController.$inject = ['$scope', 'initWorkbench'];

  function CrosswordController($scope, initWorkbench) {
    var vm = this;
    vm.initWorkbench = initWorkbench;

    $scope.on('$destroy', function cleanup() { });
  }
}).call(window);

