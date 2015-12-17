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
    addNameExtension: (name) => "#{name}.#{@rsrcExt}"

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
    asFolderPath: () -> return @rootDir

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







    verifyExportArguments: (exportRole, targetObject) ->
      MyDocumentKind = @getDocumentKind()
      MyExportRoleKind = MyDocumentKind.getExportRoles()

