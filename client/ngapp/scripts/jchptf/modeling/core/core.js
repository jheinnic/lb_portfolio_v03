(function() {
  'use strict';

  module.exports = 'jchptf.modeling.core';

  /**
   * @ngdoc overview
   * @name jchptf.modeling.core
   * @description General purpose Object utility package that are useful for more than just Document persistence,
   *              including a pattern for creating Enumerations and another for creating deep read-only clones.
   */
  angular.module('jchptf.modeling.core', [])
    .factory('CoreModelPackage', require('./CoreModelPackage.factory.coffee'))
    .service('ModelUtils', require('./ModelUtils.service'))
  ;
}).call(window);
