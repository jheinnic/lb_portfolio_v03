/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult;

import info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Task Result</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TaskResultImpl#getTaskId <em>Task
 * Id</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TaskResultImpl#getTicketHref <em>
 * Ticket Href</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TaskResultImpl#getReport <em>Report
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TaskResultImpl extends MinimalEObjectImpl.Container implements TaskResult {
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
     * The cached value of the '{@link #getReport() <em>Report</em>}' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getReport()
     * @generated
     * @ordered
     */
    protected PrizeAnalysis report;


    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TaskResultImpl( ) {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass( ) {
        return PrizecalcPackage.Literals.TASK_RESULT;
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
                PrizecalcPackage.TASK_RESULT__TASK_ID,
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
                PrizecalcPackage.TASK_RESULT__TICKET_HREF,
                oldTicketHref,
                ticketHref));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrizeAnalysis getReport( ) {
        if (report != null && report.eIsProxy()) {
            InternalEObject oldReport = (InternalEObject) report;
            report = (PrizeAnalysis) eResolveProxy(oldReport);
            if (report != oldReport) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(
                        this,
                        Notification.RESOLVE,
                        PrizecalcPackage.TASK_RESULT__REPORT,
                        oldReport,
                        report));
            }
        }
        return report;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrizeAnalysis basicGetReport( ) {
        return report;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setReport( PrizeAnalysis newReport ) {
        PrizeAnalysis oldReport = report;
        report = newReport;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.TASK_RESULT__REPORT,
                oldReport,
                report));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType ) {
        switch (featureID) {
            case PrizecalcPackage.TASK_RESULT__TASK_ID :
                return getTaskId();
            case PrizecalcPackage.TASK_RESULT__TICKET_HREF :
                return getTicketHref();
            case PrizecalcPackage.TASK_RESULT__REPORT :
                if (resolve)
                    return getReport();
                return basicGetReport();
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
    @Override
    public void eSet( int featureID, Object newValue ) {
        switch (featureID) {
            case PrizecalcPackage.TASK_RESULT__TASK_ID :
                setTaskId((String) newValue);
                return;
            case PrizecalcPackage.TASK_RESULT__TICKET_HREF :
                setTicketHref((String) newValue);
                return;
            case PrizecalcPackage.TASK_RESULT__REPORT :
                setReport((PrizeAnalysis) newValue);
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
            case PrizecalcPackage.TASK_RESULT__TASK_ID :
                setTaskId(TASK_ID_EDEFAULT);
                return;
            case PrizecalcPackage.TASK_RESULT__TICKET_HREF :
                setTicketHref(TICKET_HREF_EDEFAULT);
                return;
            case PrizecalcPackage.TASK_RESULT__REPORT :
                setReport((PrizeAnalysis) null);
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
            case PrizecalcPackage.TASK_RESULT__TASK_ID :
                return TASK_ID_EDEFAULT == null ? taskId != null : !TASK_ID_EDEFAULT.equals(taskId);
            case PrizecalcPackage.TASK_RESULT__TICKET_HREF :
                return TICKET_HREF_EDEFAULT == null
                    ? ticketHref != null
                    : !TICKET_HREF_EDEFAULT.equals(ticketHref);
            case PrizecalcPackage.TASK_RESULT__REPORT :
                return report != null;
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
        result.append(" (taskId: ");
        result.append(taskId);
        result.append(", ticketHref: ");
        result.append(ticketHref);
        result.append(')');
        return result.toString();
    }

} // TaskResultImpl
