/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Subtask Request</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getSubTaskId <em>Sub Task Id</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTaskId <em>Task Id</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTicketHref <em>Ticket Href</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTicketState <em>Ticket State</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getPStateInit <em>PState Init</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getMaxPermutes <em>Max Permutes</em>}</li>
 * </ul>
 * </p>
 *
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest()
 * @model
 * @generated
 */
public interface SubtaskRequest extends EObject {
    /**
     * Returns the value of the '<em><b>Sub Task Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Task Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sub Task Id</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_SubTaskId()
     * @model id="true" changeable="false"
     * @generated
     */
    String getSubTaskId();

    /**
     * Returns the value of the '<em><b>Task Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Task Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Task Id</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_TaskId()
     * @model changeable="false"
     * @generated
     */
    String getTaskId();

    /**
     * Returns the value of the '<em><b>Ticket Href</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ticket Href</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ticket Href</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_TicketHref()
     * @model changeable="false"
     * @generated
     */
    String getTicketHref();

    /**
     * Returns the value of the '<em><b>Ticket State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ticket State</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ticket State</em>' reference.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_TicketState()
     * @model changeable="false"
     * @generated
     */
    TicketState getTicketState();

    /**
     * Returns the value of the '<em><b>PState Init</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Integer}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>PState Init</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>PState Init</em>' attribute list.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_PStateInit()
     * @model lower="11" upper="28" changeable="false"
     * @generated
     */
    EList<Integer> getPStateInit();

    /**
     * Returns the value of the '<em><b>Max Permutes</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Max Permutes</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Max Permutes</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getSubtaskRequest_MaxPermutes()
     * @model changeable="false"
     * @generated
     */
    int getMaxPermutes();

} // SubtaskRequest
