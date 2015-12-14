package info.jchein.sandbox.app.crossword.resources.xw.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ResolvedTicketBuilder implements info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket> {
  // features and builders
  private info.jchein.sandbox.app.crossword.resources.xw.BonusValue m_bonusValue;
  private info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail m_fixedDetails;
  private info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail> m_featureFixedDetailsBuilder;
  private java.lang.String m_letterPool;
  private Integer m_payoutTier;
  private Boolean m_twentySpotPaid;
  // helper attributes
  private boolean m_featureBonusValueSet = false;
  private boolean m_featureFixedDetailsSet = false;
  private boolean m_featureLetterPoolSet = false;
  private boolean m_featurePayoutTierSet = false;
  private boolean m_featureTwentySpotPaidSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newResolvedTicketBuilder()
   */
  private ResolvedTicketBuilder() {
  }

  /**
   * This method creates a new instance of the ResolvedTicketBuilder.
   * @return new instance of the ResolvedTicketBuilder
   */
  public static ResolvedTicketBuilder newResolvedTicketBuilder() {
    return new ResolvedTicketBuilder();
  }

  /**
   * This method creates a new instance of the ResolvedTicketBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param resolvedTicket The existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the ResolvedTicketBuilder
   */
  public static ResolvedTicketBuilder newResolvedTicketBuilder(info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket p_resolvedTicket) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket _resolvedTicket = (info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket) c
        .copy(((info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket) p_resolvedTicket));
    c.copyReferences();
    ResolvedTicketBuilder _builder = newResolvedTicketBuilder();
    _builder.bonusValue(_resolvedTicket.getBonusValue());
    _builder.fixedDetails(_resolvedTicket.getFixedDetails());
    _builder.letterPool(_resolvedTicket.getLetterPool());
    _builder.payoutTier(_resolvedTicket.getPayoutTier());
    _builder.twentySpotPaid(_resolvedTicket.isTwentySpotPaid());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public ResolvedTicketBuilder but() {
    ResolvedTicketBuilder _builder = newResolvedTicketBuilder();
    _builder.m_featureBonusValueSet = m_featureBonusValueSet;
    _builder.m_bonusValue = m_bonusValue;
    _builder.m_featureFixedDetailsSet = m_featureFixedDetailsSet;
    _builder.m_fixedDetails = m_fixedDetails;
    _builder.m_featureFixedDetailsBuilder = m_featureFixedDetailsBuilder;
    _builder.m_featureLetterPoolSet = m_featureLetterPoolSet;
    _builder.m_letterPool = m_letterPool;
    _builder.m_featurePayoutTierSet = m_featurePayoutTierSet;
    _builder.m_payoutTier = m_payoutTier;
    _builder.m_featureTwentySpotPaidSet = m_featureTwentySpotPaidSet;
    _builder.m_twentySpotPaid = m_twentySpotPaid;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket type.
   * @return new instance of the info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket type
   */
  public info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket build() {
    final info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket _newInstance = (info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket) info.jchein.sandbox.app.crossword.resources.xw.XwFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.resources.xw.XwPackage.eINSTANCE.getResolvedTicket());
    if (m_featureBonusValueSet) {
      _newInstance.setBonusValue(m_bonusValue);
    }
    if (m_featureFixedDetailsSet) {
      _newInstance.setFixedDetails(m_fixedDetails);
    } else {
      if (m_featureFixedDetailsBuilder != null) {
        _newInstance.setFixedDetails(m_featureFixedDetailsBuilder.build());
      }
    }
    if (m_featureLetterPoolSet) {
      _newInstance.setLetterPool(m_letterPool);
    }
    if (m_featurePayoutTierSet) {
      _newInstance.setPayoutTier(m_payoutTier);
    }
    if (m_featureTwentySpotPaidSet) {
      _newInstance.setTwentySpotPaid(m_twentySpotPaid);
    }
    return _newInstance;
  }

  public ResolvedTicketBuilder bonusValue(info.jchein.sandbox.app.crossword.resources.xw.BonusValue p_bonusValue) {
    m_bonusValue = p_bonusValue;
    m_featureBonusValueSet = true;
    return this;
  }

  public ResolvedTicketBuilder fixedDetails(info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail p_fixedDetails) {
    m_fixedDetails = p_fixedDetails;
    m_featureFixedDetailsSet = true;
    return this;
  }

  public ResolvedTicketBuilder fixedDetails(
      info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail> p_publishedTicketDetailBuilder) {
    m_featureFixedDetailsBuilder = p_publishedTicketDetailBuilder;
    return this;
  }

  public ResolvedTicketBuilder letterPool(java.lang.String p_letterPool) {
    m_letterPool = p_letterPool;
    m_featureLetterPoolSet = true;
    return this;
  }

  public ResolvedTicketBuilder payoutTier(Integer p_payoutTier) {
    m_payoutTier = p_payoutTier;
    m_featurePayoutTierSet = true;
    return this;
  }

  public ResolvedTicketBuilder twentySpotPaid(Boolean p_twentySpotPaid) {
    m_twentySpotPaid = p_twentySpotPaid;
    m_featureTwentySpotPaidSet = true;
    return this;
  }
}
