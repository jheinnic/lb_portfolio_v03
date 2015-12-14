package info.jchein.lib.discrete.nchoosem;

import java.util.BitSet;

public final class BitsetTwiddler extends AbstractTwiddler {
    private BitSet bits;

	public BitsetTwiddler( final BitSet bits, final int m ) {
    	super(bits.size(), m);
    	this.bits = bits;
		
		// Initialize the first m slots of selected with the last m items from
		// pool.
    	bits.clear();
		for (int r = n - m; r < n; r++) {
			bits.set(r);
		}
	}
	
	public BitsetTwiddler(final BitSet bits, final int m, final int[] pState) {
		super(bits.size(), m, pState);
		this.bits = bits;
		
		bits.clear();
		for( int i=1; i<=n; i++ ) {
			final int r = pState[i]-1;
			if( r >= 0 ) {
				bits.set(r);
			}
		}
	}
    
	@Override
	public boolean next() {
		boolean hadNext = true;
		if( twiddle() ) {
			bits.set(x);
			bits.clear(y);
		} else {
			hadNext = false;
		}
		
		return hadNext;
	}

}
