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
  CoreModelPackage, DocumentModelPackage, IdentityContext, $eventAggregator, $localForage, $q, $log
) ->
  _ = require('lodash')
  url = require('url')
  Enum = CoreModelPackage.Enum
  DocumentKind = DocumentModelPacakge.DocumentKind
  AbstractDocument = DocumentModelPackage.AbstractDocument

  class CacheModeKind extends Enum
    new CacheModeKind('NO_CACHE')
    new CacheModeKind('PAUSED')
    new CacheModeKind('ONLINE')
    new CacheModeKind('OFFLINE')
    new CacheModeKind('FORBIDDEN')
    new CacheModeKind('ERROR')
  CacheModeKind.finalize()

  class CanvasKind extends Enum
    @_DOC_TO_CANVAS = {}

    constructor: (canvasKindName, @canvasClass, @docKind) ->
      throw new Error "Canvas kind name must be defined" unless canvasKindName?
      throw new Error "Canvas kind name must be a string" unless 'string' == typeof canvasKindName?
      throw new Error "Canvas kind name cannot be blank" unless canvasKindName != ''

      throw new Error "Canvas model class cannot be null" unless @canvasClass?
      throw new Error "Canvas model class must extend AbstractCanvas" unless AbstractCanvas::.isPrototypeOf(@canvasClass::)

      throw new Error "Document kind cannot be null" unless @docKind?
      throw new Error "Document kind must be a value from DocumentKind" unless @docKind instanceof DocumentKind

      throw new Error "Document kind #{@docKind} is already registered with canvas #{CanvasKind._DOC_TO_CANVAS[@docKind]}" \
        if CanvasKind._DOC_TO_CANVAS[@docKind]?
      throw new Error "Canvas model classes #{@canvasClass.name} and #{preExisting.name} would overlap" \
        for preExisting in @values() \
          where @canvasClass == preExisting.canvasClass ||
          preExisting.canvasClass::.isPrototypeOf(@docClass::) ||
          @docClass::.isPrototypeOf(preExisting.canvasClass::)

      super(@canvasKindName)

    getCanvasClass: => @canvasClass
    getDocumentKind: => @docKind

    @lookupByCanvasClass: (canvasClass) ->
      throw new Error "Canvas class cannot be null" unless canvasClass?
      throw new Error "Canvas class #{canvasClass.name} must be subtype of AbstractCanvas" \
        unless AbstractCanvas::.prototype.isPrototypeOf(canvasClass::)

      retVal = (enumVal for enumVal in @values() \
        where canvasClass == enumVal.docClass || enumVal.docClass::.isPrototypeOf(docClass::))
      switch retVal.length
        when 0 then throw new Error "#{canvasClass.name} does not conform to the model type of any known CanvasKind: #{CanvasKind.values()}"
        when 1 then return retVal[0]
        else
          throw new Error(
            "#{canvasClass.name} conforms to the model type of more than one CanvasKind??  result=#{retVal}"
          )

    @lookupByCanvas: (canvas) ->
      throw new Error "Canvas cannot be null" unless canvas?
      throw new Error "Canvas must have AbstractCanvas as its type" unless canvas instanceof AbstractCanvas

      return CanvasKind.lookupByCanvasClass(document.constructor)

    @lookupByDocumentKind: (docKind) ->
      throw new Error "Document kind cannot be null" unless docKind?
      throw new Error "Document kind must have DocumentKind as its type" unless docKind instanceof DocumentKind

      return @_DOC_TO_CANVAS[docKind.name]

    @lookupByProject: (project) ->
      throw new Error "Project cannot be null" unless project?
      throw new Error "Project must have Project as its type" unless project instanceof Project

      return CanvasKind.lookupByDocumentKind(project.docKind)


  class EditorKind extends Enum
    @_ROUTE_STATE_TO_EDITOR_KIND = {}

    constructor: (editorName, @displayName, @rootState, @canvasKind, @editorClass) ->
      throw new Error "Editor internal name may not be null" unless editorName?)
      throw new Error "Editor internal name may not be blank" unless editorName != '')

      throw new Error "Editor display name may not be null" unless @displayName?)
      throw new Error "Editor display name may not be blank" unless @displayName != '')

      throw new Error "Root state name may not be null" unless @rootState?)
      throw new Error "Root state name may not be blank" unless @rootState != '')

      throw new Error "Canvas kind must be defined" unless @canvasKind?)
      throw new Error "Canvas kind must be an CanvasKind value" unless @canvasKind instanceof CanvasKind)

      throw new Error "Editor class must be defined" unless @editorClass?)
      throw new Error "Editor class must be an AbstractEditor subclass" \
        unless AbstractEditor::.isPrototypeOf(@editorClass::))

      EditorKind._ROUTE_STATE_TO_EDITOR_KIND[@rootState] = @

      # TODO: All supported canvases must be found in CanvasKind or at least be of type CanvasKind
      super(editorName)

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

        # Physical state--is the contents cache populated or empty.
        # @hydrationDeferal = null
        @hydrationPromise = null
        @hydrated = false

        # UI state--is this folder expanded and therefore its contents shown in navigatior views.  Setting this
        # to true will prevent a timer-based flush of hydrate-cached contents from getting scheduled and from
        # doing anything should a previously scheduled content flusher fire while @expanded = true
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


  class RootFolder extends Folder
    constructor: (@rootDir) ->
      @name = ""
      @parent = null

    asFolderPath: () -> return @rootDir


  class Project
    constructor: (params) ->
      {@uuid, @name, @parent, @docKind, @createdAt, @modifiedAt, @docModel} = params
      throw new Error "All documents must define a UUID" unless @uuid?
      throw new Error "All documents must define a docKind" unless @docKind?
      throw new Error "All documents must define docKind as a DocumentKind" unless @docKind instanceof DocumentKind
      throw new Error "All documents must define a createdAt" unless @createdAt?
      throw new Error "All documents have a legitimate name" unless @name?.search(/^[_A-Za-z0-9][_A-Za-z0-9 ]*$/)
      throw new Error "All documents must have a parent" unless @parent?.instanceof Folder
      throw new Error "All documents must have a unique name under their parent" \
        if @parent.contents[@docKind]?[@name]?

      # TODO: String lengths
      # TODO: timestamp format and modifiedAt is either undefined or later than createdAt.
      throw new Error "All documents must define createdAt in a wellformed way" unless @createdAt?

      @parent.contents[@docKind] ?= {}
      @parent.contents[@docKind][@name] = @
      @hydrated = @docModel? && @docModel.getRootObject()?
      @hydrationPromise = undefined

    ###*
    # Method for hydrating the Project's document model and returning a clone of that graph.  The Project
    # retains a what is used as a read-only copy of a document for dependency and rollback use cases, while
    # the deep clone provided for encapsulation by an AbstractCanvas subtype remains mutable.
    ####
    open: () ->
      if @hydrated return _.deepClone(@docModel)
      @hydrationPromise =
        $localForage.getItem(
          "#{@docKind.toString()}::#{@uuid}"
        ).then(
          return (docModel) ->
            $log.error("Document model cannot be null") unless docModel?
            $log.error("Root object cannot be null") unless docModel.getRootObject()?
            @docModel = _.deppClone(docModel)
            @hydrated = true
            @hydrationPromise = undefined

            return docModel
        )

    getHeader: () ->

    rename: (newName) =>
      if @name == newName then return

      documentKind = @abstractDoc.getDocumentKind()
      throw new Error("Name not available") if @parent.contents[documentKind][newName]?

    getCreatedAt: => return @abstractDoc.createdAt
    getModifiedAt: => return @abstractDoc.modifiedAt
    getRevision: -> return 'TBD'

    getDocumentModel: =>
      throw new Error "Document #{parent.getFolderPath()}#{@name} is not yet hydrated" unless @hydrated
      return @docModel

    getRootObject: =>
      throw new Error "Document #{parent.getFolderPath()}#{@name} is not yet hydrated" unless @hydrated
      return @docModel.getRootObject()


  class Studio
    # constructor: (IdentityContext, $eventAggregstor, $localStorage) ->
    constructor: () ->
      #Initialize folder-inheritted attributes to effective become a root folder.
      @name = ""
      @parent = null

      @activeEditor = undefined
      @editorState = { },
      @studioLoaded = false

      editNames = _.map( EditorKind.values(), (editorKind) -> return editorKind.name )
      $localForage.getItem("edit_state.#{editorKind.name}").then(
        (states) ->
          _.map(
            _.zip(EditorKind.values(), states),
            (editorName, storedState) ->
              if (storedState? && storedState)
                @editorState[editorName] = storedState
              else
                @editorState[editorName] =
      )

    startEditorOnline: (editorKind) ->
      EditorClass = @editorState.editClass
      nextEditor =
        @editorState[editorKind] ? @editorState[editorKind] : new EditorClass()

      switch nextEditor.

    startEditorOffline: (editorKind) -> return

    pauseEditor: (editorKind) -> return

    resetEditor: (editorKind) ->
      if @connectedEditors[editorKind]
        console.log('close editor')

    hasCanvas: (project) =>
      $log.warn("Currently no active editor.") unless @activeEditor?
      return @activeEditor?.@hasCanvas(project)

    openCanvas: (project) =>
      $log.warn("Currently no active editor.") unless @activeEditor?
      return @activeEditor?.@openCanvas(project)

    saveActiveCanvas: () ->

    saveEveryCanvas: () ->

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

  # AbstractEditor
  class AbstractEditor
    constructor: () ->
      @projectToCanvasMap = {}
      @isEditorActive = false
      @activeCanvas = null

      return

    getEditorKind: -> throw new Error "AbstractEditor subtype MUST implement getEditorKind()"
    getEditorName: => return @getEditorKind().name
    getDisplayName: => return @getEditorKind().displayName

    getInitialRouterState: => return @getEditorKind().rootState
    getSuppotedCanvasKind: => return @getEditorKind().supportedCanvas

    hasActiveCanvas: => return @activeCanvas?
    getActiveCanvas: =>
      if @activeCanvas? then return @activeCanvas
      $log.warn("Suspicious but harmless request to get active canvas when no canvas is active")
      return undefined
    isEditorActive: return @isEditorActive

    # Editor extension events are delegate methods, not event handlers.  Note the use of
    # do/before/after (event delgation) instead of on/onPre/onPost (event listening)

    ###*
    # Mandatory override method for editor service startup
    ####
    doStartEditor: =>
      throw new Error "#{getEditorName()} editor must implement doCrateCanvas()"

    ###*
    # Mandatory override method for editor service shutdown
    ####
    doStopEditor: => return

    ###*
    # Mandatory override method for editor service startup
    ####
    beforeStandByEditor: => return

    ###*
    # Mandatory override method for editor service shutdown
    ####
    afterResumeEditor: => return

    ###*
    # Mandatory override method for editor service startup
    ####
    doCreateCanvas: (canvasKind, project, document) =>
      throw new Error "#{@getEditorName()} editor must implement doCrateCanvas()"

    ###*
    # Mandatory override method for editor service shutdown
    ####
    doDestroyCanvas: => return

    ###*
    # Mandatory override method for editor service startup
    ####
    beforeStandByCanvas: => return

    ###*
    # Mandatory override method for editor service shutdown
    ####
    afterResumeCanvas: => return

    hasCanvas: (project) =>
      canvasTest = projectToCanvasMap[project]
      return canvasTest? ? canvasTest : false

    openCanvas: (project) =>
      throw new Error "In openCanvas(), project argument must be defined as an object of type Project." \
        unless project? && project instanceof Project

      foundCanvas = @hasCanvas(project)
      if foundCanvas?
        $log.warn("Redundant request to open #{project} in #{@getEditorName()}")
        return currentCanvas
      } else {
        # TODO Check Koast for authorization rules!
        documentKeyPath = project.asPath()
        openPromise = $localForage.getItem(documentKeyPath).then(
          (document) -> {
            retVal = @doCreateCanvas(@getCanvasKind(), project, document)
            projectToCanvasMap.[project] = retVal
            return retVal
          }.bind(@)
        )

        temp = new CanvasModel(data)

    closeCanvas: (canvas) =>

    ###*
    # Mandatorily overridable method for creating the correct AbstractCanvas subtype to wrap an input Project this
    # Editor will subsequently be offered for editting.
    ####
    doCreateCanvas: (project, document) ->
      throw new Error("doCreateCanvas is defined but not implemented because specific editor subtypes are responsible for doing so.")

    ###*
    # Optionally overridable method to allow preparation of non-serializable content just before serialization is
    # invoked on the present thread, after it returns from this method.
    ####
    doPrepareForStandby: () -> return

    ###*
    # Optionally overridable module to allow any pending cleanup work to occur before a Canvas is discarded.
    ####
    doPrepareForClose: () ->


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
