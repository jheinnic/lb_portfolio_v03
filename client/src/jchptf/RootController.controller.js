(function() {
  'use strict';

  module.exports = RootController;
  RootController.$inject = ['$scope', '$state', '$injector'];

  var window = this;

  function RootController($scope, $state, $injector) {
    this.state = $state;
    window.$state = $state;
    window.$injector = $injector;
    $scope.state = $state;
  }
}).call(window);

