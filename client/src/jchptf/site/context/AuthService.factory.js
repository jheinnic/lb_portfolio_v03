(function() {
  'use strict';

  /**
   * @ngdoc service
   * @name jchptf.site.context:AuthService.Factory
   *
   * @description
   */
  module.exports = authServiceFactory;
  authServiceFactory.$inject = ['ipCookie'];

  function authServiceFactory(ipCookie) {
    var cookieName = 'authCookie';

    return {
      clearAuthKey: function clearAuthKey() {
        return ipCookie.remove(cookieName, {
          path: '/'
        });
      },
      getAuthKey: function getAuthKey() {
        return ipCookie(cookieName) || false;
      },
      setAuthKey: function setAuthKey(key) {
        ipCookie(cookieName, key, {
          path: '/'
        });
      }
    };
  }
}).call(window);
