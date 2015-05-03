(function(angular, jchutils) {
    "use strict";

    angular.module('jchpft.authenitcate')
        .constant(AUTH_TOKEN_EVENT_TYPE, new LoginKindEnum());

    function LoginKindEnum{
        jchutils.addEnumConstants(
            this,
            [
                'NEW_TOKEN_IS_VALID',
                'TOKEN_WAS_REFRESHED',
                'TOKEN_MAY_EXPIRE',
                'TOKEN_HAS_EXPIRED',
                'TOKEN_WAS_REVOKED',
                'TOKEN_LOGGED_OUT',
                'INTERNAL_ERROR'
           ]
        );
)(window.angular, window.jchutils));
