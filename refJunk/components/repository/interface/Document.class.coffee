module.exports = Document

DomainObject = require('./DomainObject.class.coffee')

class Document extends DomainObject
  constructor: (_documentImpl) ->
    Object.defineProperty(
      @
      'documentImpl'
      {
        get: ModelUtils.restrictToClass(
          () -> return _documentImpl
          Document
        )
      }
    )

  getName: () -> return @documentImpl.getName()
  getParentNode: () -> return @documentImpl.getParentNode()
  getRepoNodeKind: () -> return @documentImpl.getRepoNodeKind()
  getModifiedAt: () -> return @documentImpl.getModifiedAt()
  getCreatedAt: () -> return @documentImpl.getCreatedAt()
  encodeForUrl: () -> return @documentImpl.encodeForUrl()
  asPathString: () -> return @documentImpl.asPathString()
