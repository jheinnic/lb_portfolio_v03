/**
 * Created by John on 12/20/2015.
 */
(function (angular) {
  'use strict';

  module.exports = authInterceptConfig;
  authInterceptConfig.$inject = ['$stateProvider', 'RoleAuthorizationProvider'];

  function authInterceptConfig($stateProvider, RoleAuthorizationProvider) {
    // Override the internal 'views' builder with a function that takes the state
    // definition, and a reference to the internal function being overridden:
    $stateProvider.decorator('state', function ($stateConfig, $builderFn) {
      // Let the native builder produce its data result
      var data = $builderFn($stateConfig);

      // Look for our activation trigger in the data content model and take
      // action if its found.  Remove the key if its found, since its is not
      // meant for consumption directly by UI controllers.
      if (angular.isObject(data) && data.requiresIdentity === true) {
        RoleAuthorizationProvider.requiresIdentity($stateConfig)
        delete(data.requiresIdentity)
      }

      // Return all other data otherwise untouched as though nothing
      // even happened...
      return data;
    };
  }
}).call(window, angular);

