'use strict'

module.exports = class NavBarModelPackage
  @$inject = []

  _ = require('lodash')
  url = require('url')

  constructor: ->
    Object.freeze
      NavBarModel: require('./NavBarModel.class')
      TabModel: require('./TabModel.class')
      NavBarBuilder: require('./NavBarBuilder.class')
