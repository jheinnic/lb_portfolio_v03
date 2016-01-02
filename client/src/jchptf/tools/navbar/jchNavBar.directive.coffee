'use strict'

module.exports = [
  'JchNavData', (JchNavData) ->
    _ = require('lodash')

    return(
      restrict: 'E'
      scope: {}
      templateUrl: 'jchptf/tools/navbar/jch_navbar.html'
      link: ($scope) ->
        updateModel = (data) ->
          console.log(data)

          # Deep clone the immutable data object so ng-repeat can iterate over its tabs.
          $scope.navDataModel = _.omit(
            _.cloneDeep(data), 'refreshPromise'
          );
          data.refreshPromise.then(updateModel)

        updateModel(JchNavData.getNavBarModel())
    )
]
