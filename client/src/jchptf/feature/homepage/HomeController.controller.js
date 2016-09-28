(function() {
  'use strict';

  module.exports = HomeController;
  HomeController.$inject = ['$state'];
  // HomeController.$inject = ['$state', 'IdentityContext', 'JchNavData'];
  // function HomeController($state, IdentityContext, JchNavData) {
  // }

  function HomeController($state) {
    this.state = $state;
  }
}).call(window);

