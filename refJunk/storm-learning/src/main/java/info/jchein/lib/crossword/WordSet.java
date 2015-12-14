package info.jchein.lib.crossword;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.CharSet;

public final class WordSet {
    public static class WordContent {
    	private CharSet chars;
		private String word;

		WordContent(final String word) {
    		this.word = word;
    		this.chars = CharSet.getInstance(word);
    	}
	}

	public WordSet(Collection<String> singleWords, Set<String> tripleWords, String bonusWord) {
    }
    
	private ArrayList<WordContent> intermediateList = new ArrayList<WordContent>(14);
	private List<String> emptyStringArray = Collections.emptyList();
	private ArrayList<ArrayList<WordContent>> wordPerLetter = 
		new ArrayList<ArrayList<WordContent>>(26);
	/*private val lettersPerWord : Map[String,Int] =
		intermediateList.toMap.mapValues( 
			letterSet => letterSet.size 
		).withDefaultValue(0)(
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
	  ).withDefaultValue(emptyStringArray)*/
	

	public PrizeDescription calculateHits(Character[] ticket) {
		// Build a list of words where each word is listed once for each of its dependent letters 
		// included in the input ticket of type Array[Char].  Use groupBy to build a map from each
		// word to a list of its appearances, then replace those lists with their size to yield a map
		// from each word to the number of its required letters that are present.  This will be 
		// cross-checked against the "lettersPerWord" map to identify completed words.
		
		/*val wordHits:Iterator[String] = 
			ticket.flatMap(
				i => wordsPerLetter(i) 
			).groupBy(
				x => x
			).filter( 
				wordToCoverage => wordToCoverage._2.size == lettersPerWord(wordToCoverage._1)
			).keySet.iterator*/
			
		int numWords = 0;
		boolean hasTriple = false;
		boolean hasBonus = false;
		
		// Count words!
		/*val output:StringBuffer = new StringBuffer(1024);
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
		if( reportWords ) { output.append("\n"); System.out.println(output.toString()); }*/
		
		return new PrizeDescription(numWords, hasTriple, hasBonus);
	}
	
	/*def doTestPrinting() : Unit = {
		System.out.println(intermediateList);
		System.out.println(lettersPerWord);
		System.out.println(wordsPerLetter);
	}*/
}
