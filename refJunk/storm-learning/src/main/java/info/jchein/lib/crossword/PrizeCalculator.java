package info.jchein.lib.crossword;

import java.util.Arrays;

import info.jchein.lib.discrete.nchoosem.CollectionTwiddler;

import org.apache.commons.lang.CharSet;

public class PrizeCalculator {
	public PrizeCalculator() {
	}

	  // Map each possible payout value to the array index where its incidents are tracked
	  // Element 0 is used for valid non-winning combinations.  Elements 1 through 16 are used for valid
	  // payout amounts.  Element 17 is used for combinations that satisfy the criteria for a win, but
	  // do not match a valid pay-out dollar value.
	  // 
	  // For permutations that hit the bonus word, the bonus value affects whether the permutation 
	  // is possible and what value it pays out at if so.  The bonus value is an unknown, so we track
	  // the distribution of winners for all possibilities and report them separately.
	private static final int TRIPLE_MATCH_PAYOUT_OFFSET = 110;
	private static final int[] BONUS_TO_PAYOUT_OFFSET = { 22, 44, 66, 88 };
	private static final int[] PAYOUT_LEVEL_LOOKUP = {
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
	};
	private static final int NUM_PAYOUT_LEVELS = 18;   // Counting one for an "Invalid catch-all"  !!
	private static final int[] BONUS_LEVEL_VALUES = {4, 5, 10, 30};
	
	private static final int NUM_BONUS_LEVELS = 4;
	private static final int[] PAYOUT_LEVEL_VALUES = 
        { 0, 3, 4, 5, 10, 12, 15, 20, 30, 50, 60, 100, 150, 300, 1000, 3000, 20000, -1 };

