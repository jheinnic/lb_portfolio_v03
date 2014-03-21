define ['require', 'angular', 'ui-bootstrap-tpls', 'cs!public/enum', 'cs!public/crosswords_dynamic'], (require) ->
  Enum = require('cs!public/enum').Enum

  xwModule = angular.module('crosswords-reports', ['mgcrea.ngStrap.button', 'xw-dynamic']);

  xwModule.directive 'xwTicket', ['xwModelFactory', 'xwDirectiveFactory', 'valueImages', 'borderImages', 'fillImages', (valueImages, borderImages, fillImages) ->
    restrict: 'E'
    replace: true
    scope:
      ticketDocument: '='

    templateUrl: 'public/partials/crosswords/xwTicket.html',
    controller: ($scope) ->
      this.closeActiveCursor = (withCommit) ->
        if (activeGridScope != null)
          tempGridScope = activeGridScope
          activeGridScope = null
          $scope.ticketModel.activeGrid = null

          tempGridScope.$broadcast('xw.gridEvent.closeCursor', withCommit)
          if (withCommit)
            this.updateReportingService()

      this.openActiveCursor = (gridScope, gridModel, cursorCell) ->
        # TODO: Check whether atomicity protection is required for the next two instructions...
        $scope.ticketModel.activeGrid = gridModel
        activeGridScope = gridScope

        activeGridScope.$broadcast('xw.gridEvent.openCursor', cursorCell)
      # gridModel.openCursor cursorCell

      $scope.$on 'xw.gridEvent.cellClicked', (event, gridType, gridModel, cellModel) ->
        closeActiveCursor(true)
        openActiveCursor(event.targetScope, gridModel, cellModel)

      this.isEditableAt = (gridType) ->
        return gridType.isEditableAt($scope.ticketModel.lifecycleStage)

      this.purgeWordList = () ->
        $scope.dataReportingModel.purgeWordList()

      this.purgeLetterList = () ->
        $scope.dataReportingModel.revealedLetters = []
        $scope.dataReportingModel.purgedLetters = true

      this.updateReportingService = () ->

#      this.getGridModel = (gridType) ->
#        retVal
#        switch gridType
#          when GridKind.CROSSWORD
#            retVal = $scope.ticketModel.crosswordGrid
#          when GridKind.YOUR_LETTERS
#            retVal = $scope.ticketModel.yourLettersGrid
#          when GridKind.BONUS_WORD
#            retVal = $scope.ticketModel.bonusWordGrid
#          else
#            retVal = null
#        return retVal
       this.getGridModel = (gridTypeName) ->
         retVal =
#      this.getCellModel = (gridType, rowId, colId) ->
#        retVal
#        switch gridType
#          when GridKind.CROSSWORD
#            retVal = $scope.ticketModel.crosswordGrid.cells[rowId][colId]
#          when GridKind.YOUR_LETTERS
#            retVal = $scope.ticketModel.yourLettersGrid.cells[rowId][colId]
#          when GridKind.BONUS_WORD
#            retVal = $scope.ticketModel.bonusWordGrid.cells[rowId][colId]
#          else
#            retVal = null
#        return retVal

      return this
  ]

  xwModule.directive 'xwBonusValueCell', () ->
    restrict: 'E'
    replace: true
    require: '^xwTicket'
    scope: false
    templateUrl: 'public/partials/crosswords/bonusValueCell.html'

  xwModule.directive 'xwYourLettersGrid', () ->
    restrict: 'E'
    replace: true
    require: '^xwTicket'
    scope: true
    templateUrl: 'public/partials/crosswords/yourLettersGrid.html'
    link: ($scope, $elem, $attrs, ticketCtrl) ->
      $scope.gridModel = ticketCtrl.getGridModel(GridKind.YOUR_LETTERS)
      $scope.$on 'xw.gridEvent.closeCursor', ($event, withCommit) ->
        $scope.gridModel.closeCursor

      $scope.$on 'xw.gridEvent.openCursor', ($event, cursorCell) ->
        console.log('Before open cursor call on your letters grid model')
        $scope.gridModel.openCursor cursorCell
        console.log('After open cursor call on your letters grid model')
      return

  xwModule.directive 'xwYourLettersCell', () ->
    restrict: 'E'
    replace: true
    require: '^xwTicket'
    scope: true
    templateUrl: '/public/partials/crosswords/yourLettersCell.html'
    link: ($scope, $elem, $attrs, ticketCtrl) ->
      $scope.cellModel = ticketCtrl.getCellModel GridKind.YOUR_LETTERS, parseInt($attrs.rowid), parseInt($attrs.colid)
      $scope.onCellClick = () ->
        cellModel = $scope.cellModel
        $scope.$emit 'xw.gridEvent.cellClicked', GridKind.YOUR_LETTERS, cellModel.parentGrid, cellModel
      $scope.$on 'xw.gridEvent.closeCursor', (event, withCommit) ->
        $scope.cellModel.onCloseCursor(withCommit)
      $scope.$on 'xw.gridEvent.openCursor', (event, cursorCell) ->
        console.log('Cell model receives open cursor event')
        if ($scope.cellModel == cursorCell)
          console.log('Before open cursor call on your letters cell model')
          cursorCell.onOpenCursor()
          console.log('After open cursor call on your letters grid model')
        console.log('Before open cursor call on your letters grid model')

      return

  xwModule.directive 'xwBonusWordGrid', () ->
    restrict: 'E'
    replace: true
    require: '^xwTicket'
    scope: true
    templateUrl: 'public/partials/crosswords/bonusWordGrid.html'
    link: ($scope, $elem, $attrs, ticketCtrl) ->
      $scope.gridModel = ticketCtrl.getGridModel(GridKind.BONUS_WORD)
      $scope.$on 'xw.gridEvent.closeCursor', ($event, withCommit) ->
        $scope.gridModel.closeCursor(withCommit)
      $scope.$on 'xw.gridEvent.openCursor', ($event, cursorCell) ->
        $scope.gridModel.openCursor cursorCell
      return

  xwModule.directive 'xwBonusWordCell', () ->
    restrict: 'E'
    replace: true
    require: '^xwTicket'
    scope: true
    templateUrl: '/public/partials/crosswords/bonusWordCell.html'
    link: ($scope, $elem, $attrs, ticketCtrl) ->
      $scope.cellModel = ticketCtrl.getCellModel GridKind.BONUS_WORD, parseInt($attrs.rowid), parseInt($attrs.colid)
      $scope.onCellClick = () ->
        cellModel = $scope.cellModel
        $scope.$emit 'xw.gridEvent.cellClicked', GridKind.BONUS_WORD, cellModel.parentGrid, cellModel
      $scope.$on 'xw.gridEvent.closeCursor', ($event, withCommit) ->
        $scope.cellModel.onCloseCursor(withCommit)
      $scope.$on 'xw.gridEvent.openCursor', ($event, cursorCell) ->
        if ($scope.cellModel == cursorCell)
          cursorCell.onOpenCursor()
      return


  xwModule.directive 'xwCrosswordGrid', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.registerGrid(GridKind.CROSSWORD)
  ]

  xwModule.directive 'xwCrosswordCell', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.registerGrid(GridKind.CROSSWORD)
  ]

  xwModule.directive 'xwProgressReport', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.registerReport('progress')
  ]

  xwModule.directive 'xwPayoutReport', ['xwDirectiveFactory', (xwDirectiveFactory) ->
    return xwDirectiveFactory.registerReport('payout')
  ]

  xwModule.directive 'xwBonusValue', () ->
    restrict: 'E'
    replace: true
    require: '^xwTicket'
    scope: false
    templateUrl: 'public/partials/crosswords/bonusValue.html'

  xwModule.directive 'cellImg', () ->
    restrict: 'E'
    replace: true
    template: (tElem, tAttr) ->
      return '<img ng-src="' + tAttr.map + '[' + tAttr.key + ']">'

  xwModule.value 'valueImages', () ->
    a: 'public/images/val/A.png'
    b: 'public/images/val/B.png'
    c: 'public/images/val/C.png'
    d: 'public/images/val/D.png'
    e: 'public/images/val/E.png'
    f: 'public/images/val/F.png'
    g: 'public/images/val/G.png'
    h: 'public/images/val/H.png'
    i: 'public/images/val/I.png'
    j: 'public/images/val/J.png'
    k: 'public/images/val/K.png'
    l: 'public/images/val/L.png'
    m: 'public/images/val/M.png'
    n: 'public/images/val/N.png'
    o: 'public/images/val/O.png'
    p: 'public/images/val/P.png'
    w: 'public/images/val/Q.png'
    r: 'public/images/val/R.png'
    s: 'public/images/val/S.png'
    t: 'public/images/val/T.png'
    u: 'public/images/val/U.png'
    v: 'public/images/val/V.png'
    w: 'public/images/val/W.png'
    x: 'public/images/val/X.png'
    y: 'public/images/val/Y.png'
    z: 'public/images/val/Z.png'
    A: 'public/images/gval/A.png'
    B: 'public/images/gval/B.png'
    C: 'public/images/gval/C.png'
    D: 'public/images/gval/D.png'
    E: 'public/images/gval/E.png'
    F: 'public/images/gval/F.png'
    G: 'public/images/gval/G.png'
    H: 'public/images/gval/H.png'
    I: 'public/images/gval/I.png'
    J: 'public/images/gval/J.png'
    K: 'public/images/gval/K.png'
    L: 'public/images/gval/L.png'
    M: 'public/images/gval/M.png'
    N: 'public/images/gval/N.png'
    O: 'public/images/gval/O.png'
    P: 'public/images/gval/P.png'
    Q: 'public/images/gval/Q.png'
    R: 'public/images/gval/R.png'
    S: 'public/images/gval/S.png'
    T: 'public/images/gval/T.png'
    U: 'public/images/gval/U.png'
    V: 'public/images/gval/V.png'
    W: 'public/images/gval/W.png'
    X: 'public/images/gval/X.png'
    Y: 'public/images/gval/Y.png'
    Z: 'public/images/gval/Z.png'
    _: 'public/images/val/blank.png'
    '-': 'public/images/gval/qm.png'
    '?': 'public/images/val/qm.png'
    undefined: "/public/images/val/blank.png"

  xwModule.value 'borderImages', () =>
    htop: "/public/images/border/htop.png"
    hmid: "/public/images/border/hmid.png"
    hend: "/public/images/border/hend.png"
    vtop: "/public/images/border/vtop.png"
    vmid: "/public/images/border/vmid.png"
    vend: "/public/images/border/vend.png"
    undefined: "/public/images/val/blank.png"

  xwModule.value 'fillImages', () =>
    revealed: "/public/images/fill/brite1.png"
    selected: "/public/images/fill/selected1.png"
    triple: "/public/images/fill/imageSrc.png"
    blocked: "/public/images/fill/locked.png"
    tooshort: "/public/images/fill/altPath.png"
    tripshort: "/public/images/fill/golden.png"
    dualtrip: "/public/images/fill/cornsilk.png"
    dualtripshort: "/public/images/fill/briteblue.png"
    baseline: "/public/images/fill/pink2.png"
    undefined: "/public/images/fill/pink2.png"

  xwModule.controller 'CrosswordCtrl', ['$scope', 'xwReportSvc', ($scope, xwReportSvc) ->
    $scope.documentModel = new TicketDocument()

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

  # TODO: Add boundary pointers and fill state
  # TODO: Figure out how content changes, on commit, will update the post model and trigger validation.
  #       -- Likely involves prototypical inheritance for crosswordGrid to invoke ticketModel.updateCrosswordCells() ?

  class XwReportService
    constructor: ($q) ->
      @$q = $q
      @progressReportDeferal = $q.defer()
      @progressReportObject = new ProgressReportModel( null, null, @progressReportDeferal.promise )
      @payoutReportDeferal = $q.defer()
      @payoutReportObject = new PayoutReportModel( null, null, @payoutReportDeferal.promise )

    getProgressReport: () ->
      return @progressReportObject

    updateProgressReport: (ticketHref, detail) ->
      tempDeferal = @progressReportDeferal
      @progressReportDeferal = $q.defer()
      @progressReportObject = new ProgressReportModel( ticketHref, detail, @progressReportDeferal.promise )
      tempDeferal.resolve(@progressReportObject)

    getPayoutReport: () ->
      return @payoutReportObject

    updatePayoutReport: (ticketHref, detail) ->
      tempDeferal = @payoutReportDeferal
      @payoutReportDeferal = $q.defer()
      @payoutReportObject = new PayoutReportModel( ticketHref, detail, @payoutReportDeferal.promise )
      tempDeferal.resolve(@payoutReportObject)

  xwModule.service 'xwReportSvc', ['$q', XwReportService]


  ##
  ## Model Classes
  ##

  class ProgressAnalysisModel
    constructor: (@ticketHref) ->
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
      @incompleteWords    = null
      @completeWords      = null
      @tripleWords        = null
      @basicWords         = null

    setBonusWord: (rawWordStr) =>
      if (@purgedWordLists == false)
        throw "IllegalStateException"
      else if (@bonusWord?)
        throw "IllegalStateException"

      @bonusWord = new WordAnalysis(rawWordStr,true)
      @allWords.push(@bonusWord)

    addWord: (rawWordStr) =>
      if (@purgedWordLists == false)
        throw "IllegalStateException"

      @allWords.push(new WordAnalysis(rawWordStr,false))

    addRevealedLetter: (revealedLetter) =>
      if (@purgedLettersHash == false)
        throw "IllegalStateException"

      @revealedLettersHash[revealedLetter] = 1

    refreshAnalysis: () =>
      if (@purgedLettersHash == true && @purgedWordLists == true)
        throw "IllegalStateException"

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
        throw "IllegalStateException"
      return @completeTripleWords.length > 0

  class WordAnalysis
    constructor: (@rawWordStr, @isBonus) ->
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
    constructor: (@ticketHref, analysisModel, @updatePromise) ->
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


  class TicketDocument
    constructor: () ->
      @foo = 2

  class PayoutReportModel
    prizeCounts: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]

  class OrientationKind extends Enum
    @_size: 0
    @_VALUES: {@ALIGNED, @ROTATED}

    @ALIGNED = new OrientationKind('htop', 'hmid', 'hend')
    @ROTATED = new OrientationKind('vtop', 'vmid', 'vend')

    constructor: (@headBorder, @midBorder, @tailBorder) ->
      super

    getHeadBorder: () =>
      return @headBorder

    getMidBorder: () =>
      return @midBorder

    getTailBorder: () =>
      return @tailBorder

    getOpposite: () =>
      switch @
        when @ALIGNED
          return @ROTATED
        when @ROTATED
          return @ALIGNED
        else
      return null


  class LifecycleStage extends Enum
    @_size: 0
    @_VALUES: {@IDENTIFYING, @DESCRIBING, @PLAYING, @PLAYING_CALCULATING, @CALCULATING, @VERIFYING, @PUBLISHED}

    @IDENTIFYING: new LifecycleStage()
    @DESCRIBING: new LifecycleStage()
    @PLAYING: new LifecycleStage()
    @PLAYING_CALCULATING: new LifecycleStage()
    @CALCULATING: new LifecycleStage()
    @VERIFYING: new LifecycleStage()
    @PUBLISHED: new LifecycleStage()

    isTicketIdEditable: () =>
      return @ == @IDENTIFYING

    isCrosswordGridEditable: () =>
      return @ == @DESCRIBING

    isBonusWordGridEditable: () =>
      return @ == @DESCRIBING

    isTwentySpotAvailableEditable: () =>
      return @ == @DESCRIBING

    isBonusWordValueEditable: () =>
      return @ == @PLAYING || @ == @PLAYING_CALCULATING || @ == @VERIFYING

    isTwentySpotHitEditable: () =>
      return @ == @PLAYING || @ == @PLAYING_CALCULATING || @ == @VERIFYING

    isYourLetterGridEditable: () =>
      return @ == @PLAYING || @ == @PLAYING_CALCULATING || @ == @VERIFYING

    isPayoutEditable: () =>
      return @ == @PLAYING_CALCULATING || @ == @CALCULATING

    arePredictionsEditable: () =>
      return @ == @PLAYING_CALCULATING || @ == @CALCULATING

  class GridKind extends Enum
    @_size: 0
    @_VALUES: {@YOUR_LETTERS, @BONUS_WORD, @CROSSWORD}

    @YOUR_LETTERS = new GridKind([LifecycleStage.PLAYING, LifecycleStage.PLAYING_CALCULATING, LifecycleStage.VERIFYING], 'yourLetters')
    @BONUS_WORD = new GridKind([LifecycleStage.DESCRIBING], 'bonusWord')
    @CROSSWORD =  new GridKind([LifecycleStage.DESCRIBING], 'crossword')

    constructor: (@whenEditable, @discriminatorString) ->
      super

    isEditableAt: (lifeCycleStage) ->
      return @whenEditable.contains(lifeCycleStage)

    getGridTypeName: () ->
      return @discriminatorString + 'Grid'

    getCellTypeName: () ->
      return @discriminatorString + 'Cell'


  class CellStateKind extends Enum
    @_size: 0
    @_VALUES: {@COVERED, @MATCHED, @TRIPLE_COVERED, @TRIPLE_MATCHED, @REVEALED, @BLOCKED, @SELECTED, @INVALID}

    @COVERED = new CellStateKind('baseline')
    @MATCHED = new CellStateKind('revealed')
    @TRIPLE_COVERED = new CellStateKind('triple')
    @TRIPLE_MATCHED = new CellStateKind('revealed')
    @REVEALED = new CellStateKind('revealed')
    @BLOCKED = new CellStateKind('blocked')
    @SELECTED = new CellStateKind('selected')
    @INVALID = new CellStateKind('tooshort')

    constructor: (@fillImageKey) ->
      super


  class CellErrorKind extends Enum
    @_size: 0
    @_VALUES: {@ISOLATED_LETTER, @SHORT_WORD, @FULL_QUAD, @TRIPLE_INTERSECTION, @TOO_MANY_TRIPLES, @SHORT_BONUS_WORD, @REPEAT_BONUS_LETTER}

    @ISOLATED_LETTER = new CellErrorKind('Isolated letter tiles are not allowed.  Letters tiles must be adjacent to enough other letters tiles to create at least one three letter word.')
    @SHORT_WORD = new CellErrorKind('Two letter words are not allowed.  Adjacent letter tiles define words, but all words must have at least three letters in length.')
    @FULL_QUAD = new CellErrorKind('No more than 3 of the 4 tiles from any 2x2 subunit may validly contain a letter')
    @TRIPLE_INTERSECTION = new CellErrorKind('Triple bonus modifiers may not appear in any shared intersection of two distinct words.')
    @TOO_MANY_TRIPLES = new CellErrorKind('Exactly four cells are required to contain triple modifiers, but too many are in use.  Reduce the number to four.')
    @SHORT_BONUS_WORD = new CellErrorKind('The bonus word must contain exactly five letters, but fewer than five have been given.')
    @REPEAT_BONUS_LETTER = new CellErrorKind('No two letters of the bonus word can be the same.  All five letters must be unique.')

    constructor: (@toolTipMessage) ->
      super


  class TicketErrorKind extends Enum
    @_size: 0
    @_VALUES: {@MISSING_IDENTITY, @INVALID_IDENTITY, @TOO_MANY_WORDS, @NOT_ENOUGH_WORDS, @NOT_ENOUGH_TRIPLES, @BONUS_WORD_UNDEFINED, @UNKNOWN_BONUS_VALUE, @BONUS_VALUE_UNDEFINED, @TWENTY_SPOT_UNDEFINED, @NO_VALID_OUTCOME}

    @MISSING_IDENTITY = new TicketErrorKind('The ticket\'s identifier has not been set.  Identifiers require a three digit gameId, a colon ( =), a 6 or 7 digit spoolId, a dash (-), then a three digit ticketId.')
    @INVALID_IDENTITY = new TicketErrorKind('The ticket\'s identifier is malformed.  Identifiers require a three digit gameId, a colon ( =), a 6 or 7 digit spoolId, a dash (-), then a three digit ticketId.')
    @TOO_MANY_WORDS = new TicketErrorKind('The crossword grid must contain exactly 22 words, 11 horizontal and 11 vertical.  There are too many words right now for this ticket to be played.')
    @NOT_ENOUGH_WORDS = new TicketErrorKind('The crossword grid must contain exactly 22 words, 11 horizontal and 11 vertical.  There aren\'t enough words yet for this ticket to be played.')
    @NOT_ENOUGH_TRIPLES = new TicketErrorKind('The crossword grid must contain exactly four tripling modifiers, each of which must be in a cell that is part of only one word.  More triple modifiers are required before this ticket can be played.')
    @BONUS_WORD_UNDEFINED = new TicketErrorKind('The bonus word content is still undefined and is required before this ticket can be played.');
    @UNKNOWN_BONUS_VALUE = new TicketErrorKind('The bonus word\'s prize value is not recognized and cannot be used.  Try re-assigning the bonus prize value to correct the problem.');
    @BONUS_VALUE_UNDEFINED = new TicketErrorKind('The bonus word\'s prize value is required before this ticket can be marked as complete.')
    @TWENTY_SPOT_UNDEFINED = new TicketErrorKind('The presence or omission of a "fast twenty" spot must be clarified before this ticket can be played.')
    @NO_VALID_OUTCOME = new TicketErrorKind('There is no permutation of available letters left that yields either a non-winner or valid payout result.')

    constructor: (@toolTipMessage) ->
      super


  class TicketModel
    constructor: (@lifecycleStage, @yourLettersGrid, @bonusWordGrid, @crosswordGrid) ->
      @ticketHref = null
      @hasTwentySpot = null
      @hitTwentySpot = null
      @bonusValue = null
      @payoutValue = null
      @activeGrid = null
      @ticketErrors = {}

    mayChangeLifecycle = (toStage) =>
      switch @lifecycleStage
        when LifecycleStage.IDENTIFYING
          if(toStage == LifecycleStage.DESCRIBING)
            return identity?
        when LifecycleStage.DESCRIBING
          if (toStage == LifecycleStage.PLAYING)
            return @crosswordGrid.isComplete() && @bonusWordGrid.isComplete() && @hasTwentySpot?
          else if(toStage == LifecycleStage.CALCULATING)
            return @crosswordGrid.isComplete() && @bonusWordGrid.isComplete() && @yourLettersGrid.isComplete() &&
            @hasTwentySpot? && @bonusValue? && @hitTwentySpot?
          else if(toStage == LifecycleStage.VERIFYING)
            return @crosswordGrid.isComplete() && @bonusWordGrid.isComplete() && @yourLettersGrid.isComplete() &&
            @hasTwentySpot? && @bonusValue? && @hitTwentySpot? && @payoutValue?
          else if(toStage == LifecycleStage.PUBLISHED)
            return @crosswordGrid.isComplete() && @bonusWordGrid.isComplete() && @yourLettersGrid.isComplete() &&
            @hasTwentySpot? && @bonusValue? && @hitTwentySpot? && @payoutValue?
        when LifecycleStage.PLAYING
          if (toStage == LifecycleStage.PLAYING)
            return true
          else if (toStage == LifecycleStage.PLAYING_CALCULATING)
            return true
          else if (toStage == LifecycleStage.DESCRIBING)
            return true
          else if (toStage == LifecycleStage.VERIFYING)
            return @yourLettersGrid.isComplete() && @bonusValue? && @hitTwentySpot? && @payoutValue?
          else if (toStage == LifecycleStage.PUBLISHED)
            return @yourLettersGrid.isComplete() && @bonusValue? && @hitTwentySpot? && @payoutValue?
        when LifecycleStage.PLAYING_CALCULATING
          if (toStage == LifecycleStage.PLAYING)
            return true
          else if(toStage == LifecycleStage.PLAYING_CALCULATING)
            return true;
          else if(toStage == LifecycleStage.DESCRIBING)
            return true;
          else if (toStage == LifecycleStage.CALCULATING)
            return @yourLettersGrid.isComplete() && @bonusValue? && @hitTwentySpot?
          else if (toStage == LifecycleStage.VERIFYING)
            return @yourLettersGrid.isComplete() && @bonusValue? && @hitTwentySpot? && @payoutValue?
          else if (toStage == LifecycleStage.PUBLISHED)
            return @yourLettersGrid.isComplete() && @bonusValue? && @hitTwentySpot? && @payoutValue?
        when LifecycleStage.CALCULATING
          if (toStage == LifecycleStage.PLAYING)
            return true
          else if (toStage == LifecycleStage.PLAYING_CALCULATING)
            return true
          else if(toStage == LifecycleStage.CALCULATING)
            return true;
          else if(toStage == LifecycleStage.DESCRIBING)
            return true;
          else if(toStage == LifecycleStage.VERIFYING)
            return @payoutValue?
          else if(toStage == LifecycleStage.PUBLISHED)
            return @payoutValue?
        when LifecycleStage.VERIFYING
          if (toStage == LifecycleStage.PLAYING)
            return true
          else if (toStage == LifecycleStage.PLAYING_CALCULATING)
            return true
          else if(toStage == LifecycleStage.DESCRIBING)
            return true;
          else if(toStage == LifecycleStage.VERIFYING)
            return true;
          else if(toStage == LifecycleStage.PUBLISHED)
            return true;
        when LifecycleStage.PUBLISHED
          if (toStage == LifecycleStage.PLAYING)
            return true;
          else if (toStage == LifecycleStage.PLAYING_CALCULATING)
            return true
          else if (toStage == LifecycleStage.DESCRIBING)
            return true;
          else if (toStage == LifecycleStage.VERIFYING)
            return true
          else if (toStage == LifecycleStage.PUBLISHED)
            return true;
        else

      return false

    setLifecycleStage: (nextLifecycleStage) =>
      retVal = nextLifecycleStage
      if @mayAdvanceLifecycle(nextLifecycleStage)
        @lifecycleStage = nextLifecycleStage
      else
        retVal = @lifecycleStage
      return retVal

    @::identityRegex = /\d{3}:\d{6,7}\-\d{3}/

    setIdentity: (newIdentity) =>
      if !@lifecycleStage.isTicketIdEditable()
        throw "IllegalState"
      if !@::identityRegex.test(newIdentity)
        throw "IllegalArgument"

      @identity = newIdentity

    setHasTwentySpot: (hasSpot) =>
      if !@lifecycleStage.isTwentySpotAvailableEditable()
        throw "IllegalState"
      if (typeof hasSpot) != "boolean"
        throw "IllegalArgument"

      @hasTwentySpot = hasSpot

    setHitTwentySpot: (hitSpot) =>
      if !@lifecycleStage.isTwentySpotHitEditable()
        throw "IllegalState"
      if (typeof hitSpot) != "boolean"
        throw "IllegalArgument"

      @hitTwentySpot = hitSpot

    setBonusValue: (newValue) =>
      if !@lifecycleStage.isBonusValueEditable()
        throw "IllegalState"
      if ((typeof newValue) != "number") || newValue < 0 || newValue > 3
        throw "IllegalArgument"

      @bonusValue = newValue

    # TODO: Confirm the min/max valid values
    setPayoutValue: (newValue) =>
      if !@lifecycleStage.isPayoutValueEditable()
        throw "IllegalState"
      if ((typeof newValue) != "number") || newValue < 0 || newValue > 18
        throw "IllegalArgument"

      @bonusValue = newValue


  class AbstractGrid
    ticketModel: null
    dirtyCells: new Array()
    cursor: null

    constructor: (@cells, @numRows, @numCols) -> return

    isActive: () -> return @cursor?

    lookupCell: (rowId, colId) ->
      if (rowId < 0 || rowId >= @numRows || colId < 0 || colId >= @numCols)
        throw "IllegalArgument"
      cellId = colId + (rowId * @numRows)
      return @cells[cellId]

    markDirty: (dirtyCell = @cursor) ->
      if (dirtyCell == null)
        throw "IllegalArgument"
      if (! dirtyCell.dirty)
        @dirtyCells.push(dirtyCell)
        dirtyCell.dirty = true

    # The caller is responsible for confirming that cursorCell is a valid cell for opening a cursor
    openCursor: (cursorCell) =>
      if (cursorCell == null)
        throw "IllegalArgument"
      if (@cursor? || @ticketModel.hasOpenCursor())
        throw "IllegalState"

      @cursor = cursorCell

    closeCursor: (withCommit = true) =>
      if (@cursor == null)
        throw "IllegalState"

      if (withCommit)
        dirtyFn = (dirtyCell) ->
          dirtyCell.commit()
          dirtyCell.dirty = false
      else
        dirtyFn = (dirtyCell) ->
          dirtyCell.rollback()
          dirtyCell.dirty = false

      @dirtyCells.forEach dirtyFn
      @dirtyCells.length = 0
      @cursor = null

    # The caller is responsible for confirming that cursorCell is a valid cell for moving the open cursor to
    moveCursor: (fromCell, toCell) =>
      if (toCell == null)
        throw "IllegalArgument"
      if (@cursor != fromCell)
        throw "IllegalState"

      @cursor = toCell


    # Each subtype MAY override this.  Return false to reject a character.  Return the character as-is or its valid
    # equivalent to accept.
    getLegalValue: (value) -> return value.toLowerCase()

    # TODO: MAY override this if lifecycle state advancement does not depend on a grid having a value in
    #       every cell (ok rule for bonusWord and yourLetters, but not main crossword grid).
    isComplete: () -> return @cells.every (value) -> return value.hasContent()

    # TODO: MAY ovveride this in each subclass to encapsulate click-based cursor movement semantics
    testForMoveCursor: (targetCell) -> return targetCell;

    # TODO: Must override this in each subclass to encapsulate click-based cursor creation semantics
    testForOpenCursor: (targetCell) -> return targetCell;


  class AbstractCell
    dirty: false

    constructor: (parentGrid, rowId, colId, content, state) ->
      @parentGrid = parentGrid
      @rowId = rowId
      @colId = colId
      @content = content
      @state = state

    isBlank: () -> return @content == '_'
    hasContent: () -> return @content != '_'

    # Default behavior without override: set content to the lowercase version of input and then advance cursor to
    # next empty cell, if any.
    handleInput: (value) =>
      legalValue = @parentGrid.getLegalValue value
      if (legalValue? && (legalValue instanceof 'string'))
        @content = legalValue

      nextCell = @getNextCell
      while ((nextCell != @) && (nextCell.hasContent()))
        nextCell = nextCell.getNextCell
      @parentGrid.moveCursor @, nextCell, false

    # This implementation is suitable for fixed-in-place grids, like YourLetters and BonusWord.  CrosswordCell will
    # want a different implementation.
    handleBackspace: () =>
      @content = '_'

    handleLeftArrow: () =>
      @parentGrid.moveCursor @, @getPreviousCell(), false

    handleRightArrow: () =>
      @parentGrid.moveCursor @, @getNextCell(), false

    # Each subtype SHOULD override these to define forward navigation and limit
    getNextCell: () => return this

    # Each subtype SHOULD override these to define backward navigation and limit
    getPreviousCell: () => return this

  #
  # Reusable lambdas
  #
  uniqueAndComplete = (nextContent, nextIndex, sortedArray) ->
    retVal = false
    if( nextContent == '_' )
    else if( nextIndex == 0 )
      retVal = true;
    else
      retVal = nextContent != sortedArray[nextIndex - 1];

    return retVal

  notInOtherCell = (ignoreCell, value) ->
    return (nextContent) ->
      retVal = false
      if( nextContent == ignoreCell )
        retVal = true
      else if( nextContent.content != value )
        retVal = true

      return retVal


  #
  # Bonus Word Grid and Cell
  #
  class BonusWordGrid extends AbstractGrid
    isComplete: () =>
      @cells.map((cell) -> cell.content).sort().every(uniqueAndComplete)

    noOtherCellMatches: (ignoreCell, value) =>
      return

    getLegalValue: (value) =>
      value = value.toLowerCase()
      return @cells.every((value) -> return notInOtherCell(@cursor, value)) ? value: false

  class BonusWordCell extends AbstractCell
    next: null
    previous: null

    constructor: (parentGrid, yAbs, content, state) ->
      super(parentGrid, 0, yAbs, content, state)

    getNextCell: () => return @next
    getPreviousCell: () => return @previous


  #
  # Your Letters Grid and Cell
  #
  class YourLettersGrid extends AbstractGrid
    # TODO: Remove this once @cells is a flat array again
    allCells: () =>
      @cells[0].concat(@cells[1].concat(@cells[2]))

    # TODO: Remove this once @cells is a flat array again
    isComplete: () =>
      @allCells().map((cell) -> cell.content).sort().every(uniqueAndComplete)

    getLegalValue: (value) =>
      value = value.toLowerCase()
      return @allCells().every((value) -> notInOtherCell(@cursor, value)) ? value: false

  class YourLettersCell extends AbstractCell
    next: null
    previous: null

    getNextCell: () => return @next
    getPreviousCell: () => return @previous


  #
  # Main Crossword Grid and Cell
  #
  class CrosswordGrid extends AbstractGrid
    constructor: (rows, cols, fixed) ->
      super(rows)
      @fixed = fixed
      @aligned = rows
      @rotated = cols
      @wordLeft = null
      @wordRight = null
      @cursorLeft = null
      @cursorRight = null
      @orientation = OrientationKind.ALIGNED

    toggleOrientation: () =>
      @wordLeft = null
      @wordRight = null
      @cursorLeft = null
      @cursorRight = null
      @orientation = @orientation.getOpposite()

    cellBorder: (forCellModel) =>
      if (forCellModel == @wordLeft)
        return @orientation.getHeadBorder()
      else if (forCellModel == @wordRight)
        return @orientation.getTailBorder()
      else if (
        (@wordLeft?) && (@wordRight?) &&
        (forCellModel.getRelRowId() == @wordLeft.getRelRowId()) &&
        (forCellModel.getRelColId() > @wordLeft.getRelColId()) &&
        (forCellModel.getRelRowId() == @wordRight.getRelRowId()) &&
        (forCellModel.getRelColId() < @wordRight.getRelColId())
      )
        return @orientation.getMidBorder()

      return 'blank'

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
  class FixedCell extends AbstractCell
    isTriple: null
    alignedWordCell: null
    rotatedWordCell: null

    constructor: (gridModel, rowId, colId, content, state) ->
      super(gridModel, rowId, colId, content, state)
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

    # Being overriden as makes sense, but overriding AbstractCell.handleValue() eliminates their only caller
    getNextCell: () -> @getToRight()
    getPreviousCell: () -> @getToLeft()

    openCursor: () =>
      @parentGrid.wordLeft = @searchLeft(this, true)
      @parentGrid.wordRight = @searchRight(this, true)
      @parentGrid.cursorLeft = @searchLeft(@parentGrid.wordLeft, false)
      @parentGrid.cursorRight = @searchRight(@parentGrid.wordRight, false)

    searchLeft: (startCell, requireContent) =>
      if startCell == null
        throw "IllegalArgument"

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
  ## Enum and Module Base Classes
  ##
