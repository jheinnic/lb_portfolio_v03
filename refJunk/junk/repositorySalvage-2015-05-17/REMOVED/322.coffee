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

