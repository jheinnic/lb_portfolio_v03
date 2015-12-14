package info.jchein.sandbox.app.crossword.resources.xw.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class BonusWordCoverageBuilder implements info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage> {
  // features and builders
  private java.lang.String m_displayWord;
  private java.lang.String m_rawWord;
  // helper attributes
  private boolean m_featureDisplayWordSet = false;
  private boolean m_featureRawWordSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newBonusWordCoverageBuilder()
   */
  private BonusWordCoverageBuilder() {
  }

  /**
   * This method creates a new instance of the BonusWordCoverageBuilder.
   * @return new instance of the BonusWordCoverageBuilder
   */
  public static BonusWordCoverageBuilder newBonusWordCoverageBuilder() {
    return new BonusWordCoverageBuilder();
  }

  /**
   * This method creates a new instance of the BonusWordCoverageBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param bonusWordCoverage The existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the BonusWordCoverageBuilder
   */
  public static BonusWordCoverageBuilder newBonusWordCoverageBuilder(info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage p_bonusWordCoverage) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage _bonusWordCoverage = (info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage) c
        .copy(((info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage) p_bonusWordCoverage));
    c.copyReferences();
    BonusWordCoverageBuilder _builder = newBonusWordCoverageBuilder();
    _builder.displayWord(_bonusWordCoverage.getDisplayWord());
    _builder.rawWord(_bonusWordCoverage.getRawWord());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public BonusWordCoverageBuilder but() {
    BonusWordCoverageBuilder _builder = newBonusWordCoverageBuilder();
    _builder.m_featureDisplayWordSet = m_featureDisplayWordSet;
    _builder.m_displayWord = m_displayWord;
    _builder.m_featureRawWordSet = m_featureRawWordSet;
    _builder.m_rawWord = m_rawWord;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage type.
   * @return new instance of the info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage type
   */
  public info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage build() {
    final info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage _newInstance = (info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage) info.jchein.sandbox.app.crossword.resources.xw.XwFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.resources.xw.XwPackage.eINSTANCE.getBonusWordCoverage());
    if (m_featureDisplayWordSet) {
      _newInstance.setDisplayWord(m_displayWord);
    }
    if (m_featureRawWordSet) {
      _newInstance.setRawWord(m_rawWord);
    }
    return _newInstance;
  }

  public BonusWordCoverageBuilder displayWord(java.lang.String p_displayWord) {
    m_displayWord = p_displayWord;
    m_featureDisplayWordSet = true;
    return this;
  }

  public BonusWordCoverageBuilder rawWord(java.lang.String p_rawWord) {
    m_rawWord = p_rawWord;
    m_featureRawWordSet = true;
    return this;
  }
}
