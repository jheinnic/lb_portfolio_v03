'use strict';

module.exports = function (grunt, options) {
  return {
    build: {
      options: {
        mode: 755,
        create: [
          '<%= appConfig.dev %>',
          '<%= appConfig.dist %>',
          '<%= appConfig.dev %>/client',
          '<%= appConfig.dist %>/client',
          '<%= appConfig.dev %>/server',
          '<%= appConfig.dist %>/server',
          '<%= appConfig.temp %>'
        ]
      }
    }
  }
};
