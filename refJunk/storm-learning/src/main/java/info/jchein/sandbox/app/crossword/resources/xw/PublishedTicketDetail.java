/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Published Ticket Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#getBoardContent <em>Board
 * Content</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#getBonusWord <em>Bonus
 * Word</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#isTwentySpotIncluded <em>
 * Twenty Spot Included</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPublishedTicketDetail()
 * @model
 * @generated
 */
public interface PublishedTicketDetail extends EObject {
    /**
     * Returns the value of the '<em><b>Board Content</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Board Content</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Board Content</em>' attribute.
     * @see #setBoardContent(String)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPublishedTicketDetail_BoardContent()
     * @model
     * @generated
     */
    String getBoardContent( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#getBoardContent
     * <em>Board Content</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Board Content</em>' attribute.
     * @see #getBoardContent()
     * @generated
     */
    void setBoardContent( String value );

    /**
     * Returns the value of the '<em><b>Bonus Word</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bonus Word</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Bonus Word</em>' attribute.
     * @see #setBonusWord(String)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPublishedTicketDetail_BonusWord()
     * @model
     * @generated
     */
    String getBonusWord( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#getBonusWord
     * <em>Bonus Word</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Bonus Word</em>' attribute.
     * @see #getBonusWord()
     * @generated
     */
    void setBonusWord( String value );

    /**
     * Returns the value of the '<em><b>Twenty Spot Included</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Twenty Spot Included</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Twenty Spot Included</em>' attribute.
     * @see #setTwentySpotIncluded(boolean)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPublishedTicketDetail_TwentySpotIncluded()
     * @model
     * @generated
     */
    boolean isTwentySpotIncluded( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#isTwentySpotIncluded
     * <em>Twenty Spot Included</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Twenty Spot Included</em>' attribute.
     * @see #isTwentySpotIncluded()
     * @generated
     */
    void setTwentySpotIncluded( boolean value );

} // PublishedTicketDetail
