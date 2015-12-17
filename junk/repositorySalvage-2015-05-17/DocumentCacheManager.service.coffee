'use strict'

module.exports = studioInjector = (RepositoyModelPackage) ->
  STUDIO = RepositoryModelPackage.STUDIO
  return STUDIO
}

studioInjector.$inject = ['RepositoryModelPackage']

# module.exports = studioInjector

# module.exports = DocumentManager
# DocumentManager.$inject = [
#   'IdentityContext', 'DocumentModelPackage', 'RepositoryModelPackage', 'eventAggregator', '$localForage'
# ]
#
# class DocumentManager
#   constructor: (@IdentityContext, DocumentModelPackage, RepositoryModelPackage, @eventAggregator, @$localForage) ->
#     {@Document, @Folder} = DocumentModelPackage
#     {@AbstractDocument, @RepositoryNode} = RepositoryModelPackage
#
#     @eventAggregator.on('UserLogin', angular.noop)
#     @eventAggregator.on('LocationChange', angular.noop)
#
#   openDocument: () ->
#     activeUser = @IdentityContext.getLatestTokenEvent()
#
#   getOpenDocumentMetadata: () ->
#
#   changeActiveDocument: () ->
#
#   releaseDocument: () ->
#
#   saveCurrentChanges: () ->
#
#   revertCurrentChanges: () ->
#
#   markActiveDocumentDirty: () ->
#
#   checkDependencies: () ->
#     return
