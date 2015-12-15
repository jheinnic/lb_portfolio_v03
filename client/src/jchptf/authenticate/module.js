(function(angular) {
  'use strict';

  module.exports = 'jchptf.authenticate';

  /**
   * @ngdoc overview
   * @name jchptf.authenticate
   *
   * @description
   * Portfolio application's authentication module.
   */
  angular.module(
    module.exports,
    ['ng', 'ng-animate', 'angularModalService', 'toastr', require('jchptf.context')],
    require('./config')
  )
    .controller('LoginController', require('./LoginController.controller'))
    .directive('ptfLoginModal', require('./ptfLoginModal.directive'));
}).call(window, angular);