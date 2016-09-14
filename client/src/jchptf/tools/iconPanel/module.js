(function(angular) {
  'use strict';

  module.exports = 'jchptf.tools.iconPanel';

  angular.module(
    'jchptf.tools.iconPanel',
    ['ng']
  )
    .directive('jchXYPos', require('./jchXYPos.directive'))
  ;
}).call(window, window.angular);
