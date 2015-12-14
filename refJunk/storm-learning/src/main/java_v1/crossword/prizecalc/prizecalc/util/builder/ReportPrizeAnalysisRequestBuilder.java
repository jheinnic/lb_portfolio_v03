package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ReportPrizeAnalysisRequestBuilder implements
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest> {
  // features and builders
  private info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis m_prizeAnalysis;
  private info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis> m_featurePrizeAnalysisBuilder;
  // helper attributes
  private boolean m_featurePrizeAnalysisSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newReportPrizeAnalysisRequestBuilder()
   */
  private ReportPrizeAnalysisRequestBuilder() {
  }

  /**
   * This method creates a new instance of the ReportPrizeAnalysisRequestBuilder.
   * @return new instance of the ReportPrizeAnalysisRequestBuilder
   */
  public static ReportPrizeAnalysisRequestBuilder newReportPrizeAnalysisRequestBuilder() {
    return new ReportPrizeAnalysisRequestBuilder();
  }

  /**
   * This method creates a new instance of the ReportPrizeAnalysisRequestBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param reportPrizeAnalysisRequest The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the ReportPrizeAnalysisRequestBuilder
   */
  public static ReportPrizeAnalysisRequestBuilder newReportPrizeAnalysisRequestBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest p_reportPrizeAnalysisRequest) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest _reportPrizeAnalysisRequest = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest) p_reportPrizeAnalysisRequest));
    c.copyReferences();
    ReportPrizeAnalysisRequestBuilder _builder = newReportPrizeAnalysisRequestBuilder();
    _builder.prizeAnalysis(_reportPrizeAnalysisRequest.getPrizeAnalysis());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public ReportPrizeAnalysisRequestBuilder but() {
    ReportPrizeAnalysisRequestBuilder _builder = newReportPrizeAnalysisRequestBuilder();
    _builder.m_featurePrizeAnalysisSet = m_featurePrizeAnalysisSet;
    _builder.m_prizeAnalysis = m_prizeAnalysis;
    _builder.m_featurePrizeAnalysisBuilder = m_featurePrizeAnalysisBuilder;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getReportPrizeAnalysisRequest());
    if (m_featurePrizeAnalysisSet) {
      _newInstance.setPrizeAnalysis(m_prizeAnalysis);
    } else {
      if (m_featurePrizeAnalysisBuilder != null) {
        _newInstance.setPrizeAnalysis(m_featurePrizeAnalysisBuilder.build());
      }
    }
    return _newInstance;
  }

  public ReportPrizeAnalysisRequestBuilder prizeAnalysis(info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis p_prizeAnalysis) {
    m_prizeAnalysis = p_prizeAnalysis;
    m_featurePrizeAnalysisSet = true;
    return this;
  }

  public ReportPrizeAnalysisRequestBuilder prizeAnalysis(
      info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis> p_prizeAnalysisBuilder) {
    m_featurePrizeAnalysisBuilder = p_prizeAnalysisBuilder;
    return this;
  }
}
