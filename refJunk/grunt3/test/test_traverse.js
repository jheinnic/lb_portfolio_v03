/**
 * Created by John on 5/9/2015.
 */
'use strict';

var path      = require('path'),
    rootNodes = path.resolve('../..'),
    traverse  = require('../utils/traverse');

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
