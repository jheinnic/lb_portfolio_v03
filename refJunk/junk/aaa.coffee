class Base
  # @VALUES = {}
  @SIZE = 0

  constructor: (items) ->
    Class = @getClass()
    Class.VALUES[item] = item for item of items
    Class.SIZE += items.length

  @initialize: () ->
    @VALUES = {}
    console.log("Initializing Base?")
    delete @.initialize

  getClass: ->
    this.constructor

  @alertOne: () ->
    console.log('alert one')

  alertTwo: () ->
    console.log('alert two')

class MidBase extends Base
  constructor: (items) ->
    super(items)
    @s = {}
    @t = {}

    console.log(@constructor)
    console.log(@constructor.prototype)
    console.log(@constructor.prototype)
    console.log(@constructor::)
    for key, fn of @constructor.prototype
      console.log(key)
      console.log(@constructor.prototype[key])
      console.log(fn)
      continue unless typeof fn is 'function'
      continue if key in ['constructor', 'initialize'] or key[0] is '_'
      @s[key] = fn.bind?(@) || _.bind(fn, @)
    #for key, fn of @::prototype
    #  continue unless typeof fn is 'function'
    #  continue if key in ['constructor', 'initialize'] or key[0] is '_'
    #  @t[key] = fn.bind?(@) || _.bind(fn, @)

console.log('Before Foo')
console.log(Base)

class Foo extends MidBase

class Bar extends MidBase
  alertThree: () ->
    console.log('alert three')

console.log("Created subclasses")
console.log(Base)
console.log(Foo)
console.log(Bar)

f1a = new Foo([3]);
console.log('f1a', f1a)
console.log(Base)
console.log(Foo)
console.log(Bar)
f1 = new Foo(['A', 'B', 'C'])
console.log('After Foo, Before Bar')
console.log(Base)
console.log(Foo)
console.log(Bar)
f2 = new Bar(['X', 'Y', 'Z'])
console.log('After Foo and Bar')
console.log(Base)
console.log(Foo)
console.log(Bar)


f3 = new Bar(['L', 'M', 'N', 'O', 'P'])
f4 = new Foo(['one'])
console.log('After f3')
console.log(Base)

console.log(Foo)
console.log(Bar)

class Baz extends Bar
console.log(Baz)

f5 = new Baz(['apple', 'banana'])
console.log(f5)
console.log(Baz)

exports.Base = Base
exports.MidBase = MidBase
exports.Foo = Foo
exports.Bar = Bar
exports.Baz = Baz
