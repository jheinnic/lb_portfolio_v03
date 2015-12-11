 'use strict';

 module.exports = function ngAnnotate(grunt, options) {
   return {
     options: {},
     build: {
       files: {
         '<%= appConfig.dist %>/client/app.js': '<%= appConfig.dev %>/client/app.js'
       }
     }
   };
};
