(function() {
  'use strict';

  XWInventoryCanvas.$inject = ['Studio', 'RepositoryDomainPackage'];
  module.exports = XWInventoryCanvas;

  var _ = require('lodash');

  function XwInventoryCanvas(Studio, RepositoryDomainPackage) {
    var Folder = RepositoryDomainPackage.Folder;
    var RootFolder = RepositoryDomainPackage.RootFolder;

    // this.documentCacheManager = Studio;
    this.rootFolder = RepositoryDomainPackage.ROOT_FOLDER;
    // this.rootFolder = documentCacheManager.getRootFolder();
    this.rootFolder.traverse().then(
      function (data) {
        console.log(data);
      }
    );
  }
}).call(window);
