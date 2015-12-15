/*
 * Global configuration shared by components.
 */

var process = require('process');
var vsprintf = require('sprintf-js').vsprintf;

// The path where to mount the REST API app
exports.restApiRoot = '/api';

// TODO: How to build this into the dist?
// The URL where the browser client can access the REST API is available
// Replace with a full url (including hostname) if your client is being
// served from a different server than your REST API.
if (process.env.NODE_ENV === 'development') {
  if (process.env.REST_API_URL) {
    exports.restApiUrl = process.env.REST_API_URL;
  } else if (process.env.REST_API_WITH_ASSETS) {
    exports.restApiUrl = exports.restApiRoot;
  } else {
    var port = process.env.REST_API_PORT ? Number.parseInt(process.env.REST_API_PORT) : 3000;
    console.log(port);
    exports.restApiUtl = vsprintf('http://localhost:%d/', [port]);
  }
} else {
  if (process.env.REST_API_URL) {
    exports.restApiUrl = process.env.REST_API_URL;
  } else if(process.env.REST_API_HOST && process.env.REST_API_PORT) {
    exports.restApiUrl = vsprintf('http://%s:%d/', process.env.REST_API_HOST, process.env.REST_API_PORT);
  } else {
    exports.restApiUrl = exports.restApiRoot;
  }
}
