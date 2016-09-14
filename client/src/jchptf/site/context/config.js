(function () {
  'use strict';

  module.exports = userContextConfig;
  userContextConfig.$inject = ['$loginProvider', '$stateProvider'];

  var authCookieName = '__loginState';

  /**
   * @ngdoc method
   * @name jchptf.site.branding:config
   *
   * @description
   * Routing configuration for portfolio application's branding module.
   * Defines the landing page, /home.
   */
  function userContextConfig($loginProvider, $stateProvider) {
    $loginProvider
      .setDefaultLoggedInState('site.home')
      .setFallbackState('site.public.auth.login')
      .setAuthModule('AuthService')
      .setAuthClearMethod('clearAuthKey')
      .setAuthGetMethod('getAuthKey')
      .setCookieName(authCookieName);

    $stateProvider.state(
      'site.public.auth', {
        url: '/session',
        abstract: true
      }
    );
    $stateProvider.state(
      'site.public.auth.login', {
        url: '/login',
        params: {
          message: {
            default: null
          }
        }
      }
    );
    $stateProvider.state(
      'site.public.auth.process', {
        url: '/process',
        params: {
          status: {
            default: undefined
          },
          key: {
            default: null
          }
        },
        views: {
          'contextBar@defaultLayout': {
            templateUrl: 'jchptf/_noContextBar.view.html'
          },
          'sidePanel@defaultLayout': {
            templateUrl: 'jchptf/_blankArea.view.html'
          },
          'appCanvas@defaultLayout': {
            templateUrl: 'jchptf/_blankArea.view.html'
          }
        },
        onEnter: ['$stateParams', '$login', 'AuthService', function($stateParams, $login, AuthService) {
          var status = $stateParams.status;
          if (status === 'OK') {
            AuthService.setAuthKey($stateParams.key);
            $login.getLoginRedirect();
          } else {
            $login.logout('site.public.authent.login({message: "' + status + '"})');
          }
        }]
      }
    );
    $stateProvider.state(
      'site.public.auth.pass', {
        url: '/pass'
      }
    );
    $stateProvider.state(
      'site.public.auth.fail', {
        url: '/fail'
      }
    );
    $stateProvider.state(
      'site.public.auth.logout', {
        url: '/logout'
      }
    );
  }
}).call(window);
