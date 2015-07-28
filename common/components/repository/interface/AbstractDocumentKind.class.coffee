Enum = require('../core/Enum.class.coffee')

class AbstractDocumentKind extends Enum
  @_ABSTRACT: true

  _RSRC_EXT_TO_DOCUMENT_KIND = {}
  _ROOT_TYPE_TO_DOCUMENT_KIND = {}

  constructor: (name, @rsrcExt, @rootObjectType) ->
    throw new Error "Resource extension cannot be null" unless @rsrcExt?
    if @rsrcExt instanceof String then @rsrcExt = @rsrcExt.valueOf()
    throw new Error "Resource extension must be a string" unless 'string' == typeof @rsrcExt?
    throw new Error "Resource extension cannot be blank" unless @rsrcExt != ''

    throw new Error "Root object type cannot be null" unless @rootObjectType?
    throw new Error "Root object type must extend DataObject" unless DataObject.isSuperTypeOf(@rootObjectType)

    # TODO: Export Roles are a second phase feature!
    # throw new Error "Export roles cannot be null" unless @exportRoles?
    # throw new Error "Export roles enum must extend ExportRoleKind" unless ExportRoleKind.isSuperTypeOf(@exportRoles)
    # throw new Error "Export roles cannot be abstract" if @exportRoles._ABSTRACT? && @exportRoles._ABSTRACT
    # @exportRoles = @exportRoles.finalize()

    if _RSRC_EXT_TO_DOCUMENT_KIND[@rsrcExt]?
      throw new Error "Resource extension #{@rsrcExt} is already taken by #{_RSRC_EXT_TO_DOCUMENT_KIND[@rsrcExt]}"
    if _ROOT_TYPE_TO_DOCUMENT_KIND[@rootObjectType]?
      throw new Error "Root object type #{@rootObjectType.name} is already used by #{_ROOT_TYPE_TO_DOCUMENT_KIND[@rootObjectType]}"
    if _.any( @values, (preExisting) ->
      candidateType = preExisting.rootObjectType
      return @rootObjectType.isSuperTypeOf(candidateType) || @rootObjectType.isSubtypeOf(candidateType)
    )
      throw new Error "Root object type #{@rootObjectType.name} is related by inheritance to other registered root object types."

    _RSRC_EXT_TO_DOCUMENT_KIND[@rsrcExt] = @
    _ROOT_TYPE_TO_DOCUMENT_KIND[@rootObjectType] = @

    super(params)

  getResourceExtension: => @rsrcExt
  getRootObjectType: => @rootObjectType
  # getExportRoles: => @exportRoles

  encodeForUrl: (name) =>
    throw new Error("name must be defined") unless name?
    throw new Error("name may not be blank") unless name != ''
    throw new Error("name may not exceed 56 characters in length") unless name.length <= MAX_NODE_NAME_LENGTH
    throw new Error("name must only contain alphanumerics, '.', and '_'") unless NODE_NAME_REGEX.test name

    return "#{name}.#{@rsrcExt}"

  ###*
  # Accepts partial url path string returned from a prior call to encodeForUrl with a name string and a valid
  # enum value and returns a those original inputs.
  ####
  @decodeFromUrl: (urlToken) ->
    throw new Error("urlToken must be defined") unless urlToken?
    [..., nodeName, rsrcExt] = urlToken.match(DOCUMENT_URL_TOKEN_REGEX)

    throw new Error("nodeName must be extractable from #{urlToken}") unless nodeName?
    throw new Error("nodeName from #{urlToken} may not be blank") unless nodeName != ''
    unless nodeName.length <= MAX_NODE_NAME_LENGTH
      throw new Error("nodeName from #{urlToken} may not exceed 56 characters in length")
    unless NODE_NAME_REGEX.test nodeName
      throw new Error("nodeName from #{urlToken} must only contain alphanumerics, '.', and '_'")

    throw new Error("nodeKind suffix must be extractable from #{urlToken}") unless rsrcExt?
    throw new Error("nodeKind suffix from #{urlToken} may not be blank") unless rsrcExt != ''
    unless rsrcExt.length <= MAX_NODE_KIND_SUFFIX_LENGTH
      throw new Error("nodeKind suffix from #{urlToken} may not exceed #{MAX_NODE_KIND_SUFFIX_LENGTH} characters in length")
    unless NODE_NAME_REGEX.test rsrcExt
      throw new Error("nodeKind suffix from #{urlToken} must only contain alphanumerics and '_'")

    nodeKind = AbstractRepoNodeKind.lookupByResourceException(rsrcExt)
    throw new Error "No registered AbstractRepoNodeKind uses #{rsrcExt} as its resource extension" unless nodeKind?

    return name: nodeName, type: nodeKind

  @doLookupByRootObjectType: _.memoize(
    (rootObjectType, enumValues) ->
      retVal = _.first(
        enumValues,
        (value) ->
          valRootType = value.rootObjectType
          return ((rootObjectType == valRootType) || (valRootType.isSuperTypeOf(rootObjectType)))
      )

      unless retVal?
        errMsg = "#{rootObjectType} does not satisfy root object type of any DocumentKind"
        $log.error errMsg
        throw new Error errMsg

      $log.info("Initial document type look up from #{rootObjectType} yielded #{retVal}")
      return retVal
  )

  @lookupByRootObjectType: (rootObjectType) ->
    throw new Error "Root object type must be defined" unless rootObjectType?
    unless DataObject.isSuperTypeOf(rootObjectType)
      throw new Error "Root object type #{rootObjectType.name} must be a subtype of DataObject"
    return doLookupByRootObjectType(rootObjectType, @values())

  @lookupByRootObject: (rootObject) ->
    throw new Error "Root object must be defined" unless rootObject?
    unless rootObject instanceof DataObject
      throw new Error "Root object #{rootObjectType.name} must be typed by a subtype of DataObject"
    return doLookupByRootObjectType(rootObject.getType(), @values())

module.exports = AbstractDocumentKind
