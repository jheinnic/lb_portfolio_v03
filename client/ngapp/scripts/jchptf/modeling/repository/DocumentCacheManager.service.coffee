'use strict'

module.exports = DocumentManager
DocumentManager.$inject = [
  'IdentityContext', 'DocumentModelPackage', 'RepositoryModelPackage', 'eventAggregator', '$localForage'
]

class DocumentManager
  constructor: (@IdentityContext, DocumentModelPackage, RepositoryModelPackage, @eventAggregator, @$localForage) ->
    {@Document, @Folder} = DocumentModelPackage
    {@AbstractDocument, @RepositoryNode} = RepositoryModelPackage

    # Last arguments are something for the resolve() wait...

    @eventAggregator.on('UserLogin', angular.noop)
    @eventAggregator.on('LocationChange', angular.noop)

  getDocumentModel: () ->

  openDocument: () ->
    activeUser = @IdentityContext.getLatestTokenEvent()

  getOpenDocumentMetadata: () ->

  changeActiveDocument: () ->

  releaseDocument: () ->

  saveCurrentChanges: () ->

  revertCurrentChanges: () ->

  markActiveDocumentDirty: () ->

  checkDependencies: () ->
    return
