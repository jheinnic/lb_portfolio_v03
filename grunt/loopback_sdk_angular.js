(function() {
  'use strict';
  
  module.exports = function loopbackSdkAngular(grunt, options) {
    var appConfig = options.appConfig;
    var path = require('path');
  
    // TODO: Account for production URL differences?
    return {
      options: {
        input: 'server/server.js',
        output: path.join( appConfig.temp.client, appConfig.app, 'lbServices/lbServices.js'),
        ngModuleName: appConfig.app + '.lbServices',
        apiUrl: '/api'
      },
      dev: { }
    };
  };
}).call(this);
