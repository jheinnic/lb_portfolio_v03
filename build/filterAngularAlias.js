(function() {
  // TODO: Take directory locations of angular code and common components as arguments!
  // TODO: Also take the names of the common component roots as an argument!
  module.exports = filterAngularAlias;

  var path = require('path');
  var fs = require('fs');
  var _ = require('lodash');
  var browserify = require('browserify');
  var remapify = require('remapify');

  /**
   * Applies the alias name transformation rule for angular application scripts using this project's file layout
   * style guide.
   *
   * The resulting names follow one of four conventions, where:
   * -- / is a filesystem separator, '/' on Unix or '\\' on Windows.
   * -- <modulePath> is a filesystem path leading from the root angular scripts directory to file being aliased.
   * -- <moduleFQN> is a module's fully qualified name, as derived from <modulePath> by replacing <fsSep> with '.'.
   * -- <moduleName> is a module's unqualified local name, which is the last name from its <modulePath>.
   * -- <fileToken> is the name of the file being aliased, absent its '.js' or '.coffee' extension.
   * -- <fileExtension> is the extension of the file being aliases, either '.js', or '.coffee'.
   * -- <fileName> is the concatenation of <fileToken> and <fileExtension>.
   *
   * The four naming rules are as follows:
   * -- Module definition scripts: <modelFQN> -> <modulePath>/<moduleName><fileExtension>
   * -- Module config phase scripts: <modelFQN>/config -> <modulePath>/<moduleName>.config<fileExtension>
   * -- Module run phase scripts: <modelFQN>/run -> <modulePath>/<moduleName>.run<fileExtension>
   * -- Module registered subunit scripts: <modelFQN>/<fileToken> -> <modulePath>/<fileName>
   *
   * @param {string} alias
   * @param {string} dirname
   * @param {string} basename
   * @returns {string} An angularized alias name if the transformation was applicable, otherwise the original alias.
   */
  function filterAngularAlias(alias, dirname, basename) {
    // Tolerate Windows and Unix path separators, just like Browserify and Remapify do.
    var retVal = alias, moduleFQN = alias, sepToken = {regexp: undefined, join: undefined};

    // Infer the module's fully qualified name and identify its separator character by figuring out which
    // separator changes dirname when replaced by '.'
    _([
       {regexp: /\//g, join: '\/'},
       {regexp: /\\/g, join: '\\'}
    ]).find(
      function (sep) {
        if (sep.regexp.test(alias)) {
          moduleFQN = dirname.replace(sep.regexp, '.');
          sepToken = sep;
          return true;
        }

        return false;
      }
    );

    // If moduleFQN was set, then we found the right separator and may proceed.
    if (moduleFQN) {
      // Get the file name for comparing with the module name, but remember that we cannot lose .coffee suffixes the same way
      // that we can lose .js suffixes here!
      var fileNameToken = basename.replace(/\.js$/, '');
      var fileCompareToken = fileNameToken;
      if(fileNameToken != basename) {
      } else if (/\.coffee$/.test(basename)) {
        fileCompareToken = basename.replace(/\.coffee$/, '');
      } else {
        console.warn("Could not recognize a known file suffix for " + basename + ".  Using input alias of " + alias);
        return alias;
      }

      // Capitalize on the module definition file naming convention to infer which transformation to apply.
      var moduleName = _.last(moduleFQN.split('.'));
      if (fileCompareToken == moduleName) {
        // This entry is a module definition script.  It will be named for require using only its fully qualified
        // module name (e.g. require('jchptf.site.navigation')).
        retVal = moduleFQN;
      }
      else {
        if (fileCompareToken == moduleName + '.config') {
          if (fileNameToken == fileCompareToken) {
            fileNameToken = 'config';
          } else {
            fileNameToken = 'config.coffee';
          }
        }
        else
          if (fileCompareToken == moduleName + '.run') {
            if (fileNameToken == fileCompareToken) {
              fileNameToken = 'run';
            } else {
              fileNameToken = 'run.coffee';
            }
          }

        // This entry is part of a module's contents.  It will be named for require using a combination of its fully
        // qualified name and its file token, with a separator character sequence separating the two.  For example,
        // 'HomeController.controller.js' under Unix directory path jchptf/site/navigator would be named for require
        // as 'jchptf.site.navigation/HomeController.controller'.
        //
        // NOTE: There are special rules for the special "config" and "run" subsections of a module.  These are both
        // recognized by appending a designated suffix to the module's unqualified name, but they are named for require
        // by applying the above combination rule to just the designated suffix.  For example, 'navigation.config' under
        // unix folder path jchptf/site/navigation would be named for require as 'jchptf.site.navigation/config'.  On a
        // Windows system, the same import could also be named 'jchptf.site.navigation\\config'.
        retVal = [moduleFQN, fileNameToken].join(sepToken.join);
      }

      console.log('Transformed ' + alias + ' for ' + dirname + sepToken.join + basename + ' to ' + retVal);
    } else {
      // If input alias failed to apply to either separator's re-write rule, leave retVal equal to alias.
      console.log('Retaining ' + retVal + ' for ' + dirname + '->' + basename + ' unmodified.');
    }

    return retVal;
  }
}).call(this);
