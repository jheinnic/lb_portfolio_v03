(function() {
  'use strict';

  module.exports = portfolioModuleConfig;
  portfolioModuleConfig.$inject=[
    '$locationProvider', '$urlRouterProvider', '$stateProvider'
  ];

  console.log('Loading jchptf config');

  /**
   * @ngdoc method
   * @name jchptf.site.navigation.siteNavigation:config
   *
   * @description
   * Routing configuration for portfolio application's siteNavigation module.
   * Defines the landing page, /home.
   */
  function portfolioModuleConfig(
    $locationProvider, $urlRouterProvider, $stateProvider
  ) {

    // Establish the abstract base states for feature hierarchies that either
    // do or do not require autentication.  Any feature that has a mix of
    // mandatory and optional authentication requirements shoud place its
    // hierarchy under the 'site' subtree, and manually twiddle requiresLogin
    // and saveState boolean flags to match their requirements, because
    // template/controller/state sharing is often a much stronger requirement
    // than managing authentication requirement flags.
    //
    // Flag definitions:
    // requireLogin: If true, overlay a login modal and require successful
    //                authentication before navigating to the destination page.
    // saveState: Only applies if requiresLogin = true.  If saveState is true,
    //            return to the intended destination after login.  If saveState
    //            is false, return to the default post-login page instead.  This
    //            is primarily used to prevent infinite loops on at logout!
    $stateProvider.state(
      'root',
      {
        templateUrl: 'jchptf/_rootLayout.view.html',
        controller: 'RootController as vm',
        abstract: true,
      }
    );
    $stateProvider.state(
      'defaultLayout',
      {
        abstract: true,
        parent: 'root',
        views: {
          navbar: {
            templateUrl: 'jchptf/_mainNavbar.view.html'
          },
          content: {
            templateUrl: 'jchptf/_threePanelApp.view.html'
          }
        }
      }
    );
    $stateProvider.state(
      'site',
      {
        abstract: true,
        parent: 'defaultLayout',
        data: {
          requiresLogin: true,
          saveState: true
        },
        views: {
          contextBar: {
            templateUrl: 'jchptf/_noContextBar.view.html'
          },
          sidePanel: {
            templateUrl: 'jchptf/_blankArea.view.html'
          },
          appCanvas: {
            templateUrl: 'jchptf/_blankArea.view.html'
          }
        }
      }
    );
    $stateProvider.state(
      'site.public',
      {
        abstract: true,
        data: {
          requiresLogin: false,
          saveState: false
        }
      }
    );
  }
}).call(window);
