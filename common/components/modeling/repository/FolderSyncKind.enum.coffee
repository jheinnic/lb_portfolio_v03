module.exports = FolderSyncKind

Enum = require('../core/Enum.class.coffee')
HydrationStatusKind = require('./HydrationStatusKind.class.coffee')

class FolderSyncKind extends Enum
  constructor: (name, modeStatus) ->
    super(name, modeStatus)

new FolderSyncKind('ONLINE', HydrationStatusKind.SYNCHRONIZED)
new FolderSyncKind('OFFLINE', HydrationStatusKind.HYDRATED)
new FolderSyncKind('DIVERGENT', HydrationStatusKind.HYDRATED)
new FolderSyncKind('INGESTING', HydrationStatusKind.INGESTING)
new FolderSyncKind('DEHYDRATED', HydrationStatusKind.DEHYDRATED)
FolderSyncKind.finalize()
