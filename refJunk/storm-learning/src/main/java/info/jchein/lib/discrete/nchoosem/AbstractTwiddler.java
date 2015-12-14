package info.jchein.lib.discrete.nchoosem;

import java.util.Arrays;

abstract class AbstractTwiddler {
	protected final int n;
	protected final int m;
	private final int[] p;

	AbstractTwiddler(final int n, final int m) {
		if( m < 1 ) { 
			throw new IllegalArgumentException( "Input m has a lower bound of 1" );
		} else if( m >= n ) {
			throw new IllegalArgumentException( "Input m has an upper bound of n.  (n = " + n + ")" );
		}
		
		this.n = n;
		this.m = m;
		this.p = new int[n + 2];
		this.p[0] = n + 1;
		this.p[n + 1] = -2;
		
		// Setup default initial permutation, the one from which all others can be reached.
		// The last m elements are returned for the first permutation.
		int i = 1;
		final int b = n - m;
		while (i <= b) {
			p[i++] = 0;
		}
		for (int r = i - b; i <= n; i++, r++) {
			p[i] = r;
		}

		// TODO: Confirm this work-around is deprecated by asserting a minimum bound of m>=1 in
		//       constructor.  Otherwise, init(p) cannot be used to resume from this initial state
		//       without also including a special case exception block for what is essentially a silly
		//       case (n choose 0 always has exactly one permutation, empty set).
		// if (m == 0) {
		// 	p[1] = 1;
		// }
	}

	AbstractTwiddler(final int n, final int m, final int[] p) {
		if( m < 1 ) { 
			throw new IllegalArgumentException( "Input m has a lower bound of 1" );
		} else if( m >= n ) {
			throw new IllegalArgumentException( "Input m has an upper bound of n.  (n = " + n + ")" );
		} else if( (p.length != n+2) || (p[0] != n+1) || (p[n+1] != -2) ) {
			// Validate the size and fixed endpoints of p are correct
			throw new IllegalArgumentException( 
					"Input p must represent a valid Twiddler state for a " + n + 
					" choose " + m + " permutation problem.  Boundary violation." );
		}

		this.n = n;
		this.m = m;
		this.p = new int[n + 2];
		this.p[0] = n+1;
		this.p[n+1] = -2;

		// Validate the input argument, p, and use it to populate this.p along the way.
		// -- There must be exactly m elements of p with values from 1..n in the in the slots with 
		//    indices from 1..n
		// -- No value from 1..n may be used more than once.
		// -- All other values must be 0 or -1
		// -- TODO: Validate the semantics of when a -1 is legal and when it isn't		
		// -- TODO: Validate any semantics regarding when an element of with a positive non-zero value
		//          may or may not be greater or smaller than the previous such element.  Must order be
		//          monotonically increasing or is it more complex than that?
		int c=0;
		int[] v=new int[m+1];
		Arrays.fill(v,0);
		for( int i=1; i<=n; i++) {
			final int r = p[i];
			if( r == -1 ) {
				this.p[i] = -1;
			} else if( r == 0 ) {
				this.p[i] = 0;
			} else if( r <= n ) {
				if( v[r] == 1 ) {
					throw new IllegalArgumentException(
						"Input p must represent a valid Twiddler state for a " + n + 
						" choose " + m + " permutation problem.  Element " + r + " appears twice." );
				} else if( c == m ) {
					throw new IllegalArgumentException(
							"Input p must represent a valid Twiddler state for a " + n + 
							" choose " + m + " permutation problem.  More than " + m + 
							" elements initially selected." );
				}
				this.p[i] = r;
				v[r] = 1;
				c++;
			} else {
				throw new IllegalArgumentException(
						"Input p must represent a valid Twiddler state for a " + n + 
						" choose " + m + " permutation problem.  Element index " + r + 
						" is beyond maximum element index " + n + "." );
			}
		}
		if( c < m ) {
			throw new IllegalArgumentException(
					"Input p must represent a valid Twiddler state for a " + n + 
					" choose " + m + " permutation problem.  Fewer than " + m + 
					" elements initially selected." );
		}
	}

	protected int x;
	protected int y;
	protected int z;

	private void defineTwiddleIndices(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	protected final boolean twiddle() {
		int j = 1;

		while (p[j] <= 0) {
			j += 1;
		}

		final int b = j - 1;
		final boolean hadNext;
		if (p[b] == 0) {
			for (int i = 2; i < b; i++) {
				p[i] = -1;
			}

			p[j] = 0;
			p[1] = 1;
			// *x = *z = 0;
			// *y = j-1;
			defineTwiddleIndices(0, j - 1, 0);
			hadNext = true;
		} else {
			if (j > 1)
				p[b] = 0;

			do {
				j += 1;
			} while (p[j] > 0);

			int k = j - 1;
			int i = j;
			while (p[i] == 0) {
				p[i++] = -1;
			}

			if (p[i] == -1) {
				p[i] = p[k];
				// *z = p[k]-1;
				final int zVal = p[k] - 1;
				p[k] = -1;

				// *x = i-1;
				// *y = k-1;
				defineTwiddleIndices(i - 1, k - 1, zVal);
				hadNext = true;
			} else if (i == p[0]) {
				defineTwiddleIndices(0, j - 1, 0);
				hadNext = false;
			} else {
				p[j] = p[i];
				// *z = p[i]-1;
				final int zVal = p[i] - 1;
				p[i] = 0;

				// *x = j-1;
				// *y = i-1;
				defineTwiddleIndices(j - 1, i - 1, zVal);
				hadNext = true;
			}
		}

		return hadNext;
	}

	public abstract boolean next();
	
	public final int[] getMemento() {
		return Arrays.copyOf(this.p, n+2);
	}
}
