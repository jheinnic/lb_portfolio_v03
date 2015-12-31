(function() {
  'use strict';

  module.exports = CoreModelPackage;
  CoreModelPackage.$inject = [];

  function CoreModelPackage() {
    this.Enum = require('jchptf.modeling.core/Enum.class');
    this.Module = require('jchptf.modeling.core/Module.class');
    this.ModelObject = require('./ModelObject.class');
  }
}).call(window);
