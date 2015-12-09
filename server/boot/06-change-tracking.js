module.exports = function(server) {
  var _        = require('lodash'),
      db       = server.dataSources.db,
      modelCfg = require('../model-config');

  // TODO(ritch) this should be unecessary soon....
  _.chain(Object.keys(modelCfg))
    .without('_meta')
    .map(function (name) { return server.models[name]; })
    .filter(function(model) { return ! _.isUndefined(model.Change); })
    .each(function(model) { server.model(model.getChangeModel()); });

  //.map(function (name) { console.log(db[name]); return db[name]; })
  //   .filter(function(model) { console.error(model); return _.isDefined(model.Change); })
  //   .valueOf();
  // console.log(models);

  // var Todo = server.models.Todo;
  // server.model(Todo.getChangeModel());
};
