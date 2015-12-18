'use strict'

module.exports = DocumentModelPackage

DocumentModelPacakge.$inject = ['CoreModelPackage']

DocumentModelPackage = (CoreModelPackage) ->
  _ = require('lodash')
  url = require('url')
  Enum = CoreModelPackage.Enum

  # Parent-to-child relationship has to be inferred from the child-to-parent relationships in order for
  # object move to be treated atomically.  If the parent-to-child associations were stateful, moving
  # would touch two collections and atomicity could not be guaranteed.

  ###*
  # Abstract signature for the ExportRoleKind enum each DocumentKind must provide.
  #
  # Each value in this enumeration maps a role name to a model class that can be used to satisfy that
  # role.  An export role creates a label representing an intention as to how any imported replicas of
  # the exported object should be reused.
  #
  # For example, a model that exports object representing weapons might define two export roles to
  # indicate difference between weapons intended for defensive use in contrast to those intended for
  # offensive use.
  ####
  class ExportRoleKind extends Enum
    @_ABSTRACT: true

    constructor: (roleName, @modelClass) ->
      throw new Error "Model class cannot be null" unless @modelClass?
      throw new Error "Model class must extend ModelObject" unless ModelObject::.isPrototypeOf(@modelClass::)

      super(roleName)

    getModelClass: => @modelClass

  ###*
  # The purpose of this enum is to clarify, in cases where an exported objects' export role can support
  # more than one of an importer's potential re-use intention types.
  #
  # As an example consider an exported object whose export role export denotates "used by hand".  A potential
  # importer may then utilize two ImportPurposeKind values to distinguish between "for use by left hand" and
  # "for use by right hand" if its semantics care about such a difference.
  #
  # Use of this class is only required when ExportRole is insufficient to infer import intent.  It is not
  # required in cases where two or more ExportRoles support the same import intention or in cases where
  # a single ExportRole implies a single ImportPurpose.
  ####
  class ImportPurposeKind extends Enum
    @_ABSTRACT: true

    constructor: (roleName, @exportRole) ->
      throw new Error "Model class cannot be null" unless @modelClass?
      throw new Error "Model class must extend ModelObject" unless ModelObject::.isPrototypeOf(@modelClass::)

      super(roleName)

    getModelClass: => @modelClass


  class DocumentKind extends Enum
    constructor: (docKindName, @rsrcExt, @docClass, @exportRoles) ->
      throw new Error "Resource extension cannot be null" unless @rsrcExt?
      if @rsrcExt instanceof String then @rsrcExt = @rsrcExt.valueOf()
      throw new Error "Resource extension must be a string" unless 'string' == typeof @rsrcExt?
      throw new Error "Resource extension cannot be blank" unless @rsrcExt != ''

      throw new Error "Document model cannot be null" unless @docClass?
      throw new Error "Document model must extend AbstractDocument" unless AbstractDocument::.isPrototypeOf(@docClass::)

      throw new Error "Export roles cannot be null" unless @exportRoles?
      throw new Error "Export roles enum must extend ExportRoleKind" unless ExportRoleKind::.isPrototypeOf(@exportRoles::)
      throw new Error "Export roles cannot be abstract" \
        unless (@exportRoles._ABSTRACT? == false) || (@exportRoles._ABSTRACT == false)
      @exportRoles = @exportRoles.finalize()

      throw new Error "Resource extension #{@rsrcExt} is already taken by #{preExisting}" \
        for preExisting in @values() where @rsrcExt == preExisting.rsrcExt
      throw new Error "Model type #{@docClass.name} is already taken by #{preExisting}" \
        for preExisting in @values() \
          where @docClass == preExisting.docClass || preExisting.docClass::.isPrototypeOf(@docClass::)

      super(docKindName)

    getResourceExtension: => @rsrcExt
    getDocumentClass: => @docClass
    getExportRoles: => @exportRoles
    addExtensionToName: (name) => "#{name}.#{@rsrcExt}"

    @resolveExtension: (token) ->
      [rsrcExt, name] = token.split(':')
      retVal = (name: name, type: enumVal for enumVal in @values() where rsrcExt == enumVal.rsrcExt)
      switch retVal.length
        when 0 then throw new Error "No known DocumentKind uses #{rsrcExt} as its token extension"
        when 1 then return retVal[0]
        else
          throw new Error(
            "More than one DocumentKind uses #{rsrcExt} as its token extension??  result=#{retVal}"
          )

    @lookupByDocumentClass: (docClass) ->
      throw new Error "Document class cannot be null" unless docClass?
      throw new Error "Document class #{docClass.name} must be subtype of AbstractDocument" \
        unless AbstractDocument::.prototype.isPrototypeOf(docClass::)

      retVal = (enumVal for enumVal in @values() \
        where docClass == enumVal.docClass || enumVal.docClass::.isPrototypeOf(docClass::))
      switch retVal.length
        when 0 then throw new Error "#{docClass.name} does not conform to the model type of any known DocumentKind: #{DocumentKind.values()}"
        when 1 then return retVal[0]
        else
          throw new Error(
            "#{docClass.name} conforms to the model type of more than one DocumentKind??  result=#{retVal}"
          )

    @lookupByAbstractDocument: (document) ->
      throw new Error "Abstract Document cannot be null" unless document?
      throw new Error "Abstract Documents must have an AbstractDocument subtype" \
        unless document instanceof AbstractDocument

      return DocumentKind.lookupByDocumentClass(document.constructor)

