(function() {
  module.exports = filterCommonAlias;

  var fs         = require('fs'),
      path       = require('path'),
      browserify = require('browserify'),
      remapify   = require('remapify'),
      _          = require('lodash'),
      pkg        = require('./package.json');

  /**
   * Blah
   *
   * This filter must be used as a partial function by calling _.partial(filterCommonAlias, <commonComponentName>) where
   * commonComponentName names the subdirectory of common components being mapped before passing it to remapify.
   *
   * If the directory structure under the commponComponentName subdirectory for common components has any degree of nesting,
   * any path through those inner directories is considered to represent a module name.  Files at the root of a named common
   * component type are treated as having no module.  Module names are derived by replacing filesystem separators with '.'.
   *
   * The names this filter produces have two elements separated by a filesystem separator character for files without a
   * module qualifier, and three element for those which have a module qualifier.
   *
   * For example:
   * <componentDir>/repository/RepositoryService.js -> require('repository:RepositoryService')    ** unqualified form **
   * <componentDir>/models/jchptf/core/Enum.coffee -> require('models:jchptf.core/Enum')        ** fully qualified form **
   *
   * NOTE: To avoid collisions with the angular application, do not use same name for both the angular application root module
   * and a common component, and do not use '.' in the name of any common component's root subdirectory.
   * TODO: Look for painless ways to avoid potential collisions between the client and common namespaces for require().
   *
   * @param {string} componentName Name of a common component's subdirectory.  Bind componentName through _.partial() before
   *                               passing this filter to a remapify pattern!!
   * @param {string} alias Remapify-proposed default alias name based on remapify generator rules.
   * @param {string} dirname
   * @param {string} basename
   * @returns {string} An alias name transformed by common component style guide policies or the original alias name if no
   *                   transformation was possible.
   */
  function filterCommonAlias(componentName, alias, dirname, basename) {
    var retVal    = alias;

    var fileToken = undefined;
    _([
      /\.js$/,
      /\.coffee$/
    ]).find(function(suffix) {
      fileToken = basename.replace(suffix, '');
      return fileToken != basename;
    });

    if (dirname == '.') {
      retVal = [componentName, fileToken].join(':');

      // console.log('Transformed ' + alias + ' for ' + dirname + '->' + basename + ' of ' + componentName + ' to ' + retVal);
    } else {
      // Infer the module's fully qualified name and identify its separator character by figuring out
      // which separator changes dirname when replaced by '.'.  Tolerate Windows and Unix path separators,
      // just like Browserify and Remapify do.
      var moduleFQN = undefined;
      _([
        { regexp: /\//g, join: '\/' },
        { regexp: /\\/g, join: '\\' }
      ]).find(
        function(sep) {
          if (sep.regexp.test(alias)) {
            moduleFQN = dirname.replace(sep.regexp, '.');
            retVal =
              [ componentName,
                [moduleFQN, fileToken].join(sep.join)
              ].join(':');
          }

          return moduleFQN;
        }
      );

      //if (moduleFQN) {
      //  console.log('Transformed ' + alias + ' for ' + dirname + sepToken.join + basename + ' of ' + componentName + ' to ' + retVal);
      //}
      //else {
      //  // If basedir defied re-write to a module FQN, but also did not equal '.', leave the original alias unmodified.
      //  console.log('Retaining ' + retVal + ' for ' + dirname + '->' + basename + ' of ' + componentName + ' unmodified.');
      //}
    }

    return retVal;
  }
}).call(this);
