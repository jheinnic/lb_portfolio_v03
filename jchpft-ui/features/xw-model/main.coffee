# TODO: Make this work!  Need to import 'amdefine'
# if (typeof define != 'function')
#   define = require('amdefine')(module);

define ['angular'], () ->
  appModule = angular.module('xw-model', ['ng'])

  appModule.service('xwModelFactory', ['$q', ModelFactory])

  ##
  ## Factory Service
  ##
  class ModelFactory
    # TODO: Figure out why we wanted $q!?
    constructor: (@$q) -> super()

    @createProgressAnalysisModel: (ticketId) ->
      return new ProgressAnalysisModel(ticketId)

    @createProgressReportModel: (ticketId, analysisModel, updatePromise) ->
      return new ProgressReportModel(ticketId, analysisModel, updatePromise)

    @createPayoutReportModel: (ticketId) ->
      return new PayoutReportModel(ticketId)

    @newTicketDocument: (ticketId, variantKind) ->
      retVal = new TicketDocument(
        ticketId
        '_________________________________________________________________________________________________________________________'
        '__________________'
        -1
          variantKind: TicketVariantKind.UNKNOWN
          tripleNoTwenty: null
          tripleWithSpot: null
          fiveX: null
      )
      retVal.changeVariantKind(variantKind)

      return retVal

    @loadTripleNoTwentyTicketDocument: (ticketId, content, yourLetters, payoutValue, bonusWord, bonusValue) ->
      return new TicketDocument(
        ticketId
        content
        yourLetters
        payoutValue
          variantKind: TicketVariantKind.TRIPLING_NO_TWENTY
          tripleNoTwenty:
            bonusWord: bonusWord
            bonusValue: bonusValue
          tripleWithSpot: null
          fiveX: null
      )

    @loadTripleWithSpotTicketDocument: (ticketId, content, yourLetters, payoutValue, bonusWord, bonusValue, spotValue) ->
      return new TicketDocument(
        ticketId
        content
        yourLetters
        payoutValue
          variantKind: TicketVariantKind.TRIPLING_NO_TWENTY
          tripleNoTwenty: null
          tripleWithSpot:
            bonusWord: bonusWord
            bonusValue: bonusValue
            spotValue: spotValue
          fiveX: null
      )

    @loadTripleFiveXTicketDocument: (ticketId, content, yourLetters, payoutValue, multiplier, spotValue) ->
      return new TicketDocument(
        ticketId
        content
        yourLetters
        payoutValue
          variantKind: TicketVariantKind.FIVE_X
          tripleNoTwenty: null
          tripleWithSpot: null
          fiveX:
            multiplier: multiplier
            spotValue: spotValue
      )

    # TODO: Migrate construction code here!
    @createTicketModel: (ticketDocument) ->
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
          lastCellAbove = lastCellAbove.toRight
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
        nextCell = new BonusWordCell(ii, ticketDocument.variantData.bonusWord?.charAt(kk) || '_')
        cells[kk] = nextCell
        
        if lastCellLeft == null
          firstRowCell = nextCell
        else
          lastCellLeft.next = nextCell
          nextCell.prev = lastCellLeft
        lastCellLeft = nextCell

      # Establish circular navigation.  Link last.next to first.  Link first.prev to last.
      firstRowCell.prev = nextCell
      nextCell.next = firstRowCell

      bonusWordGrid = new BonusWordGrid(cells)
      return new TicketModel(ticketDocument.ticketId, crosswordGrid, yourLettersGrid, bonusWordGrid)
      
    @getDescribingLifecycleStage: ->
      return LifecycleStage.DESCRIBING

    @getPlayingLifecycleStage: ->
      return LifecycleStage.PLAYING

    @getPublishedLifecycleStage: ->
      return LifecycleStage.PUBLISHED

    @getCoveredCellState: ->
      return CellStateKind.COVERED

    @getMatchedCellState: ->
      return CellStateKind.MATCHED

    @getCoveredTripleCellState: ->
      return CellStateKind.TRIPLE_COVERED

    @getMatchedTripleCellState: ->
      return CellStateKind.TRIPLE_MATCHED

    @getRevealedCellState: ->
      return CellStateKind.REVEALED

    @getBlockedCellState: ->
      return CellStateKind.BLOCKED

    @getSelectedCellState: ->
      return CellStateKind.SELECTED

    @getInvalidCellState: ->
      return CellStateKind.INVALID

  ##
  ## Ticket Model Classes
  ##

  class OrientationKind
    @ALIGNED =
      labels: ['htop', 'hmid', 'hend']
      opposite: OrientationKind.ROTATED
    @ROTATED =
      labels: ['vtop', 'vmid', 'vend']
      opposite: OrientationKind.ALIGNED


  class LifecycleStage extends Enum
    @_size: 0
    @_VALUES: {@DESCRIBING, @PLAYING, @PUBLISHED}

    @DESCRIBING: new LifecycleStage(true, false)
    @PLAYING: new LifecycleStage(false, true)
    @PUBLISHED: new LifecycleStage(false, false)

    constructor: (@canEditDescription, @canEditDiscoveries) -> super()

    isCrosswordGridEditable:       () => return @canEditDescription
    isBonusWordGridEditable:       () => return @canEditDescription
    isTwentySpotAvailableEditable: () => return @canEditDescription
    isBonusWordValueEditable:      () => return @canEditDiscoveries
    isTwentySpotHitEditable:       () => return @canEditDiscoveries
    isYourLetterGridEditable:      () => return @canEditDiscoveries
    areReportsUpdatable:           () => return @canEditDiscoveries


