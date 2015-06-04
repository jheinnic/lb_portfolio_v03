(function() {
  'use strict';

  /**
   * @ngdoc overview
   * @name jchptf.crosswords.tickets
   * @description
   * A module encapsulating the artifact that provides an experience
   * supporting the collection of at-face ticket information against which
   * a subsequent result reporting artifact will be created to collect the
   * hidden information that yields the ticket's prize value.
   */
  angular.module(
    'jchptf.crosswords.tickets',
    [
      'ui.router',
      'drahak.hotkeys',
      'angularModalService',
      // require('jchptf.context'),
      // require('jchptf.authenticate'),
      // require('jchptf.modeling.core'),
      // require('jchptf.modeling.repository'),
      require('jchptf.tools.iconPanel'),
      require('jchptf.site.notification')
    ]
  )
    .controller('BonusWordModalController', require('./BonusWordModalController.controller'))
    .controller('TicketController', require('./TicketController.controller'))
    .service('OpenTicketCanvas', require('./OpenTicketCanvas.service.coffee'))
    .factory('XwTicketModelPackage', require('./XwTicketModelPackage.factory.coffee'));
}).call(window);
