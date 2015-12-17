(function() {
  var ModelObject, foo;

  ModelObject = (function() {
    function ModelObject() {}

    ModelObject.isSubtypeOf = function(typeQuery) {
      if (typeQuery == null) {
        throw new Error("typeQuery argument must be defined");
      }
      return ModelObject.prototype.isPrototypeOf(typeQuery.prototype);
    };

    ModelObject.values = {
      numbers: [1, 2, 3, 4, 5],
      letters: ['a', 'b', 'c'],
      fruits: ['apple', 'banana', 'grape', 'orange']
    };

    ModelObject.traverseA = function*() {
      var i, key, len, ref;
      ref = Object.keys(this.values);
      for (i = 0, len = ref.length; i < len; i++) {
        key = ref[i];
        (yield ({
          type: key,
          values: function*() {
            var j, len1, ref1, value;
            ref1 = this.values[key];
            for (j = 0, len1 = ref1.length; j < len1; j++) {
              value = ref1[j];
              (yield value);
            }
          }
        }));
      }
    };

    ModelObject.traverseB = function*() {
      var items, key, ref;
      ref = this.values;
      for (key in ref) {
        items = ref[key];
        (yield ({
          type: key,
          values: function*() {
            var i, item, len;
            for (i = 0, len = items.length; i < len; i++) {
              item = items[i];
              (yield item);
            }
          }
        }));
      }
    };

    return ModelObject;

  })();

  foo = 'bar';

  switch (foo) {
    case 'bar':
      console.log('is bar');
      break;
    case 'baz':
      console.log('is baz');
      break;
    default:
      console.log('is not known');
  }

  module.exports = ModelObject;

}).call(this);
