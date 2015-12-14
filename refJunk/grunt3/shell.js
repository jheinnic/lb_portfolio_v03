'use strict';

module.exports = function shell(/*grunt, options*/) {
  return {
    options: {
      stdout: true,
      async: false
    },
    'protractor-install': {
      options: {
        stdout: false,
        async: true
      },
      command: 'node node_modules/protractor/bin/webdriver-manager start'
    },
    'protractor-update': {
      command: 'node node_modules/protractor/bin/webdriver-manager update'
    },
    'npm-install': {
      command: 'npm install'
    },
    'npm-update': {
      command: 'npm update'
    },
    examples: {
      command: 'node generate-examples.js'
    }
  };
};