    private static final char[] ALPHABET =   
    	{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 
	    'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	// private static final int WORDS_PER_TICKET = 22;
	private static final int LETTERS_ABSENT_PER_TICKET = 8;
    private static final int  LETTERS_PRESENT_PER_TICKET = 18;

    private final int[][] bonusPayoutDist = {
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
	private final int[] basicPayoutDist = 
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private final int[] overallPayoutDist = 
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

	// For permutations that hit the bonus word, the bonus value affects whether the permutation 
	// is possible and what value it pays out at if so.  The bonus value is an unknown, so we track
	// the distribution of winners for all possibilities and report them separately.
	// private val numBonusValues : Int = 4

    void clearLastCalculation() {
        for( int ii=0; ii<NUM_PAYOUT_LEVELS; ii++ ) { 
            basicPayoutDist[ii] = 0;
            overallPayoutDist[ii] = 0;
        }
        for( int ii=0; ii<NUM_BONUS_LEVELS; ii++ ) { 
        	for( int jj=0; jj<NUM_PAYOUT_LEVELS; jj++ ) { 
        		bonusPayoutDist[ii][jj] = 0;
        	}
        }
    }
    
	public int[] calculateDistribution(
		final WordSet wordMix, 
		char[] knownLetters, 
		int[] possibleBonusValues, 
		int[] pState, 
		int maxPermutes
	) {
	    clearLastCalculation();

		if( knownLetters.length > LETTERS_PRESENT_PER_TICKET ) {
			throw new IllegalArgumentException( "Invalid request.  No more than 18 letters exist to be revealed." );
		}

		int numUnusedLetters = ALPHABET.length - knownLetters.length;
		int numLettersToPick = LETTERS_PRESENT_PER_TICKET - knownLetters.length;

		StringBuffer unusedLettersStr = new StringBuffer( 3 + knownLetters.length*2 );
		unusedLettersStr.append("a-z");
		for( int ii=0; ii<knownLetters.length; ii++ ) {
			unusedLettersStr.append('^').append(knownLetters[ii]);
		}
		final CharSet unusedLettersSet = CharSet.getInstance(unusedLettersStr.toString());
		final Character[] unusedLetters = new Character[numUnusedLetters];
		for( int ii=0,jj=0; ii<ALPHABET.length; ii++ ) {
			if( ! unusedLettersSet.contains(ALPHABET[ii])) {
				unusedLetters[jj++] = ALPHABET[ii];
			}
		}

		final Character[] nextTicket = new Character[LETTERS_PRESENT_PER_TICKET];
		for( int ii=numLettersToPick; ii<LETTERS_PRESENT_PER_TICKET; ii++ ) { 
			nextTicket[ii] = knownLetters[LETTERS_PRESENT_PER_TICKET-ii-1];
		}
		for( int ii = 0; ii<numLettersToPick; ii++ ) {
			nextTicket[ii] = unusedLetters[LETTERS_ABSENT_PER_TICKET+ii];
		}

		// TODO: Set pState from the input message
		final CollectionTwiddler<Character> permuteEngine = 
		    new CollectionTwiddler<Character>( nextTicket, unusedLetters, numLettersToPick );
		int numPermutes = 0;
		
		do {
		    PrizeDescription prizeDescription = 
		        wordMix.calculateHits(nextTicket);
		      
		    int offsetBeforeBonus = 0;
		      if (prizeDescription.hasTriple()) {
		        offsetBeforeBonus = prizeDescription.getNumWords() + TRIPLE_MATCH_PAYOUT_OFFSET;
		      } else{  
		        offsetBeforeBonus = prizeDescription.getNumWords();
		      }
		      
		    if (prizeDescription.hasBonus()) {
		    	for(int bonusLevel : possibleBonusValues) {
		    	  int payout_tier = 
		    	      PAYOUT_LEVEL_LOOKUP[offsetBeforeBonus + BONUS_TO_PAYOUT_OFFSET[bonusLevel]];
		    	  bonusPayoutDist[bonusLevel][payout_tier] += 1; 
		    	  overallPayoutDist[payout_tier] += 1;
		    	  
		    	  // if(payout_tier == 17 || payout_tier == 8) {
		    	  //     val ticketString = nextTicket.map( c => c.toString ).reduce[String]( (c:String,str:String) => str + ", " + c );
		    	  //     System.out.println( "Hit payout tier of <" + payout_tier + "> on input of <" + ticketString +"> for bonus level <" + bonusLevel + "> on <" + numWords + ">, <" + isTripled + ">" );
		    	  // }
		    	}
		    } else {
                int payout_tier = PAYOUT_LEVEL_LOOKUP[offsetBeforeBonus];
                basicPayoutDist[payout_tier] += 1;
                overallPayoutDist[payout_tier] += 1;
		    	 // if(payout_tier == 17 || payout_tier == 8) {
		    	 //     val ticketString = nextTicket.map( c => c.toString ).reduce[String]( (c:String,str:String) => str + ", " + c );
		    	 //     System.out.println( "Hit payout tier of <" + payout_tier + "> on input of <" + ticketString +"> without bonus on <" + numWords + ">, <" + isTripled + ">" );
		    	 // }
		    }
		} while( (maxPermutes > 0 || numPermutes++ < maxPermutes) && permuteEngine.next() );
		
		return Arrays.copyOf(overallPayoutDist, NUM_PAYOUT_LEVELS);
	}
	
	void printLastDistribution(final int[] possibleBonusValues) {
		StringBuffer output = new StringBuffer(1024);

		for(final int bonusLevel : possibleBonusValues) {
			output.append("For a bonus spot value of <"
			).append(BONUS_LEVEL_VALUES[bonusLevel]
			).append(">, the distribution is:\n");

			int total = 0;
			for( int payoutTier = 0; payoutTier <NUM_PAYOUT_LEVELS; payoutTier++ ) {
				int numCombos = bonusPayoutDist[bonusLevel][payoutTier] + basicPayoutDist[payoutTier];
				int payoutValue = PAYOUT_LEVEL_VALUES[payoutTier];
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