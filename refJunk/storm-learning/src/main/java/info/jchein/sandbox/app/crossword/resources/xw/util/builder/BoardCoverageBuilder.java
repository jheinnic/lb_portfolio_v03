package info.jchein.sandbox.app.crossword.resources.xw.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class BoardCoverageBuilder implements info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage> {
  // features and builders
  private info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage m_bonusWord;
  private info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage> m_featureBonusWordBuilder;
  private java.util.Collection<info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage> m_allWords = new java.util.LinkedList<info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage>();
  private java.util.Collection<info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage>> m_featureAllWordsBuilder = new java.util.LinkedList<info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage>>();
  // helper attributes
  private boolean m_featureAllWordsSet = false;
  private boolean m_featureBonusWordSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newBoardCoverageBuilder()
   */
  private BoardCoverageBuilder() {
  }

  /**
   * This method creates a new instance of the BoardCoverageBuilder.
   * @return new instance of the BoardCoverageBuilder
   */
  public static BoardCoverageBuilder newBoardCoverageBuilder() {
    return new BoardCoverageBuilder();
  }

  /**
   * This method creates a new instance of the BoardCoverageBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param boardCoverage The existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the BoardCoverageBuilder
   */
  public static BoardCoverageBuilder newBoardCoverageBuilder(info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage p_boardCoverage) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage _boardCoverage = (info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage) c
        .copy(((info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage) p_boardCoverage));
    c.copyReferences();
    BoardCoverageBuilder _builder = newBoardCoverageBuilder();
    _builder.bonusWord(_boardCoverage.getBonusWord());
    if (_boardCoverage.getAllWords() != null) {
      _builder.allWords(_boardCoverage.getAllWords());
    }
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public BoardCoverageBuilder but() {
    BoardCoverageBuilder _builder = newBoardCoverageBuilder();
    _builder.m_featureAllWordsSet = m_featureAllWordsSet;
    _builder.m_allWords = m_allWords;
    _builder.m_featureAllWordsBuilder = m_featureAllWordsBuilder;
    _builder.m_featureBonusWordSet = m_featureBonusWordSet;
    _builder.m_bonusWord = m_bonusWord;
    _builder.m_featureBonusWordBuilder = m_featureBonusWordBuilder;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage type.
   * @return new instance of the info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage type
   */
  public info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage build() {
    final info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage _newInstance = (info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage) info.jchein.sandbox.app.crossword.resources.xw.XwFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.resources.xw.XwPackage.eINSTANCE.getBoardCoverage());
    if (m_featureBonusWordSet) {
      _newInstance.setBonusWord(m_bonusWord);
    } else {
      if (m_featureBonusWordBuilder != null) {
        _newInstance.setBonusWord(m_featureBonusWordBuilder.build());
      }
    }
    if (m_featureAllWordsSet) {
      _newInstance.getAllWords().addAll(m_allWords);
    } else {
      if (!m_featureAllWordsBuilder.isEmpty()) {
        for (info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage> builder : m_featureAllWordsBuilder) {
          _newInstance.getAllWords().add(builder.build());
        }
      }
    }
    return _newInstance;
  }

  public BoardCoverageBuilder bonusWord(info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage p_bonusWord) {
    m_bonusWord = p_bonusWord;
    m_featureBonusWordSet = true;
    return this;
  }

  public BoardCoverageBuilder bonusWord(
      info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage> p_bonusWordCoverageBuilder) {
    m_featureBonusWordBuilder = p_bonusWordCoverageBuilder;
    return this;
  }

  public BoardCoverageBuilder allWords(info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage p_allWords) {
    m_allWords.add(p_allWords);
    m_featureAllWordsSet = true;
    return this;
  }

  public BoardCoverageBuilder allWords(java.util.Collection<? extends info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage> p_allWords) {
    m_allWords.addAll(p_allWords);
    m_featureAllWordsSet = true;
    return this;
  }

  public BoardCoverageBuilder allWords(
      info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage> p_gridWordCoverageBuilder) {
    m_featureAllWordsBuilder.add(p_gridWordCoverageBuilder);
    return this;
  }
}
