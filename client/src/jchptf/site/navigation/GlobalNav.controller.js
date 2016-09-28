(function(console) {

  'use strict';

  module.exports = GlobalNavController;
  GlobalNavController.$inject = ['$scope', '$state', 'NavbarData', 'userInfo']; // , '$injector'];

  var NO_USER_INFO = {userId: undefined, firstName: undefined, lastName: undefined};
  var _ = require('lodash');

  function GlobalNavController($scope, $state, $login, NavbarData, userInfo) {
    var vm = this;
    vm.$state = $state;
    vm.userInfo = userInfo || NO_USER_INFO;
    vm.navbarData = NavbarData;

    $scope.state = $state;

    function onSessionChangeEvent(event) {
      if (event.eventType.isAuthenticated()) {
        event.userInfo.then( function onUserInfoAvailable(data) {
          vm.userInfo = data || NO_USER_INFO;
        }).catch(function onError(err) {
          vm.userInfo = NO_USER_INFO;
          console.error(err);
        });
      }
    }

    vm.isLogggedIn = function isLoggedIn() {
      return !_.isUndefined(vm.userInfo.userId);
    };

    vm.logout = $login.logout;

    $scope.$on('jchptf.site.authentication.sessionChangeEvent', onSessionChangeEvent);
  }
}).call(window, window.console);

