package info.jchein.sandbox.app.crossword.resources.xw.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class PlayableTicketBuilder implements info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket> {
  // features and builders
  private info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation m_dynamicInput;
  private info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation> m_featureDynamicInputBuilder;
  private info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail m_fixedDetails;
  private info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail> m_featureFixedDetailsBuilder;
  private info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis m_latestAnalysis;
  private info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis> m_featureLatestAnalysisBuilder;
  // helper attributes
  private boolean m_featureDynamicInputSet = false;
  private boolean m_featureFixedDetailsSet = false;
  private boolean m_featureLatestAnalysisSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newPlayableTicketBuilder()
   */
  private PlayableTicketBuilder() {
  }

  /**
   * This method creates a new instance of the PlayableTicketBuilder.
   * @return new instance of the PlayableTicketBuilder
   */
  public static PlayableTicketBuilder newPlayableTicketBuilder() {
    return new PlayableTicketBuilder();
  }

  /**
   * This method creates a new instance of the PlayableTicketBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param playableTicket The existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the PlayableTicketBuilder
   */
  public static PlayableTicketBuilder newPlayableTicketBuilder(info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket p_playableTicket) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket _playableTicket = (info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket) c
        .copy(((info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket) p_playableTicket));
    c.copyReferences();
    PlayableTicketBuilder _builder = newPlayableTicketBuilder();
    _builder.dynamicInput(_playableTicket.getDynamicInput());
    _builder.fixedDetails(_playableTicket.getFixedDetails());
    _builder.latestAnalysis(_playableTicket.getLatestAnalysis());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public PlayableTicketBuilder but() {
    PlayableTicketBuilder _builder = newPlayableTicketBuilder();
    _builder.m_featureDynamicInputSet = m_featureDynamicInputSet;
    _builder.m_dynamicInput = m_dynamicInput;
    _builder.m_featureDynamicInputBuilder = m_featureDynamicInputBuilder;
    _builder.m_featureFixedDetailsSet = m_featureFixedDetailsSet;
    _builder.m_fixedDetails = m_fixedDetails;
    _builder.m_featureFixedDetailsBuilder = m_featureFixedDetailsBuilder;
    _builder.m_featureLatestAnalysisSet = m_featureLatestAnalysisSet;
    _builder.m_latestAnalysis = m_latestAnalysis;
    _builder.m_featureLatestAnalysisBuilder = m_featureLatestAnalysisBuilder;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket type.
   * @return new instance of the info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket type
   */
  public info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket build() {
    final info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket _newInstance = (info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket) info.jchein.sandbox.app.crossword.resources.xw.XwFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.resources.xw.XwPackage.eINSTANCE.getPlayableTicket());
    if (m_featureDynamicInputSet) {
      _newInstance.setDynamicInput(m_dynamicInput);
    } else {
      if (m_featureDynamicInputBuilder != null) {
        _newInstance.setDynamicInput(m_featureDynamicInputBuilder.build());
      }
    }
    if (m_featureFixedDetailsSet) {
      _newInstance.setFixedDetails(m_fixedDetails);
    } else {
      if (m_featureFixedDetailsBuilder != null) {
        _newInstance.setFixedDetails(m_featureFixedDetailsBuilder.build());
      }
    }
    if (m_featureLatestAnalysisSet) {
      _newInstance.setLatestAnalysis(m_latestAnalysis);
    } else {
      if (m_featureLatestAnalysisBuilder != null) {
        _newInstance.setLatestAnalysis(m_featureLatestAnalysisBuilder.build());
      }
    }
    return _newInstance;
  }

  public PlayableTicketBuilder dynamicInput(info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation p_dynamicInput) {
    m_dynamicInput = p_dynamicInput;
    m_featureDynamicInputSet = true;
    return this;
  }

  public PlayableTicketBuilder dynamicInput(
      info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation> p_revealedInformationBuilder) {
    m_featureDynamicInputBuilder = p_revealedInformationBuilder;
    return this;
  }

  public PlayableTicketBuilder fixedDetails(info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail p_fixedDetails) {
    m_fixedDetails = p_fixedDetails;
    m_featureFixedDetailsSet = true;
    return this;
  }

  public PlayableTicketBuilder fixedDetails(
      info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail> p_publishedTicketDetailBuilder) {
    m_featureFixedDetailsBuilder = p_publishedTicketDetailBuilder;
    return this;
  }

  public PlayableTicketBuilder latestAnalysis(info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis p_latestAnalysis) {
    m_latestAnalysis = p_latestAnalysis;
    m_featureLatestAnalysisSet = true;
    return this;
  }

  public PlayableTicketBuilder latestAnalysis(
      info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis> p_prizeAnalysisBuilder) {
    m_featureLatestAnalysisBuilder = p_prizeAnalysisBuilder;
    return this;
  }
}
