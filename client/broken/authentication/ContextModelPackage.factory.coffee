'use strict'

module.exports = ['CoreModelPackage', (CoreModelPackage) ->
  Enum = CoreModelPackage.Enum

  class AuthTokenEventKind extends Enum
    constructor: (stateName, isLoggedInState) ->
      @isLoggedInState = isLoggedInState
      super(stateName)

    isLoggedIn: () -> @isLoggedInState


  new AuthTokenEventKind('NEW_TOKEN_IS_VALID', true)
  new AuthTokenEventKind('TOKEN_WAS_REFRESHED', true)
  new AuthTokenEventKind('TOKEN_MAY_EXPIRE', true)
  new AuthTokenEventKind('TOKEN_HAS_EXPIRED', false)
  new AuthTokenEventKind('TOKEN_WAS_REVOKED', false)
  new AuthTokenEventKind('TOKEN_IS_INVALID', false)
  new AuthTokenEventKind('NO_TOKEN_AVAILABLE', false)
  new AuthTokenEventKind('INTERNAL_ERROR', false)
  AuthTokenEventKind.finalize()

  ###
  # TODO: Try to do token attachment to requests by way of a before
  #       advising interceptor, transparently to the calling service,
  #       negating any need to expose the AUTH_TOKEN via service API.
  ###

  ###*
  # An immutable object used to communicate user session status state changes.  Constructed by
  # IdentityContext service for use as a value object.
  #.
  # @type {AuthTokenEvent}
  # @param eventProps An object with key values matching this class's property names to assign values.
  # @constructor
  ###
  class AuthTokenEvent
    constructor: (params) ->
      @uuid = params.uuid
      @loginId = params.loginId
      @authToken = params.authToken
      @displayName = params.displayName
      @tokenExpiration = params.tokenExpiration
      @tokenTimeout = params.tokenTimeout
      @nextPromise = params.nextPromise
      @eventType = params.eventType
      # { @eventType, @uuid, @displayName, @loginId, @tokenExpiration, @tokenTimeout, @authToken, @nextPromise } = params

      Object.seal(this)

    isLoggedIn: -> @eventType.isLoggedIn()


  ###*
  # @type ContextModelPackage
  ###
  return { AuthTokenEvent: AuthTokenEvent,
  AuthTokenEventKind: AuthTokenEventKind }
]
