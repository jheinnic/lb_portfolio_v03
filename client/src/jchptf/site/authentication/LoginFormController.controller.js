(function() {
  'use strict';

  module.exports = LoginFormController;
  LoginFormController.$inject = [ '$scope', '$element', 'title', 'close', LoginFormController ];

  function LoginFormController($scope, $element, title, close) {
    this.vm = {
      creds: {
        username: null,
        password: null
      },
      title: title
    };

    // This close function doesn't need to use jQuery or bootstrap, because
    // the button has the 'data-dismiss' attribute.
    $scope.close = function onClose() {
      var result = {
        status: 'OK',
        key: $scope.creds.username,
        userId: $scope.creds.username,
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
      close({status: 'cancel', key: null, userId: null}, 500);
    };

    $scope.register = function onGoRegister() {
      // alert('TBD: Registration not yet implemented!');
      var result = {
        status: 'register',
        key: null,
        userId: null
      };

      // Now call close, returning control to the caller.
      // Give 500ms for bootstrap to animate
      close(result, 500);
    };

    $scope.register = function onGoForgotUsername() {
      // alert('TBD: Registration not yet implemented!');
      var result = {
        status: 'forgotUsername',
        key: null,
        userId: null
      };

      // Now call close, returning control to the caller.
      // Give 500ms for bootstrap to animate
      close(result, 500);
    };

    $scope.register = function onGoForgotPassword() {
      // alert('TBD: Registration not yet implemented!');
      var result = {
        status: 'forgotPassword',
        key: null,
        userId: $scope.creds.username
      };

      // Now call close, returning control to the caller.
      // Give 500ms for bootstrap to animate
      close(result, 500);
    };
  }
}).call(window);

