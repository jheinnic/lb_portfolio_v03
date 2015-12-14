package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class SubtaskResponseBuilder implements
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse> {
  // features and builders
  // helper attributes
  /**
   * Builder is not instantiated with a constructor.
   * @see #newSubtaskResponseBuilder()
   */
  private SubtaskResponseBuilder() {
  }

  /**
   * This method creates a new instance of the SubtaskResponseBuilder.
   * @return new instance of the SubtaskResponseBuilder
   */
  public static SubtaskResponseBuilder newSubtaskResponseBuilder() {
    return new SubtaskResponseBuilder();
  }

  /**
   * This method creates a new instance of the SubtaskResponseBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param subtaskResponse The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the SubtaskResponseBuilder
   */
  public static SubtaskResponseBuilder newSubtaskResponseBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse p_subtaskResponse) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse _subtaskResponse = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse) p_subtaskResponse));
    c.copyReferences();
    SubtaskResponseBuilder _builder = newSubtaskResponseBuilder();
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public SubtaskResponseBuilder but() {
    SubtaskResponseBuilder _builder = newSubtaskResponseBuilder();
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getSubtaskResponse());
    return _newInstance;
  }
}
