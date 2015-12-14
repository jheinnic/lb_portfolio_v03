'use strict';

// TODO: Explore whether or not *.webp files are compatible here.  Currently they are presumed
//       not compatible, but that could just be a conservative assumption.
module.exports = function imagemin(/*grunt, options*/) {
  return {
    dist: {
      files: [
        {
          expand: true,
          dot: true,
          cwd: '<%= appConfig.app %>',
          src: '**/*.{bmp,png,jpg,jpeg,gif}',
          dest: '<%= appConfig.dist %>/client'
        }
      ]
    }
  };
};
