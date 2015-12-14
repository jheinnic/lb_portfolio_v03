package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ReportPrizeAnalysisResponseBuilder implements
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse> {
  // features and builders
  // helper attributes
  /**
   * Builder is not instantiated with a constructor.
   * @see #newReportPrizeAnalysisResponseBuilder()
   */
  private ReportPrizeAnalysisResponseBuilder() {
  }

  /**
   * This method creates a new instance of the ReportPrizeAnalysisResponseBuilder.
   * @return new instance of the ReportPrizeAnalysisResponseBuilder
   */
  public static ReportPrizeAnalysisResponseBuilder newReportPrizeAnalysisResponseBuilder() {
    return new ReportPrizeAnalysisResponseBuilder();
  }

  /**
   * This method creates a new instance of the ReportPrizeAnalysisResponseBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param reportPrizeAnalysisResponse The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the ReportPrizeAnalysisResponseBuilder
   */
  public static ReportPrizeAnalysisResponseBuilder newReportPrizeAnalysisResponseBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse p_reportPrizeAnalysisResponse) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse _reportPrizeAnalysisResponse = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse) p_reportPrizeAnalysisResponse));
    c.copyReferences();
    ReportPrizeAnalysisResponseBuilder _builder = newReportPrizeAnalysisResponseBuilder();
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public ReportPrizeAnalysisResponseBuilder but() {
    ReportPrizeAnalysisResponseBuilder _builder = newReportPrizeAnalysisResponseBuilder();
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getReportPrizeAnalysisResponse());
    return _newInstance;
  }
}
