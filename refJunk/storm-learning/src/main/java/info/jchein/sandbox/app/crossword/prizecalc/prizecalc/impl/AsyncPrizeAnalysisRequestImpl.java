/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;

import info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Async Prize Analysis Request</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisRequestImpl#getTicketHref
 * <em>Ticket Href</em>}</li>
 * <li>
 * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisRequestImpl#getRevealedInfo
 * <em>Revealed Info</em>}</li>
 * <li>
 * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisRequestImpl#getBoardCoverage
 * <em>Board Coverage</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AsyncPrizeAnalysisRequestImpl extends MinimalEObjectImpl.Container
    implements
        AsyncPrizeAnalysisRequest {
    /**
     * The default value of the '{@link #getTicketHref() <em>Ticket Href</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTicketHref()
     * @generated
     * @ordered
     */
    protected static final String TICKET_HREF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTicketHref() <em>Ticket Href</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTicketHref()
     * @generated
     * @ordered
     */
    protected String ticketHref = TICKET_HREF_EDEFAULT;

    /**
     * The cached value of the '{@link #getRevealedInfo() <em>Revealed Info</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRevealedInfo()
     * @generated
     * @ordered
     */
    protected RevealedInformation revealedInfo;

    /**
     * The cached value of the '{@link #getBoardCoverage() <em>Board Coverage</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBoardCoverage()
     * @generated
     * @ordered
     */
    protected BoardCoverage boardCoverage;


    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AsyncPrizeAnalysisRequestImpl( ) {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass( ) {
        return PrizecalcPackage.Literals.ASYNC_PRIZE_ANALYSIS_REQUEST;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTicketHref( ) {
        return ticketHref;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTicketHref( String newTicketHref ) {
        String oldTicketHref = ticketHref;
        ticketHref = newTicketHref;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__TICKET_HREF,
                oldTicketHref,
                ticketHref));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RevealedInformation getRevealedInfo( ) {
        if (revealedInfo != null && revealedInfo.eIsProxy()) {
            InternalEObject oldRevealedInfo = (InternalEObject) revealedInfo;
            revealedInfo = (RevealedInformation) eResolveProxy(oldRevealedInfo);
            if (revealedInfo != oldRevealedInfo) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(
                        this,
                        Notification.RESOLVE,
                        PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__REVEALED_INFO,
                        oldRevealedInfo,
                        revealedInfo));
            }
        }
        return revealedInfo;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RevealedInformation basicGetRevealedInfo( ) {
        return revealedInfo;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRevealedInfo( RevealedInformation newRevealedInfo ) {
        RevealedInformation oldRevealedInfo = revealedInfo;
        revealedInfo = newRevealedInfo;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__REVEALED_INFO,
                oldRevealedInfo,
                revealedInfo));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BoardCoverage getBoardCoverage( ) {
        if (boardCoverage != null && boardCoverage.eIsProxy()) {
            InternalEObject oldBoardCoverage = (InternalEObject) boardCoverage;
            boardCoverage = (BoardCoverage) eResolveProxy(oldBoardCoverage);
            if (boardCoverage != oldBoardCoverage) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(
                        this,
                        Notification.RESOLVE,
                        PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__BOARD_COVERAGE,
                        oldBoardCoverage,
                        boardCoverage));
            }
        }
        return boardCoverage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BoardCoverage basicGetBoardCoverage( ) {
        return boardCoverage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBoardCoverage( BoardCoverage newBoardCoverage ) {
        BoardCoverage oldBoardCoverage = boardCoverage;
        boardCoverage = newBoardCoverage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__BOARD_COVERAGE,
                oldBoardCoverage,
                boardCoverage));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType ) {
        switch (featureID) {
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__TICKET_HREF :
                return getTicketHref();
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__REVEALED_INFO :
                if (resolve)
                    return getRevealedInfo();
                return basicGetRevealedInfo();
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__BOARD_COVERAGE :
                if (resolve)
                    return getBoardCoverage();
                return basicGetBoardCoverage();
        }
        return super.eGet(
            featureID,
            resolve,
            coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet( int featureID, Object newValue ) {
        switch (featureID) {
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__TICKET_HREF :
                setTicketHref((String) newValue);
                return;
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__REVEALED_INFO :
                setRevealedInfo((RevealedInformation) newValue);
                return;
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__BOARD_COVERAGE :
                setBoardCoverage((BoardCoverage) newValue);
                return;
        }
        super.eSet(
            featureID,
            newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset( int featureID ) {
        switch (featureID) {
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__TICKET_HREF :
                setTicketHref(TICKET_HREF_EDEFAULT);
                return;
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__REVEALED_INFO :
                setRevealedInfo((RevealedInformation) null);
                return;
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__BOARD_COVERAGE :
                setBoardCoverage((BoardCoverage) null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet( int featureID ) {
        switch (featureID) {
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__TICKET_HREF :
                return TICKET_HREF_EDEFAULT == null
                    ? ticketHref != null
                    : !TICKET_HREF_EDEFAULT.equals(ticketHref);
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__REVEALED_INFO :
                return revealedInfo != null;
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST__BOARD_COVERAGE :
                return boardCoverage != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString( ) {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(
            super.toString());
        result.append(" (ticketHref: ");
        result.append(ticketHref);
        result.append(')');
        return result.toString();
    }

} // AsyncPrizeAnalysisRequestImpl
