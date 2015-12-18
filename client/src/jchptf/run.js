(function() {
  'use strict';

  module.exports = portfolioAppLauncher;

  console.log('Loading jchptf run');
  // require('jchptf').run(portfolioAppLauncher);
  // angular.module('jchptf').run(portfolioAppLauncher);

  portfolioAppLauncher.$inject=['$state'];

  function portfolioAppLauncher ($state) {
    console.log('Running jchptf run: ', $state);
  }

}).call(window);
