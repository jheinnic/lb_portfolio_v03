module.exports = HydrationStatusKind

Enum = require('../core/Enum.class.coffee')

###*
# Each repository node describes a discrete loadable resource from the repository.  Three kinds of resources can be found in a
# repository, each of which has its own RepositoryNode subtype.  RepositoryNode subtypes include Folder, Document, and MetaModel.
# *
# RepositoryNodes provide common access to both ephemeral name-based and persistent identity-based URLs for whatever repository
# object they describe.  They also provide a cache for storing a copy of that object's state.  The cache is not always populated,
# and values from the HydrationStatusKind enum are used to differentiate an unpopulated cache (DEHYDRATED) from a populated
# cache (HYDRATED) and a cache that is in the process of being populated asynchronously (HYDRATING).
# *
# For more context, see the enums for each of the three RepositoryNode subtypes.
# *
# @see FolderModeKind
# @see DocumentModeKind
# @see MetaModelModeKind
# @constructor
###
class HydrationStatusKind extends Enum

new HydrationStatusKind('DEHYDRATED')
new HydrationStatusKind('HYDRATING')
new HydrationStatusKind('HYDRATED')
new HydrationStatusKind('SYNCHRONIZING')
HydrationStatusKind.finalize()

# TBD comment Text:
# that may or may not be populated.  Every RepositoryNode subtype has an associated enumeration of operational modes that describe
#
# Each RepositoryNode encapsulates a Promise that is available any time the RepositoryNode's
# resources present in a repository, and each has  has a
# current operational
# mode that represents a
# description of:
# -- Whether or not it has populated is cache with state
# For folders, hydration describes the degree to which a folder's child nodes are completely cached.
# If a Folder has only been created to serve as a step in the path to some Document that has been
# edited, then it is likely just DEHYDRATED.  A folder becomes HYDRATED during any operation that
# requires knowledge about all of its children, such as traverseChildren().
# such as moving a node into a directory, or browsing its children.  All Folders are created in a
# DEHYDRATED state initially, although they may be created for a Repository service method that will
# require them to receive a request to hydrate shortly after creation.
