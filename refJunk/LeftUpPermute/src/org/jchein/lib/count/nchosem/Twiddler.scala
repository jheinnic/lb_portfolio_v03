package org.jchein.lib.count.nchosem

/**
 * An abstract class for producing the indices required to perform stepwise changes on an
 * initial m-permutation of elements from a set of n possible elements (m <= n) such that
 * the total series of iterations constitutes a Hamiltonian cycle through the set of all
 * possible m-permutations from any set of n possible elements.
 *
 * This file also includes two example concrete subclasses demonstrating how to interpret
 * the output of Twiddler's next() method that can be re-used as is or form a reference 
 * point for developing a custom subclass tailored to your own requirements.
 */
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
 * An example concrete subclass of Twiddler that interprets the coordinate pairs from
 * successive calls to make changes to an initial collection of M elements taken from a 
 * collection of N elements (M <= N) such that a unique M-pemutation of elements is available
 * on return from each call for next() until no more M-permutations remain and next() returns
 * false.
 *
 * Each permutation differs from the previous by a single pairwise swap that removes one element * from the previous permutation and replaces it with an element that wasn't present on the
 * previous iteration.
 *
 * The selected array may be larger than the number to select from the pool when filling it.
 * The first M slots of selected will get filled with a M-permutationo of elements from pool,
 * and any additional slots will remain untouched (hint: this means they can carry additional
 * elements without having them disturbed).
 * 
 * It is the caller's responsibility for ensuring that numToSelecy (a.k.a M) is no greater
 * than selected's actual size.  The behavior of this routine is otherwise undefined (and
 * will undoubtedly throw an Exception eventually, if not immediately).
 *
 * It is also the caller's responsibility to populate selected with the correct arrangement
 * of items from the pool collection.  The first M slots of selected are expected to have
 * been initialized with the last M items from pool.  The behavior if this routine is less
 * obviously undefined if this constraint is not met.
 */
class CollectionTwiddler[T](selected:Array[T], pool:Array[T], numToSelect:Int) 
extends Twiddler(numToSelect, pool.size) {
    /**
     * Advances to the next permutation and returns true if there was another permutation left.
     * In the event that the previous M-permutation arranged was in fact already the last one
     * not previously seen, the return value will be false instead of true and the "selected"
     * array will contain the exact same items as before, although two of them may have traded
     * places since then.
     */
	def next() : Boolean = {
        val(x:Int, _, z:Int, hadNext:Boolean) = twiddle()
		// Apply the next swap -- Note that when the return value is false, z and x are
		// set such that they put an item in from the pool that is already in selected
		// at the given position.  In other words, its a no-op swap.  The judgment call
		// was to perform one pointless but safe assignment at loop end rather than
		// spend a CPU cycle on every successful iteration to test if hadNext == false.
		if(hadNext) 
			selected(z) = pool(x);
        
        return hadNext;
    }
}

class BitsetTwiddler(bitSet:Array[Int], numBitsOn:Int, numBitsTotal:Int)
extends Twiddler(numBitsOn, numBitsTotal) {
    def next() : Boolean = {
    	val(x:Int, y:Int, _, hadNext:Boolean) = twiddle()
    		// See the note in CollectionTwiddler to understand why the bit swap that
		// follows is not conditional on hadNext == true even though hadNext == false
		// indicates that there no new permutation could be arranged because they had
		// all been previously visited already.
		if(hadNext) {
			bitSet(x) = 1;
			bitSet(y) = 0;
		}
    	
    	return hadNext;
    }
}

// Of course, it is possible to declare additional Twiddler subclasses that take different
// application-specific actions based on an pairwise swap between the "in" subset and the
// "out" subset of the previous permutation.  This can often be more efficient than 
// examing the entire contents of either collection's full contents on each iteration since
// the difference to either set always a single swapped element.
