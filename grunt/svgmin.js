'use strict';

module.exports =
  function svgmin(grunt,options) {
    return {
      dist: {
        files: [
          {
            expand: true,
            dot: true,
            cwd: '<%= appConfig.app %>',
            src: '**/*.svg',
            dest: '<%= appConfig.dist %>/client'
          }
        ]
      }
    };
  };
