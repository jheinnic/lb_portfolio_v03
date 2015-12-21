(function () {
  'use strict';

  module.exports = function jade(grunt, options) {
    var appConfig = options.appConfig;
    var _ = require('lodash');

    var configObj = {
      build: {
        options: {
          pretty: true,
          data: {
            titleStr: 'This is a title from data'
          }
        },
        files: {}
      }
    };

    // Normalize appConfig.source.client with a junk child, then deduct the length of the junk
    // child in order to determine the precise trim path for source paths on this host.
    var srcDirLength = appConfig.source.client.length;

    function addJadeFile(nextSrcPath) {
      // Use the length of normalized appModRoot to trim it away from normalized nextSrcPath,
      // leaving the common relative path info, then undo normalization for the sake of
      // matching grunt's expectations.
      nextSrcPath = nextSrcPath.replace(/\\/g, '\/');

      // Change file suffix for relative output files from .jade to .html.
      var relPath = nextSrcPath.slice(srcDirLength).replace(/\.jade$/, '.html');

      // Create the dev and dist map entries by appending the `
      // directory with each of both dev and dist root directories.
      configObj.build.files[appConfig.dev.client + relPath] = nextSrcPath;
    }

    var globFound =
      grunt.file.glob(
        appConfig.source.client + '/**/*.jade',
        {
          dot: true, sync: true, strict: true, stat: false,
          nonull: false, nosort: true, nounique: true, nodir: true
        }
      );
    _.forEach(globFound, addJadeFile);

    return configObj;
  };
}).call();
