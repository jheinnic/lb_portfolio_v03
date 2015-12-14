/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Subtask Request</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getSubTaskId <em>Sub Task
 * Id</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTaskId <em>Task Id
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTicketHref <em>Ticket
 * Href</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTicketState <em>Ticket
 * State</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getPStateInit <em>PState
 * Init</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getMaxPermutes <em>Max
 * Permutes</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest()
 * @model
 * @generated
 */
public interface SubtaskRequest extends EObject {
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
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_SubTaskId()
     * @model id="true"
     * @generated
     */
    String getSubTaskId( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getSubTaskId
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
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_TaskId()
     * @model
     * @generated
     */
    String getTaskId( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTaskId
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
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_TicketHref()
     * @model
     * @generated
     */
    String getTicketHref( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTicketHref
     * <em>Ticket Href</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Ticket Href</em>' attribute.
     * @see #getTicketHref()
     * @generated
     */
    void setTicketHref( String value );

    /**
     * Returns the value of the '<em><b>Ticket State</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ticket State</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Ticket State</em>' reference.
     * @see #setTicketState(TicketState)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_TicketState()
     * @model
     * @generated
     */
    TicketState getTicketState( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTicketState
     * <em>Ticket State</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Ticket State</em>' reference.
     * @see #getTicketState()
     * @generated
     */
    void setTicketState( TicketState value );

    /**
     * Returns the value of the '<em><b>PState Init</b></em>' attribute list. The list contents are of type
     * {@link java.lang.Integer}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>PState Init</em>' attribute list.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_PStateInit()
     * @model lower="11" upper="28"
     * @generated
     */
    EList<Integer> getPStateInit( );

    /**
     * Returns the value of the '<em><b>Max Permutes</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Max Permutes</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Max Permutes</em>' attribute.
     * @see #setMaxPermutes(int)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_MaxPermutes()
     * @model
     * @generated
     */
    int getMaxPermutes( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getMaxPermutes
     * <em>Max Permutes</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Max Permutes</em>' attribute.
     * @see #getMaxPermutes()
     * @generated
     */
    void setMaxPermutes( int value );

} // SubtaskRequest
