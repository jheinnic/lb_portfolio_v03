module.exports = RepositoryNodeImpl

ModelObject = require('../core/ModelObject.class.coffee')

class RepositoryNodeImpl extends ModelObject
  constructor: (params) ->
    {@name, @folder, @repoInstance} = params

#    switch
#      when ! @name?
#        throw new Error 'All repository nodes must define a name.'
#      when ! @name.search(NODE_NAME_REGEX)
#        throw new Error 'Repository node names may only include alphanumerics and underscore (\'_\').'
#      when ! @folder?
#        throw new Error 'Repository nodes must define a back reference to their parent folder.'
#      when ! @folder instanceof FolderImpl
#        throw new Error 'A repository node\'s parent back reference must be of type FolderImpl.'
#      when ! @repoInstanceKind?
#        throw new Error ''
#      when ! @repoNodeKind?
#        throw new Error ''
#      when ! @repoNodeKind instanceof @repoInstanceKind.getRepoNodeKind()
#        throw new Error ''
#      when @hydrationMode.isHydrating()
#        throw new Error 'Hydration mode HYDRATING is reserved for use during asynchronous hydration resolution'
#      when @hydrationPromise? && @hydrationMode.isNotHydrated()
#        throw new Error "Cannot populate hydration promise when mode is #{@hydrationMode}" unless @hydrationMode.isHydrated()
#      when ! (@hydrationPromise? || @hydrationMode.isDehydrated())
#        throw new Error "Must populate hydration promise when mode is #{@hydrationMode}" unless @hydrationMode.isDehydrated()

  getName: () -> return @name
  getFolder: () -> return @folder.facade
  getRepoInstance: () -> return @repoInstance



