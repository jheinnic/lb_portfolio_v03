/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Subtask Response</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl#getSubTaskId <em>Sub Task Id</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl#getTaskId <em>Task Id</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl#getTicketHref <em>Ticket Href</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl#getPrizeDistribution <em>Prize Distribution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SubtaskResponseImpl extends MinimalEObjectImpl.Container implements SubtaskResponse {
    /**
     * The default value of the '{@link #getSubTaskId() <em>Sub Task Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSubTaskId()
     * @generated
     * @ordered
     */
    protected static final String SUB_TASK_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSubTaskId() <em>Sub Task Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSubTaskId()
     * @generated
     * @ordered
     */
    protected String subTaskId = SUB_TASK_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getTaskId() <em>Task Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTaskId()
     * @generated
     * @ordered
     */
    protected static final String TASK_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTaskId() <em>Task Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTaskId()
     * @generated
     * @ordered
     */
    protected String taskId = TASK_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getTicketHref() <em>Ticket Href</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTicketHref()
     * @generated
     * @ordered
     */
    protected static final String TICKET_HREF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTicketHref() <em>Ticket Href</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTicketHref()
     * @generated
     * @ordered
     */
    protected String ticketHref = TICKET_HREF_EDEFAULT;

    /**
     * The cached value of the '{@link #getPrizeDistribution() <em>Prize Distribution</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrizeDistribution()
     * @generated
     * @ordered
     */
    protected EList<Integer> prizeDistribution;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SubtaskResponseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PrizecalcPackage.Literals.SUBTASK_RESPONSE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSubTaskId() {
        return subTaskId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTicketHref() {
        return ticketHref;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Integer> getPrizeDistribution() {
        if (prizeDistribution == null) {
            prizeDistribution = new EDataTypeUniqueEList<Integer>(Integer.class, this, PrizecalcPackage.SUBTASK_RESPONSE__PRIZE_DISTRIBUTION);
        }
        return prizeDistribution;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PrizecalcPackage.SUBTASK_RESPONSE__SUB_TASK_ID:
                return getSubTaskId();
            case PrizecalcPackage.SUBTASK_RESPONSE__TASK_ID:
                return getTaskId();
            case PrizecalcPackage.SUBTASK_RESPONSE__TICKET_HREF:
                return getTicketHref();
            case PrizecalcPackage.SUBTASK_RESPONSE__PRIZE_DISTRIBUTION:
                return getPrizeDistribution();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PrizecalcPackage.SUBTASK_RESPONSE__SUB_TASK_ID:
                return SUB_TASK_ID_EDEFAULT == null ? subTaskId != null : !SUB_TASK_ID_EDEFAULT.equals(subTaskId);
            case PrizecalcPackage.SUBTASK_RESPONSE__TASK_ID:
                return TASK_ID_EDEFAULT == null ? taskId != null : !TASK_ID_EDEFAULT.equals(taskId);
            case PrizecalcPackage.SUBTASK_RESPONSE__TICKET_HREF:
                return TICKET_HREF_EDEFAULT == null ? ticketHref != null : !TICKET_HREF_EDEFAULT.equals(ticketHref);
            case PrizecalcPackage.SUBTASK_RESPONSE__PRIZE_DISTRIBUTION:
                return prizeDistribution != null && !prizeDistribution.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (subTaskId: ");
        result.append(subTaskId);
        result.append(", taskId: ");
        result.append(taskId);
        result.append(", ticketHref: ");
        result.append(ticketHref);
        result.append(", prizeDistribution: ");
        result.append(prizeDistribution);
        result.append(')');
        return result.toString();
    }

} //SubtaskResponseImpl
