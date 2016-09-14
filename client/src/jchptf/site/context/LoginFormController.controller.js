(function() {
  'use strict';

  module.exports = LoginFormController;
  LoginFormController.$inject = [ '$scope', '$element', 'title', 'close', LoginFormController ];

  function LoginFormController($scope, $element, title, close) {
    $scope.creds = {
      username: null,
      password: null
    };
    $scope.title = title;

    // This close function doesn't need to use jQuery or bootstrap, because
    // the button has the 'data-dismiss' attribute.
    $scope.close = function onClose() {
      var result = {
        status: 'OK',
        key: $scope.creds.username,
        extra: $scope.creds.password
      };
      close(result, 500); // close, but give 500ms for bootstrap to animate
    };

    // This cancel function must use the bootstrap, 'modal' function because
    // the doesn't have the 'data-dismiss' attribute.
    $scope.cancel = function onCancel() {
      //  Manually hide the modal.
      $element.modal('hide');

      // Now call close, returning control to the caller.
      // Give 500ms for bootstrap to animate
      close({status: 'cancel'}, 500);
    };

    //  This cancel function must use the bootstrap, 'modal' function because
    //  the doesn't have the 'data-dismiss' attribute.
    $scope.register = function onRegister() {
      //  Manually hide the modal.
      $element.modal('hide');

      // alert('TBD: Registration not yet implemented!');

      // Now call close, returning control to the caller.
      // Give 500ms for bootstrap to animate
      close({status: 'register'}, 500);
    };
  }
}).call(window);

