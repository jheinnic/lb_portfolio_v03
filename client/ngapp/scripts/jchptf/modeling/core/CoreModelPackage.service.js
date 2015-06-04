(function() {
  'use strict'

  module.exports = CoreModelPackage

  CoreModelPackage.$inject = [];

  function CoreModelPackage() {
    this.Enum = require('./Enum.class.coffee')
    this.ModelObject = require('./ModelObject.class.coffee')
  }
})(window);
