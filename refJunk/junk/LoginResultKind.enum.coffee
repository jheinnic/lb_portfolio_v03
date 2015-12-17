'use strict';

this.angular.module('jchptf.authenticate').constant('LoginResultKind', LoginResultKind);

Enum = require('../../enum')
class LoginKind extends Enum

new LoginResultKind('LOGIN_REQUESTED')
new LoginResultKind('BAD_CREDENTIALS')
new LoginResultKind('WITH_AUTH_TOKEN')
new LoginResultKind('TOKEN_EXPIRED')
new LoginResultKind('TOKEN_NOT_VALID')
new LoginResultKind('INTERNAL_ERROR')

LoginResultKind.finalize();
