(function (angular) {
  'use strict';

  module.exports = 'jchptf.crosswords.browse';

  /**
   * @ngdoc overview
   * @name jchptf.crosswords
   * @description
   *
   * Root module for the "Crosswords" feature set.
   */
  angular.module(
    module.exports,
    [
      'ui.router', 'cgnotify', 'drahak.hotkeys', 'tree.control',
      /*require('jchptf.context'),
      require('jchptf.authenticate'),
      require('jchptf.site.navigation'),*/
      require('jchptf.site.notification')
    ],
    require('./config')
  )
    .service('XWInventoryCanvas', require('./XWInventoryCanvas.service'))
    .controller('XWInventoryController', require('./XWInventoryController.controller'))
  ;
}).call(window, angular);
