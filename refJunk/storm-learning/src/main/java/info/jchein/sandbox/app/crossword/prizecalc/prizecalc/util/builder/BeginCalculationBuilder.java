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
  private java.lang.String m_ticketHref;
  private info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState m_ticketState;
  private info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<? extends info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState> m_featureTicketStateBuilder;
  // helper attributes
  private boolean m_featureTicketHrefSet = false;
  private boolean m_featureTicketStateSet = false;

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
    _builder.ticketHref(_beginCalculation.getTicketHref());
    _builder.ticketState(_beginCalculation.getTicketState());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public BeginCalculationBuilder but() {
    BeginCalculationBuilder _builder = newBeginCalculationBuilder();
    _builder.m_featureTicketHrefSet = m_featureTicketHrefSet;
    _builder.m_ticketHref = m_ticketHref;
    _builder.m_featureTicketStateSet = m_featureTicketStateSet;
    _builder.m_ticketState = m_ticketState;
    _builder.m_featureTicketStateBuilder = m_featureTicketStateBuilder;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getBeginCalculation());
    if (m_featureTicketHrefSet) {
      _newInstance.setTicketHref(m_ticketHref);
    }
    if (m_featureTicketStateSet) {
      _newInstance.setTicketState(m_ticketState);
    } else {
      if (m_featureTicketStateBuilder != null) {
        _newInstance.setTicketState(m_featureTicketStateBuilder.build());
      }
    }
    return _newInstance;
  }

  public BeginCalculationBuilder ticketHref(java.lang.String p_ticketHref) {
    m_ticketHref = p_ticketHref;
    m_featureTicketHrefSet = true;
    return this;
  }

  public BeginCalculationBuilder ticketState(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState p_ticketState) {
    m_ticketState = p_ticketState;
    m_featureTicketStateSet = true;
    return this;
  }

  public BeginCalculationBuilder ticketState(
      info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<? extends info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState> p_ticketStateBuilder) {
    m_featureTicketStateBuilder = p_ticketStateBuilder;
    return this;
  }
}
