(function() {
  'use strict';

  module.exports = CrosswordController;
  CrosswordController.$inject = [];

  function CrosswordController($scope) {
    if ($scope === 'foo') {
      return;
    }
  }
}).call(window);

