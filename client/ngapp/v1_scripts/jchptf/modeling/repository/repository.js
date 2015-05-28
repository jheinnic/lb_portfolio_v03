(function() {
  'use strict';

  module.exports = 'jchptf.modeling.repository';

  /**
   * @ngdoc overview
   * @name jchptf.context
   * @description Context module that is responsible for tracking global state, such as current logged in user.
   */
  angular.module(
    'jchptf.modeling.repository',
    [
      'ng',
      'ngEventAggregator',
      'LocalForageModule',
      require('jchptf.modeling.core'),
    ]
  )
    .config(require('./repository.config.js'))
    .factory('RepositoryModelPackage', require('./RepositoryDomainPackage.factory.coffee'));
}).call(window);
