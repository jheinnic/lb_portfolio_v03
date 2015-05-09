// Generated on 2014-06-23 using generator-angular 0.9.1
'use strict';

var buildClientBundle = require('./client/lbclient/build');
var fs = require('fs');

// # Globbing
// We're using this to recursively match all sub-folders:
// 'test/spec/**/*.js'
// if you only need to match one level down, you may instead
// use this for performance reasons:
// 'test/spec/{,**/}*.js'

module.exports = function (grunt) {
  // For building filesystem paths by concatenation.
  var path = require('path');

  // Load grunt tasks automatically
  require('load-grunt-tasks')(grunt);

  // Time how long tasks take. Can help when optimizing build times
  require('time-grunt')(grunt);

  // Configurable paths for the application
  var bowerJson = require('./bower.json');
  var bowerRc;
  try {
      bowerRc = JSON.parse(fs.readFileSync('./.bowerrc'), 'utf-8');
  } catch(err) {
      // No .bowerrc.  Not a problem--it's optional!
  }
  var appConfig = {
      app: (bowerJson && bowerJson.appPath) ? bowerJson.appPath : path.join('client', 'ngapp'),
      vendor: (bowerRc && bowerRc.directory) ? bowerRc.directory : './bower_components',
      dist: 'client/dist',
      lbclient: 'client/lbclient',
      staging: '.tmp'
  };

  // console.log(appConfig);

  // Define the configuration for all the tasks
  grunt.initConfig({
    // Project settings
    yeoman: appConfig,

    // Watches files for changes and runs tasks based on the changed files
    watch: {
      nodemon: {
        files: ['.rebooted'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      bower: {
        // TODO: This cannot yet handle a change to the app directory in bower.json on yeoman!!!
        files: ['bower.json'],
        tasks: ['wiredep'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      coffee: {
        files: ['<%= yeoman.app %>/scripts/**/*.coffee'],
        tasks: ['coffeelint:dist', 'coffee:dist'],
        options: {
          livereload: '<%= connect.options.livereload %>',
        }
      },
      js: {
        // TODO: Can we skip the copy step?  Its needed for minification pipeline, but for development
        //       watch purposes, livereload should serve the true source files so debugging is done
        //       with the true source files.
        files: ['<%= yeoman.app %>/scripts/**/*.js'],
        tasks: ['newer:jshint:dist', 'newer:copy:scripts'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      coffeeTest: {
        files: ['<%= yeoman.app %>/test/spec/**/*.coffee'],
         tasks: ['coffeelint:test', 'coffee:test', 'karma']
      },
      jsTest: {
        // TODO: Can we skip the copy step?  Its needed for minification pipeline, but for debug
        //       purpose, the livereload watch should serve the true source files.
        files: ['<%= yeoman.app %>/test/spec/**/*.js'],
        // tasks: ['newer:copy:test', 'newer:jshint:test', 'karma']
        tasks: ['newer:jshint:test', 'newer:copy:staging', 'karma']
      },
      less: {
        files: [ '<%= yeoman.app %>/styles/**/*.less' ],
        // tasks: ['newer:copy:styles', 'newer:less', 'autoprefixer', 'useminPrepare', 'usemin', 'filerev']
        tasks: ['newer:less', 'autoprefixer'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      styles: {
        files: ['<%= yeoman.app %>/styles/**/*.css'],
        // tasks: ['newer:copy:styles', 'newer:less', 'autoprefixer', 'useminPrepare', 'usemin', 'filerev']
        tasks: ['newer:copy:styles', 'autoprefixer'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      // images: {
      //   files: ['<%= yeoman.app %>/images/**/*.webp'],
      //   // tasks: ['newer:copy:dist', 'imagemin', 'svgmin', 'userminPrepare', 'usemin', 'filerev']
      //   tasks: ['newer:copy:dist']
      // },
      fonts: {
        files: [
          '<%= yeoman.app %>/fonts/**/*',
          '<%= yeoman.app %>/styles/fonts/**/*'
        ],
        // tasks: ['newer:copy:dist', 'autoprefixer', 'useminPrepare', 'usemin', 'filerev']
        tasks: ['newer:copy:staging'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      index: {
        files: ['<%= yeoman.app %>/index.html'],
        tasks: ['newer:copy:staging', 'wiredep'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      gruntfile: {
        files: ['Gruntfile.js']
      },
      livereload: {
        files: [
          '<%= yeoman.app %>/images/**/*.{png,jpg,jpeg,gif,webp,svg}',
          // TODO: Confirm that node doesn't require a restart to serve modified HTML due to caching.
          '<%= yeoman.app %>/views/**/*.html',
          //    '<%= yeoman.app %>/{fonts,styles/fonts}/**/*',
          // '<%= yeoman.staging %>/styles/**/*.css',
          // TODO: Consider refining server file watch dependencies since they also cause a need to restart.
          '{common,server}/**/*'
        ],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      lbclient: {
        files: [
          '<%= yeoman.lbclient %>/models/*',
          '<%= yeoman.lbclient %>/app*',
          '<%= yeoman.lbclient %>/datasources*',
          '<%= yeoman.lbclient %>/models*',
          '<%= yeoman.lbclient %>/build.js'
        ],
        tasks: ['build-lbclient'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      config: {
        files: ['<%= yeoman.app %>/config/*.json'],
        tasks: ['build-config'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      }
    },

    // The actual grunt server settings
    connect: {
      options: {
        port: 3000,
        // Change this to '0.0.0.0' to access the server from outside.
        hostname: 'localhost',
        livereload: 35729
      },
      test: {
        options: {
          port: 9001,
          middleware: function (connect) {
            return [
              connect.static('<%= yeoman.staging %>'),
              // connect.static('test'),
              connect().use(
                '/vendor', '<%= yeoman.vendor %>'
              ),
              connect().use(
                '/lbclient', connect.static( '<%= yeoman.lbclient %>' )
              ),
              connect.use(
                '/views', connect.static( '<%= yeoman.app %>/views' )
              ),
              connect.use(
                '/images', connect.static( '<%= yeoman.app %>/images' )
              ),
              connect().use(
                '/config',
                // function() {
                  connect.static('<%= yeoman.staging %>/config')
                  // connect.static('<%= yeoman.app %>/config');
                // }
              ),
              connect.use(
                '/scripts',
                // function() {
                //   connect.static( '<%= yeoman.app %>/scripts' );
                  connect.static( '<%= yeoman.staging %>/scripts' )
                // }
              ),
              connect.use(
                '/styles',
                // function() {
                  // connect.static( '<%= yeoman.app %>/styles' );
                  connect.static( '<%= yeoman.staging %>/styles' )
                // }
              ),
              connect.use(
                '/fonts',
                connect.static( '<%= yeoman.staging %>/fonts' )
              ),
              connect.use(
                '/styles/fonts',
                connect.static( '<%= yeoman.staging %>/styles/fonts' )
              ),
              // To support refresh and bookmarks in Angular HTML5 mode, final
              // URL re-write rule must redirect all to index.html.  If all
              // Angular routes are known a priori, a preferable approach is to
              // selectively redirect only those routes to index.html, allowing
              // errors to be generated for genuinely bad routes.
              connect.use(
                '/',
                connect.static( '<%= yeoman.staging %>/index.html' )
              )
            ];
          }
        }
      }
    },

    // Empties folders to start fresh
    clean: {
      all: {
        files: [{
          dot: true,
          src: [
            '<%= yeoman.staging %>',
            '<%= yeoman.dist %>/**/*',
            '!<%= yeoman.dist %>/.git*',
            '<%= yeoman.lbclient %>/browser.bundle.js',
            '<%= yeoman.app %>/config/bundle.js'
          ]
        }]
      },
      dist: {
        files: [{
          dot: true,
          src: [
            '<%= yeoman.staging %>',
            '<%= yeoman.dist %>/**/*',
            '!<%= yeoman.dist %>/.git*'
          ]
        }]
      },
      server: '<%= yeoman.staging %>',
      lbclient: '<%= yeoman.lbclient %>/browser.bundle.js',
      config: '<%= yeoman.app %>/config/bundle.js'
    },

    // Compiles CoffeeScript to JavaScript.
    coffee: {
      options: {
        sourceMap: true
      },
      dist: {
        ext: '.js',
        extDot: 'last',
        expand: true,
        cwd: '<%= yeoman.staging %>/scripts',
        src: ['**/*.coffee'],
        dest: '<%= yeoman.staging %>/scripts/'
      },
      test: {
        ext: '.js',
        extDot: 'last',
        expand: true,
        cwd: '<%= yeoman.app %>/test/spec',
        src: ['**/*.coffee'],
        dest: '<%= yeoman.staging %>/test/spec/'
      }
    },

    // Make sure code styles are up to par and there are no obvious mistakes
    jshint: {
      options: {
        jshintrc: '.jshintrc',
        reporter: require('jshint-stylish')
      },
      dist: {
        src: [
          'Gruntfile.js',
          '<%= yeoman.app %>/scripts/**/*.js'
        ]
      },
      test: {
        options: {
          jshintrc: '<%= yeoman.app %>/test/.jshintrc',
          reporter: require('jshint-stylish')
        },
        src: [
            '<%= yeoman.app %>/test/spec/**/*.js'
        ]
      }
    },

    coffeelint: {
      dist: {
        src: ['<%= yeoman.app %>/scripts/**/*.coffee']
      },
      test: {
        src: ['<%= yeoman.app %>/test/spec/**/*.coffee']
      },
      options: {
        configFile: 'coffeelint.json'
      }
    },

    less: {
      options: {
        paths: [
          '<%= yeoman.app %>/styles',
          '<%= yeoman.vendor %>/bootstrap/less'
        ]
      },
      dist: {
        ext: '.css',
        extDot: 'last',
        expand: true,
        cwd: '<%= yeoman.app %>/styles',
        src: ['**/*.less'],
        dest: '<%= yeoman.staging %>/less/'
      }
    },

    // Add vendor prefixed styles
    autoprefixer: {
      options: {
        browsers: ['last 1 version']
      },
      dist: {
        files: [
          {
            expand: true,
            cwd: '<%= yeoman.app %>/styles/',
            src: ['**/*.css'],
            dest: '<%= yeoman.staging %>/styles/'
          },
          {
            expand: true,
            cwd: '<%= yeoman.staging %>/less/',
            src: ['**/*.css'],
            dest: '<%= yeoman.staging %>/styles/'
          }
        ]
      }
    },

    // Automatically inject Bower components into the app
    wiredep: {
      options: {
        cwd: '<%= yeoman.app %>',
        bowerJson: require('./bower.json'),
        directory: '<%= yeoman.vendor %>'
        //directory: '.tmp/vendor' // '<%= yeoman.vendor %>'
      },
      app: {
        cwd: '<%= yeoman.staging %>',
        src: ['index.html'],
        dest: '<%= yeoman.staging %>'
      }
    },

    "html-build": {
      staging: {
        src: '<%= yeoman.staging %>/index.html',
        dest: '<%= yeoman.staging %>/index.html',
        options: {
          baseDir: '<%= yeoman.staging %>/',
          beautify: true,
          relative: true,
          replace: true,
          tagName: 'html-stage-build',
          parseTag: 'html-stage-build',
          logOptions: true,
          scripts: {
            bundle: [
              '<%= yeoman.staging %>/scripts/jchptf/**/*.js',
              '!<%= yeoman.staging %>/scripts/livereload.js'
            ]
          },
          styles: {
            bundle: ['<%= yeoman.staging %>/styles/**/*.css']
          }
        }
      },
      dist: {
        src: '<%= yeoman.staging %>/index.html',
        dest: '<%= yeoman.dist %>/index.html',
        options: {
          beautify: false,
          relative: false,
          replace: false,
          tagName: 'html-dist-build',
          parseTag: 'html-dist-build',
          logOptions: true
        }
      }
    },

    // Renames files for browser caching purposes
    filerev: {
      dist: {
        src: [
          // This would likely break Angular code references to templates...
          // '<%= yeoman.dist %>/views/**/*.html',
          // '<%= yeoman.dist %>/scripts/**/*.js',
          // '<%= yeoman.dist %>/styles/**/*.css',
          '<%= yeoman.dist %>/images/**/*.{png,jpg,jpeg,gif,webp,svg}',
          '<%= yeoman.dist %>/styles/fonts/**/*',
          '<%= yeoman.dist %>/fonts/**/*'
        ]
      }
    },

    // Reads HTML for usemin blocks to enable smart builds that automatically
    // concat, minify, and revision files.  Creates configurations in memory so
    // additional tasks can operate on them.
    useminPrepare: {
      html: [
        '<%= yeoman.app %>/index.html'
        // '<%= yeoman.app %>/views/**/*.html'
  ],
  options: {
    root: '<%= yeoman.app %>/',
      staging: '<%= yeoman.staging %>/',
      dest: '<%= yeoman.dist %>/',
      flow: {
      html: {
        steps: {
          js: ['concat', 'uglifyjs'],
          css: ['concat', 'cssmin']
        },
        post: {}
      }
    }
  }
},

  // Performs rewrites based on filerev and the useminPrepare configuration
  //
  // Uncommented css block below to turn on css minification via the 'cssmin'
  // grunt plugin instead of inside 'usemin'.  Done so primarily because
  // 'usemin' came configured to expect its input in location where 'cssmin'
  // writes its output, and not from where 'cssmin' gets it input.
    usemin: {
      html: [
        '<%= yeoman.dist %>/index.html',
        '<%= yeoman.dist %>/views/**/*.html'
      ],
      // css: ['<%= yeoman.staging %>/styles/**/*.css'],
      css: ['<%= yeoman.dist %>/styles/**/*.css'],
      js: [
        '<%= yeoman.dist %>/scripts/**/*.js',
        // '<%= yeoman.staging %>/scripts/**/*.js',
        // '<%= yeoman.vendor>/**/*.js'
      ],
      options: {
        assetsDirs: [
            '<%= yeoman.dist %>/styles/fonts/',
            '<%= yeoman.dist %>/images/',
            '<%= yeoman.dist %>/fonts/'
            // '<%= yeoman.dist %>/'
        ],
        patterns: {
          js: [
            [/(card\d*\.png)/, 'Replacing reference to card image file'],
            [/((grid|hend|hmid|htop|unit|vend|vmid|vtop)\.png)/, 'Replacing border highlight image file'],
            [/(gval\/[A-Z]\.png)/, 'Replacing gval letter file image file'],
            [/(([A-Z]|[1345]0*|unk|blank|qm).png)/, 'Replacing val letter image file']
          ]
        }
      }
    },

    // The following *-min tasks will produce minified files in the dist folder
    // By default, your `index.html`'s <!-- Usemin block --> will take care of
    // minification. These next options are pre-configured if you do not wish
    // to use the Usemin blocks.
    // cssmin: {
    //   dist: {
    //     files: {
    //       '<%= yeoman.dist %>/styles/main.css': [
    //         '<%= yeoman.staging %>/styles/**/*.css'
    //       ]
    //     }
    //   }
    // },

    // uglify: {
    //   dist: {
    //     files: {
    //       '<%= yeoman.dist %>/scripts/scripts.js': [
    //         '<%= yeoman.app %>/scripts/**/*.js',
    //         '<%= yeoman.staging %>/scripts/**/*.js'
    //       ]
    //     }
    //   }
    // },

    // concat: {
    //   dist: {}
    // },

    // imagemin: {
    //   dist: {
    //     files: [{
    //       expand: true,
    //       cwd: '<%= yeoman.app %>/images',
    //       src: '**/*.{png,jpg,jpeg,gif}',
    //       dest: '<%= yeoman.dist %>/images'
    //     }]
    //   }
    // },

    // svgmin: {
    //   dist: {
    //     files: [{
    //       expand: true,
    //       cwd: '<%= yeoman.app %>/images',
    //       src: '**/*.svg',
    //       dest: '<%= yeoman.dist %>/images'
    //     }]
    //   }
    // },

    htmlmin: {
      dist: {
        options: {
          collapseWhitespace: true,
          conservativeCollapse: true,
          collapseBooleanAttributes: true,
          removeCommentsFromCDATA: true,
          removeOptionalTags: true
        },
        files: [{
          expand: true,
          cwd: '<%= yeoman.dist %>',
          // cwd: '<%= yeoman.app %>/',
          src: [
            'index.html',
            'views/**/*.html'
          ],
          dest: '<%= yeoman.dist %>/'
        }]
      }
    },

    // ngAnnotate tries to make the code safe for minification automatically by
    // using the Angular long form for dependency injection. It doesn't work on
    // things like resolve or inject so those have to be done manually.
    ngAnnotate: {
      dist: {
        files: [{
          expand: true,
          cwd: '<%= yeoman.staging %>/concat/scripts',
          src: ['**/*.js'],
          dest: '<%= yeoman.staging %>/concat/scripts'
        }]
      }
    },

    // Replace Google CDN references
    cdnify: {
      dist: {
        html: ['<%= yeoman.dist %>/*.html']
      },
      staging: {
        html: ['<%= yeoman.staging %>/*.html']
      }
    },

    // Copies all files that don't migrate to where they are expected
    // through a pre-processing plugin.  A given build should copy:dist
    // or copy:staging, but unlikely both.  Use copy:staging for running
    // a dev build, and copy:dist for running or packaging a production
    // build.
    //
    // Difference is that only production builds need to copy generated
    // images from staging since there are no downstream image preprocessors
    // to pick the images up from there, whereas only dev builds need to
    // stage the test scripts (in order to run them from there).
    copy: {
      dist: {
        files: [{
          expand: true,
          dot: true,
          cwd: '<%= yeoman.app %>/',
          dest: '<%= yeoman.dist %>/',
          src: [
            '.htaccess',
            '*.{ico,png,txt}',
            'styles/fonts/**/*',
            'fonts/**/*',
            'views/**/*.html'
          ]
        }, {
          expand: true,
          cwd: '<%= yeoman.staging %>/images/',
          src: [
            '**/*.webp',
            'generated/**/*'
          ],
          dest: '<%= yeoman.dist %>/images/'
        }]
      },
      staging: {
        files: [{
          expand: true,
          dot: true,
          cwd: '<%= yeoman.app %>/',
          src: [
              'test/**/*.{js,conf,json}',
            'vendor/**/*',
            '.htaccess',
            '*.{ico,png,txt}',
            'styles/fonts/**/*',
            'fonts/**/*',
            'views/**/*.html',
            'index.html'
            ],
          dest: '<%= yeoman.staging %>/'
        // }, {
        //   // Coffee migrates all coffee-derived JS files, but hand-created ones
        //   // don't reach the staging area via a pre-processor.
        //   expand: true,
        //   cwd: '<%= yeoman.app %>/test/spec/',
        //   src: '**/*.js',
        //   dest: '<%= yeoman.staging %>/test/spec/'
        }]
      },
      styles: {
        // less migrates all template-based CSS files, but hand-created
        // .css files don't reach the staging area via a pre-processor.
        expand: true,
        cwd: '<%= yeoman.app %>/styles/',
        src: '**/*.css',
        dest: '<%= yeoman.staging %>/styles/'
      },
      scripts: {
        // coffee migrates all compiled coffeeScript files, but hand-created
        // .js files don't reach the staging area via a pre-processor.
        files: [{
          expand: true,
          cwd: '<%= yeoman.app %>/scripts/',
          src: '**/*.{js,coffee}',
          dest: '<%= yeoman.staging %>/scripts/'
        }, {
          expand: true,
          cwd: '<%= yeoman.lbclient %>/',
          src: 'browser.bundle.js',
          dest: '<%= yeoman.staging %>/lbclient/'
        }]
      },
      images: {
        // Image minification is not working well at present.
        expand: true,
        cwd: '<%= yeoman.app %>/images/',
        src: '**/*.{jpg,jpeg,gif,png,webp,svg}',
        dest: '<%= yeoman.dist %>/images/'
      }
    },

    // Run some tasks in parallel to speed up the build process
    concurrent: {
      server: [
        'copy:staging',
        'copy:scripts',
        'copy:styles'
      ],
      test: [
        // Need index.html and views?
        // 'copy:staging',
        'copy:scripts',
        'copy:styles'
      ],
      build: [
        // Re-include this if run:development and run:dist both end up having
        // some required preprocessors that source from staging.
        'copy:staging',
        'copy:dist',
        // Should copy:dist drop HTML handling and defer that to htmlmin?
        //'htmlmin',
        'copy:scripts',
        'copy:styles',
        //'imagemin'
        //'svgmin'
        'copy:images'
      ],
      runServer: {
        tasks: ['nodemon', 'node-inspector', 'watch'],
        options: {
          logConcurrentOutput: true
        }
      }
    },

  'node-inspector': {
    dev: {}
  },

  nodemon: {
    dev: {
      script: 'server/server.js',
      watch: ['common', 'server'],
      options: {
        nodeArgs: ['--debug'],
        env: {
          PORT: '3000'
        },
        // omit this property if you aren't serving HTML files and
        // don't want to open a browser tab on start
        callback: function (nodemon) {
          nodemon.on('log', function (event) {
            console.log(event.colour);
          });

          // opens browser on initial server start
          nodemon.on('config:update', function () {
            // Delay before server listens on port
            setTimeout(function() {
              require('open')('http://localhost:3000');
            }, 1000);
          });

          // refreshes browser when server reboots
          nodemon.on('restart', function () {
            // Delay before server listens on port
            setTimeout(function() {
              require('fs').writeFileSync('.rebooted', 'rebooted');
            }, 1000);
          });

          nodemon.on('error', function(err) {
            if (err.code === 'EADDRINUSE') {
              var connectConfig = grunt.config.get().connect.options;
              grunt.fatal('Port ' + connectConfig.port +
                ' is already in use by another process.');
            } else {
              grunt.fatal(err);
            }
          });
        }
      }
    }
  },

    // Test settings
    karma: {
      unit: {
        // TODO: Can the config file exist in yeoman.app while all the scripts
        //       being tested are tested from <%= yeoman.staging %> ?
        // configFile: '<%= yeoman.app %>/test/karma.conf.js',
        configFile: '<%= yeoman.staging %>/test/karma.conf.js',
        browsers: [ 'PhantomJS' ],
        singleRun: true
      }
    },

    // Server Tests
    mochaTest: {
      common: {
        options: {
          reporter: 'spec',
          quiet: false,
          clearRequireCache: false
        },
        src: ['common/models/test/**/*.js']
      },
      server: {
        options: {
          reporter: 'spec',
          quiet: false,
          clearRequireCache: false
        },
        src: ['server/test/**/*.js']
      }
    }
  });

  grunt.registerTask('build-lbclient', 'Build lbclient browser bundle', function() {
    var done = this.async();
    buildClientBundle(process.env.NODE_ENV || 'development', done);
  });

  grunt.registerTask('build-config', 'Build config.js from JSON files', function() {
    var ngapp = path.resolve(__dirname, appConfig.app);
    var configDir = path.join(ngapp, 'config');
    var config = {};

    fs.readdirSync(configDir)
      .forEach(function(f) {
        if (f === 'bundle.js') return;

        var extname = path.extname(f);
        if (extname !== '.json') {
          grunt.warn('Ignoring ' + f + ' (' + extname + ')');
          return;
        }

        var fullPath = path.resolve(configDir, f);
        var key = path.basename(f, extname);

        config[key] = JSON.parse(fs.readFileSync(fullPath), 'utf-8');
      });

    var outputDir = path.resolve(__dirname, '.tmp', 'config');
    var outputPath = path.resolve(outputDir, 'bundle.js');
    var content = 'window.CONFIG = ' +
        JSON.stringify(config, null, 2) + ';\n';
    fs.mkdirSync(outputDir);
    fs.writeFileSync(outputPath, content, 'utf-8');
  });

  grunt.registerTask('run', 'Start the app server', function() {
    var done = this.async();

    var connectConfig = grunt.config.get().connect.options;
    process.env.LIVE_RELOAD = connectConfig.livereload;
    process.env.NODE_ENV = this.args[0];

    var keepAlive = this.flags.keepalive || connectConfig.keepalive;
    console.log('LIVE_RELOAD = ', process.env.LIVE_RELOAD, 'NODE_ENV = ', this.args[0], 'keepAlive = ', keepAlive );

    var server = require('./server');
    server.set('port', connectConfig.port);
    server.set('host', connectConfig.hostname);
    server.start()
      .on('listening', function() {
        if (!keepAlive) done();
      })
      .on('error', function(err) {
        if (err.code === 'EADDRINUSE') {
          grunt.fatal('Port ' + connectConfig.port +
            ' is already in use by another process.');
        } else {
          grunt.fatal(err);
        }
      });
  });

  grunt.registerTask('serve', 'Compile then start the app server', function (target) {
    if (target === 'dist') {
      return grunt.task.run(['build', 'html-build:dist', 'run:dist:keepalive']);
    }

    grunt.task.run([
      'clean:server',
      'coffeelint',
      'jshint',
      'less',
      'build-lbclient',
      'build-config',
      'concurrent:server',
      'cdnify:staging',
      'coffee',
      'wiredep',
      'html-build:staging',
      'autoprefixer',
      // 'run:development',
      // 'watch'
      'concurrent:runServer'
    ]);
  });

  grunt.registerTask('server', 'DEPRECATED TASK. Use the "serve" task instead', function (target) {
    grunt.log.warn('The `server` task has been deprecated. Use `grunt serve` to start a server.');
    grunt.task.run(['serve:' + target]);
  });

  grunt.registerTask('test:client', [
    'clean:server',
    'coffeelint',
    'jshint',
    'less',
    'build-lbclient',
    'build-config',
    'concurrent:test',
    'coffee',
    'autoprefixer',
    'connect:test',
    'karma'
  ]);

  grunt.registerTask('test:common', [
    'mochaTest:common'
  ]);

  grunt.registerTask('test:server', [
    'mochaTest:server'
  ]);

  grunt.registerTask('test', [
    'test:server',
    'test:common',
    'test:client'
  ]);

  grunt.registerTask('build', [
    'clean:dist',
    'coffeelint',
    'jshint',
    'less',
    'build-lbclient',
    'build-config',
    'concurrent:build',
    // 'cdnify',
    'coffee',
    'wiredep',
    'html-build:staging',
    'html-build:dist',
    'useminPrepare',
    'autoprefixer',
    'concat:generated',
    'cssmin:generated',
    'uglify:generated',
    'filerev',
    'usemin',
    'ngAnnotate'
    // 'htmlmin'
  ]);

  grunt.registerTask('clean-all', [
      'clean:all'
  ]);

  grunt.registerTask('default', [
    'coffeelint',
    'newer:coffee',
    'newer:jshint',
    'test',
    'build'
  ]);
};
