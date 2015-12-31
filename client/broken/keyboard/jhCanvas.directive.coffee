'use strict'

module.exports = [
  ->
    retVal =
      restrict: 'A'
      controller: [
        '$scope', '$element', '$attrs', 'keypressHelper', '$parse', ($scope, $element, $attrs, keypressHelper, $parse) ->
          defaultKeyboardControlState =
            stateName: 'notControlled'
            onEnter: angular.noop
            onExit: angular.noop
            eventHandler: null
          registeredElementControlStates =
            default: defaultKeyboardControlState
          currentKeyboardControlState = defaultKeyboardControlState

          $scope.controlModel = {}

          ###
          Watch the enable/disable expr on the grid/cell scope.  When enabled, connect eventCallbackFn to
          ngKeyPress/ngKeyDown/ngTextInput as appropriate, using prepared $keypressHelper methods as filters.
          ###
          @parseElementControlState = (controlElement, controlStateDef) ->
            thisKeyboardControlState =
              eventName: controlElement.id
              onEnter: $parse(controlStateDef.onEnter)
              onExit: $parse(controlStateDef.onExit)
              eventHandler: keypressHelper(thisElementScope, 'keydown', controlStateDef)

            thisElementScope = controlElement.scope()

            # See discussion under updateControlState() regarding the following $watchCollection().
            thisKeyBindingWatchRemove = thisElementScope.$watchCollection(
              controlStateDef,
              (newKeyBindings) ->
                newKeyDownFn = keypressHelper(thisElementScope, 'keydown', newKeyBindings)

                # TODO: Let there be no need for rebinding.  Let jh-canvas own the continually bound handler,
                #       and just use it to call the relevant & bindings in the active jh-grid element's isolated scope.

                # If the changed element is currently in control, re-bind its new keydown handler method before
                # changing the registration entries.
                if thisKeyboardControlState is currentKeyboardControlState
                  controlElement.unbind('keydown', thisKeyboardControlState.eventHandler)
                  controlElement.on('keydown', newKeyDownFn)

                # Now modify the registry entry so the next time this element becomes active, it uses the new behavior
                # as specified.
                thisKeyboardControlState.eventHandler = newKeyDownFn
                thisKeyboardControlState.onEnter = newKeyBindings.onEnter
                thisKeyboardControlState.onExit = newKeyBindings.onExit
            )

            # Remove the watch handler we've created from the argument scope's watch list when it gets destroyed.
            thisElementScope.$on('$destroy', thisKeyBindingWatchRemove)

            return thisKeyboardControlState

          ###
          This won't get a value unless and until the controlStateDef argument to parseElementControlState comes from an
          expression whose value may change over time.  As of now, its expected that a controller wiring its element for keyboard
          control is constructing an object directly, and mutating that object if necessary rather than providing an expression
          whose return value changes to express a change in control dynamics (e.g. an alternate keyboard binding or a
          relinquishment of the keyboard).
          ###
          @updateElementControlState = null

          # TODO: jh-grid replacement will have already populated element's scope with '&' bindings.  We do not
          #       have any need to drive $parse ourselves, just use cross-directive communication via $attr and $scope
          #       from the given sub-element with jh-grid on it.
          @addKeyboardControlElement = (element, controlStateDef) ->
            elementId = element.id
            if angular.isDefined(registeredElementControlStates[elementId])
              throw new Error("Element-specific keyboard control expressions already bound and registered for #{elementId}")
            thisElementControlState = @parseElementControlState(element, controlStateDef)
            registeredElementControlStates[elementId] = thisElementControlState

          keyActiveWatch = $scope.$watch(
            'controlModel.activeCanvasElement', (newElementId) ->
            oldStateData = currentKeyboardControlState
            newStateData = registeredElementControlStates[newElementId] or defaultKeyboardControlState

            if newStateData is defaultKeyboardControlState
              console.warning("Canvas element set #{newElementId} not registered.  Reverting to initial state.")

            if oldStateData isnt newStateData
              $element.unbind('keydown', oldStateData.onKeyDown) if oldStateData.onKeyDown

              oldStateData.onExit()
              currentKeyboardControlState = newStateData
              newStateData.onEnter()

              # TODO: What does jqLite do w.r.t. preventDefault() ?
              $element.on('keydown', newStateData.onKeyDown) if newStateData.onKeyDown
          )

          @activateKeyboardControl = (element) ->
            # The watch handler will cause the change of control to take effect.
            # Done this way intentionally to permit external control of canvas
            # artifacts via the two-way data binding on controlModel.
            $scope.controlModel.activeCanvasElement = element.id

          @watchEventControl = (element, eventType, handlerStates, eventCtrl) ->
            console.log(element, eventType, handlerStates, eventCtrl, 'TBD')

          $element.on(
            '$destroy', ->
            if currentKeyboardControlState.onKeyDown?
              $element.unbind('keydown', currentKeyboardControlState.onKeyDown)
          )
          $scope.$on('$destroy', keyActiveWatch)
      ]

    return retVal
]

