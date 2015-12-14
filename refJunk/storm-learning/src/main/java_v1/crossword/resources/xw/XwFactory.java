/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage
 * @generated
 */
public interface XwFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    XwFactory eINSTANCE = info.jchein.sandbox.app.crossword.resources.xw.impl.XwFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Pending Ticket Detail</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Pending Ticket Detail</em>'.
     * @generated
     */
    PendingTicketDetail createPendingTicketDetail();

    /**
     * Returns a new object of class '<em>Published Ticket Detail</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Published Ticket Detail</em>'.
     * @generated
     */
    PublishedTicketDetail createPublishedTicketDetail();

    /**
     * Returns a new object of class '<em>Board Coverage</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Board Coverage</em>'.
     * @generated
     */
    BoardCoverage createBoardCoverage();

    /**
     * Returns a new object of class '<em>Bonus Word Coverage</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Bonus Word Coverage</em>'.
     * @generated
     */
    BonusWordCoverage createBonusWordCoverage();

    /**
     * Returns a new object of class '<em>Grid Word Coverage</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Grid Word Coverage</em>'.
     * @generated
     */
    GridWordCoverage createGridWordCoverage();

    /**
     * Returns a new object of class '<em>Revealed Information</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Revealed Information</em>'.
     * @generated
     */
    RevealedInformation createRevealedInformation();

    /**
     * Returns a new object of class '<em>Prize Analysis</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Prize Analysis</em>'.
     * @generated
     */
    PrizeAnalysis createPrizeAnalysis();

    /**
     * Returns a new object of class '<em>Playable Ticket</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Playable Ticket</em>'.
     * @generated
     */
    PlayableTicket createPlayableTicket();

    /**
     * Returns a new object of class '<em>Resolved Ticket</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Resolved Ticket</em>'.
     * @generated
     */
    ResolvedTicket createResolvedTicket();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    XwPackage getXwPackage();

} //XwFactory
