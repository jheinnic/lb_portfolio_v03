(function (module) {
  'use strict';

  module.exports = function watch(grunt, options) {
    var appConfig = options.appConfig;
    // Acquire appConfig by requiring it if the options lacked a non-null object.
    // This triggers when watch is launched as a task from
    // grunt-contrib-concurrent
    // TODO: Beware the possibility of switching ports in process.env if this
    //       triggers...
    if ((appConfig === null) || (typeof appConfig !== 'object')) {
      appConfig = require('.,/utils/appConfig')(grunt);
      options.appConfig = appConfig;
    }

    return {
      bower: {
        // TODO: This cannot yet handle a change to the app directory in bower.json on appConfig!!!
        files: ['bower.json', 'bowerrc'],
        tasks: [
          'clean:vendor', 'shell:bower-update', 'copy:vendor', 'wiredep:build', 'fixIndexHtml', 'htmlbuild:dev', 'reloadAssets'
        ],
        options: {reload: true}
      },
      nodeModules: {
        files: ['package.json', '.npmrc'],
        tasks: ['shell:npm-install', 'shell:npm-prune', 'bundleClient:dev', 'reloadAssets'],
        options: {reload: true}
      },
      grunt: {
        files: ['Gruntfile.js', appConfig.source.build + '/**/*.@(js|coffee)'],
        tasks: ['newer:jshint:build', 'newer:coffeelint:build', 'make-dev-build', 'reloadAssets'],
        options: {reload: true}
      },

      scripts: {
        files: [
          appConfig.source.client + '/**/*.@(js|coffee|json)', appConfig.source.common + '/**/*.@(js|coffee|json)'
        ],
        tasks: ['newer:jshint:source', 'newer:coffeelint:source', 'bundleClient:dev', 'reloadAssets']
      },

      index: {
        files: [appConfig.source.client + '/index.@(html|jade)'],
        tasks: ['newer:copy:dev', 'newer:pug:build', 'wiredep:build', 'fixIndexHtml', 'htmlbuild:dev', 'reloadAssets']
      },
      templates: {
        files: [appConfig.source.app + '/**/*.@(html|jade)'],
        tasks: ['newer:copy:dev', 'newer:pug:build', 'reloadAssets']
      },

      css: {
        files: [appConfig.source.app + '/**/[^_]*.css'],
        tasks: ['newer:copy:dev', 'newer:autoprefixer', 'htmlbuild:dev', 'reloadAssets']
      },
      less: {
        files: [appConfig.source.app + '/**/[^_]*.less'],
        tasks: ['newer:less', 'newer:autoprefixer', 'htmlbuild:dev', 'reloadAssets']
      },
      _css: {
        files: [appConfig.source.app + '/**/_*.css'],
        tasks: ['newer:copy:dev', 'newer:autoprefixer', 'reloadAssets']
      },
      _less: {
        files: [appConfig.source.app + '/**/_*.less'],
        tasks: ['newer:less', 'newer:autoprefixer', 'reloadAssets']
      },
      images: {
        files: [appConfig.source.app + '/**/*.@(bmp|png|jpg|jpeg|gif|webp)'],
        tasks: ['newer:copy:dev', 'reloadAssets']
      },
      fonts: {
        // NOTE: We invoke less without newer since we have no way of knowing which less files refer to which
        //       fonts.
        // NOTE: We invoke autoprefixer and htmlbuild without newer because we have to propagate result of having
        //       called less to account for font references.
        files: [appConfig.source.app + '/**/*.@(svg|eot|ttf|woff|woff2)'],
        tasks: ['newer:copy:dev', 'less', 'autoprefixer', 'htmlbuild:dev', 'reloadAssets']
      },

      //builtAssets: {
      //  files: [
      //    appConfig.dev.client + '/**/*',
      //    '!' + appConfig.dev.client + '/vendor/**/*'
      //  ],
      //  tasks: ['reloadAssets']
      //},

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
}).call(this, module);
