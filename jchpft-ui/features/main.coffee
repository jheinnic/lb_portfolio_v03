requirejs.config(
  baseUrl: '/',
  paths:
    angular: 'angular/angular'
    'angular-animate': 'angular-animate/angular-animate'
    'angular-cookies': 'angular-cookies/angular-cookies'
    'angular-locale_en-us': 'angular-i18n/angular-locale_en-us'
    'angular-resource': 'angular-resource/angular-resource'
    'angular-route': 'angular-route/angular-route'
    domReady: 'requirejs-domready/domReady'
    underscore: 'underscore/underscore'
    'ui-bootstrap': 'angular-bootstrap/ui-bootstrap'
    'ui-bootstrap-tpls': 'angular-bootstrap/ui-bootstrap-tpls'
    'angular-strap.tpl': 'angular-strap/dist/angular-strap.tpl'

  pkgs: [
    {
      path: 'cs'
      dist: 'require-cs'
      main: 'cs'
    }
    {
      path: 'angular-strap'
      dist: 'angular-strap/dist/modules'
      main: '../angular-strap'
    }
  ]

  shim:
    angular:
      deps: 'angular-locale_en-us'
      exports: 'angular'
    'angular-locale_en-us': []
    'angular-animate': ['angular']
    'angular-cookies': ['angular']
    'angular-resource': ['angular']
    'angular-route': ['angular']
    'angular-strap.tpl': ['angular']
    'angular-strap/affix': ['angular-strap/dimension', 'angular-strap.tpl']
    'angular-strap/aside': ['angular-strap/modal', 'angular-strap.tpl']
    'angular-strap/button': ['angular-animate', 'angular-strap.tpl']
    'angular-strap/modal': ['angular-strap/dimension', 'angular-strap.tpl']
    'angular-strap/navbar': ['angular-strap.tpl']
    'angular-strap/tooltip': ['angular-animate', 'angular-strap/dimension', 'angular-strap.tpl']
    'angular-strap': ['angular-strap.tpl']
    'ui-bootstrap': ['angular']
    'ui-bootstrap-tpl': ['angular']
    'underscore': []

  deps: 'cs!app'
)
