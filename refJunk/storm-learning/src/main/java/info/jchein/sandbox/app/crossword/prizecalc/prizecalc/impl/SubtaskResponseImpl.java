/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Subtask Response</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl#getSubTaskId
 * <em>Sub Task Id</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl#getTaskId <em>
 * Task Id</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl#getTicketHref
 * <em>Ticket Href</em>}</li>
 * <li>
 * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl#getPrizeDistribution
 * <em>Prize Distribution</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class SubtaskResponseImpl extends MinimalEObjectImpl.Container implements SubtaskResponse {
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
     * The cached value of the '{@link #getPrizeDistribution() <em>Prize Distribution</em>}' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPrizeDistribution()
     * @generated
     * @ordered
     */
    protected EList<Integer> prizeDistribution;


    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SubtaskResponseImpl( ) {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass( ) {
        return PrizecalcPackage.Literals.SUBTASK_RESPONSE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getSubTaskId( ) {
        return this.subTaskId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSubTaskId( final String newSubTaskId ) {
        final String oldSubTaskId = this.subTaskId;
        this.subTaskId = newSubTaskId;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.SUBTASK_RESPONSE__SUB_TASK_ID,
                oldSubTaskId,
                this.subTaskId));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getTaskId( ) {
        return this.taskId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTaskId( final String newTaskId ) {
        final String oldTaskId = this.taskId;
        this.taskId = newTaskId;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.SUBTASK_RESPONSE__TASK_ID,
                oldTaskId,
                this.taskId));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getTicketHref( ) {
        return this.ticketHref;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTicketHref( final String newTicketHref ) {
        final String oldTicketHref = this.ticketHref;
        this.ticketHref = newTicketHref;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.SUBTASK_RESPONSE__TICKET_HREF,
                oldTicketHref,
                this.ticketHref));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Integer> getPrizeDistribution( ) {
        if (this.prizeDistribution == null) {
            this.prizeDistribution = new EDataTypeUniqueEList<Integer>(
                Integer.class,
                this,
                PrizecalcPackage.SUBTASK_RESPONSE__PRIZE_DISTRIBUTION);
        }
        return this.prizeDistribution;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( final int featureID, final boolean resolve, final boolean coreType ) {
        switch (featureID) {
            case PrizecalcPackage.SUBTASK_RESPONSE__SUB_TASK_ID :
                return getSubTaskId();
            case PrizecalcPackage.SUBTASK_RESPONSE__TASK_ID :
                return getTaskId();
            case PrizecalcPackage.SUBTASK_RESPONSE__TICKET_HREF :
                return getTicketHref();
            case PrizecalcPackage.SUBTASK_RESPONSE__PRIZE_DISTRIBUTION :
                return getPrizeDistribution();
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
    public void eSet( final int featureID, final Object newValue ) {
        switch (featureID) {
            case PrizecalcPackage.SUBTASK_RESPONSE__SUB_TASK_ID :
                setSubTaskId((String) newValue);
                return;
            case PrizecalcPackage.SUBTASK_RESPONSE__TASK_ID :
                setTaskId((String) newValue);
                return;
            case PrizecalcPackage.SUBTASK_RESPONSE__TICKET_HREF :
                setTicketHref((String) newValue);
                return;
            case PrizecalcPackage.SUBTASK_RESPONSE__PRIZE_DISTRIBUTION :
                getPrizeDistribution().clear();
                getPrizeDistribution().addAll(
                    (Collection<? extends Integer>) newValue);
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
    public void eUnset( final int featureID ) {
        switch (featureID) {
            case PrizecalcPackage.SUBTASK_RESPONSE__SUB_TASK_ID :
                setSubTaskId(SUB_TASK_ID_EDEFAULT);
                return;
            case PrizecalcPackage.SUBTASK_RESPONSE__TASK_ID :
                setTaskId(TASK_ID_EDEFAULT);
                return;
            case PrizecalcPackage.SUBTASK_RESPONSE__TICKET_HREF :
                setTicketHref(TICKET_HREF_EDEFAULT);
                return;
            case PrizecalcPackage.SUBTASK_RESPONSE__PRIZE_DISTRIBUTION :
                getPrizeDistribution().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean eIsSet( final int featureID ) {
        switch (featureID) {
            case PrizecalcPackage.SUBTASK_RESPONSE__SUB_TASK_ID :
                return SUB_TASK_ID_EDEFAULT == null
                    ? this.subTaskId != null
                    : !SUB_TASK_ID_EDEFAULT.equals(this.subTaskId);
            case PrizecalcPackage.SUBTASK_RESPONSE__TASK_ID :
                return TASK_ID_EDEFAULT == null ? this.taskId != null : !TASK_ID_EDEFAULT.equals(this.taskId);
            case PrizecalcPackage.SUBTASK_RESPONSE__TICKET_HREF :
                return TICKET_HREF_EDEFAULT == null
                    ? this.ticketHref != null
                    : !TICKET_HREF_EDEFAULT.equals(this.ticketHref);
            case PrizecalcPackage.SUBTASK_RESPONSE__PRIZE_DISTRIBUTION :
                return (this.prizeDistribution != null) && !this.prizeDistribution.isEmpty();
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
        if (eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(
            super.toString());
        result.append(" (subTaskId: ");
        result.append(this.subTaskId);
        result.append(", taskId: ");
        result.append(this.taskId);
        result.append(", ticketHref: ");
        result.append(this.ticketHref);
        result.append(", prizeDistribution: ");
        result.append(this.prizeDistribution);
        result.append(')');
        return result.toString();
    }

} // SubtaskResponseImpl
