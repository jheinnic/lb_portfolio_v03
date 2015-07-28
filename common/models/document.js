(function () {
  'use strict';

  var uuid = require('uuid');
  var async = require('async');
  var loopback = require('loopback');

  module.exports = function(Document) {
    Document.definition.properties.uuid.default = uuid.v1;

    Document.beforeSave = function beforeSave(next, model) {
      if (!model.uuid)
        model.uuid = uuid.v1();
      next();
    };

    Document.stats = function stats(filter, cb) {
      var stats = {};
      // cb = arguments[arguments.length - 1];
      // var Document = this;

      async.parallel(
        [countComplete, countTotal],
        function (err) {
          if (err) return cb(err);
          stats.remaining = stats.total - stats.completed;
          cb(null, stats);
        }
      );
    }

    function countComplete(cb) {
      Document.count(
        {completed: true},
        function (err, count) {
          stats.completed = count;
          cb(err);
        }
      );
    }

    function countTotal(cb) {
      Document.count(
        function (err, count) {
          stats.total = count;
          cb(err);
        }
      );
    }
  };

  Document.remoteMethod(
    'stats', {
      accepts: {arg: 'filter', type: 'object'},
      returns: {arg: 'stats', type: 'object'},
      http: {path: '/stats'}
    }, Document.stats
  );
}).call(window);
