(function () {
  'use strict';

  module.exports = function (app) {
     /*
     var routes = require('../../client/ngapp/config/routes');
    Object.keys(routes).forEach(
      function(route) {
        app.get(route, function(req, res) {
        res.sendFile(app.get('indexFile'));
      }
           );
    */

    // Until there is a way to target specific paths,
    app.use(
      '/**', loopback.static(
        app.get('indexFile')
      )
    );
    app.use(app.loopback.static(app.get('indexFile')));
   };
}).call();
