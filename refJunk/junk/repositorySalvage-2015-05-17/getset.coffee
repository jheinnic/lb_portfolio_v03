class GetSet
  _prop = 0

  constructor: () ->
    get prop: () -> return _prop
    set prop: (x) -> _prop = x

