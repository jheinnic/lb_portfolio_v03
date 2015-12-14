/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Grid Word Coverage</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.GridWordCoverageImpl#isWordCovered <em>
 * Word Covered</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.GridWordCoverageImpl#isTripling <em>
 * Tripling</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GridWordCoverageImpl extends AbstractWordCoverageImpl implements GridWordCoverage {
    /**
     * The default value of the '{@link #isWordCovered() <em>Word Covered</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isWordCovered()
     * @generated
     * @ordered
     */
    protected static final boolean WORD_COVERED_EDEFAULT = false;

    /**
     * The default value of the '{@link #isTripling() <em>Tripling</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isTripling()
     * @generated
     * @ordered
     */
    protected static final boolean TRIPLING_EDEFAULT = false;


    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public GridWordCoverageImpl( ) {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass( ) {
        return XwPackage.Literals.GRID_WORD_COVERAGE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isWordCovered( ) {
        // TODO: implement this method to return the 'Word Covered' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isTripling( ) {
        // TODO: implement this method to return the 'Tripling' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType ) {
        switch (featureID) {
            case XwPackage.GRID_WORD_COVERAGE__WORD_COVERED :
                return isWordCovered();
            case XwPackage.GRID_WORD_COVERAGE__TRIPLING :
                return isTripling();
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
    public boolean eIsSet( int featureID ) {
        switch (featureID) {
            case XwPackage.GRID_WORD_COVERAGE__WORD_COVERED :
                return isWordCovered() != WORD_COVERED_EDEFAULT;
            case XwPackage.GRID_WORD_COVERAGE__TRIPLING :
                return isTripling() != TRIPLING_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

} // GridWordCoverageImpl
