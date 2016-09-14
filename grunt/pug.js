(function (module) {
  'use strict';

  module.exports = function pug(grunt, options) {
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
      // Denormalize glob's output as expected by grunt file mapping
      nextSrcPath = nextSrcPath.replace(/\\/g, '\/');

      // Use the length of appConfig.source.client to trim it away from 
      // nextSrcPath, leaving the common relative path info, then change
      // file suffix for relative output file from .jade to .html.
      var relPath = nextSrcPath.slice(srcDirLength).replace(/\.jade$/, '.html');

      // Create file map entry by appending the common path suffix to the
      // temp or dev build roots.  Use temp for index.html because it has
      // additional pre-processing before it reaches dev.
      if (relPath === '/index.html') {
        configObj.build.files[appConfig.temp.client + relPath] = nextSrcPath;
      } else {
        configObj.build.files[appConfig.dev.client + relPath] = nextSrcPath;
      }
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
}).call(this, module);
