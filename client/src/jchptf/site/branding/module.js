(function (angular) {
  'use strict';

  module.exports = 'jchptf.site.branding';

  /**
   * @ngdoc overview
   * @name jchptf.site.navigation
   * @description TODO
   */
  angular.module(
    module.exports,
    [
      'ui.router',
      /*
      require('jchptf.crosswords'),
      require('jchptf.context'),
      require('jchptf.authenticate'),
      */
      require('jchptf.site.notification'),
      require('jchptf.tools.navbar')
    ],
    require('./config')
  )
  ;
}).call(window, angular);
