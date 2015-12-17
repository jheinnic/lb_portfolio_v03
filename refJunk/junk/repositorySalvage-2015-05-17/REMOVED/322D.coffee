'use strict'

module.exports = RepositoryModelPacakge

RepositoryModelPackage.$inject = [
  'CoreModelPackage', 'DocumentModelPackage', 'IdentityContext', '$eventAggregator', '$localForage', '$q'
]

###*
# RepositoryDomainPackage contains a domain model abstraction providing repository services for the models registered
# through the DocumentModelPackage data model's DocumentKind enumeration and AbstractDocument extension class.
#
# To develop new repository-based functionality, a modeler will typically create two extension pacakges, one for
# DocumentModelPackage, and one for RepositoryDomainPackage.
# ** The data model is defined by subclassing DocumentModelPackage.AbstractDocument and binding that subclass to a
#    new value of the DocumentModelPackage.DocumentKind enumeration.
# ** Access to repository services for CRUD and Synchronization services for that model are acquired through
#    the RepositoryDomain using a different pair of types.  AbstractCanvas is the extension class, and CanvasKind
#    is its enumeration.
#
# DocumentModelPackage introduces the "Document" and "ModelObject" abstractions.  It provides a facility for
# creating cross-document model re-use dependencies and provides metadata support for enabling controlled change
# reconciliation and state markers (e.g. stale dependencies and constraint violations).  Although it provides an
# interface for interacting with these bookkeeping elements, it defers conventions about how to reuse that inteface
# to a DomainModeling layer.  It focuses on the semantics of what dependecies and markers are, leaves how they
# apply to a modeled Document within each Document's extension contract.
#
# This is an intentional design decision.  It allows a Document to have a representation in multiple Domains.
# RepositoryDomainPackage is the only such domain at present, and it is oriented towards a client browser environment.
# What is done with composition here has a compatible analog in Loopback through the Local and Remote Model mixing
# classes.  Keep this in mind to stay prepared for a future Loopback migration path.  Let any semantics added
# to AbstractDocument and ModelObject subtypes constrain themselves to operations that would be available on the
# a base Model subtype.  Use the object graph rooted at an AbstractCanvas to reach methods that would only come into
# context through a LocalModel subclass of your common Model type that adds the Local mixin.
#
# What follows is a brief survey of the classes provided here:
#
# Repository -- This class is instantiated once as a singleton within the scope of the RepositoryDomainPackage itself.
# It encapsulates the remote API client and bridges its services to the modeler.  It doubles as a Folder, and
# implements that portion of its interface as Repository's root folder in that capacity.
#
# Folder -- A cache for intermediate grouping nodes form Repsitory URL paths.  No persistent representaiton, but
# facilitates the semantics of move and rename operations.
#
# Project -- Web client domain-level encapsulation of a repository Document at the catalog level.  Projects can be
# found by browsing through Folders or retrieved directly from the Repository by specifying either their logical URI
# or physical UUID in combintion with their domain model ProjectKind value's name.
# representation of a Document.  Opening a Project is how a developer adds Canvases to the Studio.
#
# Studio -- This class is also a singleton and represents client-side's global UI model.  It retains a memento or direct
# reference to the AbstractCanvas artifact for every Document considered open within the UI.  It points at the
# single specific 'Current' Canvas whose interface is currently active within the view.  Canvases that are open, but
# not current may be cached in memory or may have been serialized to local storage and reversibly replaced with a
# Memento Handle.  Dirty/Clean Canvas marking is done here.  An application-scoped event bus is also available.
# A global "current selection" poiner list is available here, but may be augmented by locally scoped selection models
# found in concrete Canvas models and/or their class types.
# TODO: Is there a StudioProvider for route registration?
#
# AbstractCanvas --
#
# Workspace -- A potential refactoring of the "live" elements of Studio to separate them those dealing with
#              book-keeping for inactive document editors (e.g. not the active view)
#
#
RepositoryModelPackage = (
  CoreModelPackage, DocumentModelPackage, IdentityContext, eventAggregator, $localForage, $log
) ->
  _ = require('lodash')
  url = require('url')
  Enum = CoreModelPackage.Enum

  class CacheModeKind extends Enum
    new CacheModeKind('NO_CACHE')
    new CacheModeKind('PAUSED')
    new CacheModeKind('ONLINE')
    new CacheModeKind('OFFLINE')
    new CacheModeKind('FORBIDDEN')
    new CacheModeKind('ERROR')
  CacheModeKind.finalize()

  class CanvasKind extends Enum
    @_DOCUMENT_TO_CANVAS_KIND = {}
    @_CANVAS_SUBTYPE_TO_KIND = {}

    constructor: (canvasKindName, @canvasSubtype, @documentKind) ->
      throw new Error "Canvas kind name must be defined" unless canvasKindName?
      throw new Error "Canvas kind name must be a string" unless 'string' == typeof canvasKindName?
      throw new Error "Canvas kind name cannot be blank" unless canvasKindName != ''

      throw new Error "Canvas model class cannot be null" unless @canvasSubtype?
      throw new Error "Canvas model class must extend AbstractCanvas" unless AbstractCanvas::.isPrototypeOf(@canvasSubtype::)

      throw new Error "Document kind cannot be null" unless @documentKind?
      throw new Error "Document kind must be a value from DocumentKind" unless @documentKind instanceof DocumentKind

      throw new Error "Document kind #{@documentKind} already registered to #{CanvasKind._DOCUMENT_TO_CANVAS_KIND[@documentKind]}" \
        if CanvasKind._DOCUMENT_TO_CANVAS_KIND[@documentKind]?
      throw new Error "Canvas subtype #{@canvasSubtype.name} already registered to #{CanvasKind._CANVAS_SUBTYPE_TO_KIND[@canvasSubtype]}" \
        where CanvasKind._CANVAS_SUBTYPE_TO_KIND[@canvasSubtype]?
      throw new Error "Canvas subtype #{@canvasSubtype.name} and #{preExisting.name} would overlap" \
        for preExisting in @values() \
          where preExisting.canvasSubtype::.isPrototypeOf(@canvasSubtype::) || @canvasSubtype::.isPrototypeOf(preExisting.canvasSubtype::)

      CanvasKind._DOCUMENT_TO_CANVAS_KIND[@documentKind] = @
      CanvasKind._CANVAS_SUBTYPE_TO_KIND[@canvasSubtype] = @
      super(@canvasKindName)

    getCanvasSubtype: => @canvasSubtype
    getDocumentKind: => @documentKind

    verifyExportArguments: (exportRole, targetObject) ->
      MyDocumentKind = @getDocumentKind()
      MyExportRoleKind = MyDocumentKind.getExportRoles()

  class EditorKind extends Enum
    @_ROUTE_STATE_TO_EDITOR_KIND = {}
    @_EDITOR_SUBTYPE_TO_KIND = {}
    @_CANVAS_TO_EDITOR_KIND = {}

    constructor: (editorName, @displayName, @rootState, @canvasKind, @editorSubtype) ->
      throw new Error "Editor internal name may not be null" unless editorName?)
      throw new Error "Editor internal name may not be blank" unless editorName != '')

      throw new Error "Editor display name may not be null" unless @displayName?)
      throw new Error "Editor display name may not be blank" unless @displayName != '')

      throw new Error "Root state name may not be null" unless @rootState?)
      throw new Error "Root state name may not be blank" unless @rootState != '')

      throw new Error "Canvas kind must be defined" unless @canvasKind?)
      throw new Error "Canvas kind must be an CanvasKind value" unless @canvasKind instanceof CanvasKind)

      throw new Error "Editor class must be defined" unless @editorSubtype?)
      throw new Error "Editor class must be an AbstractEditor subclass" \
        unless AbstractEditor::.isPrototypeOf(@editorSubtype::))

      EditorKind._ROUTE_STATE_TO_EDITOR_KIND[@rootState] = @
      EditorKind._EDITOR_SUBTYPE_TO_KIND[@editorSubtype] = @
      EditorKind._CANVAS_TO_EDITOR_KIND[@canvasKind] = @

      # TODO: All supported canvases must be found in CanvasKind or at least be of type CanvasKind
      super(editorName)

      if lenDiff <= 0
        console.warn "Suspiciously vacuous request to stop exporting unexported #{uuid} under #{@roleName}"
      else if lenDiff > 1
        console.error "Request to stop exporting #{uuid} under #{@roleName} removed #{lenBefore - lenAfter} values!!!"

  # Folder and Document are UI runtime models that track hydration state.  At this level of abstraction, Document
  # is sufficiently abstract to not require subtypes.
  class Folder
    constructor: (params) ->
      {@name, @parent} = params
        throw new Error "All non-root folders must have a name" unless @name?.search(/^[-_A-Za-z0-9][-_A-Za-z0-9 ]*$/)
        throw new Error "All non-root folders must have a parent" unless @parent? instanceof Folder
        throw new Error "All non-root folders must have a unique name with respect to their parent" \
          if @parent.contents[DocumentType.FOLDER][@name]?

        @parent.contents[DocumentType.FOLDER][@name] = @
        @contents = { }
        @contents[DocumentType.FOLDER] = { }

        # Physical state--is the cache unpopulated, fully populated, or being populated.
        @hydrated = false
        @hydrationPromise = null

        # UI state--is this folder shown expanded in the navigation panel?  True here blocks
        # any effort to discard watches that are unlikely yet not impossible.
        @expanded = false

    asPathString: => return if @parent? then "#{@parent.asPathString()}#{@name}/" else "/"

    expand: =>
      if @hydrated
        @expanded = true
        return true
      else
        return traverse().then(
          () -> {
            if @hydrated
              @expanded = true
              return true

            return false

    unexpand: =>
      @expanded = false
      # TODO: Set the cache purge clock
      return

    traverse: =>
      if @hydrated
        return _.clone(@contents)
      else if @hydrationPromise
        return @hydrationPromise

      # Get a promise for reading this folder's metadata file with a list of its contents.
      # NOTE: The folder entry reference has a different format than it does as a URL path token for
      #       documents.  Since I'm compensating for the idea of phasing Loopback in only after making
      #       a local storage based solution work, I cannot load a subset of a real document's properties
      #       along with its URL as a child of a parent folder URL.  Instead, the document's name-based
      #       reference (but not its UUID) is part of the line entry as are its created and modified
      #       timestamps.
      @hydrationPromise =
        $localStorage.getItem(
          @asPathString()
        ).then(
          @ingestStoredFolder
        )

    ingestStoredFolder = (data) =>
      @ingestStoredFolderItem(item) for item in data
      @hydrationPromise = null
      @hydrated = true

      return _.clone(@contents)

    saveEveryCanvas: () ->

    ###*
    # This method expands the project hierarchy cache to include metadata taken from a summary-level AbstractNode.
    # Summary in this case means that all properties of the AbstractNode and its AbstractDocument (if any) have been
    # read, but none of the associations from AbstractDocument were requested.
    #
    # TODO: Evolve this method to one that hydrates a folder by helping the service it supports query all immediate
    #       children of the target folder's URL and ingesting them all in a loop, finally marking the target folder
    #       as hydrated.  Use this method on expanding a folder node in the browse viewer, then return to unhydrated
    #       state within a short timeout from collapsing.
    ####
#     @ingestNode: (abstractNode) ->
#       # Parse the URL and use the result to locate its corresponding node in localstorage.
#       parsedUrl = url.parse(abstractNode.url)
#       if (parsedUrl.protocol != 'objdoc:')
#         throw new Error "Unknown URI protocol: #{parsedUrl.protocol}"
#
#       # Parse out the intermediate path names and build out the folder path.
#       currentNode = ROOT_FOLDER
#       pathNodes = parsedUrl.pathname?.split('/')[1..]
#       while pathTokens.length > 1
#         currentNode = Folder.ingestFolder currentNode, pathTokens.shift()
#
#       # If the last token ended with a slash, the URL is for a directory and there will be a blank node as the
#       # last entry because the path ended with a terminal '/'.  Otherwise, the terminal node is a name and a suffix
#       # that identifies its DocumentKind.
#       lastToken = pathTokens.shift()
#       if lastToken != ''
#         currentNode = Folder.ingestDocument currentNode, lastToken, abstractNode

    ingestFolder: (nodeName) =>
      # nextNode = DocumentType.fromExtension nodeString
      # throw new Error "Folder path nodes must always have folder type" unless nextNode.type == DocumentType.FOLDER
      return @contents[DocumentType.FOLDER][nodeName] || new Folder name:nodeName, parent:@

    ingestDocument: (nodeString, abstractDoc) =>
      nextNode = DocumentType.parseExtension(nodeString)
      throw new Error "Document path nodes must never have folder type" if nextNode.type == DocumentType.FOLDER
      return @contents[nextNode.type][nextNode.name]
      document = new Project name:nextNode.name, parent:currentFolder, abstractDoc: abstractDoc unless document?
      return document

    ingestStoredFolderItem: (folderItem) =>
      # Separate the timetstamps from the document reference, then parse the document reference.
      if m = folderItem.match(/([A-Za-z0-9_]{1,}.[A-Za-z0-9]{1,}(?(uuid),(1[0-9]{9,10}),(1[0-9]{9,10}),)?/)
        docToken   = m.1
        uuid       = m.2
        createdAt  = m.3
        modifiedAt = m.4

      nextNode = DocumentType.parseExtension(docToken)
      if nextNode.type == DocumentType.FOLDER
        return @contents[DocumentType.FOLDER][nextNode.name] || new Folder name:nextNode.name, parent:@
      else
        return @contents[nextNode.type][nextNode.name] || new Project {
          uuid: uuid
          name: nextNode.name
          parent: @
          docKind: nextNode.type
          createdAt: createdAt
          modifiedAt: modifiedAt
        }

    flushCache: =>
      switch
        when @expanded:
          # Folder is currently expanded--cannot flush cache!
        when @hydrated:
          @hydrated = false
          @contents = { }
          @contents[DocumentType.FOLDER] = { }
        when @hydrationPromise?:
          @hydrationPromise.then(
            (data) =>
              @hydrationPromise = null
              @hydrated = false
              @contents = { }
              @contents[DocumentType.FOLDER] = { }

              return []
        )

      return @

    closeCanvas: (canvas) =>

  class RootFolder extends Folder
    constructor: (@rootDir) ->
      @name = ""
      @parent = null

    asFolderPath: () -> return @rootDir

  # Folder and Document are UI runtime models that track hydration state.  At this level of abstraction, Document
  # is sufficiently abstract to not require subtypes.
  class AbstractCanvas
    constructor: (@docKind, @docModel) ->
        # Physical state--is the contents cache populated or empty.
        # @hydrationDeferal = null
        @hydrationPromise = null
        @hydrated = false


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
    AbstractCanvas: AbstractCanvas
    AbstractEditor: AbstractEditor
    DOC_ROOT: DOC_ROOT
    STUDIO: STUDIO
  }

