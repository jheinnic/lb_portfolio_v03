'use strict';

module.exports = function htmlmin(/*grunt, options*/) {
  return {
    dist: {
      options: {
        collapseWhitespace: true,
        conservativeCollapse: true,
        collapseBooleanAttributes: true,
        removeCommentsFromCDATA: true,
        removeOptionalTags: true
      },
      files: {
        dot: true,
        expand: true,
        cwd: '<%= appConfig.dist %>/client',
        src: '**/*.html',
        dest: '<%= appConfig.dist %>/client'
      }
    }
  };
};
