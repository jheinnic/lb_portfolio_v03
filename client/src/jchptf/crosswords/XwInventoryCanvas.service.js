(function () {
  'use strict';

  module.exports = XwInventoryCanvas;
  XwInventoryCanvas.$inject = ['Studio'];

  function XwInventoryCanvas(Studio) {
    // var Folder = RepositoryDomainPackage.Folder;
    // var RootFolder = Studio.getRootFolder();
    // var someFolder = Studio.getFolderByPath('/a/b/c');
    // var RootFolder = RepositoryDomainPackage.RootFolder;

    // this.documentCacheManager = Studio;
    // this.rootFolder = documentCacheManager.getRootFolder();
    this.rootFolder = Studio.getRootFolder();
    this.rootFolder.traverse().then(
      function (data) {
        console.log(data);
        var namespace, children, item;

        do {
          namespace = data.next();
          console.log('Key:', namespace.value.documentKind);
          children = namespace.value.children;
          do {
            item = children.next();
            console.log('-- Item:', item.value);
          } while (!item.done);
        } while (!namespace.done);
      }
    );
  }
}).call(window);