#    @lookupByRepositoryNode: (repoNode) ->
#      throw new Error "Repository node cannot be null" unless repoNode?
#      throw new Error "Repository nodes must have type RepositoryNode" unless repoNode instanceof RepositoryNode
#      throw new Error "Repository node's abstract document cannot be null" unless repoNode.abstractDocument?
#
#      return DocumentKind.lookupByDocumentClass(repoNode.abstractDocument.constructor)

  # The folder document type for folder does not actually use its document type.  These are just placeholders...
  class PseudoFolder extends AbstractDocument
    constructor: (params) ->
      {@uuid, nodeUrl, createdAt, modifiedAt, importSources, exportRoles} = params
      super(nodeUrl, createdAt, modifiedAt, importSources, exportRoles} = params

  class PseudoFolderExportRoleKind extends ExportRoleKind
    @_ABSTRACT = false
  PseudoFolderExportRoleKind.finalize()

  # Temporary stand-ins!!
  class XwTicket extends AbstractDocument
    constructor: (params) ->
      {@uuid, nodeUrl, createdAt, modifiedAt, importSources, exportRoles} = params
      super(nodeUrl, createdAt, modifiedAt, importSources, exportRoles} = params

  # Temporary stand-ins!!
  class XwResult extends AbstractDocument
    constructor: (params) ->
      {@uuid, nodeUrl, createdAt, modifiedAt, importSources, exportRoles} = params
      super(nodeUrl, createdAt, modifiedAt, importSources, exportRoles} = params

  # Temporary stand-ins!!
  class PokerHand extends AbstractDocument
    constructor: (params) ->
      {@uuid, nodeUrl, createdAt, modifiedAt, importSources, exportRoles} = params
      super(nodeUrl, createdAt, modifiedAt, importSources, exportRoles} = params

  new DocumentKind('FOLDER', 'f', PseudoFolder, PseudoFolderExportRoleKind)
  new DocumentKind('XW_TICKET', 'xwt', XwTicket, PseudoFolderExportRoleKind)
  new DocumentKind('XW_RESULT', 'xwr', XwResult, PseudoFolderExportRoleKind)
  new DocumentKind('POKER', 'pkr', PokerHand, PseudoFolderExportRoleKind)

  # class RepositoryNode
  #   constructor: (params) ->
  #     {@nodeUrl, @abstractDocument} = params
  # This is the persistence manifestation of a document.  It is intended to

  ###*
  # This model tracks a persistent repository object type.  Fetching it populates "unhydrated" instances
  # of the UI's Document and Folder models as a side effect (cached browsing).
  # TODO: Decouple the semantics of UI model derivation from persistent classes.  Layer it over them isntead.
  #
  # The URL path of any document serves as a mutable symbolic reference to its content.  Folders are
  # a purely organization concept, and so Folder nodes have no associated UUID, but Document nodes do.
  # Moving folder or documents to different locations changes the URL path of one or more RepositoryNode
  # objects without breaking UUID-based linkage.
  #
  # Browsing to the children of an unhydrated folder node causes the repo service to fetch all
  # RepositoryNodes with one additional path element of depth beyond its the source Folder's own URI.
  #
  # Opening an unhydrated document uses the Document Type and UUID to fetch the @rootObject.  It also
  # retrieves a Document object that supplies the @importSources and @exportedObjects.  The root object
  # of a document its Document metadata, and its RepositoryNode are intended to share a common UUID.
  ####
  class AbstractDocument
    constructor: (params) ->
      # {@docType, @folder, @docName, @revision, @rootObject, @importSources, @objectExports} = params
      {@uuid, @nodeUrl, @createdAt, @modifiedAt, @importSources, @exportRoles} = params

      # TODO: Check the UUID formatting.  When does UUID become non-null with new documents?

      throw new Error "#{@nodeUrl} is not a valid repository node URL" unless @nodeUrl?
      parsedUrl = url.parse(@nodeUrl)
      throw new Error "#{@nodeUrl} is not a valid repository node URL" unless parsedUrl?

      throw new Error "Repository node URL #{url} lacks objdoc: protocol" unless parsedUrl.protocol == 'objdoc:'
      throw new Error "Repository node URL #{url} contains an unsupported search string" if parsedUrl.search?
      throw new Error "Repository node URL #{url} contains an unsupported hash string" if parsedUrl.hash?
      throw new Error "Repository node URL #{url} contains an unsupported credential" if parsedUrl.auth?
      throw new Error "Repository node URL #{url} contains an unsupported port" if parsedUrl.port?
      throw new Error "Repository node URL #{url} contains an unsupported hostname" if parsedUrl.hostname != 'jchptf'
      throw new Error "Repository node URL #{url} lacks non-empty pathname" unless parsedUrl.pathname != '/'

    ###*
    # Concrete document type providers are required to override this method for access to the root object model.
    ####
    getRootObject: -> throw new Error "Concrete document subtypes are required to provide a root object instance"
    getDocumentKind: -> return DocumentKind.lookupByDocument(@)
    getDocumentExportRoles:-> return @getDocumentKind()?.getExportRoles()

    @getDocumentKind: -> return DocumentKind.lookupByDocumentClass(@)
    @getDocumentExportRoles:-> return @getDocumentKind()?.getExportRoles()

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

    removeFromExport: (targetRoleName, targetObject) =>
      @verifyExportArguments(exportRole, targetObject)

      exportRoleObject = _.first(@exportRoles, (item) -> return item.roleName == exportRole.name)
      exportRoleObject.removeUuid(targetObject.uuid)
      if exportRoleObject.getExportedObjectCount() == 0
        @exportRoles.pull exportRoleObject

    verifyExportArguments: (exportRole, targetObject) ->
      MyDocumentKind = @getDocumentKind()
      MyExportRoleKind = MyDocumentKind.getExportRoles()

      throw new Error "#{exportRole} must not be null" unless exportRole?
      throw new Error "Target object must not be null" unless targetObject?
      throw new Error "Target object must have a UUID" unless targetObject.uuid?
      throw new Error "#{exportRole} must be a value from #{MyExportRoleKind.name}" \
        unless exportRole instanceof MyExportRoleKind
      throw new Error "#{targetObject} does not satisfy #{exportRole.name}'s class requirement" \
        unless targetObject instanceof exportRole.modelClass
      return


  # Revision is definitely stored here--it describes the last known version of a
  # linked object
  class ImportSource
    constructor: (params) ->
      {@sourceDocument, @importRevision, @importedObjects} = params

  # TODO: Validate adding @importPurpose an optional property, since in most cases the reason for exporting
  #       an object implies the reason for importing it.  The exception to that rule of thumb is the possibility
  #       that one reason for exporting may map to multiple reasons for importing.  E.g. exporting for "use in hand"
  #       could be compatible with importing for "use in left hand" and also "use in right hand".
  # TODO: The following supports direct Proxying, e.g. copy without change of type.
  class ImportedObject
    constructor: (params) ->
      {@localUuid, @foreignUuid, @exportRole} = params

  class ExportRole
    constructor: (params) ->
      {@roleName, @uuidList} = params
      Object.seal(@)

    includeUuid: (uuid) =>
      throw new Error("Uuid may not be null or undefined") unless uuid?
      if uuid in @uuidList
        console.warn "Suspiciously redundant request to export #{uuid} under #{@roleName}"
      else
        @uuidList.push uuid

    removeUuid: (uuid) =>
      throw new Error("Uuid may not be null or undefined") unless uuid?

      lenDiff   = @uuidList.length - _.pull(@uuidList, uuid).length

      if lenDiff <= 0
        console.warn "Suspiciously vacuous request to stop exporting unexported #{uuid} under #{@roleName}"
      else if lenDiff > 1
        console.error "Request to stop exporting #{uuid} under #{@roleName} removed #{lenBefore - lenAfter} values!!!"

    getUuidList: =>
      return _.clone(@uuidList)

  ###*
  # Base class to help Documents keep track of their contained objects and to help Objects find their
  # immediate parent and root ancestor ModelObject containers.
  ###
  class ModelObject

  #class AttachmentKind extends Enum
  #new AttachmentKind('ORPHAN')
  #new AttachmentKind('DOCUMENT_ROOT')
  #new AttachmentKind('DOCUMENT_CONTENT')
  #new AttachmentKind('IMPORTED_CONTENT')

  # TODO: Try to keep the Import/Export subunits of AbstractDocument private and expose them through
  #       semantics methods from AbstractDocument.
  return {
    ModelObject: ModelObject
    DocumentKind: DocumentKind
    AbstractDocument: AbstractDocument
    ExportRoleKind: ExportRoleKind
    ExportRole: ExportRole
    ImportSource: ImportSource
    ImportedObject: ImportedObject
    # ImportPurposeKind: ImportPurposeKind
    # RepositoryNode: RepositoryNode
  }
