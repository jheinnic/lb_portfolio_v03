package org.jchein.lib.count.nchosem

object CountTwiddle {
	def main( args:Array[String]) : Unit = {}
/*
	{
	val M:Int = 18;
	val N:Int = 26;
	val output:StringBuffer = new StringBuffer();

	// Get the Twiddler state, p.
	val p:Array[Int] = Twiddler.inittwiddle(M, N);

	// Allocate and initialize the collection at an initial permutation 
	// where the final M bits are set.
	val b:Array[Int] = new Array[Int](N);
	for( i <- 0 until N-M ) { b(i) = 0; /*output.append('0');*/ }
	for( i <- N-M until N ) { b(i) = 1; /*output.append('1');*/ }
	// output.append('\n');

	var hasNext:Boolean = true
	var count:Int = 0;
	do
		{
		// Capture the previous permutation
		for( i <- 0 until N ) output.append( if( b(i)==1 ) '1' else '0' );
		output.append('\n');
		
		count += 1;
		if( (count%5000) == 0 ) { System.out.println(count); /*System.out.println(output.toString());*/ output.delete(0, output.length()-1); }

		val(x:Int, y:Int, z:Int, valHasNext:Boolean) = Twiddler.twiddle(p)
		hasNext = valHasNext;
		
		// Apply the next swap
		b(x) = 1;
		b(y) = 0;
		}
	while( hasNext == true )
	
	// Capture the final permutation
	// for( i <- 0 until N ) output.append( if( b(i)==1 ) '1' else '0' );
	// output.append('\n');
	output.append(count);
	output.append('\n');
	  
	System.out.println(output.toString());
	}
*/
}