class ModelObject
  @isSubtypeOf: (typeQuery) ->
    throw new Error("typeQuery argument must be defined") unless typeQuery?
    return ModelObject::.isPrototypeOf(typeQuery::)

  @values = {
    numbers: [1, 2, 3, 4, 5]
    letters: ['a', 'b', 'c']
    fruits: ['apple', 'banana', 'grape', 'orange']
  }

  @traverseA: () ->
    yield {
      type: key,
      values:  () ->
        yield value for value in @values[key]
        return
    } for key in Object.keys(@values)
    return

  @traverseB: () ->
    yield {
      type: key,
      values:  (() ->
        yield item for item in items
        return
      )()
    } for key,items of @values
    return

foo = 'bar'

switch foo
  when 'bar'
    console.log('is bar')
  when 'baz'
    console.log('is baz')
  else
    console.log('is not known')

console.log("Collection: #{outer.type}, Item: #{item}") for item in outer.items for outer in ModelObject.traverseB()

outer = ModelObject.traverseB()
while inner = outer.next()
  while item = inner.values.next()
    console.log("Collection: #{inner.type}, Item: #{item}")


module.exports = ModelObject
