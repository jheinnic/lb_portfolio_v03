module.exports = DocumentSyncKind

Enum = require('../core/Enum.class.coffee')
HydrationStatusKind = require('./HydrationStatusKind.class.coffee')

class DocumentSyncKind extends Enum
  constructor: (name, modeStatus) ->
    super name, modeStatus

  ONLINE = new DocumentSyncKind('ONLINE', HydrationStatusKind.SYNCHRONIZED)

  READ_ONLINE = new DocumentSyncKind('READ_ONLINE', HydrationStatusKind.SYNCHRONIZED)

  ###*
  # OFFLINE indicates that the subject's synchronization link was severed at some point since the last time it was ONLINE.
  #
  # An OFFLINE document has not been changed by the client since it was last consistent with the repository's authoritative
  # instance, but it cannot be known whether or not there have been any changes at the repository since it was first disconnected.
  #
  # This mode can be reached directly from ONLINE by disconnecting from the repository without ending the edit session.
  # It can also be reached by indirectly from ONLINE by the state path of ONLINE -> FROZEN -> THAWING -> OFFLINE.
  ###
  OFFLINE = new DocumentSyncKind('OFFLINE', HydrationStatusKind.HYDRATED)

  ###*
  # DIRTY_ONLINE indicates that the editor content copy has been modified since the last time it was OFFLINE, and that the containing
  # document was previously ONLINE more recently than it was previously OFFLINE.
  #
  # A DIRTY_ONLINE document has unsaved changes and is owned by a document that has a copy of its state actively held in sync with
  # the repository that has not changed from the point where the client editor's copy first changed.
  #
  # This mode can only be reached directly from ONLINE under preconditions that the document's content has been changed in a
  # client editor.
  ###
  DIRTY_ONLINE = new DocumentSyncKind('DIRTY_ONLINE', HydrationStatusKind.HYDRATED)

  ###*
  # DIRTY_OFFLINE indicates that the editor content copy has been modified since the last time it was ONLINE, and that the containing
  # document was previously OFFLINE more recently than it was previously ONLINE.
  #
  # A DIRTY_OFFLINE document has unsaved changes and is owned by a document that has a copy of its content consistent with the point
  # when the document was taken offline.  No changes have been applied to the cached repository content copy since the editor's content
  # copy was first changed, but it cannot be known whether or not that is also true with regard to the repository's latest content.
  #
  # This mode can only be reached directly from OFFLINE under precondition that subject document's editor content copy is modified
  # by client-side editor.
  ###
  DIRTY_OFFLINE = new DocumentSyncKind('DIRTY_OFFLINE', HydrationStatusKind.HYDRATED)

  ###*
  # CHANGED_AT_REPO indicates that the repository content copy has synchronized changes since the last time the document was ONLINE.
  #
  # Although the document was open for editing when those changes arrived, the propagation should be straightforward since there were
  # no committed changes to the editor's model at that time.  A well implemented editor will accept propagation of these changes and
  # return its document to the ONLINE state before applying any additional changes from its client editor's end user.
  #
  # This mode be reached directly from ONLINE under precondition that subject document's repository content copy is modified by
  # events received from the repository synchronization channel.  It can also be reached indirectly by ingesting changes from the
  # OFFLINE state
  #
  # ONLINE -> CHANGED_AT_REPO
  #
  # OFFLINE -> INGESTING -> CHANGED_AT_REPO
  ###
  CHANGED_AT_REPO = new DocumentSyncKind('CHANGED_AT_REPO', HydrationStatusKind.HYDRATED)

  ###*
  # DIVERGED indicates that both repository content copy and editor content copy reflect changes since subject document was most
  # recently ONLINE.
  #
  # DIVERGED indicates a fork between the authoritative document content held by repository and unsaved changes owned by local
  # client-side editor.  The fork in change history must be reconciled before any client-side changes may be committed.  If the
  # reconciliation process yields conflicts that defy automatic resolution, then those conflicts must also be resolved manually
  # in order to unblock change commitment.
  #
  # There are three transition paths that can reach this mode.
  #
  # ONLINE -> DIRTY_ONLINE -> DIVERGED : Happens when synchronization receives notification of repository-side changes after already
  # applying editor-initiated changes to editor content copy, but before those changes to editor content copy are committed.
  #
  # ONLINE -> CHANGED_AT_REPO -> DIVERGED : Happens when client editor fails to reconcile repository changes from synchronization
  # stream before applying an edit from its end user.  Usually avoidable by well-implemented client editor, but always at least
  # a conceptual possibility.
  #
  # DIRTY_OFFLINE -> INGESTING -> DIVERGED : Happens when client editor makes offline changes that are discovered when
  # by ingesting incremental changes from repository in an attempt to reach DIRTY_ONLINE.
  ###
  DIVERGED = new DocumentSyncKind('DIVERGED', HydrationStatusKind.HYDRATED)

  ###*
  # IN_CONFLICT indicates that an attempt to automatically reconcile a change set fork (see DIVERGED) reported conflicts that
  # require a decision to be resolved.
  #
  # A document in this state cannot be committed to the repository.  It represents an attempt to return to a DIRTY_ONLINE state
  # by resolving divergent changes through MERGING that was halted by unresolvable conflicts.  After resolving all conflicts,
  # merge procedures may be resumed.  If successfully retried, the document will then return to DIRTY_ONLINE.
  #
  # There are three transition paths that can reach this mode.
  #
  # DIVERGED -> MERGING -> IN_CONFLICT : A client-side attempt to reconcile a fork in changes finds differences that cannot be
  # resolved by automatic merge policy.  Once a decision has been made it response to each reported conflict, the merge may be
  # attempted again.
  #
  # IN_CONFLICT -> MERGING -> IN_CONFLICT : Difficult to qualify this as a unique transition path.  It is what happens when a
  # merge attempt is resumed before resolving all previously reported conflicts.
  #
  # DIRTY_ONLINE -> COMMITTING -> IN_CONFLICT : Only differs from the first path by a timing issue.  In this case, an editor
  # attempts to commit changes after the repository accepted changes that created a divergent fork, but before synchronization
  # channel processed notification about those changes.  Instead, the fork was discovered inside repository code.  The repository
  # attempted to apply automatic merge policy, but found the same conflicts the client would have discovered if it had received
  # the overlapping change events from the repository in time.
  # TODO: When does the client state update to match any part of the automerge policy that succeeded?
  ###
  IN_CONFLICT = new DocumentSyncKind('IN_CONFLICT', HydrationStatusKind.HYDRATED)

  ###*
  # INGESTING is a transitional (HYDRATING) state used when asynchronously pulling change sets from repository.
  #
  # INGESTING can be entered from both the OFFLINE and the DIRTY_OFFLINE states.  On entering from the OFFLINE state, it can
  # complete its asynchronous operation by returning to either ONLINE or CHANGED_AT_REPO.  On entering from the DIRTY_OFFLINE
  # state, it resolved to either the DIRTY_ONLINE or DIVERGED.
  #
  # OFFLINE -> INGESTING -> ONLINE
  #
  # OFFLINE -> INGESTING -> CHANGED_AT_REPO
  #
  # DIRTY_OFFLINE -> INGESTING -> DIRTY_ONLINE
  #
  # DIRTY_OFFLINE -> INGESTING -> DIVERGED
  ###
  INGESTING = new DocumentSyncKind('INGESTING', HydrationStatusKind.HYDRATING)

  ###*
  # MERGING is a transitional state used while attempting to propagate changes from the repository content model to the editor
  # content model.  It applies both to any initial attempted merge as well as subsequent retry to resolve conflicts found during
  # previous attempts.
  #
  # Merges are either successful, resulting in a return to the DIRTY_ONLINE state, or fail with conflicts, yielding the IN_CONFLICT
  # state.  In the latter case, a collection of objects describing the necessary conflict resolutions is left populated.  In rare
  # cases, the only changes in the editor content model were also made in the repository content model.  In this rare but possible
  # corner case, the return mode is ONLINE.
  #
  # Initial merge attempts always begin from DIVERGED, and conflict resolution retries always begin from IN_CONFLICT.
  #
  # DIVERGED -> MERGING -> DIRTY_ONLINE
  #
  # DIVERGED -> MERGING -> IN_CONFLICT
  #
  # DIVERGED -> MERGING -> ONLINE
  #
  # IN_CONFLICT -> MERGING -> DIRTY_ONLINE
  #
  # IN_CONFLICT -> MERGING -> IN_CONFLICT
  #
  # IN_CONFLICT -> MERGING -> ONLINE
  ###
  MERGING = new DocumentSyncKind('MERGING', HydrationStatusKind.HYDRATING)

  ###*
  # To conserve resources, any READ_ONLY document may purge its content model and return to the NOT_OPEN state.  There are two
  # major problems with doing this for documents open for editing that THAWING helps to resolve.
  #
  # Discarding the cached content of an ONLINE or CHANGED_AT_REPO document would not lose any uncommitted changes, but it would lose
  # track of the fact that the document had even been open for editing.  If the UI were to reload, such documents would disappear
  # from any list of open documents and need to be re-opened.
  #
  # For any of the remaining active editor states (DIRTY_ONLINE, DIVERGED, and IN_CONFLICT), the additional complication of losing
  # uncommitted changes would be relevant.
  #
  # For these reasons, the five non-transitional online editor modes have three corresponding modes that are prefixed with FROZEN_.
  # The two states not directly reflected with a FROZEN variant, CHANGED_AT_REPO and DIVERGED have sufficient state to attempt a
  # merge and reach one of the remaining three supported states.  To keep the implementation simpler, documents in those two states
  # are required to attempt their pending merge first.
  #
  # The same THAWING transitional state is used for each of the three supported FROZEN variants, and it can only resolve to the
  # state the subject document was frozen from.
  #
  # FROZEN -> THAWING -> ONLINE
  #
  # FROZEN_DIRTY -> THAWING -> DIRTY_ONLINE
  #
  # FROZEN_IN_CONFLICT -> THAWING -> IN_CONFLICT
  ###
  THAWING = new DocumentSyncKind('THAWING', HydrationStatusKind.HYDRATING)

  ###*
  # COMMITTING is used while committing unsaved changes.  In most cases it resolved to the ONLINE state, but due to the reality
  # that any synchronization channel will always have some amount of latency, it may also resolve to the IN_CONFLICT state as
  # a result of unanticipated server-side merging.
  #
  # TODO: How does the client get the updated editor model for any server-side merging?
  #
  # DIRTY_ONLINE -> COMMITTING -> ONLINE
  #
  # DIRTY_ONLINE -> COMMITTING -> IN_CONFLICT
  ###
  COMMITTING = new DocumentSyncKind('COMMITTING', HydrationStatusKind.HYDRATING)

  ###*
  # NOT_OPEN is the state of any Document found by browsing folders without having accessed content in any way.
  ###
  NOT_OPEN = new DocumentSyncKind('NOT_OPEN', HydrationStatusKind.DEHYDRATED)

  ###*
  # The mode of any document paged out to local cache from an ONLINE mode.
  ###
  FROZEN = new DocumentSyncKind('FROZEN', HydrationStatusKind.DEHYDRATED)

  ###*
  # The mode of any document paged out to local cache from DIRTY_ONLINE mode.
  ###
  FROZEN_DIRTY = new DocumentSyncKind('FROZEN_DIRTY', HydrationStatusKind.DEHYDRATED)

  ###*
  # The mode of any document paged out to local cache from IN_CONFLICT mode.
  ###
  FROZEN_IN_CONFLICT = new DocumentSyncKind('FROZEN_IN_CONFLICT', HydrationStatusKind.DEHYDRATED)

  DocumentSyncKind.finalize()

