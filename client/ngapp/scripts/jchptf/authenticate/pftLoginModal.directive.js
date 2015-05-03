(function(angular, jchutils) {
    "use strict";

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
    angular.module('jchpft.authenitcate')
        .directive('pftLoginModal', pftLoginModalDescriptor );

    pftLoginModalDescriptor.$inject = ['IdentityContext']

    function pftLoginModalDescriptor(IdentityContext) {
        return {
            restrict: 'E',
            scope: {
		// These handlers are intended to be optional.  Most likely
		// complete overkill.
                'onModalActive': '?&',
		'onModalInactive': '?&'
	    }
	    templateUrl: 'views/jchpft/authenticate/loginModal.html',
            link: function($scope, $elem, $attr) {
		// The identity service uses a promise to fire this as a
		// then handler when a suitable state change occurs.
		//
		// The service should fire call this handler once 
		// immediately on registration to inform on present state.
		$scope.loginModal.state = 'TOKEN_WAS_REFRESHED';
		var onTokenEvent = function onTokenEvent(tokenEvent) {
                    // TODO: Use an enum!
		    if (tokenEvent.getEventType() === 'NEW_TOKEN_IS_VALID') {
		        $scope.loginModal.state = 'NEW_TOKEN_IS_VALID'
		    } else if (tokenEvent.getEventType() === 'TOKEN_WAS_REFRESHED') {
		        $scope.loginModal.state = 'TOKEN_WAS_REFRESHED';
		    } else if(tokenEvent.getEventType() === 'TOKEN_MAY_EXPIRE') {
		        $scope.loginModal.state = 'TOKEN_MAY_EXPIRE';
	            } else if(tokenEvent.getEventType() === 'TOKEN_HAS_EXPIRED') {
		        $scope.loginModal.state = 'TOKEN_HAS_EXPIRED';
	            } else if(tokenEvent.getEventType() === 'TOKEN_WAS_REVOKED') {
		        $scope.loginModal.state = 'TOKEN_WAS_REVOKED';
	            } else if(tokenEvent.getEventType() === 'TOKEN_LOGGED_OUT') {
		        $scope.loginModal.state = 'TOKEN_LOGGED_OUT';
	            } else if(tokenEvent.getEventType() === 'INTERNAL_ERROR') {
		        $scope.loginModal.state = 'INTERNAL_ERROR';
	            } else {
                        $log('Unexpected event message from identity context service:', tokenEvent);
                    }

		    // TODO: tokenEvent.cancelHandling( ) ?
		}

		IdentityContext.addTokenEventListener(onTokenEvent);

		$scope.$on('$destroy', function onDestroy() {
	            // Identity provider generates a new promise and 
		    // re-registers surviving observers, if any.
                    IdentityContextService.dropTokenEventListener(onTokenEvent);
		});
            }
	};
    }
)(window.angular));
