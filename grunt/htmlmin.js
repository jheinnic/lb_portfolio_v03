'use strict';

// TODO: Right now, only index.html is re-processed through htmlbuild and jade.  Partial HTML is
//       not re-processed and so cannot make use of the jade isDevelopment flag.  Problem?
module.exports = function(grunt, options) {
  return {
    dist: {
      options: {
        collapseWhitespace: true,
        conservativeCollapse: true,
        collapseBooleanAttributes: true,
        removeCommentsFromCDATA: true,
        removeOptionalTags: true
      },
      files: [
        {
          '<%= appConfig.dist %>/client/index.html': '<%= appConfig.dist %>/client/index.html'
        },
        {
          dot: true,
          expand: true,
          cwd: '<%= appConfig.dev %>/client',
          src: ['**/*.html', '!index.html'],
          dest: '<%= appConfig.dist %>/client'
        }
      ]
    }
  };
}
