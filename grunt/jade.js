(function () {
  'use strict';

  module.exports = function jade(grunt, options) {
    var appConfig = options.appConfig;
    var inputGlob = appConfig.source.client + '/**/*.jade';
    var path = require('path');
    var _ = require('lodash');

    var configObj = {
      dev: {
        options: {
          data: {
            isDev: true,
            titleStr: 'This is a title from data'
          }
        },
        files: {}
      },
      dist: {
        options: {
          data: {
            isDev: false,
            titleStr: 'This is a title from data'
          }
        },
        files: {}
      }
    };

    // Normalize appConfig.source.client with a junk child, then deduct the length of the junk
    // child in order to determine the precise trim path for source paths on this host.
    var srcDirLength = path.normalize(appConfig.source.client + '/junk').length - 4;

    var distFiles = configObj.dist.files;
    var distDir = appConfig.dist.client + '/';

    var devFiles = configObj.dev.files;
    var devDir = appConfig.dev.client + '/';

    function addJadeFile(nextSrcPath) {
      // Use the length of normalized appModRoot to trim it away from normalized nextSrcPath,
      // leaving the common relative path info, then undo normalization for the sake of
      // matching grunt's expectations.
      var normSrcPath = path.normalize(nextSrcPath);

      // Change file suffix for relative output files from .jade to .html.
      var relPath = normSrcPath.slice(srcDirLength).replace(/\.jade$/, '.html').replace(/\\/g, '\/');

      // Create the dev and dist map entries by appending the `
      // directory with each of both dev and dist root directories.
      devFiles[devDir + relPath] = distFiles[distDir + relPath] = normSrcPath.replace(/\\/g, '\/');
    }

    var globFound = grunt.file.glob(
      inputGlob, {
        sync: true,
        strict: true,
        stat: false,
        dot: true,
        nonull: false,
        nosort: true,
        nounique: true,
        nodir: true
      }
    );
    _.forEach(globFound, addJadeFile);

    return configObj;
  };
}).call();
