module.exports = ExportRole

class ExportRole
  constructor: (params) ->
    {@roleName, @uuidList} = params
    Object.seal(@)

  includeUuid: (uuid) =>
    throw new Error("Uuid may not be null or undefined") unless uuid?
    if uuid in @uuidList
      console.warn "Suspiciously redundant request to export #{uuid} under #{@roleName}"
    else
      @uuidList.push uuid

  removeUuid: (uuid) =>
    throw new Error("Uuid must be defined") unless uuid?

    lenDiff = @uuidList.length - _.pull(@uuidList, uuid).length
    if lenDiff <= 0
      console.warn "Suspiciously vacuous request to stop exporting unexported #{uuid} under #{@roleName}"
    else if lenDiff > 1
      console.error "Request to stop exporting #{uuid} under #{@roleName} removed #{lenBefore - lenAfter} values!!!"

  getUuidList: =>
    return _.clone(@uuidList)
