requirejs.config(
  baseUrl: '/app',
  paths:
    angular: 'angular/angular'
    'angular-animate': 'angular-animate/angular-animate'
    'angular-cookies': 'angular-cookies/angular-cookies'
    'angular-locale_en-us': 'angular-i18n/angular-locale_en-us'
    'angular-loader': 'angular-loader/angular-loader'
    'angular-resource': 'angular-resource/angular-resource'
    'angular-route': 'angular-route/angular-route'
    domReady: 'requirejs-domready/domReady'
    underscore: 'underscore/underscore'
    'ui-bootstrap': 'angular-bootstrap/ui-bootstrap'
    'ui-bootstrap-tpls': 'angular-bootstrap/ui-bootstrap-tpls'
    'angular-strap.tpl': 'angular-strap/dist/angular-strap.tpl'

  packages: [
    {
      name: 'cs'
      location: 'require-cs'
      main: 'cs'
    }
    {
      name: 'angular-strap'
      location: 'angular-strap/dist/modules'
      main: '../angular-strap'
    }
  ]

  shim:
    angular:
      deps: ['angular-locale_en-us']
      exports: 'angular'
    'angular-loader':
      deps: []
      exports: 'angular'
    'angular-locale_en-us': ['angular-loader']
    'angular-animate': ['angular']
    'angular-cookies': ['angular']
    'angular-resource': ['angular']
    'angular-route': ['angular']
    'angular-strap.tpl': ['angular', 'angular-strap']
    'angular-strap/affix': ['angular', 'angular-strap/dimensions', 'angular-strap.tpl']
    'angular-strap/aside': ['angular', 'angular-strap/modal', 'angular-strap.tpl']
    'angular-strap/button': ['angular', 'angular-animate', 'angular-strap.tpl']
    'angular-strap/modal': ['angular', 'angular-strap/dimensions', 'angular-strap.tpl']
    'angular-strap/navbar': ['angular', 'angular-strap.tpl']
    'angular-strap/tooltip': ['angular', 'angular-animate', 'angular-strap/dimensions', 'angular-strap.tpl']
    'angular-strap': ['angular']
    'ui-bootstrap': ['angular-loader']
    'ui-bootstrap-tpl': ['angular-loader']
    'underscore': []

  # deps: 'bootstrap'
)
require [
  'angular'
  'domReady'
  'portfolio/main'
], (angular, domReady) ->
  domReady (document) ->
    angular.bootstrap document, ['jch-portfolio']
