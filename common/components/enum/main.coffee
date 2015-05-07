##
## Enum and Module Base Classes
##

class Enum
  _ = require('underscore');

  _name: undefined
  _ordinal: undefined

  constructor: (name) ->
    'use strict'

    # Only permit uniquely named enum keys to be created.
    Class = @getClass()
    if Class[name]
      throw new Error("<%= name %> already defined!")
    else
      Class[name] = @

    Class._VALUES ?= {}
    Class._size ?= 0

    Superclass = @getSuperclass()
    if Class._VALUES == Superclass._VALUES
      Class._VALUES = _.clone(Superclass._VALUES)
      Class._size = Superclass._size
    }

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
  @finalize: () ->
    if @ == Enum then return @

    Object.defineProperty(
        @,
        '_VALUES'
        {
            "configurable": false,
            "enumerable": false,
            "editable": false,
            "writable": false,
            "value": Object.freeze(@_VALUES || {})
        }
    );
    Object.defineProperty(
        @,
        '_size'
        {
            "configurable": false,
            "enumerable": false,
            "editable": false,
            "writable": false,
            "value": @_size || 0
        }
    );
    return Object.freeze(@)

  name: () -> return @_name
  ordinal: () -> return @_ordinal
  compareTo: (other) -> return @_ordinal - other._ordinal
  equals: (other) -> return this == other
  toString: () -> return @_name + "(" + @_ordinal + ")"
  getClass: () -> return this.constructor
  getSuperclass: () -> return @getClass().__super__.constructor

# Just a usage example--do not export.
class FooEnum extends Enum
  new FooEnum('valueOne')
#  new FooEnum('valueTwo')
#  @freeze()

class BarEnum extends Enum
  new BarEnum('first')

# Modules are classes designed to be reused as run-time mixins rather
# compile-time subclasses.
#
# When a module extends a module, the extended module's properties are copied as
# properties of the exending module.  The extending class acquires new class
# properties from the extended module's class properties.

# When an module includes a module, the included module's properties are copied as
# properties of the extending class's prototype.  Instance of the including class
# acquire new properties from the included module's class properties.
#
# The extend operation is meant for objects, whereas include is meant
# to be used on type constructor functions.
#
# In either case, a module can declare a function to be called when it
# is extended or included.  The object properties named 'extended' and
# 'included' are reserved for this purpose and are never copied during
# either operation.
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
exports.FooEnum = FooEnum
exports.BarEnum = BarEnum

# return exports
