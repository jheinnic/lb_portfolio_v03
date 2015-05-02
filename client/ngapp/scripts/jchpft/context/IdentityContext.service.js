(function(angular, jchutils) {
    'use strict';
    
    /**
     * @ngdoc overview
     * @name jchpft.context
     * @description
     * Module for maintaining "global" application state that needs to be able
     * to survive UI view changes and remain available for the duration of a
     * user's interaction time with the application.
     *
     * There are two known contexts at this time:
     * <ol>
     * <li>A user's authenticated login state, credential token, and
     * the websocket session through which they receive pushed notifications
     * from the server and establish a presence in the cluster.</li>
     * <li>Project catalog and impact analysis metadata, synchronized with 
     *  the backend to avoid the cost of redundant retrieval and also to
     *  inform users about unresolved change propagation requirements.</li>
     * </ol>
     */
    angular.module('jchpft.context')
      .factory('IdentityContext', IdentityContext);
    
    IdentityContext.$inject=['$q', 'AUTH_TOKEN_EVENT_TYPE'];

    function IdentityContext($q, 'AUTH_TOKEN_EVENT_TYPE') {
        // TODO: Add JWT token handling utilities, and tools for accepting
	//       delegated authentication tokens (e.g. OAuth2)

	return this;
    };

    var tokenEventListeners = new Set();
    var nextEventDefer = null;
    var latestIdentityContext = {
        uuid = null;
	displayName = null;
	loginId = null;
	tokenExpiration = null;
	tokenTimeout = null;
	authToken = null;
	latestTokenEvent = AUTH_TOKEN_EVENT_TYPE.NOT_LOGGED_IN;
    }

    IdentityContext.prototype.getLoggedInUuid = function() {
        return latestIdentityContext.uuid;
    }

    IdentityContext.prototype.getLoggedInDisplayName = function() {
        return latestIdentityContext.displayName;
    }

    // TODO: Try to do token attachment to requests by way of a before
    //       advising interceptor, transparently to the calling service,
    //       negating any need to expose the AUTH_TOKEN via service API.
    IdentityContext.prototype.getLoggedInAuthToken = function() {
        return latestIdentityContext.authToken;
    }

    /**
     * @method
     *
     * Unlike its stronger sibling, validateAuthTokenStatus(), this method
     * just returns the catched auth token state.  It does not look for
     * an AUTH_TOKEN or attempt to decode it.  IF you have no reason to belive
     * the user has just returned with an AUTH_TOKEN, it is still more 
     * appropriate to use this method than validateAuthTokenStatus().
     *
     * Also note that getAuthTokenStatus() is unable to fire events due to
     * state changes, which is because it is returning old state about the 
     * most recent known state change.
     */
    IdentityContext.prototype.getAuthTokenStatus = function() {
        return latestIdentityContext.latestTokenEvent;
    }

    IdentityContext.prototype.verifyAuthTokenStatus = function() {
        // TODO: Get the AUTH_TOKEN cookie and validate it.

        if(true) {
            fireTokenEvent(AUTH_TOKEN_EVENT_TYPE.NEW_TOKEN_IS_VALID);
        }

	return latestIdentityContext.latestTokenEvent;
    }

    IdentityContext.prototype.addTokenEventListener = function(eventHandler) {
        if (tokenEventListeners.contains(eventHandler) {
            throw new Error("Event listener already registered for token events.");
	}
	tokenEventListeners.add(eventHandler);
	nextEventDefer.promise.then(eventHandler);
    }

    IdentityContext.prototype.dropTokenEventListener =function(eventHandler) {
        if (! tokenEventListener.remove(eventHandler) {
            throw new Error("Cannot cancel and unregistered token event handler.");
	}

	if (tokenEventListener.size() == 0) {
	    nextEventDefer = null;
	} else {
	    replaceNextEventDefer();
	}
    }

    IdentityContext.prototype.logoutUser = function() {
        var nextEvent = fireTokenEvent(AUTH_TOKEN_EVENT_TYPE.TOKEN_LOGGED_OUT);
	nextEventDefer.promise.resolve(nextEvent);
	replaceNextEventDefer();
    }

    function fireTokenEvent(eventType) {
        var nextEvent = new AuthTokenEvent(eventType);
	nextEventDefer.promise.resolve(nextEvent);
	replaceNextEventDefer();
    }

    function replaceNextEventDefer() {
        var replacementDefer = $q.defer();
	var replacementPromise = replacementDefer.promise;
	for (var eventListener in tokenEventListeners) {
            replacementPromise.then(eventListener);
	}
    }

    fuunction AuthTokenEvent(_eventType) {
	Object.defineProperty(
            this, 'eventType', {
                configurable: 'false',
		enumerable: 'true',
		writeable: 'false',
	        value: _eventType
	    }
	);
    }
}(window.angular);
