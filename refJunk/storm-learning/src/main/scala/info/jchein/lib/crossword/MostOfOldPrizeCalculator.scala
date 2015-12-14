package org.jchein.lib.crossword
import org.jchein.lib.count.nchosem.Twiddler
import org.jchein.lib.count.nchosem.CollectionTwiddler

class MOstOfOldPrizeCalculator( wordMix : WordSet ) {
  private val payoutValueList : Array[(Int, Int)] = 
    Array( (0 , 0), (3 , 1), (4 , 2), (5 , 3), (10 , 4), (12 , 5), (15 , 6), (20 , 7), (30 , 8), (50 , 9), 
         (60, 10), (100, 11), (150, 12), (300, 13), (1000, 14), (3000, 15), (20000, 16), (-1, 17) );

  // Map each possible payout value to the array index where its incidents are tracked
  // Element 0 is used for valid non-winning combinations.  Elements 1 through 16 are used for valid
  // payout amounts.  Element 17 is used for combinations that satisfy the criteria for a win, but
  // do not match a valid pay-out dollar value.
  // 
  // For permutations that hit the bonus word, the bonus value affects whether the permutation 
  // is possible and what value it pays out at if so.  The bonus value is an unknown, so we track
  // the distribution of winners for all possibilities and report them separately.
  private val BONUS_TO_PAYOUT_OFFSET:Map[Int, Int] = 
    Map( 0 -> 0, 4 -> 22, 5 -> 44, 10 -> 66, 30 -> 88 );
  private val TRIPLE_MATCH_PAYOUT_OFFSET = 110;
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

