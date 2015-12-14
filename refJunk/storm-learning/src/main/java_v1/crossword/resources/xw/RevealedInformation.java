/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Revealed Information</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getLetterPool <em>Letter Pool</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#isTwentySpotPaid <em>Twenty Spot Paid</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getBonusValue <em>Bonus Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getRevealedInformation()
 * @model
 * @generated
 */
public interface RevealedInformation extends EObject {
    /**
     * Returns the value of the '<em><b>Letter Pool</b></em>' attribute.
     * The default value is <code>"__________________"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Letter Pool</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Letter Pool</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getRevealedInformation_LetterPool()
     * @model default="__________________" changeable="false"
     * @generated
     */
    String getLetterPool();

    /**
     * Returns the value of the '<em><b>Twenty Spot Paid</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Twenty Spot Paid</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Twenty Spot Paid</em>' attribute.
     * @see #isSetTwentySpotPaid()
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getRevealedInformation_TwentySpotPaid()
     * @model unsettable="true" changeable="false"
     * @generated
     */
    boolean isTwentySpotPaid();

    /**
     * Returns whether the value of the '{@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#isTwentySpotPaid <em>Twenty Spot Paid</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Twenty Spot Paid</em>' attribute is set.
     * @see #isTwentySpotPaid()
     * @generated
     */
    boolean isSetTwentySpotPaid();

    /**
     * Returns the value of the '<em><b>Bonus Value</b></em>' attribute.
     * The literals are from the enumeration {@link info.jchein.sandbox.app.crossword.resources.xw.BonusValue}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bonus Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bonus Value</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BonusValue
     * @see #isSetBonusValue()
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getRevealedInformation_BonusValue()
     * @model unsettable="true" changeable="false"
     * @generated
     */
    BonusValue getBonusValue();

    /**
     * Returns whether the value of the '{@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getBonusValue <em>Bonus Value</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Bonus Value</em>' attribute is set.
     * @see #getBonusValue()
     * @generated
     */
    boolean isSetBonusValue();

} // RevealedInformation
