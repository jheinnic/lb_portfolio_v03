/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Resolved Ticket</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getFixedDetails <em>Fixed
 * Details</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBoardContent <em>Board
 * Content</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBonusWord <em>Bonus Word
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#isTwentySpotIncluded <em>Twenty
 * Spot Included</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getLetterPool <em>Letter Pool
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#isTwentySpotPaid <em>Twenty Spot
 * Paid</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBonusValue <em>Bonus Value
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getPayoutTier <em>Payout Tier
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBoardCoverage <em>Board
 * Coverage</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getResolvedTicket()
 * @model
 * @generated
 */
public interface ResolvedTicket extends EObject {
    /**
     * Returns the value of the '<em><b>Fixed Details</b></em>' containment reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Fixed Details</em>' containment reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Fixed Details</em>' containment reference.
     * @see #setFixedDetails(PublishedTicketDetail)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getResolvedTicket_FixedDetails()
     * @model containment="true" required="true"
     * @generated
     */
    PublishedTicketDetail getFixedDetails( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getFixedDetails
     * <em>Fixed Details</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Fixed Details</em>' containment reference.
     * @see #getFixedDetails()
     * @generated
     */
    void setFixedDetails( PublishedTicketDetail value );

    /**
     * Returns the value of the '<em><b>Board Content</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Board Content</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Board Content</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getResolvedTicket_BoardContent()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    String getBoardContent( );

    /**
     * Returns the value of the '<em><b>Bonus Word</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bonus Word</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Bonus Word</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getResolvedTicket_BonusWord()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    String getBonusWord( );

    /**
     * Returns the value of the '<em><b>Twenty Spot Included</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Twenty Spot Included</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Twenty Spot Included</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getResolvedTicket_TwentySpotIncluded()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    boolean isTwentySpotIncluded( );

    /**
     * Returns the value of the '<em><b>Letter Pool</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Letter Pool</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Letter Pool</em>' attribute.
     * @see #setLetterPool(String)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getResolvedTicket_LetterPool()
     * @model
     * @generated
     */
    String getLetterPool( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getLetterPool
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
     * @see #setTwentySpotPaid(boolean)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getResolvedTicket_TwentySpotPaid()
     * @model
     * @generated
     */
    boolean isTwentySpotPaid( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#isTwentySpotPaid
     * <em>Twenty Spot Paid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Twenty Spot Paid</em>' attribute.
     * @see #isTwentySpotPaid()
     * @generated
     */
    void setTwentySpotPaid( boolean value );

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
     * @see #setBonusValue(BonusValue)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getResolvedTicket_BonusValue()
     * @model required="true"
     * @generated
     */
    BonusValue getBonusValue( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBonusValue
     * <em>Bonus Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Bonus Value</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BonusValue
     * @see #getBonusValue()
     * @generated
     */
    void setBonusValue( BonusValue value );

    /**
     * Returns the value of the '<em><b>Payout Tier</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Payout Tier</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Payout Tier</em>' attribute.
     * @see #setPayoutTier(int)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getResolvedTicket_PayoutTier()
     * @model
     * @generated
     */
    int getPayoutTier( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getPayoutTier
     * <em>Payout Tier</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Payout Tier</em>' attribute.
     * @see #getPayoutTier()
     * @generated
     */
    void setPayoutTier( int value );

    /**
     * Returns the value of the '<em><b>Board Coverage</b></em>' containment reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Board Coverage</em>' containment reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Board Coverage</em>' containment reference.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getResolvedTicket_BoardCoverage()
     * @model containment="true" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    BoardCoverage getBoardCoverage( );

} // ResolvedTicket
