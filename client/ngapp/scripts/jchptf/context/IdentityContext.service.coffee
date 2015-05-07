'use strict';

# /**
#  * @ngdoc overview
#  * @name jchpft.context
#  * @description
#  * Module for maintaining "global" application state that needs to be able
#  * to survive UI view changes and remain available for the duration of a
#  * user's interaction time with the application.
#  *
#  * There are two known contexts at this time:
#  * <ol>
#  * <li>A user's authenticated login state, credential token, and
#  * the websocket session through which they receive pushed notifications
#  * from the server and establish a presence in the cluster.</li>
#  * <li>Project catalog and impact analysis metadata, synchronized with
#  *  the backend to avoid the cost of redundant retrieval and also to
#  *  inform users about unresolved change propagation requirements.</li>
#  * </ol>
#  */
angular.module('jchptf.context')
    .factory('IdentityContext', IdentityContext)

IdentityContext.$inject = ['$q', 'AuthTokenEventKind']

class IdentityContext
  constructor: ($q, AuthTokenEventKind) ->
    @identityContextInternal = new IdentityContextInternals($q, AuthTokenEventKind)
    @tokenEventListeners = {}

  #/**
  # * @method
  # *
  # * Unlike its stronger sibling, validateAuthTokenStatus(), this method
  # * just returns cached auth token state.  It does not look for an
  # * AUTH_TOKEN or attempt to decode it.  IF you have no reason to believe
  # * user has just returned with an AUTH_TOKEN, it is still more appropriate
  # * to use this method than validateAuthTokenStatus().
  # *
  # * Also note that getAuthTokenStatus() is unable to fire events due to
  # * state changes, which is because it is returning old state about the
  # * most recent known state change.
  # */
  getLatestTokenEvent: () ->
    return @identityContextInternals.latestTokenEvent;

  verifyAuthTokenStatus: () ->
    return @identityContextInternals.verifyAuthTokenStatus();

  getNextTokenEventPromise: () ->
    return @identityContextInternals.nextEventDefer.promise

  addTokenEventListener: (eventHandler) ->
    if @tokenEventListeners[eventHandler]
      throw new Error 'Cannot register an event handler twice'
    else
      regControl =
        isEnabled: true
      wrappedListener = (event) ->
        if regControl.isEnabled
          try
            eventHandler(event)
          catch e
            # TODO: Error handling
            console.log(e)

          eventType.nextPromise.then(wrappedListener)
      @tokenEventListeners[eventHandler] = regControl

  dropTokenEventListener: (eventHandler) ->
    regControl = @tokenEventListener[eventHandler]
    if regControl
      regControl.isEnabled = false
      delete @tokenEventListener[eventHandler]
    else
      throw new Error 'Cannot de-register an unregistered event handler'

class IdentityContextInternals

  constructor: ($q, _AuthTokenEventKind) ->
    # TODO: Add JWT token handling utilities, and tools for accepting
    #       delegated authentication tokens (e.g. OAuth2)
    @AuthTokenEventKind = _AuthTokenEventKind
    @promiseService = $q
    @nextEventDefer = $q.defer
    @latestTokenEvent = new AuthTokenEvent
      eventType: _AuthTokenEventKind.NO_TOKEN_AVAILABLE
      nextPromise: @nextEventDefer.promise

  decodeAuthToken: (authToken) ->
    # TODO: Add JWT token decoder
    # TODO: Add a current timestamp to the token deltas and set timers
    return new AuthTokenEvent
      uuid: 'fakeUuid',
      loginId: 'jheinnic'
      displayName: 'John'
      tokenExpiration: 3600000  # one hour
      tokenTimeout: 600000      # 10 minutes
      authToken: authToken
      eventType: @AuthTokenEventKind.NEW_TOKEN_IS_VALID

  isUserLoggedIn: () ->
    return @latestTokenEvent.eventType.isLoggedIn()

  verifyAuthTokenStatus: () ->
    retVal = @latestTokenEvent

    # TODO: Get a real token from the cookie environment
    authToken = "mockToken"

    if @isUserLoggedIn()
      if authToken && authToken != @latestTokenEvent.authToken
        # TODO: Check validity of new token
        retVal = @decodeAuthToken authToken

        if retVal.eventType == @AuthTokenEventKind.TOKEN_WAS_REFRESHED
          @fireTokenEvent retVal
        else
          logoutTokenEvent = new AuthTokenEvent
            eventType: _AuthTokenEventKind.NO_TOKEN_AVAILABLE
          @fireTokenEvent logoutTokenEvent
          @fireTokenEvent retVal
    else if authToken
      retVal = @decodeAuthToken authToken
      @fireTokenEvent retVal

  fireTokenEvent: (latestEvent) ->
    @latestTokenEvent = latestEvent

    promiseToFire = @nextEventDefer.promise
    @nextEventDefer = @promiseService.defer

    latestEvent.nextPromise = @nextEventDefer.nextPromise
    promiseToFire.resolve latestEvent

  logout: () ->
    if @isUserLoggedIn()
      logoutTokenEvent = new AuthTokenEvent
        eventType: _AuthTokenEventKind.NO_TOKEN_AVAILABLE
      @fireTokenEvent logoutTokenEvent


# TODO: Try to do token attachment to requests by way of a before
#       advising interceptor, transparently to the calling service,
#       negating any need to expose the AUTH_TOKEN via service API.
class AuthTokenEvent
  constructor: (eventProps) ->
    {@eventType, @uuid, @displayName, @loginId, @tokenExpiration, @tokenTimeout, @authToken} = eventProps

    Object.freeze(this);

  isLoggedIn: () -> return @eventType.isLoggedIn()
