'use strict';

var asyncTask = function (willFail) {
  if (willFail) {
    return new Promise(function(resolve, reject) {
      reject(new Error('Something went wrong'));
    });
  } else {
    return new Promise(function(resolve, reject) {
      var delay = Math.random() * 5000;
      var values = [delay, 1, 2, 3, 4, 5]
      setTimeout(function () {
        resolve(values);
      }, delay);
    });
  }
};

var makeMeLookSync = function(fn) {
  var iterator = fn();
  var loop = function(result) {
    console.log('Iterated:', result);
    if(!result.done) {
      result.value.then(
        function(res) { loop(iterator.next(res)) },
        function(err) { loop(iterator.throw(err)) }
      );
    }
  };

  loop(iterator.next());
};

makeMeLookSync(function* () {
  try {
    var result = yield asyncTask();
  
    console.log(result);
    return result;
  } catch (err) {
    console.log(err.message);
  }
});

console.log("After make me look sync");
