module.exports = RootFolderImpl

FolderImpl = require('./FolderImpl.class.coffee')

class RootFolderImpl extends FolderImpl
  constructor: () ->
    @name = ''
    @parent = null

  asPathString: () -> return '/'
