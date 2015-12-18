(function () {
  'use strict';

  module.exports = function (app) {
    /*
    var routes = require('../../client/ngapp/config/routes');
    */
    var path = require('path');
    var process = require('process');
    var indexFile =
      path.normalize(
        path.join(process.cwd(), app.get('indexFile')));
    console.log('** Index file is at ' + indexFile);

    function sendIndexFile(req, res) {
      res.sendFile(indexFile);
    }

    // Until there is a way to target specific paths,
    app.get('/', sendIndexFile);
    app.get('/**', sendIndexFile);
    app.get('/index.html', sendIndexFile);
  };
}).call();
