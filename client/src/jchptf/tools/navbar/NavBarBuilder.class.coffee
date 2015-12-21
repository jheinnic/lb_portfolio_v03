'use strict'

module.exports = class NavBarBuilder
  NavBarModel = require('./NavBarModel.class')
  TabModel = require('./TabModel.class')
  _ = require('lodash')

  constructor: (model) ->
    changed = false
    brandName = if model? then model.brandName else ''
    tabModels = if model? then _.clone(model.tabModels) else []

    Object.defineProperties @, {
      brandName:
        enumerable: true
        setter: (nextName) ->
          if brandName != nextName
            brandName = nextName
            changed = true
      _changed:
        getter: () -> changed
        setter: (nextValue) -> changed = nextValue
      _brandName:
        getter: () -> brandName
      _tabCount:
        getter: () -> tabModels.length
      _tabModels:
        getter: () -> tabModels
    }

  removeTabByName: (displayName) ->
    matchDisplayName = _.matchesProperty('displayName', displayName)
    removed = _.remove @_tabModels, matchDisplayName

    if removed.length > 0
      @_changed = true
    else
      throw new Error "No such tab named #{displayName}"
    @

  addTab: (index, displayName, clickRoute, matchRoute) ->
    if index > @_tabCount
      throw new Error "Index (#{index}) is beyond the end of the current tab list.  Max index = #{@_tabCount}"
    else if index < 0
      throw new Error "Index (#{index}) must be non-negative."

    # TODO: Make sure displayName is unique
    _addTab @, index, displayName, clickRoute, matchRoute
    @

  appendTab: (displayName, clickRoute, matchRoute) ->
    # TODO: Make sure displayName is unique
    _addTab @, @_tabCount, displayName, clickRoute, matchRoute
    @

  prependTab: (displayName, clickRoute, matchRoute) ->
    # TODO: Make sure displayName is unique
    _addTab @, 0, displayName, clickRoute, matchRoute
    @

  hasChanges: () -> @_changed

  build: (refreshPromise) -> new NavBarModel(@_brandName, @_tabModels, refreshPromise)

  _addTab = (self, index, displayName, clickRoute, matchRoute) ->
    newTab = new TabModel(displayName, clickRoute, matchRoute)
    self._tabModels.push(newTab)

    if index < self._tabCount
      self._tabModels.copyWithin(index, index-1)
      self._tabModels[index] = newTab

    self._changed = true


