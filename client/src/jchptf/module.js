(function(angular) {
  'use strict';

  module.exports = 'jchptf';

  console.log('Loading module definition for jchptf');

  /**
   * @ngdoc overview
   * @name loopbackExampleFullStackApp
   * @description
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
      'ui.router',
      require('jchptf.lbclient'),
      require('jchptf.site.authentication'),
      require('jchptf.site.navigation'),
      require('jchptf.feature.homepage'),
      require('jchptf.feature.crosswords')
    ], require('./router.config')
  )
    .run(require('./run'))
  ;
}).call(window, window.angular);