#  class GridKind extends Enum
#    @_size: 0
#    @_VALUES: {@YOUR_LETTERS, @BONUS_WORD, @CROSSWORD}
#
#    @YOUR_LETTERS = new GridKind('yourLetters')
#    @BONUS_WORD = new GridKind('bonusWord')
#    @CROSSWORD =  new GridKind('crossword')
#
#    constructor: (@typeString) -> super()
#
#    getGridTypeName: () => return @typeString + 'Grid'
#    getCellTypeName: () => return @typeString + 'Cell'
#
#    getGridDirectiveName: () =>
#      temp = getGridTypeName()
#      return 'xw' + temp.charAt(0).toUpperCase() + temp.slice(1)
#
#    getCellDirectiveName: () =>
#      temp = getGridTypeName()
#      return 'xw' + temp.charAt(0).toUpperCase() + temp.slice(1)
#
#    # TODO: Is this needed!?  It should be defined in a more reusable place if so.
#    SNAKE_CASE_REGEXP = /[A-Z]/g;
#    snake_case = (name, separator) ->
#      separator = separator || '_'
#      return name.replace SNAKE_CASE_REGEXP, (letter, pos) ->
#        return (pos ? separator : '') + letter.toLowerCase()
#
#    # TODO: Is this needed!?  If so, should it be defined in Enum base class?
#    @getByCamelCaseName: (gridName) ->
#      return @_VALUES[snake_case(gridName)]


  class CellStateKind extends Enum
    @_size: 0
    @_VALUES: {@COVERED, @MATCHED, @BLOCKED, @REVEALED, @REACHABLE, @SELECTED, @ERROR}

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
    # @AT_RISK          = new CellStateKind('tripshort')
    @SELECTED         = new CellStateKind('active')

    @ERROR            = new CellStateKind('tooshort')

    constructor: (@fillImageKey) ->
      super()


  class CellErrorKind extends Enum
    @_size: 0
    @_VALUES: {@ONE_LETTER_WORD, @TWO_LETTER_WORD, @WORD_TOO_LONG, @FULL_QUAD, @AMBIGUOUS_TRIPLE, @TOO_MANY_TRIPLES, @MISSING_BONUS_LETTER, @REUSED_BONUS_LETTER}

    @ONE_LETTER_WORD = new CellErrorKind('Isolated letter tiles are not allowed.  Letters tiles must be adjacent to enough other letters tiles to create at least one three letter word.')
    @TWO_LETTER_WORD = new CellErrorKind('Two letter words are not allowed.  Adjacent letter tiles define words, but all words must have at least three letters in length.')
    @WORD_TOO_LONG = new CellErrorKind('The maximum legal word length is 9 letters.  This cell is part of a word that has 10 or 11 letters.')
    @FULL_QUAD = new CellErrorKind('No more than 3 of the 4 tiles from any 2x2 subunit may validly contain a letter')
    @AMBIGUOUS_TRIPLE = new CellErrorKind('Triple bonus modifiers may not placed at the shared intersection between two distinct words.')
    @TOO_MANY_TRIPLES = new CellErrorKind('Exactly four cells are required to contain triple modifiers, but too many are in use.  Reduce the number to four.')
    @MISSING_BONUS_LETTER = new CellErrorKind('The bonus word must contain exactly five letters, but fewer than five have been given.')
    @REUSED_BONUS_LETTER = new CellErrorKind('No two letters of the bonus word can be the same.  All five letters must be unique.')

    constructor: (@toolTipMessage) ->
      super()


  class TicketErrorKind extends Enum
    @_size: 0
    @_VALUES: {@MISSING_IDENTITY, @INVALID_IDENTITY, @TOO_MANY_WORDS, @NOT_ENOUGH_WORDS, @NOT_ENOUGH_TRIPLES, @BONUS_WORD_UNDEFINED, @UNKNOWN_BONUS_VALUE, @BONUS_VALUE_UNDEFINED, @TWENTY_SPOT_UNDEFINED, @NO_VALID_OUTCOME}

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

    constructor: (@toolTipMessage) -> super()


  class TicketModel
    hasTwentySpot: null
    hitTwentySpot: null
    bonusValue: null
    payoutValue: null
    activeGrid: null
    cursorCell: null
    ticketErrors: {}

    @::identityRegex = /\d{3}:\d{6,7}\-\d{3}/

    constructor: (@ticketId, @crosswordGrid, @yourLettersGrid, @bonusWordGrid) ->
      if (@::identityRegex.test(@ticketId) == false)
        throw 'Invalid argument exception!'

      crosswordGrid.parentTicket = @
      yourLettersGrid.parentTicket = @
      bonusWordGrid.parentTicket = @

      # TODO: This needs to be more dynamic
      @lifecycleStage = LifecycleStage.DESCRIBING

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


  VALUE_CHARS_ARRAY = [
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
  ]
  VALID_CHARS_ARRAY = VALUE_CHARS_ARRAY.concat('_')

  class AbstractGridModel
    dirtyCells: new Array()
    cursor: null

    constructor: (@gridKind, @numRows, @numCols, @cells) ->
      super()

      @numCells   = numRows*numCols
      if (cells.size() != @numCells)
        throw "IllegalArgument: cells.size() = " + cells.size() + ', @numCells = ' + @numCells

      # A hash from character to an array of cells whose content is set to that character.
      VALID_CHARS_ARRAY.forEach( (nextChar) -> @contentMap[nextChar] = [] )
      cells.forEach( (cellModel) ->
        value = cellModel.content
        if (value != '_')
          value = value.toLowerCase()

        @contentMap[value].push(cellModel)
        cellModel.parentGrid = @
      )

    # TODO: These are here because YourLetters and BonusWord can benefit from sharing them, but these
    #       more accurately belong in a configurable subclass--they aren't sufficiently general for a
    #       cross-application base class to own them.
    #
    # Base class regular expressions for classifying content.
    @isLegalValueRegex: /^[a-z_]$/

    ##
    ## Cell registration and retrieval

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
    ## Cursor based key stroke handlers

    # Default behavior without override: set content to the lowercase version of input and then advance cursor to
    # next empty cell, if any.
    onKeyPressEvent: (inputValue) =>
      validatedInput = doBeforeHandleKeyPress(@cursorCell, inputValue)
      if (validatedInput)
        originalValue = @cursorCell.content
        if (originalValue != validatedInput)
          if (@contentMap?)
            @contentMap[originalValue].filter((cellModel) -> return cellModel == @cursorCell)
            @cursorCell.content = validatedInput
            @contentMap[validatedInput].push(@cursorCell)

        nextCell = doAfterHandleKeyInput(@cursorCell, originalValue)
        if (nextCell? && nextCell != @cursorCell)
          @ticketModel.moveCursor @cursorCell, nextCell, false

    # This implementation is suitable for fixed-in-place grids, like YourLetters and BonusWord.  CrosswordCell may
    # want a different implementation that moves the cursor.  Or perhaps not...
    handleBackspace:  () =>
      @cursorCell.content = '_'
    handleLeftArrow:  () => @ticketModel.moveCursor @ , @getPreviousCell(), false
    handleRightArrow: () => @ticketModel.moveCursor @, @getNextCell(), false

    ##
    ## Template methods for implementing subclass variants
    isComplete: () =>
      @cells.every((cellModel) ->
        return cellModel.hasValue() && @contentMap[cellModel.content.toLowerCase()].size() == 1
      )

    # TODO: Crossword cell will likely want to override this!
    isLegalValue: (value) -> return AbstractGridModel.isLegalValueRegex.test(value)

    # Each subtype MAY override this.  Return false to reject a character.  Return the character as-is or its
    # canonicalized equivalent to accept.  Default impl returns lower case version of input.  Its suitable for
    # YourLetters and BonusGrid where <Shift> is not used to indicate a modifier, no character can be used in
    # more than one cell, and all characters in the persisted form are expected to be lower case.  It is not
    # suitable for Crossword, where <Shift> applies the triple prize modifier and capitalization is used to
    # store the presence of a triple prize cell modifier.
    doBeforeHandleKeyInput: (targetCell, inputValue) =>
      validatedValue = inputValue.toLowerCase()
      if (targetCell?.content != validatedValue)
        matchedCells = @parentGrid.getCellsByContent(validatedValue)
        if (matchedCells.size() > 0)
          validatedValue = false

      return validatedValue

    doAfterHandleKeyInput: (targetCell, originalValue) =>
      return targetCell.getNextCell()

    # TODO: MAY override this if lifecycle state advancement does not depend on a grid having a value in
    #       every cell (ok rule for bonusWord and yourLetters, but not main crossword grid).
    isComplete: () -> return @cells.every (value) -> return value.hasContent()

    # TODO: MAY ovveride this in each subclass to encapsulate click-based cursor movement semantics
    testForMoveCursor: (targetCell) -> return targetCell;

    # TODO: MUST override this in each subclass to encapsulate click-based cursor creation semantics
    testForOpenCursor: (targetCell) -> return targetCell;

  class AbstractCellModel
    dirty: false

    constructor: (@indexId, @rowId, @colId, @content) ->
      super()

    isBlank: () -> return @content == '_'
    hasContent: () -> return @content != '_'

    # Each subtype SHOULD override these to define forward/backward navigation and boundaries
    getNextCell:     () => return this
    getPreviousCell: () => return this


  #
  # Bonus Word Grid and Cell
  #
  class BonusWordGrid extends AbstractGridModel
    constructor: (cells) ->
      super 'bonusWord', 1, 5, cells

  class BonusWordCell extends AbstractCellModel
    next: null
    previous: null

    constructor: (yAbs, content) ->
      super yAbs, 0, yAbs, content

    getNextCell: () => return @next
    getPreviousCell: () => return @previous


  #
  # Your Letters Grid and Cell
  #
  class YourLettersGrid extends AbstractGridModel
    constructor: (cells) ->
      super 'yourLetters', 3, 6, cells

  class YourLettersCell extends AbstractCellModel
    next: null
    previous: null

    getNextCell: () => return @next
    getPreviousCell: () => return @previous


  #
  # Main Crossword Grid and Cell
  #
  class CrosswordGrid extends AbstractGridModel
    constructor: (cells) ->
      super 'crossword', 11, 11, cells
      @orientation = OrientationKind.ALIGNED
      @wordLeft    = null
      @wordRight   = null
      @cursorLeft  = null
      @cursorRight = null

    toggleOrientation: () =>
      @orientation = @orientation.getOpposite()
      @wordLeft    = null
      @wordRight   = null
      @cursorLeft  = null
      @cursorRight = null

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

    getRel: () =>
      if (@parentGrid.orientation == OrientationKind.ROTATED)
        return @rotated
      else
        return @aligned

    getToAbove: () => return getRel().toAbove
    getToBelow: () => return getRel().toBelow
    getToLeft: () => return getRel().toLeft
    getToRight: () => return getRel().toRight
    getRelRowId: () => return getRel().rowId
    getRelColId: () => return getRel().colId

    handleInput = (value) =>
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

    openCursor: () =>
      @parentGrid.wordLeft = @searchLeft(this, true)
      @parentGrid.wordRight = @searchRight(this, true)
      @parentGrid.cursorLeft = @searchLeft(@parentGrid.wordLeft, false)
      @parentGrid.cursorRight = @searchRight(@parentGrid.wordRight, false)

    searchLeft: (startCell, requireContent) =>
      if startCell == null
        throw 'IllegalArgument'

      if requireContent
        lastCell = startCell
        nextCell = startCell.getToLeft()
        while (nextCell?.hasContent())
          lastCell = nextCell
          nextCell = lastCell.getToLeft()
      else
        nextCell        = startCell.getToLeft()

        upOne           = nextCell?.getToAbove()
        downOne         = nextCell?.getToBelow()
        leftOne         = nextCell?.getToLeft()
        rightOne        = startCell
        upOneFull       = upOne?.hasContent()
        downOneFull     = downOne?.hasContent()
        leftOneFull     = leftOne?.hasContent()
        rightOneFull    = startCell.hasContent()

        topLeft         = upOne?.getToLeft()
        topRight        = upOne?.getToRight()
        bottomLeft      = downOne?.getToLeft()
        bottomRight     = downOne?.getToRight()
        topLeftFull     = topLeft?.hasContent()
        topRightFull    = topRight?.hasContent()
        bottomLeftFull  = bottomLeft?.hasContent()
        bottomRightFull = bottomRight?.hasContent()

        upTwo           = upOne?.getToAbove()
        downTwo         = downOne?.getToBelow()
        leftTwo         = leftOne?.getToLeft()
        rightTwo        = rightOne?.getToRight()
        upTwoFull       = upTwo?.hasContent()
        downTwoFull     = downTwo?.hasContent()
        leftTwoFull     = leftTwo?.hasContent()
        rightTwoFull    = rightTwo?.hasContent()

        while (nextCell != null && !(
          (
            (lastAbove != null) && (nextAbove != null) && (lastAbove.hasContent()) && (nextAbove.hasContent())
          ) || (
            (lastBelow != null) && (nextBelow != null) && (lastBelow.hasContent()) && (nextBelow.hasContent())
          )
        ))
          lastCell  = nextCell
          lastAbove = nextAbove
          lastBelow = nextBelow
          nextCell  = lastCell.getToLeft()
          nextAbove = lastAbove.getToLeft()
          nextBelow = lastBelow.getToLeft()

      return lastCell

  class RelativeCell
    toAbove: null
    toBelow: null
    toLeft: null
    toRight: null

    constructor: (@rowId, @colId, @fixedCell) ->

