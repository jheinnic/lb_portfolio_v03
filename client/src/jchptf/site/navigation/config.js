(function(sprintf, console) {
  'use strict';

  module.exports = navigationModuleConfig;
  navigationModuleConfig.$inject=['$stateProvider', 'NavbarDataProvider'];

  console.log('Loading jchptf navigation config');

  var _ = require('lodash');
  // var sprintf = require('sprintf');

  function _handleFeatureTabData(stateName, tabData, NavbarDataProvider) {
    if (_.isObject(tabData)) {
      NavbarDataProvider.addFeatureTab(
        tabData.tabId, tabData.displayName, tabData.rankOrder, stateName, tabData.params);
    } else {
      console.warn(
        sprintf('%s is not a navigation tab data descriptor object.  Skipping...', tabData)
      );
    }
  }

  /**
   * @ngdoc method
   * @name jchptf.site.navigation.siteNavigation:config
   *
   * @description
   * Routing configuration for navigation application's siteNavigation module.
   * Defines the landing page, /home.
   */
  function navigationModuleConfig($stateProvider, NavbarDataProvider) {
    $stateProvider = $stateProvider.decorate(
      'data', function findNavbarLinks(state, parent) {
        var data = parent(state);
        if (_.isArray(data.jchptfGlobalfNav)) {
          var tabInstData;
          for (tabInstData in data.jchptfGlobalNav) {
            _handleFeatureTabData(state.name, tabInstData, NavbarDataProvider);
          }
        } else if (_.isObject(data.jchptfGlobalNav)) {
          _handleFeatureTabData(state.name, data.jchptfGlobalNav, NavbarDataProvider);
        }
      }
    );

    // Establish the abstract base states for feature hierarchies that either
    // do or do not require authentication.  Any feature that has a mix of
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
      'site',
      {
        abstract: true,
        parent: 'root',
        data: {
          requiresLogin: true,
          saveState: true
        },
        resolve: {
          userInfo: ['AuthService', function(AuthService) {
            var retVal;
            var latestEvent = AuthService.getLatestEvent();
            if (latestEvent.eventType.isAuthenticated()) {
              retVal = latestEvent.userInfo.$promise;
            }
            return retVal;
          }]
        },
        views: {
          navbar: {
            templateUrl: 'jchptf/_globalNavbar.view.html',
            controller: 'GlobalNavController as vm'
          },
          appView: {
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
}).call(window, window.sprintf, window.console);
