'use strict';

module.exports = function shell(/*grunt, options*/) {
  return {
    options: { stdout: true, async: false },
    'protractor-install': {
      options: { stdout: false, async: true },
      command: 'node node_modules/protractor/bin/webdriver-manager start'
    },
    'bower-install': {
      command: 'bower install'
    },
    'bower-update': {
      command: 'bower update'
    },
    'protractor-update': {
      command: 'node node_modules/protractor/bin/webdriver-manager update'
    },
    'npm-install': {
      command: 'npm install'
    },
    'node-inspector-install': {
      command: 'npm install node-inspector'
    },
    'node-inspector-remove': {
      command: 'npm remove node-inspector'
    },
    'imagemin-jpg': {
      command: 'npm run postinstall',
      options: {
        execOptions: {
          cwd: 'node_modules/jpegtran-bin'
        }
      }
    },
    'imagemin-png': {
      command: 'npm run postinstall',
      options: {
        execOptions: {
          cwd: 'node_modules/optipng-bin'
        }
      }
    },
    examples: {
      command: 'node generate-examples.js'
    }
  };
};
