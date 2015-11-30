(function(angular) {
  'use strict';

  module.exports = 'jchptf.modeling.core';

  /**
   * @ngdoc overview
   * @name jchptf.modeling.core
   * @description General purpose Object utility package that are useful for more than just Document persistence,
   *              including a pattern for creating Enumerations and another for creating deep read-only clones.
   */
  angular.module('jchptf.modeling.core', [])
    .service('CoreModelPackage', require('./CoreModelPackage.service'))
    // .service('ModelUtils', require('./ModelUtils.service'))
  ;
}).call(window.angular);
