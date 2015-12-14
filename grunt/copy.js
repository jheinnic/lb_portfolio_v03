'use strict';

// TODO: Performing htmlbuild before wiredep would eliminate the need to copy html files here, right?
// TODO: There is one more recurring file suffix for font graphics that I'm missing below!
module.exports = function copy(grunt, options) {
  var appConfig = options.appConfig;
  var devAssetsPattern =
    appConfig.app + '/**/*.{html,bmp,jpg,jpeg,gif,png,webp,svg,eot,ttf,woff,woff2}';

  return {
    vendor: {
      files: [
        {
          expand: true,
          cwd: appConfig.vendor,
          src: '**/*',
          dest: appConfig.dev.client + '/vendor'
        }
      ]
    },
    dev: {
      files: [
        {
          // TODO: This is what the slc deploy commands are for!!
          expand: true,
          dot: true,
          src: ['global-config.js', 'package.json', 'bower.json', 'node_modules/**/*', '.npmrc', '.bowerrc', 'README.md'],
          dest: appConfig.dev.root
        }, {
          expand: true,
          cwd: appConfig.source.client + '/..',
          src: 'lr_init.js',
          dest: appConfig.dev.client
        }, {
          expand: true,
          dot: true,
          cwd: appConfig.source.client,
          src: [
            devAssetsPattern, appConfig.app + '/.htaccess', appConfig.app + '/*.{ico,png,txt}'
          ],
          dest: appConfig.dev.client
        }, {
          expand: true,
          dot: true,
          cwd: appConfig.source.common,
          src: '**/*',
          dest: appConfig.dev.common
        }, {
          expand: true,
          dot: true,
          cwd: appConfig.source.server,
          src: '**/*',
          dest: appConfig.dev.server
        }, {
          expand: true,
          dot: true,
          cwd: appConfig.vendor,
          src: '**/*',
          dest: appConfig.devroo + '/vendor'
        }, {
          expand: true,
          dot: true,
          cwd: appConfig.vendor,
          src: '**/*',
          dest: appConfig.dev.client + '/vendor'
        }
      ]
    },
    dist: {
      // TODO: Build-derived images?  Images currently re-copied from source.
      //
      // NOTE: Distribution starts with the source index.html, but all other files come from the dev area.
      //   Rather than using partial artifact from dev, index.html is rebuilt from source in order to support
      //   different plugin behavior branched by different options to htmlbuild and jade plugins, restricted
      //   to the activation of <!-- htmlbuild:ignore --> directive (htmlbuild) and 'isDevelopment: false'
      //   data attribute (jade).
      files: [
        {
          expand: true,
          src: ['global-config.js', 'package.json', 'bower.json', 'node_modules/**/*', '.npmrc', '.bowerrc', 'README.md'],
          dest: appConfig.dist.server
        },
        {
          expand: true,
          dot: true,
          cwd: appConfig.source.client,
          src: [
            appConfig.app + '/**/*.{webp,eot,ttf,woff,woff2}', appConfig.app + '/{index.html,.htaccess,*.{ico,txt}}'
          ],
          dest: appConfig.dist.client
        }, {
          expand: true,
          dot: true,
          cwd: appConfig.source.server,
          src: '**/*',
          dest: appConfig.dist.server
        }
      ]
    }
  };
};

