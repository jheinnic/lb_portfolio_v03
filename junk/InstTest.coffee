class Instance 
  constructor: (@name) ->
    Class = @constructor
    console.log(Class)
    console.log(Class[@name])
    console.log(Class[@name] == @)

module.exports = Instance
