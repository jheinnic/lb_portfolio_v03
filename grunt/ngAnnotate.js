 'use strict';

 module.exports = function ngAnnotate(grunt, options) {
   return {
     options: {},
     build: {
       files: {
         expand: true,
         cwd: '<%= appConfig.app %>',
         src: '**/*.js',
         dest: '<%= appConfig.dev %>/client'
       }
     }
   };
};
