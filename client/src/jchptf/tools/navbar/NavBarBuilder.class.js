(function() {
  'use strict';

  module.exports = NavBarBuilder;

  var _ = require('lodash');
  var NavBarModel = require('./NavBarModel.class');
  var StateTabModel = require('./StateTabModel.class');
  var RouteTabModel = require('./RouteTabModel.class');

  function NavBarBuilder(model, _state) {
    var brandName, changed, tabModels;
    var $state = _state;
    changed = false;
    if (_.isObject(model)) {
      brandName = model.brandName || '';
      tabModels = _.isObject(model.tabModels) ? _.clone(model.tabModels) : [];
    } else {
      brandName = '';
      tabModels = [];
    }

    Object.defineProperties(this, {
      _changed: {
        get: function hasChanged() {
          return changed;
        },
        set: function setChanged(nextValue) {
          changed = nextValue;
        }
      },
      _brandName: {
        get: function getBrandName() {
          return brandName;
        },
        set: function setBrandName(nextName) {
          brandName = nextName;
        }
      },
      _tabCount: {
        get: function getTabCount() {
          return tabModels.length;
        }
      },
      _tabModels: {
        get: function getTabModels() {
          return tabModels;
        }
      },
      _state: {
        get: function getState() {
          return $state;
        }
      }
    });
  }

  NavBarBuilder.prototype.hasChanges = function hasChanges() {
    return this._changed;
  };

  NavBarBuilder.prototype.brandName = function brandName(nextName) {
    var retVal;
    console.log(this);
    if (_.isString(nextName)) {
      retVal = this;
      if (this._brandName !== nextName) {
        this._brandName = nextName;
        this._changed = true;
      }
    } else {
      retVal = this._brandName;
    }

    return retVal;
  };

  NavBarBuilder.prototype.removeTabByName = function removeTabByName(displayLabel) {
    var matchDisplayName = _.matchesProperty('displayLabel', displayLabel);
    var removed = _.remove(this._tabModels, matchDisplayName);

    if (removed.length > 0) {
      this._changed = true;
    } else {
      throw new Error('No such tab named ' + displayLabel);
    }

    return this;
  };

  NavBarBuilder.prototype.addRouteTab = function addRouteTab(index, displayLabel, clickRoute, matchRoute) {
    _addRouteTab(this, index, displayLabel, clickRoute, matchRoute);
    return this;
  };

  NavBarBuilder.prototype.addStateTab = function addStateTab(index, displayLabel, clickState, matchRoute) {
    _addStateTab(this, index, displayLabel, clickState, matchRoute);
    return this;
  };

  NavBarBuilder.prototype.appendRouteTab = function appendRouteTab(displayLabel, clickRoute, matchRoute) {
    _addRouteTab(this, this._tabCount, displayLabel, clickRoute, matchRoute);
    return this;
  };

  NavBarBuilder.prototype.appendStateTab = function appendStateTab(displayLabel, clickState, matchRoute) {
    _addStateTab(this, this._tabCount, displayLabel, clickState, matchRoute);
    return this;
  };

  NavBarBuilder.prototype.prependRouteTab = function prependRouteTab(displayLabel, clickRoute, matchRoute) {
    _addRouteTab(this, 0, displayLabel, clickRoute, matchRoute);
    return this;
  };

  NavBarBuilder.prototype.prependStateTab = function prependStateTab(displayLabel, clickState, matchRoute) {
    _addStateTab(this, 0, displayLabel, clickState, matchRoute);
    return this;
  };

  function _addRouteTab(self, index, displayLabel, clickRoute, matchRoute) {
    var newTab = new RouteTabModel({
      displayLabel: displayLabel,
      clickRoute: clickRoute,
      matchRoute: matchRoute
    });

    _addTab(self, index, newTab);
  }

  function _addStateTab(self, index, displayLabel, clickState) {
    var newTab = new StateTabModel({
      displayLabel: displayLabel,
      clickState: clickState,
      $state: self._state
    });

    _addTab(self, index, newTab);
  }

  function _addTab(self, index, newTab) {
    if (index > self._tabCount) {
      throw new Error('Index (' + index + ') is beyond the end of the current tab list.  Max index = ' + self._tabCount);
    } else if (index < 0) {
      throw new Error('Index (' + index + ') must be non-negative.');
    }

    self._tabModels.push(newTab);
    if (index < self._tabCount) {
      self._tabModels.copyWithin(index + 1, index);
      self._tabModels[index] = newTab;
    }

    self._changed = true;
  }

  NavBarBuilder.prototype.build = function build(refreshPromise) {
    return new NavBarModel({
      brandName: this._brandName,
      tabModels: this._tabModels,
      refreshPromise: refreshPromise
    });
  };
}).call(this);
