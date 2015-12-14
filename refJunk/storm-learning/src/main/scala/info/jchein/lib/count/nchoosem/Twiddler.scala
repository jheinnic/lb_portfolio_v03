package org.jchein.lib.count.nchosem

abstract class Twiddler(m:Int, n:Int) {
	private var p : Array[Int] = new Array[Int](n+2)
	
	def init() : Unit = {
		var i:Int = 1
		
		p(0) = n+1;
		for(i <- 1 until n-m+1 ) p(i) = 0;
		for(i <- n-m+1 until n+1) p(i) = i+m-n;	
		p(n+1) = -2;
		
		if(m == 0) p(1) = 1;
	}
	
	def next() : Boolean;
	
	protected def twiddle() : (Int, Int, Int, Boolean) = {
		var j:Int = 1;
		
		while(p(j) <= 0) j += 1;
		if(p(j-1) == 0) {
			for(i <- 2 to j-1) p(i) = -1;

			p(j) = 0;
			p(1) = 1;
			//*x = *z = 0;
			//*y = j-1;
			return(0, j-1, 0, true);
		} else {
			if(j > 1) p(j-1) = 0;
			
			do {
				j += 1;
			} while(p(j) > 0);
			
			var k:Int = j-1;
			var i:Int = j;
			while(p(i) == 0) { p(i) = -1; i += 1; }

			if(p(i) == -1) {
				p(i) = p(k);
				// *z = p[k]-1;
				val zVal:Int = p(k)-1;
				p(k) = -1;

				// *x = i-1;
				// *y = k-1;
				return(i-1, k-1, zVal, true);
			} else if(i == p(0)) {
				return(0, j-1, 0, false);
			} else {
				p(j) = p(i);
				// *z = p[i]-1;
				val zVal = p(i)-1;
				p(i) = 0;

				// *x = j-1;
				// *y = i-1;
				return(j-1, i-1, zVal, true);
			}
		}
	}
}

/*
 * The selected array may be larger than the number to select from the pool.  The first M entries of
 * selected will be used to select elements from pool, any remaining elements will be ignored.
 * 
 * TODO: Until I can figure out how to put a guard condition on the constructor, the caller bears
 *       responsibility for ensuring that numToSelect <= selected.size and numToSelect <= pool.size!!
 * TODO: Until I can figure out if apply() is the right place for such things, the caller also is
 *       responsible for intializing the first M slots of selected with the last M items from pool.
 */
class CollectionTwiddler[T](selected:Array[T], pool:Array[T], numToSelect:Int) 
extends Twiddler(numToSelect, pool.size) {
    /**
     * Advances to the next permutation and returns true if there was another permutation left.  If
     * no permutations are left, the "selected" array is not modified and the return value is false.
     */
	def next() : Boolean = {
        val(x:Int, _, z:Int, hadNext:Boolean) = twiddle()
		
		// Apply the next swap -- Note that when the return value is false, z and x are set such that 
        // they put an item in from the pool that is already in selected at the given position--in other
        // words, its a no-op swap.  The judgment call is to perform one pointless assignment at loop
        // end rather than test hadNext on every iteration.
		if(hadNext) 
			selected(z) = pool(x);
        
        return hadNext;
    }
}

class BitsetTwiddler(bitSet:Array[Int], numBitsOn:Int, numBitsTotal:Int)
extends Twiddler(numBitsOn, numBitsTotal) {
    def next() : Boolean = {
    	val(x:Int, y:Int, _, hadNext:Boolean) = twiddle()

    	// See the note in CollectionTwiddler to understand why hadNext is not tested.
		if(hadNext) {
			bitSet(x) = 1;
			bitSet(y) = 0;
		}
    	
    	return hadNext;
    }
}