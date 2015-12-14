/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import java.util.BitSet;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Available Letter</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter#getLetter <em>Letter
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter#getWordHitMask <em>Word
 * Hit Mask</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getAvailableLetter()
 * @model
 * @generated
 */
public interface AvailableLetter extends EObject {
    /**
     * Returns the value of the '<em><b>Letter</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Letter</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Letter</em>' attribute.
     * @see #setLetter(char)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getAvailableLetter_Letter()
     * @model required="true"
     * @generated
     */
    char getLetter( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter#getLetter
     * <em>Letter</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Letter</em>' attribute.
     * @see #getLetter()
     * @generated
     */
    void setLetter( char value );

    /**
     * Returns the value of the '<em><b>Word Hit Mask</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Word Hit Mask</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Word Hit Mask</em>' attribute.
     * @see #setWordHitMask(BitSet)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getAvailableLetter_WordHitMask()
     * @model dataType="info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BitSet" required="true"
     * @generated
     */
    BitSet getWordHitMask( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter#getWordHitMask
     * <em>Word Hit Mask</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Word Hit Mask</em>' attribute.
     * @see #getWordHitMask()
     * @generated
     */
    void setWordHitMask( BitSet value );

} // AvailableLetter
