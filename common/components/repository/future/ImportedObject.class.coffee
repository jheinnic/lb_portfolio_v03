module.exports = ImportedObject

# TODO: Validate adding @importPurpose an optional property, since in most cases the reason for exporting
#       an object implies the reason for importing it.  The exception to that rule of thumb is the possibility
#       that one reason for exporting may map to multiple reasons for importing.  E.g. exporting for "use in hand"
#       could be compatible with importing for "use in left hand" and also "use in right hand".
# TODO: The following supports direct Proxying, e.g. copy without change of type.
class ImportedObject
  constructor: (params) ->
    {@localUuid, @foreignUuid, @exportRole} = params
