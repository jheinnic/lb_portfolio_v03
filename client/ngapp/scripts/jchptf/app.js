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
    .module('jchptf.main', [
      'ui.router', 'ptf.context', 'ptf.authentication', 'ptf.notifyUser', 'ptf.site.navigation', 'ptf.site.home'
    ]);
}(window.angular));
