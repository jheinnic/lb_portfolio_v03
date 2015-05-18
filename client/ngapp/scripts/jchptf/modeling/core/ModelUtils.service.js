/**
 * Created by John on 5/16/2015.
 */
(function() {
  module.exports = ModelUtils;

  function ModelUtils() {
  }

  ModelUtils.prototype.makeImmutable = function makeImmutable(obj) {
    if ((typeof obj === "object" && obj !== null) ||
      (Array.isArray ? Array.isArray(obj) : obj instanceof Array) ||
      (typeof obj === "function")) {

      Object.freeze(obj);

      for (var key in obj) {
        if (obj.hasOwnProperty(key)) {
          makeImmutable(obj[key]);
        }
      }
    }
    return obj;
  };
});
