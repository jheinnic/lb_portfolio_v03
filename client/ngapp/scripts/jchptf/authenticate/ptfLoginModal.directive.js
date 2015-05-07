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
      controller: pftLoginModalController
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
