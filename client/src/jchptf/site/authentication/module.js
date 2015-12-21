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
      'ng', 'ng-animate', 'angularModalService', 'localForage',
      require('jchptf.site.notification')
    ]
  )
    .config('./')
    .controller('LoginController', require('./LoginController.controller'))
    .directive('ptfLoginModal', require('./ptfLoginModal.directive'));
}).call(window, angular);
