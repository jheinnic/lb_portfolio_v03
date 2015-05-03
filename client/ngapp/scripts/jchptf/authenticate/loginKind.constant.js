(function(angular, jchutils) {
    "use strict";

    angular.module('jchpft.authenitcate')
        .constant(LOGIN_KIND_ENUM, new LoginKindEnum());

    function LoginKindEnum{
        jchutils.addEnumConstants(
            this,
            [
                'LOGIN_REQUESTED',
                'BAD_CREDENTIALS',
                'WITH_AUTH_TOKEN',
                'TOKEN_EXPIRED',
                'TOKEN_NOT_VALID',
                'INTERNAL_ERROR'
           ]
        );
)(window.angular, window.jchutils));
