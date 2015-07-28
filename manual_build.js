(function() {
  var path = require('path');

  var buildForEnv = 'development';
  var angularScriptsDir = path.join(__dirname, 'client', 'ngapp', 'scripts', 'jchptf');
  var commonComponentsDir = path.join(__dirname, 'common', 'components');
  var buildOutputDir = path.join(__dirname, '.tmp', 'scripts');

  function callbackHandler(err, result) {
    if (err) {
      console.error('Failed:', err);
    }
    else {
      console.log('Passed:', result);
    }
  }

  require('./build/build')(buildForEnv, angularScriptsDir, commonComponentsDir, buildOutputDir, callbackHandler);
}).call(this);
