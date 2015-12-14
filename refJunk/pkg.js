'use strict';

// A cache buster for Grunt's options.pkgFunction
//
// NOTE: Might a less aggressive and more controlled way of triggering when to read package.json be needed
//       for grunt's watch plugin to remain sane after responding to Gruntfile.js modifications?
module.exports = function pkg(grunt, options) {
  var _ = require('lodash');

  // Package function that re-reads package.json on each call.
  function pkgFunction(){
    return grunt.file.readJSON('package.json');
  }

  // THIS extension forces the banner or whatever uses pkgFunction to always get the latest version
  // where as pkg is only done once at grunt init.
  _.extend(options, { pkgFunction: pkgFunction });
};
