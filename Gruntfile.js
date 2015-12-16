'use strict';

module.exports = function (grunt/*, options*/) {
  // Time how long tasks take. Can help when optimizing build times
  require('time-grunt')(grunt);

  var appConfig = require('./grunt/utils/appConfig')(grunt);
  var path = require('path');
  var _ = require('lodash');

  console.log(grunt.util.linefeed);

  require('./grunt/utils/customTasks')(grunt, appConfig);

  // Load grunt config from per-plugin files under 'grunt' subdirectory
  // console.log(Object.keys(options));
  require('load-grunt-config')(
    grunt,
    {
      init: true,
      configPath: path.join(appConfig.cwd, appConfig.source.build),
      data: {
        appConfig: appConfig
      },
      preMerge: _.extend,
      loadGruntTasks: {
        pattern: 'grunt-*',
        config: appConfig.packageJson,
        scope: 'devDependencies'
      }
    }
  );
};
