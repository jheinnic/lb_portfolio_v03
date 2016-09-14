(function() {
  'use strict';

  module.exports = runNavigation;
  runNavigation.$inject = ['JchNavData'];

  console.log('Loading jchptf.crosswords init navigation runner');

  function runNavigation(JchNavData) {
    console.log('Running jchptf.crosswords init navigation runner');

    JchNavData.changeNavBarModel(function director(builder) {
      builder.appendStateTab('Crosswords', 'site.crosswords');
    });
  }
}).call(window);
