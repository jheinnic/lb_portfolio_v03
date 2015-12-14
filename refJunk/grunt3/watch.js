'use strict';

module.exports = function watch(/*grunt, options*/) {
  // var getAvailPort = require('./utils/getAvailPort');
  // var port = getAvailPort(7777);

  return {
    bower: {
      // TODO: This cannot yet handle a change to the app directory in bower.json on appConfig!!!
      files: ['bower.json', 'bowerrc'],
      tasks: ['clean:vendor', 'bower', 'copy:vendor', 'wiredep:dev', 'reload-assets'],
      options: { reload: true }
    },
    grunt: {
      files: ['Gruntfile.js', 'grunt/**/*.{js,coffee}', 'client/build.js'],
      tasks: ['newer:jshint:build', 'newer:coffeelint:build', 'clean:build', 'dev-build', 'reload-assets'],
      options: { reload: true }
    },

    clientScripts: {
      files: [
        '<%= appConfig.app %>/{jchptf,boot,models}/**/*.{js,coffee}',
        '<%= appConfig.app %>/{datasources,model-config}*',
        'common/**/*.{js,coffee}',
        '!common/test/**/*.{js,coffee}'
      ],
      tasks: [
        'newer:jshint:client-source', 'newer:coffeelint:client-source',
        'newer:jshint:common-source', 'newer:coffeelint:common-source',
        'bundle-client', 'reload-assets'
      ]
    },
    serverScripts: {
      // TODO: Unlike clientScript restart, it is not necessary to refresh the browser for server-centric changes.
      files: ['{common,server}/**/*.{js,coffee}', '!{common,server}/test/**/*.{js,coffee}'],
      tasks: [
        'newer:jshint:server-source', 'newer:coffeelint:server-source',
        'newer:jshint:common-source', 'newer:coffeelint:common-source',
        'restart-server'
      ]
    },
    testScripts: {
      // TODO: Confirm the role of watch during test builds!
      files: ['{client,common,server}/test/**/*.{js,coffee}'],
      tasks: ['newer:jshint:test', 'newer:coffeelint:test', 'karma']
    },

    indexJade: {
      files: ['<%= appConfig.app %>/index.jade'],
      tasks: ['jade:dev', 'wiredep:dev', 'htmlbuild:dev', 'reload-assets'],
    },
    indexHtml: {
      files: ['<%= appConfig.app %>/index.html'],
      tasks: ['newer:copy:dev', 'wiredep:dev', 'htmlbuild:dev', 'reload-assets'],
    },
    templatesJade: {
      files: ['<%= appConfig.app %>/**/*.jade', '!<%= appConfig.app/index.jade'],
      tasks: ['jade:templates', 'reload-assets'],
    },
    templatesHtml: {
      files: ['<%= appConfig.app %>/**/*.html', '!<%= appConfig.app/index.html'],
      tasks: ['newer:copy:dev', 'reload-assets'],
    },

    styles: {
      files: ['<%= appConfig.app %>/**/*.{css,less}'],
      tasks: ['newer:copy:dev', 'newer:less', 'autoprefixer', 'htmlbuild:dev', 'reload-assets']
    },
    fonts: {
      // NOTE: All less sheets are re-parsed since we have no way of using file newness to test which may reference
      //       affected fonts.  Otherwise we could handle fonts and style sheets in a single common task.
      // NOTE: We only invoke autoprefixer and htmlbuild only because we have to propagate result of having
      //       called less to account for font references.
      files: ['<%= appConfig.app %>/**/*.{svg,eot,ttf,woff,woff2}'],
      tasks: ['newer:copy:dev', 'less', 'autoprefixer', 'htmlbuild:dev', 'reload-assets']
    },
    images: {
      files: ['<%= appConfig.app %>/**/*.{bmp,png,jpg,jpeg,gif,webp,svg}'],
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
      files: ['build/.rebooted'],
      options: { livereload: '<%= connect.options.livereload %>' }
    }
  };
};

// var _files = ['src/**/*.js', 'test/unit/**.js', 'test/unit/**.coffee', 'test/e2e/**.js'];
