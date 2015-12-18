(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
(function() {
  'use strict';

  portfolioGlobalConfig.$inject=['$locationProvider'];
  module.exports = portfolioGlobalConfig;

  // require('jchptf').config(portfolioGlobalConfig);
  // angular.module('jchptf.app').config(portfolioGlobalConfig);

  altPortfolioGlobalConfig.$inject=['$locationProvider', '$urlRouterProvider'];

  function portfolioGlobalConfig($locationProvider) {
    $locationProvider.html5Mode(true);
  }

  function altPortfolioGlobalConfig($locationProvider, $urlRouterProvider) {
    $locationProvider.html5Mode(true);

    // Prevent $urlRouter from automatically intercepting URL changes;
    // this allows you to configure custom behavior in between
    // location changes and route synchronization:
    $urlRouterProvider.deferIntercept();
  }
}).call(window);

},{}],2:[function(require,module,exports){
(function() {
  'use strict';

  /**
   * @ngdoc overview
   * @name loopbackExampleFullStackApp
   * @description
   * # loopbackExampleFullStackApp
   *
   * Main module of the application, primarily tasked with branching between 
   * login page and the authenticated user's designated landing page.  Has no
   * routes of its own or pages of its own, but delegates to
   * 'jchptf.site.navigation' module, which in turn activates every feature
   * module to gather their routes.  Cross-cutting site service modules get
   * activated by the first feature encountered with a dependency on any of
   * their services.
   */
  angular.module('jchptf', [
    'ui.router', 'jchptf.context', 'jchptf.authenticate',
    'jchptf.site.notification', 'jchptf.site.navigation'
  ])
    .config(require('./app.configuration'))
    .run(require('./app.run'));

  module.exports = 'jchptf';
}).call(window);

},{"./app.configuration":1,"./app.run":3}],3:[function(require,module,exports){
(function() {
  'use strict';

  module.exports = portfolioAppLauncher;

  // require('jchptf').run(portfolioAppLauncher);
  // angular.module('jchptf').run(portfolioAppLauncher);

  portfolioAppLauncher.$inject=['IdentityContext', '$state'];
  altPortfolioAppLauncher.$inject=['$rootScope', '$urlRouter', 'IdentityContext'];

  function portfolioAppLauncher (IdentityContext, $state) {
    var latestEvent = IdentityContext.getAuthTokenStatus();

    var eventType = latestEvent.getEventType();
    if (eventType.isLoggedIn()) {
      $state.go('home', {reload: false});
    } else {
      // $state.go('loginForm.showForm', {reload: false});
      $state.go('loginForm', {reload: false});
    }
  }

  function altPortfolioAppLauncher($rootScope, $urlRouter, IdentityContext) {
    $rootScope.$on('$locationChangeSuccess', function(e) {
      // UserService is an example service for managing user state
      if (IdentityContext.isLoggedIn()) return;

      // Prevent $urlRouter's default handler from firing
      e.preventDefault();

      // TODO: This would be nifty, but can handleLogin() exist as a synchronous
      //       method call?  Possibly in an in-page modal?
      IdentityContext.handleLogin().then(function() {
        // Once the user has logged in, sync the current URL
        // to the router:
        $urlRouter.sync();
      });
    });

    // Configures $urlRouter's listener *after* your custom listener
    $urlRouter.listen();
  }
}).call(window);

},{}],4:[function(require,module,exports){
(function() {
    'use strict';

    angular.module('jchptf.authenticate').controller('LoginController', LoginController);

    LoginController.$inject =
        ['$state', 'IdentityContext', 'authTokenStatus', 'loginKind', 'LoginResultKind', 'AuthTokenEventKind'];

    function LoginController(
        IdentityContext, $state, authTokenStatus, loginKind, LoginResultKind, AuthTokenEventKind
    ) {
        // First check for a reply from the identity context service.  If that
        // confirms that the user is not logged in, check to see if they came
        // back with a potentially valid token that must be checked before
        // proceeding, routing the user back into the login workflow if not.
        //
        // Finally, all other states are specific known cases where this page
        // form is the right place to be.  Those states only differ by the
        // kind of feedback the user is given.

        var tokenEventType = authTokenStatus.eventType;
        if (tokenEventType.isLoggedIn()) {
            $state.go('xw', {reload: false});
        } else if (tokenEventType !== AuthTokenEventKind.NO_TOKEN_AVAILABLE) {
            $state.go('^.attemptFailed', {reload: false});
        } else {
            // The remaining options stay on the login form and only differ
            // in terms of the initial feedback state of the form:
            //
            // ** please login
            //    -OR-
            // ** bad credentials, try again
            //    -OR-
            // ** server error, try again later
            //    -OR-
            // your session has expired, please login to continue
            futureWork();
        }

        // Form behavior itself is TBD!
        function futureWork() {
            return IdentityContext.getAuthTokenStatus() === AuthTokenEventKind.NEW_TOKEN_IS_VALID;
        }
    }
}).call(window);


},{}],5:[function(require,module,exports){
(function() {
    'use strict';

    /**
     * @ngdoc overview
     * @name jchptf.authenticate
     *
     * @description
     * Portfolio application's authentication module.
     */
    angular.module('jchptf.authenticate', ['ng']);
}).call(window);

},{}],6:[function(require,module,exports){
(function() {
    'use strict';

    /**
     * @ngdoc overview
     * @name jchptf.authenticate
     *
     * @description
     * Routing component of the portfolio application's authentication module.
     */
    angular.module('jchptf.authenticate').config(authenticationRoutes);

    authenticationRoutes.$inject=['$stateProvider'];

    function authenticationRoutes ($stateProvider) {
        $stateProvider.state(
            'login',
            {
                url: '/login/showForm',
                templateUrl: '/views/authentication/loginForm.view.html',
                controller: 'LoginController',
                controllerAs: 'login',
                resolve: {
                    appContextStatus: null
                },
                abstract: false,
                authenticate: false
            }
        );
    }

    checkAppContext.$inject = ['IdentityContext'];

    function checkAppContext(IdentityContext) {
        return IdentityContext.verifyAuthTokenStatus();
    }
    /*
            $stateProvider
                .state('login', {
                    templateUrl: '/views/jchptf/authenticate/loginForm.html',
                    controller: 'LoginCtrl',
                    controllerAs: 'login',
                    abstract: true,
                    authenticate: false
                }).state('login.showForm', {
                    url: '/login/showForm',
                    resolve: {
                        appContextStatus: null,
                        loginKind: getRoutedToLoginKind()
                    },
                    abstract: false,
                    authenticate: false
                }).state('login.onReturn', {
                    url: '/login/onFail',
                    resolve: {
                        appContextStatus: checkAppContext()
                        loginKind: getWithAuthTokenLoginKind()
                    },
                    abstract: false,
                    authenticate: false
                }.state('login.attemptFailed', {
                    url: '/login/attemptFailed',
                    resolve: {
                        appContextStatus: null,
                        loginKind: getBadCredentialsLoginKind()
                    },
                    abstract: false,
                    authenticate: false
               }
            );
        }

        getLoginRequestedLoginKind.$inject = ['LOGIN_KIND_ENUM'];
        getWithAuthTokenLoginKind.$inject = ['LOGIN_KIND_ENUM'];
        getBadCredentialsLoginKind.$inject = ['LOGIN_KIND_ENUM'];

        function getLoginRequestedLoginKind(LOGIN_KIND_ENUM) {
            return LOGIN_KIND_ENUM.LOGIN_REQUESTED;
        }

        function getBadCredentialsLoginKind(LOGIN_KIND_ENUM) {
            return LOGIN_KIND_ENUM.BAD_CREDENTIALS;
        }

        function getWithAuthTokenLoginKind(LOGIN_KIND_ENUM) {
            return LOGIN_KIND_ENUM.WITH_AUTH_TOKEN;
        }

        function getInternalErrorLoginKind(LOGIN_KIND_ENUM) {
            return LOGIN_KIND_ENUM.INTERNAL_ERROR;
        }

        function getInvalidTokenLoginKind(LOGIN_KIND_ENUM) {
            return LOGIN_KIND_ENUM.TOKEN_NOT_VALID;
        }

        function getExpiredTokenLoginKind(LOGIN_KIND_ENUM) {
            return LOGIN_KIND_ENUM.TOKEN_EXPIRED;
    */
}).call(window);

},{}],7:[function(require,module,exports){
(function () {
  'use strict';

  /**
   * @ngdoc jchpft.directive
   * @description Directive for placing a modal on any page supporting a
   * workflow that can involve a significant time investment.  The form
   * it embedds supports two modes of operation.
   *
   * 1)  If a user's session token is in danger of timing out, the modal
   *     asks the user to click an Ok button and allow it to refresh the
   *     session.
   * 2)  IF the session has already expired, the modal instead prompts
   *     the user to enter credentials, allowing for an authentication
   *     attempt to take place without breaking away from the current
   *     page's invested state.
   *
   * The modal's initial state is always 'AUTHENTICATED', so please only
   * apply this directive on pages whose ui.router state descriptor
   * has 'authenticated=true'.
   *
   * Also note that it is not necessary to use this directive on every
   * authenticated page.  Its only needed if there is a significant cost
   * to redirecting a user to the login page in terms of the time invested
   * in an interrupted workflow.
   */
  angular.module('jchptf.authenticate')
    .directive('pftLoginModal', ptfLoginModalDescriptor);

  PtfLoginModalController.$inject = ['$scope', '$log', 'IdentityContext', 'AuthTokenEventKind'];

  function ptfLoginModalDescriptor() {
    return {
      restrict: 'E',
      scope: {
        // These handlers are intended to be optional.  Most likely
        // complete overkill.
        'onModalActive': '?&',
        'onModalInactive': '?&'
      },
      templateUrl: 'views/jchptf/authenticate/loginModal.html',
      controller: PtfLoginModalController
    };
  }

  function PtfLoginModalController($scope, $log, IdentityContext, AuthTokenEventKind) {
    var ctrl = this;

    // The identity service uses a promise to fire this as a
    // then handler when a suitable state change occurs.
    //
    // The service should fire call this handler once
    // immediately on registration to inform on present state.
    ctrl.state = AuthTokenEventKind.TOKEN_LOGGED_OUT;

    function onTokenEvent(tokenEvent) {
      if (tokenEvent && tokenEvent.eventType) {
        ctrl.state = tokenEvent.eventType;
      } else {
        $log('Unexpected event message from identity context service:', tokenEvent);
      }

      // TODO: tokenEvent.cancelHandling( ) ?
    }

    IdentityContext.addTokenEventListener(onTokenEvent);

    $scope.$on('$destroy', function onDestroy() {
      // Identity provider generates a new promise and
      // re-registers surviving observers, if any.
      IdentityContext.dropTokenEventListener(onTokenEvent);
    });
  }
}).call(window);

},{}],8:[function(require,module,exports){
(function() {
    'use strict';

    /**
     * @ngdoc overview
     * @name jchptf.context
     * @description Context module that is responsible for tracking global state, such as current logged in user.
     */
    angular.module('jchptf.context',
      ['ui.router']);
}).call(window);

},{}],9:[function(require,module,exports){
(function () {
  'use strict';

  /**
   * @ngdoc overview
   * @name jchptf.crosswords
   * @description
   *
   * Root module for the "Crosswords" feature set.
   */
  angular.module(
    'jchptf.crosswords',
    [
      'ui.router', 'jchptf.context', 'jchptf.authenticate', 'jchptf.site.notification',
      'jchptf.crosswords.tickets', 'jchptf.crosswords.results'
    ]
  );
}).call(window);

},{}],10:[function(require,module,exports){
(function() {
  'use strict';

  angular.module('jchptf.crosswords').config(crosswordRoutes);

  crosswordRoutes.$inject=['$stateProvider'];

  // TODO: Make sure IdentityContext is wired in to the authenticate check
  // TODO: Add the project query to repository service.
  function crosswordRoutes ($stateProvider) {
    $stateProvider.state(
      'crosswords', {
        templateUrl: '/views/jchptf/crosswords/browseRepository.view.html',
        controller: 'CrosswordsCtrl',
        controllerAs: 'crosswords',
        resolve: { },
        abstract: false,
        authenticate: true
      }
    );
  }
}).call(window);

},{}],11:[function(require,module,exports){
(function() {
    'use strict';

    /**
     * @ngdoc overview
     * @name jchpft.xw.solution
     * @description
     * An module encapsulating the artifact that provides an experience
     * supporting the collection of resolution data from a crossword puzzle
     * ticket previously published from the definition workflow.
     */
    angular.module(
      'jchptf.crosswords.results',
      [
        'ui.router', 'jchptf.context', 'jchptf.authenticate',
        'jchptf.site.notification','jchptf.crosswords.tickets'
      ]
    );
}).call(window);

},{}],12:[function(require,module,exports){
(function() {
  'use strict';

  /**
   * @ngdoc overview
   * @name jchptf.crosswords.tickets
   * @description
   * A module encapsulating the artifact that provides an experience
   * supporting the collection of at-face ticket information against which
   * a subsequent result reporting artifact will be created to collect the
   * hidden information that yields the ticket's prize value.
   */
  angular.module(
    'jchptf.crosswords.tickets',
    ['ui.router', 'jchptf.context', 'jchptf.authenticate', 'jchptf.site.notification']
  );
}).call(window);

},{}],13:[function(require,module,exports){
(function () {
  'use strict';

  /**
   * @ngdoc overview
   * @name jchptf.site.navigation
   * @description
   *
   * TODO
   */
  angular.module(
    'jchptf.site.navigation',
    [
      'ui.router', 'jchptf.context', 'jchptf.authenticate', 'jchptf.site.notification', 'jchptf.crosswords'
    ]
  );
}).call(window);

},{}],14:[function(require,module,exports){
(function() {
    'use strict';

    /**
     * @ngdoc overview
     * @name jchptf.site.notification
     * @description
     *
     * A directive for placing an area for asynchronous application messages
     * at a suitable location in a view and a service for accepting them
     * from places where events trigger.
     */
    angular.module('jchptf.site.notification', ['ng', 'ui.bootstrap']);
}).call(window);

},{}],15:[function(require,module,exports){
(function() {
  'use strict';

  angular.module(
    'jchptf.tools.iconPanel',
    ['ng', 'jchptf.tools.keyboard']
  );
}).call(window);

},{}],16:[function(require,module,exports){
(function () {
  'use strict';

  angular.module('jchptf.tools.keyboard').service(
    'keypressHelper', ['$parse', function keypress($parse) {
      var keysByCode = {
        8: 'backspace',
        9: 'tab',
        13: 'enter',
        27: 'esc',
        32: 'space',
        33: 'pageup',
        34: 'pagedown',
        35: 'end',
        36: 'home',
        37: 'left',
        38: 'up',
        39: 'right',
        40: 'down',
        45: 'insert',
        46: 'delete'
      };

      return function (scope, params) {
        var combinations = [];

        // Prepare combinations for simple checking
        angular.forEach(params, function (v, k) {
          var combination, expression;
          expression = $parse(v);

          angular.forEach(k.split(' '), function (variation) {
            combination = {
              expression: expression,
              keys: {}
            };
            angular.forEach(variation.split('-'), function (value) {
              combination.keys[value] = true;
            });
            combinations.push(combination);
          });
        });

        // Check only matching of pressed keys one of the conditions
        return function (event) {
          // No need to do that inside the cycle
          var metaPressed = !!(event.metaKey && !event.ctrlKey);
          var altPressed = !!event.altKey;
          var ctrlPressed = !!event.ctrlKey;
          var shiftPressed = !!event.shiftKey;
          var keyCode = event.keyCode;

          // Do NOT normalize key codes -- a-z are 97 to 122, A-Z are 65 to 90
          // ** keydown/keyup may bind a trigger to 'A'.  Caps-lock does not matter because browser
          //    normalizes these events as upper case.  (TODO: Verify uniform behavior)
          // ** keypress may bind a trigger to 'a', 'A', 'shift-A', or 'shift-a'  Caps-lock matters.
          //    shift-A fires when caps-lock is off, shift-a fires when it isn't.  In general,
          //    using shift-<alpha> is confusing and should be discouraged--just use 'a' or 'A'.
          // if (mode === 'keypress' && shiftPressed && keyCode >= 97 && keyCode <= 122) {
          //   keyCode = keyCode - 32;
          // }

          // Iterate over prepared combinations
          angular.forEach(combinations, function (combination) {
            var mainKeyPressed =
              combination.keys[keysByCode[keyCode]] ||
              combination.keys[String.fromCharCode(keyCode)];
            var metaRequired = !!combination.keys.meta;
            var altRequired = !!combination.keys.alt;
            var ctrlRequired = !!combination.keys.ctrl;
            var shiftRequired = !!combination.keys.shift;

            if (
              mainKeyPressed &&
              ( metaRequired === metaPressed ) &&
              ( altRequired === altPressed ) &&
              ( ctrlRequired === ctrlPressed ) &&
              ( shiftRequired === shiftPressed )
            ) {
              scope.$apply(function () {
                combination.expression(scope, {'$event': event});
              });
            }
          });
        };
      };
    }]
  );
}).call(window);

},{}],17:[function(require,module,exports){
(function() {
  'use strict';

  angular.module('jchptf.tools.keyboard', []);
}).call(window);

},{}],18:[function(require,module,exports){
(function() {
  'use strict';

  angular.module('jchptf.tools.workspace', ['ng', 'LocalForageModule']);
}).call(window);

},{}]},{},[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18]);
