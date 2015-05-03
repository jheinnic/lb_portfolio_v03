(function(angular) {
    'use strict';

    angular.module('jchptf.authenticate').controller("LoginController", LoginController);

    LoginController.$inject =
        ['$state', 'IdentityContext', 'authTokenStatus', 'loginKind', 'LOGIN_KIND_ENUM', 'AUTH_TOKEN_EVENT_TYPE'];

    function LoginController(
        IdentityContext, $state, authTokenStatus, loginKind, LOGIN_KIND_ENUM, AUTH_TOKEN_EVENT_TYPE
    ) {
        // First check for a reply from the identity context service.  If that
        // confirms that the user is not logged in, check to see if they came
        // back with a potentially valid token that must be checked before
        // proceeding, routing the user back into the login workflow if not.
        //
        // Finally, all other states are specific known cases where this page
        // form is the right place to be.  Those states only differ by the
        // kind of feedback the user is given.

        if (angular.isUndefined(authTokenStatus)) {
            $state.go('xw', {reload: false});
        } else if (loginKind === LOGIN_KIND_ENUM.WITH_AUTH_TOKEN) {
            $state.go('^.attemptFailed', {reload: false});
        } else {
            // The remaining options stay on the login form and only differ
            // in terms of the initial feedback state of the form:
            //
            // ** please login
            //    -OR-
            // ** bad credentials, try again
            //    -OR-
            // ** server error, try again later
            //    -OR-
            // your session has expired, please login to continue
        }

        // Form behavior itself is TBD!
        function futureWork() {
            return IdentityContext.getAuthTokenStatus() == AUTH_TOKEN_EVENT_TYPE;
        }
    }
}(window.angular));

