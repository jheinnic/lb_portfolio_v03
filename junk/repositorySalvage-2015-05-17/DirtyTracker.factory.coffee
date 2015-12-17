module.exports = DirtyTracker

DirtyTracker.$inject = ['DocumentModelPackage']

DirtyTracker = (DocumentModelPackage) ->
  {Studio, AbstractCanvas} = DocumentModelPackage

  workspaceHash = { }

  _markDirty = (workspace, dirtyObject) ->
    if !dirtyObject[workspace.dirtyTagProp]
      workspace.mementoHelper.storeMemento(dirtyObject)
      dirtyObject[workspace.dirtyTagProp] = true
      workspace.content.push(dirtyObject)
    return

  self =
    trackChanges: (workspaceId, dirtyTagProp, mementoHelper) ->
      workspace = workspaceHash[workspaceId]
      if workspace?
        throw new Error "Workspace already exists with workspaceId = #{workspaceId}"
      workspaceHash[workspaceId] =
        dirtyTagProp: dirtyTagProp,
        mementoHelper: mementoHelper
        content: []
      return

    markDirty: (workspaceId, dirtyObject) ->
      workspace = workspaceHash[workspaceId]
      if !workspace?
        throw new Error "No workspace found with workspaceId = #{workspaceId}"
      if angular.isArray(dirtyObject)
        dirtyObject.forEach( (v) -> _markDirty(workspace, v) )
      else
        _markDirty(workspace, dirtyObject)
      return

    clearDirtyList: (workspaceId, onPurgeFnName) ->
      workspace = workspaceHash[workspaceId]
      if ! workspace?
        throw new Error "No workspace found with workspaceId = #{workspaceId}"
      workspace.content.forEach (v) ->
        purgeFn = v[onPurgeFnName]
        purgeFn.call(v)
        Object.delete(v, workspace.dirtyTagProp)
        return

      Object.delete(workspaceHash, workspaceId)
      return retVal

  return self
