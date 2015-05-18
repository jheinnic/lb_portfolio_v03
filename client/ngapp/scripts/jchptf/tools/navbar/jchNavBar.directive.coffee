_ = require('lodash')

module.exports = jchNavBar
jchNavBar.$inject = ['JchNavData']

NavBarModel = require('./NavBarModel.model')
TabModel = require('./TabModel.model')

jchNavBar = (JchNavData) ->
  return {
    restrict: 'E'
    scope: { }
    templateUrl: 'views/jchptf/tools/navbar/jch_navbar.html'
    link: ($scope, $elem, $attr) ->
      updateModel = (data) ->
        data.nextPromise.then(updateModel)
        $scope.navDataModel = _.omit(data, 'nextPromise')

      updateModel(JchNavData.getNavBarModel())
  }
