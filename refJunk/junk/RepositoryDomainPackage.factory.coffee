'use strict'

module.exports = RepositoryDomainPackage =

  ###*
  # SupportDomainPackage contains a domain model abstraction providing repository services for the models registered
  # through the DocumentModelPackage data model's RepoNodeKind enumeration and Document extension class.
  #
  # To develop new repository-based functionality, a modeler will typically create two extension packages, one for
  # DocumentModelPackage, and one for SupportDomainPackage.
  # ** The data model is defined by sub-classing DocumentModelPackage.Document and binding that subclass to a
  #    new value of the DocumentModelPackage.RepoNodeKind enumeration.
  # ** Access to repository services for CRUD and Synchronization services for that model are acquired through
  #    the SupportDomain using a different pair of types.  AbstractCanvas is the extension class, and CanvasKind
  #    is its enumeration.
  #
  # DocumentModelPackage introduces the "Document" and "DataObject" abstractions.  It provides a facility for
  # creating cross-document model re-use dependencies and provides metadata support for enabling controlled change
  # reconciliation and state markers (e.g. stale dependencies and constraint violations).  Although it provides an
  # interface for interacting with these bookkeeping elements, it defers conventions about how to reuse that interface
  # to a DomainModeling layer.  It focuses on the semantics of what dependencies and markers are, leaves how they
  # apply to a modeled Document within each Document's extension contract.
  #
  # This is an intentional design decision.  It allows a Document to have a representation in multiple Domains.
  # SupportDomainPackage is the only such domain at present, and it is oriented towards a client browser environment.
  # What is done with composition here has a compatible analog in Loopback through the Local and Remote Model mixing
  # classes.  Keep this in mind to stay prepared for a future Loopback migration path.  Let any semantics added
  # to Document and DataObject subtypes constrain themselves to operations that would be available on the
  # a base Model subtype.  Use the object graph rooted at an AbstractCanvas to reach methods that would only come into
  # context through a LocalModel subclass of your common Model type that adds the Local mixin.
  #
  # What follows is a brief survey of the classes provided here:
  #
  # Repository -- This class is instantiated once as a singleton within the scope of the SupportDomainPackage itself.
  # It encapsulates the remote API client and bridges its services to the modeler.  It doubles as a Folder, and
  # implements that portion of its interface as Repository's root folder in that capacity.
  #
  # Folder -- A cache for intermediate grouping nodes form Repository URL paths.  No persistent representation, but
  # facilitates the semantics of move and rename operations.
  #
  # Project -- Web client domain-level encapsulation of a repository Document at the catalog level.  Projects can be
  # found by browsing through Folders or retrieved directly from the Repository by specifying either their logical URI
  # or physical UUID in combination with their domain model ProjectKind value's name.
  # representation of a Document.  Opening a Project is how a developer adds Canvases to the Studio.
  #
  # Studio -- This class is also a singleton and represents client-side's global UI model.  It retains a memento or direct
  # reference to the AbstractCanvas artifact for every Document considered open within the UI.  It points at the
  # single specific 'Current' Canvas whose interface is currently active within the view.  Canvases that are open, but
  # not current may be cached in memory or may have been serialized to local storage and reversibly replaced with a
  # Memento Handle.  Dirty/Clean Canvas marking is done here.  An application-scoped event bus is also available.
  # A global "current selection" pointer list is available here, but may be augmented by locally scoped selection models
  # found in concrete Canvas models and/or their class types.
  # TODO: Is there a StudioProvider for route registration?
  #
  # AbstractCanvas --
  #
  # Workspace -- A potential refactoring of the "live" elements of Studio to separate them those dealing with
  #              book-keeping for inactive document editors (e.g. not the active view)
  ####
  (CoreModelPackage, ModelUtils, eventAggregator, $localForage, $q, $log) ->
    RepositoryDomainPackage.$inject = [
      'CoreModelPackage'
      'ModelUtils'
      'eventAggregator'
      '$localForage'
      '$q'
      '$log'
    ]

    Enum = CoreModelPackage.Enum
    _ = require('lodash')
    url = require('url')

    NODE_KIND_SUFFIX_LENGTH = 8
    NODE_KIND_SUFFIX_REGEX = /^(?:[A-Za-z0-9_]+)$/

    MAX_DOCUMENT_NAME_LENGTH = 56
    DOCUMENT_NAME_REGEX = /^(?:[A-Za-z0-9_.]+)$/
    DOCUMENT_URL_TOKEN_REGEX = /^([A-Za-z0-9_.]*)\.([A-Za-z0-9_]+)$/

    ###*
    # Wraps getter with a function that will only permit member functions of clazz to call.
    #
    # To lock out unwanted access, call Object.freeze() on your class prototype after defining it, otherwise
    # the protection offered can be violated by adding methods to break through.
    ###
    restrictToClass = (privateFn, clazz) ->
      callFn = () ->
        if callFn.caller in _.valuesIn(clazz::)
          return privateFn.apply(@, arguments)
        throw new Error("Illegal Call")
      return callFn

    ###*
    # Abstract signature for the ExportRoleKind enum each RepoNodeKind must provide.
    #
    # Each value in this enumeration maps a role name to a model class that can be used to satisfy that
    # role.  An export role creates a label representing an intention as to how any imported replicas of
    # the exported object should be reused.
    #
    # For example, a model that exports object representing weapons might define two export roles to
    # indicate difference between weapons intended for defensive use in contrast to those intended for
    # offensive use.
    ###
    class ExportRoleKind extends Enum
      @_ABSTRACT: true

      constructor: (roleName, @modelClass) ->
        throw new Error "Model class cannot be null" unless @modelClass?
        throw new Error "Model class must extend DataObject" unless DataObject.isSuperTypeOf(@modelClass)

        super(roleName)

      getModelClass: () -> return @modelClass


    ###*
    # The purpose of this enum is to clarify, in cases where an exported objects' export role can support
    # more than one of an importer's potential re-use intention types.
    #
    # As an example consider an exported object whose export role export denotes "used by hand".  A potential
    # importer may then utilize two ImportSourceKind values to distinguish between "for use by left hand" and
    # "for use by right hand" if its semantics care about such a difference.
    #
    # Use of this class is only required when ExportRoleKind is insufficient to infer import intent.  It is not
    # required in cases where two or more ExportRoles support the same import intention or in cases where
    # a single ExportRole implies a single ImportSource.
    ####
    class ImportSourceKind extends Enum
      @_ABSTRACT: true

      constructor: (roleName, @exportRole) ->
        throw new Error "Export role cannot be null" unless @exportRole?
        throw new Error "Export role must be a subtype of ExportRole" unless ExportRole.isSuperTypeOf(@exportRole)
        super(roleName)

      getExportRole: () => return @exportRole
      getModelClass: () => return @exportRole.getModelClass()


    ###*
    # Base class to help Documents keep track of their contained objects and to help Objects find their
    # immediate parent and root ancestor DataObject containers.
    ###
    class DomainObject
      @getSuperType: ->
        retVal = undefined
        if @ != DomainObject then retVal = @__super__.constructor
        return undefined
      @isSuperTypeOf: (target) ->
        throw new Error("target argument must be defined") unless target?
        throw new Error("target argument must be a DataObject subtype") unless DataObject.isSuperTypeOf(target)
        return @::.isPrototypeOf(target::)
      @isSubTypeOf: (target) ->
        throw new Error("target argument must be defined") unless target?
        throw new Error("target argument must be a DataObject subtype") unless DataObject.isSuperTypeOf(target)
        return target::.isPrototypeOf(@::)
      getType: -> return @__super__.constructor
      getSuperType: -> return @getType().getSuperType()
      hasSuperTypeOf: (target) -> return @getType().isSuperTypeOf(target)
      hasSubTypeOf: (target) -> return @getType().isSubTypeOf(target)


    class RepositoryNodeImpl extends DomainObject
      constructor: (params) ->
        {@name, @parentNode, @repoInstanceKind, @repoNodeKind, @hydrationMode, @hydrationPromise, _repositoryService} = params
        @hydrationMode ?= HydrationModeKind.DEHYDRATED

        switch
          when ! @name?
            throw new Error 'All repository nodes must define a name.'
          when ! @name.search(NODE_NAME_REGEX)
            throw new Error 'Repository node names may only include alphanumerics and underscore (\'_\').'
          when ! @parentNode?
            throw new Error 'Repository nodes must define a back reference to their parent folder.'
          when ! @parentNode instanceof FolderImpl
            throw new Error 'A repository node\'s parent back reference must be of type FolderImpl.'
          when ! @repoInstanceKind?
            throw new Error ''
          when ! @repoNodeKind?
          when ! @repoNodeKind instanceof @repoInstanceKind.getRepoNodeKind()
          when @hydrationMode.isHydrating()
          when @hydrationPromise? && @hydrationMode.isNotHydrated()
          when ! (@hydrationPromise? || @hydrationMode.isDehydrated())
        throw new Error '' unless @repoInstanceKind?
        throw new Error "" unless @repoNodeKind?
        throw new Error "" unless @repoNodeKind instanceof @repoInstanceKind.getRepoNodeKind()
        if @hydrationMode.isHydrating()
          throw new Error 'Hydration mode HYDRATING is reserved for use during asynchronous hydration resolution'
        else if @hydrationPromise
          throw new Error "Cannot populate hydration promise when mode is #{@hydrationMode}" unless @hydrationMode.isHydrated()
        else
          throw new Error "Must populate hydration promise when mode is #{@hydrationMode}" unless @hydrationMode.isDehydrated()

      getName: () -> return @name
      getParentNode: () -> return @parentNode.facade
      getRepoNodeKind: () -> return @repoNodeKind
      encodeForUrl: () -> return @repoNodeKind.encodeForUrl(@name)
      asPathString: () ->
        throw new Error("Subtypes must provide a concrete implementation for asPathString()")


    # Private implementation method allowing both Folder and Document classes to trigger a folder contents
    # write.  This a transient implementation detail--when we decouple from $localForage, and the URL moves
    # back into a header sub-document of a MongoDB document, atomic move will entail updating that header,
    # instead of writing two distinct folder documents.
    #
    # NOTE: If I find its possible to update two Folder documents in a single transaction, the above may be
    # overstating the degree of temporariness to this after all...
    #
    # updateFolderContents = (pathString, contents) ->
    #  console.error("TODO: Call $localForage.writeItem!!")


    ###*
    # Folder is a client-side representation of the organizational relationship between Documents with partially
    # overlapping ancestor sub-paths that begin at at the repository's root path ("/").  It is a derived concept,
    # meaning that Folder need not be a concrete object in the server side repository implementation, provided it
    # has the ability to recognized documents that can be grouped by a common URL root and calling letting the
    # concept of that group be labeled "Folder".
    #
    # Folder and Document work together to provide an abstraction of the repository's content tree.  Folder is
    # used for the organizational hierarchy provided by the intermediate portion of URL paths, whereas Document
    # is used exclusively for the leaf nodes that contain user artifact object graphs.
    #
    # A Folder is not a single namespace.  It provides a namespace for child Folders and an additional namespace
    # for each registered RepoNodeKind.  This is reflected in the conventions governing URL construction:
    # -- For any URL path, every node except the last represents a Folder
    # -- The URL path for a Document has no trailing slash and is composed from a document name portion and a
    #    document kind portion.  The document kind portion matches the "Resource Extension" attribute of its
    #    RepoNodeKind enumeration value.  The document name and document kind are separated by a dot ('.').
    # -- No path element representing a folder uses an extension suffix.  If a folder node ends in '.foo', then
    #    .foo is the last part of the folder's name.
    # -- To disambiguate whether any suffix preceded by a dot is a type suffix or part of a folder's name,
    #    URLs that identify a Folder must end with a trailing '/', whereas URLs that represent a document
    #    must never terminate on a trailing '/'.
    # -- Because '.' is legal in a document name, it is not a legitimate character to use in a RepoNodeKind's
    #    "Resource Extension" value.  For the same reason, when parsing a document's path element, only the
    #    right-most '.' is treated as a separator.
    #
    # Folder and Document each define have two modes of operation--"hydrated" an "dehydrated".  These states
    # represent, respectively, "completely loaded" and "partially loaded" degrees of state completion.
    #
    # An dehydrated Folder can be used to identify itself in a catalog listing, but has incomplete information
    # about its children, possibly no information at all.  The hydrate method will transition a Folder from
    # its dehydrated state to the hydrated state by asynchronously loading children.  To indicate completion,
    # Hydrate is not part of the public API for Folder or Document because its promise would expose the underlying
    # model content, but there are several use-facing methods that invoke hydrate to establish a fully hydrated
    # parent that allows them to search through all folder contents for matches, conflicts, etc..  addFolder(),
    # moveDocument, findChild, and hasChild() are examples of such methods.  There is currently no support for
    # dehydrating a repository object to spare memory, but this is functionality that is expected to manifest at
    # a later date.
    #
    #XX  To keep memory utilization under control, Folder will periodically purge its cached children and return to
    #XX  a dehydrated state, with the following exceptions:
    #XX  -- If the Studio's UI model indicates that the Folder is currently expanded and that a navigation view is
    #XX  active, Folder will not revert to a dehydrated state.
    #XX  -- When reverting to a dehydrated state, it will retain any children that are hydrated.  It will refuse
    #XX  to provide a partial list of its children until hydrated, and it will not create a duplicate entry for
    #XX  any children it from its previous reversion to dehydrated.
    #
    # TODO: Eliminate subtypes of Document--all they add is a type constraint on their 'rootObject', and
    #       that can be accomplished in a purely data driven fashion.
    # TODO: Evaluate the option of providing a semantic facade for Import/Export functionality.
    #
    # @class {Folder}
    ####
  class FolderImpl extends RepositoryNodeImpl
      repositoryService = undefined
      Object.defineProperty(
        FolderImpl::
        'repositoryService'
        {
          get: restrictToClass(
            () -> return repositoryService
            FolderImpl::
          )
        }
      )

      constructor: (params) ->
        # First object constructed sets the global reference and it sticks
        repositoryService ?= params.repositoryService

        repoInstanceKind = params.repoInstanceKind
        throw new Error '' unless repoInstanceKind?
        RepoNodeKind = repoInstanceKind.getRepoNodeKind()

        super _.defaults(
          {
            repoNodeKind: RepoNodeKind.FOLDER
            hydrationMode: HydrationModeKind.DEHYDRATED
          }
          params
        )

        Object.describeProperties(@, {
          contents: {value: {}}
          hydratedDescendants: {value: 0, editable: true}  # A ref count to detect dehydration permissibility
        })
        Object.seal(@)

        # Allocate slots in the contents object for each distinct RepoNodeKind's namespace, sealing the root
        # collection of namespace-specific collections once they have all been populated.
        for repoNodeKind in RepoNodeKind.values()
          @contents[repoNodeKind] = {}
        Object.seal(@contents)

      hydrate: () -> return @repositoryService.hydrateFolder()
      dehydrate: () -> return @repositoryService.dehydrateFolder()

      getContents: () -> return @contents
      mayDehydrate: () -> return @hydratedDescendants <= 0

      incrementHydratedDescendants: (increment) ->
        if increment > 0 && @hydratedDescendants == 0
          @parent.incrementHydratedDescendants(1)
        @hydratedDescendants = @hydratedDescendants + increment
      decrementHydratedDescendants: (decrement) ->
        @hydratedDescendants = @hydratedDescendants - decrement
        if decrement > 0 && @hydratedDescendants == 0
          @parent.decrementHydratedDescendants(1)

      asPathString: -> return "#{@getParentNode().asPathString()}#{@name}/"

      hasChildNode: (childNodeKind, childNodeName) ->
        return @repositoryService
          .hydrate(@, childNodeKind, childNodeName)
          .then(
            (folder) -> return folder.contents[childNodeKind][childNodeName]?
          )

      getChildNode: (childNodeKind, childNodeName) ->
        return @repositoryService
          .hydrate(@, childNodeKind, childNodeName)
          .then(
            (folder) -> return (folder.contents[childNodeKind][childNodeName] || undefined)
          )

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
      constructor: (params, cachedObject) ->
        # TODO: String lengths and UUID format
        {@uuid, @createdAt, @modifiedAt, hydrationMode} = params.uuid
        throw new Error "All documents must define a UUID" unless @uuid?
        throw new Error "All documents must define a createdAt" unless @createdAt?
        if @modifiedAt? && @modifiedAt < @createdAt
          throw new Error "Any document with modifiedAt defined must use a time later than createdAt"

        if hydrationMode?
          unless hydrationMode instanceof HydrationModeKind
            throw new Error "Explicit hydrationMode must be a value from its enum."
          if cachedObject?
            unless hydrationMode.isHydrated()
              throw new Error "With rootObject cache, #{hydrationMode} must map to an 'is hydrated' status"
          else
            unless hydrationMode.isDehydrated()
              throw new Error "Without rootObject cache, #{hydrationMode} must map to an 'is dehydrated' status"
        else
          if cachedObject?
            throw new Error "Hydration mode must be defined when supplying Document Root on construction"
          else
            hydrationMode = HydrationModeKind.DEHYDRATED

        if cachedObject?
          {@rootObject, @importSources, @exportRoles} = cachedObject
          throw new Error "Cache parameters are incomplete unless rootObject is defined" unless @rootObject?
          throw new Error "Cache parameters are incomplete unless importSources enum is defined" unless @importSources?
          throw new Error "Cache parameters are incomplete unless exportRoles enum is defined" unless @exportRoles?

          hydrationPromise = $q.when(@rootObject)
        else
          [@rootObject, @importSources, @exportRoles, hydrationPromise] = [undefined, undefined, undefined]

        super(params)
        @hydrationPromise = hydrationPromise
        @hydrationMode = hydrationMode

      # throw new Error "All documents must define a docKind" unless @repoNodeKind?
      # throw new Error "All documents must define docKind as a RepoNodeKind" unless @repoNodeKind instanceof RepoNodeKind
      # throw new Error "All documents have a legitimate repository node name" \
      #   unless @name?.search(/^[_A-Za-z0-9][_A-Za-z0-9 ]*$/)
      # throw new Error "All documents must have a parent" unless @parent? and @parent instanceof Folder
      # throw new Error "All documents must have a unique name under a suitable namespace of their parent" \
      #   if @parent.contents[@repoNodeKind]?[@name]?

      ###*
      # Concrete document type providers are required to override this method for access to the root object model.
      ####
      # getRootObject: -> throw new Error "Concrete document subtypes are required to provide a root object instance"

      getCreatedAt: => return @createdAt
      getModifiedAt: => return @modifiedAt
      getRevision: -> return 'TBD'
      getExportRoleKinds: => return @getRepoNodeKind()?.getExportRoles()

      ###*
      # Method that extracts externalizable identification of a document, with no references to runtime abstractions
      # like Studio, Folder, Document, Canvas, EditorState, or any of the Enum classes.
      ####
      encodeForUrl: () ->
        retVal = {
          uuid: @uuid
          url: "repo://jchptf#{@parentFolder.asPathString()}#{@repoNodeKind.encodeForUrl(@name)}"
          repoNodeKind: @repoNodeKind.name
          createdAt: @createdAt
          modifiedAt: @modifiedAt
        }
        return Object.freeze(retVal)

      encodeForKey: () ->
        "#{@repoNodeKind.toString()}::#{@uuid}"

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
              @parent.contents[@docKind][@name] = @abstractDocument
              @hydrationStatus = true
              @hydrationPromise = undefined

              return abstractDocument
          )

  #     rename: (newName) =>
  #       if @name == newName then return
  #       throw new Error("Name not available") if @parent.contents[repoNodeKind][newName]?
  #       newNameNode = @repoNodeKind
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


    # Revision is definitely stored here--it's used to remember the last known revision ID of a
    # linked document, not to define its own revision.
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
        throw new Error("Uuid must be defined") unless uuid?

        lenDiff = @uuidList.length - _.pull(@uuidList, uuid).length
        if lenDiff <= 0
          console.warn "Suspiciously vacuous request to stop exporting unexported #{uuid} under #{@roleName}"
        else if lenDiff > 1
          console.error "Request to stop exporting #{uuid} under #{@roleName} removed #{lenBefore - lenAfter} values!!!"

      getUuidList: =>
        return _.clone(@uuidList)


    class RootFolderImpl extends FolderImpl
      constructor: () ->
        @name = ''
        @parent = null

      asPathString: () -> return '/'


    class Document extends DomainObject
      constructor: (_documentImpl) ->
        Object.defineProperty(
          @
          'documentImpl'
          {
            get: ModelUtils.restrictToClass(
              () -> return _documentImpl
              Document
            )
          }
        )

      getName: () -> return @documentImpl.getName()
      getParentNode: () -> return @documentImpl.getParentNode()
      getRepoNodeKind: () -> return @documentImpl.getRepoNodeKind()
      getModifiedAt: () -> return @documentImpl.getModifiedAt()
      getCreatedAt: () -> return @documentImpl.getCreatedAt()
      encodeForUrl: () -> return @documentImpl.encodeForUrl()
      asPathString: () -> return @documentImpl.asPathString()


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


    return {
      Folder: Folder
      FolderImpl: FolderImpl
      RootFolderImpl: RootFolderImpl
      RepositoryNode: RepositoryNode
      RepositoryNodeImpl: RepositoryNodeImpl
      HydrationModeKind: HydrationModeKind
      ExportRole: ExportRole
      ExportRoleKind: ExportRoleKind
      ImportSource: ImportSourceKind
      ImportSourceKind: ImportSourceKind
    }
