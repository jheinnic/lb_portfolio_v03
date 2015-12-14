'use strict';

module.exports = createGruntBlock;

var _         = require('lodash'),
    path      = require('path'),
    traverse  = require('./traverse'),
    devClient = '<%= appConfig.dev %>/client';

function createGruntBlock(appConfig) {
  var pluginBlocks = {
    dispatch: {
      options: {
        directory: '<%= appConfig.app %>'
      }
    }
  };
  var contextObjects = {
    dispatch: [
      { src: '*.{js,coffee}' },
      { cwd: 'assets/views', src: '**/*.{html,jade}' },
      { cwd: 'assets/styles', src: '**/*.{css,sass}' },
      { cwd: 'assets/images', src: '**/*'},
      { cwd: 'assets/fonts', src: '**/*'}
    ]
  };

  function createDispatchTarget(modulePath, ruleTemplate) {
    return {
      cwd: ruleTemplate.cwd,
      src: ruleTemplate.src,
      dest: path.join(devClient, ruleTemplate.cwd || 'scripts', modulePath)
    };
  }

  function findModules(appConfig, pluginBlocks, contextObjects, filePath) {
    var parsedPath = path.parse(filePath);
    if (parsedPath.base === 'module.js') {
      var modulePath = path.relative(appConfig.app, parsedPath.dir);
      pluginBlocks.dispatch[modulePath] = {
        use: contextObjects.dispatch.map(
          _.partial(createDispatchTarget, modulePath)
        )
      };
    }
  }

  function pruneAssets(dirPath) {
    return path.basename(appConfig.app, dirPath) !== 'assets';
  }

  traverse(
    path.dirname(appConfig.appMain), _.partial(findModules, appConfig, pluginBlocks, contextObjects), pruneAssets
  );

  function lookupPluginBlock(pluginName) {
    return pluginBlocks[pluginName];
  }

  return lookupPluginBlock;
}

