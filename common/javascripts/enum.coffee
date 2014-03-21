# TODO: Make this work!  Need to import 'amdefine'
# if (typeof define != 'function')
#   define = require('amdefine')(module);

define ['require', 'exports'], (require, exports) ->
  ##
  ## Enum and Module Base Classes
  ##
  class Enum
    @_size: 0
    @_VALUES: {}

    _name: undefined
    _ordinal: undefined

    constructor: () ->
      Class = @getSuperclass()
      @_name = Object.keys(Class._VALUES)[Class._size]
      @_ordinal = Class._size
      Class._size += 1

    @values: () -> return Object.keys(@_VALUES)
    @valueOf: (name) -> return @_VALUES[name]

    name: () -> return @_name
    ordinal: () -> return @_ordinal
    compareTo: (other) -> return @_ordinal - other._ordinal
    equals: (other) -> return this == other
    toString: () -> return @_name + "(" + @_ordinal + ")"
    getClass: () -> return this.constructor
    getSuperclass: () -> return @getClass().__super__.constructor


  class Module
    moduleKeywords = ['extended', 'included']

    @extend: (obj) ->
      for key, value of obj when key not in moduleKeywords
        @[key] = value

      obj.extended?.apply(@)
      return this

    @include: (obj) ->
      for key, value of obj when key not in moduleKeywords
        # Assign properties to the prototype
        @::[key] = value

      obj.included?.apply(@)
      return this

  exports.Enum = Enum
  exports.Module = Module

  return exports