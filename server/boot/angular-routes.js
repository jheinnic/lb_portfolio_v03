(function() {
  module.exports = function(app) {
    /* var routes = require('../../client/ngapp/config/routes'); */
    var routes = {
      '/crosswords': true, '/crosswords/tickets/:ticketId': true, '/index.html': true, '/home': true, '/': true
    };

    Object.keys(routes).forEach(
      function (route) {
        app.get(
          route, function (req, res) {
            res.sendFile(app.get('indexFile'));
          }
        );
      }
    );
  }
}).call(this);
