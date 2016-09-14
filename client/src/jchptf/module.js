(function(angular) {
  'use strict';

  module.exports = 'jchptf';

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
  angular.module(
    module.exports,
    [
      /* require('jchptf.context'),
       'ng',
       require('jchptf.authenticate'),*/
      'ui.router',
      require('jchptf.site.notification'),
      require('jchptf.site.navigation'),
      require('jchptf.crosswords'),
      require('jchptf.lbclient')
    ]
  )
    .config(require('./router.config'))
    .config(require('./layout.config'))
    .run(require('./run'))
  ;
}).call(window, angular);
