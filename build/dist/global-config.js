/*
 * Global configuration shared by components.
 */

var process = require('process');
var vsprintf = require('sprintf-js').vsprintf;

// The path where to mount the REST API app
exports.restApiRoot = '/api';

// TODO: https?
var proto;
if (process.env.REST_API_SSL && process.env.REST_API_SSL !== '0') {
  proto = 'https';
} else {
  proto = 'http';
}

// TODO: How to build this into the dist?
// The URL where the browser client can access the REST API is available
// Replace with a full url (including hostname) if your client is being
// served from a different server than your REST API.
if (process.env.NODE_ENV === 'development') {
  if (process.env.REST_API_URL) {
    // TODO: What if SSL is enabled too?
    exports.restApiUrl = process.env.REST_API_URL;
  } else if (process.env.REST_API_WITH_ASSETS) {
    // TODO: What if SSL is enabled too?
    exports.restApiUrl = exports.restApiRoot;
  } else {
    var port = process.env.REST_API_PORT ? Number.parseInt(process.env.REST_API_PORT) : 3000;
    var host = process.env.REST_API_HOST ? process.env.REST_API_HOST : 'localhost';
    exports.restApiUtl = vsprintf('%s://%s:%d/', [proto, host, port]);
  }
} else {
  if (process.env.REST_API_URL) {
    // TODO: What if SSL is enabled too?
    exports.restApiUrl = process.env.REST_API_URL;
  } else if(process.env.REST_API_HOST && process.env.REST_API_PORT) {
    exports.restApiUrl = vsprintf(
      '%s://%s:%d/',
      [proto, process.env.REST_API_HOST, Number.parseInt(process.env.REST_API_PORT)]
    );
  } else {
    // TODO: What if SSL is enabled too?
    exports.restApiUrl = exports.restApiRoot;
  }
}
