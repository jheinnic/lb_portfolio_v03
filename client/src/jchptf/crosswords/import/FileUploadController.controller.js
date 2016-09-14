(function() {
  module.exports = TicketImportController
  TicketImportController.$inject = ['$scope', '$http', 'FileUploader', 'FiveXTicket'];

  function TicketImportController ($scope, $http, FileUploader, FiveXTicket) {
    $scope.list = function list() {
      $http.get('/api/containers/container1/files').success(function (data) {
        console.log(data);
        $scope.files = data;
      });
    };

    $scope.delete = function delete(index, id) {
      $http.delete('/api/containers/container1/files/' + encodeURIComponent(id)).success(function (data, status, headers) {
        $scope.files.splice(index, 1);
      });
    };

    $scope.$on('uploadCompleted', function(event) {
      console.log('uploadCompleted event received');
      $scope.load();
    });
  }
}).call(window);
