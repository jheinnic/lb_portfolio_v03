'use strict'

module.exports = [
  'JchNavData',
  (JchNavData) ->
    _ = require('lodash')

    return {
      restrict: 'E'
      scope: { }
      templateUrl: 'views/jchptf/tools/navbar/jch_navbar.html'
      link: ($scope) ->
        updateModel = (data) ->
          console.log(data);
          data.refreshPromise.then(updateModel)
          $scope.navDataModel = _.omit(data, 'refreshPromise')

        updateModel(JchNavData.getNavBarModel())
    }
]
