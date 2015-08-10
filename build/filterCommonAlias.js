(function() {
  module.exports = filterCommonAlias;

  var path = require('path');
  var fs = require('fs');
  var _ = require('lodash');
  var browserify = require('browserify');
  var remapify = require('remapify')

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
   * <componentDir>/repository/RepositoryService.js -> require('repository/RepositoryService')    ** unqualified form **
   * <componentDir>/models/jchptf/core/Enum.coffee -> require('models/jchptf.core/Enum')        ** fully qualified form **
   *
   * NOTE: To avoid collisions with the angular application, do not use same name for both the angular application root module
   * and a common component, and do not use '.' in the name of any common component's root subdirectory.
   * TODO: Look for painless ways to avoid potential collisions between the client and common namespaces for require().
   *
   * @param {string} alias Remapify-proposed default alias name based on remapify generator rules.
   * @param {string} dirname
   * @param {string} basename
   * @returns {string} An alias name transformed by common component style guide policies or the original alias name if no
   *                   transformation was possible.
   */
  function filterCommonAlias(alias, dirname, basename) {
    var fileToken = basename.replace(/\.js$/, '');
    if( (fileToken == basename) && (! /\.coffee$/.test(basename)) ) {
      console.warn("Could not recognize a known file suffix for " + basename + ".  Using input alias of " + alias);
      return alias;
    }

    // Infer the module's fully qualified name and identify its separator character by figuring out
    // which separator changes dirname when replaced by '.'.  Tolerate Windows and Unix path separators,
    // just like Browserify and Remapify do.
    var retVal = undefined;
    var sepToken = undefined;
    var componentName = undefined;
    _([
      { regexp: /\//g, join: '\/' },
      { regexp: /\\/g, join: '\\' }
    ]).find(
      function(sep) {
        if (sep.regexp.test(alias)) {
          var pathTokens = dirname.split(sep.regexp);
          componentName = pathTokens.shift();
          sepToken = sep;

          if (pathTokens.length == 0) {
            retVal = [componentName, fileToken].join(sep.join);
          } else {
            retVal = [
              componentName,
              pathTokens.join('.'),     // moduleFQN
              fileToken
            ].join(sep.join);
          }
        }

        return retVal;
      }
    );

    if (sepToken) {
      console.log('Transformed ' + alias + ' for ' + dirname + sepToken.join + basename + ' of ' + componentName + ' to ' + retVal);
    } else {
      // If basedir defied re-write to a module FQN, but also did not equal '.', leave the original alias unmodified.
      console.log('Retaining ' + retVal + ' for ' + dirname + '->' + basename + ' of ' + componentName + ' unmodified.');
    }

    return retVal || alias;
  }
}).call(this);
