    asFolderPath: () -> return @rootDir


#  class Project
#    constructor: (params) ->
#      {@uuid, @name, @parent, @docKind, @createdAt, @modifiedAt, @abstractDocument} = params
#      throw new Error "All documents must define a UUID" unless @uuid?
#      throw new Error "All documents must define a docKind" unless @docKind?
#      throw new Error "All documents must define docKind as a DocumentKind" unless @docKind instanceof DocumentKind
#      throw new Error "All documents must define a createdAt" unless @createdAt?
#      throw new Error "All documents have a legitimate name" unless @name?.search(/^[_A-Za-z0-9][_A-Za-z0-9 ]*$/)
#      throw new Error "All documents must have a parent" unless @parent?.instanceof Folder
#      throw new Error "All documents must have a unique name under their parent" \
#        if @parent.contents[@docKind]?[@name]?
#
#      # TODO: String lengths
#      # TODO: timestamp format and modifiedAt is either undefined or later than createdAt.
#      throw new Error "All documents must define createdAt in a wellformed way" unless @createdAt?
#
#      @parent.contents[@docKind] ?= {}
#      @parent.contents[@docKind][@name] = @
#      @hydrated = @abstractDocument? && @abstractDocument.getRootObject()?
#      @hydrationPromise = undefined
#
#    ###*
#    # Method for hydrating the Project's document model and returning a clone of that graph.  The Project
#    # retains a what is used as a read-only copy of a document for dependency and rollback use cases, while
#    # the deep clone provided for encapsulation by an AbstractCanvas subtype remains mutable.
#    ####
#    open: () ->
#      if @hydrated return _.deepClone(@abstractDocument)
#      @hydrationPromise =
#        $localForage.getItem(
#          "#{@docKind.toString()}::#{@uuid}"
#        ).then(
#          (abstractDocument) ->
#            $log.error("Abstract document cannot be null") unless abstractDocument?
#            $log.error("Root object cannot be null") unless abstractDocument.getRootObject()?
#            @abstractDocument = _.deepClone(abstractDocument)
#
#            # AbstractDocument is currently the hydrated equivalent of Project, so to hydrate a
#            # Project, replace it with the exanded-scope AbstractDocument equivalent.
#            @parent.contents[@docKind][@name] = @abstractDocument
#            @hydrated = true
#            @hydrationPromise = undefined
#
#            return abstractDocument
#        )
#
#    getHeader: () ->
#
#    rename: (newName) =>
#      if @name == newName then return
#
#      documentKind = @abstractDoc.getDocumentKind()
#      throw new Error("Name not available") if @parent.contents[documentKind][newName]?
#
#    getCreatedAt: => return @abstractDoc.createdAt
#    getModifiedAt: => return @abstractDoc.modifiedAt
#    getRevision: -> return 'TBD'
#
#    getDocumentModel: =>
#      throw new Error "Document #{parent.getFolderPath()}#{@name} is not yet hydrated" unless @hydrated
#      return @abstractDocument
#
#    getRootObject: =>
#      throw new Error "Document #{parent.getFolderPath()}#{@name} is not yet hydrated" unless @hydrated
#      return @abstractDocument.getRootObject()


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
     {@uuid, @nodeName, @parentFolder, @documentKind, @createdAt, @modifiedAt, @rootObject, @importSources, @exportRoles} = params
       # Options #1 :: Store decomposed, derive URL when/if needed
       throw new Error "All documents must define a UUID" unless @uuid?
       throw new Error "All documents must define a docKind" unless @documentKind?
       throw new Error "All documents must define docKind as a DocumentKind" unless @documentKind instanceof DocumentKind
       throw new Error "All documents must define a createdAt" unless @createdAt?
       throw new Error "All documents have a legitimate repository node name" \
         unless @nodeName?.search(/^[_A-Za-z0-9][_A-Za-z0-9 ]*$/)
       throw new Error "All documents must have a parent" unless @parent? and @parent instanceof Folder
       throw new Error "All documents must have a unique name under a suitable namespace of their parent" \
         if @parent.contents[@documentKind]?[@nodeName]?

       # TODO: String lengths
       # TODO: timestamp format and modifiedAt is either undefined or later than createdAt.
       throw new Error "All documents must define createdAt in a wellformed way" unless @createdAt?

       @parent.contents[@documentKind] ?= {}
       @parent.contents[@documentKind][@nodeName] = @
       @hydrated = @rootObject? && @importSources? && @exportRoles?
       @hydrationPromise = undefined

        ###*
        # Method for hydrating the Project's document model and returning a clone of that graph.  The Project
        # retains a what is used as a read-only copy of a document for dependency and rollback use cases, while
        # the deep clone provided for encapsulation by an AbstractCanvas subtype remains mutable.
        ####
        open: () ->
          if @hydrated return _.deepClone(@abstractDocument)
          @hydrationPromise =
            $localForage.getItem(
              "#{@docKind.toString()}::#{@uuid}"
            ).then(
              (abstractDocument) ->
                $log.error("Abstract document cannot be null") unless abstractDocument?
                $log.error("Root object cannot be null") unless abstractDocument.getRootObject()?
                @abstractDocument = _.deepClone(abstractDocument)
#
                # AbstractDocument is currently the hydrated equivalent of Project, so to hydrate a
                # Project, replace it with the exanded-scope AbstractDocument equivalent.
                @parent.contents[@docKind][@name] = @abstractDocument
                @hydrated = true
                @hydrationPromise = undefined
#
                return abstractDocument
            )
