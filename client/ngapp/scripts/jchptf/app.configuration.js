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

    portfolioAppLauncher.$inject=['IdentityContext', 'AuthTokenEventKind', '$state'];

    function portfolioAppLauncher (IdentityContext, AuthTokenEventKind, $state) {
        var latestEvent = IdentityContext.getAuthTokenStatus();

        var eventType = latestEvent.getEventType();
        if (eventType.isLoggedIn()) {
            $state.go('home', {reload: false});
        } else {
            // $state.go('loginForm.showForm', {reload: false});
            $state.go('loginForm', {reload: false});
        }

    }
}(window, window.angular));
