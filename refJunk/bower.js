'use strict';

module.exports = function bower(grunt, options) {
  var appConfig = options.appConfig,
      path      = appConfig.path,
      cwd       = appConfig.cwd;

  console.log('Bower: ', new Date());

  return {
    // Update bower's download cache and also deploy to build area.
    install: {
      options: {
        install: true,
        copy: true,
        layout: 'byComponent',
        cleanTargetDir: true,
        bowerOptions: {
          production: false
        },
        targetDir: path.join(cwd, appConfig.dev.client, 'vendor')
      }
    },
    // Update bower's download cache, but do not deploy to build area.
    cache: {
      options: {
        install: true,
        // layout: 'byComponent',
        bowerOptions: {
          production: true
        }
      }
    },
    // Redeploy previously cached bower content to the build area.
    redeploy: {
      options: {
        copy: true,
        layout: 'byComponent',
        cleanTargetDir: true,
        targetDir: path.join(cwd, appConfig.dev.client, 'vendor')
      }
    }
  };
};
