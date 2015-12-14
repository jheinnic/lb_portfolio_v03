package info.jchein.sandbox.app.crossword.resources.xw.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class PrizeAnalysisBuilder implements info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis> {
  // features and builders
  private info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation m_dynamicInput;
  private info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation> m_featureDynamicInputBuilder;
  private java.util.Collection<Integer> m_prizeDistribution = new java.util.LinkedList<Integer>();
  // helper attributes
  private boolean m_featureDynamicInputSet = false;
  private boolean m_featurePrizeDistributionSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newPrizeAnalysisBuilder()
   */
  private PrizeAnalysisBuilder() {
  }

  /**
   * This method creates a new instance of the PrizeAnalysisBuilder.
   * @return new instance of the PrizeAnalysisBuilder
   */
  public static PrizeAnalysisBuilder newPrizeAnalysisBuilder() {
    return new PrizeAnalysisBuilder();
  }

  /**
   * This method creates a new instance of the PrizeAnalysisBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param prizeAnalysis The existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the PrizeAnalysisBuilder
   */
  public static PrizeAnalysisBuilder newPrizeAnalysisBuilder(info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis p_prizeAnalysis) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis _prizeAnalysis = (info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis) c
        .copy(((info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis) p_prizeAnalysis));
    c.copyReferences();
    PrizeAnalysisBuilder _builder = newPrizeAnalysisBuilder();
    _builder.dynamicInput(_prizeAnalysis.getDynamicInput());
    if (_prizeAnalysis.getPrizeDistribution() != null) {
      _builder.prizeDistribution(_prizeAnalysis.getPrizeDistribution());
    }
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public PrizeAnalysisBuilder but() {
    PrizeAnalysisBuilder _builder = newPrizeAnalysisBuilder();
    _builder.m_featureDynamicInputSet = m_featureDynamicInputSet;
    _builder.m_dynamicInput = m_dynamicInput;
    _builder.m_featureDynamicInputBuilder = m_featureDynamicInputBuilder;
    _builder.m_featurePrizeDistributionSet = m_featurePrizeDistributionSet;
    _builder.m_prizeDistribution = m_prizeDistribution;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis type.
   * @return new instance of the info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis type
   */
  public info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis build() {
    final info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis _newInstance = (info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis) info.jchein.sandbox.app.crossword.resources.xw.XwFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.resources.xw.XwPackage.eINSTANCE.getPrizeAnalysis());
    if (m_featureDynamicInputSet) {
      _newInstance.setDynamicInput(m_dynamicInput);
    } else {
      if (m_featureDynamicInputBuilder != null) {
        _newInstance.setDynamicInput(m_featureDynamicInputBuilder.build());
      }
    }
    if (m_featurePrizeDistributionSet) {
      _newInstance.getPrizeDistribution().addAll(m_prizeDistribution);
    }
    return _newInstance;
  }

  public PrizeAnalysisBuilder dynamicInput(info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation p_dynamicInput) {
    m_dynamicInput = p_dynamicInput;
    m_featureDynamicInputSet = true;
    return this;
  }

  public PrizeAnalysisBuilder dynamicInput(
      info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation> p_revealedInformationBuilder) {
    m_featureDynamicInputBuilder = p_revealedInformationBuilder;
    return this;
  }

  public PrizeAnalysisBuilder prizeDistribution(Integer p_prizeDistribution) {
    m_prizeDistribution.add(p_prizeDistribution);
    m_featurePrizeDistributionSet = true;
    return this;
  }

  public PrizeAnalysisBuilder prizeDistribution(java.util.Collection<? extends Integer> p_prizeDistribution) {
    m_prizeDistribution.addAll(p_prizeDistribution);
    m_featurePrizeDistributionSet = true;
    return this;
  }
}
