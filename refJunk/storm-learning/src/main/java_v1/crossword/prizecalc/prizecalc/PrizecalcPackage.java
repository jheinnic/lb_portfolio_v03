/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel basePackage='info.jchein.sandbox.app.crossword.prizecalc'"
 * @generated
 */
public interface PrizecalcPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "prizecalc";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://info.jchein/ECORE+Crossword+Storm+Topology+Messages/EN/0.0.1";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "pcalc";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    PrizecalcPackage eINSTANCE = info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl.init();

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisRequestImpl <em>Async Prize Analysis Request</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisRequestImpl
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getAsyncPrizeAnalysisRequest()
     * @generated
     */
    int ASYNC_PRIZE_ANALYSIS_REQUEST = 0;

    /**
     * The feature id for the '<em><b>Ticket Href</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASYNC_PRIZE_ANALYSIS_REQUEST__TICKET_HREF = 0;

    /**
     * The feature id for the '<em><b>Revealed Info</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASYNC_PRIZE_ANALYSIS_REQUEST__REVEALED_INFO = 1;

    /**
     * The feature id for the '<em><b>Board Coverage</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASYNC_PRIZE_ANALYSIS_REQUEST__BOARD_COVERAGE = 2;

    /**
     * The number of structural features of the '<em>Async Prize Analysis Request</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASYNC_PRIZE_ANALYSIS_REQUEST_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Async Prize Analysis Request</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASYNC_PRIZE_ANALYSIS_REQUEST_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisResponseImpl <em>Async Prize Analysis Response</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisResponseImpl
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getAsyncPrizeAnalysisResponse()
     * @generated
     */
    int ASYNC_PRIZE_ANALYSIS_RESPONSE = 1;

    /**
     * The number of structural features of the '<em>Async Prize Analysis Response</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASYNC_PRIZE_ANALYSIS_RESPONSE_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Async Prize Analysis Response</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASYNC_PRIZE_ANALYSIS_RESPONSE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.ReportPrizeAnalysisRequestImpl <em>Report Prize Analysis Request</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.ReportPrizeAnalysisRequestImpl
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getReportPrizeAnalysisRequest()
     * @generated
     */
    int REPORT_PRIZE_ANALYSIS_REQUEST = 2;

    /**
     * The feature id for the '<em><b>Prize Analysis</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPORT_PRIZE_ANALYSIS_REQUEST__PRIZE_ANALYSIS = 0;

    /**
     * The number of structural features of the '<em>Report Prize Analysis Request</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPORT_PRIZE_ANALYSIS_REQUEST_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Report Prize Analysis Request</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPORT_PRIZE_ANALYSIS_REQUEST_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.ReportPrizeAnalysisResponseImpl <em>Report Prize Analysis Response</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.ReportPrizeAnalysisResponseImpl
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getReportPrizeAnalysisResponse()
     * @generated
     */
    int REPORT_PRIZE_ANALYSIS_RESPONSE = 3;

    /**
     * The number of structural features of the '<em>Report Prize Analysis Response</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPORT_PRIZE_ANALYSIS_RESPONSE_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Report Prize Analysis Response</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPORT_PRIZE_ANALYSIS_RESPONSE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AvailableLetterImpl <em>Available Letter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AvailableLetterImpl
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getAvailableLetter()
     * @generated
     */
    int AVAILABLE_LETTER = 4;

    /**
     * The feature id for the '<em><b>Letter</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AVAILABLE_LETTER__LETTER = 0;

    /**
     * The feature id for the '<em><b>Word Hit Mask</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AVAILABLE_LETTER__WORD_HIT_MASK = 1;

    /**
     * The number of structural features of the '<em>Available Letter</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AVAILABLE_LETTER_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Available Letter</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AVAILABLE_LETTER_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TicketStateImpl <em>Ticket State</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TicketStateImpl
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getTicketState()
     * @generated
     */
    int TICKET_STATE = 5;

    /**
     * The feature id for the '<em><b>Bonus Word Index</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TICKET_STATE__BONUS_WORD_INDEX = 0;

    /**
     * The feature id for the '<em><b>Tripled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TICKET_STATE__TRIPLED = 1;

    /**
     * The feature id for the '<em><b>Words Completed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TICKET_STATE__WORDS_COMPLETED = 2;

    /**
     * The feature id for the '<em><b>Incomplete Words</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TICKET_STATE__INCOMPLETE_WORDS = 3;

    /**
     * The feature id for the '<em><b>Available Letters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TICKET_STATE__AVAILABLE_LETTERS = 4;

    /**
     * The number of structural features of the '<em>Ticket State</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TICKET_STATE_FEATURE_COUNT = 5;

    /**
     * The number of operations of the '<em>Ticket State</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TICKET_STATE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.BeginCalculationImpl <em>Begin Calculation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.BeginCalculationImpl
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getBeginCalculation()
     * @generated
     */
    int BEGIN_CALCULATION = 6;

    /**
     * The feature id for the '<em><b>Ticket Href</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BEGIN_CALCULATION__TICKET_HREF = 0;

    /**
     * The feature id for the '<em><b>Ticket State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BEGIN_CALCULATION__TICKET_STATE = 1;

    /**
     * The number of structural features of the '<em>Begin Calculation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BEGIN_CALCULATION_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Begin Calculation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BEGIN_CALCULATION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskRequestImpl <em>Subtask Request</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskRequestImpl
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getSubtaskRequest()
     * @generated
     */
    int SUBTASK_REQUEST = 7;

    /**
     * The feature id for the '<em><b>Sub Task Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_REQUEST__SUB_TASK_ID = 0;

    /**
     * The feature id for the '<em><b>Task Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_REQUEST__TASK_ID = 1;

    /**
     * The feature id for the '<em><b>Ticket Href</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_REQUEST__TICKET_HREF = 2;

    /**
     * The feature id for the '<em><b>Ticket State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_REQUEST__TICKET_STATE = 3;

    /**
     * The feature id for the '<em><b>PState Init</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_REQUEST__PSTATE_INIT = 4;

    /**
     * The feature id for the '<em><b>Max Permutes</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_REQUEST__MAX_PERMUTES = 5;

    /**
     * The number of structural features of the '<em>Subtask Request</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_REQUEST_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Subtask Request</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_REQUEST_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl <em>Subtask Response</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getSubtaskResponse()
     * @generated
     */
    int SUBTASK_RESPONSE = 8;

    /**
     * The feature id for the '<em><b>Sub Task Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_RESPONSE__SUB_TASK_ID = 0;

    /**
     * The feature id for the '<em><b>Task Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_RESPONSE__TASK_ID = 1;

    /**
     * The feature id for the '<em><b>Ticket Href</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_RESPONSE__TICKET_HREF = 2;

    /**
     * The feature id for the '<em><b>Prize Distribution</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_RESPONSE__PRIZE_DISTRIBUTION = 3;

    /**
     * The number of structural features of the '<em>Subtask Response</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_RESPONSE_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Subtask Response</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBTASK_RESPONSE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TaskResultImpl <em>Task Result</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TaskResultImpl
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getTaskResult()
     * @generated
     */
    int TASK_RESULT = 9;

    /**
     * The feature id for the '<em><b>Task Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_RESULT__TASK_ID = 0;

    /**
     * The feature id for the '<em><b>Ticket Href</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_RESULT__TICKET_HREF = 1;

    /**
     * The feature id for the '<em><b>Report</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_RESULT__REPORT = 2;

    /**
     * The number of structural features of the '<em>Task Result</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_RESULT_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Task Result</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_RESULT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '<em>Bit Set</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.BitSet
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getBitSet()
     * @generated
     */
    int BIT_SET = 10;


    /**
     * Returns the meta object for class '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest <em>Async Prize Analysis Request</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Async Prize Analysis Request</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest
     * @generated
     */
    EClass getAsyncPrizeAnalysisRequest();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getTicketHref <em>Ticket Href</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ticket Href</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getTicketHref()
     * @see #getAsyncPrizeAnalysisRequest()
     * @generated
     */
    EAttribute getAsyncPrizeAnalysisRequest_TicketHref();

    /**
     * Returns the meta object for the reference '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getRevealedInfo <em>Revealed Info</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Revealed Info</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getRevealedInfo()
     * @see #getAsyncPrizeAnalysisRequest()
     * @generated
     */
    EReference getAsyncPrizeAnalysisRequest_RevealedInfo();

    /**
     * Returns the meta object for the reference '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getBoardCoverage <em>Board Coverage</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Board Coverage</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest#getBoardCoverage()
     * @see #getAsyncPrizeAnalysisRequest()
     * @generated
     */
    EReference getAsyncPrizeAnalysisRequest_BoardCoverage();

    /**
     * Returns the meta object for class '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse <em>Async Prize Analysis Response</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Async Prize Analysis Response</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse
     * @generated
     */
    EClass getAsyncPrizeAnalysisResponse();

    /**
     * Returns the meta object for class '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest <em>Report Prize Analysis Request</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Report Prize Analysis Request</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest
     * @generated
     */
    EClass getReportPrizeAnalysisRequest();

    /**
     * Returns the meta object for the reference '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest#getPrizeAnalysis <em>Prize Analysis</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Prize Analysis</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest#getPrizeAnalysis()
     * @see #getReportPrizeAnalysisRequest()
     * @generated
     */
    EReference getReportPrizeAnalysisRequest_PrizeAnalysis();

    /**
     * Returns the meta object for class '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse <em>Report Prize Analysis Response</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Report Prize Analysis Response</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse
     * @generated
     */
    EClass getReportPrizeAnalysisResponse();

    /**
     * Returns the meta object for class '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter <em>Available Letter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Available Letter</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter
     * @generated
     */
    EClass getAvailableLetter();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter#getLetter <em>Letter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Letter</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter#getLetter()
     * @see #getAvailableLetter()
     * @generated
     */
    EAttribute getAvailableLetter_Letter();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter#getWordHitMask <em>Word Hit Mask</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Word Hit Mask</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter#getWordHitMask()
     * @see #getAvailableLetter()
     * @generated
     */
    EAttribute getAvailableLetter_WordHitMask();

    /**
     * Returns the meta object for class '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState <em>Ticket State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Ticket State</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState
     * @generated
     */
    EClass getTicketState();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getBonusWordIndex <em>Bonus Word Index</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bonus Word Index</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getBonusWordIndex()
     * @see #getTicketState()
     * @generated
     */
    EAttribute getTicketState_BonusWordIndex();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#isTripled <em>Tripled</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tripled</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#isTripled()
     * @see #getTicketState()
     * @generated
     */
    EAttribute getTicketState_Tripled();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getWordsCompleted <em>Words Completed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Words Completed</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getWordsCompleted()
     * @see #getTicketState()
     * @generated
     */
    EAttribute getTicketState_WordsCompleted();

    /**
     * Returns the meta object for the attribute list '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getIncompleteWords <em>Incomplete Words</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Incomplete Words</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getIncompleteWords()
     * @see #getTicketState()
     * @generated
     */
    EAttribute getTicketState_IncompleteWords();

    /**
     * Returns the meta object for the containment reference list '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getAvailableLetters <em>Available Letters</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Available Letters</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getAvailableLetters()
     * @see #getTicketState()
     * @generated
     */
    EReference getTicketState_AvailableLetters();

    /**
     * Returns the meta object for class '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation <em>Begin Calculation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Begin Calculation</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation
     * @generated
     */
    EClass getBeginCalculation();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation#getTicketHref <em>Ticket Href</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ticket Href</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation#getTicketHref()
     * @see #getBeginCalculation()
     * @generated
     */
    EAttribute getBeginCalculation_TicketHref();

    /**
     * Returns the meta object for the reference '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation#getTicketState <em>Ticket State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Ticket State</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation#getTicketState()
     * @see #getBeginCalculation()
     * @generated
     */
    EReference getBeginCalculation_TicketState();

    /**
     * Returns the meta object for class '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest <em>Subtask Request</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Subtask Request</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest
     * @generated
     */
    EClass getSubtaskRequest();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getSubTaskId <em>Sub Task Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Sub Task Id</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getSubTaskId()
     * @see #getSubtaskRequest()
     * @generated
     */
    EAttribute getSubtaskRequest_SubTaskId();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTaskId <em>Task Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Task Id</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTaskId()
     * @see #getSubtaskRequest()
     * @generated
     */
    EAttribute getSubtaskRequest_TaskId();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTicketHref <em>Ticket Href</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ticket Href</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTicketHref()
     * @see #getSubtaskRequest()
     * @generated
     */
    EAttribute getSubtaskRequest_TicketHref();

    /**
     * Returns the meta object for the reference '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTicketState <em>Ticket State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Ticket State</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getTicketState()
     * @see #getSubtaskRequest()
     * @generated
     */
    EReference getSubtaskRequest_TicketState();

    /**
     * Returns the meta object for the attribute list '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getPStateInit <em>PState Init</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>PState Init</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getPStateInit()
     * @see #getSubtaskRequest()
     * @generated
     */
    EAttribute getSubtaskRequest_PStateInit();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getMaxPermutes <em>Max Permutes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max Permutes</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest#getMaxPermutes()
     * @see #getSubtaskRequest()
     * @generated
     */
    EAttribute getSubtaskRequest_MaxPermutes();

    /**
     * Returns the meta object for class '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse <em>Subtask Response</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Subtask Response</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse
     * @generated
     */
    EClass getSubtaskResponse();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getSubTaskId <em>Sub Task Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Sub Task Id</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getSubTaskId()
     * @see #getSubtaskResponse()
     * @generated
     */
    EAttribute getSubtaskResponse_SubTaskId();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getTaskId <em>Task Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Task Id</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getTaskId()
     * @see #getSubtaskResponse()
     * @generated
     */
    EAttribute getSubtaskResponse_TaskId();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getTicketHref <em>Ticket Href</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ticket Href</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getTicketHref()
     * @see #getSubtaskResponse()
     * @generated
     */
    EAttribute getSubtaskResponse_TicketHref();

    /**
     * Returns the meta object for the attribute list '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getPrizeDistribution <em>Prize Distribution</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Prize Distribution</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse#getPrizeDistribution()
     * @see #getSubtaskResponse()
     * @generated
     */
    EAttribute getSubtaskResponse_PrizeDistribution();

    /**
     * Returns the meta object for class '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult <em>Task Result</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Task Result</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult
     * @generated
     */
    EClass getTaskResult();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getTaskId <em>Task Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Task Id</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getTaskId()
     * @see #getTaskResult()
     * @generated
     */
    EAttribute getTaskResult_TaskId();

    /**
     * Returns the meta object for the attribute '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getTicketHref <em>Ticket Href</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ticket Href</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getTicketHref()
     * @see #getTaskResult()
     * @generated
     */
    EAttribute getTaskResult_TicketHref();

    /**
     * Returns the meta object for the reference '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getReport <em>Report</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Report</em>'.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult#getReport()
     * @see #getTaskResult()
     * @generated
     */
    EReference getTaskResult_Report();

    /**
     * Returns the meta object for data type '{@link java.util.BitSet <em>Bit Set</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Bit Set</em>'.
     * @see java.util.BitSet
     * @model instanceClass="java.util.BitSet"
     * @generated
     */
    EDataType getBitSet();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PrizecalcFactory getPrizecalcFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each operation of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisRequestImpl <em>Async Prize Analysis Request</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisRequestImpl
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getAsyncPrizeAnalysisRequest()
         * @generated
         */
        EClass ASYNC_PRIZE_ANALYSIS_REQUEST = eINSTANCE.getAsyncPrizeAnalysisRequest();

        /**
         * The meta object literal for the '<em><b>Ticket Href</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ASYNC_PRIZE_ANALYSIS_REQUEST__TICKET_HREF = eINSTANCE.getAsyncPrizeAnalysisRequest_TicketHref();

        /**
         * The meta object literal for the '<em><b>Revealed Info</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASYNC_PRIZE_ANALYSIS_REQUEST__REVEALED_INFO = eINSTANCE.getAsyncPrizeAnalysisRequest_RevealedInfo();

        /**
         * The meta object literal for the '<em><b>Board Coverage</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASYNC_PRIZE_ANALYSIS_REQUEST__BOARD_COVERAGE = eINSTANCE.getAsyncPrizeAnalysisRequest_BoardCoverage();

        /**
         * The meta object literal for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisResponseImpl <em>Async Prize Analysis Response</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AsyncPrizeAnalysisResponseImpl
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getAsyncPrizeAnalysisResponse()
         * @generated
         */
        EClass ASYNC_PRIZE_ANALYSIS_RESPONSE = eINSTANCE.getAsyncPrizeAnalysisResponse();

        /**
         * The meta object literal for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.ReportPrizeAnalysisRequestImpl <em>Report Prize Analysis Request</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.ReportPrizeAnalysisRequestImpl
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getReportPrizeAnalysisRequest()
         * @generated
         */
        EClass REPORT_PRIZE_ANALYSIS_REQUEST = eINSTANCE.getReportPrizeAnalysisRequest();

        /**
         * The meta object literal for the '<em><b>Prize Analysis</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference REPORT_PRIZE_ANALYSIS_REQUEST__PRIZE_ANALYSIS = eINSTANCE.getReportPrizeAnalysisRequest_PrizeAnalysis();

        /**
         * The meta object literal for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.ReportPrizeAnalysisResponseImpl <em>Report Prize Analysis Response</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.ReportPrizeAnalysisResponseImpl
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getReportPrizeAnalysisResponse()
         * @generated
         */
        EClass REPORT_PRIZE_ANALYSIS_RESPONSE = eINSTANCE.getReportPrizeAnalysisResponse();

        /**
         * The meta object literal for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AvailableLetterImpl <em>Available Letter</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.AvailableLetterImpl
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getAvailableLetter()
         * @generated
         */
        EClass AVAILABLE_LETTER = eINSTANCE.getAvailableLetter();

        /**
         * The meta object literal for the '<em><b>Letter</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute AVAILABLE_LETTER__LETTER = eINSTANCE.getAvailableLetter_Letter();

        /**
         * The meta object literal for the '<em><b>Word Hit Mask</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute AVAILABLE_LETTER__WORD_HIT_MASK = eINSTANCE.getAvailableLetter_WordHitMask();

        /**
         * The meta object literal for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TicketStateImpl <em>Ticket State</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TicketStateImpl
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getTicketState()
         * @generated
         */
        EClass TICKET_STATE = eINSTANCE.getTicketState();

        /**
         * The meta object literal for the '<em><b>Bonus Word Index</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TICKET_STATE__BONUS_WORD_INDEX = eINSTANCE.getTicketState_BonusWordIndex();

        /**
         * The meta object literal for the '<em><b>Tripled</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TICKET_STATE__TRIPLED = eINSTANCE.getTicketState_Tripled();

        /**
         * The meta object literal for the '<em><b>Words Completed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TICKET_STATE__WORDS_COMPLETED = eINSTANCE.getTicketState_WordsCompleted();

        /**
         * The meta object literal for the '<em><b>Incomplete Words</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TICKET_STATE__INCOMPLETE_WORDS = eINSTANCE.getTicketState_IncompleteWords();

        /**
         * The meta object literal for the '<em><b>Available Letters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TICKET_STATE__AVAILABLE_LETTERS = eINSTANCE.getTicketState_AvailableLetters();

        /**
         * The meta object literal for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.BeginCalculationImpl <em>Begin Calculation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.BeginCalculationImpl
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getBeginCalculation()
         * @generated
         */
        EClass BEGIN_CALCULATION = eINSTANCE.getBeginCalculation();

        /**
         * The meta object literal for the '<em><b>Ticket Href</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BEGIN_CALCULATION__TICKET_HREF = eINSTANCE.getBeginCalculation_TicketHref();

        /**
         * The meta object literal for the '<em><b>Ticket State</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BEGIN_CALCULATION__TICKET_STATE = eINSTANCE.getBeginCalculation_TicketState();

        /**
         * The meta object literal for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskRequestImpl <em>Subtask Request</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskRequestImpl
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getSubtaskRequest()
         * @generated
         */
        EClass SUBTASK_REQUEST = eINSTANCE.getSubtaskRequest();

        /**
         * The meta object literal for the '<em><b>Sub Task Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUBTASK_REQUEST__SUB_TASK_ID = eINSTANCE.getSubtaskRequest_SubTaskId();

        /**
         * The meta object literal for the '<em><b>Task Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUBTASK_REQUEST__TASK_ID = eINSTANCE.getSubtaskRequest_TaskId();

        /**
         * The meta object literal for the '<em><b>Ticket Href</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUBTASK_REQUEST__TICKET_HREF = eINSTANCE.getSubtaskRequest_TicketHref();

        /**
         * The meta object literal for the '<em><b>Ticket State</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SUBTASK_REQUEST__TICKET_STATE = eINSTANCE.getSubtaskRequest_TicketState();

        /**
         * The meta object literal for the '<em><b>PState Init</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUBTASK_REQUEST__PSTATE_INIT = eINSTANCE.getSubtaskRequest_PStateInit();

        /**
         * The meta object literal for the '<em><b>Max Permutes</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUBTASK_REQUEST__MAX_PERMUTES = eINSTANCE.getSubtaskRequest_MaxPermutes();

        /**
         * The meta object literal for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl <em>Subtask Response</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.SubtaskResponseImpl
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getSubtaskResponse()
         * @generated
         */
        EClass SUBTASK_RESPONSE = eINSTANCE.getSubtaskResponse();

        /**
         * The meta object literal for the '<em><b>Sub Task Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUBTASK_RESPONSE__SUB_TASK_ID = eINSTANCE.getSubtaskResponse_SubTaskId();

        /**
         * The meta object literal for the '<em><b>Task Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUBTASK_RESPONSE__TASK_ID = eINSTANCE.getSubtaskResponse_TaskId();

        /**
         * The meta object literal for the '<em><b>Ticket Href</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUBTASK_RESPONSE__TICKET_HREF = eINSTANCE.getSubtaskResponse_TicketHref();

        /**
         * The meta object literal for the '<em><b>Prize Distribution</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUBTASK_RESPONSE__PRIZE_DISTRIBUTION = eINSTANCE.getSubtaskResponse_PrizeDistribution();

        /**
         * The meta object literal for the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TaskResultImpl <em>Task Result</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TaskResultImpl
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getTaskResult()
         * @generated
         */
        EClass TASK_RESULT = eINSTANCE.getTaskResult();

        /**
         * The meta object literal for the '<em><b>Task Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TASK_RESULT__TASK_ID = eINSTANCE.getTaskResult_TaskId();

        /**
         * The meta object literal for the '<em><b>Ticket Href</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TASK_RESULT__TICKET_HREF = eINSTANCE.getTaskResult_TicketHref();

        /**
         * The meta object literal for the '<em><b>Report</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TASK_RESULT__REPORT = eINSTANCE.getTaskResult_Report();

        /**
         * The meta object literal for the '<em>Bit Set</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.util.BitSet
         * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcPackageImpl#getBitSet()
         * @generated
         */
        EDataType BIT_SET = eINSTANCE.getBitSet();

    }

} //PrizecalcPackage
