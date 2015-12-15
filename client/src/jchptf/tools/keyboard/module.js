(function(angular) {
  'use strict';

  module.export = 'jchptf.tools.keyboard';

  angular.module(
    module.export,
    ['ng', 'drahak.hotkeys']
  )
    .factory('KeypressHelper', require('./keypressHelper.factory'))
  ;
}).call(window, angular);
