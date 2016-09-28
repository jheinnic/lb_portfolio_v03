(function () {
  'use strict';

  module.exports = importCrosswordConfig;
  importCrosswordConfig.$inject = ['$mdDialogProvider'];

  /**
   * @ngdoc method
   * @name jchptf.site.branding:config
   *
   * @description
   * Routing configuration for portfolio application's branding module.
   * Defines the landing page, /home.
   */
  function importCrosswordConfig($mdDialogProvider) {
    $mdDialogProvider.addPreset('importCrossword', {
      options: function() {
        return {
          templateUrl: 'jchptf/feature/import/crosswords/_importCrossword.modal.html',
          controller: 'TicketImportController as vm',
          bindToController: true,
          clickOutsideToClose: true,
          escapeToClose: true
        };
      }
    });
  }
}).call(window);
