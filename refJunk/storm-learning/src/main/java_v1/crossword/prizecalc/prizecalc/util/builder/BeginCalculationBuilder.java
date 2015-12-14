package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class BeginCalculationBuilder implements
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation> {
  // features and builders
  // helper attributes
  /**
   * Builder is not instantiated with a constructor.
   * @see #newBeginCalculationBuilder()
   */
  private BeginCalculationBuilder() {
  }

  /**
   * This method creates a new instance of the BeginCalculationBuilder.
   * @return new instance of the BeginCalculationBuilder
   */
  public static BeginCalculationBuilder newBeginCalculationBuilder() {
    return new BeginCalculationBuilder();
  }

  /**
   * This method creates a new instance of the BeginCalculationBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param beginCalculation The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the BeginCalculationBuilder
   */
  public static BeginCalculationBuilder newBeginCalculationBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation p_beginCalculation) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation _beginCalculation = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation) p_beginCalculation));
    c.copyReferences();
    BeginCalculationBuilder _builder = newBeginCalculationBuilder();
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public BeginCalculationBuilder but() {
    BeginCalculationBuilder _builder = newBeginCalculationBuilder();
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getBeginCalculation());
    return _newInstance;
  }
}
