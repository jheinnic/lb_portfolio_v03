// Generated on 2014-06-23 using generator-angular 0.9.1
'use strict';

var buildClientBundle = require('./client/lbclient/build');
var fs = require('fs');
var _ = require('lodash');

// For building lists of files
var traverse = require('./common/components/fsutil/traverse');
function findAll(searchDir, searchRegex) {
  var allMatches = [];

  function fileHandler(currentFile) {
    if (searchRegex.test(currentFile)) {
      allMatches.push(currentFile);
    }
  }

  traverse(searchDir, fileHandler, undefined, false);
  return allMatches;
}

// # Globbing
// We're using this to recursively match all sub-folders:
//   'test/spec/**/*.js'
//
// If you only need to match one level down, you may use this for performance reasons instead:
//   'test/spec/{,*/}*.js'
//
// The {,*/} token substring gives an option of matching no subdirectory (left of the ,) or
// a non-empty wildcard followed by a path separator (right of the ,)

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
      app: bowerJson.appPath ? bowerJson.appPath : 'client/ngapp',
      vendor: (bowerRc && bowerRc.directory) ? bowerRc.directory : 'bower_components',
      dist: 'client/dist',
      lbclient: 'client/lbclient',
      staging: '.tmp'
  };

  // For Jade, predict arrays of style files post-preprocessing.
  var styleRoot = appConfig.app + '/styles';
  var styleRootRegex = new RegExp('^' + styleRoot);
  var stagedStyles = _.map(
    findAll(styleRoot, /\.(less|css)$/),
    function(source) {
      return source.replace(styleRootRegex, 'styles').replace(/\.less$/, '.css');
    }
  );

  // Define the configuration for all the tasks
  grunt.initConfig({
    // Project settings
    yeoman: appConfig,

    env: {
      dev: {
        NODE_ENV: 'development',
        LIVE_RELOAD: '<%= connect.options.livereload %>'
      },
      dist: {
        NODE_ENV: 'staging',
        LIVE_RELOAD: '<%= connect.options.livereload %>'
      },
      test: {
        NODE_ENV: 'development',
        LIVE_RELOAD: false
      }
    },

    // Watches files for changes and runs tasks based on the changed files
    watch: {
      nodemon: {
        // If nodemon recycles the server, it touches this marker, which in turn causes livereload to
        // signal need for refresh to browsers.
        files: ['.rebooted'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      bower: {
        // TODO: This cannot yet handle a change to the app directory in bower.json on yeoman!!!
        files: ['bower.json', 'build/*.js'],
        tasks: ['copy:wiredep', 'wiredep:dev', 'browserify'],
        options: {
          livereload: '<%= connect.options.livereload %>',
          reload: true
        }
      },
      coffee: {
        files: ['<%= yeoman.app %>/scripts/**/*.coffee', 'common/components/**/*.coffee'],
        tasks: ['coffeelint:dist', 'browserify'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      js: {
        files: ['<%= yeoman.app %>/scripts/**/*.js', 'common/components/**/*.js'],
        tasks: ['jshint:dist', 'copy:wiredep', 'wiredep:dev', 'browserify'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      // TODO: Can we skip the copy step?  Its needed for minification pipeline, but for debug purpose, watch
      //       for livereload should serve the true source files.
      // coffeeTest: {
      //   files: ['<%= yeoman.app %>/test/spec/**/*.coffee'],
      //   tasks: ['coffeelint:test', 'coffee:test', 'karma']
      // },
      // jsTest: {
      //   files: ['<%= yeoman.app %>/test/spec/**/*.js'],
      //   // tasks: ['newer:copy:test', 'newer:jshint:test', 'karma']
      //   tasks: ['newer:jshint:test', 'newer:copy:staging', 'karma']
      // },
      less: {
        files: ['<%= yeoman.app %>/styles/**/*.less'],
        tasks: ['less', 'autoprefixer'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      styles: {
        files: ['<%= yeoman.app %>/styles/**/*.css'],
        // tasks: ['newer:copy:styles', 'newer:less', 'autoprefixer', 'useminPrepare', 'usemin', 'filerev']
        tasks: ['copy:dev', 'autoprefixer'],
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
        tasks: ['copy:dev'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      index: {
        files: ['<%= yeoman.app %>/index.jade'],
        tasks: ['jade', 'wiredep'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      gruntfile: {
        files: ['Gruntfile.js'],
        tasks: ['jshint', 'coffeelint', 'browserify', 'less', 'copy:dev', 'copy:wiredep', 'wiredep', 'autoprefixer'],
        options: {
          reload: true
        }
      },
      livereload: {
        files: [
          '<%= yeoman.app %>/images/**/*.{png,jpg,jpeg,gif,webp,svg}',
          // TODO: Confirm that node doesn't require a restart to serve modified HTML due to caching.
          '<%= yeoman.app %>/views/**/*.html',
          //    '<%= yeoman.app %>/{fonts,styles/fonts}/**/*',
          // '<%= yeoman.staging %>/styles/**/*.css',
          // TODO: Consider refining server file watch dependencies since they also cause a need to restart.
          // '{common,server}/**/*'
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
        host: 'localhost',
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

    // Compiles CoffeeScript to JavaScript.
    coffee: {
      options: {
        sourceMap: true
      },
    //   dist: {
    //     ext: '.js',
    //     extDot: 'last',
    //     expand: true,
    //     cwd: '<%= yeoman.app %>/scripts',
    //     src: ['**/*.coffee'],
    //     dest: '<%= yeoman.staging %>/scripts/'
    //   },
      test: {
        ext: '.js',
        extDot: 'last',
        expand: true,
        cwd: '<%= yeoman.app %>/test/spec',
        src: ['**/*.coffee'],
        dest: '<%= yeoman.staging %>/test/spec/'
      }
    },

    browserify: {
      build: {
        files: {
          '<%= yeoman.staging %>/app.js': [
            '<%= yeoman.app %>/scripts/**/*.{js,coffee}',
            '<%= common/components/**/*.{js,coffee}',
            '!<%= yeoman.app %>/scripts/lr_init.js'
          ]
        }
      },
      options: {
//        alias: {
//          'jchptf' : path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/jchptf.js'),
////            'jchptf.authenticate': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/authenticate/authenticate.js'),
////            'jchptf.context': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/context/context.js'),
////            'jchptf.crosswords': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/crosswords/crosswords.js'),
////            'jchptf.crosswords.browse': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/crosswords/browse/browse.js'),
////            'jchptf.crosswords.tickets': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/crosswords/tickets/tickets.js'),
////            'jchptf.crosswords.results': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/crosswords/results/results.js'),
////            'jchptf.modeling.core': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/modeling/core/core.js'),
////            'jchptf.modeling.repository': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/modeling/repository/repository.js'),
////            'jchptf.modeling.studio': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/modeling/studio/studio.js'),
//          'jchptf.site.navigation': path.join(__dirname, appConfig.app, 'scripts', 'jchptf', 'site', 'navigation', 'navigation.js'),
//          'jchptf.site.notification': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/site/notification/notification.js'),
//          'jchptf.tools.iconPanel': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/tools/iconPanel/iconPanel.js'),
//          'jchptf.tools.keyboard': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/tools/keyboard/keyboard.js'),
//          'jchptf.tools.navbar': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/tools/navbar/navbar.js'),
//          // 'jchptf.tools.workspace': path.normalize(__dirname  +  '/' + appConfig.app + '/scripts/jchptf/tools/workspace/workspace.js'),
//          // 'jchptf.poker': path.normalize(__dirname  +  '/.tmp/scripts/jchptf/poker/poker.js'),
//          // 'jchptf.support.feedback': path.normalize(__dirname  +  '/.tmp/scripts/jchptf/support/feedback/feedback.js'),
//          // 'jchptf.support.password': path.normalize(__dirname + '/.tmp/scripts/jchptf/support/password/password.js'),
//          // 'jchptf.user': path.normalize(__dirname '/.tmp/scripts/jchptf/user/user.js')
//        },
        require: [
          [path.normalize(
            path.join(__dirname, appConfig.app, 'scripts', 'jchptf', 'jchptf.js')
          ), {expose: 'jchptf'}],
          [path.normalize(
            path.join(__dirname, appConfig.app, 'scripts', 'jchptf', 'jchptf.config.js')
          ), {expose: 'jchptf/config'}],
            [path.normalize(
            path.join(__dirname, appConfig.app, 'scripts', 'jchptf', 'jchptf.run.js')
          ), {expose: 'jchptf/run'}]
        ],
        /*
        browserifyOptions: {
           debug: process.env.NODE_ENV === 'development'
          extensions: ['.js', '.json', '.coffee'],
          preBundleCB: function (b) {
            process.exit(-6);
            console.log('Browserify configure event fired', b);
            b.on('remapify:files', function(files, expandedAliases, pattern){
              fs.writeFileSync(
                path.join('foo', 'file') + Math.random(),
                'Remapify for pattern <' + pattern + '> processed <' + files + '> and returned <' + expandedAliases + '>'
              );
              console.log(
                'Remapify for pattern <' + pattern + '> processed <' + files + '> and returned <' + expandedAliases + '>'
              );
            });
          }
        },
         */
        transform: ['coffeeify'],
        plugin: [
          [
            'remapify',
            [
              {
                src: path.join('**', '*.js'),
                cwd: path.join(__dirname, 'common', 'components')
                // filter: require('./build/filterCommonAlias')
              },
              {
                src: path.join('**', '*.coffee'),
                cwd: path.join(__dirname, 'common', 'components')
                // filter: require('./build/filterCommonAlias')
              },
              {
                src: path.join('*', '**', '*.js'),
                cwd: path.join(__dirname, 'client', 'ngapp', 'scripts', 'jchptf'),
                expose: 'jchptf',
                filter: require('./build/filterAngularAlias')
              },
              {
                src: path.join('*', '**', '*.coffee'),
                cwd: path.join(__dirname, 'client', 'ngapp', 'scripts', 'jchptf'),
                expose: 'jchptf',
                filter: require('./build/filterAngularAlias')
              }
            ]
          ]
        ]
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
      dev: {
        src: ['<%= yeoman.staging %>/index.html'],
        ignorePath: /^\.\.\/client\/ngapp\//
      },
      dist: {
        src: ['<%= yeoman.dist %>/index.html'],
        ignorePath: /^\.\.\/ngapp\//
      }
    },

    // TODO: Use a stored variable to distinguish between dev and dist builds
    jade: {
      dev: {
        options: {
          data: function () {
            return {
              isDevelopment: true,
              // stagedScripts: stagedScripts,
              stagedStyles: stagedStyles
            };
          }
        },
        files: {
          '<%= yeoman.staging %>/index.html': '<%= yeoman.app %>/index.jade'
        }
      },
      dist: {
        options: {
          data: function () {
            return {
              isDevelopment: false,
              // stagedScripts: stagedScripts,
              stagedStyles: stagedStyles
            };
          }
        },
        files: {
          '<%= yeoman.dist %>/index.html': '<%= yeoman.app %>/index.jade'
        }
      }
    },

    // Renames files for browser caching purposes
    filerev: {
      dist: {
        src: [
          // This would likely break Angular code references to templates...
          '<%= yeoman.dist %>/*.js',
          '<%= yeoman.dist %>/views/**/*.html',
          '<%= yeoman.dist %>/styles/**/*.css',
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
        '<%= yeoman.dist %>/index.html',
        '<%= yeoman.dist %>/views/**/*.html'
      ],
      options: {
        root: '<%= yeoman.dist %>/',
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
      options: {
        assetsDirs: [
          '<%= yeoman.dist %>/',
          '<%= yeoman.dist %>/views/',
          '<%= yeoman.dist %>/fonts/',
          '<%= yeoman.dist %>/images/',
          '<%= yeoman.dist %>/scripts/',
          '<%= yeoman.dist %>/styles/',
          '<%= yeoman.dist %>/styles/fonts/'
        ]
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
          dest: '<%= yeoman.dist %>'
        }]
      }
    },

    // ngAnnotate tries to make the code safe for minification automatically by
    // using the Angular long form for dependency injection. It doesn't work on
    // things like resolve or inject so those have to be done manually.
    //ngAnnotate: {
    //  dist: {
    //    files: [{
    //      expand: true,
    //      cwd: '<%= yeoman.staging %>/concat/scripts',
    //      src: ['**/*.js'],
    //      dest: '<%= yeoman.staging %>/concat/scripts'
    //    }]
    //  }
    //},

    // Replace Google CDN references
    // cdnify: {
    //   dist: {
    //     html: ['<%= yeoman.dist %>/index.html']
    //   },
    //   dev: {
    //     html: ['<%= yeoman.staging %>/index.html']
    //   }
    // },

    // Copies all files that don't migrate to where they are expected through a pre-processing plugin.  A given build should copy:dist or
    // or copy:dev, but unlikely both.  Use copy:staging for running a dev build, and copy:dist for running/packaging a production build.
    //
    // Differences between the two copy sets are as follows:
    // -- Only production builds need to copy generated images from staging?  They must be in the dist file tree for the filerev plugin
    //    to find them and schedule them to receive a new randomized name suffix.
    // -- The dev build acquires application code by Browserification, not direct copy.  But it does copy the Javascript spec files for
    //    testing and it Coffee-compiles any spec files written in CoffeeScript.  The production build, in contrast, copies the
    //    browserified application bundle and minifies it from under the dist tree.
    //
    // Less migrates all template-based CSS files to staging by writing their compiled output there, but it needs to copy hand-created .css
    // files to get them under .tmp.  By the time dist wants them, they have already been concatenated and analysed for minification,
    // but not yet minified.
    //
    // Less is to CSS as Coffee is to Javascript, so ditto for those types.
    copy: {
      wiredep: {
        files: [{
          cwd: '<%= yeoman.app %>/', dest: '<%= yeoman.staging %>/', src: ['index.html']
        }]
      },
      dev: {
        files: [{
          expand: true,
          dot: true,
          cwd: '<%= yeoman.app %>/',
          dest: '<%= yeoman.staging %>/',
          src: [
            'test/**/*',
            'scripts/lr_init.js',
            'styles/**/*.css',
            '.htaccess',
            '*.{ico,png,txt}',
            'styles/fonts/**/*',
            'fonts/**/*',
            'views/**/*.html',
          ]
        }, {
          expand: true,
          cwd: '<%= yeoman.app %>/images/',
          dest: '<%= yeoman.staging %>/images/',
          src: [
            '**/*.webp',                         // TODO: Yes, this block is redundant, but there is some cleanup
            '**/*.{jpg,jpeg,gif,png,svg}'        //       work yet for image handling that will make the reasons for
          ]                                      //       keeping the boundary between classifications intact make sense.
        }, {
          expand: true,
          cwd: '<%= yeoman.lbclient %>/',
          dest: '<%= yeoman.staging %>/lbclient/',
          src: 'browser.bundle.js'
        }]
      },
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
            'images/**/*.webp',
            'images/**/*.{jpg,jpeg,gif,png,svg}', // TODO: This line should be done by image minification
            'views/**/*.html',
          ]
        }, {
          expand: true,
          cwd: '<%= yeoman.staging %>/',
          dest: '<%= yeoman.dist %>/',
          src: [
            'index.html',
            'styles/**/*.css',
            // 'scripts/**/*.js',
            'images/generated/**/*'
          ]
        }, {
          expand: true,
          cwd: '<%= yeoman.lbclient %>/',
          dest: '<%= yeoman.dist %>/lbclient/',
          src: 'browser.bundle.js'
        }]
      }
    },

    // Run some tasks in parallel to speed up the build process
    // To do a combined dev and dist full build, you must first follow the dev process because the dist
    concurrent: {
      build: [
        'less',
        'copy:dev',
        'coffeelint:dist',
        'jshint:dist',
        'build-lbclient',
        'build-config'
      ],
      test: [
        // TODO: Need view HTML?
        'less',
        'copy:dev',
        'coffeelint:test',
        'jshint:test',
        'coffeelint:dist',
        'jshint:dist',
        'build-lbclient',
        'build-config'
      ],
      dist: [
        // The dist step follows the build or test step.  It has only one more item in it (until image processing
        // is restored), but it is functionally dependent on the minification that needs staging to finish being
        // build before it may proceed.
        'copy:dist'
        // TODO: Migrate images from copy:dist if these can be made to work.
        //'imagemin'
        //'svgmin'
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
      options: {
        nodeArgs: ['--debug'],
        watch: ['common/models', 'server', '.tmp/app.js', '.tmp/index.html'],
        env: {
          PORT: 3000,
          LIVE_RELOAD: '<%= connect.options.livereload %>'
        },
        cwd: __dirname,
        ignore: ['node_modules/**', 'client/**'],
        ext: 'js,coffee,json',
        delay: 1250,
        // omit this property if you aren't serving HTML files and
        // don't want to open a browser tab on start
        callback: function (nodemon) {
          nodemon.on('log', function (event) {
            console.log(event.colour);
          });

          // opens browser on initial server start
          nodemon.on('config:update', function () {
            // Delay before browser opens navigation to root page
            var portConfig = grunt.config.get().nodemon.dev.options.env.PORT;
            setTimeout(function() {
              require('open')('http://localhost:' + portConfig);
            }, 3500);
          });

          // refreshes browser when server reboots
          nodemon.on('restart', function () {
            // Delay before server listens on port
            setTimeout(function() {
              require('fs').writeFileSync('.rebooted', 'rebooted');
            }, 3500);
          });

          nodemon.on('error', function(err) {
            if (err.code === 'EADDRINUSE') {
              var portConfig = '<%= nodemon.dev.options.env.PORT %>';
              grunt.fatal('Port ' + portConfig + ' is already in use by another process.', err);
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
        //       being tested are tested from <%= yeoman.staging %>?
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

    var stagingDir = path.resolve(__dirname, '.tmp');
    var outputDir = path.join(stagingDir, 'config');
    var outputPath = path.resolve(outputDir, 'bundle.js');
    if (! fs.existsSync(stagingDir)) {
      fs.mkdirSync(stagingDir);
      fs.mkdirSync(outputDir);
    } else if (! fs.existsSync(outputDir)) {
      fs.mkdirSync(outputDir);
    }

    var content = 'window.CONFIG = ' + JSON.stringify(config, null, 2) + ';\n';
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
      // return grunt.task.run(['env:dist', 'build', 'run:dist:keepalive']);
      return grunt.task.run(['build:dist', 'concurrent:runServer']);
    } else {
      // TODO: serve test for continuous karma?
      return grunt.task.run(['build:dev', 'concurrent:runServer']);
    }
  });

  grunt.registerTask('server', 'DEPRECATED TASK. Use the "serve" task instead', function (target) {
    grunt.log.warn('The `server` task has been deprecated. Use `grunt serve` to start a server.');
    grunt.task.run(['serve:' + target]);
  });

  grunt.registerTask('test:client', [
    'build:test',
    'connect:test',
    'karma'
  ]);

  grunt.registerTask('test:common', [
    'build:test',
    'mochaTest:common'
  ]);

  grunt.registerTask('test:server', [
    'build:test',
    'mochaTest:server'
  ]);

  grunt.registerTask('test', [
    'build:test',
    'mochaTest:common',
    'mochaTest:server',
    'connect:test',
    'karma'
  ]);

  grunt.registerTask('build:temp', [
    'clean:all',
    'build:dev'
  ]);

  grunt.registerTask('build', [
    'clean:all',
    'env:test',
    'concurrent:test',
    'browserify',
    'coffee:test',
    'copy:wiredep',
    'wiredep:dev',
    'autoprefixer',
    'env:dist',
    'copy:dist',    // Does not use concurrent:dist as there is only one task here.
    'useminPrepare',
    'concat:generated',
    'cssmin:generated',
    'uglify:generated',
    'filerev',
    'usemin',
    'htmlmin'
  ]);

  grunt.registerTask('build:test', [
    'clean:all',
    'env:test',
    'concurrent:test',
    'browserify',
    'copy:wiredep',
    'wiredep:dev',
    'autoprefixer'
  ]);

  grunt.registerTask('build:dist', [
    'clean:all',
    'env:dist',
    'concurrent:build',
    'browserify',
    'copy:wiredep',
    'wiredep:dev',
    'copy:dist',    // Does not use concurrent:dist as there is only one task here.
    'useminPrepare',
    'autoprefixer',
    'concat:generated',
    'cssmin:generated',
    'uglify:generated',
    'filerev',
    'usemin',
    'htmlmin'
  ]);

  grunt.registerTask('build:dev', [
    'clean:all',
    'env:dev',
    'concurrent:build',
    'browserify',
    'copy:wiredep',
    'wiredep:dev',
    'autoprefixer'
  ]);

  grunt.registerTask('clean-all', [
    'clean:all'
  ]);

  grunt.registerTask('default', [
    'clean:all',
    'build:test',
    'test',
    'build:dist',
    'test'
  ]);
};
