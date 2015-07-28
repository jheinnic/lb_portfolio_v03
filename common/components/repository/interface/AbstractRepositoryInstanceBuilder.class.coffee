module.exports = AbstractRepositoryInstanceBuilder

class AbstractRepositoryInstanceBuilder
  constructor: () ->
    throw new Error("AbstractRepositoryInstanceBuilder is just an interface.  Subclass it without calling its constructor")

  addDocumentType: (typeName, extensionSuffix, rootModelType) ->
    console.log("addDocumentType(#{typeName}, #{extensionSuffix}, #{rootModelType})")
    return @
