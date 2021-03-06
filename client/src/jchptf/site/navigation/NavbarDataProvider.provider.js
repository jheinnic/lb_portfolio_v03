(function (angular, sprintf, console) {
  'use strict';

  module.exports = NavbarDataProvider;

  var _ = require('lodash');

  function NavbarDataProvider() {
    // Provider is a singleton and (ab)uses context variables to implement private state.
    // Provider's private state is shared with the singleton service instance it spawns
    // by co-location within this source file's isolated namespace.

    var davMeta = {
      brandName: 'John Heinnickel',
      defaultTab: undefined,
      tabsById: {},
      tabModels: []
    };
    //var temp = [
    //    {
    //      tabId: 'home',
    //      displayName: 'Home',
    //      rankOrder: 1,
    //      srefTarget: 'site.homepage'
    //    },
    //    {
    //      tabId: 'xw',
    //      displayName: 'Crosswords',
    //      rankOrder: 2,
    //      srefTarget: 'site.crosswords'
    //    }
    //  ];

    function compareTabRanks(tabOne, tabTwo) {
      if (tabOne.rankOrder > tabTwo.rankOrder) {
        return 1;
      } else if (tabOne.rankOrder < tabTwo.rankOrder) {
        return -1;
      }
      return 0;
    }

    return Object.freeze(
      {
        get brandName() {
          return davMeta.brandName;
        },

        set brandName(value) {
          davMeta.brandName = value;
        },

        setBrandName: function setBrandName(brandName) {
          davMeta.brandName = brandName;
        },

        addFeatureTab: function addFeatureTab(tabId, displayName, rankOrder, stateName, stateParams) {
          if (davMeta.tabsById.hasOwnProperty(tabId)) {
            if (davMeta.defaultTab === davMeta.tabsById[tabId]) {
              davMeta.defaultTab = undefined;
            }
            console.err('Warning: duplicate redefinition of nav tab previously marked as default!');
          }

          davMeta.tabsById[tabId] = {
            tabId: tabId,
            displayName: displayName || tabId,
            rankOrder: rankOrder || Number.MAX_VALUE,
            stateName: stateName,
            srefTarget: _.isObject(stateParams) ? sprintf('%s(%s)', stateName, JSON.stringify(stateParams)) : stateName
          };
        },

        setDefaultTabId: function setDefaultTabId(defaultTabId) {
          var newDefault = davMeta.tabsById[defaultTabId];
          var retVal = _.isObject(newDefault);

          if (retVal) {
            if (_.isObject(davMeta.defaultTab)) {
              console.warn(
                sprintf(
                  'Previously defined default tab with id=%s overriden by tab with id=%s',
                  davMeta.defaultTab.tabId, defaultTabId
                )
              );
            }
            davMeta.defaultTab = newDefault;
          } else {
            console.warn(
              sprintf('Cannot set default navigation tab to non-existent tab id=%s', defaultTabId)
            );
          }

          return retVal;
        },

        $get: function $get() {
          var tabId;
          for (tabId in davMeta.tabsById) {
            davMeta.tabModels.push(
              Object.freeze(
                davMeta.tabsById[tabId]));
          }
          davMeta.tabModels.sort(compareTabRanks);

          if (_.isUndefined(davMeta.defaultTab) && (davMeta.tabModels.length > 0)) {
            davMeta.defaultTab = davMeta.tabModels[0];
          }
          davMeta = Object.freeze(davMeta);

          NavbarData.$inject = ['$state'];
          return NavbarData;
        }
      }
    );

    /**
     * NavbarData is a service singleton and so uses class-level variables (e.g. var foo; outside of
     * constructor) in lieu of either instance fields (e.g. this.foo; inside of constructor) or
     * hidden instance variables (e.g. var foo; inside of constructor with Object.defineProperties to
     * create getter/setter methods.
     *
     * @constructor
     */
    function NavbarData() {
      /**
       * @type {$state}
       */
      var activeTab = davMeta.defaultTab;

      return Object.freeze(
        {
          get navMeta() {
            return davMeta;
          },
          get activeTabId() {
            return activeTab.tabId;
          },
          set activeTabId(nextTabId) {
            activeTab = nextTabId;
          }
        }
      );
    }
  }
}).call(window, window.angular, window.sprintf, window.console);
