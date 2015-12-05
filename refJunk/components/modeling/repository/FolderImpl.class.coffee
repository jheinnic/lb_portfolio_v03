ModelUtil = require('../core/ModelUtil')
RepositoryNodeImpl = require('./RepositoryNodeImpl.class.coffee')

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
# TODO: Evaluate the option of providing a semantic facade for Import/Export functionality.
#
# @class {Folder}
###
class FolderImpl extends RepositoryNodeImpl
  repositoryService = undefined
  Object.defineProperty(
    FolderImpl::
    'repositoryService'
    {
      get: ModelUtil.restrictToClass(
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

module.exports = FolderImpl
