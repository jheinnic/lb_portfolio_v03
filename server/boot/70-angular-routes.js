(function () {
  'use strict';

  module.exports = function (server) {
    /*
    var routes = require('../../client/ngapp/config/routes');
    */
    var path = require('path');
    var process = require('process');
    var indexFile =
      path.normalize(
        path.join(process.cwd(), server.get('indexFile')));
    console.log('** Index file is at ' + indexFile);

    function sendIndexFile(req, res) {
      res.sendFile(indexFile);
    }

    // Add a status page under /status
    var router = server.loopback.Router();
    router.get('/status', server.loopback.status());

    // Until there is a way to target specific paths,
    router.get('/', sendIndexFile);
    router.get('/index', sendIndexFile);
    router.get('/index.htm', sendIndexFile);
    router.get('/index.html', sendIndexFile);

    // This wildcard must follow after all NGApp path have explicit routing!
    router.get('/**', sendIndexFile);

    server.use(router);
  };
}).call();
