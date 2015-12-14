'use strict';

// TODO: Performing htmlbuild before wiredep would eliminate the need to copy html files here, right?
module.exports = function copy(/*grunt, options*/) {
  var devAssetsPattern = '**/*.{html,bmp,jpg,jpeg,gif,png,webp,svg,eot,ttf,woff,woff2}';

  return {
    dev: {
      files: [
        { '<%= appConfig.dev %>/client/lr_init.js': 'client/lr_init.js' },
        {
          expand: true,
          dot: true,
          cwd: '<%= appConfig.app %>',
          src: [devAssetsPattern, '.htaccess', '*.{ico,png,txt}'],
          dest: '<%= appConfig.dev %>/client'
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
      files: {
        expand: true,
        dot: true,
        cwd: '<%= appConfig.app %>',
        src: ['**/*.{webp,eot,ttf,woff,woff2}', 'index.html', '.htaccess', '*.{ico,txt}'],
        // '**/*.{html,css,webp,eot,ttf,woff,woff2}', '!index.html', '.htaccess', '*.{ico,txt}'
        dest: '<%= appConfig.dist %>/client'
      }
    }
  };
};

