(function(angular) {
  'use strict';

  module.export = 'jchptf.tools.keyboard';

  angular.module(
    module.export,
    ['ng', 'drahak.hotkeys']
  )
    .directive('jhCanvas', require('./jhCanvas.directive'))
    .directive('jhGrid', require('./jhGrid.directive'))
    .factory('KeypressHelper', require('./keypressHelper.factory'))
  ;
}).call(window, angular);
