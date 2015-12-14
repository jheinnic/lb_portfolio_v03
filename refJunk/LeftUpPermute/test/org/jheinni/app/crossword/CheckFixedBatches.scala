package org.jchein.app.crossword
import org.jchein.lib.crossword.WordSet
import org.jchein.lib.crossword.PrizeCalculator

object CheckFixedBatches {
	def main( args : Array[String] ) : Unit = {
		val source = scala.io.Source.fromFile(args(0))
		val lines = source.getLines.filter(_.length > 0)
		val bonusPrizeList:List[Int] = List(0, 1, 2, 3)

		// Get a dictionary for the ticket to analyze
		val dictionary : WordSet = 
			new WordSet(
				lines.next.split(','),
				lines.next.split(',').toSet,
				lines.next
			);
	
		// Allocate and initialize the collection at an initial permutation 
		// where the final M bits are set.
		var calculator : PrizeCalculator = new PrizeCalculator(dictionary);
		calculator.calculateDistribution( 
		    Set[Char]('u', 't', 'z', 'g', 'a', 'q', 'k', 'e', 'h', 'p', 's', 'n', 'j', 'y', 'f', 'o', 'c', 'd' ),
		    bonusPrizeList, true
		);
		calculator.printLastDistribution(bonusPrizeList);

		calculator.calculateDistribution( 
		    Set[Char]('u', 't', 'z', 'g', 'a', 'q', 'k', 'e', 'h', 'p', 's', 'n' ),
		    bonusPrizeList, false
		);
		calculator.printLastDistribution(bonusPrizeList);

		calculator.calculateDistribution( 
		    Set[Char]('k', 'e', 'h', 'p', 's', 'n', 'j', 'y', 'f', 'o', 'c', 'd' ),
		    bonusPrizeList, false
		);
		calculator.printLastDistribution(bonusPrizeList);

		calculator.calculateDistribution( 
		    Set[Char]('u', 't', 'z', 'g', 'a', 'q', 'j', 'y', 'f', 'o', 'c', 'd' ),
		    bonusPrizeList, false
		);
		calculator.printLastDistribution(bonusPrizeList);

		calculator.calculateDistribution( 
		    Set[Char]('u', 'y', 'h', 'g', 'c', 'n', 'k', 't', 'f', 'p', 'a', 'd' ),
		    bonusPrizeList, false
		);
		calculator.printLastDistribution(bonusPrizeList);

		calculator.calculateDistribution( 
		    Set[Char]('u', 'y', 'h', 'g', 'j', 'e', 'k', 't', 'f', 'p', 'z', 'o' ),
		    bonusPrizeList, false
		);
		calculator.printLastDistribution(bonusPrizeList);

		calculator.calculateDistribution(
		    Set[Char]('a', 'b', 'c', 'e', 'f', 'i', 'k', 'l', 'o', 'p', 't', 'v', 'x', 'y'),
		    bonusPrizeList, false
		);
		calculator.printLastDistribution(bonusPrizeList);
		
		calculator.calculateDistribution( 
		    Set[Char](), bonusPrizeList, false
		);
		calculator.printLastDistribution(bonusPrizeList);
	}
}