  private val ALPHABET:Array[Char] =   
    Array( 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

  private val LETTERS_PRESENT_PER_TICKET = 18
  private val LETTERS_ABSENT_PER_TICKET = 8
  private val FINAL_KNOWN_LETTER_INDEX = 17
  private val WORDS_PER_TICKET = 22
  private val NUM_BONUS_TIERS = 4
  private val NUM_PAYOUT_LEVELS = 18;   // Counting one for an "Invalid catch-all"  !!
  private val EMPTY_ARRAY:Array[Char] = Array( );

	// For permutations that hit the bonus word, the bonus value affects whether the permutation 
	// is possible and what value it pays out at if so.  The bonus value is an unknown, so we track
	// the distribution of winners for all possibilities and report them separately.
	// private val numBonusValues : Int = 4

	private val lettersPerTicket:Int = 18;
	private val alphabetSize:Int = 26;


    val tripleOffset:Int = -1;

	def printDistribution(knownLetters : Set[Char], possibleBonusValues : List[Int], detailedReport : Boolean ) : Unit = {
		val bonusPayoutDist : List[(Int,Array[Int])] = 
			possibleBonusValues.map( value => (value,new Array[Int](NUM_PAYOUT_LEVELS))
		);
		val basicPayoutDist : Array[Int] = Array( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 );

		if( knownLetters.size > lettersPerTicket ) {
			System.out.println( "Invalid request.  No more than 18 letters exist to be revealed." );
			return;
		}

		val numUnusedLetters = alphabetSize - knownLetters.size;
		val numLettersToPick = lettersPerTicket - knownLetters.size;

		val nextTicket:Array[Char] = new Array[Char](lettersPerTicket);
		val unusedLetters:Array[Char] = new Array[Char](numUnusedLetters);
		val knownLettersList:List[Char] = knownLetters.toList

		var j:Int = 0;
		for( i <- 0 until alphabetSize ) {
			if(knownLetters.contains(ALPHABET(i)) == false) {
				unusedLetters(j) = ALPHABET(i);
				j += 1;
			}
		}
		for( i <- numLettersToPick until lettersPerTicket ) { 
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
		      if (hasBonusMatch) {
		        offsetBeforeBonus = numWords + TRIPLE_MATCH_PAYOUT_OFFSET;
		      } else{  
		        offsetBeforeBonus = numWords;
		      }
		      
		    if (hasBonusMatch) {
		    	for(bonusDistPair <- bonusPayoutDist) {
		    	  val payout_tier:Int = 
		    	    PAYOUT_LEVEL_LOOKUP(offsetBeforeBonus + BONUS_TO_PAYOUT_OFFSET(bonusDistPair._1));
		    	  bonusDistPair._2(payout_tier) += 1; 
		    	}
		    } else {
                val payout_tier:Int = PAYOUT_LEVEL_LOOKUP(offsetBeforeBonus);
                basicPayoutDist(payout_tier) += 1;
		    }
		} while( permuteEngine.next() )

		val output:StringBuffer = new StringBuffer( 1024 );
		for( bonusDistPair <- bonusPayoutDist ) {
			output.append("For a bonus spot value of <").append(bonusDistPair._1).append(">, the distribution is:\n");
			var total:Int = 0;
			for( payoutTier <- payoutValueList ) {
				val numCombos:Int = bonusDistPair._2(payoutTier._2) + basicPayoutDist(payoutTier._2)
				total += numCombos;

				output.append("** <").append(numCombos);
				if(payoutTier._1 == 0)
					output.append("> combinations won nothing\n");
				else if(payoutTier._1 == -1)
				    output.append("> combinations were invalid\n");
				else
					output.append("> combinations won <" ).append(payoutTier._1).append(">\n");
			}
			output.append("==========\nTotal combinations = <").append(total).append(">\n");
		}
		
		System.out.println(
			output.toString()
		);
	}

	def inferValidity(numWords:Int, hasTriple:Boolean, hasBonus:Boolean) : (String, Boolean, Boolean, Boolean) = {
		val suffix : String = " <" + numWords + ", " + hasTriple + ", " + hasBonus + '>';
		(numWords, hasTriple, hasBonus) match {
	 		case (n:Int, ht:Boolean, hb:Boolean) 
	 			if hb == false && n<2 => 
	 				return("Loser" + suffix, false, true, false); 

	 		// A win on two words cannot include a tripled word and must also miss the bonus word
	 		case (n:Int, ht:Boolean, hb:Boolean) 
	 			if n==2 && ht == false && hb == false => 
	 				return("Winner" + suffix, true, false, false); 

	 		// A win on three words can include a tripled word without the bonus word or a tripled word
	 		// with the bonus word, but cannot hit the bonus word without a tripled word.
	 		case (n:Int, ht:Boolean, hb:Boolean) 
	 			if n == 3 && (hb == false || ht == true) => 
	 				return("Winner" + suffix, true, false, false);
	 				
	 		// Wins on 4 or 5 words have no restrictions on whether they can occur with either
	 		// a tripled word, a bonus word hit, both modifiers, or neither modifier.
	 		case (n:Int, ht:Boolean, hb:Boolean) 
	 			if (n==4 || n==5) => 
	 				return("Winner" + suffix, true, false, false);

	 		// Wins on 6 or 7 words can occur with no modifiers, the bonus word without a tripled
	 		// word, or a tripled word without the bonus word.  The bonus word and a tripled word
	 		// cannot both be hit.
	 		case (n:Int, ht:Boolean, hb:Boolean) 
	 			if ((n==6 || n==7) && (ht == false || hb == false)) => 
	 				return("Winner" + suffix, true, false, false); 

	 		// TODO: Is there a $50 Bonus Word prize?  If so, then 8 Words + Bonus word is possible!
	 		// Wins on 8 or 9 words cannot occur with the bonus word, but they may be tripled.
	 		case (n:Int, ht:Boolean, hb:Boolean) 
	 			if ((n==8 || n==9) && (hb == false)) => 
	 				return("Winner" + suffix, true, false, false); 

	 		// Wins on 10 words cannot occur with the bonus word and cannot be tripled.
	 		case (n:Int, ht:Boolean, hb:Boolean) 
	 			if (n==10) && (hb==false) && (ht==false) => 
	 				return("Winner" + suffix, true, false, false); 

	 		// Any other permutation calls for a prize that is not available.
	 		case (n:Int, ht:Boolean, hb:Boolean) => 
	 			return("Invalid" + suffix, false, false, true); 
		}
	}
}