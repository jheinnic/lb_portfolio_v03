(function () {
  'use strict';

  module.exports = 'jchptf.site.navigation';

  /**
   * @ngdoc overview
   * @name jchptf.site.navigation
   * @description
   *
   * TODO
   */
  angular.module(
    'jchptf.site.navigation',
    [
      'ui.router',
      require('jchptf.crosswords'),
      require('jchptf.context'),
      require('jchptf.authenticate'),
      require('jchptf.site.notification'),
      require('jchptf.tools.navbar')
    ]
  )
    .config(require('./navigation.config'))
    .controller('HomeController', require('./HomeController.controller'));
}).call(window);
