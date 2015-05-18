(function() {
  'use strict';

  module.exports = configLocalForage;
  configLocalForage.$inject = ['$localForageProvider'];

  function configLocalForage($localForageProvider) {
    $localForageProvider.config({
      driver: 'indexedDB',
      name: 'jchptf',
      storeName: 'portfolio',
      description: 'Local cache for inactive documents held open by editor presently inactive editors.' +
      'indexing keys used to this end are curies whose namespaces are associated with types of artifact' +
      'managed by the "owning" feature\'s editor and values are indexed UUID keys for identifying each' +
      'document uniquely. Notice how name is not a part of this key.  Name is available through a record' +
      'record our Angular service managing this cache uses to mark its own book-keeping state, including' +
      'things like its file name associations, state flags, and other descriptive metadata used to service' +
      'its consumers and answer any request for comment from the repository regarding a cached document.'
    });

    $localForageProvider.createInstance()
  }
}).call(window);
