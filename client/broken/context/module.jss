(function(angular) {
  'use strict';

  module.exports = 'jchptf.context';

  /**
   * @ngdoc overview
   * @name jchptf.context
   * @description Context module that is responsible for tracking global state, such as current logged in user.
   */
  angular.module(
    module.exports,
    ['ng', 'ui.router', 'ngEventAggregator']
  )
    .service('IdentityContext', require('./IdentityContext.service'))
    .factory('ContextModelPackage', require('./ContextModelPackage.factory'))
  ;
}).call(window, angular);
