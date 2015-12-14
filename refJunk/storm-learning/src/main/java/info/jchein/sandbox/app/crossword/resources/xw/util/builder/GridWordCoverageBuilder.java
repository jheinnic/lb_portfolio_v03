package info.jchein.sandbox.app.crossword.resources.xw.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class GridWordCoverageBuilder implements info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage> {
  // features and builders
  private java.lang.String m_displayWord;
  private java.lang.String m_rawWord;
  // helper attributes
  private boolean m_featureDisplayWordSet = false;
  private boolean m_featureRawWordSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newGridWordCoverageBuilder()
   */
  private GridWordCoverageBuilder() {
  }

  /**
   * This method creates a new instance of the GridWordCoverageBuilder.
   * @return new instance of the GridWordCoverageBuilder
   */
  public static GridWordCoverageBuilder newGridWordCoverageBuilder() {
    return new GridWordCoverageBuilder();
  }

  /**
   * This method creates a new instance of the GridWordCoverageBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param gridWordCoverage The existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the GridWordCoverageBuilder
   */
  public static GridWordCoverageBuilder newGridWordCoverageBuilder(info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage p_gridWordCoverage) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage _gridWordCoverage = (info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage) c
        .copy(((info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage) p_gridWordCoverage));
    c.copyReferences();
    GridWordCoverageBuilder _builder = newGridWordCoverageBuilder();
    _builder.displayWord(_gridWordCoverage.getDisplayWord());
    _builder.rawWord(_gridWordCoverage.getRawWord());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public GridWordCoverageBuilder but() {
    GridWordCoverageBuilder _builder = newGridWordCoverageBuilder();
    _builder.m_featureDisplayWordSet = m_featureDisplayWordSet;
    _builder.m_displayWord = m_displayWord;
    _builder.m_featureRawWordSet = m_featureRawWordSet;
    _builder.m_rawWord = m_rawWord;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage type.
   * @return new instance of the info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage type
   */
  public info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage build() {
    final info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage _newInstance = (info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage) info.jchein.sandbox.app.crossword.resources.xw.XwFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.resources.xw.XwPackage.eINSTANCE.getGridWordCoverage());
    if (m_featureDisplayWordSet) {
      _newInstance.setDisplayWord(m_displayWord);
    }
    if (m_featureRawWordSet) {
      _newInstance.setRawWord(m_rawWord);
    }
    return _newInstance;
  }

  public GridWordCoverageBuilder displayWord(java.lang.String p_displayWord) {
    m_displayWord = p_displayWord;
    m_featureDisplayWordSet = true;
    return this;
  }

  public GridWordCoverageBuilder rawWord(java.lang.String p_rawWord) {
    m_rawWord = p_rawWord;
    m_featureRawWordSet = true;
    return this;
  }
}
