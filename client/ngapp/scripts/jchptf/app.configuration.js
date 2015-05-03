(function(angular) {
    'use strict';

    /**
     * @ngdoc overview
     * @name jchpft.app
     * @description
     * # jchPortfolioApp
     *
     * Main module of the author's portfolio application.  Responsible for establishing global
     * configuration and bootstrapping the application's entry point for its run() method, which
     * Angular executes once after all modules loading has completed.
     */
    angular.module('jchptf.main').run(portfolioAppLauncher);

    portfolioAppLauncher.$inject=['IdentityContext', 'AUTH_TOKEN_EVENT_TYPE', '$state'];

    function portfolioAppLauncher (IdentityContext, AUTH_TOKEN_EVENT_TYPE, $state) {
        var latestEvent = IdentityContext.getAuthTokenStatus();

        var eventType = latestEvent.getEventType();
        if (true
            // (eventType == AUTH_TOKEN_EVENT_TYPE.NEW_VALID_LOGIN) ||
            // (eventType == AUTH_TOKEN_EVENT_TYPE.TOKEN_WAS_REFRESHED)
        ) {
            $state.go('home', {reload: false});
        } else {
            // $state.go('loginForm.showForm', {reload: false});
            $state.go('loginForm', {reload: false});
        }

    }
}(window, window.angular));
