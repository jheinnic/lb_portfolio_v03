'use strict';

module.exports = ['Enum', (Enum) ->
  class SessionEventType extends Enum
    constructor: (name, authenticated, identifiable) ->
      super(name)
      @authenticated = authenticated
      @identifiable = identifiable
    ###
    # True if the user the session identifies has been authenticated as identified.
    ###
    isAuthenticated: -> return @authenticated
    ###
    # True if the session is associated with an identifiable user, false if it is anonymous.
    ###
    isIdentifiable: -> return @identifiable

  new SessionEventType('LOGGED_IN', true, true)
  new SessionEventType('TOKEN_CACHED', true, true)
  new SessionEventType('TOKEN_REFRESHED', true, true)
  new SessionEventType('LOGGED_OUT', false, true)
  new SessionEventType('TOKEN_EXPIRED', false, true)
  new SessionEventType('TOKEN_REVOKED', false, true)
  new SessionEventType('ANONYMOUS', false, false)
  new SessionEventType('TOKEN_INVALID', false, false)

  return SessionEventType
]