#  class Enum
#    @_size: 0
#    @_VALUES: {}
#
#    _name: undefined
#    _ordinal: undefined
#
#    constructor: () ->
#      Class = @getSuperclass()
#      @_name = Object.keys(Class._VALUES)[Class._size]
#      @_ordinal = Class._size
#      Class._size += 1
#      Class._VALUES[@_name] = this
#
#    @values: () -> return Object.keys(@_VALUES)
#    @valueOf: (name) -> return @_VALUES[name]
#
#    name: () -> return @_name
#    ordinal: () -> return @_ordinal
#    compareTo: (other) -> return @_ordinal - other._ordinal
#    equals: (other) -> return this == other
#    toString: () -> return @_name + "(" + @_ordinal + ")"
#    getClass: () -> return this.constructor
#    getSuperclass: () -> return @getClass().__super__.constructor
#
#
#  class Module
#    moduleKeywords = ['extended', 'included']
#
#    @extend: (obj) ->
#      for key, value of obj when key not in moduleKeywords
#        @[key] = value
#
#      obj.extended?.apply(@)
#      return this
#
#    @include: (obj) ->
#      for key, value of obj when key not in moduleKeywords
#        # Assign properties to the prototype
#        @::[key] = value
#
#      obj.included?.apply(@)
#      return this
#

  return xwModule