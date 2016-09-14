(function () {
  'use strict';

  module.exports = runLoginInterceptor;
  runLoginInterceptor.$inject = ['$rootScope', 'ModalService', '$state', '$login', 'ipCookie'];

  var _ = require('lodash');
  var AUTH_COOKIE_NAME = '__loginState';
  var AUTH_COOKIE_OPTIONS = {path: '/'};

  function runLoginInterceptor($rootScope, ModalService, $state, $login, ipCookie ) {
    $rootScope.$on('$stateChangeStart', function onStateChangeStart(event, toState, toParams) {
      var propVal;
      var requireAuth = false;
      var saveCurrentState = true;

      try {
        propVal = toState.data.requireAuth;
        if (_.isBoolean(propVal)) {
          requireAuth = propVal;
        }
      } catch (err) {
        /* Default to false if requireAuth was not defined */
      }

      try {
        propVal = toState.data.saveCurrentState;
        if (_.isBoolean(propVal)) {
          saveCurrentState = propVal;
        }
      } catch (err) {
        /* Default to false if requireAuth was not defined */
      }

      if (requireAuth && ($login.isLoggedIn() === false)) {
        /* We need to be logged in but we're not - save current destination and detour for Auth.  */
        /* I cannot use the $login method here because it calls state.go() unconditionally */
        // $login.checkLoggedIn(toState.name, toParams, toState.data.saveState);
        if (saveCurrentState === true) {
          var cookieValue = {
            state: toState.data.saveState,
            params: toParams
          };
          ipCookie(AUTH_COOKIE_NAME, cookieValue, AUTH_COOKIE_OPTIONS);
        }
        event.preventDefault();

        // put up the login modal
        ModalService.showModal({
          templateUrl: 'jchptf/site/context/_loginForm.modal.html',
          controller: 'LoginFormController as vm',
          inputs: {
            title: 'Authentication Required'
          }
        }).then(
          function (modal) {
            modal.element.modal();
            modal.close.then(
              function (result) {
                // A real implementation will redirect user to authenticate with OAuth provider, and receive
                // a token on return via an inbound web hook.
                $state.go('site.public.authent.process', {status: result.status, key: result.key});
              }
            ).catch(
              function (error) {
                $login.logout('site.public.authent.login({message: "' + error + '"})');
              }
            );
          }
        );
      }
    });
  }
}).call(window);
