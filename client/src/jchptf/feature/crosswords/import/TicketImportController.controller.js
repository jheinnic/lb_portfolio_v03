(function (console) {
  'use strict';

  module.exports = TicketImportController;
  TicketImportController.$inject = ['$scope', '$http', 'FileUploader', 'FiveXTicket'];

  function TicketImportController($scope, $http, FileUploader, FiveXTicket) {
    $scope.list = function listFiles() {
      $http.get('/api/containers/container1/files')
        .success(function onGetListSuccess(data) {
          console.log(data);
          $scope.files = data;
        }
      );
      return FiveXTicket;
    };

    $scope.delete = function deleteFile(index, id) {
      $http.delete('/api/containers/container1/files/' + encodeURIComponent(id))
        .success(
        function onDeleteSuccess(data, status, headers) {
          console.log('On delete of ' + id, data, status, headers);
          $scope.files.splice(index, 1);
        }
      ).failure(
        function onDeleteFailed(err) {
          console.error('Could not delete file with ID ' + id, err);
        }
      );
    };

    // create a uploader with options
    var uploader = new FileUploader({
      scope: $scope,              // to automatically update the html. Default: $rootScope
      url: '/api/TicketPlayStates/upload',
      formData: [
        {key: 'value'}
      ]
    });
    $scope.uploader = uploader;

    $scope.$on('uploadCompleted', function onUploadCompleted(event) {
      console.log('uploadCompleted event received: ', event);
      $scope.list();
    });
  }
}).call(window, window.console);
