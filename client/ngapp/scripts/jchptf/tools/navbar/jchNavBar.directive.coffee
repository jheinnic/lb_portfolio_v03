_ = require('lodash')

module.exports = jchNavBar
jchNavBar.$inject = ['JchNavData']

jchNavBar = (JchNavData) ->
  return {
    restrict: 'E'
    scope: true
    templateUrl: 'views/jchptf/tools/navbar/jch_nav_bar.html'
    link: ($scope) ->
      updateModel = (data) ->
        data.nextPromise.then(updateModel)
        $scope.navDataModel = _.omit(data, 'nextPromise')

      updateModel(JchNavData.getDataModel())
