package org.jchein.app.crossword

import org.jchein.lib.crossword.PrizeCalculator;
import org.jchein.lib.crossword.WordSet;

object BatchCharSets {
	def main( args : Array[String] ) : Unit = {
		val source = scala.io.Source.fromFile(args(0))
		val lines = source.getLines.filter(_.length > 0)

		// Get a dictionary for the ticket to analyze
		val dictionary : WordSet = 
			new WordSet(
				lines.next.split(','),          // Words without tripling modifier (18)
				lines.next.split(',').toSet,    // Words with tripling modifier (4) 
				lines.next                      // Bonus word (1)
			);
		val bonusPrizeList:List[Int] = 
			lines.next.split(',').toList.map((numStr:String) => Integer.parseInt(numStr))

		// Each remaining line in the file describes an input set of letters.  Calculate the prize 
		// potentials for each line.  When there are 16, 17, or 18 characters in the input set, set
		// the "printWords" flag true to see the word mixes as well.  At smaller letter counts, the number of 
		// combinations to consider is just too great to take the time printing word lists.
		val calculator : PrizeCalculator = new PrizeCalculator(dictionary);

        while( lines.hasNext ) {
            val tokenSet:Set[Char] = 
              lines.next.toCharArray().toSet.map((c:Char) => c)
            calculator.calculateDistribution(
              tokenSet, bonusPrizeList, (tokenSet.size >= 16)  
            );
            calculator.printLastDistribution(bonusPrizeList);
        }
	}
}