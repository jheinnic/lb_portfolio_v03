/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest;

import info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Report Prize Analysis Request</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.ReportPrizeAnalysisRequestImpl#getPrizeAnalysis <em>Prize Analysis</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReportPrizeAnalysisRequestImpl extends MinimalEObjectImpl.Container implements ReportPrizeAnalysisRequest {
    /**
     * The cached value of the '{@link #getPrizeAnalysis() <em>Prize Analysis</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrizeAnalysis()
     * @generated
     * @ordered
     */
    protected PrizeAnalysis prizeAnalysis;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ReportPrizeAnalysisRequestImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PrizecalcPackage.Literals.REPORT_PRIZE_ANALYSIS_REQUEST;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PrizeAnalysis getPrizeAnalysis() {
        if (prizeAnalysis != null && prizeAnalysis.eIsProxy()) {
            InternalEObject oldPrizeAnalysis = (InternalEObject)prizeAnalysis;
            prizeAnalysis = (PrizeAnalysis)eResolveProxy(oldPrizeAnalysis);
            if (prizeAnalysis != oldPrizeAnalysis) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PrizecalcPackage.REPORT_PRIZE_ANALYSIS_REQUEST__PRIZE_ANALYSIS, oldPrizeAnalysis, prizeAnalysis));
            }
        }
        return prizeAnalysis;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PrizeAnalysis basicGetPrizeAnalysis() {
        return prizeAnalysis;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPrizeAnalysis(PrizeAnalysis newPrizeAnalysis) {
        PrizeAnalysis oldPrizeAnalysis = prizeAnalysis;
        prizeAnalysis = newPrizeAnalysis;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PrizecalcPackage.REPORT_PRIZE_ANALYSIS_REQUEST__PRIZE_ANALYSIS, oldPrizeAnalysis, prizeAnalysis));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PrizecalcPackage.REPORT_PRIZE_ANALYSIS_REQUEST__PRIZE_ANALYSIS:
                if (resolve) return getPrizeAnalysis();
                return basicGetPrizeAnalysis();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PrizecalcPackage.REPORT_PRIZE_ANALYSIS_REQUEST__PRIZE_ANALYSIS:
                setPrizeAnalysis((PrizeAnalysis)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case PrizecalcPackage.REPORT_PRIZE_ANALYSIS_REQUEST__PRIZE_ANALYSIS:
                setPrizeAnalysis((PrizeAnalysis)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PrizecalcPackage.REPORT_PRIZE_ANALYSIS_REQUEST__PRIZE_ANALYSIS:
                return prizeAnalysis != null;
        }
        return super.eIsSet(featureID);
    }

} //ReportPrizeAnalysisRequestImpl
