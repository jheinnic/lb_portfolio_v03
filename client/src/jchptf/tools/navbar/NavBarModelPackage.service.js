(function() {
  'use strict';

  module.exports = NavBarModelPackage;
  NavBarModelPackage.$inject = [];

  function NavBarModelPackage() {
    return Object.freeze({
      NavBarModel: require('./NavBarModel.class'),
      TabModel: require('./TabModel.class.coffee'),
      NavBarBuilder: require('./NavBarBuilder.class')
    });
  }
}).call(this);
