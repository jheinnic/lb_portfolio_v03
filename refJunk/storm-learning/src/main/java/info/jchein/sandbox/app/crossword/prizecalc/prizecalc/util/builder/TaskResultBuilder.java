package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TaskResultBuilder implements info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult> {
  // features and builders
  private info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis m_report;
  private info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis> m_featureReportBuilder;
  private java.lang.String m_taskId;
  private java.lang.String m_ticketHref;
  // helper attributes
  private boolean m_featureReportSet = false;
  private boolean m_featureTaskIdSet = false;
  private boolean m_featureTicketHrefSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newTaskResultBuilder()
   */
  private TaskResultBuilder() {
  }

  /**
   * This method creates a new instance of the TaskResultBuilder.
   * @return new instance of the TaskResultBuilder
   */
  public static TaskResultBuilder newTaskResultBuilder() {
    return new TaskResultBuilder();
  }

  /**
   * This method creates a new instance of the TaskResultBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param taskResult The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the TaskResultBuilder
   */
  public static TaskResultBuilder newTaskResultBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult p_taskResult) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult _taskResult = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult) p_taskResult));
    c.copyReferences();
    TaskResultBuilder _builder = newTaskResultBuilder();
    _builder.report(_taskResult.getReport());
    _builder.taskId(_taskResult.getTaskId());
    _builder.ticketHref(_taskResult.getTicketHref());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public TaskResultBuilder but() {
    TaskResultBuilder _builder = newTaskResultBuilder();
    _builder.m_featureReportSet = m_featureReportSet;
    _builder.m_report = m_report;
    _builder.m_featureReportBuilder = m_featureReportBuilder;
    _builder.m_featureTaskIdSet = m_featureTaskIdSet;
    _builder.m_taskId = m_taskId;
    _builder.m_featureTicketHrefSet = m_featureTicketHrefSet;
    _builder.m_ticketHref = m_ticketHref;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getTaskResult());
    if (m_featureReportSet) {
      _newInstance.setReport(m_report);
    } else {
      if (m_featureReportBuilder != null) {
        _newInstance.setReport(m_featureReportBuilder.build());
      }
    }
    if (m_featureTaskIdSet) {
      _newInstance.setTaskId(m_taskId);
    }
    if (m_featureTicketHrefSet) {
      _newInstance.setTicketHref(m_ticketHref);
    }
    return _newInstance;
  }

  public TaskResultBuilder report(info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis p_report) {
    m_report = p_report;
    m_featureReportSet = true;
    return this;
  }

  public TaskResultBuilder report(info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<? extends info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis> p_prizeAnalysisBuilder) {
    m_featureReportBuilder = p_prizeAnalysisBuilder;
    return this;
  }

  public TaskResultBuilder taskId(java.lang.String p_taskId) {
    m_taskId = p_taskId;
    m_featureTaskIdSet = true;
    return this;
  }

  public TaskResultBuilder ticketHref(java.lang.String p_ticketHref) {
    m_ticketHref = p_ticketHref;
    m_featureTicketHrefSet = true;
    return this;
  }
}
