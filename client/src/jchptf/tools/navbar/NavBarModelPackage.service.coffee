'use strict'

module.exports = class NavBarModelPackage
  @$inject = []

  _ = require('lodash')
  url = require('url')

  constructor: ->
    Object.freeze
      NavBarModel: require('./NavBarModel')
      TabModel: require('./TabModel')
      NavBarBuilder: require('./NavBarBuilder')
