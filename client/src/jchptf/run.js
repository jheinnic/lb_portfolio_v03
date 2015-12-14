(function() {
  'use strict';

  module.exports = portfolioAppLauncher;

  // require('jchptf').run(portfolioAppLauncher);
  // angular.module('jchptf').run(portfolioAppLauncher);

  portfolioAppLauncher.$inject=['$rootScope', '$state'];

  function portfolioAppLauncher ($rootScope, $state) {
    // var latestEvent = IdentityContext.getAuthTokenStatus();

    // var eventType = latestEvent.getEventType();
    var eventType = true;
    if (eventType) {
      $state.go('home', {reload: false});
    } else {
      // $state.go('loginForm.showForm', {reload: false});
      $state.go('loginForm', {reload: false});
    }
  }

}).call(window);
