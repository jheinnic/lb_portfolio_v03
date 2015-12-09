var explorer = require('loopback-explorer');

module.exports = function(server) {
  var restApiRoot = server.get('restApiRoot');

  var explorerApp = explorer(server, { mountPath: '/explorer', basePath: restApiRoot });
  server.once('started', function() {
    var baseUrl = server.get('url').replace(/\/$/, '');
    console.log('Browse your REST API at %s%s', baseUrl, '/explorer');
  });
};
