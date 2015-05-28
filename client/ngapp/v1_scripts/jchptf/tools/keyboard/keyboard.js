(function() {
  'use strict';

  module.export = 'jchptf.tools.keyboard';

  angular.module('jchptf.tools.keyboard', ['ng'])
    .factory('KeypressHelper', require('./keypressHelper.factory'));
}).call(window);
