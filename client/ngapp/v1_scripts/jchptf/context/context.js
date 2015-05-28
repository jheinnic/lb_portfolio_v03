(function() {
  'use strict';

  module.exports = 'jchptf.context';

  /**
   * @ngdoc overview
   * @name jchptf.context
   * @description Context module that is responsible for tracking global state, such as current logged in user.
   */
  angular.module('jchptf.context', ['ng', 'ui.router', 'ngEventAggregator'])
    .service('IdentityContext', require('./IdentityContext.service.coffee'))
    .factory('ContextModelPackage', require('./ContextModelPackage.factory.coffee'));
}).call(window);
