## TODO:  Eliminate replication of this code in jchpft-backend/lib/xwprovider.js

module.exports = XwModelPackage

XwModelPackage.$inject = ['CoreModelPackage', 'DocumentModelPackage', 'RepositoryModelPackage']

XwModelPackage = (CoreModelPackage, DocumentModelPackage, RepositoryModelPackage) ->
  Enum = CoreModelPackage.Enum
  {AbstractDocument, DocumentKind, ExportRoleKind, ModelObject} = DocumentModelPackage
  {Canvas, Folder, Document} = RepositoryModelPackage

