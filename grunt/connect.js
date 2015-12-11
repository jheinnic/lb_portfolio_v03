'use strict';

module.exports = function (grunt, options) {
  var getAvailPort = require('./utils/getAvailPort');
  var assetPort = getAvailPort(8888);
  var lrPort = getAvailPort(35729);

  return {
    serve: {
      options: {
        // hostname: 'localhost',
        // host: 'localhost',
        base: '',
        port: assetPort,
        livereload: lrPort,
        keepalive: true,
        middleware: function (connect) {
          return [
            connect.static('<%= appConfig.dev %>/client'),
            connect.use('/vendor', '<%= appConfig.vendor %>')
          ];
        }
      }
    },
    test: {
      options: {
        base: '',
        port: 9999,
        middleware: function (connect) {
          return [
            connect.static('<%= appConfig.dev %>/client'),
            connect.use('/vendor', '<%= appConfig.vendor %>')
          ];
        }
      }
    },
    coverage: {
      options: {
        port: 5555,
        keepalive: true,
        base: 'coverage/',
        directory: 'coverage/'
      }
    }
  };
};
