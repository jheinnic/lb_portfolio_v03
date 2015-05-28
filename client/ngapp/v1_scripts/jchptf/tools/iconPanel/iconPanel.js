(function() {
  'use strict';

  module.exports = 'jchptf.tools.iconPanel';

  angular.module(
    'jchptf.tools.iconPanel', ['ng', require('jchptf.tools.keyboard')]
  )
    .directive('jchXYPos', require('./jchXYPos.directive.coffee'))
    .directive('jhCanvas', require('./jhCanvas.directive.coffee'))
    .directive('jhGrid', require('./jhGrid.directive.coffee'));
}).call(window);
