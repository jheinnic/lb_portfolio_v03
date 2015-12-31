'use strict'

module.exports = class NavBarBuilder
  NavBarModel = require('./NavBarModel.class')
  TabModel = require('./TabModel.class')
  _ = require('lodash')

  constructor: (model) ->
    changed = false
    aBrandName = if model? then model.brandName else ''
    tabModels = if model? then _.clone(model.tabModels) else []

    Object.defineProperties(this, {
      brandName:
        enumerable: true
        setter: (nextName) ->
          if (aBrandName isnt nextName)
            changed = true
            aBrandName = nextName
      _changed:
        getter: -> changed
        setter: (nextValue) -> changed = nextValue
      _brandName:
        getter: -> brandName
      _tabCount:
        getter: -> tabModels.length
      _tabModels:
        getter: -> tabModels
    })

  removeTabByName: (displayName) ->
    matchDisplayName = _.matchesProperty('displayName', displayName)
    removed = _.remove(this._tabModels, matchDisplayName)

    if (removed.length > 0)
      this._changed = true
    else
      throw new Error("No such tab named #{displayName}")

    return this

  addTab: (index, displayName, clickRoute, matchRoute) ->
    if index > this._tabCount
      throw new Error("Index (#{index}) is beyond the end of the current tab list.  Max index = #{this._tabCount}")
    else if index < 0
      throw new Error("Index (#{index}) must be non-negative.")

    # TODO: Make sure displayName is unique
    _addTab(this, index, displayName, clickRoute, matchRoute)

    this

  appendTab: (displayName, clickRoute, matchRoute) ->
    # TODO: Make sure displayName is unique
    _addTab(@, sthis._tabCount, displayName, clickRoute, matchRoute)

    this

  prependTab: (displayName, clickRoute, matchRoute) ->
    # TODO: Make sure displayName is unique
    _addTab(this, 0, displayName, clickRoute, matchRoute)

    this

  hasChanges: -> this._changed

  build: (refreshPromise) -> new NavBarModel(this._brandName, this._tabModels, refreshPromise)

  _addTab = (self, index, displayName, clickRoute, matchRoute) ->
    newTab = new TabModel(displayName, clickRoute, matchRoute)
    self._tabModels.push(newTab)

    if index < self._tabCount
      self._tabModels.copyWithin(index, index-1)
      self._tabModels[index] = newTab

    self._changed = true


