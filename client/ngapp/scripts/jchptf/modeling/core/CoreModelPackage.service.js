(function() {
  'use strict';

  module.exports = CoreModelPackage;
  CoreModelPackage.$inject = [];

  function CoreModelPackage() {
    this.Enum = require('modeling/core/Enum.class.coffee');
    this.Module = require('modeling/core/Module.class.coffee');
    this.ModelObject = require('modeling/core/ModelObject.class.coffee');
  }
}).call(window);
