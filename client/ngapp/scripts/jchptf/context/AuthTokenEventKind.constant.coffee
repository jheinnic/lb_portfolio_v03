'use strict'

angular.module('jchptf.context').constant('AuthTokenEventKind', AuthTokenEventKind)

# require('coffee-script/registry')
Enum = require('../../enum')

class AuthTokenEventKind extends Enum
  constructor: (stateName, isActiveState) ->
    super(stateName)
    @isLoggedInState = isLoggedInState

  isLoggedIn: () -> return @isLoggedInState

new AuthTokenEventKind('NEW_TOKEN_IS_VALID', true);
new AuthTokenEventKind('TOKEN_WAS_REFRESHED', true);
new AuthTokenEventKind('TOKEN_MAY_EXPIRE', true);
new AuthTokenEventKind('TOKEN_HAS_EXPIRED', false);
new AuthTokenEventKind('TOKEN_WAS_REVOKED', false);
new AuthTokenEventKind('TOKEN_IS_INVALID', false);
new AuthTokenEventKind('NO_TOKEN_AVAILABLE', false);
new AuthTokenEventKind('INTERNAL_ERROR', false);
AuthTokenEventKind.finalize();
