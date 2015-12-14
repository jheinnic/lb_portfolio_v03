/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Subtask Response</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getSubTaskId <em>Sub
 * Task Id</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getTaskId <em>Task Id
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getTicketHref <em>Ticket
 * Href</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getPrizeDistribution
 * <em>Prize Distribution</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskResponse()
 * @model
 * @generated
 */
public interface SubtaskResponse extends EObject {
    /**
     * Returns the value of the '<em><b>Sub Task Id</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Task Id</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Sub Task Id</em>' attribute.
     * @see #setSubTaskId(String)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskResponse_SubTaskId()
     * @model id="true"
     * @generated
     */
    String getSubTaskId( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getSubTaskId
     * <em>Sub Task Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Sub Task Id</em>' attribute.
     * @see #getSubTaskId()
     * @generated
     */
    void setSubTaskId( String value );

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
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskResponse_TaskId()
     * @model
     * @generated
     */
    String getTaskId( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getTaskId
     * <em>Task Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskResponse_TicketHref()
     * @model
     * @generated
     */
    String getTicketHref( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getTicketHref
     * <em>Ticket Href</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Ticket Href</em>' attribute.
     * @see #getTicketHref()
     * @generated
     */
    void setTicketHref( String value );

    /**
     * Returns the value of the '<em><b>Prize Distribution</b></em>' attribute list. The list contents are
     * of type {@link java.lang.Integer}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Prize Distribution</em>' attribute list.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskResponse_PrizeDistribution()
     * @model lower="18" upper="18"
     * @generated
     */
    EList<Integer> getPrizeDistribution( );

} // SubtaskResponse
