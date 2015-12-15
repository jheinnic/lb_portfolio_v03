/**
 *
 * Created by John on 5/9/2015.
 */

traverse = require('./traverse');

function onFile(currentFile) {
  console.log('File:', currentFile);
}

function onDirectory(currentDirectory, isBeforeEntry) {
  if (isBeforeEntry) {
    console.log('Entering:', currentDirectory);
  } else {
    console.log('Exiting:', currentDirectory);
  }
}

var path = require('path');
var rootNodes = path.resolve('../..');

console.log('\nFiles only, denormalized:');
traverse(rootNodes, onFile);

console.log('\nDirectories only, denormalized:');
traverse(rootNodes, undefined, onDirectory);

console.log('\nAll nodes, denormalized:');
traverse(rootNodes, onFile, onDirectory);

console.log('\nFiles only, normalized:');
traverse(rootNodes, onFile, undefined, true);

console.log('\nDirectories only, normalized:');
traverse(rootNodes, undefined, onDirectory, true);

console.log('\nAll nodes, normalized:');
traverse(rootNodes, onFile, onDirectory, true);