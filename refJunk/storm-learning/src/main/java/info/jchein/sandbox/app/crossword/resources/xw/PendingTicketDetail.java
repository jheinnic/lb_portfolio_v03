/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Pending Ticket Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBoardContent <em>Board
 * Content</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBonusWord <em>Bonus Word
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#isTwentySpotIncluded <em>
 * Twenty Spot Included</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getAllWords <em>All Words
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBasicWords <em>Basic
 * Words</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getTripleWords <em>Triple
 * Words</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#isPublishable <em>
 * Publishable</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPendingTicketDetail()
 * @model
 * @generated
 */
public interface PendingTicketDetail extends EObject {
    /**
     * Returns the value of the '<em><b>Board Content</b></em>' attribute. The default value is
     * <code>"_________________________________________________________________________________________________________________________"</code>
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Board Content</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Board Content</em>' attribute.
     * @see #setBoardContent(String)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPendingTicketDetail_BoardContent()
     * @model default=
     *        "_________________________________________________________________________________________________________________________"
     * @generated
     */
    String getBoardContent( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBoardContent
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
     * @see #isSetBonusWord()
     * @see #unsetBonusWord()
     * @see #setBonusWord(String)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPendingTicketDetail_BonusWord()
     * @model unsettable="true"
     * @generated
     */
    String getBonusWord( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBonusWord
     * <em>Bonus Word</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Bonus Word</em>' attribute.
     * @see #isSetBonusWord()
     * @see #unsetBonusWord()
     * @see #getBonusWord()
     * @generated
     */
    void setBonusWord( String value );

    /**
     * Unsets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBonusWord
     * <em>Bonus Word</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isSetBonusWord()
     * @see #getBonusWord()
     * @see #setBonusWord(String)
     * @generated
     */
    void unsetBonusWord( );

    /**
     * Returns whether the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBonusWord
     * <em>Bonus Word</em>}' attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return whether the value of the '<em>Bonus Word</em>' attribute is set.
     * @see #unsetBonusWord()
     * @see #getBonusWord()
     * @see #setBonusWord(String)
     * @generated
     */
    boolean isSetBonusWord( );

    /**
     * Returns the value of the '<em><b>Twenty Spot Included</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Twenty Spot Included</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Twenty Spot Included</em>' attribute.
     * @see #isSetTwentySpotIncluded()
     * @see #unsetTwentySpotIncluded()
     * @see #setTwentySpotIncluded(boolean)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPendingTicketDetail_TwentySpotIncluded()
     * @model unsettable="true"
     * @generated
     */
    boolean isTwentySpotIncluded( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#isTwentySpotIncluded
     * <em>Twenty Spot Included</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Twenty Spot Included</em>' attribute.
     * @see #isSetTwentySpotIncluded()
     * @see #unsetTwentySpotIncluded()
     * @see #isTwentySpotIncluded()
     * @generated
     */
    void setTwentySpotIncluded( boolean value );

    /**
     * Unsets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#isTwentySpotIncluded
     * <em>Twenty Spot Included</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isSetTwentySpotIncluded()
     * @see #isTwentySpotIncluded()
     * @see #setTwentySpotIncluded(boolean)
     * @generated
     */
    void unsetTwentySpotIncluded( );

    /**
     * Returns whether the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#isTwentySpotIncluded
     * <em>Twenty Spot Included</em>}' attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return whether the value of the '<em>Twenty Spot Included</em>' attribute is set.
     * @see #unsetTwentySpotIncluded()
     * @see #isTwentySpotIncluded()
     * @see #setTwentySpotIncluded(boolean)
     * @generated
     */
    boolean isSetTwentySpotIncluded( );

    /**
     * Returns the value of the '<em><b>All Words</b></em>' attribute list. The list contents are of type
     * {@link java.lang.String}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>All Words</em>' attribute list.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPendingTicketDetail_AllWords()
     * @model upper="22" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<String> getAllWords( );

    /**
     * Returns the value of the '<em><b>Basic Words</b></em>' attribute list. The list contents are of type
     * {@link java.lang.String}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Basic Words</em>' attribute list.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPendingTicketDetail_BasicWords()
     * @model upper="18" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<String> getBasicWords( );

    /**
     * Returns the value of the '<em><b>Triple Words</b></em>' attribute list. The list contents are of type
     * {@link java.lang.String}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Triple Words</em>' attribute list.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPendingTicketDetail_TripleWords()
     * @model upper="4" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<String> getTripleWords( );

    /**
     * Returns the value of the '<em><b>Publishable</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Publishable</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Publishable</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPendingTicketDetail_Publishable()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    boolean isPublishable( );

} // PendingTicketDetail
