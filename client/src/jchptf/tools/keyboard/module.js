(function(angular) {
  'use strict';

  module.export = 'jchptf.tools.keyboard';

  angular.module(
    'jchptf.tools.keyboard',
    ['ng', 'drahak.hotkeys']
  )
    .factory('KeypressHelper', require('./keypressHelper.factory'))
  ;
}).call(window.angular);
