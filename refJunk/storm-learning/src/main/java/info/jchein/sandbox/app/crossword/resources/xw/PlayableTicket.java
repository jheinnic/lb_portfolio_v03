/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Playable Ticket</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getFixedDetails <em>Fixed
 * Details</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getBoardContent <em>Board
 * Content</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getBonusWord <em>Bonus Word
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#isTwentySpotIncluded <em>Twenty
 * Spot Included</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getDynamicInput <em>Dynamic
 * Input</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getLatestAnalysis <em>Latest
 * Analysis</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getBoardCoverage <em>Board
 * Coverage</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#isResolved <em>Resolved</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPlayableTicket()
 * @model
 * @generated
 */
public interface PlayableTicket extends EObject {
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
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPlayableTicket_FixedDetails()
     * @model containment="true" required="true"
     * @generated
     */
    PublishedTicketDetail getFixedDetails( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getFixedDetails
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
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPlayableTicket_BoardContent()
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
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPlayableTicket_BonusWord()
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
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPlayableTicket_TwentySpotIncluded()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    boolean isTwentySpotIncluded( );

    /**
     * Returns the value of the '<em><b>Dynamic Input</b></em>' containment reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Dynamic Input</em>' containment reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Dynamic Input</em>' containment reference.
     * @see #setDynamicInput(RevealedInformation)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPlayableTicket_DynamicInput()
     * @model containment="true" required="true"
     * @generated
     */
    RevealedInformation getDynamicInput( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getDynamicInput
     * <em>Dynamic Input</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Dynamic Input</em>' containment reference.
     * @see #getDynamicInput()
     * @generated
     */
    void setDynamicInput( RevealedInformation value );

    /**
     * Returns the value of the '<em><b>Latest Analysis</b></em>' containment reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Latest Analysis</em>' containment reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Latest Analysis</em>' containment reference.
     * @see #setLatestAnalysis(PrizeAnalysis)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPlayableTicket_LatestAnalysis()
     * @model containment="true"
     * @generated
     */
    PrizeAnalysis getLatestAnalysis( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getLatestAnalysis
     * <em>Latest Analysis</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Latest Analysis</em>' containment reference.
     * @see #getLatestAnalysis()
     * @generated
     */
    void setLatestAnalysis( PrizeAnalysis value );

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
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPlayableTicket_BoardCoverage()
     * @model containment="true" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    BoardCoverage getBoardCoverage( );

    /**
     * Returns the value of the '<em><b>Resolved</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resolved</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Resolved</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPlayableTicket_Resolved()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    boolean isResolved( );

} // PlayableTicket
