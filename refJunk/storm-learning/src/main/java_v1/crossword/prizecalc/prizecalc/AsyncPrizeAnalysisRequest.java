/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Async Prize Analysis Request</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getTicketHref <em>Ticket Href</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getRevealedInfo <em>Revealed Info</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getBoardCoverage <em>Board Coverage</em>}</li>
 * </ul>
 * </p>
 *
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getAsyncPrizeAnalysisRequest()
 * @model
 * @generated
 */
public interface AsyncPrizeAnalysisRequest extends EObject {
    /**
     * Returns the value of the '<em><b>Ticket Href</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ticket Href</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ticket Href</em>' attribute.
     * @see #setTicketHref(String)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getAsyncPrizeAnalysisRequest_TicketHref()
     * @model id="true"
     * @generated
     */
    String getTicketHref();

    /**
     * Sets the value of the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getTicketHref <em>Ticket Href</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ticket Href</em>' attribute.
     * @see #getTicketHref()
     * @generated
     */
    void setTicketHref(String value);

    /**
     * Returns the value of the '<em><b>Revealed Info</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Revealed Info</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Revealed Info</em>' reference.
     * @see #setRevealedInfo(RevealedInformation)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getAsyncPrizeAnalysisRequest_RevealedInfo()
     * @model
     * @generated
     */
    RevealedInformation getRevealedInfo();

    /**
     * Sets the value of the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getRevealedInfo <em>Revealed Info</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Revealed Info</em>' reference.
     * @see #getRevealedInfo()
     * @generated
     */
    void setRevealedInfo(RevealedInformation value);

    /**
     * Returns the value of the '<em><b>Board Coverage</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Board Coverage</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Board Coverage</em>' reference.
     * @see #setBoardCoverage(BoardCoverage)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getAsyncPrizeAnalysisRequest_BoardCoverage()
     * @model
     * @generated
     */
    BoardCoverage getBoardCoverage();

    /**
     * Sets the value of the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getBoardCoverage <em>Board Coverage</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Board Coverage</em>' reference.
     * @see #getBoardCoverage()
     * @generated
     */
    void setBoardCoverage(BoardCoverage value);

} // AsyncPrizeAnalysisRequest
