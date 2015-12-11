'use strict';

// Construct grunt-dispatch configuration block by filesystem traversal for discovery.

module.exports = function(grunt, options) {
  return sourceBlocks.dispatch;

  //var path = require('path'),
  //    traverse = require('../common/components/fsutil/traverse'),
  //    devClient = appConfig.dev + '/client',
  //    dispatch = {
  //      options: {
  //        directory: appConfig.app
  //      }
  //    },
  //    moduleBlock = [
  //      {src: '*.{js,coffee}'},
  //      {cwd: 'assets/views', src: '**/*.{html,jade}'},
  //      {cwd: 'assets/styles', src: '**/*.{css,sass}'},
  //      {cwd: 'assets/images', src: '**/*'},
  //      {cwd: 'assets/fonts', src: '**/*'}
  //    ];
  //
  //function appendModulePath(modulePath, contentItem) {
  //  return {
  //    cwd: contentItem.cwd, src: contentItem.src, dest: path.join(devClient, contentItem.cwd || 'scripts', modulePath)
  //  };
  //}
  //
  //function findModules(dispatchCfg, moduleBlock, filePath) {
  //  var parsedPath = path.parse(filePath);
  //  if (parsedPath.base === 'module.js') {
  //    var modulePath = path.relative(appConfig.app, parsedPath.dir);
  //    dispatchCfg[modulePath] = {
  //      use: moduleBlock.map(
  //        _.partial(appendModulePath, modulePath)
  //      )
  //    };
  //  }
  //
  //  return true;
  //}
  //
  //function pruneAssets(dirPath) {
  //  return path.basename(appConfig.app, dirPath) !== 'assets';
  //}
  //
  //traverse(
  //  path.dirname(appConfig.appMain),
  //  _.partial(findModules, dispatch, moduleBlock),
  //  pruneAssets
  //);
  //
  // return dispatch;
};

