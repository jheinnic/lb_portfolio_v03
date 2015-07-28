RepositoryNodeImpl = require('./RepositoryNodeImpl.class.coffee')

  ###*
  # This model tracks a persistent repository object type.  Fetching it populates "dehydrated" instances
  # of the UI's Document and Folder models as a side effect (cached browsing).
  # TODO: Decouple semantics of UI model derivation from persistent classes.  Layer over them instead.
  #
  # The URL path of any document serves as a mutable symbolic reference to its content.  Folders are
  # a purely organization concept, and so Folder nodes have no associated UUID, but Document nodes do.
  # Moving folder or documents to different locations changes the URL path of one or more RepositoryNode
  # objects without breaking UUID-based linkage.
  #
  # Browsing to the children of a dehydrated folder node causes the repo service to fetch all
  # RepositoryNodes with one additional path element of depth beyond its the source Folder's own URI.
  #
  # Opening a dehydrated document uses the Document Type and UUID to fetch the @rootObject.  It also
  # retrieves a Document object that supplies the @importSources and @exportedObjects.  The root object
  # of a document its Document metadata, and its RepositoryNode are intended to share a common UUID.
  ####
class DocumentImpl extends RepositoryNodeImpl
  constructor: (params) ->
    {@uuid, @documentKind, @createdAt, @modifiedAt, @docSyncMode, @rootObject} = params.uuid

    # TODO: String lengths and UUID format
    throw new Error "All documents must define a UUID" unless @uuid?
    throw new Error "All documents must define a createdAt" unless @createdAt?
    throw new Error "All documents must have a document sync mode" unless @docSyncMode?
    throw new Error "All documents must have a document type" unless @documentKind?

    if @modifiedAt? && @modifiedAt < @createdAt
      throw new Error "Any document with modifiedAt defined must use a time later than createdAt"

    @docSyncMode ?= DocumentSyncKind.NOT_OPEN
    unless @docSyncMode instanceof DocumentSyncKind
      throw new Error "Document sync mode must be a value from the DocumentSyncKind enum."
    if @rootObject?
      unless @documentKind.isValidRootObject(@rootObject)
        throw new Error "#{@rootObject} is not a valid root object for #{@documentKind}"
      unless @docSyncMode.isHydrated()
        throw new Error "With rootObject cache, #{@docSyncMode} must map to an 'is hydrated' status"
      @hydrationPromise = $q.when(@rootObject)
    else
      unless hydrationMode.isDehydrated()
        throw new Error "Without rootObject cache, #{@docSyncMode} must map to an 'is dehydrated' status"

    super(params)

# throw new Error "All documents must define a documentKind" unless @documentKind?
# throw new Error "All documents must define documentKind as a RepoNodeKind" unless @documentKind instanceof RepoNodeKind
# throw new Error "All documents have a legitimate repository node name" \
#   unless @name?.search(/^[_A-Za-z0-9][_A-Za-z0-9 ]*$/)
# throw new Error "All documents must have a parent" unless @parent? and @parent instanceof Folder
# throw new Error "All documents must have a unique name under a suitable namespace of their parent" \
#   if @parent.contents[@documentKind]?[@name]?

  ###*
  # Concrete document type providers are required to override this method for access to the root object model.
  ####
# getRootObject: -> throw new Error "Concrete document subtypes are required to provide a root object instance"

  getCreatedAt: => return @createdAt
  getModifiedAt: => return @modifiedAt
  # getRevision: -> return 'TBD'
  # getExportRoleKinds: => return @getRepoNodeKind()?.getExportRoles()

  ###*
  # Method that extracts externalizable identification of a document, with no references to runtime abstractions
  # like Studio, Folder, Document, Canvas, EditorState, or any of the Enum classes.
  ####
  toMemento: () ->
    retVal = {
      uuid: @uuid
      url: "repo://jchptf#{@getFolder.asPathString()}#{@documentKind.encodeForPathElement(@name)}"
      documentKind: @documentKind.getName()
      createdAt: @createdAt
      modifiedAt: @modifiedAt
    }
    return Object.freeze(retVal)

  toStorageKey: () ->
    "#{@documentKind.toString()}::#{@uuid}"

  ###*
  # Method for hydrating the Project's document model and returning a clone of that graph.  The Project
  # retains a what is used as a read-only copy of a document for dependency and rollback use cases, while
  # the deep clone provided for encapsulation by an AbstractCanvas subtype remains mutable.
  ####
  hydrate: () ->
    return @hydrationPromise if @hydrationPromise

    @hydrationPromise =
      $localForage.getItem(@encodeForKey())
      .then(
        (abstractDocument) ->
          $log.error("Abstract document cannot be null") unless abstractDocument?
          $log.error("Root object cannot be null") unless abstractDocument.getRootObject()?
          @abstractDocument = _.deepClone(abstractDocument)

          # Document is currently the hydrated equivalent of Project, so to hydrate a
          # Project, replace it with the exanded-scope Document equivalent.
          @parent.contents[@documentKind][@name] = @abstractDocument
          @hydrationStatus = true
          @hydrationPromise = undefined

          return abstractDocument
      )

#     rename: (newName) =>
#       if @name == newName then return
#       throw new Error("Name not available") if @parent.contents[documentKind][newName]?
#       newNameNode = @documentKind
#       # TODO: Emit a name change event?

  getRootObject: ->
    throw new Error "Document #{parentFolder.getFolderPath()}#{@name} is not yet hydrated" unless @hydrationStatus
    return @getRootObject()
# TODO: getImportSourceList, getImportedObjectListForSource, and getImportedObjectListsBySource
# TODO: addImportedObject and removeImportedObject
# TODO: linkImportSource and unlinkImportSource

  exposeForExport: (exportRole, targetObject) =>
    @verifyExportArguments(exportRole, targetObject)
    exportRoleObject = _.first(@exportRoles, (item) -> return item.roleName == exportRole.name)
    if exportRoleObject?
      exportRoleObject.includeUuid(targetObject.uuid)
    else
      @exportRoles.push(new ExportRole roleName: exportRole.name, uuidList: [targetObject.uuid])

  removeFromExport: (exportRole, targetObject) =>
    @verifyExportArguments(exportRole, targetObject)

    exportRoleObject = _.first(@exportRoles, (item) -> return item.roleName == exportRole.name)
    exportRoleObject.removeUuid(targetObject.uuid)
    if exportRoleObject.getExportedObjectCount() == 0
      @exportRoles.pull exportRoleObject

  verifyExportArguments: (exportRole, targetObject) ->
    MyRepoNodeKind = @getRepoNodeKind()
    MyExportRoleKind = MyRepoNodeKind.getExportRoles()

    throw new Error "#{exportRole} must not be null" unless exportRole?
    throw new Error "Target object must not be null" unless targetObject?
    throw new Error "Target object must have a UUID" unless targetObject.uuid?
      throw new Error "#{exportRole} must be a value from #{MyExportRoleKind.name}" \
    unless exportRole instanceof MyExportRoleKind
      throw new Error "#{targetObject} does not satisfy #{exportRole.name}'s class requirement" \
    unless targetObject instanceof exportRole.modelClass

module.exports = DocumentImpl
