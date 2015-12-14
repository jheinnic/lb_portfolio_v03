/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.BonusValue;
import info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail;
import info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket;
import info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis;
import info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail;
import info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket;
import info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation;
import info.jchein.sandbox.app.crossword.resources.xw.XwFactory;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class XwPackageImpl extends EPackageImpl implements XwPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass pendingTicketDetailEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass publishedTicketDetailEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass boardCoverageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractWordCoverageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass bonusWordCoverageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass gridWordCoverageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass revealedInformationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass prizeAnalysisEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass playableTicketEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass resolvedTicketEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum bonusValueEEnum = null;


    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()},
     * which also performs initialization of the package, or returns the registered package, if one already
     * exists. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private XwPackageImpl( ) {
        super(
            eNS_URI,
            XwFactory.eINSTANCE);
    }


    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;


    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which
     * it depends.
     * <p>
     * This method is used to initialize {@link XwPackage#eINSTANCE} when that field is accessed. Clients
     * should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static XwPackage init( ) {
        if (isInited)
            return (XwPackage) EPackage.Registry.INSTANCE.getEPackage(XwPackage.eNS_URI);

        // Obtain or create and register package
        XwPackageImpl theXwPackage =
            (XwPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof XwPackageImpl
                ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new XwPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theXwPackage.createPackageContents();

        // Initialize created meta-data
        theXwPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theXwPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(
            XwPackage.eNS_URI,
            theXwPackage);
        return theXwPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPendingTicketDetail( ) {
        return pendingTicketDetailEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPendingTicketDetail_BoardContent( ) {
        return (EAttribute) pendingTicketDetailEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPendingTicketDetail_BonusWord( ) {
        return (EAttribute) pendingTicketDetailEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPendingTicketDetail_TwentySpotIncluded( ) {
        return (EAttribute) pendingTicketDetailEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPendingTicketDetail_AllWords( ) {
        return (EAttribute) pendingTicketDetailEClass.getEStructuralFeatures().get(
            3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPendingTicketDetail_BasicWords( ) {
        return (EAttribute) pendingTicketDetailEClass.getEStructuralFeatures().get(
            4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPendingTicketDetail_TripleWords( ) {
        return (EAttribute) pendingTicketDetailEClass.getEStructuralFeatures().get(
            5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPendingTicketDetail_Publishable( ) {
        return (EAttribute) pendingTicketDetailEClass.getEStructuralFeatures().get(
            6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPublishedTicketDetail( ) {
        return publishedTicketDetailEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPublishedTicketDetail_BoardContent( ) {
        return (EAttribute) publishedTicketDetailEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPublishedTicketDetail_BonusWord( ) {
        return (EAttribute) publishedTicketDetailEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPublishedTicketDetail_TwentySpotIncluded( ) {
        return (EAttribute) publishedTicketDetailEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBoardCoverage( ) {
        return boardCoverageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBoardCoverage_BonusWord( ) {
        return (EReference) boardCoverageEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBoardCoverage_AllWords( ) {
        return (EReference) boardCoverageEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBoardCoverage_BasicWords( ) {
        return (EReference) boardCoverageEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBoardCoverage_TripleWords( ) {
        return (EReference) boardCoverageEClass.getEStructuralFeatures().get(
            3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAbstractWordCoverage( ) {
        return abstractWordCoverageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractWordCoverage_RawWord( ) {
        return (EAttribute) abstractWordCoverageEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractWordCoverage_DisplayWord( ) {
        return (EAttribute) abstractWordCoverageEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractWordCoverage_UncoveredLetters( ) {
        return (EAttribute) abstractWordCoverageEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBonusWordCoverage( ) {
        return bonusWordCoverageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBonusWordCoverage_BonusPaid( ) {
        return (EAttribute) bonusWordCoverageEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getGridWordCoverage( ) {
        return gridWordCoverageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGridWordCoverage_WordCovered( ) {
        return (EAttribute) gridWordCoverageEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGridWordCoverage_Tripling( ) {
        return (EAttribute) gridWordCoverageEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRevealedInformation( ) {
        return revealedInformationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRevealedInformation_LetterPool( ) {
        return (EAttribute) revealedInformationEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRevealedInformation_TwentySpotPaid( ) {
        return (EAttribute) revealedInformationEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRevealedInformation_BonusValue( ) {
        return (EAttribute) revealedInformationEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPrizeAnalysis( ) {
        return prizeAnalysisEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPrizeAnalysis_PrizeDistribution( ) {
        return (EAttribute) prizeAnalysisEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPrizeAnalysis_DynamicInput( ) {
        return (EReference) prizeAnalysisEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPlayableTicket( ) {
        return playableTicketEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPlayableTicket_FixedDetails( ) {
        return (EReference) playableTicketEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPlayableTicket_BoardContent( ) {
        return (EAttribute) playableTicketEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPlayableTicket_BonusWord( ) {
        return (EAttribute) playableTicketEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPlayableTicket_TwentySpotIncluded( ) {
        return (EAttribute) playableTicketEClass.getEStructuralFeatures().get(
            3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPlayableTicket_DynamicInput( ) {
        return (EReference) playableTicketEClass.getEStructuralFeatures().get(
            4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPlayableTicket_LatestAnalysis( ) {
        return (EReference) playableTicketEClass.getEStructuralFeatures().get(
            5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPlayableTicket_BoardCoverage( ) {
        return (EReference) playableTicketEClass.getEStructuralFeatures().get(
            6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPlayableTicket_Resolved( ) {
        return (EAttribute) playableTicketEClass.getEStructuralFeatures().get(
            7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getResolvedTicket( ) {
        return resolvedTicketEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getResolvedTicket_FixedDetails( ) {
        return (EReference) resolvedTicketEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getResolvedTicket_BoardContent( ) {
        return (EAttribute) resolvedTicketEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getResolvedTicket_BonusWord( ) {
        return (EAttribute) resolvedTicketEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getResolvedTicket_TwentySpotIncluded( ) {
        return (EAttribute) resolvedTicketEClass.getEStructuralFeatures().get(
            3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getResolvedTicket_LetterPool( ) {
        return (EAttribute) resolvedTicketEClass.getEStructuralFeatures().get(
            4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getResolvedTicket_TwentySpotPaid( ) {
        return (EAttribute) resolvedTicketEClass.getEStructuralFeatures().get(
            5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getResolvedTicket_BonusValue( ) {
        return (EAttribute) resolvedTicketEClass.getEStructuralFeatures().get(
            6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getResolvedTicket_PayoutTier( ) {
        return (EAttribute) resolvedTicketEClass.getEStructuralFeatures().get(
            7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getResolvedTicket_BoardCoverage( ) {
        return (EReference) resolvedTicketEClass.getEStructuralFeatures().get(
            8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getBonusValue( ) {
        return bonusValueEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public XwFactory getXwFactory( ) {
        return (XwFactory) getEFactoryInstance();
    }


    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;


    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any
     * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents( ) {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        pendingTicketDetailEClass = createEClass(PENDING_TICKET_DETAIL);
        createEAttribute(
            pendingTicketDetailEClass,
            PENDING_TICKET_DETAIL__BOARD_CONTENT);
        createEAttribute(
            pendingTicketDetailEClass,
            PENDING_TICKET_DETAIL__BONUS_WORD);
        createEAttribute(
            pendingTicketDetailEClass,
            PENDING_TICKET_DETAIL__TWENTY_SPOT_INCLUDED);
        createEAttribute(
            pendingTicketDetailEClass,
            PENDING_TICKET_DETAIL__ALL_WORDS);
        createEAttribute(
            pendingTicketDetailEClass,
            PENDING_TICKET_DETAIL__BASIC_WORDS);
        createEAttribute(
            pendingTicketDetailEClass,
            PENDING_TICKET_DETAIL__TRIPLE_WORDS);
        createEAttribute(
            pendingTicketDetailEClass,
            PENDING_TICKET_DETAIL__PUBLISHABLE);

        publishedTicketDetailEClass = createEClass(PUBLISHED_TICKET_DETAIL);
        createEAttribute(
            publishedTicketDetailEClass,
            PUBLISHED_TICKET_DETAIL__BOARD_CONTENT);
        createEAttribute(
            publishedTicketDetailEClass,
            PUBLISHED_TICKET_DETAIL__BONUS_WORD);
        createEAttribute(
            publishedTicketDetailEClass,
            PUBLISHED_TICKET_DETAIL__TWENTY_SPOT_INCLUDED);

        boardCoverageEClass = createEClass(BOARD_COVERAGE);
        createEReference(
            boardCoverageEClass,
            BOARD_COVERAGE__BONUS_WORD);
        createEReference(
            boardCoverageEClass,
            BOARD_COVERAGE__ALL_WORDS);
        createEReference(
            boardCoverageEClass,
            BOARD_COVERAGE__BASIC_WORDS);
        createEReference(
            boardCoverageEClass,
            BOARD_COVERAGE__TRIPLE_WORDS);

        abstractWordCoverageEClass = createEClass(ABSTRACT_WORD_COVERAGE);
        createEAttribute(
            abstractWordCoverageEClass,
            ABSTRACT_WORD_COVERAGE__RAW_WORD);
        createEAttribute(
            abstractWordCoverageEClass,
            ABSTRACT_WORD_COVERAGE__DISPLAY_WORD);
        createEAttribute(
            abstractWordCoverageEClass,
            ABSTRACT_WORD_COVERAGE__UNCOVERED_LETTERS);

        bonusWordCoverageEClass = createEClass(BONUS_WORD_COVERAGE);
        createEAttribute(
            bonusWordCoverageEClass,
            BONUS_WORD_COVERAGE__BONUS_PAID);

        gridWordCoverageEClass = createEClass(GRID_WORD_COVERAGE);
        createEAttribute(
            gridWordCoverageEClass,
            GRID_WORD_COVERAGE__WORD_COVERED);
        createEAttribute(
            gridWordCoverageEClass,
            GRID_WORD_COVERAGE__TRIPLING);

        revealedInformationEClass = createEClass(REVEALED_INFORMATION);
        createEAttribute(
            revealedInformationEClass,
            REVEALED_INFORMATION__LETTER_POOL);
        createEAttribute(
            revealedInformationEClass,
            REVEALED_INFORMATION__TWENTY_SPOT_PAID);
        createEAttribute(
            revealedInformationEClass,
            REVEALED_INFORMATION__BONUS_VALUE);

        prizeAnalysisEClass = createEClass(PRIZE_ANALYSIS);
        createEAttribute(
            prizeAnalysisEClass,
            PRIZE_ANALYSIS__PRIZE_DISTRIBUTION);
        createEReference(
            prizeAnalysisEClass,
            PRIZE_ANALYSIS__DYNAMIC_INPUT);

        playableTicketEClass = createEClass(PLAYABLE_TICKET);
        createEReference(
            playableTicketEClass,
            PLAYABLE_TICKET__FIXED_DETAILS);
        createEAttribute(
            playableTicketEClass,
            PLAYABLE_TICKET__BOARD_CONTENT);
        createEAttribute(
            playableTicketEClass,
            PLAYABLE_TICKET__BONUS_WORD);
        createEAttribute(
            playableTicketEClass,
            PLAYABLE_TICKET__TWENTY_SPOT_INCLUDED);
        createEReference(
            playableTicketEClass,
            PLAYABLE_TICKET__DYNAMIC_INPUT);
        createEReference(
            playableTicketEClass,
            PLAYABLE_TICKET__LATEST_ANALYSIS);
        createEReference(
            playableTicketEClass,
            PLAYABLE_TICKET__BOARD_COVERAGE);
        createEAttribute(
            playableTicketEClass,
            PLAYABLE_TICKET__RESOLVED);

        resolvedTicketEClass = createEClass(RESOLVED_TICKET);
        createEReference(
            resolvedTicketEClass,
            RESOLVED_TICKET__FIXED_DETAILS);
        createEAttribute(
            resolvedTicketEClass,
            RESOLVED_TICKET__BOARD_CONTENT);
        createEAttribute(
            resolvedTicketEClass,
            RESOLVED_TICKET__BONUS_WORD);
        createEAttribute(
            resolvedTicketEClass,
            RESOLVED_TICKET__TWENTY_SPOT_INCLUDED);
        createEAttribute(
            resolvedTicketEClass,
            RESOLVED_TICKET__LETTER_POOL);
        createEAttribute(
            resolvedTicketEClass,
            RESOLVED_TICKET__TWENTY_SPOT_PAID);
        createEAttribute(
            resolvedTicketEClass,
            RESOLVED_TICKET__BONUS_VALUE);
        createEAttribute(
            resolvedTicketEClass,
            RESOLVED_TICKET__PAYOUT_TIER);
        createEReference(
            resolvedTicketEClass,
            RESOLVED_TICKET__BOARD_COVERAGE);

        // Create enums
        bonusValueEEnum = createEEnum(BONUS_VALUE);
    }


    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;


    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have no
     * affect on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents( ) {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        bonusWordCoverageEClass.getESuperTypes().add(
            this.getAbstractWordCoverage());
        gridWordCoverageEClass.getESuperTypes().add(
            this.getAbstractWordCoverage());

        // Initialize classes and features; add operations and parameters
        initEClass(
            pendingTicketDetailEClass,
            PendingTicketDetail.class,
            "PendingTicketDetail",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getPendingTicketDetail_BoardContent(),
            ecorePackage.getEString(),
            "boardContent",
            "_________________________________________________________________________________________________________________________",
            0,
            1,
            PendingTicketDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPendingTicketDetail_BonusWord(),
            ecorePackage.getEString(),
            "bonusWord",
            null,
            0,
            1,
            PendingTicketDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPendingTicketDetail_TwentySpotIncluded(),
            ecorePackage.getEBoolean(),
            "twentySpotIncluded",
            null,
            0,
            1,
            PendingTicketDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPendingTicketDetail_AllWords(),
            ecorePackage.getEString(),
            "allWords",
            null,
            0,
            22,
            PendingTicketDetail.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPendingTicketDetail_BasicWords(),
            ecorePackage.getEString(),
            "basicWords",
            null,
            0,
            18,
            PendingTicketDetail.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPendingTicketDetail_TripleWords(),
            ecorePackage.getEString(),
            "tripleWords",
            null,
            0,
            4,
            PendingTicketDetail.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPendingTicketDetail_Publishable(),
            ecorePackage.getEBoolean(),
            "publishable",
            null,
            0,
            1,
            PendingTicketDetail.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);

        initEClass(
            publishedTicketDetailEClass,
            PublishedTicketDetail.class,
            "PublishedTicketDetail",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getPublishedTicketDetail_BoardContent(),
            ecorePackage.getEString(),
            "boardContent",
            null,
            0,
            1,
            PublishedTicketDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPublishedTicketDetail_BonusWord(),
            ecorePackage.getEString(),
            "bonusWord",
            null,
            0,
            1,
            PublishedTicketDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPublishedTicketDetail_TwentySpotIncluded(),
            ecorePackage.getEBoolean(),
            "twentySpotIncluded",
            null,
            0,
            1,
            PublishedTicketDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(
            boardCoverageEClass,
            BoardCoverage.class,
            "BoardCoverage",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEReference(
            getBoardCoverage_BonusWord(),
            this.getBonusWordCoverage(),
            null,
            "bonusWord",
            null,
            1,
            1,
            BoardCoverage.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getBoardCoverage_AllWords(),
            this.getGridWordCoverage(),
            null,
            "allWords",
            null,
            22,
            22,
            BoardCoverage.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getBoardCoverage_BasicWords(),
            this.getGridWordCoverage(),
            null,
            "basicWords",
            null,
            18,
            18,
            BoardCoverage.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getBoardCoverage_TripleWords(),
            this.getGridWordCoverage(),
            null,
            "tripleWords",
            null,
            4,
            4,
            BoardCoverage.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);

        initEClass(
            abstractWordCoverageEClass,
            AbstractWordCoverage.class,
            "AbstractWordCoverage",
            IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getAbstractWordCoverage_RawWord(),
            ecorePackage.getEString(),
            "rawWord",
            null,
            0,
            1,
            AbstractWordCoverage.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getAbstractWordCoverage_DisplayWord(),
            ecorePackage.getEString(),
            "displayWord",
            null,
            0,
            1,
            AbstractWordCoverage.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getAbstractWordCoverage_UncoveredLetters(),
            ecorePackage.getEString(),
            "uncoveredLetters",
            null,
            0,
            1,
            AbstractWordCoverage.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);

        initEClass(
            bonusWordCoverageEClass,
            BonusWordCoverage.class,
            "BonusWordCoverage",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getBonusWordCoverage_BonusPaid(),
            ecorePackage.getEBoolean(),
            "bonusPaid",
            null,
            0,
            1,
            BonusWordCoverage.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);

        initEClass(
            gridWordCoverageEClass,
            GridWordCoverage.class,
            "GridWordCoverage",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getGridWordCoverage_WordCovered(),
            ecorePackage.getEBoolean(),
            "wordCovered",
            null,
            0,
            1,
            GridWordCoverage.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getGridWordCoverage_Tripling(),
            ecorePackage.getEBoolean(),
            "tripling",
            null,
            0,
            1,
            GridWordCoverage.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);

        initEClass(
            revealedInformationEClass,
            RevealedInformation.class,
            "RevealedInformation",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getRevealedInformation_LetterPool(),
            ecorePackage.getEString(),
            "letterPool",
            "__________________",
            0,
            1,
            RevealedInformation.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getRevealedInformation_TwentySpotPaid(),
            ecorePackage.getEBoolean(),
            "twentySpotPaid",
            null,
            0,
            1,
            RevealedInformation.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getRevealedInformation_BonusValue(),
            this.getBonusValue(),
            "bonusValue",
            null,
            0,
            1,
            RevealedInformation.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(
            prizeAnalysisEClass,
            PrizeAnalysis.class,
            "PrizeAnalysis",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getPrizeAnalysis_PrizeDistribution(),
            ecorePackage.getEInt(),
            "prizeDistribution",
            null,
            18,
            18,
            PrizeAnalysis.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getPrizeAnalysis_DynamicInput(),
            this.getRevealedInformation(),
            null,
            "dynamicInput",
            null,
            0,
            1,
            PrizeAnalysis.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(
            playableTicketEClass,
            PlayableTicket.class,
            "PlayableTicket",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEReference(
            getPlayableTicket_FixedDetails(),
            this.getPublishedTicketDetail(),
            null,
            "fixedDetails",
            null,
            1,
            1,
            PlayableTicket.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPlayableTicket_BoardContent(),
            ecorePackage.getEString(),
            "boardContent",
            null,
            0,
            1,
            PlayableTicket.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPlayableTicket_BonusWord(),
            ecorePackage.getEString(),
            "bonusWord",
            null,
            0,
            1,
            PlayableTicket.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPlayableTicket_TwentySpotIncluded(),
            ecorePackage.getEBoolean(),
            "twentySpotIncluded",
            null,
            0,
            1,
            PlayableTicket.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getPlayableTicket_DynamicInput(),
            this.getRevealedInformation(),
            null,
            "dynamicInput",
            null,
            1,
            1,
            PlayableTicket.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getPlayableTicket_LatestAnalysis(),
            this.getPrizeAnalysis(),
            null,
            "latestAnalysis",
            null,
            0,
            1,
            PlayableTicket.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getPlayableTicket_BoardCoverage(),
            this.getBoardCoverage(),
            null,
            "boardCoverage",
            null,
            0,
            1,
            PlayableTicket.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getPlayableTicket_Resolved(),
            ecorePackage.getEBoolean(),
            "resolved",
            null,
            0,
            1,
            PlayableTicket.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);

        initEClass(
            resolvedTicketEClass,
            ResolvedTicket.class,
            "ResolvedTicket",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEReference(
            getResolvedTicket_FixedDetails(),
            this.getPublishedTicketDetail(),
            null,
            "fixedDetails",
            null,
            1,
            1,
            ResolvedTicket.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getResolvedTicket_BoardContent(),
            ecorePackage.getEString(),
            "boardContent",
            null,
            0,
            1,
            ResolvedTicket.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getResolvedTicket_BonusWord(),
            ecorePackage.getEString(),
            "bonusWord",
            null,
            0,
            1,
            ResolvedTicket.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getResolvedTicket_TwentySpotIncluded(),
            ecorePackage.getEBoolean(),
            "twentySpotIncluded",
            null,
            0,
            1,
            ResolvedTicket.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getResolvedTicket_LetterPool(),
            ecorePackage.getEString(),
            "letterPool",
            null,
            0,
            1,
            ResolvedTicket.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getResolvedTicket_TwentySpotPaid(),
            ecorePackage.getEBoolean(),
            "twentySpotPaid",
            null,
            0,
            1,
            ResolvedTicket.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getResolvedTicket_BonusValue(),
            this.getBonusValue(),
            "bonusValue",
            null,
            1,
            1,
            ResolvedTicket.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getResolvedTicket_PayoutTier(),
            ecorePackage.getEInt(),
            "payoutTier",
            null,
            0,
            1,
            ResolvedTicket.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getResolvedTicket_BoardCoverage(),
            this.getBoardCoverage(),
            null,
            "boardCoverage",
            null,
            0,
            1,
            ResolvedTicket.class,
            IS_TRANSIENT,
            IS_VOLATILE,
            !IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            IS_DERIVED,
            IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(
            bonusValueEEnum,
            BonusValue.class,
            "BonusValue");
        addEEnumLiteral(
            bonusValueEEnum,
            BonusValue.FOUR);
        addEEnumLiteral(
            bonusValueEEnum,
            BonusValue.FIVE);
        addEEnumLiteral(
            bonusValueEEnum,
            BonusValue.TEN);
        addEEnumLiteral(
            bonusValueEEnum,
            BonusValue.THIRTY);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http://www.eclipse.org/emf/2002/GenModel
        createGenModelAnnotations();
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createGenModelAnnotations( ) {
        String source = "http://www.eclipse.org/emf/2002/GenModel";
        addAnnotation(
            this,
            source,
            new String[] {"basePackage", "info.jchein.sandbox.app.crossword.resources"});
    }

} // XwPackageImpl
