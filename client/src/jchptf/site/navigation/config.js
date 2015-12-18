(function() {
  'use strict';

  module.exports = siteNavigationConfig;
  siteNavigationConfig.$inject=['$stateProvider'];

  console.log('Loading site config');

  /**
   * @ngdoc method
   * @name jchptf.site.navigation.siteNavigation:config
   *
   * @description
   * Routing configuration for portfolio application's siteNavigation module.
   * Defines the landing page, /home.
   */
  function siteNavigationConfig ($stateProvider) {
    console.log('Running site config');
    console.log('Defining home');
    $stateProvider.state(
      'home',
      {
        url: '/home',
        templateUrl: 'jchptf/site/navigation/_home.view.html',
        controller: 'HomeController',
        controllerAs: 'home',
        abstract: false,
        authenticate: true
      }
    );
  }

    /*
            $stateProvider
                .state('login', {
                    templateUrl: '/views/jchptf/authenticate/loginForm.html',
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
    */
}).call(window);
