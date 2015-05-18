(function() {
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
    'jchptf.authenticate',
    ['ng', 'ng-animate', 'angularModalService', 'toastr', require('jchptf.context')]
  )
    .config(require('./authenticate.config'))
    .controller('LoginController', require('./LoginController.controller'))
    .directive('ptfLoginModal', require('./ptfLoginModal.directive'));
}).call(window);
