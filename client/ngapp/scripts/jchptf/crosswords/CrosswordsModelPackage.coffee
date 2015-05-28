## TODO:  Eliminate replication of this code in jchpft-backend/lib/xwprovider.js

module.exports = XwModelPackage =
  (CoreModelPackage, DocumentModelPackage, RepositoryModelPackage) ->
    XwModelPackage.$inject = [
      'CoreModelPackage'
      'DocumentModelPackage'
      'RepositoryModelPackage'
    ]

    Enum = CoreModelPackage.Enum
    {
      AbstractDocument
      DocumentKind
      ExportRoleKind
      ModelObject
    } = RepositoryModelPackage
    {
      Canvas
      Folder
      Document
    } = DocumentModelPackage

