(function(angular) {
  'use strict';

  module.exports = 'jchptf.crosswords.import';

  angular.module(
    module.exports,
    ['ng', 'angularFileUpload', 'jchptf.lbclient']
  )
    .controller('TestUploadController', require('./TestUploadController.controller'))
    .controller('TicketImportController', require('./TicketImportController.controller'))
  ;
}).call(window, window.angular);
