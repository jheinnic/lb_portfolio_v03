module.exports = function(TimeStamped, options) {
  // TimeStamped is the model class
  // options is an object containing the config properties from model definition
  var _ = require('lodash');

  TimeStamped.defineProperty('created', {type: Date, default: '$now'});
  TimeStamped.defineProperty('modified', {type: Date, default: '$now'});

  TimeStamped.observe('before save', function(ctx, next) {
    if (ctx.isNewInstance() === false) {
      ctx.instance.modified = new Date();
    } else if (_.isDefined(ctx.currentInstance)) {
      ctx.currentInstance.modified = new Date();
    }

    next();
  })
}
