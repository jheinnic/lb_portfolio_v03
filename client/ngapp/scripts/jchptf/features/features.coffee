this.angular.module(
  'jchptf.site.home'
  ['ng', 'ui.bootstrap', 'ui.router']
)

# Foo.$inject = ['$state']

class Foo extends ScopeBase
  @inject '$rootScope'

  f(n) -> return 5

  g(n) -> this.$rootScope.fa

appModule.factory('Foo', Foo)
