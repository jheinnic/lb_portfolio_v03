/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class XwFactoryImpl extends EFactoryImpl implements XwFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static XwFactory init( ) {
        try {
            XwFactory theXwFactory = (XwFactory) EPackage.Registry.INSTANCE.getEFactory(XwPackage.eNS_URI);
            if (theXwFactory != null) {
                return theXwFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new XwFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public XwFactoryImpl( ) {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create( EClass eClass ) {
        switch (eClass.getClassifierID()) {
            case XwPackage.PENDING_TICKET_DETAIL :
                return createPendingTicketDetail();
            case XwPackage.PUBLISHED_TICKET_DETAIL :
                return createPublishedTicketDetail();
            case XwPackage.BOARD_COVERAGE :
                return createBoardCoverage();
            case XwPackage.BONUS_WORD_COVERAGE :
                return createBonusWordCoverage();
            case XwPackage.GRID_WORD_COVERAGE :
                return createGridWordCoverage();
            case XwPackage.REVEALED_INFORMATION :
                return createRevealedInformation();
            case XwPackage.PRIZE_ANALYSIS :
                return createPrizeAnalysis();
            case XwPackage.PLAYABLE_TICKET :
                return createPlayableTicket();
            case XwPackage.RESOLVED_TICKET :
                return createResolvedTicket();
            default :
                throw new IllegalArgumentException(
                    "The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object createFromString( EDataType eDataType, String initialValue ) {
        switch (eDataType.getClassifierID()) {
            case XwPackage.BONUS_VALUE :
                return createBonusValueFromString(
                    eDataType,
                    initialValue);
            default :
                throw new IllegalArgumentException(
                    "The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String convertToString( EDataType eDataType, Object instanceValue ) {
        switch (eDataType.getClassifierID()) {
            case XwPackage.BONUS_VALUE :
                return convertBonusValueToString(
                    eDataType,
                    instanceValue);
            default :
                throw new IllegalArgumentException(
                    "The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PendingTicketDetail createPendingTicketDetail( ) {
        PendingTicketDetailImpl pendingTicketDetail = new PendingTicketDetailImpl();
        return pendingTicketDetail;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PublishedTicketDetail createPublishedTicketDetail( ) {
        PublishedTicketDetailImpl publishedTicketDetail = new PublishedTicketDetailImpl();
        return publishedTicketDetail;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BoardCoverage createBoardCoverage( ) {
        BoardCoverageImpl boardCoverage = new BoardCoverageImpl();
        return boardCoverage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BonusWordCoverage createBonusWordCoverage( ) {
        BonusWordCoverageImpl bonusWordCoverage = new BonusWordCoverageImpl();
        return bonusWordCoverage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public GridWordCoverage createGridWordCoverage( ) {
        GridWordCoverageImpl gridWordCoverage = new GridWordCoverageImpl();
        return gridWordCoverage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RevealedInformation createRevealedInformation( ) {
        RevealedInformationImpl revealedInformation = new RevealedInformationImpl();
        return revealedInformation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrizeAnalysis createPrizeAnalysis( ) {
        PrizeAnalysisImpl prizeAnalysis = new PrizeAnalysisImpl();
        return prizeAnalysis;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PlayableTicket createPlayableTicket( ) {
        PlayableTicketImpl playableTicket = new PlayableTicketImpl();
        return playableTicket;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ResolvedTicket createResolvedTicket( ) {
        ResolvedTicketImpl resolvedTicket = new ResolvedTicketImpl();
        return resolvedTicket;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BonusValue createBonusValueFromString( EDataType eDataType, String initialValue ) {
        BonusValue result = BonusValue.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException(
                "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertBonusValueToString( EDataType eDataType, Object instanceValue ) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public XwPackage getXwPackage( ) {
        return (XwPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static XwPackage getPackage( ) {
        return XwPackage.eINSTANCE;
    }

} // XwFactoryImpl
