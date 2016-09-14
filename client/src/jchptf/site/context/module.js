(function (angular) {
  'use strict';

  module.exports = 'jchptf.site.context';

  /**
   * @ngdoc overview
   * @name jchptf.site.context
   * @description TODO
   */
  angular.module(
    module.exports,
    [ 'ng', 'ui.router.login' ],
    require('./config')
  )
    .factory('AuthService', require('./AuthService.factory'))
    .controller('LoginFormController', require('./LoginFormController.controller'))
    .run(require('./run'))
  ;
}).call(window, angular);
