(function() {
  /**
   * @ngdoc service
   * @name jchptf.site.context:UserIdentity
   *
   * @description A singleton model used by the AuthEventNames to store
   */
  module.exports = UserInfo;
  UserInfo.$inject = ['$rootScope', '$q', 'AuthEventNames'];


  var $q;
  var AuthEventNames;

  var nextUpdate;
  var userInfoHandle;

  function UserInfo($rootScope, AuthEventNames) {
    $q = _$q;
    AuthEventNames = AuthEventNames;
    nextUpdate = $q.defer();
    updateUserInfo(undefined, undefined, undefined);

    $rootScope.on(AuthEventNames.LOGIN_EVENT_NAME, function(event) {
      userInfoHandle = { userId: event.userId, firstName: 'John', lastName: 'Doe', }:
    });
  }

  UserInfo.prototype.getUserInfo = function getUserInfo() {
    return userInfoHandle;
  };

  function updateUserInfo(userId, firstName, lastName) {
    var thisUpdate = nextUpdate;
    nextUpdate = $q.defer();

    var userInfoModel = {
      __proto__: null,
      userId: userId,
      firstName: firstName,
      lastName: lastName,
      $promise: nextUpdate.$promise
    };

    var handleDescriptors = {
      __proto__: null
    };
    for (propName in ['userId', 'firstName', 'lastName', '$promise']) {
      handleDescriptors[propName] = {
        __proto__: null,
        enumerable: true,
        getter: function getter() {
          return userInfoModel[propName];
        }
      };
    }
    return Object.freeze(
      Object.defineProperties({}, handleDescriptors)
    );
  }
}).call(window);
