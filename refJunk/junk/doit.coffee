class Base
  @SIZE = 0

  console.log("In Base Class Init")

  constructor: (name) ->
    console.log("In constructor")
    Class = @getClass()
    SuperClass = @getSuperClass()
    AltSuperClass = @getClass().getSuperClass()
    console.log(Class)
    console.log(SuperClass)
    console.log(AltSuperClass)
    if @getClass() == Base
      throw new Error "Base must be instantiated through an immediate subclass, not directly."
    else if @getSuperClass() != Base
      delete Class.VALUES
      delete Class.SIZE
      throw new Error "Base must be instantiated through an immediate subclass, not a subclass of a subclass."
    else
      console.log("Calling initialize")
      Class.initialize()
      console.log("Returned from initialize")

    @name = name
    @ordinal = Class.SIZE
    Class.VALUES[name] = @
    Class.SIZE += 1

  @initialize: () ->
    console.log('In Base Initialize')
    console.log(@)
    console.log(@getSuperClass())

    if @SIZE == 0 && @getSuperClass() == Base && @ != Base
      @VALUES ?= { }
    return

  getClass: () -> @constructor
  getSuperClass: () -> @getClass().__super__?.constructor
  @getSuperClass: () -> @__super__?.constructor
  altSuperClass: () -> @getClass().getSuperClass()


class SubOne extends Base
  @initialize = Base.initialize.bind(SubOne)

class SubTwo extends Base
  @initialize = Base.initialize

class SubThree extends Base

exports.Base = Base
exports.SubOne = SubOne
exports.SubTwo = SubTwo
exports.SubThree = SubThree
