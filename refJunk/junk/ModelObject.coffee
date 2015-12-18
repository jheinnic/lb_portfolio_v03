'use strict'

module.exports = RepositoryModelPacakge

SupportDomainPackage.$inject = ['CoreModelPackage', 'eventAggregator', '$localForage', '$q', '$log']

###*
# SupportDomainPackage contains a domain model abstraction providing repository services for the models registered
# through the DocumentModelPackage data model's DocumentKind enumeration and Document extension class.
#
# To develop new repository-based functionality, a modeler will typically create two extension pacakges, one for
# DocumentModelPackage, and one for SupportDomainPackage.
# ** The data model is defined by sub-classing DocumentModelPackage.Document and binding that subclass to a
#    new value of the DocumentModelPackage.DocumentKind enumeration.
# ** Access to repository services for CRUD and Synchronization services for that model are acquired through
#    the SupportDomain using a different pair of types.  AbstractCanvas is the extension class, and CanvasKind
#    is its enumeration.
#
# DocumentModelPackage introduces the "Document" and "ModelObject" abstractions.  It provides a facility for
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
# to Document and ModelObject subtypes constrain themselves to operations that would be available on the
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
#
#
### #
SupportDomainPackage = (CoreModelPackage, IdentityContext, eventAggregator, $localForage, $q, $log) ->
  Enum = CoreModelPackage.Enum
  Promise = require('bluebird')
  _ = require('lodash')
  url = require('url')

  # WHERE DOES THIS BELONG (Begin)
  #      if lenDiff <= 0
  #        console.warn "Suspiciously vacuous request to stop exporting unexported #{uuid} under #{@roleName}"
  #      else if lenDiff > 1
  #        console.error "Request to stop exporting #{uuid} under #{@roleName} removed #{lenBefore - lenAfter} values!!!"
  # WHERE DOES THIS BELONG (End)

  MAX_DOCUMENT_NAME_LENGTH = 56
  DOCUMENT_NAME_REGEX = /^([A-Za-z0-9_.]*)$/
  DOCUMENT_URL_TOKEN_REGEX = /^([A-Za-z0-9_.]*)\.([A-Za-z0-9_]*)$/

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
### #
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
### #
class ImportPurposeKind extends Enum
  @_ABSTRACT: true

  constructor: (roleName, @exportRole) ->
    throw new Error "Export role cannot be null" unless @exportRole?
    throw new Error "Export role must be a subtype of ExportRole" unless ExportRole::.isPrototypeOf(@exportRole::)
    super(roleName)

  getExportRole: => @exportRole
  getModelClass: => @exportRole.getModelClass()


###*
#
### #
class DocumentKind extends Enum
  _RSRC_EXT_TO_DOCUMENT_KIND = {}
  _ROOT_TYPE_TO_DOCUMENT_KIND = {}

  # constructor: (docKindName, @rsrcExt, @documentSubtype, @rootObjectType, @exportRoles) ->
  constructor: (docKindName, @rsrcExt, @rootObjectType, @exportRoles) ->
    throw new Error "Resource extension cannot be null" unless @rsrcExt?
    if @rsrcExt instanceof String then @rsrcExt = @rsrcExt.valueOf()
    throw new Error "Resource extension must be a string" unless 'string' == typeof @rsrcExt?
    throw new Error "Resource extension cannot be blank" unless @rsrcExt != ''

    # throw new Error "Document subtype cannot be null" unless @documentSubtype?
    # throw new Error "Document subtype must extend Document" unless AbstractDocument::.isPrototypeOf(@documentSubtype::)

    throw new Error "Root object type cannot be null" unless @rootObjectType?
    throw new Error "Root object type must extend ModelObject" unless ModelObject::.isPrototypeOf(@rootObjectType::)

    throw new Error "Export roles cannot be null" unless @exportRoles?
    throw new Error "Export roles enum must extend ExportRoleKind" unless ExportRoleKind::.isPrototypeOf(@exportRoles::)
    throw new Error "Export roles cannot be abstract" if @exportRoles._ABSTRACT? && @exportRoles._ABSTRACT
    @exportRoles = @exportRoles.finalize()

      throw new Error "Resource extension #{@rsrcExt} is already taken by #{_RSRC_EXT_TO_DOCUMENT_KIND[@rsrcExt]}" \
    if _RSRC_EXT_TO_DOCUMENT_KIND[@rsrcExt]?
      throw new Error "Root object type #{@rootObjectType.name} is already used by #{_ROOT_TYPE_TO_DOCUMENT_KIND[@rootObjectType]}" \
    if _ROOT_TYPE_TO_DOCUMENT_KIND[@rootObjectType]?
    throw new Error "Root object type #{@rootObjectType.name}'s subtypes overlap root object subtypes from other Document Kinds."
      if _.any(@values, (preExising) ->
        return @rootObjectType::.isPrototypeOf(preExisting.rootObjectType::) ||
            preExisting.rootObjectType::.isPrototypeOf(@rootObjectType::)
      )

    _RSRC_EXT_TO_DOCUMENT_KIND[@rsrcExt] = @
    _ROOT_TYPE_TO_DOCUMENT_KIND[@rootObjectType] = @
    super(docKindName)

  getResourceExtension: => @rsrcExt
  getRootObjectType: => @rootObjectType
  getExportRoles: => @exportRoles

  encodeForUrl: (name) =>
    throw new Error("name must be defined") unless name?
    throw new Error("name may not be blank") unless name != ''
    throw new Error("name may not exceed 56 characters in length") unless name.length <= MAX_DOCUMENT_NAME_LENGTH
    throw new Error("name must only contain alphanumerics, '.', and '_'") unless DOCUMENT_NAME_REGEX.test name

    return "#{name}.#{@rsrcExt}"

