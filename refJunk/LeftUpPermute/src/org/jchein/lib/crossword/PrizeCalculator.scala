package org.jchein.lib.crossword
import org.jchein.lib.count.nchosem.Twiddler
import org.jchein.lib.count.nchosem.CollectionTwiddler

class PrizeCalculator( wordMix : WordSet ) {

	  // Map each possible payout value to the array index where its incidents are tracked
	  // Element 0 is used for valid non-winning combinations.  Elements 1 through 16 are used for valid
	  // payout amounts.  Element 17 is used for combinations that satisfy the criteria for a win, but
	  // do not match a valid pay-out dollar value.
	  // 
	  // For permutations that hit the bonus word, the bonus value affects whether the permutation 
	  // is possible and what value it pays out at if so.  The bonus value is an unknown, so we track
	  // the distribution of winners for all possibilities and report them separately.
	private val TRIPLE_MATCH_PAYOUT_OFFSET = 110;
	private val BONUS_TO_PAYOUT_OFFSET:List[Int] = List( 22, 44, 66, 88 );
	private val PAYOUT_LEVEL_LOOKUP:Array[Int] = Array(
	    // No Bonus, No Triple 
	    0, 0, 1, 2, 3, 4, 7, 9, 11, 14, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
	    // $4 Bonus, No Triple
	    2, 2, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 
	    // $5 Bonus, No Triple
	    3, 3, 17, 17, 4, 6, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
	    // $10 Bonus, No Triple
	    4, 4, 17, 17, 6, 7, 8, 10, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
	    // $30 Bonus, No Triple
	    8, 8, 17, 17, 17, 17, 9, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
	    // No Bonus, With Triple
	    17, 0, 17, 5, 6, 8, 10, 12, 13, 15, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
	    // $4 Bonus, With Triple
	    17, 2, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
	    // $5 Bonus, With Triple
	    17, 3, 17, 17, 7, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
	    // $10 Bonus, With Triple
	    17, 4, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
	    // $30 Bonus, No Triple
	    17, 8, 17, 17, 17, 10, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17
    );
	private val NUM_PAYOUT_LEVELS = 18;   // Counting one for an "Invalid catch-all"  !!
	private val FINAL_PAYOUT_INDEX = 17;
	private val BONUS_LEVEL_VALUES : List[Int] = List(4, 5, 10, 30)
	
	private val NUM_BONUS_LEVELS = 4
	private val FINAL_BONUS_INDEX = 3;
    private val PAYOUT_LEVEL_VALUES : List[Int] = 
        List( 0, 3, 4, 5, 10, 12, 15, 20, 30, 50, 60, 100, 150, 300, 1000, 3000, 20000, -1 );

    private val ALPHABET:List[Char] =   
	    List( 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 
	    'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

	private val WORDS_PER_TICKET = 22
	private val LETTERS_ABSENT_PER_TICKET = 8
    private val LETTERS_PRESENT_PER_TICKET = 18
    private val FINAL_KNOWN_LETTER_INDEX = 17
	
    private val EMPTY_ARRAY:List[Char] = List( );

    private val bonusPayoutDist : List[Array[Int]] = 
		BONUS_LEVEL_VALUES.map( value => Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) )
	private val basicPayoutDist : Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

	// For permutations that hit the bonus word, the bonus value affects whether the permutation 
	// is possible and what value it pays out at if so.  The bonus value is an unknown, so we track
	// the distribution of winners for all possibilities and report them separately.
	// private val numBonusValues : Int = 4

    def clearLastCalculation() : Unit = {
        for( i <- 0 to FINAL_PAYOUT_INDEX ) { 
            basicPayoutDist(i) = 0;
        }
        for( i <- 0 to FINAL_BONUS_INDEX ) { 
            for( j <- 0 to FINAL_PAYOUT_INDEX ) { bonusPayoutDist(i)(j) = 0; }
        }
    }
    
