'use strict'

_ = require 'lodash'

module.exports = coreModelPackage
coreModelPackage.$inject = []

coreModelPackage = ->
  class Enum
    _name: undefined
    _ordinal: undefined

    constructor: (name) ->
      # Only permit uniquely named enum keys to be created.
      Class = @getClass()
      throw new Error "Enum may only be instantiated through an immediate subclass, not directly." if Class == Enum
      throw new Error "Abstract enumerations may not instantiate values" if Class._ABSTRACT
      throw new Error "#{Class.name} already has an enumerated value named #{name}!" if Class[name]?

      #else if @getSuperclass != Enum
      Superclass = @getSuperclass()
      while Enum::.isPrototypeOf(Superclass::)
        unless Superclass._ABSTRACT == true
          throw new Error "Enum allows only concrete subtype in any inheritance chain, but #{Superclass.name} is already concrete"
        #Superclass = Object.freeze(Superclass).getSuperclass()
        Superclass = Superclass.finalize().getSuperclass()

      Object.defineProperty(
        Class,
        name,
          configurable: false,
          enumerable: true,
          editable: false,
          value: @
      )
      Class._VALUES ?= {}
      Class._SIZE ?= 0

      Object.defineProperty(
        @,
        'name',
          configurable: false,
          enumerable: true,
          editable: false,
          value: name
      )
      Object.defineProperty(
        @,
        'ordinal',
          configurable: false,
          enumerable: true,
          editable: false,
          value: Class._SIZE
      )

      Class._VALUES[name] = @
      Class._SIZE += 1

      return Object.seal(@)

    @keys: ->
      # throw new Error "Abstract enumerations have no enum values to return the names from" if @_ABSTRACT?
      return [] if @_ABSTRACT

      @_VALUES ?= {}
      return Object.keys(@_VALUES)

    @values: ->
      # throw new Error "Abstract enumerations have no enum values to return" if @_ABSTRACT?
      return [] if @_ABSTRACT

      @_VALUES ?= {}
      return _.values(@_VALUES)

    @valueOf: (name) ->
      switch
        when name? && @_ABSTRACT?
          console.log('abstract')
          return undefined
        when name? && (! @_ABSTRACT?)
          console.log('concrete')
          @_VALUES ?= {}
          return @_VALUES[name]
        else
          console.log('no name')
          return @


    # Call freeze after instantiating the last enum value to prevent further
    # modification of the enumerated value set (by sealing the class and its _VALUES).
    @finalize: () ->
      if Object.isSealed @
        return @
      else if ! @_ABSTRACT?
        @_VALUES ?= {}
        @_VALUES = Object.seal @_VALUES

      return Object.seal @

    name: () -> return @name
    ordinal: () -> return @ordinal
    compareTo: (other) -> return if @getClass() == other.getClass() then @ordinal - other.ordinal else undefined
    equals: (other) -> return this == other
    toString: () -> return "#{@getClass().name}::#{@name}(#{@ordinal})"

    getClass: () -> return @constructor
    getSuperclass: () -> return @getClass().getSuperclass()

    @getSuperclass: () -> return @__super__.constructor

  return Enum: Enum
