(function() {
  // TODO: Take directory locations of angular code and common components as arguments!
  // TODO: Also take the names of the common component roots as an argument!
  module.exports = buildNgAppBundle;

  var fs         = require('fs'),
      path       = require('path'),
      browserify = require('browserify'),
      remapify   = require('remapify'),
      _          = require('lodash'),
      pkg        = require('./package.json');

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
    var retVal    = alias,
        moduleFQN = alias,
        sepToken  = { regexp: undefined, join: undefined };

    // Infer the module's fully qualified name and identify its separator character by figuring out which
    // separator changes dirname when replaced by '.'
    _([{ regexp: /\//g, join: '\/' }, { regexp: /\\/g, join: '\\' }]).find(
      function(sep) {
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
      var fileToken  = undefined;
      _([/\.js$/, /\.coffee$/]).find(function(suffix) {
        fileToken = basename.replace(suffix, '');
        return fileToken != basename;
      });

      // Capitalize on the module definition file naming convention to infer which transformation to apply.
      var moduleName = _.last(moduleFQN.split('.'));
      if (fileToken == moduleName) {
        // This entry is a module definition script.  It will be named for require using only its fully qualified
        // module name (e.g. require('jchptf.site.navigation')).
        retVal = moduleFQN;
      }
      else {
        if (fileToken == moduleName + '.config') {
          fileToken = 'config'
        } else if (fileToken == moduleName + '.run') {
          fileToken = 'run'
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
        retVal = [moduleFQN, fileToken].join(sepToken.join);
      }

      // console.log('Transformed ' + alias + ' for ' + dirname + sepToken.join + basename + ' to ' + retVal);
    } else {
      // If input alias failed to apply to either separator's re-write rule, leave retVal equal to alias.
      // console.log('Retaining ' + retVal + ' for ' + dirname + '->' + basename + ' unmodified.');
    }

    return retVal;
  }


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
    // Tolerate Windows and Unix path separators, just like Browserify and Remapify do.
    var retVal    = alias,
        moduleFQN = undefined,
        sepToken  = { regexp: undefined, join: undefined };

    // Infer the module's fully qualified name and identify its separator character by figuring out
    // which separator changes dirname when replaced by '.'
    _.find(
      [{ regexp: /\//g, join: '\/' }, { regexp: /\\/g, join: '\\' }],
      function(sep) {
        if (sep.regexp.test(alias)) {
          moduleFQN = dirname.replace(sep.regexp, '.');
          sepToken = sep;
          return true;
        }

        return false;
      }
    );

    var fileToken = undefined;
    _([/\.js$/, /\.coffee$/]).find(function(suffix) {
      fileToken = basename.replace(suffix, '');
      return fileToken != basename;
    });

    if (moduleFQN) {
      retVal = [
        componentName,
        [moduleFQN, fileToken].join(sepToken.join)
      ].join(':');

      // console.log('Transformed ' + alias + ' for ' + dirname + sepToken.join + basename + ' of ' + componentName + ' to ' + retVal);
    } else if (moduleFQN == '.') {
      retVal = [componentName, fileToken].join(':');

      // console.log('Transformed ' + alias + ' for ' + dirname + '->' + basename + ' of ' + componentName + ' to ' + retVal);
    } else {
      // If basedir defied re-write to a module FQN, but because it was equal to '.', leave the original alias unmodified.
      // console.log('Retaining ' + retVal + ' for ' + dirname + '->' + basename + ' of ' + componentName + ' unmodified.');
    }

    return retVal;
  }

  function buildNgAppBundle(env, angularScriptsDir, commonComponentsDir, commonComponentNames, buildOutputDir, callback) {
    var isDevEnv      = ['debug', 'development', 'test'].indexOf(env) >= 0,
        bundlePath    = path.join(buildOutputDir, 'app.bundle.js'),
        sourceMapPath = path.join(buildOutputDir, 'app.bundle.js.map');

    // TODO(bajtos) debug should be always true, the source maps should be
    // saved to a standalone file when !isDev(env)
    // TODO(jch) Assess https://github.com/thlorenz/exorcist#usage as a
    // non-dev build extra step for extracting source maps from the bundle
    // after generating it.  Browserify does not seem to have an option for
    // generating them outside the bundle, but this tool is capable of
    // extracting them after-the-fact.
    var sourceGlob     = path.join('**', '*.{js,coffee}'),
        b = browserify({basedir: __dirname}, {debug: true /*isDevEnv*/});

    // Filter out this script's own output.
    b.exclude(bundlePath).ignore(bundlePath);
    b.ignore(sourceMapPath).exclude(sourceMapPath);

    // Handle .coffee files
    b.transform('coffeeify');
    b._extensions.push('.coffee');

    // Configure browserify to load the angular application's main module at end of bundle definition script's execution.
    var mainModuleFile = path.join(angularScriptsDir, pkg.main);
    console.log(mainModuleFile);
    b.require(mainModuleFile /*, {expose: 'jchptf'}*/);

    /*
    // Debug verboicty--see what the bundle's remapify filters did and what files satisfied either input pattern.
    b.on('remapify:files', function(files, expandedAliases, pattern){
      console.log('Files:', files);
      console.log('Expanded Aliases:', expandedAliases);
      console.log('Pattern:', pattern);
    });
    */

    // Configure remapify to select common and ngapp client files and apply project policy for naming commonjs
    // packages that bundle both angular client code and common components.
    b.plugin(
      remapify,
      _.flatten([  // Otherwise the _.map() would result in passing a 2D matrix, not a 1D array
        {
          src: sourceGlob, cwd: angularScriptsDir, filter: filterAngularAlias
        },
        _.map(
          commonComponentNames,
          function getComponentPattern(componentName) {
            return {
              src: sourceGlob, cwd: path.join(commonComponentsDir, componentName), filter: _.partial(filterCommonAlias, componentName)
            }
          }
        )
      ])
    );

    /*try {
     boot.compileToBrowserify({
     angularScriptsDir: appRootDir,
     env: env
     }, b);
     } catch(err) {
     return callback(err);
     }*/

    // Prepare and execute the output pipeline from Browserify runtime to local filesystem.
    var out = fs.createWriteStream(bundlePath);

    // All streams in the pipeline should invoke callback as their onError event handler.
    out.on('error', callback);

    // Only the last stream in the pipeline should invoke callback as its onClose event handler.
    out.on('close', callback);

    if (isDevEnv) {
      var exorcist      = require('exorcist'),
          sourceMapOut  = exorcist(sourceMapPath, 'scripts/app.bundle.js.map', 'scripts');

      sourceMapOut.pipe(out);
      out = sourceMapOut;
      out.on('error', callback);
    }

    // Connect the first stream of the output pipeline to browserify's bundle builder and let it run!
    b.bundle().on('error', callback).pipe(out);
  }
}).call(this);
