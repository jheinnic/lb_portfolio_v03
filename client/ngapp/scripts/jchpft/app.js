'use strict';

/**
 * @ngdoc overview
 * @name loopbackExampleFullStackApp
 * @description
 * # loopbackExampleFullStackApp
 *
 * Main module of the application.
 */
angular
  .module('jchpft.app', [
    'ui.router', 'app.context', 'app.auth', 'app.notify', 'app.xw.describe', 'app.xw.resolve'
  ]):
