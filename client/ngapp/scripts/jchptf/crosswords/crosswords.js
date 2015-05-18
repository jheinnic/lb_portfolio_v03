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
      require('jchptf.context'),
      require('jchptf.repository'),
      require('jchptf.authenticate'),
      require('jchptf.modeling.core'),
      require('jchptf.modeling.document'),
      require('jchptf.site.notification'),
      require('jchptf.crosswords.tickets'),
      require('jchptf.crosswords.results')
    ]
  )
    .config(require('./crosswords.config'))
    .service('XWInventoryCanvas', require('./XWInventoryCanvas.service'))
    .controller('XWInventoryController', require('./XWInventoryController.controller'));
}).call(window);
