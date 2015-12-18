(function() {
  'use strict';

  module.exports = runNavigation;

  console.log( 'Loading jchptf.site.navigation run');
  // require('jchptf').run(portfolioAppLauncher);
  // angular.module('jchptf').run(portfolioAppLauncher);

  runNavigation.$inject=['$state'];

  function runNavigation ($state) {
    console.log( 'Running jchptf.site.navigation run');
    // var latestEvent = IdentityContext.getAuthTokenStatus();

    // var eventType = latestEvent.getEventType();
    var eventType = true;
    if (eventType) {
      console.log('Go home');
      $state.go('home', {reload: false});
    } else {
      // $state.go('loginForm.showForm', {reload: false});
      $state.go('loginForm', {reload: false});
    }
  }

}).call(window);
