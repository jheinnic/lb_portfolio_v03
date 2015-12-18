(function (angular) {
  'use strict';

  module.exports = 'jchptf.site.navigation';

  console.log('jchptf.site.navigation module definition');

  /**
   * @ngdoc overview
   * @name jchptf.site.navigation
   * @description TODO
   */
  angular.module(
    module.exports,
    ['ng', 'ui.router'],
    require('./config')
  )
    .run(require('./run'))
    .controller('HomeController', require('./HomeController.controller'))
  ;
}).call(window, angular);
