(function() {
  'use strict';

  module.exports = 'jchptf.modeling.document';

  /**
   * @ngdoc overview
   * @name jchptf.context
   * @description Context module that is responsible for tracking global state, such as current logged in user.
   */
  angular.module('jchptf.modeling.document', [require('jchptf.modeling.core')])
    .factory('DocumentModelPackage', require('./DocumentModelPackage.factory.coffee'));
}).call(window);
