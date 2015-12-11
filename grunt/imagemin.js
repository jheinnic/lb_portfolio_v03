'use strict';

module.exports =
  function imagemin(grunt, options) {
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
