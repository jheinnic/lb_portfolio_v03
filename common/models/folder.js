(function () {
  'use strict';

  var uuid = require('uuid');
  var async = require('async');
  var loopback = require('loopback');

  module.exports = function (Folder) {
    Folder.definition.properties.uuid.default = uuid.v1;
  };
}).call(window);
