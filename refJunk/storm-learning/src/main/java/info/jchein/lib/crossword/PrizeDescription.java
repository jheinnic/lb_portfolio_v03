package info.jchein.lib.crossword;

// Candidate model class!
public final class PrizeDescription {

	private final int numWords;
	private final boolean hasTriple;
	private final boolean hasBonus;

	PrizeDescription(final int numWords, final boolean hasTriple, final boolean hasBonus) {
		this.numWords = numWords;
		this.hasTriple = hasTriple;
		this.hasBonus = hasBonus;
	}

	public int getNumWords() {
		return numWords;
	}

	public boolean hasTriple() {
		return hasTriple;
	}

	public boolean hasBonus() {
		return hasBonus;
	}
}
