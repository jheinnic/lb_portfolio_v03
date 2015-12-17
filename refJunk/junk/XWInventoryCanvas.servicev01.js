(function() {
  'use strict';

  XWInventoryCanvas.$inject = ['Studio', 'RepositoryDomainPackage'];
  module.exports = XWInventoryCanvas;

  var _ = require('lodash');

  function XWInventoryCanvas(Studio, RepositoryDomainPackage) {
    var Folder = RepositoryDomainPackage.Folder;
    // var RootFolder = RepositoryDomainPackage.RootFolder;

    this.documentCacheManager = Studio;
    this.rootFolder = RepositoryDomainPackage.ROOT_FOLDER;
    this.rootFolder = documentCacheManager.getRootFolder();

    this.rootFolder.traverse();

    class Folder2 extends Folder

    this.Folder2 = Folder2
  }

  XWInventoryCanvas.prototype.getChildFolders = function () {
    return _.clone(this.childNodeList);
  };

  XWInventoryCanvas.prototype.addFolderToSelection =
    function selectFolder(folder)

  XWInventoryCanvas.function selectFolder(folder)
  {
    this.selectedFolder = folder;
    this.documentCacheManager.listChildren(
      this.selectedFolder
    ).then(
      _.curry(ingestFolderChildren)
    );
  };

  XWInventoryCanvas.prototype.ingestFolderChildren =
    function ingestFolderChildren(childNodeList) {
      console.log('Nom nom nom');

      return [1, 2, 3, 4, 5];
    };
})(window);
