define [
  'angular'
  'domReady'
  'cs!portfolio'
], (angular, domReady) ->
  domReady (document) ->
    angular.bootstrap document, 'jch-portfolio'
