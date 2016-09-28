//(function(module, console) {
//  'use strict';
//
//  module.exports = runNavigation;
//
//  console.log( 'Loading jchptf.site.navigation run');
//
//  runNavigation.$inject=['$state', 'JchNavData'];
//
//  function runNavigation ($state, JchNavData) {
//    console.log( 'Running jchptf.site.navigation run');
//    // var latestEvent = IdentityContext.getAuthTokenStatus();
//
//    JchNavData.changeNavBarModel( function( builder ) {
//      builder.brandName('John Heinnickel')
//        .appendStateTab('Home', 'site.home');
//    });
//
//    // var eventType = latestEvent.getEventType();
//    var eventType = true;
//    if (eventType) {
//      console.log('Go home');
//      $state.go('site.home', {reload: false});
//    } else {
//      // $state.go('loginForm.showForm', {reload: false});
//      $state.go('site.public.authenticate.login', {reload: false});
//    }
//  }
//}).call(window, module, console);