##
## Ticket Anaylsis Models
##
  class ProgressAnalysisModel
    constructor: (@ticketId) ->
      super()
      @purgeWordLists()
      @purgeLettersHash()

    purgeWordLists: () =>
      @purgedWordLists = true
      @bonusWord       = null
      @allWords        = new Array()
      @purgeAnalysis()

    purgeLettersHash: () =>
      @purgedLettersHash    = true
      @revealedLettersHash = {}
      @purgeAnalysis()

    purgeAnalysis: () =>
      @allWords.forEach( (wordAnalysis) -> wordAnalysis.reset() )
      @incompleteTripleWords = null
      @incompleteBasicWords  = null
      @completeTripleWords   = null
      @completeBasicWords    = null
      @incompleteWords       = null
      @completeWords         = null
      @tripleWords           = null
      @basicWords            = null

    setBonusWord: (rawWordStr) =>
      if (@purgedWordLists == false)
        throw 'IllegalStateException'
      else if (@bonusWord?)
        throw 'IllegalStateException'

      @bonusWord = new WordAnalysis(rawWordStr,true)
      @allWords.push(@bonusWord)

    addWord: (rawWordStr) =>
      if (@purgedWordLists == false)
        throw 'IllegalStateException'

      @allWords.push(new WordAnalysis(rawWordStr,false))

    addRevealedLetter: (revealedLetter) =>
      if (@purgedLettersHash == false)
        throw 'IllegalStateException'

      @revealedLettersHash[revealedLetter] = 1

    refreshAnalysis: () =>
      if (@purgedLettersHash == true && @purgedWordLists == true)
        throw 'IllegalStateException'

      @incompleteTripleWords = new Array()
      @incompleteBasicWords  = new Array()
      @completeTripleWords   = new Array()
      @completeBasicWords    = new Array()
      bonusWords             = new Array()

      @allWords.forEach( (wordReport) ->
        wordReport.doAnalysis(@revealedLettersHash, @incompleteBasicWords, @completeBasicWords, @incompleteTripleWords, @completeTripleWords, bonusWords)
      )

      @bonusWord = bonusWords[0]
      @completeWords   = @completeBasicWords.concat(@completeTripleWords)
      @basicWords      = @incompleteBasicWords.concat(@completeBasicWords)
      @incompleteWords = @incompleteBasicWords.concat(@incompleteTripleWords)
      @tripleWords     = @incompleteTripleWords.concat(@completeTripleWords)

      @purgedLettersHash = false
      @purgedWordLists = false

    @hasTripleBonus: () =>
      if (@purgedLettersHash == false || @purgedWordLists == false)
        throw 'IllegalStateException'
      return @completeTripleWords.length > 0

  class WordAnalysis
    constructor: (@rawWordStr, @isBonus) ->
      super()
      @reset()

    reset: () =>
      @isTriple = null
      @isComplete = null
      @displayWordStr = null
      @missingLetterHash = {}
      @missingLetterString = null
      @missingLetterArray = new Array()

    doAnalysis: (revealedLetters, incompleteBasicWords, completeBasicWords, incompleteTripleWords, completeTripleWords, bonusWords) =>
      @isTriple = false
      @isComplete = true

      @displayWordStr =
        @rawWordStr.split('').map(
          (nextChar) ->
            retVal = nextChar
            lowerChar = nextChar.toLowerCase()

            if (revealedLetters[lowerChar] == 1)
              if (nextChar != lowerChar)
                retVal = '#'
                @isTriple = true
              else
                retVal = '*'
            else
              @isComplete = false
              if (@missingLetterHash[lowerChar] != 1)
                @missingLetterHash[lowerChar] = 1
                @missingLetterArray.push(lowerChar);

              if (nextChar != lowerChar)
                @isTriple = true

            return retVal
        ).join('')

      @missingLetterArray = @missingLetterArray.sort()
      @missingLetterString = @missingLetterArray.join('')

      if(@isBonus)
        bonusWords.push(@)
      else if (@isComplete)
        if (@isTriple)
          completeTripleWords.push(@)
        else
          completeBasicWords.push(@)
      else
        if (@isTriple)
          incompleteTripleWords.push(@)
        else
          incompleteBasicWords.push(@)


  class ProgressReportModel
    constructor: (@ticketId, analysisModel, @updatePromise) ->
      if (analysisModel?)
        tempWordMap = {}
        buildWordMapFn = (wordAnalysis) -> tempWordMap[wordAnalysis.rawWordStr] = new WordReport(wordAnalysis)
        analysisModel.allWords.forEach buildWordMapFn

        @bonusWord = tempWordMap
        deRefWordMapFn = (wordAnalysis) -> return tempWordMap[wordAnalysis.rawWordStr]
        @allCompleteBasicWords = analysisModel.allCompleteBasicWords.map(deRefWordMapFn)
        @allCompleteTripleWords = analysisModel.allCompleteTripleWords.map(deRefWordMapFn)
        @allIncompleteBasicWords = analysisModel.allIncompleteBasicWords.map(deRefWordMapFn)
        @allIncompleteTripleWords = analysisModel.allIncompleteTripleWords.map(deRefWordMapFn)

  class WordReport
    constructor: (wordAnalysis) ->
      @rawStr = wordAnalysis.rawWordStr
      @displayStr = wordAnalysis.displayWordStr
      @missingLetterHash = wordAnalysis.missingLetterHash
      @missingLetterArray = wordAnalysis.missingLetterArray

  class PayoutReportModel
    constructor: (@ticketId, @prizeCounts) ->