# TODO: Is this wise?
  createDocument: (params, rootObject) =>
    throw new Error("params must be defined") unless params?
    throw new Error("rootObject must be defined") unless rootObject?
    throw new Error("rootObject must be of type #{@rootObjectType.name}" \
      unless rootObject instanceof @rootObjectType

# _TODO: Use @exportRoles to prepopulate collection of empty ExportRoles objects
# _TODO: Who says there may not be valid ExportRole assignments by this point??
# _TODO: This artifact is not yet saved.  How to manage that?
      hydrationParams = {
        rootObject: rootObject,
        importSources: [],
        exportRoles: []
      }
      newDocument = new Document(params, hydrationParams)

      @decodeFromUrl: (urlToken) ->
        throw new Error("urlToken must be defined") unless urlToken?
        [..., name, rsrcExt] = urlToken.match(DOCUMENT_URL_TOKEN_REGEX)

        throw new Error("name must be defined") unless name?
        throw new Error("name may not be blank") unless name != ''
        throw new Error("name may not exceed 56 characters in length") unless name.length <= MAX_DOCUMENT_NAME_LENGTH
        throw new Error("name must only contain alphanumerics, '.', and '_'") unless DOCUMENT_NAME_REGEX.test name
            throw new Error "No registered DocumentKind uses #{rsrcExt} as its resource extension" \
          unless @_RSRC_EXT_TO_DOCUMENT_KIND[rsrcExt]?

        return name: name, type: @_RSRC_EXT_TO_DOCUMENT_KIND[rsrcExt]

      doLookupByRootObjectType = _.memoize(
        (rootObjectType, enumValues) ->
          retVal = _.first(
            enumValues,
            (value) ->
              valRootType = value.rootObjectType
              return ((rootObjectType == valRootType) || (valRootType.isSuperTypeOf(rootObjectType)))
          )

          unless retVal?
            errMsg = "#{rootObjectType} does not satisfy root object type of any UIDomain"
            $log.error errMsg
            throw new Error errMsg

          $log.info("First time looking up #{rootObjectType}, matched #{retVal}")
          return retVal
      )

      @lookupByRootObjectType: (rootObjectType) ->
        throw new Error "Root object type must be defined" unless rootObjectType?
        throw new Error "Root object type #{rootObjectType.name} must be a subtype of ModelObject" \
          unless ModelObject.isSuperTypeOf(rootObjectType)
        return doLookupByRootObjectType(rootObjectType, @values())

      @lookupByRootObject: (rootObject) ->
        throw new Error "Root object must be defined" unless rootObject?
            throw new Error "Root object #{rootObjectType.name} must be typed by a subtype of ModelObject" \
          unless rootObject instanceof ModelObject

        return doLookupByRootObjectType(rootObject.getType(), @values())


      class HydrationModeKind extends Enum
        new HydrationModeKind('DEHYDRATED')
        new HydrationModeKind('NASCENT')
        new HydrationModeKind('ONLINE')
        new HydrationModeKind('OFFLINE')
        new HydrationModeKind('FROZEN')
        new HydrationModeKind('DIVERGENT')
        new HydrationModeKind('FORBIDDEN')
        new HydrationModeKind('ERROR')
      HydrationModeKind.finalize()


      ###*
      # For folders, hydration describes the degree to which a folder's child nodes are completely cached.
      # If a Folder has only been created to serve as a step in the path to some Document that has been
      # edited, then it is likely just DEHYDRATED.  A folder becomes HYDRATED when the Repository singleton
      # is asked to perform an operation that requires knowledge about the full contents of a child set,
      # such as moving a node into a directory, or browsing its children.  All Folders are created in a
      # DEHYDRATED state initially, although they may be created for a Repository service method that will
      # require them to receive a request to hydrate shortly after creation.
      ####
      class HydrationStatusKind extends Enum
        new HydrationStatusKind("DEHYDRATED")
        new HydrationStatusKind("HYDRATING")
        new HydrationStatusKind("HYDRATED")
      HydrationStatusKind.finalize()


      ###*
      # Base class to help Documents keep track of their contained objects and to help Objects find their
      # immediate parent and root ancestor ModelObject containers.
      ####
      class ModelObject
        @getSuperType: -> return @ != ModelObject ? @.__super__.constructor: undefined
        @isSuperTypeOf: (target) ->
          throw new Error("target argument must be defined") unless target?
          throw new Error("target argument must be a ModelObject subtype") unless ModelObject.isSuperTypeOf(target)
          return @::.isPrototypeOf(target::)
        @isSubTypeOf: (target) ->
          throw new Error("target argument must be defined") unless target?
          throw new Error("target argument must be a ModelObject subtype") unless ModelObject.isSuperTypeOf(target)
          return target::.isPrototypeOf(@::)

        getType: => return @.__super__.constructor
# getSuperType: => return @getType().getSuperType()
        hasSuperTypeOf: (target) => return @getType().isSuperTypeOf(target)
        hasSubTypeOf: (target) => return @getType().isSubTypeOf(target)


      class RepositoryNodeImpl
        constructor: (params) ->
          {@nodeName, @parentNode, @documentKind, @hydrationMode, @hydrationStatus, @hydrationPromise} = params
          throw new Error "All non-root nodes must have a nodeName" unless @nodeName?.search(/^[-_A-Za-z0-9][-_A-Za-z0-9 ]*$/)
          throw new Error "All non-root nodes must have a parentNode" unless @parentNode? instanceof Folder
              throw new Error "All non-root nodes require a unique nodeName with respect to their parentNode and documentKind" \
            if @parentNode.contents[@documentKind][@nodeName]?
          throw new Error "" unless @documentKind?
          throw new Error "" unless @documentKind instanceof DocumentKind
          throw new Error "" unless @hydrationStatus == HydrationStatusKind.HYDRATING
          throw new Error "" unless @hydrationPromise? && (!@hydrationStatus? || @hydrationState == HydrationStatusKind.DEHYDRATED)
          throw new Error "" unless !@hydrationPromise? && (@hydrationStatus == HydrationStatusKind.HYDRATED)
          throw new Error "" unless @hydrationStatus != HydrationStatusKind.DEHYDRATED && @hydrationMode == HydrationModeKind.DEHYDRATED

        getNodeName: () -> return @nodeName
        getParentNode: () -> retutrn @parentNode.facade
        getDocumentKind: () -> return @documentKind

        encodeForUrl: () -> return @documentKind.encodeForUrl(@nodeName)
        asPathString: () -> return getNodeImpl().asPathString()

# If acquired by sync, a directory needs a stronger reason to be held in cache if not actively
# shown on a project navigator.  Addressed on by constructing party now.
# @parentNode.@contents[@documentKind][@nodeName] = @

# Object.describeProperties(@, {
#   documentKind: {value: documentKind}
#   nodeName: {value: nodeName, editable: true}
#   parentNode: {value: parentNode, editable: true}
#   hydrationMode: {value: hydrationMode, editable: true}
#   hydrationStatus: {value: hydrationState, editable: true}
#   hydrationPromise: {value: hydrationPromise, editable: true}
#});


      ###*
      # Private implementation method allowing both Folder and Document classes to trigger a folder contents
      # write.  This a transient implementation detail--when we decouple from $localForage, and the URL moves
      # back into a header sub-document of a MongoDB document, atomic move will entail updating that header,
      # instead of writing two distinct folder documents.
      #
      # NOTE: If I find its possible to update two Folder documents in a single transaction, the above may be
      # overstating the degree of temporariness to this after all...
      ####
      updateFolderContents = (pathString, contents) ->
        console.error("TODO: Call $localForage.writeItem!!")


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
      # for each registered DocumentKind.  This is reflected in the conventions governing URL construction:
      # -- For any URL path, every node except the last represents a Folder
      # -- The URL path for a Document has no trailing slash and is composed from a document name portion and a
      #    document kind portion.  The document kind portion matches the "Resource Extension" attribute of its
      #    DocumentKind enumeration value.  The document name and document kind are separated by a dot ('.').
      # -- No path element representing a folder uses an extension suffix.  If a folder node ends in '.foo', then
      #    .foo is the last part of the folder's name.
      # -- To disambiguate whether any suffix preceded by a dot is a type suffix or part of a folder's name,
      #    URLs that identify a Folder must end with a trailing '/', whereas URLs that represent a document
      #    must never terminate on a trailing '/'.
      # -- Because '.' is legal in a document name, it is not a legitimate character to use in a DocumentKind's
      #    "Resource Extension" value.  For the same reason, when parsing a document's path element, only the
      #    right-most '.' is treated as a separator.
      #
      # Folder and Document each define have two modes of operation--"hydrated" an "dehydrated".  These states
      # represent, respectively, "completely loaded" and "partially loaded" degrees of state completion.
      #
      # An dehydrated Folder can be used to idenitify itself in a catalog listing, but has incomplete information
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
      class FolderImpl extends RepositoryNode
        constructor: (params) ->
          folderParams = {documentKind: DocumentKind.FOLDER}
          folderDefaults = {hydrationMode: HydratedModeKind.DEHYDRATED}
          _.merge(folderDefaults, params, folderParams)
          ((folderDefaults.hydrationStatus != HydrationStatusKind.DEHYDRATED) || folderDefaults.hydrationPromise?)

          super(folderDefaults)

          Object.describeProperties(@, {
            contents: {value: {}}
            hydratedDescendants: {value: 0, editable: true}
          });
          Object.seal(@)

          # Allocate slots in the contents object for each distinct DocumentKind's namespace, sealing the root
          # collection of namespace-specific collections once they have all been populated.
          @contents = {}
          for documentKind in DocumentKind.values()
            @contents[@documentKind] = {}
          Object.seal(@contents)

          # A refcounter so we can know when a Node is safe to remove by dehydration of its parent
          @hydratedDescendants = 0


        ###*
        # Get a promise for reading this folder's metadata file with a list of its contents.
        #
        # NOTE: The folder entry reference has a different format than it does as a URL path token for
        #       documents.  Since I'm compensating for the idea of phasing Loopback in only after making
        #       a local storage based solution work, I cannot load a subset of a real document's properties
        #       along with its URL from a child document without doing a read per document.  Instead, the
        #       document's name-based reference, its UUID, and its create/modify timestamps are embedded
        #       in a line entry of its parent folder's document for now.  This compromises the ability to
        #       perform atomic writes, but since this is a temporary draft solution, I'm letting that go.
        ####
        hydrate: =>
# If a previous hydration promise is still here, there is no need to construct or process a new one.
          return @hydrationPromise if @hydrationPromise?

          ingestStoredFolderItem = (contents, folderItem) =>
            throw new Error("folderItem must be defined") unless folderItem?

            # Separate the timetstamps from the document reference, then parse the document reference.
            if m = folderItem.match(/^([A-Za-z0-9_]+.[A-Za-z0-9]+)(?:([a-fA-F0-9-]+),(1[0-9]{9,10}),(1[0-9]{9,10}))?$/)
              docToken = m[1]
              uuid = m[2]
              createdAt = m[3]
              modifiedAt = m[4]

              throw new Error("Document memento must be embedded in #{folderItem}") unless docToken?
              throw new Error("Uuid must be embedded in #{folderItem}") unless uuid?
              throw new Error("CreatedAt must be embedded in #{folderItem}") unless createdAt?
              throw new Error("ModifiedAt must be embedded in #{folderItem}") unless modifiedAt?

              nextNode = DocumentKind.decodeFromUrl(docToken)
              throw new Error("Document memento, #{docToken}, must include a name definition") unless nextNode.name?
              throw new Error("Document memento, #{docToken}, must include a document kind") unless nextNode.type?
              throw new Error("Document memento's kind (#{docToken}), must not be Folder") unless nextNode.type != DocumentKind.FOLDER

              # TODO: What about resolving conflicts between two recently created documents, only one saved?
              unless contents[nextNode.type][nextNode.name]?
# Create the internal representation
                nextInternal = new DocumentImpl({
                  uuid: uuid
                  parentNode: @
                  nodeName: nextNode.name
                  documentKind: nextNode.type
                  createdAt: createdAt
                  modifiedAt: modifiedAt
                  hydrationMode: HydrationModeKind.DEHYDRATED
                  hydrationStatus: HydrationStatusKind.DEHYDRATED
                  hydrationPromise: null
                })

                # With nothing thrown, confidently add the cached repository node state to our own collection of children
                contents[nextNode.type][nextNode.name] = nextInternal

                # Create a closure that will return the internal node when called, and construct the public
                # Document facade.
                getNodeImpl = () -> return nextInternal
                new Document({
                  getNodeImpl: getNodeImpl,
                  getRepositoryImpl: getRepositoryImpl
                })

# Finally, link the public facade to a lookup reference on the internal stat eobject.
            else
              nextNode = DocumentKind.decodeFromUrl(folderItem)
              throw new Error("Document memento, #{docToken}, must include a name definition") unless nextNode.name?
              throw new Error("Document memento, #{docToken}, must include a document kind") unless nextNode.type?
                  throw new Error("Document memento's kind (#{docToken}), must be Folder") \
                unless nextNode.type == DocumentKind.FOLDER

              unless contents[DocumentKind.FOLDER][nextNode.name]?
                new Folder({
                  parentNode: @
                  nodeName: nextNode.name
                  hydrationMode: HydrationModeKind.DEHYDRATED
                  hydrationStatus: HydrationStatusKind.DEHYDRATED
                  hydrationPromise: null
                })
            return

          # TODO: Verify whether or not .bind(@) is needed for ingestStoredFolderItem
          ingestStoredFolder = (data) =>
            ingestStoredFolderItem(item) for item in data

            if (@hydrationStatus != HydrationStatusKind.HYDRATED) {
              @hydrationStatus = HydrationStatusKind.HYDRATED
              @parentNode.incrementHydratedDescendents(1)
            }

            @hydrationMode = HydrationModeKind.ONLINE
            @hydrationPromise = $q.when(@)
            return @

          # On a failed ingestion, revert any partial changes to contents by cloning @ownContents, the set of
          # folders that predate the client has created for holding unsaved new and opened document locations
          onFailTraversal = (err) =>
            $log.error("Error blocked attempted hydration of #{@asPathString()}", err)
            @contents = _.clone(@ownContents)
            @hydrationStatus = HydrationStatusKind.DEHYDRATED
            @hydrationMode = HydrationModeKind.DEHYDRATED
            @hydrationPromise = null
            return $q.reject(new Error ("Error blocked attempted hydration of #{@asPathString()}: #{err}"))

          @hydrationPromise =
            $localStorage.getItem(@asPathString())
            .then(ingestStoredFolder)
            .catch(onFailTraversal)

        getContents: () -> return @contents
        mayParentDehydrate: () -> return @hydratedDescendants <= 0
        incrementHydratedDescendents = (increment) -> @hydratedDescendants = @hydratedDescendants + increment; return
        decrementHydratedDescendents = (decrement) -> @hydratedDescendants = @hydratedDescendants - decrement; return

        traverse: () =>
          createTraversalGenerator = (hydratedFolder) ->
            fixedContents = {}
            for documentKind, children of folder.contents
              fixedContents[documentKind] = Object.freeze(_.clone(children))
            Object.freeze(fixedContents)

            yield {
            documentKind: documentKind,
            children: ((items) ->
              yield item for item in items
              return)(children)
            } for documentKind, children of fixedContents

          return @hydrate().then(createTraversalGenerator)

        asPathString: => return "#{@parentNode.asPathString()}#{@nodeName}/"

        checkFolderMayLookup = (nodeName) ->
          throw new Error "nodeName argument must be defined" unless nodeName?
              throw new Error "#{nodeName} exceeds the maximum length of #{MAX_DOCUMENT_NAME_LENGTH}"
                throw new Error "#{nodeName} may only contain alphanumerics, period (.), and underscore (_)" \
            unless DOCUMENT_NAME_REGEX.match(nodeName)
          # throw new Error "#{@asPathString()} cannot lookup child nodes until its hydrated"
          #   unless @hydrationStatus == HydrationStatusKind.HYDRATED
          return

        hasChild: (documentKind, documentName) =>
          throw new Error("documentKind must be defined") unless documentKind?
          documentKind = DocumentKind.valueOf(documentKind)
          checkFolderMayLookup(documentName)

          @hydrate().then(
            (folder) -> return folder.contents[documentKind][documentName]?
          )

        getChild: (documentKind, documentName) =>
          checkFolderMayLookup(folderName)

          return async.series(
            (callback) ->
              @hydrate
              .then((folder) ->
                callback(undefined, folder.contents[documentKind][documentName])
                return
              )
              .catch((error) -> callback(error); return)
          )

          return @contents[DocumentKind.FOLDER][folderName] || undefined

        getDocument: (documentKind, documentName) =>
          throw new Error("documentKind must be defined") unless documentKind?
          documentKind = DocumentKind.valueOf(documentKind)
          checkFolderMayLookup(documentName)

          return @contents[documentKind][documentName] || undefined

        addFolder: (folderName) =>
          checkFolderMayLookup(folderName)
            throw new Error "#{@asPathString()} already has a subfolder named #{folderName}" \
          if @contents[DocumentKind.FOLDER][folderName]?

          retVal = new Folder name: folderName, parent: @
          updateFolderContents(@asPathString(), @contents)

          return retVal

        ensureFolder: (folderName) ->
          checkFolderMayLookup(folderName)

          retVal = @contents[DocumentKind.FOLDER][folderName]
          unless retVal?
            retVal = new Folder name: folderName, parent: @
            updateFolderContents(@asPathString(), @contents)

          return retVal

# NOTE: Documents are placed in a folders by constructor and move to another folder through Document API

        dehydrate: =>
          if @hydrationPromise? && @expanded == FolderExpandedKind.COLLAPSED
            doFlushCache = () =>
              @hydrationPromise = null
              @hydrationStatus = HydrationState.DEHYDRATED
              @contents = _.clone(@ownContent)
              return

            @hydrationPromise.then(doFlushCache).catch(doFlushCache).done()

          return

#     expand: (setValue) =>
#       return @expanded unless setValue?
#
#       if setValue == false
#         @expanded = FolderExpandKind.COLLAPSED
#       else if setValue == true
#         @expanded = FolderExpandKind.EXPANDING
#         @hydrate.then(
#           (folder) -> return folder.expanded = FolderExpandKind.EXPANDED
#         ).catch(
#           (error) => {
#             @expanded = FolderExpandKind.COLLAPSED
#             $log.error("Error loading children prevented marking #{asPathString()} as expanded: #{error}" )
#           } # .bind(@)
#         ).done()
#       else
#         throw new Error("Expand is assigned as a boolean value--true or false")


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
          if hydrationParams?
            {@rootObject, @importSources, @exportRoles} = hydrationParams

            if @rootObject? && @importSources? && @exportRoles?
# If an explicit caching mode is provided, it must not be an error state to be accepted.
              hydrationMode = params.hydrationMode

              if hydrationMode? && hydrationMode instanceof HydrationModeKind &&
                hydrationMode not in [
                  HydrationModeKind.DEHYDRATED, HydrationModeKind.ERROR, HydrationModeKind.FORBIDDEN
                ]
                super(
                  _.merge(
                    {
                      hydrationStatus: HydrationStatusKind.HYDRATED,
                      hydrationPromise: $q.when(@)
                    },
                    hydrationParams
                  )
                );
            else unless hydrationMode?
# Absent any explicit hydration mode, the default assumption is file content installed
# from offline cache without live synchronization, hence, a DIVERGENT graph that needs
# to undergo a reconciliation cycle.
              super(
                _.merge(
                  {
                    hydrationStatus: HydrationStatusKind.HYDRATED,
                    hydrationMode: HydrationModeKind.DIVERGENT,
                    hydrationPromise: $q.when(@)
                  },
                  hydrationParams
                )
              );
            else
              [@rootObject, @importSources, @exportRoles, @hydrationPromise] = [undefined, undefined, undefined, undefined]
              @hydrationStatus = HydrationStatusKind.DEHYDRATED

              @uuid = params.uuid
              throw new Error "All documents must define a UUID" unless @uuid?
              throw new Error "All documents must define a createdAt" unless @createdAt?
                  throw new Error "Any document with a modifiedAt must use a time later than createdAt" \
                if @modifiedAt? && @modifiedAt < @createdAt
# TODO: String lengths
# TODO: Timestamp formats/representation?

# throw new Error "All documents must define a docKind" unless @documentKind?
# throw new Error "All documents must define docKind as a DocumentKind" unless @documentKind instanceof DocumentKind
# throw new Error "All documents have a legitimate repository node name" \
#   unless @nodeName?.search(/^[_A-Za-z0-9][_A-Za-z0-9 ]*$/)
# throw new Error "All documents must have a parent" unless @parent? and @parent instanceof Folder
# throw new Error "All documents must have a unique name under a suitable namespace of their parent" \
#   if @parent.contents[@documentKind]?[@nodeName]?


# This relationship now set up during the instantiation process just after this constructor returns.
# @parent.contents[@documentKind] ?= {}
# @parent.contents[@documentKind][@nodeName] = @

      ###*
      # Concrete document type providers are required to override this method for access to the root object model.
      ####
# getRootObject: -> throw new Error "Concrete document subtypes are required to provide a root object instance"
      getCreatedAt: => return @createdAt
      getModifiedAt: => return @modifiedAt
      getRevision: -> return 'TBD'
      getExportRoleKinds: => return @getDocumentKind()?.getExportRoles()

# @getDocumentKind: -> return DocumentKind.lookupByDocumentClass(@)
# @getDocumentExportRoles:-> return @getDocumentKind()?.getExportRoles()

    ###*
    # Method that extracts externalizable identification of a document, with no references to runtime abstractions
    # like Studio, Folder, Document, Canvas, EditorState, or any of the Enum classes.  It is meant for capturing an
    # externalizable reference to the Document that generates it.
    ####
      asMemento: () ->
        retVal = {
          uuid: @uuid
          url: "repo://jchptf#{@parentFolder.asStringPath}#{@documentKind.encodeForUrl(@nodeName)}"
          documentKind: @documentKind.name
          createdAt: @createdAt
          modifiedAt: @modifiedAt
        }
        return Object.freeze(retVal)

    ###*
    # Method for hydrating the Project's document model and returning a clone of that graph.  The Project
    # retains a what is used as a read-only copy of a document for dependency and rollback use cases, while
    # the deep clone provided for encapsulation by an AbstractCanvas subtype remains mutable.
    ####
      hydrate: () ->
# if @hydrationStatus return _.deepClone(@abstractDocument)
        return @hydrationPromise if @hydrationPromise

        @hydrationPromise =
          $localForage.getItem(
            "#{@docKind.toString()}::#{@uuid}"
          ).then(
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

      rename: (newName) =>
        if @name == newName then return
        newNameNode = @documentKind
        .
        throw new Error("Name not available") if @parent.contents[documentKind][newName]?

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


      class RepositoryImpl



      class RepositoryNode
        getNodeImpl = {}
        # getRespositoryImpl = {}

        constructor: (params)
# {getNodeImpl, getRepositoryImpl} = params
          {getNodeImpl} = params

# This is done for us now even.
# getNodeImpl().facade = @

        getNodeName: () -> return getNodeImpl().nodeName
        getParentNode: () -> return getNodeImpl().parentFolder.facade
        getDocumentKind: () -> return getNodeImpl().documentKind
        getCreatedAt: () -> return getNodeImpl().createdAt
        getModifiedAt: () -> return getNodeImpl().modifiedAt

        asMemento: () -> return getNodeImpl().asMemento()
        asFolderPath: () -> return getNodeImpl().asFolderPath()

        hydrate: () -> return getNodeImpl().hydrate()
        dehydrate: () -> return getNodeImpl().dehydrate()

        traversalIterator(docKinds) -> return undefined
        listChildren(docKinds) -> return getNodeImpl().listChildren(docKinds)
        listChildrenSync(docKinds) -> return getNodeImpl().listChildrenSync(docKinds)
# TODO: lookupChild



      class Folder extends RepositoryNode
        getNodeImpl = null
        getRepositoryImpl

        constructor: (params) ->
          super(params)
          {getNodeImpl, getRepositoryImpl} = params
          getFolderImpl = getNodeImpl


      RootFolder
extends
Folder
  constructor: (@rootDir) ->
    @name = ""
    @parent = null

  asPathString: () => return @rootDir


class Repository


class Studio
  constructor: () ->
    @activeEditor = undefined
    @editorState = {}
    ,
    @studioLoaded = false

    async.each(
      CanvasKind.values(),
      (canvasKind, callback) =>
        $localForage.getItem("edit_state.#{canvasKind.name}")
        .then((storedState) =>
          switch
          when
          not storedState?
          error = new Error("Cached state for #{canvasKind.name} reloaded as undefined")
          when
          not storedState instanceof EditWorkspace
          error = new Error("Cached state for #{canvasKind.name} is not of type EditWorkspace")
          when
          not storedState.getCanvasKind()?
          error = new Error("Cached state for #{canvasKind.name} reloaded with undefined CanvasKind")
          when
          not storedState.getCanvasKind().equals(canvasKind)
          storedCanvasKind = storedState.getCanvasKind()
          error = new Error("Cached state for #{canvasKind.name} reloaded for #{storedCanvasKind.name}")
          else
          @editorState[canvasKind] = storedState
          return callback(undefined, storedState)

          callback(error)
          return $q.reject(error)
        )
        .catch((error) =>
          $log.warn("Error #{error} reading cached state for #{canvasKind.name} editor.  Creating a new cache.")
          try {
          storedState = new EditWorkspace(canvasKind)
          @editorState[canvasKind] = storedState
          return callback(undefined, storedState)
          } catch
          (error2) {
          msg =
          "Secondary error, #{error2}, on resorting to creating blank EditorState cache for #{canvasKind.name}!!"
          $log.fatal(msg);

          # Return non-rejected errors so we can selectively disable affected editors in the summary handler
          return [error, new Error(msg)]
          }
        ).done()
      (err, results) =>
        if err?
          $log.fatal("Editing envionment boostrap halted by unexpected error!  Unsafe to proceed!  #{err}")
          @editorState = {}

        else
        console.log(callback)
        return
    )


  startEditorOnline: (editorKind) ->
    EditorClass = @editorState.editClass
    nextEditor =
      @editorState[editorKind] ? @editorState[editorKind]
    :
    new EditorClass()

    switch nextEditor.

    startEditorOffline: (editorKind) -> return

    pauseEditor: (editorKind) -> return

    resetEditor: (editorKind) ->
      if @connectedEditors[editorKind]
        console.log('close editor')

    hasCanvas: (project) =>
      $log.warn("Currently no active editor.") unless @activeEditor?
      return @activeEditor?
      .
      @hasCanvas(project)

    openCanvas: (project) =>
      $log.warn("Currently no active editor.") unless @activeEditor?
      return @activeEditor?
      .
      @openCanvas(project)

    saveActiveCanvas: () ->

    saveEveryCanvas: () ->

    saveSelectedCanvas: () ->

      ###*
      #
      # @param canvasUuid {(String|String[])} Either a string (one UUID) or
      #   an array of strings (many UUIDs) to target the delete operation
      # @throw Error Fails if any of the input UUIDs either don't map to an
      #   open canvas or do map to one that isn't dirty.  Nothing is saveed
      #   in the event of a thrown Error.
      ####
    saveCanvas: (canvasUuid) ->

    revertActiveCanvas: () ->

    revertEveryCanvas: () ->

    revertSelectedCanvas: () ->

    revertCanvas: (canvasUuid) ->

    closeActiveCanvas: () ->
# TODO: Verify its Ok to return to feature's home page
      console.log('close canvas')

    closeEveryCanvas: () ->
      console.log('close canvas')

    closeSelectedCanvas: () ->
      console.log('close canvas')

# TODO: Any such use case?
    closeCanvas: (canvasUuid) ->
      console.log('close canvas')

    markActiveCanvas: () ->

    checkDependencies: () ->
      return


class AbstractEditor
  constructor: () ->
    @projectToCanvasMap = {}
    @isEditorActive = false
    @activeCanvas = null

  getEditorKind: -> throw new Error "AbstractEditor subtypes MUST implement getEditorKind()"
  getEditorName: => return @getEditorKind().name
  getDisplayName: => return @getEditorKind().displayName
  getInitialRouterState: => return @getEditorKind().rootState
  getSupportedCanvasKind: => return @getEditorKind().supportedCanvas

  isEditorActive: => return @isEditorActive
  hasActiveCanvas: => return @activeCanvas?
  getActiveCanvas: =>
    if @activeCanvas? then return @activeCanvas
    $log.warn("Harmless request to get active canvas when no canvas is active.")
    return undefined

  hasCanvasFor: (document) => return @projectToCanvasMap[document]?
  openCanvasFor: (document) =>
    throw new Error "document must be defined" unless document?
    throw new Error "document must be of type Document" unless document instanceof Document
    throw new Error "#{document.asPathString()} is already assigned to an open canvas" if @projectToCanvasMap[document]?

    # TODO Check Koast for authorization rules!
    documentKeyPath = document.asPath()
    openPromise = $localForage.getItem(documentKeyPath).then(
      (document) -> {
        return @projectToCanvasMap.[document] = @doCreateCanvas(@getCanvasKind(), document)
      }.bind(@))

  openCanvas: (project) =>
    throw new Error "In openCanvas(), project argument must be defined as an object of type Project." \
      unless project? && project instanceof Project

    foundCanvas = @hasCanvas(project)
    if foundCanvas?
      $log.warn("Redundant request to open #{project} in #{@getEditorName()}")
      return currentCanvas
    else
      # TODO Check Koast for authorization rules!
      documentKeyPath = project.asPath()
      openPromise = $localForage.getItem(documentKeyPath).then(
        (document) -> {
          retVal = @doCreateCanvas(@getCanvasKind(), project, document)
          projectToCanvasMap.[project] = retVal
          return retVal
        }. bind(@)
      )

      temp = new CanvasModel(data)

  closeCanvas: (canvas) =>

  # Editor extension events are delegate methods, not event handlers.  Note the use of
  # do/before/after (event delegation) instead of on/before/after (event listening)

  ###*
  # Override-mandatory method for editor service startup
  ####
  doStartEditor: =>
    throw new Error "#{@getEditorName()} editor must implement doStartEditor()"

  ###*
  # Override-mandatory method for editor service shutdown
  ####
  doStopEditor: -> return

  ###*
  # Override-mandatory method for editor service startup
  ####
  beforeStandByEditor: -> return

  ###*
  # Override-mandatory method for editor service shutdown
  ####
  afterResumeEditor: -> return

  ###*
  # Override-mandatory method for editor service startup
  ####
  doCreateCanvas: (canvasKind, project, document) =>
    throw new Error "#{@getEditorName()} editor must implement doCreateCanvas()"

  ###*
  # Override-mandatory method for editor service shutdown
  ####
  doDestroyCanvas: -> return

  ###*
  # Override-mandatory method for editor service startup
  ####
  beforeStandByCanvas: -> return

  ###*
  # Override-mandatory method for editor service shutdown
  ####
  afterResumeCanvas: -> return

  ###*
  # Override-mandatory method for creating the correct AbstractCanvas subtype to wrap an input Project this
  # Editor will subsequently be offered for editting.
  ####
  doCreateCanvas: (document) ->
    throw new Error("doCreateCanvas is defined but not implemented because specific editor subtypes are responsible for doing so.")

  ###*
  # Override-optional method to allow preparation of non-serializable content just before serialization is
  # invoked on the present thread, after it returns from this method.
  ####
  doPrepareForStandby: () -> return

  ###*
  # Override-optional module to allow any pending cleanup work to occur before a Canvas is discarded.
  ####
  doPrepareForClose: () ->


  # Folder and Document are UI runtime models that track hydration state.  At this level of abstraction, Document
  # is sufficiently abstract to not require subtypes.
  class AbstractCanvas
    constructor: (@docKind, @docModel) ->
      # Physical state--is the contents cache populated or empty.
      # @hydrationDeferal = null
      @hydrationPromise = null
      @hydrationStatus = false

  # Folder and Document are UI runtime models that track hydration state.  At this level of abstraction, Document
  # is sufficiently abstract to not require subtypes.
  class AbstractCanvas
    constructor: (@abstractDocument)
      # Physical state--is the contents cache populated or empty.
      # @hydrationPromise = null
      # @hydrationStatus = false


  STUDIO = new Studio()
  ROOT_FOLDER = new RootFolder('D:\\DevProj\\Git\\lb_express_sandbox\\junk\\reposit\\')


  return {
    Studio: Studio
    Folder: Folder
    Project: Project
    RootFolder: RootFolder
    CanvasKind: CanvasKind
    EditorKind: EditorKind
    CacheModeKind: CacheModeKind
    FolderExpansionKind: FolderExpansionKind
    ExportRole: ExportRole
    AbstractCanvas: AbstractCanvas
    AbstractEditor: AbstractEditor
    DOC_ROOT: DOC_ROOT
    STUDIO: STUDIO
  }
