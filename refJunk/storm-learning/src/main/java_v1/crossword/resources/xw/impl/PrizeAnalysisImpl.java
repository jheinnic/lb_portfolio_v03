/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis;
import info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail;
import info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Prize Analysis</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PrizeAnalysisImpl#getPrizeDistribution <em>Prize Distribution</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PrizeAnalysisImpl#getDynamicInput <em>Dynamic Input</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PrizeAnalysisImpl#getFixedInput <em>Fixed Input</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PrizeAnalysisImpl extends MinimalEObjectImpl.Container implements PrizeAnalysis {
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
     * The cached value of the '{@link #getDynamicInput() <em>Dynamic Input</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDynamicInput()
     * @generated
     * @ordered
     */
    protected RevealedInformation dynamicInput;

    /**
     * The cached value of the '{@link #getFixedInput() <em>Fixed Input</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFixedInput()
     * @generated
     * @ordered
     */
    protected PublishedTicketDetail fixedInput;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PrizeAnalysisImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return XwPackage.Literals.PRIZE_ANALYSIS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Integer> getPrizeDistribution() {
        if (prizeDistribution == null) {
            prizeDistribution = new EDataTypeUniqueEList<Integer>(Integer.class, this, XwPackage.PRIZE_ANALYSIS__PRIZE_DISTRIBUTION);
        }
        return prizeDistribution;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RevealedInformation getDynamicInput() {
        return dynamicInput;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDynamicInput(RevealedInformation newDynamicInput, NotificationChain msgs) {
        RevealedInformation oldDynamicInput = dynamicInput;
        dynamicInput = newDynamicInput;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XwPackage.PRIZE_ANALYSIS__DYNAMIC_INPUT, oldDynamicInput, newDynamicInput);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PublishedTicketDetail getFixedInput() {
        if (fixedInput != null && fixedInput.eIsProxy()) {
            InternalEObject oldFixedInput = (InternalEObject)fixedInput;
            fixedInput = (PublishedTicketDetail)eResolveProxy(oldFixedInput);
            if (fixedInput != oldFixedInput) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, XwPackage.PRIZE_ANALYSIS__FIXED_INPUT, oldFixedInput, fixedInput));
            }
        }
        return fixedInput;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PublishedTicketDetail basicGetFixedInput() {
        return fixedInput;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case XwPackage.PRIZE_ANALYSIS__DYNAMIC_INPUT:
                return basicSetDynamicInput(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case XwPackage.PRIZE_ANALYSIS__PRIZE_DISTRIBUTION:
                return getPrizeDistribution();
            case XwPackage.PRIZE_ANALYSIS__DYNAMIC_INPUT:
                return getDynamicInput();
            case XwPackage.PRIZE_ANALYSIS__FIXED_INPUT:
                if (resolve) return getFixedInput();
                return basicGetFixedInput();
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
            case XwPackage.PRIZE_ANALYSIS__PRIZE_DISTRIBUTION:
                return prizeDistribution != null && !prizeDistribution.isEmpty();
            case XwPackage.PRIZE_ANALYSIS__DYNAMIC_INPUT:
                return dynamicInput != null;
            case XwPackage.PRIZE_ANALYSIS__FIXED_INPUT:
                return fixedInput != null;
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
        result.append(" (prizeDistribution: ");
        result.append(prizeDistribution);
        result.append(')');
        return result.toString();
    }

} //PrizeAnalysisImpl
