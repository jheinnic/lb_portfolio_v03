###*
# Created by John on 12/20/2015.
###
module.exports = ['Enum', (Enum) ->
  class IdentityCheckResult extends Enum

  new IdentityCheckResult('IDENTIFIED')
  new IdentityCheckResult('NO_COOKIE')
  new IdentityCheckResult('DECODE_ERROR')
  new IdentityCheckResult('CONTENT_ERROR')
  new IdentityCheckResult('EXPIRED')
  new IdentityCheckResult('REVOKED')
  new IdentityCheckResult('INTERNAL_ERROR')

  return IdentityCheckResult
]
