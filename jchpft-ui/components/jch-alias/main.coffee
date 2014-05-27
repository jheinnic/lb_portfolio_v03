define [
  'angular'
], () ->
  angular.module 'jch-alias', ['ng']

  angular.factory 'jhAliasDirective', () ->
    return (srcDirName, aliasDirName, restrictProp) ->
      if restrictProp = 'C'
        retVal =
          restrict: 'C'
          replace: true
          scope: false
          priority: 1200
          template: ($element) ->
            clone = $element.clone()
            clone.addClass(srcDirName).removeClass(aliasDirName)
            return angular.element('<div></div>').append(clone).html()
      else if restrictProp = 'E'
        retVal =
          restrict: 'E'
          replace: true
          scope: false
          priority: 1200
          template: ($element, $attrs) ->
            clone = '<' + srcDirName + '></' + srcDirName + '>'
            clone.attr name, $element.attr(name) for name in $element.attr
            clone.addClass name for name in $element.class
            clone.appendTo $element.children
            return angular.element('<div></div>').append(clone).html()
      else if restrictProp = 'A'
        retVal =
          restrict: 'A'
          replace: true
          scope: false
          priority: 1200
          template: ($element) ->
            clone = $element.clone()
            clone.attr(srcDirName, attr[aliasDirName]).removeAttr(aliasDirName)
            return angular.element('<div></div>').append(clone).html()
      else
        throw "Illegal Argument: Unsupported restrict property value of " + restrictProp

      return retVal
