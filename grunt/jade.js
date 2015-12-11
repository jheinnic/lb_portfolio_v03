'use strict';

var pkg = require('./utils/pkg');
module.exports = function jade(grunt, options) {
  //function assetMethod(assetType, selfModule, moduleName, assetPath) {
  //  if (assetPath === undefined) {
  //    assetPath = moduleName;
  //    moduleName = selfModule;
  //  }
  //
  //  return assetType + '/' + moduleName + '/' + assetPath;
  //}

  return {
    //play: {
    //  options: {
    //    data: function (dest, src) {
    //      // TODO: Extract the module path from src?
    //      console.log('Src(', src, ') -> Dest(', dest, ')');
    //      var selfModule = 'myself';
    //
    //      return {
    //        viewAsset: _.partial(assetMethod, 'views', selfModule),
    //        imageAsset: _.partial(assetMethod, 'images', selfModule),
    //        styleAsset: _.partial(assetMethod, 'styles', selfModule),
    //        fontAsset: _.partial(assetMethod, 'fonts', selfModule)
    //      };
    //    }
    //  },
    //  files: {
    //    '<%= appConfig.dev %>/play.html': '<%= appConfig.app %>/play.jade'
    //  }
    //},
    dev: {
      options: {
        data: { isDev: true }
        //data: function (dest, src) {
        //  return { isDev: true };
        //}
      },
      files: {
        cwd: '<%= appConfig.app %>',
        src: ['**/*.jade'],
        expand: true, ext: '.html', extDot: 'last',
        dest: '<%= appConfig.dev %>/client'
      }
    },
    dist: {
      options: {
        data: { isDev: false }
      },
      files: {
        cwd: '<%= appConfig.app %>',
        src: ['**/*.jade'],
        expand: true, ext: '.html', extDot: 'last',
        dest: '<%= appConfig.dist %>/client'
      }
    }
  };
};
