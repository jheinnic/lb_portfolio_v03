/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;

import java.util.BitSet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Available Letter</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AvailableLetterImpl#getLetter <em>
 * Letter</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AvailableLetterImpl#getWordHitMask
 * <em>Word Hit Mask</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AvailableLetterImpl extends MinimalEObjectImpl.Container implements AvailableLetter {
    /**
     * The default value of the '{@link #getLetter() <em>Letter</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getLetter()
     * @generated
     * @ordered
     */
    protected static final char LETTER_EDEFAULT = '\u0000';

    /**
     * The cached value of the '{@link #getLetter() <em>Letter</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getLetter()
     * @generated
     * @ordered
     */
    protected char letter = LETTER_EDEFAULT;

    /**
     * The default value of the '{@link #getWordHitMask() <em>Word Hit Mask</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWordHitMask()
     * @generated
     * @ordered
     */
    protected static final BitSet WORD_HIT_MASK_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWordHitMask() <em>Word Hit Mask</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWordHitMask()
     * @generated
     * @ordered
     */
    protected BitSet wordHitMask = WORD_HIT_MASK_EDEFAULT;


    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AvailableLetterImpl( ) {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass( ) {
        return PrizecalcPackage.Literals.AVAILABLE_LETTER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public char getLetter( ) {
        return letter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLetter( char newLetter ) {
        char oldLetter = letter;
        letter = newLetter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.AVAILABLE_LETTER__LETTER,
                oldLetter,
                letter));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BitSet getWordHitMask( ) {
        return wordHitMask;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setWordHitMask( BitSet newWordHitMask ) {
        BitSet oldWordHitMask = wordHitMask;
        wordHitMask = newWordHitMask;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.AVAILABLE_LETTER__WORD_HIT_MASK,
                oldWordHitMask,
                wordHitMask));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType ) {
        switch (featureID) {
            case PrizecalcPackage.AVAILABLE_LETTER__LETTER :
                return getLetter();
            case PrizecalcPackage.AVAILABLE_LETTER__WORD_HIT_MASK :
                return getWordHitMask();
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
            case PrizecalcPackage.AVAILABLE_LETTER__LETTER :
                setLetter((Character) newValue);
                return;
            case PrizecalcPackage.AVAILABLE_LETTER__WORD_HIT_MASK :
                setWordHitMask((BitSet) newValue);
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
            case PrizecalcPackage.AVAILABLE_LETTER__LETTER :
                setLetter(LETTER_EDEFAULT);
                return;
            case PrizecalcPackage.AVAILABLE_LETTER__WORD_HIT_MASK :
                setWordHitMask(WORD_HIT_MASK_EDEFAULT);
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
            case PrizecalcPackage.AVAILABLE_LETTER__LETTER :
                return letter != LETTER_EDEFAULT;
            case PrizecalcPackage.AVAILABLE_LETTER__WORD_HIT_MASK :
                return WORD_HIT_MASK_EDEFAULT == null
                    ? wordHitMask != null
                    : !WORD_HIT_MASK_EDEFAULT.equals(wordHitMask);
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
        result.append(" (letter: ");
        result.append(letter);
        result.append(", wordHitMask: ");
        result.append(wordHitMask);
        result.append(')');
        return result.toString();
    }

} // AvailableLetterImpl
