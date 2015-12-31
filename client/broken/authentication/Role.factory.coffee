###*
# Created by John on 12/20/2015.
###
module.exports = ['Enum', (Enum) ->
  class Role extends Enum

  new Role('GUEST')
  new Role('USER')
  new Role('ADMIN')

  return Role
]
