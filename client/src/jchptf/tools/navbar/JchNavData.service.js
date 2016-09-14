(function () {
  'use strict';

  module.exports = JchNavData;
  JchNavData.$inject = ['$q', '$state'];


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
  function JchNavData(_$q, _$state ) {
    /**
     * @type {$state}
     */
    var $state = _$state;
    var updateHandle = _$q.defer();
    var nbDataModel = new NavBarBuilder( {}, _$state
    //).appendRouteTab(
    //  'Poker', '/poker/odds', /^\/poker\/odds$/
    ).build(updateHandle.promise);

    $q = _$q;

    Object.defineProperties(
      this, {
        _nbDataModel: {
          get: function getDataModel() {
            return nbDataModel;
          },
          set: function setDataModel(newModel) {
            nbDataModel = newModel;
          }
        },
        _updateHandle: {
          get: function getUpdateHandle() {
            return updateHandle;
          },
          set: function setUpdateHandle(newHandle) {
            updateHandle = newHandle;
          }
        },
        _state: {
          get: function getState() {
            return $state;
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

    var navBarBuilder = new NavBarBuilder(this._nbDataModel, this._state);
    changeDirector(navBarBuilder);

    if (navBarBuilder.hasChanges()) {
      var lastDefer = this._updateHandle;
      this._updateHandle = $q.defer();
      this._nbDataModel = navBarBuilder.build(this._updateHandle.promise);
      return lastDefer.resolve(this._nbDataModel);
    }
  };
}).call(window);
