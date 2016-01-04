(function () {
  'use strict';

  module.exports = JchNavData;
  JchNavData.$inject = ['$q'];


  /**
   * @type {lodash}
   */
  var _ = require('lodash');


  /**
   * @type {$q}
   */
  var $q;

  /**
   * @constructor
   */
  var NavBarBuilder = require('./NavBarBuilder.class');

  /**
   * JchNavData is a service singleton and so uses class-level variables (e.g. var foo; outside of
   * constructor) in lieu of either instance fields (e.g. this.foo; inside of constructor) or
   * hidden instance variables (e.g. var foo; inside of constructor with Object.defineProperties to
   * create getter/setter methods.
   *
   * @constructor
   */
  function JchNavData(_$q) {
    $q = _$q;

    var updateHandle = $q.defer();
    var nbDataModel = new NavBarBuilder(
    ).brandName(
      'John Heinnickel'
    ).appendRouteTab(
      'Home', '/home', /^\/home$/
    ).appendRouteTab(
      'Crosswords', '/crosswords', /^\/crosswords$/
    ).appendRouteTab(
      'Poker', '/poker/odds', /^\/poker\/odds$/
    ).build(updateHandle.promise);

    Object.defineProperties(
      this, {
        _nbDataModel: {
          get: function () {
            return nbDataModel;
          },
          set: function (newModel) {
            nbDataModel = newModel;
          }
        },
        _updateHandle: {
          get: function () {
            return updateHandle;
          },
          set: function (newHandle) {
            updateHandle = newHandle;
          }
        }
      }
    );

    return Object.freeze(this);
  }

  JchNavData.prototype.getNavBarModel = function getNavBarModel() {
    console.log('getNBModel', this);
    return this._nbDataModel;
  };

  JchNavData.prototype.changeNavBarModel = function changeNavBarModel(changeDirector) {
    if (!(_.isFunction(changeDirector))) {
      var msg = 'changeDirector argument must be a function, but got ' + changeDirector + ' instead';
      console.err(msg);
      throw new Error(msg);
    }

    var navBarBuilder = new NavBarBuilder(this._nbDataModel);
    changeDirector(navBarBuilder);

    if (navBarBuilder.hasChanges()) {
      var lastDefer = this._updateHandle;
      this._updateHandle = $q.defer();
      this._nbDataModel = navBarBuilder.build(this._updateHandle.promise);
      return lastDefer.resolve(this._nbDataModel);
    }
  };
}).call(this);
