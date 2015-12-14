package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class AvailableLetterBuilder implements
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter> {
  // features and builders
  // helper attributes
  /**
   * Builder is not instantiated with a constructor.
   * @see #newAvailableLetterBuilder()
   */
  private AvailableLetterBuilder() {
  }

  /**
   * This method creates a new instance of the AvailableLetterBuilder.
   * @return new instance of the AvailableLetterBuilder
   */
  public static AvailableLetterBuilder newAvailableLetterBuilder() {
    return new AvailableLetterBuilder();
  }

  /**
   * This method creates a new instance of the AvailableLetterBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param availableLetter The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the AvailableLetterBuilder
   */
  public static AvailableLetterBuilder newAvailableLetterBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter p_availableLetter) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter _availableLetter = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter) p_availableLetter));
    c.copyReferences();
    AvailableLetterBuilder _builder = newAvailableLetterBuilder();
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public AvailableLetterBuilder but() {
    AvailableLetterBuilder _builder = newAvailableLetterBuilder();
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getAvailableLetter());
    return _newInstance;
  }
}