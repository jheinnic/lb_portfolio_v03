(function(angular) {
  'use strict';

  module.exports = 'jchptf.feature.crosswords.import';

  angular.module(
    module.exports,
    ['ng', 'angularFileUpload', require('jchptf.lbclient'), 'jchptf.lbServices']
  )
    .controller('TestUploadController', require('./TestUploadController.controller'))
    .controller('TicketImportController', require('./TicketImportController.controller'))
  ;
}).call(window, window.angular);
