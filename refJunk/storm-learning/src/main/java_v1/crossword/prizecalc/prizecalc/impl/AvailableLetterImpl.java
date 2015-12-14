/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;

import java.util.BitSet;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Available Letter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AvailableLetterImpl#getLetter <em>Letter</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AvailableLetterImpl#getWordHitMask <em>Word Hit Mask</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AvailableLetterImpl extends MinimalEObjectImpl.Container implements AvailableLetter {
    /**
     * The default value of the '{@link #getLetter() <em>Letter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLetter()
     * @generated
     * @ordered
     */
    protected static final char LETTER_EDEFAULT = '\u0000';

    /**
     * The cached value of the '{@link #getLetter() <em>Letter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLetter()
     * @generated
     * @ordered
     */
    protected char letter = LETTER_EDEFAULT;

    /**
     * The default value of the '{@link #getWordHitMask() <em>Word Hit Mask</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWordHitMask()
     * @generated
     * @ordered
     */
    protected static final BitSet WORD_HIT_MASK_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWordHitMask() <em>Word Hit Mask</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWordHitMask()
     * @generated
     * @ordered
     */
    protected BitSet wordHitMask = WORD_HIT_MASK_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AvailableLetterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PrizecalcPackage.Literals.AVAILABLE_LETTER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public char getLetter() {
        return letter;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BitSet getWordHitMask() {
        return wordHitMask;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PrizecalcPackage.AVAILABLE_LETTER__LETTER:
                return getLetter();
            case PrizecalcPackage.AVAILABLE_LETTER__WORD_HIT_MASK:
                return getWordHitMask();
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
            case PrizecalcPackage.AVAILABLE_LETTER__LETTER:
                return letter != LETTER_EDEFAULT;
            case PrizecalcPackage.AVAILABLE_LETTER__WORD_HIT_MASK:
                return WORD_HIT_MASK_EDEFAULT == null ? wordHitMask != null : !WORD_HIT_MASK_EDEFAULT.equals(wordHitMask);
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
        result.append(" (letter: ");
        result.append(letter);
        result.append(", wordHitMask: ");
        result.append(wordHitMask);
        result.append(')');
        return result.toString();
    }

} //AvailableLetterImpl
