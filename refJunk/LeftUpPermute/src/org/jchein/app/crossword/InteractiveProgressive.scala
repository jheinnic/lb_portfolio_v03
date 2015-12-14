package org.jchein.app.crossword
import org.jchein.lib.crossword.WordSet
import org.jchein.lib.crossword.PrizeCalculator

object CheckProgress {
	def main( args : Array[String] ) : Unit = {
		// Get a dictionary for the ticket to analyze
		// val dictionary : WordSet = 
		// 	new WordSet(
		// 		Set("super", "spy", "tea", "perceive", "name", "shall", "yoga", "dim", "bright", "here", "skip", "sum", "car", "grey", "agenda", "date", "prism", "examine"), 
		// 		Set("cinema", "grip", "pharmacy", "jellyfish"), 
		// 		"daisy"
		// 	);
		// val bonusSpot = List[Int](30);
		val source = scala.io.Source.fromFile(args(0))
		val lines = source.getLines.filter(_.length > 0)

		// Get a dictionary for the ticket to analyze
		val dictionary : WordSet = 
			new WordSet(
				lines.next.split(','),
				lines.next.split(',').toSet,
				lines.next
			);
 		val bonusPrizeList:List[Int] = 
		  lines.next.split(',').toList.map( (numStr:String) => Integer.parseInt(numStr) )

		// Allocate and initialize the collection at an initial permutation 
		// where the final M bits are set.
		val calculator : PrizeCalculator = new PrizeCalculator(dictionary);
		var progress : Set[Char] = Set[Char]();
		var alphabet : List[Char] = "abcdefghijklmnopqrstuvwxyz".toCharArray.toList

		Console.println("Calculating naive possibilities... (This may take a while)\n");
		calculator.calculateDistribution(progress, bonusPrizeList, false );
	    calculator.printLastDistribution(bonusPrizeList);
		for( i <- 1 to 18 ) {
			Console.println("Reveal another letter, type it at the prompt below, then hit <ENTER>.");

			var nextChar : Char = Console.readChar();
			
			// while( (progress.contains(nextChar) == true) || (alphabet.contains(nextChar) == false) ) {
			while( alphabet.contains(nextChar) == false ) {
				Console.println("<" + nextChar + "> is not valid input.  Viable inputs are <" + alphabet.mkString(", ") + ">");
				nextChar = Console.readChar();
			}

			// Add the new letter to the progress set and calculate new permutation counts.
			progress = progress + nextChar;
 				alphabet = alphabet - nextChar;
			Console.println("\nFactoring in <" + nextChar + ">...");
			
			if( i >= 16 )
			  calculator.calculateDistribution(progress, bonusPrizeList, true);
			else 
			  calculator.calculateDistribution(progress, bonusPrizeList, false);
		
			calculator.printLastDistribution(bonusPrizeList);
		}
		
		Console.println("\nHow lucky were you today?");
	}
}