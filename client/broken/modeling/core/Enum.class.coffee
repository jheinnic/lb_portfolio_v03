'use strict'

ModelObject = require('./ModelObject.class')
_ = require('lodash')

class Enum extends ModelObject
  @$inject = []

  _name: undefined
  _ordinal: undefined

  constructor: (name) ->
    throw new Error('name must be defined') unless name?
    throw new Error('name may not be blank') if _.isEmpty(name)
    name = name.toUpperCase()
    throw new Error('name may only contain alphanumerics and underscore (\'_\')') unless /^[A-Z0-9_]+$/.test(name)

    # Only permit uniquely named enum keys to be created.
    Class = @getType()
    throw new Error('Enum may only be instantiated through an immediate subclass, not directly.') if Class is Enum
    throw new Error('Abstract enumerations may not instantiate values') if Class._ABSTRACT
    throw new Error("#{Class.name} already has an enumerated value named #{name}!") if Class[name]?

    SuperType = @getSuperType()
    while Enum.isSuperTypeOf(SuperType)
      unless SuperType._ABSTRACT
        throw new Error(
          "Enum allows only one concrete subtype in any inheritance chain, but #{SuperType.name} is already concrete"
        )
      SuperType = SuperType.finalize().getSuperType()

    Class._VALUES ?= {}
    Class._SIZE ?= 0
    Object.defineProperty(
      Class,
      name,
      { __proto__: null,
        enumerable: true,
        value: @ }
    )
    Object.defineProperties(
      @,
      { name: { __proto__: null,
        enumerable: true,
        value: name },
        ordinal: { __proto__: null,
          enumerable: true,
          value: Class._SIZE } }
    )

    Class._VALUES[name] = @
    Class._SIZE += 1

    return Object.freeze(@)

  @keys: ->
    # throw new Error('Abstract enumerations have no enum values to return the names from') if @_ABSTRACT?
    return [] if @_ABSTRACT

    @_VALUES ?= {}
    return Object.keys(@_VALUES)

  @values: ->
    # throw new Error('Abstract enumerations have no enum values to return') if @_ABSTRACT?
    return [] if @_ABSTRACT

    @_VALUES ?= {}
    return _.values(@_VALUES)

  @valueOf: (name) ->
    switch
      when name? && @_ABSTRACT?
        console.log('abstract')
        return undefined
      when name? && (!@_ABSTRACT?)
        console.log('concrete')
        @_VALUES ?= {}
        return @_VALUES[name]
      else
        console.log('no name')
        return @


  # Call freeze after instantiating the last enum value to prevent further
  # modification of the enumerated value set (by sealing the class and its _VALUES).
  @finalize: () ->
    return @ if Object.isSealed @

    if !@_ABSTRACT?
      @_VALUES ?= {}
      @_VALUES = Object.seal @_VALUES

      # Verify that the Type\'s named properties match the content of its keys() return value exactly.
      valueKeys = @keys
      clazz = @getType()
      propKeys = (name for name of clazz when clazz[name] instanceof clazz)
      unless valueKeys.length is propKeys.length is _.intersection(propKeys, valueKeys).length
        throw new Error("""Expected property names to store the same #{valueKeys} values using #{propKeys} matched properties, but those sets are disjointed.""")

    return Object.seal @

  name: () -> return @name
  ordinal: () -> return @ordinal
  compareTo: (other) -> return if @getType() is other.getType() then @ordinal - other.ordinal
  toString: () -> return "#{@getType().name}::#{@name} (#{@ordinal})"

module.exports = Enum
