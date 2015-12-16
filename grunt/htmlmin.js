(function () {
  'use strict';

  module.exports = function htmlmin(grunt, options) {
    console.log('Config htmlmin', new Date());
    var appConfig = options.appConfig;
    var _ = require('lodash');
    var configObj = {
      dist: {
        options: {
          collapseWhitespace: true,
          conservativeCollapse: true,
          collapseBooleanAttributes: true,
          removeCommentsFromCDATA: true,
          removeOptionalTags: true
        },
        files: { }
      }
    };

    // Denormalize nextSrcPath by converting all '\\' separators to '/'.
    // Use denormalized length of appConfig.source.client to trim it away from common relative path info.
    // Prepend the output root dir and create a dest -> src file map.

    // Map each file to itself for in-place transformation.
    function addHtmlTemplate(nextSrcPath) {
      nextSrcPath = nextSrcPath.replace(/\\/g, '\/');
      // configObj.dist.files[appConfig.dist.client + nextSrcPath.slice(srcDirLength)] = nextSrcPath;
      configObj.dist.files[nextSrcPath] = nextSrcPath;
    }

    console.log('Pre-Glob htmlmin', new Date());
    var globFound = grunt.file.glob(
      appConfig.dist.client + '/**/*.html', {
        sync: true, strict: true, stat: false, dot: true,
        nonull: false, nosort: true, nounique: true, nodir: true
      }
    );
    _.forEach(globFound, addHtmlTemplate);
    console.log('Post-Glob htmlmin', new Date());

    return configObj;
  };
}).call();
