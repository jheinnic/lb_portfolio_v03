(function () {
  'use strict';

  module.exports = function (app) {
     /*
     var routes = require('../../client/ngapp/config/routes');
     function routePathToIndexFile(route) {
       app.get(
         route,
         function(req, res) {
           app.loopback.static(
             app.get('indexFile')
           );
         }
       )
     }
    Object.keys(routes).forEach(routePathToIndexFile);
    */

    // Until there is a way to target specific paths,
    app.use(
      '/**', app.loopback.static(
        app.get('indexFile')
      )
    );
   };
}).call();
