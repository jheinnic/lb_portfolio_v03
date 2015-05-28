_ = require('lodash')

module.exports = jchNavBar
jchNavBar.$inject = ['JchNavData', 'NavBarModelPackage']

jchNavBar = (JchNavData, NavBarModelPackage) ->
  {NavBarModel, TabModel} = NavBarModelPackage

  return {
    restrict: 'E'
    scope: { }
    templateUrl: 'views/jchptf/tools/navbar/jch_navbar.html'
    link: ($scope) ->
      updateModel = (data) ->
        data.nextPromise.then(updateModel)
        $scope.navDataModel = _.omit(data, 'nextPromise')

      updateModel(JchNavData.getNavBarModel())
  }