##
## Ticket Document Model
##

  class TicketVariantKind
    @UNKNOWN: 'unknown'
    @TRIPLING_NO_TWENTY: 'tripleNoTwenty'
    @TRIPLING_WITH_SPOT: 'tripleWithSpot'
    @FIVE_X: 'fiveX'

  class TicketDocument
    constructor: (@ticketId, @content, @yourLetters, @payoutValue, @variantData) ->
      changeVariantKind: (variantKind) ->
        origVariantKind = @variantData?.variantKind || TicketVariantKind.UNKNOWN
        if (origVariantKind != variantKind)
          if (origVariantKind != TicketVariantKind.UNKNOWN)
            origVariantData = @variantData[origVariantKind]

          switch variantKind
            when TicketVariantKind.TRIPLING_NO_TWENTY
              @variantData =
                variantKind: variantKind
                tripleNoTwenty:
                  bonusWord: origVariantData?.bonusWord || '_____'
                  bonusValue: origVariantData?.bonusValue || -1
                tripleWithSpot: null
                fiveX: null
            when TicketVariantKind.TRIPLING_WITH_SPOT
              @variantData =
                variantKind: variantKind
                tripleNoTwenty: null
                tripleWithSpot:
                  bonusWord: origVariantData?.bonusWord || '_____'
                  bonusValue: origVariantData?.bonusValue || -1
                  spotValue: origVariantData?.spotValue || -1
                fiveX: null
            when TicketVariantKind.FIVE_X
              @variantData =
                variantKind: variantKind
                tripleNoTwenty: null
                tripleWithSpot: null
                fiveX:
                  multiplier: -1
                  spotValue: origVariantData?.spotValue || -1
            else
              @variantData =
                variantKind: TicketVariantKind.UNKNOWN
                tripleNoTwenty: null
                tripleWithSpot: null
                fiveX: null

  return appModule
