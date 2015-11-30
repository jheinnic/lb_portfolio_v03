'use strict';

var pkg = require('./utils/pkg');
module.exports = function (grunt, options) {
  function assetMethod(assetType, selfModule, moduleName, assetPath) {
    if (assetPath === undefined) {
      assetPath = moduleName;
      moduleName = selfModule;
    }

    return assetType + '/' + moduleName + '/' + assetPath;
  }

  return {
    play: {
      options: {
        data: function (dest, src) {
          // TODO: Extract the module path from src?
          console.log('Src(', src, ') -> Dest(', dest, ')');
          var selfModule = 'myself';

          return {
            viewAsset: _.partial(assetMethod, 'views', selfModule),
            imageAsset: _.partial(assetMethod, 'images', selfModule),
            styleAsset: _.partial(assetMethod, 'styles', selfModule),
            fontAsset: _.partial(assetMethod, 'fonts', selfModule)
          };
        }
      },
      files: {
        '<%= appConfig.dev %>/play.html': '<%= appConfig.app %>/play.jade'
      }
    },
    dev: {
      options: {
        data: function (dest, src) {
          return {
            isDevelopment: true,
            // stagedScripts: stagedScripts,
            stagedStyles: stagedStyles,
            method: function() { return 'Ah-hah!' }
          };
        }
      },
      files: {
        '<%= appConfig.dev %>/index.html': '<%= appConfig.app %>/index.jade'
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
        '<%= appConfig.dist %>/index.html': '<%= appConfig.app %>/index.jade'
      }
    }
  };
};
