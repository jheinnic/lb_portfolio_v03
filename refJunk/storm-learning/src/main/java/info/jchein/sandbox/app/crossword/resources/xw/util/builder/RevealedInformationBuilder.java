package info.jchein.sandbox.app.crossword.resources.xw.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class RevealedInformationBuilder implements info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation> {
  // features and builders
  private info.jchein.sandbox.app.crossword.resources.xw.BonusValue m_bonusValue;
  private java.lang.String m_letterPool;
  private Boolean m_twentySpotPaid;
  // helper attributes
  private boolean m_featureBonusValueSet = false;
  private boolean m_featureLetterPoolSet = false;
  private boolean m_featureTwentySpotPaidSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newRevealedInformationBuilder()
   */
  private RevealedInformationBuilder() {
  }

  /**
   * This method creates a new instance of the RevealedInformationBuilder.
   * @return new instance of the RevealedInformationBuilder
   */
  public static RevealedInformationBuilder newRevealedInformationBuilder() {
    return new RevealedInformationBuilder();
  }

  /**
   * This method creates a new instance of the RevealedInformationBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param revealedInformation The existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the RevealedInformationBuilder
   */
  public static RevealedInformationBuilder newRevealedInformationBuilder(info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation p_revealedInformation) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation _revealedInformation = (info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation) c
        .copy(((info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation) p_revealedInformation));
    c.copyReferences();
    RevealedInformationBuilder _builder = newRevealedInformationBuilder();
    _builder.bonusValue(_revealedInformation.getBonusValue());
    _builder.letterPool(_revealedInformation.getLetterPool());
    _builder.twentySpotPaid(_revealedInformation.isTwentySpotPaid());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public RevealedInformationBuilder but() {
    RevealedInformationBuilder _builder = newRevealedInformationBuilder();
    _builder.m_featureBonusValueSet = m_featureBonusValueSet;
    _builder.m_bonusValue = m_bonusValue;
    _builder.m_featureLetterPoolSet = m_featureLetterPoolSet;
    _builder.m_letterPool = m_letterPool;
    _builder.m_featureTwentySpotPaidSet = m_featureTwentySpotPaidSet;
    _builder.m_twentySpotPaid = m_twentySpotPaid;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation type.
   * @return new instance of the info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation type
   */
  public info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation build() {
    final info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation _newInstance = (info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation) info.jchein.sandbox.app.crossword.resources.xw.XwFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.resources.xw.XwPackage.eINSTANCE.getRevealedInformation());
    if (m_featureBonusValueSet) {
      _newInstance.setBonusValue(m_bonusValue);
    }
    if (m_featureLetterPoolSet) {
      _newInstance.setLetterPool(m_letterPool);
    }
    if (m_featureTwentySpotPaidSet) {
      _newInstance.setTwentySpotPaid(m_twentySpotPaid);
    }
    return _newInstance;
  }

  public RevealedInformationBuilder bonusValue(info.jchein.sandbox.app.crossword.resources.xw.BonusValue p_bonusValue) {
    m_bonusValue = p_bonusValue;
    m_featureBonusValueSet = true;
    return this;
  }

  public RevealedInformationBuilder letterPool(java.lang.String p_letterPool) {
    m_letterPool = p_letterPool;
    m_featureLetterPoolSet = true;
    return this;
  }

  public RevealedInformationBuilder twentySpotPaid(Boolean p_twentySpotPaid) {
    m_twentySpotPaid = p_twentySpotPaid;
    m_featureTwentySpotPaidSet = true;
    return this;
  }
}
