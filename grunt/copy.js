'use strict';

module.exports = function (grunt, options) {
  return {
    dev: {
      files: [
        {
          expand: true,
          dot: true,
          cwd: '<%= appConfig.app %>/',
          dest: '<%= appConfig.dev %>/',
          src: [
            'test/**/*', 'scripts/lr_init.js', 'styles/**/*.css', '.htaccess', '*.{ico,png,txt}', 'styles/fonts/**/*', 'fonts/**/*',
            'views/**/*.html',
          ]
        }, {
          expand: true,
          dot: true,
          cwd: '<%= appConfig.app %>/',
          dest: '<%= appConfig.dev %>/',
          src: [
            'test/**/*', 'scripts/lr_init.js', 'styles/**/*.css', '.htaccess', '*.{ico,png,txt}', 'styles/fonts/**/*', 'fonts/**/*',
            'views/**/*.html',
          ]
        }, {
          expand: true,
          cwd: '<%= appConfig.app %>',
          dest: '<%= appConfig.dev %>/images',
          src: [ '**/*.{bmp,webp,jpg,jpeg,gif,png}' ]
        }, {
          expand: true,
          cwd: '<%= appConfig.lbclient %>/',
          dest: '<%= appConfig.temp %>/lbclient/',
          src: 'browser.bundle.js'
        }
      ]
    },
    dist: {
      files: [
        {
          expand: true,
          dot: true,
          cwd: '<%= appConfig.app %>/',
          dest: '<%= appConfig.dist %>/',
          src: [
            // TODO: This line should be done by image minification
            '.htaccess', '*.{ico,png,txt}', 'styles/fonts/**/*', 'fonts/**/*', 'images/**/*.webp',
            'images/**/*.{jpg,jpeg,gif,png,svg}', 'views/**/*.html'
          ]
        }, {
          expand: true,
          dot: true,
          cwd: '<%= appConfig.app %>/',
          dest: '<%= appConfig.dist %>/',
          src: [
            // TODO: This line should be done by image minification
            '.htaccess', '*.{ico,png,txt}', 'styles/fonts/**/*', 'fonts/**/*', 'images/**/*.webp',
            'images/**/*.{jpg,jpeg,gif,png,svg}', 'views/**/*.html'
          ]
        }, {
          expand: true,
          dot: true,
          cwd: '<%= appConfig.app %>/',
          dest: '<%= appConfig.dist %>/',
          src: [
            // TODO: This line should be done by image minification
            '.htaccess', '*.{ico,png,txt}', 'styles/fonts/**/*', 'fonts/**/*', 'images/**/*.webp',
            'images/**/*.{jpg,jpeg,gif,png,svg}', 'views/**/*.html'
          ]
        }, {
          expand: true,
          dot: true,
          cwd: '<%= appConfig.app %>/',
          dest: '<%= appConfig.dist %>/',
          src: [
            // TODO: This line should be done by image minification
            '.htaccess', '*.{ico,png,txt}', 'styles/fonts/**/*', 'fonts/**/*', 'images/**/*.webp',
            'images/**/*.{jpg,jpeg,gif,png,svg}', 'views/**/*.html'
          ]
        }, {
          expand: true,
          cwd: '<%= appConfig.temp %>/',
          dest: '<%= appConfig.dist %>/',
          src: [
            'index.html', 'styles/**/*.css', // 'scripts/**/*.js',
            'images/generated/**/*'
          ]
        }, {
          expand: true,
          cwd: '<%= appConfig.lbclient %>/',
          dest: '<%= appConfig.dist %>/lbclient/',
          src: 'browser.bundle.js'
        }
      ]
    }
  };
};

