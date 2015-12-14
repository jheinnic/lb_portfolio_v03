/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.util;

import info.jchein.sandbox.app.crossword.resources.xw.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of
 * the model, starting with the actual class of the object and proceeding up the inheritance hierarchy until
 * a non-null result is returned, which is the result of the switch. <!-- end-user-doc -->
 * 
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage
 * @generated
 */
public class XwSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static XwPackage modelPackage;


    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public XwSwitch( ) {
        if (modelPackage == null) {
            modelPackage = XwPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor( EPackage ePackage ) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields
     * that result. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch( int classifierID, EObject theEObject ) {
        switch (classifierID) {
            case XwPackage.PENDING_TICKET_DETAIL : {
                PendingTicketDetail pendingTicketDetail = (PendingTicketDetail) theEObject;
                T result = casePendingTicketDetail(pendingTicketDetail);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case XwPackage.PUBLISHED_TICKET_DETAIL : {
                PublishedTicketDetail publishedTicketDetail = (PublishedTicketDetail) theEObject;
                T result = casePublishedTicketDetail(publishedTicketDetail);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case XwPackage.BOARD_COVERAGE : {
                BoardCoverage boardCoverage = (BoardCoverage) theEObject;
                T result = caseBoardCoverage(boardCoverage);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case XwPackage.ABSTRACT_WORD_COVERAGE : {
                AbstractWordCoverage abstractWordCoverage = (AbstractWordCoverage) theEObject;
                T result = caseAbstractWordCoverage(abstractWordCoverage);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case XwPackage.BONUS_WORD_COVERAGE : {
                BonusWordCoverage bonusWordCoverage = (BonusWordCoverage) theEObject;
                T result = caseBonusWordCoverage(bonusWordCoverage);
                if (result == null)
                    result = caseAbstractWordCoverage(bonusWordCoverage);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case XwPackage.GRID_WORD_COVERAGE : {
                GridWordCoverage gridWordCoverage = (GridWordCoverage) theEObject;
                T result = caseGridWordCoverage(gridWordCoverage);
                if (result == null)
                    result = caseAbstractWordCoverage(gridWordCoverage);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case XwPackage.REVEALED_INFORMATION : {
                RevealedInformation revealedInformation = (RevealedInformation) theEObject;
                T result = caseRevealedInformation(revealedInformation);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case XwPackage.PRIZE_ANALYSIS : {
                PrizeAnalysis prizeAnalysis = (PrizeAnalysis) theEObject;
                T result = casePrizeAnalysis(prizeAnalysis);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case XwPackage.PLAYABLE_TICKET : {
                PlayableTicket playableTicket = (PlayableTicket) theEObject;
                T result = casePlayableTicket(playableTicket);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case XwPackage.RESOLVED_TICKET : {
                ResolvedTicket resolvedTicket = (ResolvedTicket) theEObject;
                T result = caseResolvedTicket(resolvedTicket);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            default :
                return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Pending Ticket Detail</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pending Ticket Detail</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePendingTicketDetail( PendingTicketDetail object ) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Published Ticket Detail</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Published Ticket Detail</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePublishedTicketDetail( PublishedTicketDetail object ) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Board Coverage</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
     * switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Board Coverage</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBoardCoverage( BoardCoverage object ) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Word Coverage</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Word Coverage</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractWordCoverage( AbstractWordCoverage object ) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Bonus Word Coverage</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
     * switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Bonus Word Coverage</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBonusWordCoverage( BonusWordCoverage object ) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Grid Word Coverage</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
     * switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Grid Word Coverage</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGridWordCoverage( GridWordCoverage object ) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Revealed Information</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
     * switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Revealed Information</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRevealedInformation( RevealedInformation object ) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Prize Analysis</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
     * switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Prize Analysis</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePrizeAnalysis( PrizeAnalysis object ) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Playable Ticket</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
     * switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Playable Ticket</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePlayableTicket( PlayableTicket object ) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Resolved Ticket</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
     * switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Resolved Ticket</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResolvedTicket( ResolvedTicket object ) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
     * switch, but this is the last case anyway. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase( EObject object ) {
        return null;
    }

} // XwSwitch
