(function() {
  'use strict';

  module.exports = 'jchptf.site.notification';

  /**
   * @ngdoc overview
   * @name jchptf.site.notification
   * @description
   *
   * A directive for placing an area for asynchronous application messages
   * at a suitable location in a view and a service for accepting them
   * from places where events trigger.
   */
  angular.module('jchptf.site.notification', ['ng', /*'ui.bootstrap',*/ 'toastr']);
}).call(window);
