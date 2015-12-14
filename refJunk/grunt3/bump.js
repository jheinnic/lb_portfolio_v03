'use strict';

module.exports = function bump(/*grunt, options*/) {
  return {
    options: {
      files: ['package.json', 'bower.json'],
      updateConfigs: [],
      commit: true,
      commitMessage: 'Release v%VERSION%',
      commitFiles: [
        'CHANGELOG.md',
        'package.json',
        'bower.json',
        '<%= appConfig.dist %>/app.js',
        '<%= appConfig.dist %>/app.min.js',
        '<%= appConfig.dist %>/app_dev_mapped.js',
        '<%= appConfig.dist %>/app_dev_mapped.js.map',
        '<%= appConfig.dist %>/architecture/**/*'
      ],
      createTag: true,
      tagName: 'v%VERSION%',
      tagMessage: 'Version %VERSION%',
      push: false,
      pushTo: 'origin',
      gitDescribeOptions: '--tags --always --abbrev=1 --dirty=-d'
    }
  };
};
