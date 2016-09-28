(function(console) {
  'use strict';

  module.exports = TestUploadController;
  TestUploadController.$inject = ['$scope', 'FileUploader'];

  function TestUploadController($scope, FileUploader) {
    // create a uploader with options
    var uploader =
      new FileUploader({
        scope: $scope,              // to automatically update the html. Default: $rootScope
        url: '/api/Containers/container1/upload',
        formData: [
          {key: 'value'}
        ]
      });
    $scope.uploader = uploader;

    // ADDING FILTERS
    uploader.filters.push({
      name: 'filterName',
      fn: function filter(item, options) {
        console.info('filter2', item, options);
        return true;
      }
    });

    // REGISTER HANDLERS
    // --------------------
    uploader.onAfterAddingFile = function onAfterAddingFile(item) {
      console.info('After adding a file', item);
    };
    // --------------------
    uploader.onAfterAddingAll = function onAfterAddingAll(items) {
      console.info('After adding all files', items);
    };
    // --------------------
    uploader.onWhenAddingFileFailed = function onWhenAddingFileFailed(item, filter, options) {
      console.info('When adding a file failed', item, filter, options);
    };
    // --------------------
    uploader.onBeforeUploadItem = function onBeforeUploadItem(item) {
      console.info('Before upload', item);
    };
    // --------------------
    uploader.onProgressItem = function onProgressItem(item, progress) {
      console.info('Progress: ' + progress, item);
    };
    // --------------------
    uploader.onProgressAll = function onProgressAll(progress) {
      console.info('Total progress: ' + progress);
    };
    // --------------------
    uploader.onSuccessItem = function onSuccessItem(item, response, status, headers) {
      console.info('Success', response, status, headers);
      $scope.$broadcast('uploadCompleted', item);
    };
    // --------------------
    uploader.onErrorItem = function onErrorItem(item, response, status, headers) {
      console.info('Error', response, status, headers);
    };
    // --------------------
    uploader.onCancelItem = function onCancelItem(item, response, status, headers) {
      console.info('Cancel', response, status, headers);
    };
    // --------------------
    uploader.onCompleteItem = function onCompleteItem(item, response, status, headers) {
      console.info('Complete', response, status, headers);
    };
    // --------------------
    uploader.onCompleteAll = function onCompleteAll() {
      console.info('Complete all');
    };
  }
}).call(window, window.console);
