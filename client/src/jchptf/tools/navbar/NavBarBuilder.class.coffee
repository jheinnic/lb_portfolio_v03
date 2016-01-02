'use strict'

module.exports = class NavBarBuilder
  NavBarModel = require('./NavBarModel.class')
  TabModel = require('./TabModel.class')
  _ = require('lodash')

  constructor: (model) ->
    changed = false
    brandName = if model? then model._brandName else ''
    tabModels = if model? then _.clone(model._tabModels) else []

    Object.defineProperties(
      this, {
        _changed:
          get: -> changed
          set: (nextValue) -> changed = nextValue
        _brandName:
          get: -> brandName
          set: (nextName) ->
            if (brandName isnt nextName)
              changed = true
              brandName = nextName
        _tabCount:
          get: -> tabModels.length
        _tabModels:
          get: -> tabModels }
    )

  brandName: (nextName) ->
    console.log(this)
    return if (nextName isnt undefined)
      @_brandName = nextName
      this
    else


  removeTabByName: (displayLabel) ->
    matchDisplayName = _.matchesProperty('displayLabel', displayLabel)
    removed = _.remove(@_tabModels, matchDisplayName)

    if (removed.length > 0)
      @_changed = true
    else
      throw new Error("No such tab named #{displayLabel}")

    return this

  addTab: (index, displayLabel, clickRoute, matchRoute) ->
    if (index > @_tabCount)
      throw new Error("Index (#{index}) is beyond the end of the current tab list.  Max index = #{@_tabCount}")
    else if (index < 0)
      throw new Error("Index (#{index}) must be non-negative.")

    # TODO: Make sure displayLabel is unique
    _addTab(this, index, displayLabel, clickRoute, matchRoute)

    return this

  appendTab: (displayLabel, clickRoute, matchRoute) ->
    # TODO: Make sure displayLabel is unique
    _addTab(this, @_tabCount, displayLabel, clickRoute, matchRoute)

    return this

  prependTab: (displayLabel, clickRoute, matchRoute) ->
    # TODO: Make sure displayLabel is unique
    _addTab(this, 0, displayLabel, clickRoute, matchRoute)

    return this

  _addTab = (self, index, displayLabel, clickRoute, matchRoute) ->
    newTab = new TabModel(
      displayLabel: displayLabel
      clickRoute: clickRoute
      matchRoute: matchRoute
    )
    self._tabModels.push(newTab)

    if (index < self._tabCount)
      self._tabModels.copyWithin(index + 1, index)
      self._tabModels[index] = newTab

    self._changed = true

  hasChanges: -> @_changed

  build: (refreshPromise) -> new NavBarModel(
    brandName: @_brandName
    tabModels: @_tabModels
    refreshPromise: refreshPromise
  )

