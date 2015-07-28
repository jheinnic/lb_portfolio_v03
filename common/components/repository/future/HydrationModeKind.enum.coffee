module.exports = HydrationModeKind

Enum = require('../core/Enum.class.coffee')
HydrationStatusKind = require('./HydrationStatusKind.class.coffee')

class HydrationModeKind extends Enum
  constructor: (name, @modeStatus, @inSync) ->
    throw new Error "Synchronization requires status == HYDRATED" if @inSync && @modeStatus != HydrationStatusKind.HYDRATED
    super name

  getHydrationStatus: () -> return @modeStatus
  isHydrated: () -> return @modeStatus == HydrationStatusKind.HYDRATED
  isDehydrated: () -> return @modeStatus == HydrationStatusKind.DEHYDRATED
  isHydrating: () -> return @modeStatus == HydrationStatusKind.HYDRATING
  isSynchronized: () -> return @inSync

new HydrationModeKind('ONLINE', HydrationStatusKind.HYDRATED, true)
new HydrationModeKind('OFFLINE', HydrationStatusKind.HYDRATED, false)
new HydrationModeKind('FROZEN', HydrationStatusKind.HYDRATED, false)
new HydrationModeKind('DIVERGENT', HydrationStatusKind.HYDRATED, false)
new HydrationModeKind('INGESTING', HydrationStatusKind.HYDRATING, false)
new HydrationModeKind('DEHYDRATED', HydrationStatusKind.DEHYDRATED, false)
new HydrationModeKind('FORBIDDEN', HydrationStatusKind.DEHYDRATED, false)
new HydrationModeKind('ERROR', HydrationStatusKind.DEHYDRATED, false)
HydrationModeKind.finalize()
