(function (angular) {
  'use strict';

  module.exports = 'jchptf.feature.homepage';

  /**
   * @ngdoc overview
   * @name jchptf.site.navigation
   * @description TODO
   */
  angular.module(
    module.exports,
    [
      'ng', 'ui.router',
      require('jchptf.site.authentication'),
      require('jchptf.site.navigation')
    ],
    require('./config')
  )
    // .run(require('./run'))
    .controller('HomeController', require('./HomeController.controller'))
  ;
}).call(window, window.angular);
