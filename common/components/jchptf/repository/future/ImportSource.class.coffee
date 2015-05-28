module.exports = ImportSource

# Revision is definitely stored here--it's used to remember the last known revision ID of a
# linked document, not to define its own revision.
class ImportSource
  constructor: (params) ->
    {@sourceDocument, @importRevision, @importedObjects} = params
