(function () {
  'use strict';

  module.exports = 'jchptf.crosswords';

  /**
   * @ngdoc overview
   * @name jchptf.crosswords
   * @description
   *
   * Root module for the "Crosswords" feature set.
   */
  angular.module(
    'jchptf.crosswords',
    [
      'cgnotify',
      'ui.router',
      'drahak.hotkeys',
      'tree.control',
      require('jchptf.context'),
      require('jchptf.authenticate'),
      require('jchptf.site.notification')
    ]
  )
    .config(require('./browse.config'))
    .service('XWInventoryCanvas', require('./XWInventoryCanvas.service'))
    .controller('XWInventoryController', require('./XWInventoryController.controller'));
}).call(window);
