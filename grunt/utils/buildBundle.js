(function() {
  'use strict';

  module.exports = buildNgAppBundle;

  var _ = require('lodash');
  var fs = require('fs');
  var path = require('path');
  var sprintf = require('sprintf-js').sprintf;
  var browserify = require('browserify');
  var boot = require('loopback-boot');

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
   * @param {Browserify} Browserify runtime object
   * @param {string} A glob-returned file path to add a browserify registration for.
   */
  function registerFileByModuleAlias(b, appConfig, fileGlobPath) {
    var registeredFilePath = path.relative(appConfig.source.client, fileGlobPath).replace(/\\/g, '/');
    console.log(fileGlobPath);
    console.log(registeredFilePath);
    if (fileGlobPath !== appConfig.app + '/module.js' || fileGlobPath !== appConfig.app + '/module.coffee') {
      var moduleFQN = path.dirname(registeredFilePath).replace(/\//g, '.');
      var baseName = path.basename(registeredFilePath).replace(/\.coffee$|\.js$/, '');
      var exposedName;

      // For module.{js|coffee} (module descriptor file), omit '/module' and use FQN alone for file name.
      if (baseName === 'module') {
        exposedName = moduleFQN;
      } else {
        exposedName = moduleFQN + '/' + baseName;
      }

      registeredFilePath = './' + registeredFilePath;
      // console.log(fileGlobPath, registeredFilePath, moduleFQN, exposedName);

      b.add(registeredFilePath, {expose: exposedName});
    }
  }


  /**
   *
   * @param {Grunt} grunt Grunt runtime object, used to accessing its file glob resolver.
   * @param {string} appConfig appConfig object, used to locate application build paths by type.
   * @param {string} callback An async callback handler to call on completion (Node (err, data) style arguments)
   * @returns {string} An angularized alias name if the transformation was applicable, otherwise the original alias.
   */
  function buildNgAppBundle(grunt, appConfig, callback) {
    console.log('Config client-bundle', new Date());
    // Acquire appConfig by requiring it if the argument passed is not a non-null object.
    if ((appConfig === null) || (typeof appConfig !== 'object')) {
      appConfig = require('./appConfig');
    }

    // NOTE(bajtos) debug should be always true, the source maps should be saved to a standalone file when !isDev(env)
    var isDevEnv      = ['debug', 'development', 'test'].indexOf(appConfig.nodeEnv) >= 0;
    var b = browserify( { basedir: appConfig.source.client }, { debug: true } );

    // Handle .coffee files
    b.transform(require('coffeeify'));
    b._extensions.push('.coffee');

    // Construct file glob and normalized absolute path for use with grunt and browserify here.
    // Register source files using the moduleFQN/fileName convention described in function comment block.
    var sourceGlob    = appConfig.source.client + '/' + appConfig.app + '/**/*.{js,coffee,json}';
    _.forEach(
      grunt.file.glob(
        sourceGlob,
        { sync: true, strict: true, stat: false, dot: true,
          nonull: false, nosort: true, nounique: true, nodir: true }
      ),
      _.partial(registerFileByModuleAlias, b, appConfig)
    );

    // Configure browserify to load Loopback and Main Angular Application Module to bootstrap bundle activation on load.
    b.add( './lbclient.js', { expose: 'lbclient' } );
    b.require(sprintf('./%s/module.js', appConfig.app), { expose: appConfig.app });

    var clientSrcDir  = path.normalize(
      path.join(appConfig.cwd, appConfig.source.client));
    try {
      boot.compileToBrowserify(clientSrcDir, b);
    } catch (err) {
      // TODO: Is catching the Error in order to pass it to callback better or worse than letting it bubble up by
      //       having omitted the try/catch construct altogether?
      console.error(err);
      return callback(err);
    }

    // Prepare and execute output pipeline from Browserify runtime to local filesystem.
    var bundlePath = path.normalize(
      path.join(appConfig.cwd, appConfig.dev.client, 'app.js'));
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
      var bundleMapPath = bundlePath + '.map';
      var bundleMapOut  =
        require('exorcist')(bundleMapPath, path.basename(bundleMapPath), 'root', 'base');

      bundleMapOut.pipe(out);
      bundleMapOut.on('error', callback);
      out = bundleMapOut;
    }

    // Connect the first stream of the output pipeline to browserify's bundle builder and let it run!
    console.log('Activating the bundle pipeline', new Date());
    try {
      b.bundle().on('error', callback).pipe(out);
    } catch(err) {
      console.error('Failed buildBundle()', new Date(), err);

      // NOTE: Here it seems important to catch the Exception since it appears to be thrown from some
      //       code downstream of on() that is not callback aware and so has not yet passed its error
      //       to callback before it hurls an Error back towards its caller.
      callback(err);
    }
  }
}).call();
