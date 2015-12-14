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

  var filterAngularAlias = require('./filterAngularAlias');

  function buildNgAppBundle(env, angularScriptsDir, commonComponentsDir, buildOutputDir, callback) {
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

    // Handle .coffee files
    b.transform('coffeeify');
    b._extensions.push('.coffee');

    // Configure browserify to load the angular application's main module at end of bundle definition script's execution.
    var mainModuleFile = path.join(angularScriptsDir, pkg.main);
    console.log(mainModuleFile);
    b.require(mainModuleFile /*, {expose: 'jchptf'}*/);

    //
    // Debug verboicty--see what the bundle's remapify filters did and what files satisfied either input pattern.
    b.on('remapify:files', function(files, expandedAliases, pattern){
      console.log('Files:', files);
      console.log('Expanded Aliases:', expandedAliases);
      console.log('Pattern:', pattern);
    });
    //

    // Configure remapify to select common and ngapp client files and apply project policy for naming commonjs
    // packages that bundle both angular client code and common components.
    b.plugin(
      remapify,
      [
        {
          src: sourceGlob, cwd: angularScriptsDir, filter: require('./filterAngularAlias')
        }, {
          src: sourceGlob, cwd: commonComponentsDir, filter: require('./filterCommonAlias')
        }
      ]
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

    // Substitute an instance of exorcist between browserify and file writer if this is not a
    // development artifact under construction.
    if (! isDevEnv) {
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
