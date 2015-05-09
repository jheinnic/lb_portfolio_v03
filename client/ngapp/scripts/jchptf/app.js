(function(angular) {
  'use strict';

  /**
   * @ngdoc overview
   * @name loopbackExampleFullStackApp
   * @description
   * # loopbackExampleFullStackApp
   *
   * Main module of the application, primarily tasked with branching between the login page and the authenticated
   * user's designated landing page.  Has no routes of its own or pages of its own, but delegates to the central
   * 'site' and 'user' modules, effectively triggering the bootstrap of application semantics.
   */
  angular
    .module('jchptf.app', [
      'ui.router', 'jchptf.context', 'jchptf.authenticate', 'jchptf.site.notification', 'jchptf.site.navigation'
    ]);
}(window.angular));
