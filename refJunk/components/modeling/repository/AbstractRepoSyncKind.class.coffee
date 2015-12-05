Enum = require('../core/Enum.class.coffee')
HydrationStatusKind = require('./HydrationStatusKind.class.coffee')

class AbstractRepoSyncKind extends Enum
  @ABSTRACT = true
  constructor: (name, @modeStatus) ->
    super(name)

  getHydrationStatus: () -> return @modeStatus

  isSynchronizing: () -> return @modeStatus == HydrationStatusKind.SYNCHRONIZING
  isNotSynchronizing: () -> return @modeStatus == HydrationStatusKind.SYNCHRONIZING

  isHydrated: () -> return @modeStatus == HydrationStatusKind.HYDRATED
  isNotHydrated: () -> return @modeStatus != HydrationStatusKind.HYDRATED

  isCached: () -> return @modeStatus == HydrationStatusKind.HYDRATED || @modeStatus == HydrationStatusKind.SYNCHRONIZING
  isNotCached: () -> return @modeStatus != HydrationStatusKind.HYDRATED && @modeStatus != HydrationStatusKind.SYNCHRONIZING

  isHydrating: () -> return @modeStatus == HydrationStatusKind.HYDRATING
  isNotHydrating: () -> return @modeStatus != HydrationStatusKind.HYDRATING

  isDehydrated: () -> return @modeStatus == HydrationStatusKind.DEHYDRATED
  isNotDehydrated: () -> return @modeStatus != HydrationStatusKind.DEHYDRATED

  isActive: @isNotDehydrated
  isNotActive: @isDehydrated

module.exports = HydrationModeKind
