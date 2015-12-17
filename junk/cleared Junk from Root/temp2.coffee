class Foo
  constructor: () ->
    a = 2
    @b = 2
    @:: = 
      d: 2
      e: 2
    @::c = 2
    # @::f: 2

  seeOne: () ->
    console.log( "a: 'NO', b: #{@b||'NO'}, c: #{@::c||'NO'}, d: 'NO', e: #{@e||'NO'}")
  
  seeTwo: () =>
    console.log( "a: #{a||'NO'}, b: #{@b||'NO'}, c: #{@::c||'NO'}, d: #{d||'NO'}, e: #{@e||'NO'}")

  @seeThree: () ->
    console.log( "a: #{'NO'}, b: #{@b||'NO'}, c: #{@::c||'NO'}, d: #{d||'NO'}, e: #{@e||'NO'}")
  
  @seeFour: () =>
    console.log( "a: #{a||'NO'}, b: #{@b||'NO'}, c: #{@::c||'NO'}, d: #{d||'NO'}, e: #{@e||'NO'}")

exports.Foo = Foo

f = new Foo()
f.seeOne()
f.seeTwo()
Foo.seeThree()
Foo.seeFour()

