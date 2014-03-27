define [
  'angular'
  'angular-strap/button'
  'jch-ui-blocks/main'
  'xw-dynamic/main'
  'xw-model/main'
  'xw-report/main'
], () ->
  xwModule = angular.module('xw-ticket', ['ng', 'mgcrea.ngStrap.button', 'jch-ui-blocks', 'xw-dynamic', 'xw-model', 'xw-report']);

  xwModule.config ['$routeProvider', ($routeProvider) ->
    $routeProvider.when '/crosswords',
      templateUrl: '/app/xw-ticket/partials/view.html'
      controller: 'CrosswordCtrl'
  ]

  xwModule.directive 'xwTicket', [
    'xwModelFactory', 'valueImages', 'borderImages', 'fillImages', (xwModelFactory, valueImages, borderImages, fillImages) ->
      restrict: 'E'
      replace: true
      scope:
        ticketDocument: '='
#        onDescripion: '&'
#        onDiscovery: '&'
#        onComplete: '&'

      templateUrl: '/app/xw-ticket/partials/xwTicket.html',
      controller: ($scope) ->
        # ** closeCursor(), openCursor(), and moveCursor() **
        # Private cursor management functions that carry out a cursor state change requested by the underlying model
        # (hence presumed to be correct and formed with valid arguments--no "are you sure" or null input verification
        # by-design.
        closeCursor = (withCommit) ->
          tempGrid = $scope.ticketModel.activeGrid
          tempCell = $scope.ticketModel.cursorCell
          $scope.ticketModel.activeGrid = null
          $scope.ticketModel.cursorCell = null
          tempGrid.handleCloseCursor(withCommit, tempCell)

        openCursor = (targetCell) ->
          targetGrid = targetCell.parentGrid
          $scope.ticketModel.activeGrid = targetGrid
          $scope.ticketModel.cursorCell = targetCell
          targetGrid.handleOpenCursor(targetCell)

        moveCursor = (targetCell) ->
          tempGrid = $scope.ticketModel.activeGrid
          tempCell = $scope.ticketModel.cursorCell
          $scope.ticketModel.cursorCell = targetCell
          tempGrid.handleMoveCursor(tempCell, targetCell)

        # ** onCellClick() and onKeyPress() **
        # Validation of user input occurs in the
        # DOM event handlers that call these methods post-validation: 'onCellClick' and 'onKeyPress'.
        $scope.onCellClick = (cellModel, $event) ->
          responseAction = cellModel.parentGrid.handleCellClick(cellModel, $event)
          if (responseAction? && responseAction instanceof Array && responseAction.length > 0)
            switch (responseAction[0])
              when OnHandleEventAction.NO_ACTION
                angular.noop()
              when OnHandleEventAction.MOVE_CURSOR
                moveCursor responseAction[1]
              when OnHandleEventAction.OPEN_CURSOR
                if ($scope.ticketModel.activeGrid?)
                  closeCursor responseAction[1]
                openCursor responseAction[2]
              when OnHandleEventAction.CLOSE_CURSOR
                closeCursor responseAction[1]
              else
                suffixStr = ' returned by cell click event handler'
                if (responseAction.length > 1)
                  suffixStr = ', with additional arguments ' + responseAction.slice(1).toString() + suffixStr
                throw 'IllegalStateException -- Unknown response action type ' + responseAction[0] + suffixStr
          else
            throw 'IllegalStateException: Non-array or empty-array ' + responseAction?.toString() + ' returned by cell click event handler'
          return false

        # This is also likely to vanish in favor of $rootScope.$broadcast('updateNotice')
        this.purgeWordList = () ->
          $scope.dataReportingModel.purgeWordList()
          return

        this.purgeLetterList = () ->
          $scope.dataReportingModel.revealedLetters = []
          $scope.dataReportingModel.purgedLetters = true
          return

        this.updateReportingService = () ->
          return

        $scope.ticketModel = xwModelFactory.createModelFromDocument $scope.ticketDocument
        $scope.imageModel =
          fillImages: fillImages
          valueImages: valueImages
          borderImages: borderImages
        $scope.dataReportingModel = 'TBD'
  ]

  xwModule.directive 'xwBonusValueCell', () ->
    restrict: 'E'
    replace: true
    require: '^xwTicket'
    scope: false
    templateUrl: '/app/xw-ticket/partials/bonusValueCell.html'

  xwModule.directive 'xwYourLettersGrid', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.gridDirective('yourLetters')
  ]

  xwModule.directive 'xwYourLettersCell', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.cellDirective('yourLetters')
  ]

  xwModule.directive 'xwBonusWordGrid', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.gridDirective('bonusWord')
  ]

  xwModule.directive 'xwBonusWordCell', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.cellDirective('bonusWord')
  ]

  xwModule.directive 'xwCrosswordGrid', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.gridDirective('crossword')
  ]

  xwModule.directive 'xwCrosswordCell', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.cellDirective('crossword')
  ]

  xwModule.directive 'xwBonusValue', () ->
    restrict: 'E'
    replace: true
    require: '^xwTicket'
    scope: false
    templateUrl: '/app/xw-ticket/partials/bonusValue.html'

  xwModule.value 'valueImages', {
    a: '/app/xw-ticket/images/val/A.png'
    b: '/app/xw-ticket/images/val/B.png'
    c: '/app/xw-ticket/images/val/C.png'
    d: '/app/xw-ticket/images/val/D.png'
    e: '/app/xw-ticket/images/val/E.png'
    f: '/app/xw-ticket/images/val/F.png'
    g: '/app/xw-ticket/images/val/G.png'
    h: '/app/xw-ticket/images/val/H.png'
    i: '/app/xw-ticket/images/val/I.png'
    j: '/app/xw-ticket/images/val/J.png'
    k: '/app/xw-ticket/images/val/K.png'
    l: '/app/xw-ticket/images/val/L.png'
    m: '/app/xw-ticket/images/val/M.png'
    n: '/app/xw-ticket/images/val/N.png'
    o: '/app/xw-ticket/images/val/O.png'
    p: '/app/xw-ticket/images/val/P.png'
    w: '/app/xw-ticket/images/val/Q.png'
    r: '/app/xw-ticket/images/val/R.png'
    s: '/app/xw-ticket/images/val/S.png'
    t: '/app/xw-ticket/images/val/T.png'
    u: '/app/xw-ticket/images/val/U.png'
    v: '/app/xw-ticket/images/val/V.png'
    w: '/app/xw-ticket/images/val/W.png'
    x: '/app/xw-ticket/images/val/X.png'
    y: '/app/xw-ticket/images/val/Y.png'
    z: '/app/xw-ticket/images/val/Z.png'
    A: '/app/xw-ticket/images/gval/A.png'
    B: '/app/xw-ticket/images/gval/B.png'
    C: '/app/xw-ticket/images/gval/C.png'
    D: '/app/xw-ticket/images/gval/D.png'
    E: '/app/xw-ticket/images/gval/E.png'
    F: '/app/xw-ticket/images/gval/F.png'
    G: '/app/xw-ticket/images/gval/G.png'
    H: '/app/xw-ticket/images/gval/H.png'
    I: '/app/xw-ticket/images/gval/I.png'
    J: '/app/xw-ticket/images/gval/J.png'
    K: '/app/xw-ticket/images/gval/K.png'
    L: '/app/xw-ticket/images/gval/L.png'
    M: '/app/xw-ticket/images/gval/M.png'
    N: '/app/xw-ticket/images/gval/N.png'
    O: '/app/xw-ticket/images/gval/O.png'
    P: '/app/xw-ticket/images/gval/P.png'
    Q: '/app/xw-ticket/images/gval/Q.png'
    R: '/app/xw-ticket/images/gval/R.png'
    S: '/app/xw-ticket/images/gval/S.png'
    T: '/app/xw-ticket/images/gval/T.png'
    U: '/app/xw-ticket/images/gval/U.png'
    V: '/app/xw-ticket/images/gval/V.png'
    W: '/app/xw-ticket/images/gval/W.png'
    X: '/app/xw-ticket/images/gval/X.png'
    Y: '/app/xw-ticket/images/gval/Y.png'
    Z: '/app/xw-ticket/images/gval/Z.png'
    _: '/app/xw-ticket/images/val/blank.png'
    cursor: '/app/xw-ticket/images/val/qm.png'
    blank: '/app/xw-ticket/images/val/blank.png'
    undefined: '/app/xw-ticket/images/val/blank.png'
  }

  xwModule.value 'borderImages', {
    htop: '/app/xw-ticket/images/border/htop.png'
    hmid: '/app/xw-ticket/images/border/hmid.png'
    hend: '/app/xw-ticket/images/border/hend.png'
    vtop: '/app/xw-ticket/images/border/vtop.png'
    vmid: '/app/xw-ticket/images/border/vmid.png'
    vend: '/app/xw-ticket/images/border/vend.png'
    blank: '/app/xw-ticket/images/val/blank.png'
    undefined: '/app/xw-ticket/images/val/blank.png'
  }

  xwModule.value 'fillImages', {
    revealed: '/app/xw-ticket/images/fill/brite1.png'
    selected: '/app/xw-ticket/images/fill/selected1.png'
    triple: '/app/xw-ticket/images/fill/imageSrc.png'
    blocked: '/app/xw-ticket/images/fill/locked.png'
    tooshort: '/app/xw-ticket/images/fill/altPath.png'
    tripshort: '/app/xw-ticket/images/fill/golden.png'
    dualtrip: '/app/xw-ticket/images/fill/cornsilk.png'
    dualtripshort: '/app/xw-ticket/images/fill/briteblue.png'
    baseline: '/app/xw-ticket/images/fill/pink2.png'
    undefined: '/app/xw-ticket/images/fill/pink2.png'
  }

  xwModule.controller 'CrosswordCtrl', ['$scope', 'xwDocumentFactory', 'xwReportSvc', ($scope, xwDocumentFactory, xwReportSvc) ->
    $scope.documentModel = xwDocumentFactory.newTripleNoTwentyTicketDocument('858:323842-049', 'tripleNoTwenty')

    updateProgressReportFn = (nextReportModel) ->
      $scope.progressModel = nextReportModel
      nextReportModel.updatePromise.then updateProgressReportFn
      return nextReportModel
    updateProgressReportFn xwReportSvc.getProgressReport()

    updatePayoutModelFn = (nextReportModel) ->
      $scope.payoutModel = nextReportModel
      # nextReportModel.updatePromise.then updatePayoutModelFn
      return nextReportModel
    updatePayoutModelFn xwReportSvc.getPayoutReport()
  ]

  class XwModelFactory
    # Given a TicketDocument, produce a TicketModel equivalent suitable for the editor directive at the heart of this
    # module.
    createModelFromDocument: (ticketDocument) ->
      # Populate the cells for each grid model--an 11x11 square
      ii = 0
      kk = 0
      nextCell = null
      firstRowCell = null
      cells = new Array(121)
      while ii < 11
        lastCellAbove = firstRowCell
        lastCellLeft  = null
        jj            = 0
        while jj < 11
          nextCell = new FixedCell(kk, ii, jj, ticketDocument.content.charAt(kk))
          cells[kk] = nextCell

          if(lastCellLeft == null)
            # Remember the start of the present row so we can use it to set above/below links
            # when we start the next row.
            firstRowCell  = nextCell
          else
            # Use the previous allocation reference to set left/right links.
            nextCell.aligned.toLeft       = lastCellLeft
            lastCellLeft.aligned.toRight  = nextCell
            nextCell.rotated.toAbove      = lastCellLeft
            lastCellLeft.rotated.toBelow  = nextCell

          if(lastCellAbove != null)
            # Use a walk of the previous row to set above/below links
            nextCell.rotated.toLeft       = lastCellAbove
            lastCellAbove.rotated.toRight = nextCell
            nextCell.aligned.toAbove      = lastCellAbove
            lastCellAbove.aligned.toBelow = nextCell

          # Keep this cell as the previous allocation of the current row.  Use the left/right
          # navigability built when processing the previous row to advance the lastRow pointer
          # one cell to the right.  This prepares us for linking the left/up navigation for the
          # upcoming next allocation.  Advance the column and flattened index counters.
          lastCellLeft = nextCell
          lastCellAbove = lastCellAbove?.toRight || null
          jj = jj + 1
          kk = kk + 1
        # Advance the row counter.
        ii = ii + 1

      crosswordGrid = new CrosswordGrid(cells)

      #
      # Your Letters Grid
      #
      ii = 0
      kk = 0
      nextCell = null
      cells = new Array(18)
      while ii < 3
        lastCellLeft  = null
        jj            = 0
        while jj < 6
          nextCell = new YourLettersCell(kk, ii, jj, ticketDocument.yourLetters.charAt(kk))
          cells[kk] = nextCell

          if (lastCellLeft == null)
            if (ii==0)
              firstRowCell = nextCell
          else
            lastCellLeft.next = nextCell
            nextCell.prev = lastCellLeft

          lastCellLeft = nextCell
          jj = jj + 1
          kk = kk + 1
        ii = ii + 1

      # Establish circular navigation.  Link last.next to first.  Link first.prev to last.
      firstRowCell.prev = nextCell
      nextCell.next = firstRowCell

      yourLettersGrid = new YourLettersGrid(cells)

      #
      # Bonus Word Grid
      #
      ii = 0
      nextCell = null
      lastCellLeft = null
      cells = new Array(5)
      while ii < 5
        nextCell = new BonusWordCell(ii, ticketDocument.variantData.bonusWord?.charAt(ii) || '_')
        cells[ii] = nextCell

        if lastCellLeft == null
          firstRowCell = nextCell
        else
          lastCellLeft.next = nextCell
          nextCell.prev = lastCellLeft
        lastCellLeft = nextCell
        ii = ii + 1

      # Establish circular navigation.  Link last.next to first.  Link first.prev to last.
      firstRowCell.prev = nextCell
      nextCell.next = firstRowCell

      bonusWordGrid = new BonusWordGrid(cells)
      return new TicketModel(ticketDocument.ticketId, crosswordGrid, yourLettersGrid, bonusWordGrid)

  xwModule.service('xwModelFactory', [XwModelFactory])

  ##
  ## Ticket Model Classes
  ##

  class OrientationKind
    @ALIGNED = new OrientationKind( {
        head: 'htop',
        mid:  'hmid',
        tail: 'hend'
      },
      () -> return OrientationKind.ROTATED
    )
    @ROTATED = new OrientationKind( {
        head: 'vtop',
        mid:  'vmid',
        tail: 'vend'
      },
      () -> return OrientationKind.ALIGNED
    )

    constructor: (@labels, @oppositeFn) ->
      return @

    getBorderHead: () => return @labels.head
    getBorderMiddle: () => return @labels.mid
    getBorderTail: () => return @labels.tail
    changeOrientation: () => return @oppositeFn()

  class LifecycleStage
    constructor: (@canEditDescription, @canEditDiscoveries) ->
      return @

    @DESCRIBING = new LifecycleStage(true, false)
    @PLAYING =  new LifecycleStage(false, true)
    @PUBLISHED = new LifecycleStage(false, false)

    isCrosswordGridEditable:       () => return @canEditDescription
    isBonusWordGridEditable:       () => return @canEditDescription
    isTwentySpotAvailableEditable: () => return @canEditDescription
    isBonusWordValueEditable:      () => return @canEditDiscoveries
    isTwentySpotHitEditable:       () => return @canEditDiscoveries
    isYourLetterGridEditable:      () => return @canEditDiscoveries
    areReportsUpdatable:           () => return @canEditDiscoveries

  class CellStateKind
    constructor: (fillImageKey) ->
      @fillImageKey = fillImageKey
      return @

    # The next four states are used for the cells of a grid that is not reachable by a currently open edit cursor.
    #
    # The BonusWord grid uses @COVERED for all cells whose value has not yet been discovered in YourLetters and
    # @MATCHED for those whose value has.  BonusWord does not use @BLOCKED or @REVEALED.
    #
    # The YourLetters grid uses @COVERED for all cells with no value assigned and @REVEALED for all cells whose
    # letter value has been discovered.
    #
    # The CrosswordGrid uses @COVERED and @MATCHED
    @COVERED          = new CellStateKind('baseline')
    @MATCHED          = new CellStateKind('revealed')
    @BLOCKED          = new CellStateKind('blocked')
    @REVEALED         = new CellStateKind('revealed')

    # @REACHABLE and @SELECTED define the location of cursors on an actively open grid.
    #
    # For BonusWord and YourLetters, all cells become @REACHABLE when the grid is active.
    #
    # For Crossword, any cell where cursor can be moved without being unable to leave the grid in a valid state
    # is reachable.  Any edit in the reachable space has a potential to leave short 1-letter or 2-letter words that
    # are not legal.  Cell border highlighting is used instead of fill colors to convey the location and completion
    # requirements of words in the current cursor's reach.  See {TODO} for a more complete discussion on the designed
    # use of cell boundary highlighting.
    @REACHABLE        = new CellStateKind('selected')
    @SELECTED         = new CellStateKind('active')

    @ERROR            = new CellStateKind('tooshort')

    getImageKey: () => return @fillImageKey


  class CellErrorKind
    @ONE_LETTER_WORD = new CellErrorKind('Isolated letter tiles are not allowed.  Letters tiles must be adjacent to enough other letters tiles to create at least one three letter word.')
    @TWO_LETTER_WORD = new CellErrorKind('Two letter words are not allowed.  Adjacent letter tiles define words, but all words must have at least three letters in length.')
    @WORD_TOO_LONG = new CellErrorKind('The maximum legal word length is 9 letters.  This cell is part of a word that has 10 or 11 letters.')
    @FULL_QUAD = new CellErrorKind('No more than 3 of the 4 tiles from any 2x2 subunit may validly contain a letter')
    @AMBIGUOUS_TRIPLE = new CellErrorKind('Triple bonus modifiers may not placed at the shared intersection between two distinct words.')
    @TOO_MANY_TRIPLES = new CellErrorKind('Exactly four cells are required to contain triple modifiers, but too many are in use.  Reduce the number to four.')
    @MISSING_BONUS_LETTER = new CellErrorKind('The bonus word must contain exactly five letters, but fewer than five have been given.')
    @REUSED_BONUS_LETTER = new CellErrorKind('No two letters of the bonus word can be the same.  All five letters must be unique.')

    constructor: (@toolTipMessage) -> return @

  class TicketErrorKind
    @MISSING_IDENTITY = new TicketErrorKind('The ticket\'s identifier has not been set.  Identifiers require a three digit gameId, a colon ( =), a 6 or 7 digit spoolId, a dash (-), then a three digit ticketId.')
    @INVALID_IDENTITY = new TicketErrorKind('The ticket\'s identifier is malformed.  Identifiers require a three digit gameId, a colon ( =), a 6 or 7 digit spoolId, a dash (-), then a three digit ticketId.')
    @TOO_MANY_WORDS = new TicketErrorKind('The crossword grid must contain exactly 22 words, 11 horizontal and 11 vertical.  There are too many words right now for this ticket to be played.')
    @NOT_ENOUGH_WORDS = new TicketErrorKind('The crossword grid must contain exactly 22 words, 11 horizontal and 11 vertical.  There aren\'t enough words yet for this ticket to be played.')
    @NOT_ENOUGH_TRIPLES = new TicketErrorKind('The crossword grid must contain exactly four tripling modifiers, each of which must be in a cell that is part of only one word.  More triple modifiers are required before this ticket can be played.')
    @NO_BONUS_WORD = new TicketErrorKind('The bonus word content is still undefined and is required before this ticket can be played.');
    @NO_BONUS_VALUE = new TicketErrorKind('The bonus word\'s prize value is required before this ticket can be marked as complete.')
    @BAD_BONUS_VALUE = new TicketErrorKind('The bonus word\'s prize value is set to unusable value and must be changed.');
    @TWENTY_SPOT_UNDEFINED = new TicketErrorKind('The presence or omission of a "fast twenty" spot must be clarified before this ticket can be played.')
    @NO_VALID_OUTCOME = new TicketErrorKind('There is no permutation of available letters left that yields either a non-winner or valid payout result.')

    constructor: (@toolTipMessage) -> return @

  class TicketModel
    hasTwentySpot: null
    hitTwentySpot: null
    bonusValue: null
    payoutValue: null
    activeGrid: null
    cursorCell: null
    ticketErrors: {}

    # @identityRegex = /\d{3}:\d{6,7}\-\d{3}/

    constructor: (@ticketId, @crosswordGrid, @yourLettersGrid, @bonusWordGrid) ->
      # if (TicketModel.identityRegex.test(@ticketId) == false)
      #   throw 'Invalid argument exception!'

      crosswordGrid.parentTicket = @
      yourLettersGrid.parentTicket = @
      bonusWordGrid.parentTicket = @

      # TODO: This needs to be more dynamic
      @lifecycleStage = LifecycleStage.DESCRIBING
      return @

    isDescriptionComplete: () =>
      return @crosswordGrid.isComplete() && @bonusWordGrid.isComplete() && @hasTwentySpot != null

    isDiscoveryComplete: () =>
      return @yourLettersGrid.isComplete() && @bonusValue != null && @hitTwentySpot != null && @payoutValue != null

    mayChangeLifecycle: (toStage) =>
      switch @lifecycleStage
        when LifecycleStage.DESCRIBING
          if (toStage == LifecycleStage.DESCRIBING)
            return true
          else if(toStage == LifecycleStage.PLAYING)
            return isDescriptionComplete()
          else if(toStage == LifecycleStage.PUBLISHED)
            return isDescriptionComplete() && isDiscoveryComplete()
        when LifecycleStage.PLAYING
          if (toStage == LifecycleStage.DESCRIBING)
            return true
          else if (toStage == LifecycleStage.PLAYING)
            return true
          else if (toStage == LifecycleStage.PUBLISHED)
            return isDiscoveryComplete()
        when LifecycleStage.PUBLISHED
          return true
        else

      return false

    setLifecycleStage: (nextLifecycleStage) =>
      retVal = nextLifecycleStage
      if @mayAdvanceLifecycle(nextLifecycleStage)
        @lifecycleStage = nextLifecycleStage
      else
        retVal = @lifecycleStage
      return retVal

    setHasTwentySpot: (hasSpot) =>
      if !@lifecycleStage.isTwentySpotAvailableEditable()
        throw 'IllegalState'
      if (typeof hasSpot) != 'boolean'
        throw 'IllegalArgument'

      @hasTwentySpot = hasSpot

    setHitTwentySpot: (hitSpot) =>
      if !@lifecycleStage.isTwentySpotHitEditable()
        throw 'IllegalState'
      if (typeof hitSpot) != 'boolean'
        throw 'IllegalArgument'

      @hitTwentySpot = hitSpot

    setBonusValue: (newValue) =>
      if !@lifecycleStage.isBonusValueEditable()
        throw 'IllegalState'
      if ((typeof newValue) != 'number') || newValue < 0 || newValue > 3
        throw 'IllegalArgument'

      @bonusValue = newValue

    # TODO: Confirm the min/max valid values
    setPayoutValue: (newValue) =>
      if (!@lifecycleStage.isPayoutValueEditable())
        throw 'IllegalState'
      if (((typeof newValue) != 'number') || newValue < 0 || newValue > 17)
        throw 'IllegalArgument'

      @setPayoutValue = newValue

  # Every handleX() method returns an array describing the caller's requested next
  # action.  The first element is a value from this array, which both defines the
  # next requested action and how the remaining array elements shape that request
  # (e.g. the next element of a @MOVE_CURSOR reply identifies the target cell
  class OnHandleEventAction
    @NO_ACTION = 'NoAction'
    @MOVE_CURSOR = 'MoveCursor'
    @OPEN_CURSOR = 'OpenCursor'
    @CLOSE_CURSOR = 'CloseCursor'
    @ROLLBACK = 'Rollback'


  VALUE_CHARS_ARRAY = [
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
  ]
  VALID_CHARS_ARRAY = VALUE_CHARS_ARRAY.concat('_')

  class AbstractGridModel
    dirtyCells: new Array()
    cursor: null

    constructor: (@gridKind, @numRows, @numCols, @cells) ->
      @numCells   = numRows*numCols
      if (cells.length != @numCells)
        throw 'IllegalArgument: cells.length = ' + cells.length + ', @numRows = ' + @numRows + ', @numCols = ' + @numCols

      # A hash from character to an array of cells whose content is set to that character.
      contentMap = {}
      VALID_CHARS_ARRAY.forEach( (nextChar) -> contentMap[nextChar] = [] )

      parentGrid = @
      cells.forEach( (cellModel) ->
        value = cellModel.content
        if (value != '_')
          value = value.toLowerCase()

        contentMap[value].push(cellModel)
        cellModel.parentGrid = parentGrid
      )
      @contentMap = contentMap
      return @

    # TODO: These are here because YourLetters and BonusWord can benefit from sharing them, but these
    #       more accurately belong in a configurable subclass--they aren't sufficiently general for a
    #       cross-application base class to own them.
    #
    # Base class regular expressions for classifying content.
    @isLegalValueRegex: /^[a-z_]$/

    ##
    ## Cell retrieval

    getCellByCoordinates: (rowId, colId) ->
      if (rowId < 0 || rowId >= @numRows)
        throw 'IllegalArgument: rowId = ' + rowId
      if (colId < 0 || colId >= @numCols)
        throw 'IllegalArgument: colId = ' + colId

      return @cells[colId + (rowId*@numRows)]

    getCellsByContent: (value) ->
      if (!@isLegalValue(value))
        throw 'IllegalArgument: value = ' + value

      return clone @contentMap[value]

    ##
    ## Cell clickability

    # TODO: Primitive (wrong) implementation: accept all clicks.
    handleCellClick = (cellModel, clickEvent) =>
      return [OnHandleEventAction.OPEN_CURSOR, true, cellModel]

    handleOpenCursor = (cursorCell) =>
      cursorCell.temp = true
      @temp = cursorCell
      return

    handleCloseCursor = (withCommit, lastCursor) =>
      @temp.temp = false
      @temp = null
      return

    handleMoveCursor = (origCell, nextCell) =>
      return

    ##
    ## Cursor based key stroke handlers

    # Default behavior without override: set content to the lowercase version of input and then advance cursor to
    # next empty cell, if any.
    handleKeyPressEvent = (inputValue) =>
      retVal = [OnHandleEventAction.NO_ACTION]
      validatedInput = doBeforeHandleKeyPress(@cursorCell, inputValue)
      if (validatedInput)
        originalValue = @cursorCell.content
        if (originalValue != validatedInput)
          @contentMap[originalValue].filter((cellModel) -> return cellModel == @cursorCell)
          @cursorCell.content = validatedInput
          @contentMap[validatedInput].push(@cursorCell)

        nextCell = doAfterHandleKeyInput(@cursorCell, originalValue)
        if (nextCell? && nextCell != @cursorCell)
          # @ticketModel.moveCursor @cursorCell, nextCell, false
          retVal = [OnHandleEventAction.MOVE_CURSOR, nextCell]
      return retVal
    # This implementation is suitable for fixed-in-place grids that can rely exclusively on the arrow keys for
    # cursor movement.# , like YourLetters and BonusWord.  CrosswordCell may
    # want a different implementation that moves the cursor.  Or perhaps not...
    handleBackspace =  () =>
      @cursorCell.content = '_'
      return [OnHandleEventAction.NO_ACTION]
    handleLeftArrow =  () => # @ticketModel.moveCursor @ , @getPreviousCell(), false
      return [OnHandleEventAction.MOVE_CURSOR, @getPreviousCell()]
    handleRightArrow = () => # @ticketModel.moveCursor @, @getNextCell(), false
      return [OnHandleEventAction.MOVE_CURSOR, @getNextCell()]
    handleEscape =     () =>
      return [OnHandleEventAction.ROLLBACK]
    handleEnter =      () =>
      return [OnHandleEventAction.CLOSE]
    handleTab =      () =>
      return [OnHandleEventAction.OPEN, 'withCommit', ticketModel.getCursorCell()]
    handleOthers =     () =>
      return [OnHandleEventAction.NO_ACTION]

    ##
    ## Template methods for implementing subclass variants

    # TODO: Crossword cell will likely want to override this!
    isLegalValue = (value) -> return AbstractGridModel.isLegalValueRegex.test(value)

    # Each subtype MAY override this.  Return false to reject a character.  Return the character as-is or its
    # canonicalized equivalent to accept.  Default impl returns lower case version of input.  Its suitable for
    # YourLetters and BonusGrid where <Shift> is not used to indicate a modifier, no character can be used in
    # more than one cell, and all characters in the persisted form are expected to be lower case.  It is not
    # suitable for Crossword, where <Shift> applies the triple prize modifier and capitalization is used to
    # store the presence of a triple prize cell modifier.
    doBeforeHandleKeyInput = (targetCell, inputValue) =>
      validatedValue = inputValue.toLowerCase()
      if (targetCell?.content != validatedValue)
        matchedCells = @parentGrid.getCellsByContent(validatedValue)
        if (matchedCells.length > 0)
          validatedValue = false

      return validatedValue

    doAfterHandleKeyInput = (targetCell, originalValue) =>
      return targetCell.getNextCell()

    # TODO: MAY override this if lifecycle state advancement does not depend on a grid having a value in
    #       every cell (ok rule for bonusWord and yourLetters, but not main crossword grid).
    isComplete = () =>
      @cells.every((cellModel) ->
        return cellModel.hasValue() && @contentMap[cellModel.content.toLowerCase()].length == 1
      )

    # TODO: MAY ovveride this in each subclass to encapsulate click-based cursor movement semantics
    #testForMoveCursor = (targetCell) -> return targetCell

    # TODO: MUST override this in each subclass to encapsulate click-based cursor creation semantics
    #testForOpenCursor = (targetCell) -> return targetCell

  class AbstractCellModel
    dirty: false

    constructor: (@indexId, @rowId, @colId, @content) ->
      @coordinates = rowId + ',' + colId
      return @

    isBlank = () -> return @content == '_'
    hasContent = () -> return @content != '_'

    getContent = () ->
      retVal = '_'
      if (@parentGrid.parentTicket.cursorCell == this)
        retVal = 'cursor'
      else if (@hasContent())
        retVal = @content

      return retVal

    # Each subtype will want to override this method--they each have different business logic surrounding fill states
    getFill = () =>
      if (@temp)
        retVal = CellStateKind.MATCHED.fillImageKey
      else
        retVal = CellStateKind.COVERED.fillImageKey
      return retVal

    # Each subtype will want to override this method--they each have different business logic surrounding borders
    getBorder = () ->
      return 'blank'

    # Each subtype SHOULD override these to define forward/backward navigation and boundaries
    getNextCell =     () => return this
    getPreviousCell=  () => return this


  #
  # Bonus Word Grid and Cell
  #
  class BonusWordGrid extends AbstractGridModel
    constructor: (cells) ->
      super 'bonusWord', 1, 5, cells
      return @

  class BonusWordCell extends AbstractCellModel
    next: null
    previous: null

    constructor: (yAbs, content) ->
      super yAbs, 0, yAbs, content
      return @

    getNextCell: () => return @next
    getPreviousCell: () => return @previous

    getFill = () =>
      if (@parentGrid.parentTicket.cursorCell == this)
        retVal = CellStateKind.SELECTED
      else if(@hasContent() && @parentGrid.parentTicket.yourLettersGrid.getCellsByContent(@content).length >= 1)
        retVal = CellStateKind.MATCHED
      else
        retVal = CellStateKind.COVERED

      return retVal.fillImageKey

    getBorder: () =>
      retVal = 'blank'
      if (@parentGrid.parentTicket.cursorCell == this)
        switch(@colId)
          when 0
            retVal = OrientationKind.ALIGNED.getBorderHead()
          when 4
            retVal = OrientationKind.ALIGNED.getBorderTail()
          else
            retVal = OrientationKind.ALIGNED.getBorderMiddle()

      return retVal


  #
  # Your Letters Grid and Cell
  #
  class YourLettersGrid extends AbstractGridModel
    constructor: (cells) ->
      super 'yourLetters', 3, 6, cells
      return @

  class YourLettersCell extends AbstractCellModel
    next: null
    previous: null

    getNextCell: () => return @next
    getPreviousCell: () => return @previous

    getFill = () =>
      if (@parentGrid.parentTicket.cursorCell == this)
        retVal = CellStateKind.SELECTED
      else if(@hasContent())
        retVal = CellStateKind.REVEALED
      else
        retVal = CellStateKind.COVERED

      return retVal.fillImageKey

    # TODO: Need an image for unary borders
    getBorder: () =>
      return 'blank'


  #
  # Main Crossword Grid and Cell
  #
  class CrosswordGrid extends AbstractGridModel
    constructor: (cells) ->
      super 'crossword', 11, 11, cells
      @orientation = OrientationKind.ALIGNED

      return @

    toggleOrientation: () =>
      @orientation = @orientation.getOpposite()

    cellBorder: (forCellModel) =>
      retVal = 'blank'
      if (forCellModel?)
        if (forCellModel == @wordLeft)
          retVal = @orientation.getHeadBorder()
        else if (forCellModel == @wordRight)
          retVal = @orientation.getTailBorder()
        else if (
          (forCellModel.parentGrid == @wordLeft?.parentGrid) &&
          (forCellModel.parentGrid == @wordRight?.parentGrid) &&
          (forCellModel.getRelRowId() == @wordLeft.getRelRowId()) &&
          (forCellModel.getRelRowId() == @wordRight.getRelRowId()) &&
          (forCellModel.getRelColId() > @wordLeft.getRelColId()) &&
          (forCellModel.getRelColId() < @wordRight.getRelColId())
        )
          # The complex-looking test above passes if forCellModel, wordLeft, and wordRight
          # all belong to the same orientation-aware row of the same grid and forCellModel's
          # orientation-aware column is both to wordLeft's right and to wordRight's left.
          retVal = @orientation.getMidBorder()

      return retVal

    cellState: (forCellModel) =>
      if ((forCellModel == @cursorLeft) || (forCellModel == @cursorRight))
        return CellStateKind.SELECTED
      else if (
        (@cursorLeft?) && (@cursorRight?) &&
        (forCellModel.getRelRowId() == @cursorLeft.getRelRowId()) &&
        (forCellModel.getRelColId() > @cursorLeft.getRelColId()) &&
        (forCellModel.getRelRowId() == @cursorRight.getRelRowId()) &&
        (forCellModel.getRelColId() < @cursorRight.getRelColId())
      )
        return CellStateKind.SELECTED

      return forCellModel.state

    # TODO: Provide a REAL implementation!
    isComplete = () => return true

  # TODO: Enforce constraint that boardModel must be a CrosswordGrid
  class FixedCell extends AbstractCellModel
    isTriple: null
    alignedWordCell: null
    rotatedWordCell: null

    constructor: (gridModel, rowId, colId, content) ->
      super gridModel, rowId, colId, content
      @isTriple = false
      @aligned = new RelativeCell(rowId, colId, @)
      @rotated = new RelativeCell(colId, rowId, @)
      return @

    # Get the orientation-dependent object with links in all four directions plus diagonals.
    getRel: () =>
      if (@parentGrid.orientation == OrientationKind.ROTATED)
        return @rotated
      else
        return @aligned

    getToAbove: () => return getRel().toAbove
    getToBelow: () => return getRel().toBelow
    getToLeft: () => return getRel().toLeft
    getToRight: () => return getRel().toRight

    # Access the oreintation-dependent (x,y) coordinates.  @ALIGNED coordinates are identical to
    # fixed, but @ROTATED are swapped and return as (y,x).
    getRelRowId: () => return getRel().rowId
    getRelColId: () => return getRel().colId

    handleKeyPress = (value) =>
      legalValue = @getLegalValue value
      if (legalValue?)
        @content = legalValue
        if (legalValue == legalValue.toUpperCase())
          @isTriple = null

      nextCell = @getNextCell()
      if (nextCell?)
        @parentGrid.moveCursor @, nextCell, false

    # This implementation is suitable for fixed-in-place grids, like YourLetters and BonusWord.  CrosswordCell will
    # want a different implementation.
    handleBackspace: () =>
      @content = '_'

    handleLeftArrow: () =>
      @parentGrid.moveCursor @, @getPreviousCell(), false

    handleRightArrow: () =>
      @parentGrid.moveCursor @, @getNextCell(), false

    getLegalValue: (value) =>
      # TODO: If upper case, make sure there aren't already four trippling cells and that no cell above or below
      #       has content
      return value

    # Being overriden as makes sense, but overriding AbstractCellModel.handleValue() eliminates their only caller
    getNextCell: () -> @getToRight()
    getPreviousCell: () -> @getToLeft()

    # TODO: Blocked and error cases...
    getFill = () =>
      if (@parentGrid.parentTicket.cursorCell == this)
        retVal = CellStateKind.SELECTED
      else if(@hasContent() && @parentGrid.parentTicket.yourLettersGrid.getCellsByContent(@content.toLowerCase()).length >= 1)
        retVal = CellStateKind.MATCHED
      else
        retVal = CellStateKind.COVERED

      return retVal.fillImageKey

    getBorder: () =>
      retVal = 'blank'
      if (@parentGrid.parentTicket.cursorCell == this)
        switch(@colId)
          when 0
            retVal = OrientationKind.ALIGNED.getBorderHead()
          when 4
            retVal = OrientationKind.ALIGNED.getBorderTail()
          else
            retVal = OrientationKind.ALIGNED.getBorderMiddle()

      return retVal

    openCursor: () =>
      @parentGrid.wordLeft = @searchLeft(this, true)
      @parentGrid.wordRight = @searchRight(this, true)
      @parentGrid.cursorLeft = @searchLeft(@parentGrid.wordLeft, false)
      @parentGrid.cursorRight = @searchRight(@parentGrid.wordRight, false)

  # Enumeration of single and multi-bit masks for rapid corner/edge/interior detection
  class CellRegionEnum
    @INTERIOR = 0

    @TOP_EDGE = 1
    @BOTTOM_EDGE = 2
    @LEFT_EDGE = 4
    @RIGHT_EDGE = 8

    @TOP_LEFT_CORNER = @TOP_EDGE | @LEFT_EDGE          # 5
    @TOP_RIGHT_CORNER = @TOP_EDGE | @RIGHT_EDGE        # 9
    @BOTTOM_LEFT_CORNER = @BOTTOM_EDGE | @LEFT_EDGE    # 6
    @BOTTOM_RIGHT_CORNER = @BOTTOM_EDGE | @RIGHT_EDGE  # 10

    @HORIZONTAL_EDGE = @TOP_EDGE | @BOTTOM_EDGE        # 3
    @VERTICAL_EDGE = @LEFT_EDGE | @RIGHT_EDGE          # 12
    @ANY_EDGE = @HORIZONTAL_EDGE | @VERTICAL_EDGE      # 15

    # 7, 11, 13, 14 are not terribly useful trios of edges)

  class RelativeCell

    toAbove: null
    toBelow: null
    toLeft: null
    toRight: null

    toAboveLeft: null
    toAboveRight: null
    toBelowLeft: null
    toBelowRight: null

    constructor: (@rowId, @colId, @fixedCell) ->
      if (rowId == 0)
        if (colId == 0)
          # Top Left Corner
          @cellRegion = CellRegionEnum.TOP_LEFT_CORNER
        else if (colId == 10)
          # Top Right Corner
          @cellRegion = CellRegionEnum.TOP_RIGHT_CORNER
        else
          # Top Edge
          @cellRegion = CellRegionEnum.TOP_EDGE
      else if (rowId == 10)
        if (colId == 0)
          # Bottom Left Corner
          @cellRegion = CellRegionEnum.BOTTOM_LEFT_CORNER
        else if (colId == 10)
          # Bottom Right Corner
          @cellRegion = CellRegionEnum.BOTTOM_RIGHT_CORNER
        else
          # Bottom Edge
          @cellRegion = CellRegionEnum.BOTTOM_EDGE
      else if (colId == 0)
        # Left Edge
        @cellRegion = CellRegionEnum.LEFT_EDGE
      else if (colId == 10)
        # Right Edge
        @cellRegion = CellRegionEnum.RIGHT_EDGE
      else
        # Interior
        @cellRegion = CellRegionEnum.INTERIOR

      return @

    # This routine is called twice for each fixed cell during model construction.  The orientation is toggled
    # between the calls such that each RelativeCell handles its call at a time when the overall grid is in its
    # assigned orientation.  This provides navigability in a total of eight different directions, simplifying
    # tests for fill viability, which involves checking the other three cells of either 1 (4 corners), 2 (36
    # non-corner edges) or 4 (81 interior cells) 2x2 quads.  To simplify, triplets for the appropriate quads
    # are derived and cached at this time as well.
    initDiagonals: () ->
      if (@cellRegion == CellRegionEnum.INTERIOR)
        @toAboveLeft = @toAbove.getToLeft()
        @toAboveRight = @toAbove.getToRight()
        @toBelowLeft = @toBelow.getToLeft()
        @toBelowRight = @toBelow.getToRight()
        @quads = [
          new Array(@toLeft,  @toAboveLeft,  @toAbove)
          new Array(@toRight, @toAboveRight, @toAbove)
          new Array(@toLeft,  @toBelowLeft,  @toBelow)
          new Array(@toRight, @toBelowRight, @toBelow)
        ]
      else if (@cellRegion & CellRegionEnum.HORIZONTAL_EDGE)
        if (@cellRegion & CellRegionEnum.VERTICAL_EDGE)
          # Its a corner
          if (@cellRegion & CellRegionEnum.TOP_EDGE)
            if (@cellRegion == CellRegionEnum.TOP_LEFT_CORNER)
              @toBelowRight = @toBelow.getToRight()
              @quads = [new Array(@toRight, @toBelowRight, @toBelow)]
            else
              @toBelowLeft = @toBelow.getToLeft()
              @quads = [new Array(@toLeft,  @toBelowLeft,  @toBelow)]
          else if (@cellRegion == CellRegionEnum.BOTTOM_LEFT_CORNER)
            @toAboveRight = @toAbove.getToRight()
            @quads = [new Array(@toRight, @toAboveRight, @toAbove)]
          else
            @toAboveLeft = @toAbove.getToLeft()
            @quads = [new Array(@toLeft,  @toAboveLeft,  @toAbove)]
        else if (@cellRegion == CellRegionEnum.TOP_EDGE)
          @toBelowLeft = @toBelow.getToLeft()
          @toBelowRight = @toBelow.getToRight()
          @quads = [
            new Array(@toLeft,  @toBelowLeft,  @toBelow)
            new Array(@toRight, @toBelowRight, @toBelow)
          ]
        else
          @toAboveLeft = @toAbove.getToLeft()
          @toAboveRight = @toAbove.getToRight()
          @quads = [
            new Array(@toLeft,  @toAboveLeft,  @toAbove)
            new Array(@toRight, @toAboveRight, @toAbove)
          ]
      else if (@cellRegion == CellRegionEnum.LEFT_EDGE)
        @toAboveRight = @toAbove.getToRight()
        @toBelowRight = @toBelow.getToRight()
        @quads = [
          new Array(@toRight, @toAboveRight, @toAbove)
          new Array(@toRight, @toBelowRight, @toBelow)
        ]
      else
        @toAboveLeft = @toAbove.getToLeft()
        @toBelowLeft = @toBelow.getToLeft()
        @quads = [
          new Array(@toLeft,  @toAboveLeft,  @toAbove)
          new Array(@toLeft,  @toBelowLeft,  @toBelow)
        ]
      return



  return xwModule