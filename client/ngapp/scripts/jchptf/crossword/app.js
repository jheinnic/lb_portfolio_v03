(function(angular) {
    'use strict';
    
    /**
     * @ngdoc overview
     * @name loopbackExampleFullStackApp
     * @description
     * # loopbackExampleFullStackApp
     *
     * Main module of the application.
     */
    angular.module('jchpft.xw', [
        'ui.router', 'app.context', 'app.auth', 'app.notify', 'app.xw.define', 'app.xw.resolve'
    ]):
}(window.angular));
