'use strict';

module.exports = function mkdir(/*grunt, options*/) {
  return {
    build: {
      options: {
        mode: 755,
        create: [
          '<%= appConfig.dev %>/client',
          '<%= appConfig.dist %>/client',
          '<%= appConfig.temp %>/client',
          '<%= appConfig.dev %>/server',
          '<%= appConfig.dist %>/server',
          '<%= appConfig.temp %>/server'
        ]
      }
    }
  };
};
