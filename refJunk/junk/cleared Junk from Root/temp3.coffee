_ = require('lodash')

class Foo
  constructor: () ->
    _value1 = 'value'
    _value2 = 'value'
    _value3 = 'value'
    _value4 = 'value'
    _value5 = 'value'

    getValue1 = () ->
      isMyFn(getValue1.caller)
      return _value1

    Object.defineProperty( @, 'value1', {
      get: getValue1
    })

    Object.defineProperty( @, 'value2', {
      get: protectGet(() -> return _value2)
    })

    getValue3 = () ->
      isMyFn2(getValue3.caller)
      return _value3

    Object.defineProperty( @, 'value3', {
      get: getValue3
    })

    Object.defineProperty( @, 'value4', {
      get: protectGet2(() -> return _value4)
    })

    Object.defineProperty( @, 'value5', {
      get: protectGet3(() -> return _value5)
      set: protectGet3((x) -> _value5 = x)
    })


  protectGet = (getter) ->
    getValue = () ->
      caller = getValue.caller
      for myFn of Foo::
        if Foo::[myFn] == caller
          return getter()
      throw new Error("Illegal Call")
    return getValue
  
  isMyFn = (testFn) =>
    for myFn of Foo::
      if Foo::[myFn] == testFn then return
    throw new Error("Illegal Call")

  protectGet2 = (getter) ->
    getValue = () ->
      if getValue.caller in _.valuesIn(Foo::)
        return getter()
      throw new Error("Illegal Call")
    return getValue
  
  isMyFn2 = (testFn) =>
    if testFn in _.valuesIn(Foo::) then return
    throw new Error("Illegal Call")

  protectGet3 = (getter) ->
    getValue = () ->
      if getValue.caller in _.valuesIn(Foo::)
        return getter.apply(@, arguments)
      throw new Error("Illegal Call")
    return getValue
  
  v1: () ->
    return @value1
  v2: () ->
    return @value2
  v3: () ->
    return @value3
  v4: () ->
    return @value4
  v5: (x) ->
    if x?
      @value5 = x
      return
    else 
      return @value5
  

  one: () ->
    @two()

  two: () ->
    isMyFn(@two.caller)
    console.log(@value)
Object.seal(Foo::)
 
class Bar extends Foo
  constructor: () -> super()

  three: () -> @one()

  four: () -> @two()

  five: () -> super()

class Baz extends Foo
  constructor: () -> super()

  two: () -> super()
  towo: () -> super.two(1, 2, 3)
  bedo: () -> super

exports.Foo = Foo
exports.Bar = Bar
exports.Baz = Baz

  one: () ->
    @two()

  two: () ->
    unless isMyFn(@two.caller)
      throw new Error("Illegal call")
    console.log(@value)
 
class Bar extends Foo
  constructor: () -> super()

  three: () -> @one()

  four: () -> @two()

  five: () -> super()

class Baz extends Foo
  constructor: () -> super()

  two: () -> super()
  towo: () -> super.two(1, 2, 3)
  bedo: () -> super

exports.Foo = Foo
exports.Bar = Bar
exports.Baz = Baz

