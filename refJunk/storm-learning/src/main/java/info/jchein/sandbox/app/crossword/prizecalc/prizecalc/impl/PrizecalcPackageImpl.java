/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState;

import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;

import info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl;

import java.util.BitSet;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class PrizecalcPackageImpl extends EPackageImpl implements PrizecalcPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass asyncPrizeAnalysisRequestEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass asyncPrizeAnalysisResponseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass reportPrizeAnalysisRequestEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass reportPrizeAnalysisResponseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass availableLetterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass ticketStateEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass beginCalculationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass subtaskRequestEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass subtaskResponseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass taskResultEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType bitSetEDataType = null;


    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()},
     * which also performs initialization of the package, or returns the registered package, if one already
     * exists. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private PrizecalcPackageImpl( ) {
        super(
            eNS_URI,
            PrizecalcFactory.eINSTANCE);
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
     * This method is used to initialize {@link PrizecalcPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the
     * package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static PrizecalcPackage init( ) {
        if (isInited)
            return (PrizecalcPackage) EPackage.Registry.INSTANCE.getEPackage(PrizecalcPackage.eNS_URI);

        // Obtain or create and register package
        PrizecalcPackageImpl thePrizecalcPackage =
            (PrizecalcPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PrizecalcPackageImpl
                ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new PrizecalcPackageImpl());

        isInited = true;

        // Obtain or create and register interdependencies
        XwPackageImpl theXwPackage =
            (XwPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(XwPackage.eNS_URI) instanceof XwPackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(XwPackage.eNS_URI)
                : XwPackage.eINSTANCE);

        // Create package meta-data objects
        thePrizecalcPackage.createPackageContents();
        theXwPackage.createPackageContents();

        // Initialize created meta-data
        thePrizecalcPackage.initializePackageContents();
        theXwPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thePrizecalcPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(
            PrizecalcPackage.eNS_URI,
            thePrizecalcPackage);
        return thePrizecalcPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAsyncPrizeAnalysisRequest( ) {
        return asyncPrizeAnalysisRequestEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAsyncPrizeAnalysisRequest_TicketHref( ) {
        return (EAttribute) asyncPrizeAnalysisRequestEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAsyncPrizeAnalysisRequest_RevealedInfo( ) {
        return (EReference) asyncPrizeAnalysisRequestEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAsyncPrizeAnalysisRequest_BoardCoverage( ) {
        return (EReference) asyncPrizeAnalysisRequestEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAsyncPrizeAnalysisResponse( ) {
        return asyncPrizeAnalysisResponseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getReportPrizeAnalysisRequest( ) {
        return reportPrizeAnalysisRequestEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getReportPrizeAnalysisRequest_PrizeAnalysis( ) {
        return (EReference) reportPrizeAnalysisRequestEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getReportPrizeAnalysisResponse( ) {
        return reportPrizeAnalysisResponseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAvailableLetter( ) {
        return availableLetterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAvailableLetter_Letter( ) {
        return (EAttribute) availableLetterEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAvailableLetter_WordHitMask( ) {
        return (EAttribute) availableLetterEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTicketState( ) {
        return ticketStateEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTicketState_BonusWordIndex( ) {
        return (EAttribute) ticketStateEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTicketState_Tripled( ) {
        return (EAttribute) ticketStateEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTicketState_WordsCompleted( ) {
        return (EAttribute) ticketStateEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTicketState_IncompleteWords( ) {
        return (EAttribute) ticketStateEClass.getEStructuralFeatures().get(
            3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTicketState_AvailableLetters( ) {
        return (EReference) ticketStateEClass.getEStructuralFeatures().get(
            4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBeginCalculation( ) {
        return beginCalculationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBeginCalculation_TicketHref( ) {
        return (EAttribute) beginCalculationEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBeginCalculation_TicketState( ) {
        return (EReference) beginCalculationEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSubtaskRequest( ) {
        return subtaskRequestEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSubtaskRequest_SubTaskId( ) {
        return (EAttribute) subtaskRequestEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSubtaskRequest_TaskId( ) {
        return (EAttribute) subtaskRequestEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSubtaskRequest_TicketHref( ) {
        return (EAttribute) subtaskRequestEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSubtaskRequest_TicketState( ) {
        return (EReference) subtaskRequestEClass.getEStructuralFeatures().get(
            3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSubtaskRequest_PStateInit( ) {
        return (EAttribute) subtaskRequestEClass.getEStructuralFeatures().get(
            4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSubtaskRequest_MaxPermutes( ) {
        return (EAttribute) subtaskRequestEClass.getEStructuralFeatures().get(
            5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSubtaskResponse( ) {
        return subtaskResponseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSubtaskResponse_SubTaskId( ) {
        return (EAttribute) subtaskResponseEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSubtaskResponse_TaskId( ) {
        return (EAttribute) subtaskResponseEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSubtaskResponse_TicketHref( ) {
        return (EAttribute) subtaskResponseEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSubtaskResponse_PrizeDistribution( ) {
        return (EAttribute) subtaskResponseEClass.getEStructuralFeatures().get(
            3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTaskResult( ) {
        return taskResultEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTaskResult_TaskId( ) {
        return (EAttribute) taskResultEClass.getEStructuralFeatures().get(
            0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTaskResult_TicketHref( ) {
        return (EAttribute) taskResultEClass.getEStructuralFeatures().get(
            1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTaskResult_Report( ) {
        return (EReference) taskResultEClass.getEStructuralFeatures().get(
            2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EDataType getBitSet( ) {
        return bitSetEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrizecalcFactory getPrizecalcFactory( ) {
        return (PrizecalcFactory) getEFactoryInstance();
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
        asyncPrizeAnalysisRequestEClass = createEClass(ASYNC_PRIZE_ANALYSIS_REQUEST);
        createEAttribute(
            asyncPrizeAnalysisRequestEClass,
            ASYNC_PRIZE_ANALYSIS_REQUEST__TICKET_HREF);
        createEReference(
            asyncPrizeAnalysisRequestEClass,
            ASYNC_PRIZE_ANALYSIS_REQUEST__REVEALED_INFO);
        createEReference(
            asyncPrizeAnalysisRequestEClass,
            ASYNC_PRIZE_ANALYSIS_REQUEST__BOARD_COVERAGE);

        asyncPrizeAnalysisResponseEClass = createEClass(ASYNC_PRIZE_ANALYSIS_RESPONSE);

        reportPrizeAnalysisRequestEClass = createEClass(REPORT_PRIZE_ANALYSIS_REQUEST);
        createEReference(
            reportPrizeAnalysisRequestEClass,
            REPORT_PRIZE_ANALYSIS_REQUEST__PRIZE_ANALYSIS);

        reportPrizeAnalysisResponseEClass = createEClass(REPORT_PRIZE_ANALYSIS_RESPONSE);

        availableLetterEClass = createEClass(AVAILABLE_LETTER);
        createEAttribute(
            availableLetterEClass,
            AVAILABLE_LETTER__LETTER);
        createEAttribute(
            availableLetterEClass,
            AVAILABLE_LETTER__WORD_HIT_MASK);

        ticketStateEClass = createEClass(TICKET_STATE);
        createEAttribute(
            ticketStateEClass,
            TICKET_STATE__BONUS_WORD_INDEX);
        createEAttribute(
            ticketStateEClass,
            TICKET_STATE__TRIPLED);
        createEAttribute(
            ticketStateEClass,
            TICKET_STATE__WORDS_COMPLETED);
        createEAttribute(
            ticketStateEClass,
            TICKET_STATE__INCOMPLETE_WORDS);
        createEReference(
            ticketStateEClass,
            TICKET_STATE__AVAILABLE_LETTERS);

        beginCalculationEClass = createEClass(BEGIN_CALCULATION);
        createEAttribute(
            beginCalculationEClass,
            BEGIN_CALCULATION__TICKET_HREF);
        createEReference(
            beginCalculationEClass,
            BEGIN_CALCULATION__TICKET_STATE);

        subtaskRequestEClass = createEClass(SUBTASK_REQUEST);
        createEAttribute(
            subtaskRequestEClass,
            SUBTASK_REQUEST__SUB_TASK_ID);
        createEAttribute(
            subtaskRequestEClass,
            SUBTASK_REQUEST__TASK_ID);
        createEAttribute(
            subtaskRequestEClass,
            SUBTASK_REQUEST__TICKET_HREF);
        createEReference(
            subtaskRequestEClass,
            SUBTASK_REQUEST__TICKET_STATE);
        createEAttribute(
            subtaskRequestEClass,
            SUBTASK_REQUEST__PSTATE_INIT);
        createEAttribute(
            subtaskRequestEClass,
            SUBTASK_REQUEST__MAX_PERMUTES);

        subtaskResponseEClass = createEClass(SUBTASK_RESPONSE);
        createEAttribute(
            subtaskResponseEClass,
            SUBTASK_RESPONSE__SUB_TASK_ID);
        createEAttribute(
            subtaskResponseEClass,
            SUBTASK_RESPONSE__TASK_ID);
        createEAttribute(
            subtaskResponseEClass,
            SUBTASK_RESPONSE__TICKET_HREF);
        createEAttribute(
            subtaskResponseEClass,
            SUBTASK_RESPONSE__PRIZE_DISTRIBUTION);

        taskResultEClass = createEClass(TASK_RESULT);
        createEAttribute(
            taskResultEClass,
            TASK_RESULT__TASK_ID);
        createEAttribute(
            taskResultEClass,
            TASK_RESULT__TICKET_HREF);
        createEReference(
            taskResultEClass,
            TASK_RESULT__REPORT);

        // Create data types
        bitSetEDataType = createEDataType(BIT_SET);
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

        // Obtain other dependent packages
        XwPackage theXwPackage = (XwPackage) EPackage.Registry.INSTANCE.getEPackage(XwPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(
            asyncPrizeAnalysisRequestEClass,
            AsyncPrizeAnalysisRequest.class,
            "AsyncPrizeAnalysisRequest",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getAsyncPrizeAnalysisRequest_TicketHref(),
            ecorePackage.getEString(),
            "ticketHref",
            null,
            0,
            1,
            AsyncPrizeAnalysisRequest.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getAsyncPrizeAnalysisRequest_RevealedInfo(),
            theXwPackage.getRevealedInformation(),
            null,
            "revealedInfo",
            null,
            0,
            1,
            AsyncPrizeAnalysisRequest.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_COMPOSITE,
            IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getAsyncPrizeAnalysisRequest_BoardCoverage(),
            theXwPackage.getBoardCoverage(),
            null,
            "boardCoverage",
            null,
            0,
            1,
            AsyncPrizeAnalysisRequest.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_COMPOSITE,
            IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(
            asyncPrizeAnalysisResponseEClass,
            AsyncPrizeAnalysisResponse.class,
            "AsyncPrizeAnalysisResponse",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);

        initEClass(
            reportPrizeAnalysisRequestEClass,
            ReportPrizeAnalysisRequest.class,
            "ReportPrizeAnalysisRequest",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEReference(
            getReportPrizeAnalysisRequest_PrizeAnalysis(),
            theXwPackage.getPrizeAnalysis(),
            null,
            "prizeAnalysis",
            null,
            0,
            1,
            ReportPrizeAnalysisRequest.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_COMPOSITE,
            IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(
            reportPrizeAnalysisResponseEClass,
            ReportPrizeAnalysisResponse.class,
            "ReportPrizeAnalysisResponse",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);

        initEClass(
            availableLetterEClass,
            AvailableLetter.class,
            "AvailableLetter",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getAvailableLetter_Letter(),
            ecorePackage.getEChar(),
            "letter",
            null,
            1,
            1,
            AvailableLetter.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getAvailableLetter_WordHitMask(),
            this.getBitSet(),
            "wordHitMask",
            null,
            1,
            1,
            AvailableLetter.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(
            ticketStateEClass,
            TicketState.class,
            "TicketState",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getTicketState_BonusWordIndex(),
            ecorePackage.getEInt(),
            "bonusWordIndex",
            null,
            0,
            1,
            TicketState.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getTicketState_Tripled(),
            ecorePackage.getEBoolean(),
            "tripled",
            null,
            0,
            1,
            TicketState.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getTicketState_WordsCompleted(),
            ecorePackage.getEInt(),
            "wordsCompleted",
            null,
            0,
            1,
            TicketState.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getTicketState_IncompleteWords(),
            ecorePackage.getEInt(),
            "incompleteWords",
            null,
            13,
            23,
            TicketState.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getTicketState_AvailableLetters(),
            this.getAvailableLetter(),
            null,
            "availableLetters",
            null,
            9,
            26,
            TicketState.class,
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
            beginCalculationEClass,
            BeginCalculation.class,
            "BeginCalculation",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getBeginCalculation_TicketHref(),
            ecorePackage.getEString(),
            "ticketHref",
            null,
            0,
            1,
            BeginCalculation.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getBeginCalculation_TicketState(),
            this.getTicketState(),
            null,
            "ticketState",
            null,
            0,
            1,
            BeginCalculation.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_COMPOSITE,
            IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(
            subtaskRequestEClass,
            SubtaskRequest.class,
            "SubtaskRequest",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getSubtaskRequest_SubTaskId(),
            ecorePackage.getEString(),
            "subTaskId",
            null,
            0,
            1,
            SubtaskRequest.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getSubtaskRequest_TaskId(),
            ecorePackage.getEString(),
            "taskId",
            null,
            0,
            1,
            SubtaskRequest.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getSubtaskRequest_TicketHref(),
            ecorePackage.getEString(),
            "ticketHref",
            null,
            0,
            1,
            SubtaskRequest.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getSubtaskRequest_TicketState(),
            this.getTicketState(),
            null,
            "ticketState",
            null,
            0,
            1,
            SubtaskRequest.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_COMPOSITE,
            IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getSubtaskRequest_PStateInit(),
            ecorePackage.getEInt(),
            "pStateInit",
            null,
            11,
            28,
            SubtaskRequest.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getSubtaskRequest_MaxPermutes(),
            ecorePackage.getEInt(),
            "maxPermutes",
            null,
            0,
            1,
            SubtaskRequest.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(
            subtaskResponseEClass,
            SubtaskResponse.class,
            "SubtaskResponse",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getSubtaskResponse_SubTaskId(),
            ecorePackage.getEString(),
            "subTaskId",
            null,
            0,
            1,
            SubtaskResponse.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getSubtaskResponse_TaskId(),
            ecorePackage.getEString(),
            "taskId",
            null,
            0,
            1,
            SubtaskResponse.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getSubtaskResponse_TicketHref(),
            ecorePackage.getEString(),
            "ticketHref",
            null,
            0,
            1,
            SubtaskResponse.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getSubtaskResponse_PrizeDistribution(),
            ecorePackage.getEInt(),
            "prizeDistribution",
            null,
            18,
            18,
            SubtaskResponse.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(
            taskResultEClass,
            TaskResult.class,
            "TaskResult",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(
            getTaskResult_TaskId(),
            ecorePackage.getEString(),
            "taskId",
            null,
            0,
            1,
            TaskResult.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(
            getTaskResult_TicketHref(),
            ecorePackage.getEString(),
            "ticketHref",
            null,
            0,
            1,
            TaskResult.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(
            getTaskResult_Report(),
            theXwPackage.getPrizeAnalysis(),
            null,
            "report",
            null,
            0,
            1,
            TaskResult.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_COMPOSITE,
            IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        // Initialize data types
        initEDataType(
            bitSetEDataType,
            BitSet.class,
            "BitSet",
            IS_SERIALIZABLE,
            !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http://www.eclipse.org/emf/2002/GenModel
        createGenModelAnnotations();
        // http://www.eclipse.org/emf/2002/Ecore
        createEcoreAnnotations();
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
            new String[] {"basePackage", "info.jchein.sandbox.app.crossword.prizecalc"});
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createEcoreAnnotations( ) {
        String source = "http://www.eclipse.org/emf/2002/Ecore";
        addAnnotation(
            ticketStateEClass,
            source,
            new String[] {
                "documentation",
                "A rearrangement of the reusable information from Task input, optimized for the analysis task."});
        addAnnotation(
            getTicketState_BonusWordIndex(),
            source,
            new String[] {
                "documentation",
                "If the bonus word has been covered, this value will be -1.  Otherwise, it will be the index of both incompleteWords and wordHit BitMask that represents the bonus word."});
        addAnnotation(
            getTicketState_Tripled(),
            source,
            new String[] {
                "documentation",
                "If true, at least one tripling word has already been covered.  If false, then the incomplete words at indices 0..3 are qualifiers for the triple bonus"});
        addAnnotation(
            getTicketState_WordsCompleted(),
            source,
            new String[] {
                "documentation",
                "The number of crossword grid words already covered and therefore excluded from incompleteWords and the wordHit BitMask"});
        addAnnotation(
            getTicketState_IncompleteWords(),
            source,
            new String[] {
                "documentation",
                "Each array value gives the number of unique letters remaining to cover an incomplete word.  For each AvailableLetter in the availableLetters property that corresponds to one of those missing letters, that object\'s wordHit BitMask has a bit set at the same index."});
        addAnnotation(
            getTicketState_AvailableLetters(),
            source,
            new String[] {
                "documentation",
                "A collection of objects representing the unique letters not yet uncovered and an associated BitMask selecting the indices of wordsCompleted that represent words that need the associated letter."});
    }

} // PrizecalcPackageImpl
