/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bonus Word Coverage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.BonusWordCoverageImpl#isBonusPaid <em>Bonus Paid</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BonusWordCoverageImpl extends AbstractWordCoverageImpl implements BonusWordCoverage {
    /**
     * The default value of the '{@link #isBonusPaid() <em>Bonus Paid</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBonusPaid()
     * @generated
     * @ordered
     */
    protected static final boolean BONUS_PAID_EDEFAULT = false;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BonusWordCoverageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return XwPackage.Literals.BONUS_WORD_COVERAGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isBonusPaid() {
        // TODO: implement this method to return the 'Bonus Paid' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case XwPackage.BONUS_WORD_COVERAGE__BONUS_PAID:
                return isBonusPaid();
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
            case XwPackage.BONUS_WORD_COVERAGE__BONUS_PAID:
                return isBonusPaid() != BONUS_PAID_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

} //BonusWordCoverageImpl
