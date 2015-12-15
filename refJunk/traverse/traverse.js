var fs = require('fs');
var path = require('path');

/**
 * Traverses a directory structure by depth first search and optionally calls file and directory
 * callback handlers as it finds items of each respective type.  The directory handler is called
 * twice--once before descending into a directory, then once again on returning from it.  A boolean
 * argument is provided to the directory callback in order to distinguish between those two types
 * of calls.
 *
 * NOTE: Traversal is done synchronously by this utility method.
 * NOTE: Traversal is done recursively.  This could be re-written to use a stack instead.
 *
 * @param currentPath {string)}
 * @param fileCallback {(string)->void}
 * @param dirCallback {(string)->void}
 * @param isNormalized {boolean} Set to true to get paths as normalized by
 *                     path.normalize()
 */
function traverseFileSystemSync (rootDirectory, fileCallback, dirCallback, isNormalized) {
  // To use this with Grunt, denormalized paths are required.
  var _appendToPath;
  if (isNormalized) {
    _appendToPath =
      function _appendToPath(currentPath, dirItem) {
        return path.join(currentPath, dirItem);
      };
  } else {
    _appendToPath =
      function _appendToPath(currentPath, dirItem) {
        return currentPath + '/' + dirItem;
      };
  }

  if (dirCallback) {
    dirCallback(rootDirectory, true);

    if (fileCallback) {
      _doTraversal(
        rootDirectory,
	_curryBothIterator(fileCallback, dirCallback, _appendToPath),
	_appendToPath
      );
    } else {
      _doTraversal(
        rootDirectory,
        _curryDirectoryIterator(dirCallback, _appendToPath),
        _appendToPath
      )
    }

    dirCallback(rootDirectory, false);
  } else if(fileCallback) {
    _doTraversal(
      rootDirectory,
      _curryFileIterator(fileCallback, _appendToPath),
      _appendToPath
    )
  } else {
    console.log(
      "Saving time by skipping requested traversal without callbacks" ,
      rootDirectory
    );
  }
}

function _curryFileIterator(fileCallback, _appendToPath) {
  var _iterateNextEntry = function _iterateNextEntry(currentPath) {
    var stats = fs.statSync(currentPath);

    if (stats.isFile()) {
      fileCallback(currentPath);
    } else if (stats.isDirectory()) {
      _doTraversal(currentPath, _iterateNextEntry, _appendToPath);
    } else {
      console.debug("No traversal handling for non-file, non-directory nodes.", stats);
    }
  }

  return _iterateNextEntry;
}

function _curryDirectoryIterator(dirCallback, _appendToPath) {
  var _iterateNextEntry = function _iterateNextEntry(currentPath) {
    var stats = fs.statSync(currentPath);
    var test = true;

    if (stats.isDirectory()) {
      test = dirCallback(currentPath, true);
      if( test ) {
      	_doTraversal(currentPath, _iterateNextEntry, _appendToPath);
      }
      dirCallback(currentPath, false);
    } else if (! stats.isFile()) {
      console.debug("No traversal handling for non-file, non-directory nodes.", stats);
    }
  }

  return _iterateNextEntry;
}

function _curryBothIterator(fileCallback, dirCallback, _appendToPath) {
  var _iterateNextEntry = function _iterateNextEntry(currentPath) {
    var stats = fs.statSync(currentPath);
    if (stats.isFile()) {
      fileCallback(currentPath);
    }
    else if (stats.isDirectory()) {
      test = dirCallback(currentPath, true);
      if (test) {
      	_doTraversal(currentPath, _iterateNextEntry, _appendToPath);
      }
      dirCallback(currentPath, false);
    } else {
      console.debug("No traversal handling for non-file, non-directory nodes.", stats);
    }
  }

  return _iterateNextEntry;
}

function _filterSelfParent(dirItem) {
  return (dirItem != '.') && (dirItem != '..');
}

function _doTraversal(currentPath, _iterateNextEntry, _appendToPath) {
  fs.readdirSync(currentPath)
    .filter(_filterSelfParent)
    .map(function(dirItem) { return _appendToPath(currentPath, dirItem); })
    .forEach(_iterateNextEntry);
}

module.exports = traverseFileSystemSync;
