function PendingTicket() {
    this.boardContent = "_________________________________________________________________________________________________________________________".split('');
    this.bonusWord = null;
    this.twentySpotIncluded = null;

    const tripleMarkerRegex = /[A-Z-]/;
    const basicMarkerRegex = /[a-z_]/;
    const validMarkerRegex = /[a-zA-Z_-]/;

    const tripleWordRegex = /^(([a-z]+[A-Z][a-z]+)|([A-Z][a-z]{2,})|([a-z]{2,}[A-Z]))$/;
    const basicWordRegex = /^[a-z]{3,}$/;
    const validWordRegex = /^([a-z]{3,}|([a-z]+[A-Z][a-z]+)|([A-Z][a-z]{2,})|([a-z]{2,}[A-Z]))$/;

    const validBoardRegex1 = /^[a-zA-Z_-]{121}$/;
    const validBoardRegex2 = /^([a-z_]*[A-Z-]){0,4}$/;
    const invalidBoardRegex1 = /[a-zA-Z-][a-zA-Z-].{9}[a-zA-Z-][a-zA-Z-]/;
    const invalidBoardRegex2 = /^(.{11})*(.*_[a-zA-Z-]{2,2}_.*)|([a-zA-Z-]{2,2}_.{8})|(.{8}_[a-zA-Z-]{2,2})(.{11})*$/;
    const invalidBoardRegex3 = /^((.{11})*(.*_.{11}[a-zA-Z-].{11}[a-zA-Z-].{11}_.*)(.{11})*)|(.{0,10}[a-zA-Z-].{11}[a-zA-Z-].{11}_.{0,10}.{88,88})|(.{88,88}.{0,10}_.{11}[a-zA-Z-].{11}[a-zA-Z-].{0,10})$/;

    this.setBoardContent = function setBoardContent(boardContent) {
        if( boardContent.length != 121 ) { throw "IllegalArgument"; }

        this.boardContent = boardContent.split('');
        this.cachedWords = null;
    }

    this.setBoardContentElement = function setBoardContentElement( rowId, colId, newVal ) {
        if( (typeof newVal) !== 'string' && (newVal != null) ) { throw "IllegalArgument"; }
        if( newVal == null ) {
            newVal = '_';
        } else if( newVal.length != 1 ) { throw "IllegalArgument"; }

        var offset = rowId*11 + colId;
        this.boardContent[offset] = newVal;

        // Invalidate any previously cached word list.
        this.cachedWords = null;
    }

    this.getBoardContentElement = function getBoardContentElement( rowId, colId ) {
        var offset = rowId*11 + colId;

        return this.boardContent[offset];
    }

    function isValidWord(subStr) {
        return ((typeof subStr) == 'string') && (subStr.length > 2);
    }

    this.getBonusWord = function getBonusWord() {
        return this.bonusWord;
    }

    this.setBonusWord = function setBonusWord(newBonusWord) {
        var isValid = true;
        if(((typeof newBonusWord) == 'string') && (newBonusWord.length == 5)) {
            var sortedChars = newBonusWord.split('').sort;
            for( var ii=0, jj=1; ii<4; ii=jj, jj++ ) {
                if( sortedChars[ii] == sortedChars[jj] ) {
                    isValid = false;
                    break;
                }
            }
        } else {
            isValid = false;
        }

        if( isValid ) {
            this.bonusWord = newBonusWord;
        } else {
            throw "IllegalArgument";
        }
    }

    this.isSetBonusWord = function isSetBonusWord() {
        return this.bonusWord != null;
    }

    this.unsetBonusWord = function unsetBonusWord() {
        this.bonusWord = null;
    }

    this.isTwentySpotIncluded = function isTwentySpotIncluded() {
        return this.twentySpotIncluded;
    }

    this.setTwentySpotIncluded = function setTwentySpotIncluded( boolVal ) {
        if( (typeof boolVal) != 'boolean' ) {
            throw "IllegalArgument";
        }

        this.twentySpotIncluded = boolVal;
    }

    this.isTwentySpotIncludedSet = function isTwentySpotIncludedSet() {
        return this.twentySpotIncluded != null;
    }
    this.unsetTwentySpotIncluded = function unsetTwentySpotIncluded() {
        this.twentySpotIncluded = null;
    }

    function isBasicWord(subStr) {
        return !tripleMarkerRegex.test(subStr);
    }

    function isTripleWord(subStr) {
        return tripleMarkerRegex.test(subStr);
    }

    this.getAllWords = function getAllWords() {
        if( this.cachedWords != null ) {
            return new Array(0).concat(this.cachedWords);
        }

        var rotated = this.boardContent.map(
            function transpose( val, idx, board ) {
                var rowId = idx % 11;
                var colId = ((idx-rowId) / 11);

                return board[(rowId*11) + colId];
            }
        );

        var runs = new Array(0);
        for( var ii=0; ii<121; ii+=11 ) {
            var jj = ii+10;
            runs = runs.concat(
                this.board.slice(ii, jj).join('').split(/_+/).filter(isValidWord).concat(
                    rotated.slice(ii,jj).join('').split(/_+/).filter(isValidWord)
                )
            );
        }

        this.cachedWords = runs;
        return new Array(0).concat(runs);
    }

    this.getBasicWords = function getBasicWords() {
        getAllWords().filter(isBasicWord);
    }

    this.getTripleWords = function getTripleWords() {
        getAllWords().filter(isTripleWord);
    }


    this.isPublishable = function isPublishable() {
        if( this.bonusWord != null && this.twentySpotIncluded != null ) {
            if( this.getAllWords().length == 22 && this.getTripleWords().length == 4 ) {
                return true;
            }
        }

        return false;
    };

    return this;
}
