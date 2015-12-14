package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class AsyncPrizeAnalysisResponseBuilder implements
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse> {
  // features and builders
  // helper attributes
  /**
   * Builder is not instantiated with a constructor.
   * @see #newAsyncPrizeAnalysisResponseBuilder()
   */
  private AsyncPrizeAnalysisResponseBuilder() {
  }

  /**
   * This method creates a new instance of the AsyncPrizeAnalysisResponseBuilder.
   * @return new instance of the AsyncPrizeAnalysisResponseBuilder
   */
  public static AsyncPrizeAnalysisResponseBuilder newAsyncPrizeAnalysisResponseBuilder() {
    return new AsyncPrizeAnalysisResponseBuilder();
  }

  /**
   * This method creates a new instance of the AsyncPrizeAnalysisResponseBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param asyncPrizeAnalysisResponse The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the AsyncPrizeAnalysisResponseBuilder
   */
  public static AsyncPrizeAnalysisResponseBuilder newAsyncPrizeAnalysisResponseBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse p_asyncPrizeAnalysisResponse) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse _asyncPrizeAnalysisResponse = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse) p_asyncPrizeAnalysisResponse));
    c.copyReferences();
    AsyncPrizeAnalysisResponseBuilder _builder = newAsyncPrizeAnalysisResponseBuilder();
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public AsyncPrizeAnalysisResponseBuilder but() {
    AsyncPrizeAnalysisResponseBuilder _builder = newAsyncPrizeAnalysisResponseBuilder();
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getAsyncPrizeAnalysisResponse());
    return _newInstance;
  }
}
