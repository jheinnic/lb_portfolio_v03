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

 

