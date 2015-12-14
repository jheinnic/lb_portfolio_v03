'use strict';

module.exports = function jade(/*grunt, options*/) {
  var fs         = require('fs'),
      path       = require('path'),
      _          = require('lodash'),
      traverse   = require('./utils/traverse'),
      indexSrc   = path.join(global.appConfig.app, 'index.jade'),
      configObj = {
        dev: {
          options: {
            data: {
              isDev: true,
              titleStr: 'This is a title from data'
            }
          },
          files: { }
        },
        dist: {
          options: {
            data: {
              isDev: false,
              titleStr: 'This is a title from data'
            }
          },
          files: { }
        }
      };

  // Traversal won't find index.html since we start at the application root to avoid sifting
  // through bower content and test cases.
  if (fs.existsSync(indexSrc)) {
    configObj.dev.files[
      path.join(global.appConfig.dev, 'client', 'index.html')
    ] = indexSrc;
    configObj.dist.files[
      path.join(global.appConfig.dist, 'client', 'index.html')
    ] = indexSrc;
  }

  function findTemplates(
    devDir, devFiles, distDir, distFiles,
    jadeRegexp, srcDirRegexp, srcFilePath )
  {
    if (jadeRegexp.test(srcFilePath)) {
      // Normalize the source path so regular expressions derived from the input path arguments
      // work even after fs traversal has replaced some '\\' separators with '/'.
      var normSrcPath = path.normalize(srcFilePath);

      // Replace '.jade' suffix of input template path argument with '.html' suffix of output path.
      var normDestPath = normSrcPath.replace(jadeRegexp, '.html');

      // Create the dev and dist map entries by replacing the source root directory with each of
      // dev and dist root directories.
      devFiles[normDestPath.replace(srcDirRegexp, devDir)] = normSrcPath;
      distFiles[normDestPath.replace(srcDirRegexp, distDir)] = normSrcPath;
    }
  }

  // TODO: Avoid hard coding the application's root module name here!!
  // NOTE: Be aware of the hockey stick magic needed to account for slash escaping when
  //       building the regexp for source file path.  We need to inject a second layer of
  //       escaping because we passing the string as argument to the RegExp constructor
  //       causes its escapes to get resolved one additional time.
  traverse(
    path.join(global.appConfig.app, 'jchptf'),
    _.partial(
      findTemplates,
      path.join(global.appConfig.dev, 'client'), configObj.dev.files,
      path.join(global.appConfig.dist, 'client'), configObj.dist.files,
      /\.jade$/,
      new RegExp('^' + path.normalize(global.appConfig.app).replace(/\\/, '\\\\'))
    )
  );

  return configObj;
};
