module.exports = Folder

class Folder extends DomainObject
  # Class-scoped variables.  Inaccessible beyond the class`
  constructor: (_folderImpl) ->
    Object.defineProperty(
      @
      'folderImpl'
      {
        get: restrictToClass(
          () -> return _folderImpl
          Folder
        )
      }
    )

  getName: () -> return @folderImpl.getName()
  getParentNode: () -> return @folderImpl.getParentNode()
  asPathString: () -> return @folderImpl.asPathString()

  traverse: () -> return @folderImpl.traverse()
  # listChildren: (docKinds) -> return @folderImpl.listChildren(docKinds)
  createFolder: (folderName) -> return @folderImpl.createFolder(folderName)
  createDocument: (documentName, documentRoot) -> return @folderImpl.createDocument(documentName, documentRoot)

# TODO: Synchronous methods
# traverseSync: (docKinds) -> return @folderImpl.traverseSync(docKinds)
# listChildrenSync: (docKinds) -> return @folderImpl.listChildrenSync(docKinds)
