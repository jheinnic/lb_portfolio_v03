class Foo
  bbb = 'west'
  innerA = undefined

  constructor: () ->
    ooo = 5
    innerA = () -> return ooo;

    Object.defineProperties( @, {
      aoa: {
        get: () -> return ooo,
        set: (val) -> ooo = val; return
      }
      bob: {
        get: () -> return bbb,
        set: (val) -> bbb = val; return
      }
    })

  # innerA = () ->
  #   return ooo;

  outerA1: () ->
    return innerA()

  outerA2: () =>
    return innerA()

module.exports = Foo
