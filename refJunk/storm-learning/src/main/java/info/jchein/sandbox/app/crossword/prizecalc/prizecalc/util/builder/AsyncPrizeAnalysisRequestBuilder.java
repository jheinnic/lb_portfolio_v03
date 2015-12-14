package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class AsyncPrizeAnalysisRequestBuilder implements
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest> {
  // features and builders
  private info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage m_boardCoverage;
  private info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage> m_featureBoardCoverageBuilder;
  private info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation m_revealedInfo;
  private info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation> m_featureRevealedInfoBuilder;
  private java.lang.String m_ticketHref;
  // helper attributes
  private boolean m_featureBoardCoverageSet = false;
  private boolean m_featureRevealedInfoSet = false;
  private boolean m_featureTicketHrefSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newAsyncPrizeAnalysisRequestBuilder()
   */
  private AsyncPrizeAnalysisRequestBuilder() {
  }

  /**
   * This method creates a new instance of the AsyncPrizeAnalysisRequestBuilder.
   * @return new instance of the AsyncPrizeAnalysisRequestBuilder
   */
  public static AsyncPrizeAnalysisRequestBuilder newAsyncPrizeAnalysisRequestBuilder() {
    return new AsyncPrizeAnalysisRequestBuilder();
  }

  /**
   * This method creates a new instance of the AsyncPrizeAnalysisRequestBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param asyncPrizeAnalysisRequest The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the AsyncPrizeAnalysisRequestBuilder
   */
  public static AsyncPrizeAnalysisRequestBuilder newAsyncPrizeAnalysisRequestBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest p_asyncPrizeAnalysisRequest) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest _asyncPrizeAnalysisRequest = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest) p_asyncPrizeAnalysisRequest));
    c.copyReferences();
    AsyncPrizeAnalysisRequestBuilder _builder = newAsyncPrizeAnalysisRequestBuilder();
    _builder.boardCoverage(_asyncPrizeAnalysisRequest.getBoardCoverage());
    _builder.revealedInfo(_asyncPrizeAnalysisRequest.getRevealedInfo());
    _builder.ticketHref(_asyncPrizeAnalysisRequest.getTicketHref());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public AsyncPrizeAnalysisRequestBuilder but() {
    AsyncPrizeAnalysisRequestBuilder _builder = newAsyncPrizeAnalysisRequestBuilder();
    _builder.m_featureBoardCoverageSet = m_featureBoardCoverageSet;
    _builder.m_boardCoverage = m_boardCoverage;
    _builder.m_featureBoardCoverageBuilder = m_featureBoardCoverageBuilder;
    _builder.m_featureRevealedInfoSet = m_featureRevealedInfoSet;
    _builder.m_revealedInfo = m_revealedInfo;
    _builder.m_featureRevealedInfoBuilder = m_featureRevealedInfoBuilder;
    _builder.m_featureTicketHrefSet = m_featureTicketHrefSet;
    _builder.m_ticketHref = m_ticketHref;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getAsyncPrizeAnalysisRequest());
    if (m_featureBoardCoverageSet) {
      _newInstance.setBoardCoverage(m_boardCoverage);
    } else {
      if (m_featureBoardCoverageBuilder != null) {
        _newInstance.setBoardCoverage(m_featureBoardCoverageBuilder.build());
      }
    }
    if (m_featureRevealedInfoSet) {
      _newInstance.setRevealedInfo(m_revealedInfo);
    } else {
      if (m_featureRevealedInfoBuilder != null) {
        _newInstance.setRevealedInfo(m_featureRevealedInfoBuilder.build());
      }
    }
    if (m_featureTicketHrefSet) {
      _newInstance.setTicketHref(m_ticketHref);
    }
    return _newInstance;
  }

  public AsyncPrizeAnalysisRequestBuilder boardCoverage(info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage p_boardCoverage) {
    m_boardCoverage = p_boardCoverage;
    m_featureBoardCoverageSet = true;
    return this;
  }

  public AsyncPrizeAnalysisRequestBuilder boardCoverage(
      info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage> p_boardCoverageBuilder) {
    m_featureBoardCoverageBuilder = p_boardCoverageBuilder;
    return this;
  }

  public AsyncPrizeAnalysisRequestBuilder revealedInfo(info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation p_revealedInfo) {
    m_revealedInfo = p_revealedInfo;
    m_featureRevealedInfoSet = true;
    return this;
  }

  public AsyncPrizeAnalysisRequestBuilder revealedInfo(
      info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation> p_revealedInformationBuilder) {
    m_featureRevealedInfoBuilder = p_revealedInformationBuilder;
    return this;
  }

  public AsyncPrizeAnalysisRequestBuilder ticketHref(java.lang.String p_ticketHref) {
    m_ticketHref = p_ticketHref;
    m_featureTicketHrefSet = true;
    return this;
  }
}
