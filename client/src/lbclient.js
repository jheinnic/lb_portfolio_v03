(function(){
  'use strict';

  var loopback = require('loopback');
  var boot = require('loopback-boot');

  module.exports = loopback();
  boot(module.exports, {}, console.log);
}).call(this || window);

