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
  // helper attributes
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
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public TaskResultBuilder but() {
    TaskResultBuilder _builder = newTaskResultBuilder();
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getTaskResult());
    return _newInstance;
  }
}
