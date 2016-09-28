(function () {
  'use strict';

  module.exports = function (options) {
    /*
     var routes = require('../../client/ngapp/config/routes');
     */
    var _ = require('lodash');
    var path = require('path');
    var process = require('process');

    var opts = _.defaults( {}, options, {indexFile: '../client/index.html'} );
    var indexFile = path.normalize( path.join( process.cwd(), opts.indexFile));
    console.log('** Index file is at ' + indexFile);

    function sendIndexFile(req, res) {
      // var request = req;
      var url = req.url;
      console.log(req, url);
      res.sendFile(indexFile);
    }

    return sendIndexFile;
  };
}).call(this);
