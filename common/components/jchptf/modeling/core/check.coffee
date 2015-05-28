Enum = require('./Enum.class.coffee')

console.log(Enum)

console.log('b4')

class Check extends Enum
  constructor: (name) ->
    super(name)

  console.log('b5')
  FOO = new Check('FOO')
  console.log('b5')
  BAR = new Check('BAR')
  BAZ = new Check('BAZ')
console.log('b6')
Check.finalize()
console.log('b7')

module.exports = Check

