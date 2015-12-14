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
  private java.lang.String m_subTaskId;
  private java.lang.String m_taskId;
  private java.lang.String m_ticketHref;
  private java.util.Collection<Integer> m_prizeDistribution = new java.util.LinkedList<Integer>();
  // helper attributes
  private boolean m_featurePrizeDistributionSet = false;
  private boolean m_featureSubTaskIdSet = false;
  private boolean m_featureTaskIdSet = false;
  private boolean m_featureTicketHrefSet = false;

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
    _builder.subTaskId(_subtaskResponse.getSubTaskId());
    _builder.taskId(_subtaskResponse.getTaskId());
    _builder.ticketHref(_subtaskResponse.getTicketHref());
    if (_subtaskResponse.getPrizeDistribution() != null) {
      _builder.prizeDistribution(_subtaskResponse.getPrizeDistribution());
    }
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public SubtaskResponseBuilder but() {
    SubtaskResponseBuilder _builder = newSubtaskResponseBuilder();
    _builder.m_featurePrizeDistributionSet = m_featurePrizeDistributionSet;
    _builder.m_prizeDistribution = m_prizeDistribution;
    _builder.m_featureSubTaskIdSet = m_featureSubTaskIdSet;
    _builder.m_subTaskId = m_subTaskId;
    _builder.m_featureTaskIdSet = m_featureTaskIdSet;
    _builder.m_taskId = m_taskId;
    _builder.m_featureTicketHrefSet = m_featureTicketHrefSet;
    _builder.m_ticketHref = m_ticketHref;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getSubtaskResponse());
    if (m_featureSubTaskIdSet) {
      _newInstance.setSubTaskId(m_subTaskId);
    }
    if (m_featureTaskIdSet) {
      _newInstance.setTaskId(m_taskId);
    }
    if (m_featureTicketHrefSet) {
      _newInstance.setTicketHref(m_ticketHref);
    }
    if (m_featurePrizeDistributionSet) {
      _newInstance.getPrizeDistribution().addAll(m_prizeDistribution);
    }
    return _newInstance;
  }

  public SubtaskResponseBuilder subTaskId(java.lang.String p_subTaskId) {
    m_subTaskId = p_subTaskId;
    m_featureSubTaskIdSet = true;
    return this;
  }

  public SubtaskResponseBuilder taskId(java.lang.String p_taskId) {
    m_taskId = p_taskId;
    m_featureTaskIdSet = true;
    return this;
  }

  public SubtaskResponseBuilder ticketHref(java.lang.String p_ticketHref) {
    m_ticketHref = p_ticketHref;
    m_featureTicketHrefSet = true;
    return this;
  }

  public SubtaskResponseBuilder prizeDistribution(Integer p_prizeDistribution) {
    m_prizeDistribution.add(p_prizeDistribution);
    m_featurePrizeDistributionSet = true;
    return this;
  }

  public SubtaskResponseBuilder prizeDistribution(java.util.Collection<? extends Integer> p_prizeDistribution) {
    m_prizeDistribution.addAll(p_prizeDistribution);
    m_featurePrizeDistributionSet = true;
    return this;
  }
}
