
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
    @lookupByCanvasClass: (canvasSubtype) ->
      throw new Error "Canvas class cannot be null" unless canvasSubtype?
      throw new Error "Canvas class #{canvasSubtype.name} must be subtype of AbstractCanvas" \
        unless AbstractCanvas::.prototype.isPrototypeOf(canvasSubtype::)
      retVal = (enumVal for enumVal in @values() \
        where canvasSubtype == enumVal.docClass || enumVal.docClass::.isPrototypeOf(docClass::))        )    @lookupByCanvasClass: (canvasSubtype) ->
      throw new Error "Canvas class cannot be null" unless canvasSubtype?
      throw new Error "Canvas class #{canvasSubtype.name} must be subtype of AbstractCanvas" \
        unless AbstractCanvas::.prototype.isPrototypeOf(canvasSubtype::)
      retVal = (enumVal for enumVal in @values() \
        where canvasSubtype == enumVal.docClass || enumVal.docClass::.isPrototypeOf(docClass::))
      switch retVal.length
        when 0 then throw new Error "#{canvasSubtype.name} does not conform to the model type of any known CanvasKind: #{CanvasKind.values()}"
        when 1 then return retVal[0]
        else
          throw new Error(
            "#{canvasSubtype.name} conforms to the model type of more than one CanvasKind??  result=#{retVal}"
          )

    @lookupByCanvas: (canvas) ->
      throw new Error "Canvas cannot be null" unless canvas?
      throw new Error "Canvas must have AbstractCanvas as its type" unless canvas instanceof AbstractCanvas

      return CanvasKind.lookupByCanvasClass(document.constructor)

    @lookupByDocumentKind: (documentKind) ->
      throw new Error "Document kind cannot be null" unless documentKind?
      throw new Error "Document kind must have DocumentKind as its type" unless documentKind instanceof DocumentKind

      return @_DOCUMENT_TO_CANVAS_KIND[documentKind]

    @lookupByAbstractDoc: (abstractDocument) ->
      throw new Error "Document cannot be null" unless abstractDocument?
      throw new Error "Document must have AbstractDocument as its type" unless abstractDocument instanceof AbstractDocument

      return CanvasKind.lookupByDocumentKind(project.documentKind)

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

