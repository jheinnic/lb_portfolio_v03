module.exports   = function(app) {
  var _          = require('lodash'),
      async      = require('async'),
      vsprintf   = require('sprintf-js').vsprintf,
      modelCfg   = require('../model-config'),
      db         = app.dataSources.db,
      modelNames = _.without(Object.keys(modelCfg), '_meta');

  db.isActual(
    modelNames,
    function onActualIsKnown(err, result) {
      if (err) {
        var msg = vsprintf(
          'Failed to discover whether or not %s are up to date.  Bootstrap is aborting: %s', [modelNames.toString(), err.toString()]
        );
        console.error(msg);
        throw new Error(msg);
      } else if (result) {
        console.info(
          vsprintf('Models in %s are all up to date.  No special action was required', [modelNames.toString()])
        );
      } else {
        db.autoupgrade(
          modelNames,
          function onAutoUpdate(err, result) {
            // if (err && err.code !== '42P07') {
            if (err) {
              var msg = vsprintf(
                'Failed to autoupgrade %s after determining at least one was out of date.  Bootstrap is aborting: %s',
                [modelNames.toString(), err.stack.toString()]
              );
              console.error(msg);
              throw new Error(msg);
            } else if (result) {
              console.info(
                vsprintf('Successfully autoupgraded %s: %s', [modelNames.toString(), result.toString()])
              );
            } else {
              console.info(
                vsprintf('Successfully autoupgraded %s', [modelNames.toString()])
              );
            }
          }
        );
      }
    }
  );

  //function onBuiltinSchemaCreated() {
  //  db.User.create(
  //    [
  //      { username: "admin", password: "password", email: "admin@questinygroup.com", emailVerified: true, verificationToken: "email1", created: Date.now() },
  //      { username: "anjan", password: "WiNS2015", email: "anjan@questinygroup.com", emailVerified: true, verificationToken: "email2", created: Date.now() },
  //      { username: "john", password: "abcd1234", email: "jheinnic@lycos.com", emailVerified: true, verificationToken: "email3", created: Date.now() },
  //      { username: "keith", password: "WiNS2015", email: "keith@questinygroup.com", emailVerified: true, verificationToken: "email4", created: Date.now() },
  //      { username: "questiny", password: "WiNS2015", email: "gis@questinygroup.com", emailVerified: true, verificationToken: "email5", created: Date.now() },
  //      { username: "robert", password: "WiNS2015", email: "robert@questinygroup.com", emailVerified: true, verificationToken: "email6", created: Date.now() },
  //      { username: "stan", password: "WiNS2015", email: "stan@questinygroup.com", emailVerified: true, verificationToken: "email7", created: Date.now() }
  //    ],
  //    function onUsersCreated(err, users) {
  //      if (err) {
  //        console.error('Failed to create user: \n', err);
  //        throw err;
  //      }
  //
  //      console.log('Models created: \n', users);
  //    }
  //  );
  //}
};
