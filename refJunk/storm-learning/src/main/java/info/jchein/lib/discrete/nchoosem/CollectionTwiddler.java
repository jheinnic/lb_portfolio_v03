package info.jchein.lib.discrete.nchoosem;

/*
 * The selected array may be larger than the number to select from the pool.  The first M entries of
 * selected will be used to select elements from pool, any remaining elements will be ignored.
 */
public class CollectionTwiddler<T> extends AbstractTwiddler {
	private final T[] selected;
	private final T[] pool;

	public CollectionTwiddler(T[] selected, T[] pool, int numToSelect) {
		super(pool.length, numToSelect);
		this.selected = selected;
		this.pool = pool;
		
		// Initialize the first m slots of selected with the last m items from
		// pool.
		final int m = selected.length;
		for (int i = 0, r = pool.length - m; i < m; i++, r++) {
			selected[i] = pool[r];
		}
	}

	public CollectionTwiddler(T[] selected, T[] pool) {
		this(selected, pool, selected.length);
	}
	
	public CollectionTwiddler(T[] selected, T[] pool, int numToSelect, int[] pState) {
		super(pool.length, numToSelect, pState);
		this.selected = selected;
		this.pool = pool;
		
		for( int i=1, s=0; i<=n; i++ ) {
			final int r = pState[i]-1;
			if( r >= 0 ) {
				selected[s++] = pool[r];
			}
		}
	}

	public CollectionTwiddler(T[] selected, T[] pool, int[] pState) {
		this(selected, pool, selected.length, pState);
	}

	/**
	 * Advances "selected" to the next permutation and returns true if there is
	 * at least one more permutation left. If no permutations remain, the
	 * "selected" array is not modified and the return value is false.
	 */
	public boolean next() {
		// Apply the next swap -- Note that when the return value is false, z
		// and x are set such that
		// they put an item in from the pool that is already in selected at the
		// given position--in other
		// words, its a no-op swap. The judgment call is to perform one
		// pointless assignment at loop
		// end rather than test hadNext on every iteration.
		boolean hadNext = true;
		if (twiddle()) {
			selected[z] = pool[x];
		} else {
			hadNext = false;
		}

		return hadNext;
	}
}
