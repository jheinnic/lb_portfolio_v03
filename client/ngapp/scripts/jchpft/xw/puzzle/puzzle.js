(function(angular) {
    'use strict';
    
    /**
     * @ngdoc overview
     * @name jchpft.xw.puzzle
     * @description
     * A module encapsulating the artifact that provides an experience 
     * supporting the collection of at-face ticket infomation against which
     * a subseqeuent solution artifact will be created to report the result
     * on revealing the hidden information not available herein.
     */
    angular.module('jchpft.xw.puzzle', ['ui.router', 'app.context',
'app.auth', 'app.notify', 'jchpft.xw', 'jchpft.xw.solution' ]
    ]):
}(window.angular));
