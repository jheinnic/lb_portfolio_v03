define [
  'angular'
  'domReady'
  'portfolio'
], (angular, domReady) ->
  domReady (document) ->
    angular.bootstrap document, 'jch-portfolio'
