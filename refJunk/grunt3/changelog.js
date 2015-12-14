'use strict';

module.exports = function changelog(/*grunt, options*/) {
  var Semver = require('semver'),
    version  = require('../package.json').version;

  return {
    patch: {
      options:{
        version: new Semver(version).inc('patch').version
      }
    },
    minor: {
      options:{
        version: new Semver(version).inc('minor').version
      }
    },
    major: {
      options:{
        version: new Semver(version).inc('major').version
      }
    },
    prepatch: {
      options:{
        version: new Semver(version).inc('prepatch', 'rc').version
      }
    },
    preminor: {
      options:{
        version: new Semver(version).inc('premajor', 'rc').version
      }
    },
    premajor: {
      options:{
        version: new Semver(version).inc('premajor', 'rc').version
      }
    },
    prerelease: {
      options:{
        version: new Semver(version).inc('prerelease', 'rc').version
      }
    }
  };
};
