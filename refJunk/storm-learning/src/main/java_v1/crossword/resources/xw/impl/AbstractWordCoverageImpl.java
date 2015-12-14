/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Word Coverage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.AbstractWordCoverageImpl#getRawWord <em>Raw Word</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.AbstractWordCoverageImpl#getDisplayWord <em>Display Word</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.AbstractWordCoverageImpl#getUncoveredLetters <em>Uncovered Letters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractWordCoverageImpl extends MinimalEObjectImpl.Container implements AbstractWordCoverage {
    /**
     * The default value of the '{@link #getRawWord() <em>Raw Word</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRawWord()
     * @generated
     * @ordered
     */
    protected static final String RAW_WORD_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRawWord() <em>Raw Word</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRawWord()
     * @generated
     * @ordered
     */
    protected String rawWord = RAW_WORD_EDEFAULT;

    /**
     * The default value of the '{@link #getDisplayWord() <em>Display Word</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayWord()
     * @generated
     * @ordered
     */
    protected static final String DISPLAY_WORD_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDisplayWord() <em>Display Word</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayWord()
     * @generated
     * @ordered
     */
    protected String displayWord = DISPLAY_WORD_EDEFAULT;

    /**
     * The default value of the '{@link #getUncoveredLetters() <em>Uncovered Letters</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUncoveredLetters()
     * @generated
     * @ordered
     */
    protected static final String UNCOVERED_LETTERS_EDEFAULT = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractWordCoverageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return XwPackage.Literals.ABSTRACT_WORD_COVERAGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRawWord() {
        return rawWord;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDisplayWord() {
        return displayWord;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUncoveredLetters() {
        // TODO: implement this method to return the 'Uncovered Letters' attribute
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
            case XwPackage.ABSTRACT_WORD_COVERAGE__RAW_WORD:
                return getRawWord();
            case XwPackage.ABSTRACT_WORD_COVERAGE__DISPLAY_WORD:
                return getDisplayWord();
            case XwPackage.ABSTRACT_WORD_COVERAGE__UNCOVERED_LETTERS:
                return getUncoveredLetters();
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
            case XwPackage.ABSTRACT_WORD_COVERAGE__RAW_WORD:
                return RAW_WORD_EDEFAULT == null ? rawWord != null : !RAW_WORD_EDEFAULT.equals(rawWord);
            case XwPackage.ABSTRACT_WORD_COVERAGE__DISPLAY_WORD:
                return DISPLAY_WORD_EDEFAULT == null ? displayWord != null : !DISPLAY_WORD_EDEFAULT.equals(displayWord);
            case XwPackage.ABSTRACT_WORD_COVERAGE__UNCOVERED_LETTERS:
                return UNCOVERED_LETTERS_EDEFAULT == null ? getUncoveredLetters() != null : !UNCOVERED_LETTERS_EDEFAULT.equals(getUncoveredLetters());
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
        result.append(" (rawWord: ");
        result.append(rawWord);
        result.append(", displayWord: ");
        result.append(displayWord);
        result.append(')');
        return result.toString();
    }

} //AbstractWordCoverageImpl
