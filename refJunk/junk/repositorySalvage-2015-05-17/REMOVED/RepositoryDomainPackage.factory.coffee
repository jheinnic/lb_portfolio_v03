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


#  class Project
#    constructor: (params) ->
#      {@uuid, @name, @parent, @docKind, @createdAt, @modifiedAt, @abstractDocument} = params
#      throw new Error "All documents must define a UUID" unless @uuid?
#      throw new Error "All documents must define a docKind" unless @docKind?
#      throw new Error "All documents must define docKind as a DocumentKind" unless @docKind instanceof DocumentKind
#      throw new Error "All documents must define a createdAt" unless @createdAt?
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
#      return DocumentKind.lookupByDocumentClass(repoNode.abstractDocument.constructor)  ###*
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
#      return DocumentKind.lookupByDocumentClass(repoNode.abstractDocument.constructor)#      throw new Error "All documents have a legitimate name" unless @name?.search(/^[_A-Za-z0-9][_A-Za-z0-9 ]*$/)
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
       throw new Error "All documents have a legitimate repository node name" unless @nodeName?.search(/^[_A-Za-z0-9][_A-Za-z0-9 ]*$/)
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
        throw new Error "All documents have a legitimate repository node name" unless @nodeName?.search(/^[_A-Za-z0-9][_A-Za-z0-9 ]*$/)
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
      # @hydrationPromise = null
      # @hydrated = false


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
