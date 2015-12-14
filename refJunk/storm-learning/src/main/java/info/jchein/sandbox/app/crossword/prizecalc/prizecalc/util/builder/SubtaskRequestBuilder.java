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
  private Integer m_maxPermutes;
  private java.lang.String m_subTaskId;
  private java.lang.String m_taskId;
  private java.lang.String m_ticketHref;
  private info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState m_ticketState;
  private info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<? extends info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState> m_featureTicketStateBuilder;
  private java.util.Collection<Integer> m_pStateInit = new java.util.LinkedList<Integer>();
  // helper attributes
  private boolean m_featureMaxPermutesSet = false;
  private boolean m_featurePStateInitSet = false;
  private boolean m_featureSubTaskIdSet = false;
  private boolean m_featureTaskIdSet = false;
  private boolean m_featureTicketHrefSet = false;
  private boolean m_featureTicketStateSet = false;

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
    _builder.maxPermutes(_subtaskRequest.getMaxPermutes());
    _builder.subTaskId(_subtaskRequest.getSubTaskId());
    _builder.taskId(_subtaskRequest.getTaskId());
    _builder.ticketHref(_subtaskRequest.getTicketHref());
    _builder.ticketState(_subtaskRequest.getTicketState());
    if (_subtaskRequest.getPStateInit() != null) {
      _builder.pStateInit(_subtaskRequest.getPStateInit());
    }
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public SubtaskRequestBuilder but() {
    SubtaskRequestBuilder _builder = newSubtaskRequestBuilder();
    _builder.m_featureMaxPermutesSet = m_featureMaxPermutesSet;
    _builder.m_maxPermutes = m_maxPermutes;
    _builder.m_featurePStateInitSet = m_featurePStateInitSet;
    _builder.m_pStateInit = m_pStateInit;
    _builder.m_featureSubTaskIdSet = m_featureSubTaskIdSet;
    _builder.m_subTaskId = m_subTaskId;
    _builder.m_featureTaskIdSet = m_featureTaskIdSet;
    _builder.m_taskId = m_taskId;
    _builder.m_featureTicketHrefSet = m_featureTicketHrefSet;
    _builder.m_ticketHref = m_ticketHref;
    _builder.m_featureTicketStateSet = m_featureTicketStateSet;
    _builder.m_ticketState = m_ticketState;
    _builder.m_featureTicketStateBuilder = m_featureTicketStateBuilder;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getSubtaskRequest());
    if (m_featureMaxPermutesSet) {
      _newInstance.setMaxPermutes(m_maxPermutes);
    }
    if (m_featureSubTaskIdSet) {
      _newInstance.setSubTaskId(m_subTaskId);
    }
    if (m_featureTaskIdSet) {
      _newInstance.setTaskId(m_taskId);
    }
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
    if (m_featurePStateInitSet) {
      _newInstance.getPStateInit().addAll(m_pStateInit);
    }
    return _newInstance;
  }

  public SubtaskRequestBuilder maxPermutes(Integer p_maxPermutes) {
    m_maxPermutes = p_maxPermutes;
    m_featureMaxPermutesSet = true;
    return this;
  }

  public SubtaskRequestBuilder subTaskId(java.lang.String p_subTaskId) {
    m_subTaskId = p_subTaskId;
    m_featureSubTaskIdSet = true;
    return this;
  }

  public SubtaskRequestBuilder taskId(java.lang.String p_taskId) {
    m_taskId = p_taskId;
    m_featureTaskIdSet = true;
    return this;
  }

  public SubtaskRequestBuilder ticketHref(java.lang.String p_ticketHref) {
    m_ticketHref = p_ticketHref;
    m_featureTicketHrefSet = true;
    return this;
  }

  public SubtaskRequestBuilder ticketState(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState p_ticketState) {
    m_ticketState = p_ticketState;
    m_featureTicketStateSet = true;
    return this;
  }

  public SubtaskRequestBuilder ticketState(
      info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<? extends info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState> p_ticketStateBuilder) {
    m_featureTicketStateBuilder = p_ticketStateBuilder;
    return this;
  }

  public SubtaskRequestBuilder pStateInit(Integer p_pStateInit) {
    m_pStateInit.add(p_pStateInit);
    m_featurePStateInitSet = true;
    return this;
  }

  public SubtaskRequestBuilder pStateInit(java.util.Collection<? extends Integer> p_pStateInit) {
    m_pStateInit.addAll(p_pStateInit);
    m_featurePStateInitSet = true;
    return this;
  }
}
