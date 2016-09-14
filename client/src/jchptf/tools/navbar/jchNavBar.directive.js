(function() {
  'use strict';

  module.exports = [
    'JchNavData',
    function(JchNavData) {
      var _ = require('lodash');

      return {
        scope: {},
        restrict: 'E',
        templateUrl: 'jchptf/tools/navbar/_jch_navbar.view.html',
        link: function link($scope) {
          console.log('navbar directive');

          function updateModel(data) {
            console.log(data);
            $scope.navDataModel =
              _.omit(
                _.cloneDeep(data), 'refreshPromise');
            data.refreshPromise.then(updateModel);
          }

          return updateModel(JchNavData.getNavBarModel());
        }
      };
    }
  ];
}).call(window);
