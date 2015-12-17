Enum = require('./enum').Enum

class BaseEnum extends Enum
  @_ABSTRACT = true
  # BaseEnum._ABSTRACT = true

class RealEnum extends Enum

class SimpleEnum extends Enum
  @_ABSTRACT = false
  # SimpleEnum._ABSTRACT = false

class SubOneEnum extends BaseEnum
  @_ABSTRACT = false
  # SubOneEnum._ABSTRACT = false

class SubTwoEnum extends BaseEnum
  @_ABSTRACT = false
  # SubTwoEnum._ABSTRACT = false

class SubThreeEnum extends BaseEnum
  @_ABSTRACT = true
  # SubThreeEnum._ABSTRACT = true

class BadSubTwoSubOneEnum extends SubTwoEnum
  @_ABSTRACT = true
  # BadSubTwoSubOneEnum._ABSTRACT = true

class BadSubTwoSubTwoEnum extends BadSubTwoSubOneEnum
  @_ABSTRACT = false
  # BadSubTwoSubTwoEnum._ABSTRACT = false

class SubThreeSubOneEnum extends SubThreeEnum

class SubThreeSubTwoEnum extends SubThreeSubOneEnum
  @_ABSTRACT = false
  # SubThreeSubTwoEnum._ABSTRACT = false

class SubThreeSubThreeEnum extends SubThreeEnum
  @_ABSTRACT = false
  # SubThreeSubThreeEnum._ABSTRACT = false

class BadRealOneEnum extends RealEnum
  @_ABSTRACT = true
  # BadRealOneEnum._ABSTRACT = true

class BadRealTwoEnum extends BadRealOneEnum
  @_ABSTRACT = false
  # BadRealTwoEnum._ABSTRACT = false

class BadRealThreeEnum extends RealEnum

exports.Enum = Enum
exports.RealEnum = RealEnum
exports.BadRealOneEnum = BadRealOneEnum
exports.BadRealTwoEnum = BadRealTwoEnum
exports.BadRealThreeEnum = BadRealThreeEnum
exports.BaseEnum = BaseEnum
exports.SubOneEnum = SubOneEnum
exports.SubTwoEnum = SubTwoEnum
exports.BadSubTwoSubOneEnum = BadSubTwoSubOneEnum
exports.BadSubTwoSubTwoEnum = BadSubTwoSubTwoEnum
exports.SubThreeEnum = SubThreeEnum
exports.SubThreeSubOneEnum = SubThreeSubOneEnum
exports.SubThreeSubTwoEnum = SubThreeSubTwoEnum
exports.SubThreeSubThreeEnum = SubThreeSubThreeEnum
exports.tryIt = (clazz) ->
  retVal = {}
  try
    new clazz('ALPHA')
    new clazz('BETA')
    clazz.finalize()
    # console.log 'Created values with ' + clazz.name
    # console.log clazz
    retVal[clazz.name] = clazz
    return retVal
  catch e
    # console.log 'Failed to create values with ' + clazz.name
    # console.log clazz
    # console.log e
    retVal[clazz.name] = e.stack
    return retVal
