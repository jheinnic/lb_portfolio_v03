(function (angular) {
  'use strict';

  module.exports = 'jchptf.site.navigation';

  /**
   * @ngdoc overview
   * @name jchptf.site.navigation
   * @description TODO
   */
  angular.module(
    module.exports,
    [],
    require('./config')
  )
    .controller('HomeController', require('./HomeController.controller'))
  ;
}).call(window.angular);
