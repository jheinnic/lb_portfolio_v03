_ = require 'underscore'

class Enum
  # @_size: 0
  # @_VALUES: {}

  Object.defineProperty(
      @,
      'testing'
      {
          "configurable": false,
          "editable": false,
          "writable": false,
          "value": { }
      }
  );

  _name: undefined
  _ordinal: undefined

  constructor: (name) ->
    'use strict'

    # Only permit uniquely named enum keys to be created.
    Class = @getClass()
    if Class == Enum
      throw new Error "Enum may only be instantiated through a subclass.  Direct instantiation is not supported."
    else if Class[name]
      throw new Error "#{name} already defined!"
    else
      Class[name] = @

    Class._VALUES ?= {}
    Class._size ?= 0

    Superclass = @getSuperclass()
    if Class._VALUES == Superclass._VALUES
      Class._VALUES = {}
      Class._size = 0

    @_name = name
    @_ordinal = Class._size

    Class._VALUES[name] = @
    Class._size += 1

    return Object.freeze(@)

  @keys: () -> @_VALUES ?= {}; return Object.keys(@_VALUES)
  @values: () -> @_VALUES ?= {}; return _.values(@_VALUES)
  @valueOf: (name) -> @_VALUES ?= {}; return if name then @_VALUES[name] else @

  # Call freeze after instantiating the last enum value to prevent
  # further modification of the enumerated value set.
  @freeze: () ->
    @_VALUES ?= {}
    @_VALUES = Object.freeze(@_VALUES)
    return Object.freeze(@)

  name: () -> return @_name
  ordinal: () -> return @_ordinal
  compareTo: (other) -> return @_ordinal - other._ordinal
  equals: (other) -> return this == other
  toString: () -> return @_name + "(" + @_ordinal + ")"
  getClass: () -> return this.constructor
  getSuperclass: () -> return @getClass().__super__.constructor

module.exports = Enum
