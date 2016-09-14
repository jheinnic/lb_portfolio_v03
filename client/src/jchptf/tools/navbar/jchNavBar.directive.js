(function() {
  'use strict';

  module.exports = [
    'JchNavData',
    '$state',
    function jchNavBar(JchNavData, $state) {
      return {
        scope: {},
        restrict: 'E',
        templateUrl: 'jchptf/tools/navbar/_jch_navbar.view.html',
        link: function link($scope) {
          console.log('navbar directive');
          $scope.routerState = $state;
          return updateModel($scope, JchNavData.getNavBarModel());
        }
      };
    }
  ];

  var _ = require('lodash');

  function updateModel($scope, data) {
    console.log(data);
    $scope.navDataModel =
      _.omit(
        _.cloneDeep(data), 'refreshPromise');
    data.refreshPromise.then(
      _.partial(updateModel, $scope));
  }
}).call(window);
