'use strict';

module.exports = function (grunt, options) {
  return {
    dev: {
      files: [
        {
          expand: true,
          dot: true,
          cwd: '<%= appConfig.app %>',
          dest: '<%= appConfig.dev %>/client',
          src: [
            'lr_init.js',
            '**/*.{html,bmp,webp,jpg,jpeg,gif,png,svg,eot,ttf,woff,woff2}',
            '.htaccess',
            '*.{ico,png,txt}'
          ]
        }
      ]
    },
    dist: {
      files: [
        {
          expand: true,
          dot: true,
          cwd: '<%= appConfig.dev %>/client',
          dest: '<%= appConfig.dist %>/client',
          src: [
            '**/*.{html,bmp,webp,jpg,jpeg,gif,png,svg,eot,ttf,woff,woff2}',
            '.htaccess',
            '*.{ico,png,txt}'
          ]
        }
      ]
    }
  };
};

