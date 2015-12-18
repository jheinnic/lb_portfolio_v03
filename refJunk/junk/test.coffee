class Test
  one: 1
  two: 2
  three: 3

  constructor: () ->
    [@one, @two, @three] = [1, 2, 3]
    

class Input
  constructor: (test) ->
    {@one, @two, @three} = test

class Shuffle
  constructor: (test) ->
    {@three, @two, @one} = test

exports.Test = Test
exports.Input = Input
exports.Shuffle = Shuffle

