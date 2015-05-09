'use strict'

# Interim NodeJS/BrowserJS compatibility glue
if !exports
  require = (name) =>
    @jchptfModels
  if !@jchptfModels
    exports = @jchptfModels = {}
else
  require('coffee-script/registry')


Enum = require('../../enum').Enum

# angular.module('jchptf.context').constant('AuthTokenEventKind', AuthTokenEventKind)

class AuthTokenEventKind extends Enum
  constructor: (stateName, isLoggedInState) ->
    @isLoggedInState = isLoggedInState
    super(stateName)

  isLoggedIn: () => @isLoggedInState

new AuthTokenEventKind('NEW_TOKEN_IS_VALID', true)
new AuthTokenEventKind('TOKEN_WAS_REFRESHED', true)
new AuthTokenEventKind('TOKEN_MAY_EXPIRE', true)
new AuthTokenEventKind('TOKEN_HAS_EXPIRED', false)
new AuthTokenEventKind('TOKEN_WAS_REVOKED', false)
new AuthTokenEventKind('TOKEN_IS_INVALID', false)
new AuthTokenEventKind('NO_TOKEN_AVAILABLE', false)
new AuthTokenEventKind('INTERNAL_ERROR', false)
AuthTokenEventKind.finalize()

exports.AuthTokenEventKind = AuthTokenEventKind
