(function(angular, jchutils) {
    'use strict';
    
    /**
     * @ngdoc overview
     * @name jchpft.app
     * @description
     * # jchPortfolioApp
     *
     * Main module of the author's portfolio application.
     */
    angular
      .module('jchpft.authenticate')
      .config(authenticateRoutes);
    
    authenticateRoutes.$inject=['$stateProvider'];

    function mainAppRoutes ($stateProvider) {
        $stateProvider
            .state('login', {
                templateUrl: '/views/jchpft/authenticate/loginForm.html',
                controller: 'LoginCtrl',
                controllerAs: 'login',
                abstract: true,
                authenticate: false
            }).state('login.showForm', {
                url: '/login/showForm',
                resolve: { 
                    appContextStatus: null,
                    loginKind: getRoutedToLoginKind()
                },
                abstract: false,
                authenticate: false
            }).state('login.onReturn', {
                url: '/login/onFail',
                resolve: { 
                    appContextStatus: checkAppContext()
                    loginKind: getWithAuthTokenLoginKind()
                },
                abstract: false,
                authenticate: false
            }.state('login.attemptFailed', {
                url: '/login/attemptFailed',
                resolve: {
                    appContextStatus: null,
                    loginKind: getBadCredentialsLoginKind()
                },
                abstract: false,
                authenticate: false
           }
        );
    }

    getLoginRequestedLoginKind.$inject = ['LOGIN_KIND_ENUM'];
    getWithAuthTokenLoginKind.$inject = ['LOGIN_KIND_ENUM'];
    getBadCredentialsLoginKind.$inject = ['LOGIN_KIND_ENUM'];

    checkAppContext.$inject = ['IdentityContextService'];
    
    function getLoginRequestedLoginKind(LOGIN_KIND_ENUM) {
        return LOGIN_KIND_ENUM.LOGIN_REQUESTED;
    }
    
    function getBadCredentialsLoginKind(LOGIN_KIND_ENUM) {
        return LOGIN_KIND_ENUM.BAD_CREDENTIALS;
    }
    
    function getWithAuthTokenLoginKind(LOGIN_KIND_ENUM) {
        return LOGIN_KIND_ENUM.WITH_AUTH_TOKEN;
    }
    
    function getInternalErrorLoginKind(LOGIN_KIND_ENUM) {
        return LOGIN_KIND_ENUM.INTERNAL_ERROR;
    }
    
    function getInvalidTokenLoginKind(LOGIN_KIND_ENUM) {
        return LOGIN_KIND_ENUM.TOKEN_NOT_VALID;
    }
    
    function getExpiredTokenLoginKind(LOGIN_KIND_ENUM) {
        return LOGIN_KIND_ENUM.TOKEN_EXPIRED;
    
    function checkAppContext(IdentityContextService) {
        return IdentityContextService.validateAuthToken();
    }
}(window.angular, window.jchutils));
