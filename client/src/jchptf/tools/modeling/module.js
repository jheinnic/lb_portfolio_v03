(function(angular) {
  'use strict';

  module.exports = 'jchptf.tools.modeling';

  /**
   * @ngdoc overview
   * @name jchptf.tools.modeling
   * @description General purpose Object utility package including a design pattern for creating Enumerations
   *              and a utility for creating deep immutable clones.
   */
  angular.module(
    module.exports,
    []
  )
    .factory('ModelObject', require('./ModelObject.factory'))
    .factory('Enum', require('./Enum.factory'))
    .value('ModelUtils', require('./ModelUtils.value'))
  ;
}).call(window, window.angular);
