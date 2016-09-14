/*
 * Global configuration shared by components.
 */
(function(module) {

  'use strict';

  var process = require('process');
  var vsprintf = require('sprintf-js').vsprintf;
  var p = require('./package.json');
  
  var isDevEnv = (process.env.NODE_ENV || 'development') === 'development';
  module.exports.isDevEnv = isDevEnv;
  
  // The path where to mount the REST API app
  var version = p.version.split('.').shift();
  module.exports.restApiRoot = '/api' + (version > 0 ? '/v' + version : '');
  
  // TODO: https?
  var proto;
  if (process.env.REST_API_SSL && process.env.REST_API_SSL !== '0') {
    proto = 'https';
  } else {
    proto = 'http';
  }
  
  // TODO: How to build this into the dist?
  // Set restApiUr to  URL through which browser clients can access REST API.
  // Replace with a full url (including hostname) if client HTML is served
  // from a different server than REST API.
  var restApiUrl;
  if (isDevEnv) {
    if (process.env.REST_API_URL) {
      // TODO: What if SSL is enabled too?
      restApiUrl = process.env.REST_API_URL;
    } else if (process.env.REST_API_WITH_ASSETS) {
      // TODO: What if SSL is enabled too?
      restApiUrl = module.exports.restApiRoot;
    } else {
      var port =
        process.env.REST_API_PORT ? Number.parseInt(process.env.REST_API_PORT) : 3000;
      var host =
        process.env.REST_API_HOST ? process.env.REST_API_HOST : 'localhost';
      restApiUrl =
        vsprintf('%s://%s:%d' + module.exports.restApiRoot, [proto, host, port]);
    }
  } else {
    if (process.env.REST_API_URL) {
      // TODO: What if SSL is enabled too?
      restApiUrl = process.env.REST_API_URL;
    } else if(process.env.REST_API_HOST && process.env.REST_API_PORT) {
      restApiUrl = vsprintf(
        '%s://%s:%d' + module.exports.restApiRoot,
        [proto, process.env.REST_API_HOST, Number.parseInt(process.env.REST_API_PORT)]
      );
    } else {
      // TODO: What if SSL is enabled too?
      restApiUrl = module.exports.restApiRoot;
    }
  }

  module.exports.restApiUrl = restApiUrl;

  console.log('Global: ');
  console.log(module.exports);
}).call(this, module); 
