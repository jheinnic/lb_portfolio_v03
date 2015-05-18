(function() {
  'use strict';

  module.exports = 'jchptf.repository';

  /**
   * @ngdoc overview
   * @name jchptf.context
   * @description Context module that is responsible for tracking global state, such as current logged in user.
   */
  angular.module(
    'jchptf.repository',
    [
      'ng',
      'ngEventAggregator',
      'LocalForageModule',
      require('jchptf.modeling.core'),
      require('jchptf.modeling.document')
    ]
  )
    .config(require('./repository.config.js'))
    .factory('DirtyTracker', require('./DirtyTracker.factory.coffee'))
    .service('DocumentCacheManager', require('./DocumentCacheManager.service.coffee'))
    .factory('RepositoryModelPackage', require('./RepositoryModelPackage.factory.coffee'));
}).call(window);
