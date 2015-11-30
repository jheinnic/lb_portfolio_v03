(function() {
  'use strict';

  module.exports = 'jchptf.tools.iconPanel';

  angular.module(
    'jchptf.tools.iconPanel',
    ['ng', require('jchptf.tools.keyboard')]
  )
    // .directive('jchXYPos', require('./jchXYPos.directive.coffee'))
    .directive('jchXYPos', require('./jchXYPos.directive'))
    // .directive('jhCanvas', require('./jhCanvas.directive.coffee'))
    .directive('jhCanvas', require('./jhCanvas.directive'))
    // .directive('jhGrid', require('./jhGrid.directive.coffee'))
    .directive('jhGrid', require('./jhGrid.directive'))
  ;
}).call(window);
