(function () {
  'use strict';

  module.exports = function watch(grunt, options) {
    var appConfig = options.appConfig;
    var appModRoot = appConfig.source.client + '/' + appConfig.app;

    return {
      bower: {
        // TODO: This cannot yet handle a change to the app directory in bower.json on appConfig!!!
        files: ['bower.json', 'bowerrc'],
        tasks: ['clean:vendor', 'shell:bower-update', 'newer:copy:vendor', 'wiredep:dev', 'reload-assets'],
        options: {reload: true}
      },
      nodeModules: {
        files: ['package.json', '.npmrc'],
        tasks: ['shell:npm-install', 'bundle-client', 'reload-assets'],
        options: {reload: true}
      },
      grunt: {
        files: ['Gruntfile.js', appConfig.source.build + '/**/*.{js,coffee}'],
        tasks: ['newer:jshint:build', 'newer:coffeelint:build', 'build-dev-fast', 'reload-assets'],
        options: {reload: true}
      },

      scripts: {
        files: [
          appConfig.source.client + '/**/*.{js,coffee,json}',
          appConfig.source.common + '/**/*.{js,coffee,json}',
          appConfig.source.server + '/**/*.{js,coffee,json}'
        ],
        tasks: [
          'newer:jshint:common-source', 'newer:coffeelint:common-source', 'bundle-client', 'newer:copy:dev', 'reload-assets'
        ]
      },

      index: {
        files: [appConfig.source.client + '/index.{html,jade}'],
        tasks: ['newer:copy:dev', 'newer:jade:dev', 'wiredep:dev', 'htmlbuild:dev', 'reload-assets'],
      },
      templatesJade: {
        files: [appModRoot + '/**/*.{html,jade}'],
        tasks: ['newer:copy:dev', 'newer:jade:templates', 'reload-assets'],
      },

      styles: {
        files: [appModRoot + '/**/*.{css,less}'],
        tasks: ['newer:copy:dev', 'newer:less', 'newer:autoprefixer', 'htmlbuild:dev', 'reload-assets']
      },
      fonts: {
        // NOTE: All less sheets are re-parsed since we have no way of using file newness to test which may reference
        //       affected fonts.  Otherwise we could handle fonts and style sheets in a single common task.
        // NOTE: We only invoke autoprefixer and htmlbuild only because we have to propagate result of having
        //       called less to account for font references.
        files: [appModRoot + '/**/*.{svg,eot,ttf,woff,woff2}'],
        tasks: ['newer:copy:dev', 'newer:less', 'newer:autoprefixer', 'htmlbuild:dev', 'reload-assets']
      },
      images: {
        files: [appModRoot + '/**/*.{bmp,png,jpg,jpeg,gif,webp,svg}'],
        tasks: ['newer:copy:dev', 'reload-assets']
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
