package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class SubtaskRequestBuilder implements
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest> {
  // features and builders
  // helper attributes
  /**
   * Builder is not instantiated with a constructor.
   * @see #newSubtaskRequestBuilder()
   */
  private SubtaskRequestBuilder() {
  }

  /**
   * This method creates a new instance of the SubtaskRequestBuilder.
   * @return new instance of the SubtaskRequestBuilder
   */
  public static SubtaskRequestBuilder newSubtaskRequestBuilder() {
    return new SubtaskRequestBuilder();
  }

  /**
   * This method creates a new instance of the SubtaskRequestBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param subtaskRequest The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the SubtaskRequestBuilder
   */
  public static SubtaskRequestBuilder newSubtaskRequestBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest p_subtaskRequest) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest _subtaskRequest = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest) p_subtaskRequest));
    c.copyReferences();
    SubtaskRequestBuilder _builder = newSubtaskRequestBuilder();
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public SubtaskRequestBuilder but() {
    SubtaskRequestBuilder _builder = newSubtaskRequestBuilder();
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getSubtaskRequest());
    return _newInstance;
  }
}
