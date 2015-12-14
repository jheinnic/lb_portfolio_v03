package org.jchein.lib.crossword

class WordSet(singleWords : Collection[String], tripleWords : Set[String], bonusWord : String) {
	private val intermediateList : List[(String, Set[Char])] =
		(List(bonusWord) ++ singleWords ++ tripleWords).map(
			x => Tuple2(x, x.toCharArray().toSet)
		)
	private val emptyStringArray : List[String] = new Array[String](0).toList
 
	private val lettersPerWord : Map[String,Int] =
		intermediateList.toMap.mapValues( 
			letterSet => letterSet.size 
		).withDefaultValue(0)
		
	// Expand a list of Tuple[String,Set[Char]] to a List[List[Tuple[String,Char]], 
	private val wordsPerLetter : Map[Char,List[String]] =
	  intermediateList.map(
		  wordChars => Tuple2(wordChars._1, wordChars._2.toList)
	  ).flatMap(
		  wordChars => (0 until wordChars._2.size).map(
			  charIndex => Tuple2(wordChars._1, wordChars._2(charIndex))
		  )
	  ).groupBy(				  // Group each Tuple(word:String, charOf:Char) by its Char
		  wordChar => wordChar._2 
	  ).mapValues(
		  x => x.map( pair => pair._1 )   // Collapse Map[Char,List[Tuple[String,Char]]] to a Map[Char,List[String]]
	  ).withDefaultValue(emptyStringArray)

	def calculateHits(ticket : Array[Char], reportWords : Boolean) : (Int, Boolean, Boolean) = {
		// Build a list of words where each word is listed once for each of its dependent letters 
		// included in the input ticket of type Array[Char].  Use groupBy to build a map from each
		// word to a list of its appearances, then replace those lists with their size to yield a map
		// from each word to the number of its required letters that are present.  This will be 
		// cross-checked against the "lettersPerWord" map to identify completed words.
		val wordHits:Iterator[String] = 
			ticket.flatMap(
				i => wordsPerLetter(i) 
			).groupBy(
				x => x
			).filter( 
				wordToCoverage => wordToCoverage._2.size == lettersPerWord(wordToCoverage._1)
			).keySet.iterator
			
		var numWords:Int = 0
		var hasTriple:Boolean = false;
		var hasBonus:Boolean = false;
		
		// Count words!
		val output:StringBuffer = new StringBuffer(1024);
		for( foundWord <- wordHits ) {
		    if( reportWords ) { 
		        if( numWords == 0 ) {
		          output.append("Words hit include: <").append(foundWord).append(">");
		        } else {
		          output.append( ", <").append(foundWord).append(">");
		        }
		    }
			if( bonusWord.compareTo(foundWord) == 0 )
				hasBonus = true;
			else if( (hasTriple == false) && (tripleWords.contains(foundWord)) ) {
				hasTriple = true;
				numWords += 1;
			} else {
				numWords += 1;
			}
		}
		if( reportWords ) { output.append("\n"); System.out.println(output.toString()); }
		
		return(numWords, hasTriple, hasBonus);
	}
	
	def doTestPrinting() : Unit = {
		System.out.println(intermediateList);
		System.out.println(lettersPerWord);
		System.out.println(wordsPerLetter);
	}
}
