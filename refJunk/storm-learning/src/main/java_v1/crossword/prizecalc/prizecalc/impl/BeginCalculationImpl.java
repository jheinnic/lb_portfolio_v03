/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Begin Calculation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.BeginCalculationImpl#getTicketHref <em>Ticket Href</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.BeginCalculationImpl#getTicketState <em>Ticket State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BeginCalculationImpl extends MinimalEObjectImpl.Container implements BeginCalculation {
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
     * The cached value of the '{@link #getTicketState() <em>Ticket State</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTicketState()
     * @generated
     * @ordered
     */
    protected TicketState ticketState;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BeginCalculationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PrizecalcPackage.Literals.BEGIN_CALCULATION;
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
    public TicketState getTicketState() {
        if (ticketState != null && ticketState.eIsProxy()) {
            InternalEObject oldTicketState = (InternalEObject)ticketState;
            ticketState = (TicketState)eResolveProxy(oldTicketState);
            if (ticketState != oldTicketState) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PrizecalcPackage.BEGIN_CALCULATION__TICKET_STATE, oldTicketState, ticketState));
            }
        }
        return ticketState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TicketState basicGetTicketState() {
        return ticketState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PrizecalcPackage.BEGIN_CALCULATION__TICKET_HREF:
                return getTicketHref();
            case PrizecalcPackage.BEGIN_CALCULATION__TICKET_STATE:
                if (resolve) return getTicketState();
                return basicGetTicketState();
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
            case PrizecalcPackage.BEGIN_CALCULATION__TICKET_HREF:
                return TICKET_HREF_EDEFAULT == null ? ticketHref != null : !TICKET_HREF_EDEFAULT.equals(ticketHref);
            case PrizecalcPackage.BEGIN_CALCULATION__TICKET_STATE:
                return ticketState != null;
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
        result.append(" (ticketHref: ");
        result.append(ticketHref);
        result.append(')');
        return result.toString();
    }

} //BeginCalculationImpl
