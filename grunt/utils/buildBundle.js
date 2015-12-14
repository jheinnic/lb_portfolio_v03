(function() {
  'use strict';

  module.exports = buildNgAppBundle;

  var _          = require('lodash');
  var fs         = require('fs');
  var path       = require('path');
  var remapify   = require('remapify');
  var browserify = require('browserify');
  var boot       = require('loopback-boot');

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
    // Figure out which separator character to use by figuring out which modifies the content of alias.
    var sepToken = _.find(
      [
        {
          regexp: /\//g,
          join: '\/'
        }, {
        regexp: /\\/g,
        join: '\\'
      }
      ], function checkForMatch(candidate) {
        return candidate.regexp.test(alias);
      }
    );

    // Infer the module's fully qualified name by replacing all separators from dirname with '.'
    // Remove the file extension from basename
    // Concatenate moduleFQN to the extension-free base filename, joined by a '/' character if the file
    //   is not a module descriptor, otherwise just return the moduleFQN.
    var retVal = alias;
    if (_.isObject(sepToken)) {
      var moduleFQN = dirname.replace(sepToken.regexp, '.');
      var fileToken = basename.replace(/\.(js|coffee)$/, '');

      // Capitalize on the module definition file naming convention to infer which transformation to apply.
      if (fileToken === 'module') {
        // This entry is a module definition script.  It will be named for require using only its fully qualified
        // module name (e.g. require('jchptf.site.navigation')).
        retVal = moduleFQN;
      }
      else {
        // This entry is part of a module's contents.  It will be named for require using a combination of its fully
        // qualified name and its file token, with a separator character sequence separating the two.  For example,
        // 'HomeController.controller.js' under Unix directory path jchptf/site/navigator would be named for require
        // as 'jchptf.site.navigation/HomeController.controller'.
        //
        // On a Windows system, although we needed '\\' to split the path, we will still always join with '/' so the
        // correct syntax to require is not OS-dependent!
        // retVal = [moduleFQN, fileToken].join(sepToken.join);
        retVal = moduleFQN + '/' + fileToken;
      }

      console.log('Transformed ' + alias + ' for ' + dirname + sepToken.join + basename + ' into ' + retVal);
    }
    else {
      // If input alias failed to apply to either separator's re-write rule, leave retVal equal to alias.
      console.log('Retaining ' + alias + ' for dirname=' + dirname + ' and basename=' + basename);
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
   * TODO: This is not currently even in use--there are no client dependencies from common other than loopback models and
   *       model semantics, both of which Loopback already handles for us.
   *
   * @param {string} componentName Name of a common component's subdirectory.  Bind componentName through _.partial() before
   *                               passing this filter to a remapify pattern!!
   * @param {string} alias Remapify-proposed default alias name based on remapify generator rules.
   * @param {string} dirname
   * @param {string} basename
   * @returns {string} An alias name transformed by common component style guide policies or the original alias name if no
   *                   transformation was possible.
   */
  //function filterCommonAlias(componentName, alias, dirname, basename) {
  //  // Tolerate Windows and Unix path separators, just like Browserify and Remapify do.
  //  // Infer the module's fully qualified name and identify its separator character by figuring out
  //  // which separator changes dirname when replaced by '.'
  //  var retVal   = alias,
  //      sepToken = _.find(
  //        [ { regexp: /\//g, join: '\/' }, { regexp: /\\/g, join: '\\' } ],
  //        function testSeparatorDescriptor(sep) {
  //          return (
  //            sep.regexp.test(alias)
  //          );
  //        }
  //      ),
  //      moduleFQN = (sepToken !== undefined) ? dirname.replace(sep.regexp, '.') : dirname,
  //      fileToken = basename.replace(/\.js$|\.coffee$/, '');
  //
  //  if (moduleFQN) {
  //    if (moduleFQN === '.') {
  //      retVal = [componentName, fileToken].join(':');
  //
  //      // console.log('Transformed ' + alias + ' for ' + dirname + '->' + basename + ' of ' + componentName + ' to ' + retVal);
  //    } else {
  //      // retVal = [componentName, [moduleFQN, fileToken].join(sepToken.join)].join(':');
  //      retVal = [componentName, [moduleFQN, fileToken].join('\/')].join(':');
  //
  //      // console.log(
  //      //   'Transformed ' + alias + ' for ' + dirname + sepToken.join + basename + ' of ' + componentName + ' to ' + retVal);
  //    }
  //  } else {
  //    // If basedir defied re-write to a module FQN, but because it was equal to '.', leave the original alias unmodified.
  //    // console.log('Retaining ' + retVal + ' for ' + dirname + '->' + basename + ' of ' + componentName + ' unmodified.');
  //  }
  //
  //  return retVal;
  //}

  function buildNgAppBundle(grunt, appConfig, callback) {
    console.log('Starting buildBundle()', new Date());

    // Acquire appConfig by requiring it if the argument passed is not a non-null object.
    if ((appConfig === null) || (typeof appConfig !== 'object')) {
      appConfig = require('./appConfig');
    }

    var isDevEnv      = ['debug', 'development', 'test'].indexOf(appConfig.nodeEnv) >= 0,
        cwd           = appConfig.cwd,
        sourceGlob    = appConfig.app + '/**/*.{js,coffee}',
        clientSrcDir  = path.normalize(
          path.join(cwd, appConfig.source.client)
        ),
        bundlePath    = path.normalize(
          path.join(cwd, appConfig.dev.client, 'app.js')
        ),
        bundleMapPath = bundlePath + '.map';

    // NOTE(bajtos) debug should be always true, the source maps should be saved to a standalone file when !isDev(env)
    var b = browserify( { basedir: clientSrcDir }, { debug: true } );

    // Handle .coffee files
    b.transform('coffeeify');
    b._extensions.push('.coffee');

    // Configure browserify to load Loopback and Main Angular Application Module to bootstrap bundle activation on load.
    b.require( path.join(clientSrcDir, 'lbclient'), { expose: 'lbclient' } );
    b.require( path.join(clientSrcDir, appConfig.app, 'module'), { expose: appConfig.app } );

    // Debug verbosity--see what the bundle's remapify filters did and what files satisfied either input pattern.
    // b.on(
    //   'remapify:files', function (files, expandedAliases, pattern) {
    //     console.log('Files:', files);
    //     console.log('Expanded Aliases:', expandedAliases);
    //     console.log('Pattern:', pattern);
    //   }
    // );

    try {
      boot.compileToBrowserify(clientSrcDir, b);
    } catch (err) {
      // TODO: Is catching the Error in order to pass it to callback better or worse than letting it bubble up by
      //       having omitted the try/catch construct altogether?
      console.error(err);
      return callback(err);
    }

    // Configure remapify to select common and ngapp client files and apply project policy for naming commonjs
    // packages that bundle both angular client code and common components.
    // TODO: Rather than using remapify at all, loop over the results of a glob and wire a series of direct
    //       calls to one of Wiredep's static registration methods: alias or one whose name I can't recall.
    b.plugin(
      remapify, [
        { src: sourceGlob, cwd: clientSrcDir, filter: filterAngularAlias }
      ]
    );

    // Prepare and execute output pipeline from Browserify runtime to local filesystem.
    var out = fs.createWriteStream(bundlePath);

    // All streams in the pipeline should invoke callback as their onError event handler.
    out.on('error', callback);

    // Only the last stream in the pipeline should invoke callback as its onClose event handler.
    out.on('close', callback);

    // Substitute an instance of exorcist between browserify and file writer if this is not a
    // development artifact under construction.
    if (! isDevEnv) {
      // TODO: First file arguments is required.  Next three are optional and for locating source maps:
      //       "url": ???
      //       "root": ???
      //       "base": ???
      //
      //       More clarify on their precise meaning is needed to help complete this documentation
      var bundleMapOut =
        require('exorcist')(bundleMapPath, 'url', 'root', 'base');
      bundleMapOut.pipe(out);
      bundleMapOut.on('error', callback);

      out = bundleMapOut;
    }

    // Connect the first stream of the output pipeline to browserify's bundle builder and let it run!
    console.log('Activating the bundle pipeline', new Date());
    try {
      b.bundle().on('error', callback).pipe(out);
      console.log('Finished buildBundle()', new Date());
    } catch(err) {
      console.log('Failed buildBundle()', new Date());

      // NOTE: Here it seems important to catch the Exception since it appears to be thrown from some
      //       code downstream of on() that is not callback aware and so has not yet passed its error
      //       to callback before it hurls an Error back towards its caller.
      console.error(err);
      callback(err);
    }
  }
}());
