(function () {
  'use strict';

  module.exports = 'jchptf.modeling.studio';

  /**
   * @ngdoc overview
   * @name jchptf.modeling.studio
   * @description
   */
  angular.module(
    'jchptf.modeling.studio', [
      'ng', 'ngEventAggregator', 'LocalForageModule', require('jchptf.modeling.core'), require('jchptf.modeling.repository')]
  )
    // .config(require('./studio.config.js'))
    // .factory('StudioModelPackage', require('./StudioDomainPackage.factory.coffee'))
  ;
}).call(window);
