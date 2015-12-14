/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Task Result</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getTaskId <em>Task Id</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getTicketHref <em>Ticket Href
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getReport <em>Report</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getTaskResult()
 * @model
 * @generated
 */
public interface TaskResult extends EObject {
    /**
     * Returns the value of the '<em><b>Task Id</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Task Id</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Task Id</em>' attribute.
     * @see #setTaskId(String)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getTaskResult_TaskId()
     * @model id="true"
     * @generated
     */
    String getTaskId( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getTaskId <em>Task Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Task Id</em>' attribute.
     * @see #getTaskId()
     * @generated
     */
    void setTaskId( String value );

    /**
     * Returns the value of the '<em><b>Ticket Href</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ticket Href</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Ticket Href</em>' attribute.
     * @see #setTicketHref(String)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getTaskResult_TicketHref()
     * @model
     * @generated
     */
    String getTicketHref( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getTicketHref
     * <em>Ticket Href</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Ticket Href</em>' attribute.
     * @see #getTicketHref()
     * @generated
     */
    void setTicketHref( String value );

    /**
     * Returns the value of the '<em><b>Report</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Report</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Report</em>' reference.
     * @see #setReport(PrizeAnalysis)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getTaskResult_Report()
     * @model
     * @generated
     */
    PrizeAnalysis getReport( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getReport <em>Report</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Report</em>' reference.
     * @see #getReport()
     * @generated
     */
    void setReport( PrizeAnalysis value );

} // TaskResult
