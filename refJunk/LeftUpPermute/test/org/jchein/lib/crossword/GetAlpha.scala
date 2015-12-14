package org.jchein.lib.crossword

object GetAlpha extends Application {
    System.out.println( "cat".toCharArray.toList )

    val Dictionary : WordSet = 
    	new WordSet( 
    	    Set("cat", "dog", "dart"), 
    	    Set("tag", "sand"), 
    	    "music"
    	)

    Dictionary.doTestPrinting;
}