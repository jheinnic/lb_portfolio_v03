(function () {
  'use strict';

  module.exports = function watch(grunt, options) {
    var appConfig = options.appConfig;
    var appModRoot = appConfig.source.client + '/' + appConfig.app;

    return {
      bower: {
        // TODO: This cannot yet handle a change to the app directory in bower.json on appConfig!!!
        files: ['bower.json', 'bowerrc'],
        tasks: ['clean:vendor', 'shell:bower-update', 'copy:vendor', 'wiredep:build', 'fixIndexHtml', 'htmlbuild:dev'],
        options: {reload: true}
      },
      nodeModules: {
        files: ['package.json', '.npmrc'],
        tasks: ['shell:npm-install', 'shell:npm-prune', 'bundleClient'],
        options: {reload: true}
      },
      grunt: {
        files: ['Gruntfile.js', appConfig.source.build + '/**/*.{js,coffee}'],
        tasks: ['newer:jshint:build', 'newer:coffeelint:build', 'build-dev-fast'],
        options: {reload: true}
      },

      scripts: {
        files: [
          appConfig.source.client + '/**/*.{js,coffee,json}',
          appConfig.source.common + '/**/*.{js,coffee,json}',
          appConfig.source.server + '/**/*.{js,coffee,json}'
        ],
        tasks: ['newer:jshint:source', 'newer:coffeelint:source', 'bundleClient']
      },

      index: {
        files: [appConfig.source.client + '/index.@(html|jade)'],
        tasks: ['newer:copy:dev', 'newer:jade:build', 'wiredep:build', 'fixIndexHtml', 'htmlbuild:dev']
      },
      templates: {
        files: [appModRoot + '/**/*.{html,jade}'],
        tasks: ['newer:copy:dev', 'newer:jade:build']
      },

      css: {
        files: [appModRoot + '/**/[^_]*.css'],
        tasks: ['newer:copy:dev', 'newer:autoprefixer', 'htmlbuild:dev']
      },
      less: {
        files: [appModRoot + '/**/[^_]*.less'],
        tasks: ['newer:less', 'newer:autoprefixer', 'htmlbuild:dev']
      },
      _css: {
        files: [appModRoot + '/**/_*.{css,less}'],
        tasks: ['newer:copy:dev', 'newer:autoprefixer']
      },
      _less: {
        files: [appModRoot + '/**/_*.less'],
        tasks: ['newer:less', 'newer:autoprefixer']
      },
      images: {
        files: [appModRoot + '/**/*.{bmp,png,jpg,jpeg,gif,webp}'],
        tasks: ['newer:copy:dev']
      },
      fonts: {
        // NOTE: We invoke less without newer since we have no way of knowing which less files refer to which
        //       fonts.
        // NOTE: We invoke autoprefixer and htmlbuild without newer because we have to propagate result of having
        //       called less to account for font references.
        files: [appModRoot + '/**/*.{svg,eot,ttf,woff,woff2}'],
        tasks: ['newer:copy:dev', 'less', 'autoprefixer', 'htmlbuild:dev']
      },

      builtAssets: {
        files: [appConfig.dev.client + '/**/*'],
        tasks: ['reloadAssets']
      },

      nodemon: {
        // When nodemon recycles the server, it touches this marker, which in turn causes livereload to
        // signal need for refresh to browsers.
        // TODO: Reconcile the difference between connect (for unit tests?) and loopback server as origins
        //       for a liveReload signal.
        // TODO: Consider running all reload signals through a connect instance that only serves lr_init.js
        //       and the liveReload protocol in order to avoid including any liveReload code in the server
        //       that is only intended for use during development.
        files: [appConfig.temp.server + '/.rebooted'],
        options: {livereload: appConfig.ports.liveReload}
      }
    };
  };
}).call();
