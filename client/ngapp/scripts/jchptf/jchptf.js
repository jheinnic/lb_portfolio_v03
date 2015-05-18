(function() {
  'use strict';

  /**
   * @ngdoc overview
   * @name loopbackExampleFullStackApp
   * @description
   * # loopbackExampleFullStackApp
   *
   * Main module of the application, primarily tasked with branching between 
   * login page and the authenticated user's designated landing page.  Has no
   * routes of its own or pages of its own, but delegates to
   * 'jchptf.site.navigation' module, which in turn activates every feature
   * module to gather their routes.  Cross-cutting site service modules get
   * activated by the first feature encountered with a dependency on any of
   * their services.
   */
  angular.module('jchptf', [
    'ui.router', 'jchptf.context', 'jchptf.authenticate',
    'jchptf.site.notification', 'jchptf.site.navigation'
  ])
    .config(require('./app.configuration'))
    .run(require('./app.run'));

  module.exports = 'jchptf';
}).call(window);
