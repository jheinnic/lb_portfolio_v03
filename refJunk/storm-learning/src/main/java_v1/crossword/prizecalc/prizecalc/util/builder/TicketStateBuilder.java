package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TicketStateBuilder implements info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState> {
  // features and builders
  // helper attributes
  /**
   * Builder is not instantiated with a constructor.
   * @see #newTicketStateBuilder()
   */
  private TicketStateBuilder() {
  }

  /**
   * This method creates a new instance of the TicketStateBuilder.
   * @return new instance of the TicketStateBuilder
   */
  public static TicketStateBuilder newTicketStateBuilder() {
    return new TicketStateBuilder();
  }

  /**
   * This method creates a new instance of the TicketStateBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param ticketState The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the TicketStateBuilder
   */
  public static TicketStateBuilder newTicketStateBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState p_ticketState) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState _ticketState = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState) p_ticketState));
    c.copyReferences();
    TicketStateBuilder _builder = newTicketStateBuilder();
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public TicketStateBuilder but() {
    TicketStateBuilder _builder = newTicketStateBuilder();
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getTicketState());
    return _newInstance;
  }
}
