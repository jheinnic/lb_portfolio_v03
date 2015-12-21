(function () {
  'use strict';

  module.exports = function htmlmin(grunt, options) {
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
      configObj.dist.files[nextSrcPath] = nextSrcPath;
    }

    _.forEach(
      grunt.file.glob(
        appConfig.dist.client + '/**/*.html', {
          sync: true, strict: true, stat: false, dot: true,
          nonull: false, nosort: true, nounique: true, nodir: true
        }
      ), addHtmlTemplate
    );

    return configObj;
  };
}).call();
