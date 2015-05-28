module.exports = MetaModelSyncKind

Enum = require('../core/Enum.class.coffee')
HydrationStatusKind = require('./HydrationStatusKind.class.coffee')
AbstractRepoSyncKind = require('./AbstractRepoSyncKind.class.coffee')

class MetaModelSyncKind extends AbstractRepoSyncKind
  @ABSTRACT = false
  constructor: (name, modeStatus) ->
    super name, modeStatus

###*
# CURRENT indicates the client and repository have identical versions installed.
###
new MetaModelSyncKind('CURRENT', HydrationStatusKind.SYNCHRONIZING)

###*
# PATCHED indicates the client and repository have identical release versions installed, but one or the other have different
# patch revisions indicated.  No indication is attempted about which side has a later patch revision, as it cannot always be
# accurately inferred by comparing patch indicators.  Expected behavior should match expected behavior when CURRENT, with
# any perceived difference attributable to documentation corrections, bug fixes, and performance improvements.
###
new MetaModelSyncKind('PATCHED', HydrationStatusKind.SYNCHRONIZING)

###*
# BACKWARD_COMPATIBLE indicates that the client has an earlier version installed than the server, and the server has indicated
# it has a compatibility mode available.  No guarantee can be offered that all previous functionality is still supported, but
# the client may proceed at its own discretion with this warning in place.
#
# Opening a document that uses functionality added in the repository's version will fail with a version-related error.
#
# Saving a document that uses functionality not preserved from the client's version will fail with a version-related error.
###
new MetaModelSyncKind('BACKWARD_COMPATIBLE', HydrationStatusKind.HYDRATED)

###*
# UPGRADE_REQUIRED indicates that the client has an earlier version installed than the server, and the server has indicated it
# has no backwards compatibility with the client available.  This may be resolved by upgrading the client to a newer version,
# or reverting the server to an earlier version.
###
new MetaModelSyncKind('UPGRADE_REQUIRED', HydrationStatusKind.DEHYDRATED)

###*
# FORWARD_COMPATIBLE indicates that the client has a later version installed than the repository, and the client recognizes
# the repository's version as forward compatible.  No guarantee can be offered that all previous functionality is still
# supported, but the client may proceed at its own discretion with this warning in place.
#
# Opening a document that uses functionality added in the repository's version will fail with a version-related error.
#
# Saving a document that uses functionality not preserved from the client's version will fail with a version-related error.
#
# Unlike BACKWARD_COMPATIBLE, in this scenario the client may be able to be more proactive about protecting the user from
# encountering after-the-fact compatibility errors.
###
new MetaModelSyncKind('FORWARD_COMPATIBLE', HydrationStatusKind.HYDRATED)

###*
# DOWNGRADE_REQUIRED indicates that the client has a later version installed than the repository, and the client does not
# recognize the server's version as forward compatible.  This may be resolved by either reverting the client to an earlier
# version, or upgrading the repository to a newer version.
###
new MetaModelSyncKind('DOWNGRADE_REQUIRED', HydrationStatusKind.DEHYDRATED)

###*
# ERROR indicates that a problem unrelated to meta-model versions prevented the repository client from hydrating the subject
# meta-model and/or using it to establish repository access.
###
new MetaModelSyncKind('ERROR', HydrationStatusKind.DEHYDRATED)

MetaModelSyncKind.finalize()
