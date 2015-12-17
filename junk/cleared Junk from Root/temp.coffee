class Foo
  @aa = -4
  @vv = -1
  @ww = 5
  @xx = 0
  @yy = 3
  @zz = -2

  mm = 'm'
  nn = 'n'
  oo = 'o'
  pp = 'p'
  qq = 'q'

  @t1: 't'
  t2: 't'

  constructor: () ->
    @a = 4
    @b = -2
    @d = 1
    ww = -3
    vv = 3
    @m = mm
    nn = 1
    oo = 2
    @p = pp
    @q = qq

  log1: () ->
    console.log("#{mm || 'no'} #{nn || 'no'} #{oo || 'no'} #{pp || 'no'} #{qq || 'no'}")

  log2: () =>
    console.log("#{mm || 'no'} #{nn || 'no'} #{oo || 'no'} #{pp || 'no'} #{qq || 'no'}")

  @log3: () ->
    console.log("#{mm || 'no'} #{nn || 'no'} #{oo || 'no'} #{pp || 'no'} #{qq || 'no'}")

  @log4: () =>
    console.log("#{mm || 'no'} #{nn || 'no'} #{oo || 'no'} #{pp || 'no'} #{qq || 'no'}")


class Bar extends Foo
  @cc = 3
  @xx = 2

  constructor: () ->
    @b = 1
    vv = 2
    # @n = nn
    pp = -1
    super()
    qq = 3
    # @o = oo
    @a = 3
    @c = 0
    yy = 4

exports.Foo = Foo
exports.Bar = Bar
