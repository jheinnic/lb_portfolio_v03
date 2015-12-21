'use strict'

module.exports = [
  () ->
    retVal =
      restrict: 'A'
      controller: [
        '$scope', '$element', '$attrs', 'keypressHelper', '$parse', ($scope, $element, $attrs, keypressHelper, $parse) ->
          defaultKeyboardControlState =
            stateName: 'default'
            onEnter: angular.noop
            onExit: angular.noop
            eventHandler: null
          currentKeyboardControlState = defaultKeyboardControlState

          eventControlStates =
            keyboard:
              default: defaultKeyboardControlState
            click: {}
          eventControllers =
            keyboard: @

          @parseElementControlState = (controlElement, controlStateDef) ->
            thisKeyboardControlState =
              eventName: controlElement.id
              onEnter: $parse(controlStateDef.onEnter)
              onExit: $parse(controlStateDef.onExit)
              eventHandler: null

            thisElementScope = controlElement.scope()

            # See discussion under updateControlState() regarding the following $watchCollection().
            thisKeyBindingWatchRemove = thisElementScope.$watchCollection(
              controlStateDef,
              (newKeyBindings) ->
                newKeyDownFn = keypressHelper(thisElementScope, 'keydown', newKeyBindings)
                if currentKeyboardControlState.eventName is controlElement.id
                  controlElement.unbind('keydown', thisKeyboardControlState.eventHandler)
                  controlElement.on('keydown', newKeyDownFn)
                thisKeyboardControlState.eventHandler = newKeyDownFn # TODO: Handle enterExpr/exitExpr changes too
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

          @addKeyboardControlElement = (element, controlStateDef) ->
            elementId = element.id
            if angular.isDefined(eventControlStates[elementId])
              throw new Error(
                "Key binding, onEnter, and onExit expressions already registered for #{elementId}"
              )
            thisCanvasStateData = eventControllers.keyboard.parseElementControlState(element, controlStateDef)
            eventControlStates.keyboard[elementId] = thisCanvasStateData

          ###
          Watch the enable/disable expr on the grid/cell scope.  When enabled, connect eventCallbackFn to
          ngKeyPress/ngKeyDown/ngTextInput as appropriate, using prepared $keypressHelper methods as filters.
          ###
          # if angular.isObject keyBindings
          #   thisCanvasStateData.eventHandler = keypressHelper(thisElementScope, 'keydown', keyBindings)
          # else if angular.isString keyBindings
          # If key binding is given as an expression itself, parse and watch it.

          keyActiveWatch = $scope.$watch(
            'controlModel.activeCanvasElement', (newElementId) ->
            oldStateData = currentKeyboardControlState
            newStateData = eventControlStates[newElementId] || defaultKeyboardControlState

            if newStateData == defaultKeyboardControlState
              console.warning("Canvas element set #{newElementId} not registered.  Reverting to initial state.")

            if oldStateData != newStateData
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

          $element.on(
            '$destroy', ->
            if currentKeyboardControlState.onKeyDown?
              $element.unbind 'keydown', currentKeyboardControlState.onKeyDown
          )
          $scope.$on('$destroy', keyActiveWatch)
      ]

    return retVal
]

