define [
    'angular'
    'angular-route'
    'angular-cookies'
    'ui-router'
    'ui-bootstrap-tpls'
    'angular-strap/aside'
    'angular-strap/button'
    'angular-strap/navbar'
    'cs!public/crosswords_angular'
  ], () ->
    appModule = angular.module(
      'jch-portfolio'
      [
        'ng'
        'ngRoute'
        'ngCookies'
        'ui.router'
        'ui.bootstrap'
        'mgcrea.ngStrap.aside'
        'mgcrea.ngStrap.navbar'
        'crosswords'
      ]
    )
    return appModule