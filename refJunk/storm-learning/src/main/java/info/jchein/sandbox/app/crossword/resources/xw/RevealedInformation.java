/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Revealed Information</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getLetterPool <em>Letter
 * Pool</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#isTwentySpotPaid <em>Twenty
 * Spot Paid</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getBonusValue <em>Bonus
 * Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getRevealedInformation()
 * @model
 * @generated
 */
public interface RevealedInformation extends EObject {
    /**
     * Returns the value of the '<em><b>Letter Pool</b></em>' attribute. The default value is
     * <code>"__________________"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Letter Pool</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Letter Pool</em>' attribute.
     * @see #setLetterPool(String)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getRevealedInformation_LetterPool()
     * @model default="__________________"
     * @generated
     */
    String getLetterPool( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getLetterPool
     * <em>Letter Pool</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Letter Pool</em>' attribute.
     * @see #getLetterPool()
     * @generated
     */
    void setLetterPool( String value );

    /**
     * Returns the value of the '<em><b>Twenty Spot Paid</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Twenty Spot Paid</em>' attribute isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Twenty Spot Paid</em>' attribute.
     * @see #isSetTwentySpotPaid()
     * @see #unsetTwentySpotPaid()
     * @see #setTwentySpotPaid(boolean)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getRevealedInformation_TwentySpotPaid()
     * @model unsettable="true"
     * @generated
     */
    boolean isTwentySpotPaid( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#isTwentySpotPaid
     * <em>Twenty Spot Paid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Twenty Spot Paid</em>' attribute.
     * @see #isSetTwentySpotPaid()
     * @see #unsetTwentySpotPaid()
     * @see #isTwentySpotPaid()
     * @generated
     */
    void setTwentySpotPaid( boolean value );

    /**
     * Unsets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#isTwentySpotPaid
     * <em>Twenty Spot Paid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isSetTwentySpotPaid()
     * @see #isTwentySpotPaid()
     * @see #setTwentySpotPaid(boolean)
     * @generated
     */
    void unsetTwentySpotPaid( );

    /**
     * Returns whether the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#isTwentySpotPaid
     * <em>Twenty Spot Paid</em>}' attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return whether the value of the '<em>Twenty Spot Paid</em>' attribute is set.
     * @see #unsetTwentySpotPaid()
     * @see #isTwentySpotPaid()
     * @see #setTwentySpotPaid(boolean)
     * @generated
     */
    boolean isSetTwentySpotPaid( );

    /**
     * Returns the value of the '<em><b>Bonus Value</b></em>' attribute. The literals are from the
     * enumeration {@link info.jchein.sandbox.app.crossword.resources.xw.BonusValue}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Bonus Value</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Bonus Value</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BonusValue
     * @see #isSetBonusValue()
     * @see #unsetBonusValue()
     * @see #setBonusValue(BonusValue)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getRevealedInformation_BonusValue()
     * @model unsettable="true"
     * @generated
     */
    BonusValue getBonusValue( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getBonusValue
     * <em>Bonus Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Bonus Value</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BonusValue
     * @see #isSetBonusValue()
     * @see #unsetBonusValue()
     * @see #getBonusValue()
     * @generated
     */
    void setBonusValue( BonusValue value );

    /**
     * Unsets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getBonusValue
     * <em>Bonus Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isSetBonusValue()
     * @see #getBonusValue()
     * @see #setBonusValue(BonusValue)
     * @generated
     */
    void unsetBonusValue( );

    /**
     * Returns whether the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getBonusValue
     * <em>Bonus Value</em>}' attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return whether the value of the '<em>Bonus Value</em>' attribute is set.
     * @see #unsetBonusValue()
     * @see #getBonusValue()
     * @see #setBonusValue(BonusValue)
     * @generated
     */
    boolean isSetBonusValue( );

} // RevealedInformation
