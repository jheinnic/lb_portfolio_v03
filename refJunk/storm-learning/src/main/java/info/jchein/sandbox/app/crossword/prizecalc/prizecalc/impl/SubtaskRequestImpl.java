/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Subtask Request</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskRequestImpl#getSubTaskId
 * <em>Sub Task Id</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskRequestImpl#getTaskId <em>
 * Task Id</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskRequestImpl#getTicketHref
 * <em>Ticket Href</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskRequestImpl#getTicketState
 * <em>Ticket State</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskRequestImpl#getPStateInit
 * <em>PState Init</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskRequestImpl#getMaxPermutes
 * <em>Max Permutes</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class SubtaskRequestImpl extends MinimalEObjectImpl.Container implements SubtaskRequest {
    /**
     * The default value of the '{@link #getSubTaskId() <em>Sub Task Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSubTaskId()
     * @generated
     * @ordered
     */
    protected static final String SUB_TASK_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSubTaskId() <em>Sub Task Id</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSubTaskId()
     * @generated
     * @ordered
     */
    protected String subTaskId = SUB_TASK_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getTaskId() <em>Task Id</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTaskId()
     * @generated
     * @ordered
     */
    protected static final String TASK_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTaskId() <em>Task Id</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTaskId()
     * @generated
     * @ordered
     */
    protected String taskId = TASK_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getTicketHref() <em>Ticket Href</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTicketHref()
     * @generated
     * @ordered
     */
    protected static final String TICKET_HREF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTicketHref() <em>Ticket Href</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTicketHref()
     * @generated
     * @ordered
     */
    protected String ticketHref = TICKET_HREF_EDEFAULT;

    /**
     * The cached value of the '{@link #getTicketState() <em>Ticket State</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTicketState()
     * @generated
     * @ordered
     */
    protected TicketState ticketState;

    /**
     * The cached value of the '{@link #getPStateInit() <em>PState Init</em>}' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPStateInit()
     * @generated
     * @ordered
     */
    protected EList<Integer> pStateInit;

    /**
     * The default value of the '{@link #getMaxPermutes() <em>Max Permutes</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMaxPermutes()
     * @generated
     * @ordered
     */
    protected static final int MAX_PERMUTES_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMaxPermutes() <em>Max Permutes</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMaxPermutes()
     * @generated
     * @ordered
     */
    protected int maxPermutes = MAX_PERMUTES_EDEFAULT;


    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SubtaskRequestImpl( ) {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass( ) {
        return PrizecalcPackage.Literals.SUBTASK_REQUEST;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getSubTaskId( ) {
        return subTaskId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSubTaskId( String newSubTaskId ) {
        String oldSubTaskId = subTaskId;
        subTaskId = newSubTaskId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.SUBTASK_REQUEST__SUB_TASK_ID,
                oldSubTaskId,
                subTaskId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTaskId( ) {
        return taskId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTaskId( String newTaskId ) {
        String oldTaskId = taskId;
        taskId = newTaskId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.SUBTASK_REQUEST__TASK_ID,
                oldTaskId,
                taskId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTicketHref( ) {
        return ticketHref;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTicketHref( String newTicketHref ) {
        String oldTicketHref = ticketHref;
        ticketHref = newTicketHref;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.SUBTASK_REQUEST__TICKET_HREF,
                oldTicketHref,
                ticketHref));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TicketState getTicketState( ) {
        if (ticketState != null && ticketState.eIsProxy()) {
            InternalEObject oldTicketState = (InternalEObject) ticketState;
            ticketState = (TicketState) eResolveProxy(oldTicketState);
            if (ticketState != oldTicketState) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(
                        this,
                        Notification.RESOLVE,
                        PrizecalcPackage.SUBTASK_REQUEST__TICKET_STATE,
                        oldTicketState,
                        ticketState));
            }
        }
        return ticketState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TicketState basicGetTicketState( ) {
        return ticketState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTicketState( TicketState newTicketState ) {
        TicketState oldTicketState = ticketState;
        ticketState = newTicketState;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.SUBTASK_REQUEST__TICKET_STATE,
                oldTicketState,
                ticketState));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Integer> getPStateInit( ) {
        if (pStateInit == null) {
            pStateInit = new EDataTypeUniqueEList<Integer>(
                Integer.class,
                this,
                PrizecalcPackage.SUBTASK_REQUEST__PSTATE_INIT);
        }
        return pStateInit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getMaxPermutes( ) {
        return maxPermutes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMaxPermutes( int newMaxPermutes ) {
        int oldMaxPermutes = maxPermutes;
        maxPermutes = newMaxPermutes;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.SUBTASK_REQUEST__MAX_PERMUTES,
                oldMaxPermutes,
                maxPermutes));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType ) {
        switch (featureID) {
            case PrizecalcPackage.SUBTASK_REQUEST__SUB_TASK_ID :
                return getSubTaskId();
            case PrizecalcPackage.SUBTASK_REQUEST__TASK_ID :
                return getTaskId();
            case PrizecalcPackage.SUBTASK_REQUEST__TICKET_HREF :
                return getTicketHref();
            case PrizecalcPackage.SUBTASK_REQUEST__TICKET_STATE :
                if (resolve)
                    return getTicketState();
                return basicGetTicketState();
            case PrizecalcPackage.SUBTASK_REQUEST__PSTATE_INIT :
                return getPStateInit();
            case PrizecalcPackage.SUBTASK_REQUEST__MAX_PERMUTES :
                return getMaxPermutes();
        }
        return super.eGet(
            featureID,
            resolve,
            coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet( int featureID, Object newValue ) {
        switch (featureID) {
            case PrizecalcPackage.SUBTASK_REQUEST__SUB_TASK_ID :
                setSubTaskId((String) newValue);
                return;
            case PrizecalcPackage.SUBTASK_REQUEST__TASK_ID :
                setTaskId((String) newValue);
                return;
            case PrizecalcPackage.SUBTASK_REQUEST__TICKET_HREF :
                setTicketHref((String) newValue);
                return;
            case PrizecalcPackage.SUBTASK_REQUEST__TICKET_STATE :
                setTicketState((TicketState) newValue);
                return;
            case PrizecalcPackage.SUBTASK_REQUEST__PSTATE_INIT :
                getPStateInit().clear();
                getPStateInit().addAll(
                    (Collection<? extends Integer>) newValue);
                return;
            case PrizecalcPackage.SUBTASK_REQUEST__MAX_PERMUTES :
                setMaxPermutes((Integer) newValue);
                return;
        }
        super.eSet(
            featureID,
            newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset( int featureID ) {
        switch (featureID) {
            case PrizecalcPackage.SUBTASK_REQUEST__SUB_TASK_ID :
                setSubTaskId(SUB_TASK_ID_EDEFAULT);
                return;
            case PrizecalcPackage.SUBTASK_REQUEST__TASK_ID :
                setTaskId(TASK_ID_EDEFAULT);
                return;
            case PrizecalcPackage.SUBTASK_REQUEST__TICKET_HREF :
                setTicketHref(TICKET_HREF_EDEFAULT);
                return;
            case PrizecalcPackage.SUBTASK_REQUEST__TICKET_STATE :
                setTicketState((TicketState) null);
                return;
            case PrizecalcPackage.SUBTASK_REQUEST__PSTATE_INIT :
                getPStateInit().clear();
                return;
            case PrizecalcPackage.SUBTASK_REQUEST__MAX_PERMUTES :
                setMaxPermutes(MAX_PERMUTES_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet( int featureID ) {
        switch (featureID) {
            case PrizecalcPackage.SUBTASK_REQUEST__SUB_TASK_ID :
                return SUB_TASK_ID_EDEFAULT == null
                    ? subTaskId != null
                    : !SUB_TASK_ID_EDEFAULT.equals(subTaskId);
            case PrizecalcPackage.SUBTASK_REQUEST__TASK_ID :
                return TASK_ID_EDEFAULT == null ? taskId != null : !TASK_ID_EDEFAULT.equals(taskId);
            case PrizecalcPackage.SUBTASK_REQUEST__TICKET_HREF :
                return TICKET_HREF_EDEFAULT == null
                    ? ticketHref != null
                    : !TICKET_HREF_EDEFAULT.equals(ticketHref);
            case PrizecalcPackage.SUBTASK_REQUEST__TICKET_STATE :
                return ticketState != null;
            case PrizecalcPackage.SUBTASK_REQUEST__PSTATE_INIT :
                return pStateInit != null && !pStateInit.isEmpty();
            case PrizecalcPackage.SUBTASK_REQUEST__MAX_PERMUTES :
                return maxPermutes != MAX_PERMUTES_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString( ) {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(
            super.toString());
        result.append(" (subTaskId: ");
        result.append(subTaskId);
        result.append(", taskId: ");
        result.append(taskId);
        result.append(", ticketHref: ");
        result.append(ticketHref);
        result.append(", pStateInit: ");
        result.append(pStateInit);
        result.append(", maxPermutes: ");
        result.append(maxPermutes);
        result.append(')');
        return result.toString();
    }

} // SubtaskRequestImpl