#
        getHeader: () ->
#
        rename: (newName) =>
          if @name == newName then return
          newNameNode = @documentKind.
          throw new Error("Name not available") if @parent.contents[documentKind][newName]?

       # Option #2 :: Store combiner URL, parse to fields at load time.
       # throw new Error "#{@nodeUrl} is not a valid repository node URL" unless @nodeUrl?
       # parsedUrl = url.parse(@nodeUrl)
       # throw new Error "#{@nodeUrl} is not a valid repository node URL" unless parsedUrl?

       # throw new Error "Repository node URL #{@nodeUrl} lacks objdoc: protocol" unless parsedUrl.protocol == 'objdoc:'
       # throw new Error "Repository node URL #{@nodeUrl} contains an unsupported search string" if parsedUrl.search?
       # throw new Error "Repository node URL #{@nodeUrl} contains an unsupported hash string" if parsedUrl.hash?
       # throw new Error "Repository node URL #{@nodeUrl} contains an unsupported credential" if parsedUrl.auth?
       # throw new Error "Repository node URL #{@nodeUrl} contains an unsupported port" if parsedUrl.port?
       # throw new Error "Repository node URL #{@nodeUrl} contains an unsupported hostname" if parsedUrl.hostname != 'jchptf'
       # throw new Error "Repository node URL #{@nodeUrl} lacks non-empty pathname" unless parsedUrl.pathname != '/'
    asPathString: => return if @parentFolder? then "#{@parent.asPathString()}#{@name}/" else

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

    ###*
    # Concrete document type providers are required to override this method for access to the root object model.
    ####
    # getRootObject: -> throw new Error "Concrete document subtypes are required to provide a root object instance"
    getDocumentModel: ->
      throw new Error "Document #{parentFolder.getFolderPath()}#{@name} is not yet hydrated" unless @hydrated
      return @
    getRootObject: ->
      throw new Error "Document #{parentFolder.getFolderPath()}#{@name} is not yet hydrated" unless @hydrated
      return @getRootObject()
    getCreatedAt: => return @abstractDoc.createdAt
    getModifiedAt: => return @abstractDoc.modifiedAt
    getRevision: -> return 'TBD'
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


  class AbstractEditor
    constructor: () ->
      @projectToCanvasMap = {}
      @isEditorActive = false
      @activeCanvas = null

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
    constructor: (@abstractDocument)
        # Physical state--is the contents cache populated or empty.


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

