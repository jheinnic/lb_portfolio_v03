(function() {
    'use strict';

    angular.module('jchptf.authenticate').controller('LoginController', LoginController);

    LoginController.$inject =
        ['$state', 'IdentityContext', 'authTokenStatus', 'loginKind', 'LoginResultKind', 'AuthTokenEventKind'];

    function LoginController(
        IdentityContext, $state, authTokenStatus, loginKind, LoginResultKind, AuthTokenEventKind
    ) {
        // First check for a reply from the identity context service.  If that
        // confirms that the user is not logged in, check to see if they came
        // back with a potentially valid token that must be checked before
        // proceeding, routing the user back into the login workflow if not.
        //
        // Finally, all other states are specific known cases where this page
        // form is the right place to be.  Those states only differ by the
        // kind of feedback the user is given.

        var tokenEventType = authTokenStatus.eventType;
        if (tokenEventType.isLoggedIn()) {
            $state.go('xw', {reload: false});
        } else if (tokenEventType !== AuthTokenEventKind.NO_TOKEN_AVAILABLE) {
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
            futureWork();
        }

        // Form behavior itself is TBD!
        function futureWork() {
            return IdentityContext.getAuthTokenStatus() === AuthTokenEventKind.NEW_TOKEN_IS_VALID;
        }
    }
}).call(window);

