(function() {
  'use strict';

  /**
   * @ngdoc service
   * @name jchptf.site.context:AuthService.Factory
   *
   * @description
   */
  module.exports = authServiceFactory;
  authServiceFactory.$inject = ['$rootScope', '$q', 'ipCookie', 'SessionEventType', 'ModelUtils'];

  function authServiceFactory($rootScope, $q, ipCookie, SessionEventType, ModelUtils) {
    var authTokenCookieName = 'authToken';
    var principalCookieName = 'userInfo';

    var val = ModelUtils.getValueDescriptor;
    var latestEvent;
    var CHANGE_EVENT_ID = 'jchptf.site.authentication.sessionChangeEvent';

    function getPrincipal() {
      return ipCookie(principalCookieName) || false;
    }

    function getAuthKey() {
      return ipCookie(authTokenCookieName) || false;
    }

    // NOTE: When a remote query replaces this boilerplate, this resolve will happen inside the remote call's
    //       callback handler, but for now this is as much overkill as it would appear to be.
    function updateUserInfo(deferHandle, userId) {
      var firstName = 'John';
      var lastName = 'Doe';
      var handleDescriptors = {
        __proto__: null,
        userId: ModelUtils.getValueDescriptor(userId),
        firstName: ModelUtils.getValueDescriptor(firstName),
        lastName: ModelUtils.getValueDescriptor(lastName)
      };
      deferHandle.resolve(
        Object.freeze(
          Object.defineProperties(
            {}, handleDescriptors)));
    }

    // Initially there is either anonimity or a cached principal.
    var cachedPrincipal = getPrincipal();
    if (cachedPrincipal) {
      var userInfoDeferHandle = $q.defer();
      updateUserInfo(userInfoDeferHandle, cachedPrincipal);

      latestEvent =
        Object.freeze(
          Object.definePropeties(
            {}, {
              eventType: val(SessionEventType.TOKEN_CACHED),
              authToken: val(ipCookie(authTokenCookieName)),
              userId: val(cachedPrincipal),
              userInfo: val(userInfoDeferHandle.$promise)
            }
          )
        );
    } else {
      latestEvent =
        Object.freeze(
          Object.definePropeties(
            {}, {
              eventType: val(SessionEventType.ANONYMOUS)
            }
          )
        );
    }
    $rootScope.$broadcast(CHANGE_EVENT_ID, latestEvent);

    return {
      CHANGE_EVENT_ID: CHANGE_EVENT_ID,

      clearAuthKey: function clearAuthKey() {
        ipCookie.remove(principalCookieName, {path: '/'});
        var retVal = ipCookie.remove(authTokenCookieName, {path: '/'});

        if (latestEvent.eventType.isIdentfiable()) {
          latestEvent =
            Object.freeze(
              Object.definePropeties(
                {}, {
                  eventType: val(SessionEventType.LOGGED_OUT),
                  userId: val(latestEvent.userId),
                  userInfo: val(latestEvent.userInfo)
                }
              )
            );
        } else {
          latestEvent =
            Object.freeze(
              Object.definePropeties(
                {}, {
                  eventType: val(SessionEventType.ANONYMOUS)
                }
              )
            );
        }

        $rootScope.$broadcast(CHANGE_EVENT_ID, latestEvent);
        return retVal;
      },

      getAuthKey: getAuthKey,

      // This method is defined by ui.router.login, but not used.  It is not sufficient for our requirements
      // as it leaves no way to remember who the user is.  Instead of this method, we therefore will use the
      // setCredentials( ) method that is not defined by ui.router.login, and is intended to replace a method
      // that ui.router.login defines without making use of it itself.
      setAuthKey: function setAuthKey(token) {
        ipCookie(authTokenCookieName, token, {path: '/'});
      },

      setIdentity: function setIdentity(userId, token) {
        // TODO: If feasible, validate that token is for a session associated with userId.
        ipCookie(authTokenCookieName, token, {path: '/'});
        ipCookie(principalCookieName, userId, {path: '/'});

        // TODO: Make a remote call to access roles grants and group membership to facilitate presentation's
        //       ability to avoid exposing UI for unauthorized functionality.
        var deferedHandle = $q.defer();
        updateUserInfo(deferedHandle, userId);
        latestEvent =
          Object.freeze(
            Object.definePropeties(
              {}, {
                eventType: val(SessionEventType.LOGGED_IN),
                userId: val(latestEvent.userId),
                userInfo: val(deferedHandle.$promise),
                authToken: val(token)
              }
            )
          );

        $rootScope.$broadcast(CHANGE_EVENT_ID, latestEvent);
      },

      getPrincipal: getPrincipal,

      getLatestEvent: function getLatestEvent() {
        return latestEvent;
      }
    };
  }
}).call(window);
