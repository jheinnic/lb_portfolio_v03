// Generated on 2014-06-23 using generator-angular 0.9.1
'use strict';

var buildClientBundle = require('./client/lbclient/build');
var fs = require('fs');
var path = require('path');

// # Globbing
// for performance reasons we're only matching one level down:
// 'test/spec/{,**/}*.js'
// use this if you want to recursively match all subfolders:
// 'test/spec/{,**/}*.js'

module.exports = function (grunt) {
  // For building filesystem paths by concatenation.
  var path = require('path');

  // Load grunt tasks automatically
  require('load-grunt-tasks')(grunt);

  // Time how long tasks take. Can help when optimizing build times
  require('time-grunt')(grunt);

  // Configurable paths for the applicatio
  var bowerSpec = require('./bower.json');
  var bowerRc = require('./.bowerrc.json');
  var appConfig = {
      app: null,
      vendor: null,
      dist: null
  };
  appConfig.app = bowerSpec.appPath || path.join('client', 'ngapp');
  appConfig.vendor = bowerRc.directory || bowerSpec.vendorPath || path.join('client', 'vendor');
  appConfig.dist = bowerSpec.distPath || path.join('client', 'dist');

    console.log(appConfig);
    // console.log('<%= yeoman.app %>/scripts/{,**/}*.coffee');
    // console.log('<%= yeoman.app %>/scripts/{,**/}*.js');
    // console.log('.tmp/coffee/{,*/}*.js');

  // Define the configuration for all the tasks
  grunt.initConfig({
    // Project settings
    yeoman: appConfig,

    // Watches files for changes and runs tasks based on the changed files
    watch: {
      bower: {
        files: ['bower.json'],
        tasks: ['wiredep']
      },
      coffee: {
        files: [
          '<%= yeoman.app %>/scripts/{,**/}*.coffee'
        ],
        tasks: ['coffee:dist']
      },
      js: {
        files: ['<%= yeoman.app %>/scripts/{,**/}*.js'],
        tasks: ['newer:jshint:all'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
    // coffeeTest: {
    //   files: [
    //      '<%= yeoman.app %>/test/spec-coffee/{,**/}*.coffee',
    //      '<%= yeoman.app %>/test/spec/{,**/}*.coffee'
    //    ],
    //    tasks: ['coffee:test']
    //  },
      jsTest: {
        files: [ '<%= yeoman.app %>/test/spec/{,**/}*.js' ],
        tasks: ['newer:jshint:test', 'karma']
      },
      less: {
        files: [ '<%= yeoman.app %>/styles/{{,**/}}*.less' ],
        // tasks: ['newer:copy:styles', 'newer:less', 'autoprefixer', 'useminPrepare', 'usemin', 'filerev']
        tasks: ['newer:less', 'autoprefixer']
      },
      styles: {
        files: ['<%= yeoman.app %>/styles/{,**/}}*.css'],
        // tasks: ['newer:copy:styles', 'newer:less', 'autoprefixer', 'useminPrepare', 'usemin', 'filerev']
        tasks: ['newer:copy:styles', 'autoprefixer']
      },
      // images: {
      //   files: ['<%= yeoman.app %>/images/{,**/}'],
      //   // tasks: ['newer:copy:dist', 'imagemin', 'svgmin', 'userminPrepare', 'usemin', 'filerev']
      //   tasks: ['newer:copy:dist']
      // },
      fonts: {
        files: ['<%= yeoman.app %>/fonts/{,**/}*' ],
        // tasks: ['newer:copy:fonts', 'autoprefixer', 'useminPrepare', 'usemin', 'filerev']
        tasks: ['newer:copy:dist', 'autoprefixer']
      },
      gruntfile: {
        files: ['Gruntfile.js']
      },
      livereload: {
        options: {
          livereload: '<%= connect.options.livereload %>'
        },
        files: [
          '<%= yeoman.app %>/images/{,**/}*.{png,jpg,jpeg,gif,webp,svg}',
          '<%= yeoman.app %>/{index,views/{,**/}*}.html',
	  '<%= yeoman.app %>/{fonts,styles/fonts}/{,**/}*',
          '.tmp/styles/{,**/}*.css',
        ]
      },
      lbclient: {
        files: [
          'lbclient/models/*',
          'lbclient/app*',
          'lbclient/datasources*',
          'lbclient/models*',
          'lbclient/build.js'
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
              // connect.static('.tmp'),
              // connect.static('test'),
              connect().use(
                '/vendor', '<%= appConfig.vendor %>'
              ),
              connect().use(
                '/lbclient', connect.static( './lbclient' )
              ),
              connect.use(
                '/views', connect.static( '<%= appConfig.app %>/views' )
              ),
              connect.use(
                '/images', connect.static( '<%= appConfig.app %>/images' )
              ),
              connect.configure(
                '/scripts',
                function() {
                  connect.static( '<%= appConfig.app %>/scripts' );
                  connect.static( '.tmp/coffee' );
                }
              ),
              connect.configure(
                '/styles',
                function() {
                  connect.static( '<%= appConfig.app %>/styles' );
                  connect.static( '.tmp/styles' );
                }
              ),
              connect.configure(
                '/fonts',
                function() {
                  connect.static( '<%= appConfig.app %>/fonts' );
                  connect.static( '<%= appConfig.app %>/styles/fonts' );
                }
              ),
              connect.use(
                '/', connect.static( '<%= appConfig.app %>/index.html' )
              )
            ];
          }
        }
      }
    },

    // Compiles CoffeeScript to JavaScript.
    coffee: {
      options: { },
      dist: {
        cwd: '<%= yeoman.app %>/scripts',
        src: [
          '<%= yeoman.app %>/scripts/{,**/}*.coffee'
        ],
        dest: '.tmp/coffee/'
      },
      test: {
        cwd: '<%= yeoman.app %>/test/spec',
        src: [
          '<%= yeoman.app %>/test/spec/{,**/}*.coffee'
        ],
        dest: '.tmp/test/spec-coffee/'
      }
    },

    // Make sure code styles are up to par and there are no obvious mistakes
    jshint: {
      options: {
        jshintrc: '.jshintrc',
        reporter: require('jshint-stylish')
      },
      all: {
        src: [
          'Gruntfile.js',
          '<%= yeoman.app %>/scripts/{,**/}*.js',
            'client/ngapp/scripts/*/*/*.js'
        ]
      },
      test: {
        options: {
          jshintrc: '<%= yeoman.app %>/test/.jshintrc'
        },
        src: [
            '<%= yeoman.app %>/test/spec/{,**/}*.js',
        ]
      }
    },

    less: {
      options: {
        paths: [
          appConfig.app + '/styles',
          appConfig.app + '/vendor/bootstrap/less'
        ]
      },
      dist: {
        cwd: '<%= yeoman.app %>/styles/',
        src: [
          '<%= yeoman.app %>/styles/{,**/}*.less'
        ],
        dest: '.tmp/styles/'
      }
    },

    // Empties folders to start fresh
    clean: {
      all: {
        files: [{
          dot: true,
          src: [
            '.tmp',
            '<%= yeoman.dist %>/{,**/}*',
            '!<%= yeoman.dist %>/.git*',
            'lbclient/browser.bundle.js',
            '<%= yeoman.app %>/config/bundle.js'
          ]
        }]
      },
      dist: {
        files: [{
          dot: true,
          src: [
            '.tmp',
            '<%= yeoman.dist %>/{,**/}*',
            '!<%= yeoman.dist %>/.git*'
          ]
        }]
      },
      server: '.tmp',
      lbclient: 'lbclient/browser.bundle.js',
      config: '<%= yeoman.app %>/config/bundle.js'
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
            src: '<%= yeoman.app %>/styles/{,**/}*.css',
            dest: '.tmp/styles/'
          }
        ]
      }
    },

    // Automatically inject Bower components into the app
    wiredep: {
      options: {
        cwd: '<%= yeoman.app %>',
        bowerJson: require('./bower.json'),
        directory: '<%= yeoman.dist %>'
      },
      app: {
        src: ['<%= yeoman.app %>/index.html'],
        ignorePath:  /..\//
      }
    },

    // Renames files for browser caching purposes
    filerev: {
      dist: {
        src: [
          '<%= yeoman.dist %>/scripts/{,**/}*.js',
          '<%= yeoman.dist %>/styles/{,**/}*.css',
          '<%= yeoman.dist %>/images/{,**/}',
          '<%= yeoman.dist %>/styles/fonts/{,**/}*',
          '<%= yeoman.dist %>/fonts/{,**/}*'
        ]
      }
    },

    // Reads HTML for usemin blocks to enable smart builds that automatically
    // concat, minify and revision files. Creates configurations in memory so
    // additional tasks can operate on them
    useminPrepare: {
      html: '<%= yeoman.app %>/index.html',
      options: {
        dest: '<%= yeoman.dist %>',
        flow: {
          html: {
            steps: {
              js: ['concat', 'uglifyjs'],
              images: ['imagemin'],
              css: ['cssmin']
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
    // 'usemin' can configured to expect its input in location where 'cssmin'
    // writes its output, and not from where 'cssmin' gets it input.
    usemin: {
      html: [
          '<%= yeoman.dist %>/index.html',
          '<%= yeoman.dist %>/views/{,**/}*.html'
      ],
      css: [
          '<%= yeoman.dist %>/styles/{,**/}*.css',
      ],
      options: {
        assetsDirs: [
            '<%= yeoman.dist %>/fonts',
            '<%= yeoman.dist %>/images'
        ]
      }
    },

    // The following *-min tasks will produce minified files in the dist folder
    // By default, your `index.html`'s <!-- Usemin block --> will take care of
    // minification. These next options are pre-configured if you do not wish
    // to use the Usemin blocks.
    cssmin: {
      dist: {
        files: {
          '<%= yeoman.dist %>/styles/main.css': [
            '.tmp/styles/{,**/}*.css'
          ]
        }
      }
    },

    uglify: {
      dist: {
        files: {
          '<%= yeoman.dist %>/scripts/scripts.js': [
            '<%= yeoman.app %>/scripts/{,**/}*.js',
            '.tmp/scripts/{,**/}*.js'
          ]
        }
      }
    },

    // concat: {
    //   dist: {}
    // },

    imagemin: {
      dist: {
        files: [{
          expand: true,
          cwd: '<%= yeoman.app %>/images',
          src: '<%= yeoman.app %>/images/{,**/}',
          dest: '<%= yeoman.dist %>/images'
        }]
      }
    },

    svgmin: {
      dist: {
        files: [{
          expand: true,
          cwd: '<%= yeoman.app %>/images',
          src: '{,**/}*.svg',
          dest: '<%= yeoman.dist %>/images'
        }]
      }
    },

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
          // cwd: '<%= yeoman.dist %>',
          cwd: '<%= yeoman.app %>',
          src: [
            'index.html',
            '<%= yeoman.app %>/views/{,**/}*.html'
          ],
          dest: '<%= yeoman.dist %>'
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
          cwd: '.',
          src: '.tmp/concat/scripts/{,**/}*.js',
          dest: '.tmp/concat/scripts'
        }]
      }
    },

    // Replace Google CDN references
    cdnify: {
      dist: {
        html: ['<%= yeoman.dist %>/*.html']
      }
    },

    // Copies remaining files to places other tasks can use
    copy: {
      dist: {
        files: [{
          expand: true,
          dot: true,
          cwd: '<%= yeoman.app %>',
          dest: '<%= yeoman.dist %>',
          src: [
            '*.{ico,png,txt}',
            '.htaccess',
            //'*.html',
            //'views/{,**/}*.html',
            'images/{,**/}',
            'fonts/*'
          ]
        }, {
          expand: true,
          cwd: '.tmp/images',
          dest: '<%= yeoman.dist %>/images',
          src: ['generated/*']
        }]
      },
      styles: {
        expand: true,
        cwd: '<%= yeoman.app %>',
        dest: '.tmp/styles/',
        src: '<%= yeoman.app %>/styles/{,**/}*.css'
      }
    },

    // Run some tasks in parallel to speed up the build process
    concurrent: {
      server: [
        'copy:styles'
      ],
      test: [
        'copy:styles'
      ],
      dist: [
        'copy:styles',
        'imagemin',
        'svgmin'
      ]
    },

    // Test settings
    karma: {
      unit: {
        configFile: '<%= yeoman.app %>/test/karma.conf.js',
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
        src: ['common/models/test/{,**/}*.js']
      },
      server: {
        options: {
          reporter: 'spec',
          quiet: false,
          clearRequireCache: false
        },
        src: ['server/test/{,**/}*.js']
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

    var outputPath = path.resolve(ngapp, 'config', 'bundle.js');
    var content = 'window.CONFIG = ' +
        JSON.stringify(config, null, 2) +
        ';\n';
    fs.writeFileSync(outputPath, content, 'utf-8');
  });

  grunt.registerTask('run', 'Start the app server', function() {
    var done = this.async();

    var connectConfig = grunt.config.get().connect.options;
    process.env.LIVE_RELOAD = connectConfig.livereload;
    process.env.NODE_ENV = this.args[0];

    var keepAlive = this.flags.keepalive || connectConfig.keepalive;

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
      return grunt.task.run(['build', 'run:dist:keepalive']);
    }

    grunt.task.run([
      'clean:server',
      'coffee',
      'less',
      'build-lbclient',
      'build-config',
      'wiredep',
      'concurrent:server',
      'autoprefixer',
      'run:development',
      'watch'
    ]);
  });
  console.log("Registered task 'server'");

  grunt.registerTask('server', 'DEPRECATED TASK. Use the "servv" task instead', function (target) {
    grunt.log.warn('The `server` task has been deprecated. Use `grunt serve` to start a server.');
    grunt.task.run(['serve:' + target]);
  });

  grunt.registerTask('test:client', [
    'clean:server',
    'coffee',
    'less',
    'build-lbclient',
    'build-config',
    'concurrent:test',
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
    'coffee',
    'less',
    'build-lbclient',
    'build-config',
    'wiredep',
    'useminPrepare',
    'concurrent:dist',
    'autoprefixer',
    'concat',
    'ngAnnotate',
    'copy:dist',
    'cdnify',
    'cssmin',
    'uglify',
    'filerev',
    'usemin',
    'htmlmin',
    'imagemin'
  ]);
  console.log( "Register Task 'build'");

  grunt.registerTask('clean-all', [
      'clean:dist',
      'clean:server',
      'clean:config',
      'clean:lbclient'
  ]);

  grunt.registerTask('default', [
    'newer:jshint',
    'test',
    'build'
  ]);
};
