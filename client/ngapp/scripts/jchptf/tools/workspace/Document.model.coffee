'use strict'

_ = @_

# Interim NodeJS/BrowserJS compatibility glue
if !exports
  require = (name) =>
    @jchptfModels
  if !@jchptfModels
    exports = @jchptfModels = {}
else
  require('coffee-script/registry')

# Parent-to-child relationship has to be inferred from the child-to-parent relationships in order for
# object move to be treated atomically.  If the parent-to-child associations were stateful, moving
# would touch two collections and atomicity could not be guaranteed.

# Revision is unclear here.  It should be a self-describing meta-property.
class Document
  constructor: (params) ->
    {@docId, @docClass, @docPath, @revision, @rootObject, @importSources, @objectExports} = params

  qualifiedFolderName: => "#{@docClass}::#{@docName}"

# Revision is definitely stored here--it describes the last known version of a
# linked object
class ImportSource
  constructor(params) ->
    {@linkedDocument, @revision, @importProxies} = params

class RootObject
  constructor (params) ->
    {@modelClass, @ownedId} = params

class ImportProxy
  constructor(params) ->
    {@modelClass, @proxyId, @sourceId} = params

class ExportedObject
  constructor (params) ->
    {@modelClass, @objectId} = params

# This cannot store a persistent collection of children.  That must be inferred
# from the back-references to parent of all Documents and Folders...
#class Folder
#  constructor: (params)
#    {@parentFolder, @folderId, @name} = params
#
#  getSubfolders: () =>
#    _.clone @subFolders
#
#  getDocuments: () =>
#    _.clone @documents
#
#  addChildFolder: (folderName)
#    subFolder = new Folder
#      parentFolder: this
#      name: folderName
#      subFolders: []
#      documents: []
#    subFolder.parentFolder = @
#    @subFolders.push subFolder
#    return
#
#  addDocument: (document)
#    document.parentFolder = @
#    @.documents.push document
#    return

exports.Document = Document
exports.ImportSource = ImportSource
exports.ImportProxy = ImportProxy
exports.ExportedObject = ExportedObject