	def calculateDistribution(knownLetters:Set[Char], possibleBonusValues:List[Int], detailedReport:Boolean) : Unit = {
	    clearLastCalculation();

		if( knownLetters.size > LETTERS_PRESENT_PER_TICKET ) {
			System.out.println( "Invalid request.  No more than 18 letters exist to be revealed." );
			return;
		}

		val numUnusedLetters = ALPHABET.size - knownLetters.size;
		val numLettersToPick = LETTERS_PRESENT_PER_TICKET - knownLetters.size;

		val nextTicket:Array[Char] = new Array[Char](LETTERS_PRESENT_PER_TICKET);
		val unusedLetters:Array[Char] = new Array[Char](numUnusedLetters);
		val knownLettersList:List[Char] = knownLetters.toList

		var j:Int = 0;
		for( i <- 0 until ALPHABET.size ) {
			if(knownLetters.contains(ALPHABET(i)) == false) {
				unusedLetters(j) = ALPHABET(i);
				j += 1;
			}
		}
		for( i <- numLettersToPick until LETTERS_PRESENT_PER_TICKET ) { 
			nextTicket(i) = knownLettersList(LETTERS_PRESENT_PER_TICKET-i-1);
		}
		for( i <- 0 until numLettersToPick ) {
			nextTicket(i) = unusedLetters(LETTERS_ABSENT_PER_TICKET+i);
		}

		// TODO: Find a way to invoke init() as a side effect of the constructor!  It should be do-able.
		val permuteEngine : Twiddler = 
		    new CollectionTwiddler( nextTicket, unusedLetters, numLettersToPick );
		permuteEngine.init();
		
		do {
		    val( numWords:Int, isTripled:Boolean, hasBonusMatch:Boolean) = 
		        wordMix.calculateHits(nextTicket, detailedReport);
		      
		    var offsetBeforeBonus:Int = 0;
		      if (isTripled) {
		        offsetBeforeBonus = numWords + TRIPLE_MATCH_PAYOUT_OFFSET;
		      } else{  
		        offsetBeforeBonus = numWords;
		      }
		      
		    if (hasBonusMatch) {
		    	for(bonusLevel <- possibleBonusValues) {
		    	  val payout_tier:Int = 
		    	      PAYOUT_LEVEL_LOOKUP(offsetBeforeBonus + BONUS_TO_PAYOUT_OFFSET(bonusLevel));
		    	  bonusPayoutDist(bonusLevel)(payout_tier) += 1; 
		    	  
		    	  // if(payout_tier == 17 || payout_tier == 8) {
		    	  //     val ticketString = nextTicket.map( c => c.toString ).reduce[String]( (c:String,str:String) => str + ", " + c );
		    	  //     System.out.println( "Hit payout tier of <" + payout_tier + "> on input of <" + ticketString +"> for bonus level <" + bonusLevel + "> on <" + numWords + ">, <" + isTripled + ">" );
		    	  // }
		    	}
		    } else {
                val payout_tier:Int = PAYOUT_LEVEL_LOOKUP(offsetBeforeBonus);
                basicPayoutDist(payout_tier) += 1;
		    	 // if(payout_tier == 17 || payout_tier == 8) {
		    	 //     val ticketString = nextTicket.map( c => c.toString ).reduce[String]( (c:String,str:String) => str + ", " + c );
		    	 //     System.out.println( "Hit payout tier of <" + payout_tier + "> on input of <" + ticketString +"> without bonus on <" + numWords + ">, <" + isTripled + ">" );
		    	 // }
		    }
		} while( permuteEngine.next() )
	}
	
	def printLastDistribution(possibleBonusValues:List[Int]) : Unit = {
		val output:StringBuffer = new StringBuffer(1024);

		for(bonusLevel <- possibleBonusValues) {
			output.append("For a bonus spot value of <").append(BONUS_LEVEL_VALUES(bonusLevel)).append(">, the distribution is:\n");
			var total:Int = 0;
			for( payoutTier <- (0 to FINAL_PAYOUT_INDEX) ) {
				val numCombos:Int = bonusPayoutDist(bonusLevel)(payoutTier) + basicPayoutDist(payoutTier)
				val payoutValue = PAYOUT_LEVEL_VALUES(payoutTier)
				total += numCombos;

				output.append("** <").append(numCombos);
				if(payoutValue == 0)
					output.append("> combinations won nothing\n");
				else if(payoutValue == -1)
				    output.append("> combinations were invalid\n");
				else
					output.append("> combinations won <" ).append(payoutValue).append(">\n");
			}
			output.append("==========\nTotal combinations = <").append(total).append(">\n");
		}
		
		System.out.println(
			output.toString()
		);
	}
}