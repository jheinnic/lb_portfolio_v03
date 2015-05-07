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
    ['ui.router', 'jchptf.context', 'jchptf.authenticate', 'jchptf.site.notification']
  );
}).call(window);
