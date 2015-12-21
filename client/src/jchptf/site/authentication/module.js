(function(angular) {
  'use strict';

  module.exports = 'jchptf.site.authenticate';

  /**
   * @ngdoc overview
   * @name jchptf.authenticate
   *
   * @description
   * Portfolio application's authentication module.
   */
  angular.module(
    module.exports,
    [
      'ng', 'ng-animate', 'angularModalService', 'toastr',
      require('jchptf.site.notification')
    ]
  )
    .config(require('./states.config'))
    .config(require('./checkIdentity.config'))
    .factory('IdentityCheckResult', require('./IdentityCheckResult.factory'))
    .provider('UserIdentityServiceProvider', require('./UserIdentityServiceProvider.provider'))
    .controller('LoginController', require('./LoginController.controller'))
    .directive('ptfLoginModal', require('./ptfLoginModal.directive'));
}).call(window, angular);
