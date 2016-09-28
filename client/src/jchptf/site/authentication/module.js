(function (angular) {
  'use strict';

  module.exports = 'jchptf.site.authentication';

  /**
   * @ngdoc overview
   * @name jchptf.site.context
   * @description TODO
   */
  angular.module(
    module.exports,
    [ 'ng', 'ui.router.login', 'ngMaterial' ],
    require('./config')
  )
    .factory('SessionEventType', require('./SessionEventType.factory'))
    .factory('AuthService', require('./AuthService.factory'))
    .controller('LoginFormController', require('./LoginFormController.controller'))
    .run(require('./run'))
  ;
}).call(window, window.angular);
