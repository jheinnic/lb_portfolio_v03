'use strict';

// TODO: Performing htmlbuild before wiredep would eliminate the need to copy html files here, right?
// TODO: There is one more recurring file suffix for font graphics that I'm missing below!
module.exports = function copy(grunt, options) {
  var appConfig = options.appConfig;
  var devAssetsPattern = '**/*.@(css|bmp|jpg|jpeg|gif|png|webp|svg|eot|ttf|woff|woff2)';

  return {
    vendor: {
      files: [
        {
          dot: true,
          expand: true,
          cwd: appConfig.vendor,
          src: '**/*',
          dest: appConfig.dev.client + '/vendor'
        }
      ]
    },
    nodeModules: {
      files: [
        {
          // TODO: This is what the slc deploy commands are for!!
          dot: true,
          expand: true,
          src: ['node_modules/**/*'],
          dest: appConfig.dist.root
        }
      ]
    },
    dev: {
      files: [
        {
          // NOTE: Jade trumps static HTML when names collide.  By copying
          //       static files before resolving Jade templates, Jade will
          //       win name conflicts as designed.
          dot: true,
          expand: true,
          cwd: appConfig.source.client,
          src: [
            appConfig.app + '/.htaccess', appConfig.app + '/*.@(png|txt|ico)',
            appConfig.app + '/' + devAssetsPattern, '{,models/}*.json', 'lr_init.js'
          ],
          dest: appConfig.dev.client
        }, {
          dot: true,
          expand: true,
          cwd: appConfig.source.client,
          src: [appConfig.app + '/**/*.html', 'index.html'],
          dest: appConfig.temp.client
        }, {
          dot: true,
          expand: true,
          cwd: appConfig.source.common,
          src: ['**/*'],
          dest: appConfig.dev.common
        }
      ]
    },
    dist: {
      // TODO: Build-derived images?  Images currently re-copied from source.
      //
      // NOTE: Distribution starts with the source index.html, but all other
      //       files come from the dev area.  Rather than using partial
      //       artifacts from dev, index.html is rebuilt from source in
      //       order to support different plugin behavior branched by
      //       different options to htmlbuild and jade plugins, restricted
      //       to the activation of <!-- htmlbuild:ignore --> directive
      //       (htmlbuild) and 'isDevelopment: false' data attribute (jade).
      files: [
        {
          dot: true,
          expand: true,
          src: ['global-config.js', 'package.json', 'bower.json', '.npmrc', '.bowerrc', 'README.md'],
          dest: appConfig.dist.root
        }, {
          // TODO: Change CSS and HTML builds to first populate an area in temp, so Jade's dev/dist
          //       stages can overwrite the files there for dist without overwriting what was previously
          //       copied from there for dev.  Then htmlbuild & usemin can run against overwritten html
          //       without needing to copy unnecessary CSS files into dist or run less/autoprefix twice.
          dot: true,
          expand: true,
          cwd: appConfig.source.client,
          src: [
            appConfig.app + '/.htaccess', appConfig.app + '/*.@(ico|txt)', appConfig.app + '/**/*.@(webp|eot|ttf|woff|woff2}',
            '{,models/}*.json'
          ],
          dest: appConfig.dist.client
        }, {
          dot: true,
          expand: true,
          cwd: appConfig.dev.client,
          src: ['app.js', appConfig.app + '**/_*.css', appConfig.app + '**/*.html'],
          dest: appConfig.dist.client
        }, {
          dot: true,
          expand: true,
          cwd: appConfig.source.common,
          src: ['**/*'],
          dest: appConfig.dist.common
        }, {
          dot: true,
          expand: true,
          cwd: appConfig.source.server,
          src: ['**/*'],
          dest: appConfig.dist.server
        }
      ]
    }
  };
};

