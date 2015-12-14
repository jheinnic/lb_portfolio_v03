/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to
 * represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwFactory
 * @model kind="package" annotation=
 *        "http://www.eclipse.org/emf/2002/GenModel basePackage='info.jchein.sandbox.app.crossword.resources'"
 * @generated
 */
public interface XwPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "xw";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://info.jchein/ECORE+Crossword+Resource+Representations/EN/0.0.1";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "xw";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    XwPackage eINSTANCE = info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl
     * <em>Pending Ticket Detail</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getPendingTicketDetail()
     * @generated
     */
    int PENDING_TICKET_DETAIL = 0;

    /**
     * The feature id for the '<em><b>Board Content</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PENDING_TICKET_DETAIL__BOARD_CONTENT = 0;

    /**
     * The feature id for the '<em><b>Bonus Word</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PENDING_TICKET_DETAIL__BONUS_WORD = 1;

    /**
     * The feature id for the '<em><b>Twenty Spot Included</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PENDING_TICKET_DETAIL__TWENTY_SPOT_INCLUDED = 2;

    /**
     * The feature id for the '<em><b>All Words</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PENDING_TICKET_DETAIL__ALL_WORDS = 3;

    /**
     * The feature id for the '<em><b>Basic Words</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PENDING_TICKET_DETAIL__BASIC_WORDS = 4;

    /**
     * The feature id for the '<em><b>Triple Words</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PENDING_TICKET_DETAIL__TRIPLE_WORDS = 5;

    /**
     * The feature id for the '<em><b>Publishable</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PENDING_TICKET_DETAIL__PUBLISHABLE = 6;

    /**
     * The number of structural features of the '<em>Pending Ticket Detail</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PENDING_TICKET_DETAIL_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.PublishedTicketDetailImpl
     * <em>Published Ticket Detail</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.PublishedTicketDetailImpl
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getPublishedTicketDetail()
     * @generated
     */
    int PUBLISHED_TICKET_DETAIL = 1;

    /**
     * The feature id for the '<em><b>Board Content</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PUBLISHED_TICKET_DETAIL__BOARD_CONTENT = 0;

    /**
     * The feature id for the '<em><b>Bonus Word</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PUBLISHED_TICKET_DETAIL__BONUS_WORD = 1;

    /**
     * The feature id for the '<em><b>Twenty Spot Included</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PUBLISHED_TICKET_DETAIL__TWENTY_SPOT_INCLUDED = 2;

    /**
     * The number of structural features of the '<em>Published Ticket Detail</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PUBLISHED_TICKET_DETAIL_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.BoardCoverageImpl <em>Board Coverage</em>}
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.BoardCoverageImpl
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getBoardCoverage()
     * @generated
     */
    int BOARD_COVERAGE = 2;

    /**
     * The feature id for the '<em><b>Bonus Word</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOARD_COVERAGE__BONUS_WORD = 0;

    /**
     * The feature id for the '<em><b>All Words</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOARD_COVERAGE__ALL_WORDS = 1;

    /**
     * The feature id for the '<em><b>Basic Words</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOARD_COVERAGE__BASIC_WORDS = 2;

    /**
     * The feature id for the '<em><b>Triple Words</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOARD_COVERAGE__TRIPLE_WORDS = 3;

    /**
     * The number of structural features of the '<em>Board Coverage</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOARD_COVERAGE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.AbstractWordCoverageImpl
     * <em>Abstract Word Coverage</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.AbstractWordCoverageImpl
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getAbstractWordCoverage()
     * @generated
     */
    int ABSTRACT_WORD_COVERAGE = 3;

    /**
     * The feature id for the '<em><b>Raw Word</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_WORD_COVERAGE__RAW_WORD = 0;

    /**
     * The feature id for the '<em><b>Display Word</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_WORD_COVERAGE__DISPLAY_WORD = 1;

    /**
     * The feature id for the '<em><b>Uncovered Letters</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_WORD_COVERAGE__UNCOVERED_LETTERS = 2;

    /**
     * The number of structural features of the '<em>Abstract Word Coverage</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_WORD_COVERAGE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.BonusWordCoverageImpl
     * <em>Bonus Word Coverage</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.BonusWordCoverageImpl
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getBonusWordCoverage()
     * @generated
     */
    int BONUS_WORD_COVERAGE = 4;

    /**
     * The feature id for the '<em><b>Raw Word</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BONUS_WORD_COVERAGE__RAW_WORD = ABSTRACT_WORD_COVERAGE__RAW_WORD;

    /**
     * The feature id for the '<em><b>Display Word</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BONUS_WORD_COVERAGE__DISPLAY_WORD = ABSTRACT_WORD_COVERAGE__DISPLAY_WORD;

    /**
     * The feature id for the '<em><b>Uncovered Letters</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BONUS_WORD_COVERAGE__UNCOVERED_LETTERS = ABSTRACT_WORD_COVERAGE__UNCOVERED_LETTERS;

    /**
     * The feature id for the '<em><b>Bonus Paid</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BONUS_WORD_COVERAGE__BONUS_PAID = ABSTRACT_WORD_COVERAGE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Bonus Word Coverage</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BONUS_WORD_COVERAGE_FEATURE_COUNT = ABSTRACT_WORD_COVERAGE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.GridWordCoverageImpl
     * <em>Grid Word Coverage</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.GridWordCoverageImpl
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getGridWordCoverage()
     * @generated
     */
    int GRID_WORD_COVERAGE = 5;

    /**
     * The feature id for the '<em><b>Raw Word</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRID_WORD_COVERAGE__RAW_WORD = ABSTRACT_WORD_COVERAGE__RAW_WORD;

    /**
     * The feature id for the '<em><b>Display Word</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRID_WORD_COVERAGE__DISPLAY_WORD = ABSTRACT_WORD_COVERAGE__DISPLAY_WORD;

    /**
     * The feature id for the '<em><b>Uncovered Letters</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRID_WORD_COVERAGE__UNCOVERED_LETTERS = ABSTRACT_WORD_COVERAGE__UNCOVERED_LETTERS;

    /**
     * The feature id for the '<em><b>Word Covered</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRID_WORD_COVERAGE__WORD_COVERED = ABSTRACT_WORD_COVERAGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Tripling</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRID_WORD_COVERAGE__TRIPLING = ABSTRACT_WORD_COVERAGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Grid Word Coverage</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRID_WORD_COVERAGE_FEATURE_COUNT = ABSTRACT_WORD_COVERAGE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.RevealedInformationImpl
     * <em>Revealed Information</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.RevealedInformationImpl
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getRevealedInformation()
     * @generated
     */
    int REVEALED_INFORMATION = 6;

    /**
     * The feature id for the '<em><b>Letter Pool</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REVEALED_INFORMATION__LETTER_POOL = 0;

    /**
     * The feature id for the '<em><b>Twenty Spot Paid</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REVEALED_INFORMATION__TWENTY_SPOT_PAID = 1;

    /**
     * The feature id for the '<em><b>Bonus Value</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REVEALED_INFORMATION__BONUS_VALUE = 2;

    /**
     * The number of structural features of the '<em>Revealed Information</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REVEALED_INFORMATION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.PrizeAnalysisImpl <em>Prize Analysis</em>}
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.PrizeAnalysisImpl
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getPrizeAnalysis()
     * @generated
     */
    int PRIZE_ANALYSIS = 7;

    /**
     * The feature id for the '<em><b>Prize Distribution</b></em>' attribute list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PRIZE_ANALYSIS__PRIZE_DISTRIBUTION = 0;

    /**
     * The feature id for the '<em><b>Dynamic Input</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PRIZE_ANALYSIS__DYNAMIC_INPUT = 1;

    /**
     * The number of structural features of the '<em>Prize Analysis</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PRIZE_ANALYSIS_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl
     * <em>Playable Ticket</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getPlayableTicket()
     * @generated
     */
    int PLAYABLE_TICKET = 8;

    /**
     * The feature id for the '<em><b>Fixed Details</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLAYABLE_TICKET__FIXED_DETAILS = 0;

    /**
     * The feature id for the '<em><b>Board Content</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLAYABLE_TICKET__BOARD_CONTENT = 1;

    /**
     * The feature id for the '<em><b>Bonus Word</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLAYABLE_TICKET__BONUS_WORD = 2;

    /**
     * The feature id for the '<em><b>Twenty Spot Included</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLAYABLE_TICKET__TWENTY_SPOT_INCLUDED = 3;

    /**
     * The feature id for the '<em><b>Dynamic Input</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLAYABLE_TICKET__DYNAMIC_INPUT = 4;

    /**
     * The feature id for the '<em><b>Latest Analysis</b></em>' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLAYABLE_TICKET__LATEST_ANALYSIS = 5;

    /**
     * The feature id for the '<em><b>Board Coverage</b></em>' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLAYABLE_TICKET__BOARD_COVERAGE = 6;

    /**
     * The feature id for the '<em><b>Resolved</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLAYABLE_TICKET__RESOLVED = 7;

    /**
     * The number of structural features of the '<em>Playable Ticket</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLAYABLE_TICKET_FEATURE_COUNT = 8;

    /**
     * The meta object id for the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl
     * <em>Resolved Ticket</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getResolvedTicket()
     * @generated
     */
    int RESOLVED_TICKET = 9;

    /**
     * The feature id for the '<em><b>Fixed Details</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOLVED_TICKET__FIXED_DETAILS = 0;

    /**
     * The feature id for the '<em><b>Board Content</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOLVED_TICKET__BOARD_CONTENT = 1;

    /**
     * The feature id for the '<em><b>Bonus Word</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOLVED_TICKET__BONUS_WORD = 2;

    /**
     * The feature id for the '<em><b>Twenty Spot Included</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOLVED_TICKET__TWENTY_SPOT_INCLUDED = 3;

    /**
     * The feature id for the '<em><b>Letter Pool</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOLVED_TICKET__LETTER_POOL = 4;

    /**
     * The feature id for the '<em><b>Twenty Spot Paid</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOLVED_TICKET__TWENTY_SPOT_PAID = 5;

    /**
     * The feature id for the '<em><b>Bonus Value</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOLVED_TICKET__BONUS_VALUE = 6;

    /**
     * The feature id for the '<em><b>Payout Tier</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOLVED_TICKET__PAYOUT_TIER = 7;

    /**
     * The feature id for the '<em><b>Board Coverage</b></em>' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOLVED_TICKET__BOARD_COVERAGE = 8;

    /**
     * The number of structural features of the '<em>Resolved Ticket</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOLVED_TICKET_FEATURE_COUNT = 9;

    /**
     * The meta object id for the '{@link info.jchein.sandbox.app.crossword.resources.xw.BonusValue
     * <em>Bonus Value</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see info.jchein.sandbox.app.crossword.resources.xw.BonusValue
     * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getBonusValue()
     * @generated
     */
    int BONUS_VALUE = 10;


    /**
     * Returns the meta object for class '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail
     * <em>Pending Ticket Detail</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Pending Ticket Detail</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail
     * @generated
     */
    EClass getPendingTicketDetail( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBoardContent
     * <em>Board Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Board Content</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBoardContent()
     * @see #getPendingTicketDetail()
     * @generated
     */
    EAttribute getPendingTicketDetail_BoardContent( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBonusWord
     * <em>Bonus Word</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Bonus Word</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBonusWord()
     * @see #getPendingTicketDetail()
     * @generated
     */
    EAttribute getPendingTicketDetail_BonusWord( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#isTwentySpotIncluded
     * <em>Twenty Spot Included</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Twenty Spot Included</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#isTwentySpotIncluded()
     * @see #getPendingTicketDetail()
     * @generated
     */
    EAttribute getPendingTicketDetail_TwentySpotIncluded( );

    /**
     * Returns the meta object for the attribute list '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getAllWords
     * <em>All Words</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>All Words</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getAllWords()
     * @see #getPendingTicketDetail()
     * @generated
     */
    EAttribute getPendingTicketDetail_AllWords( );

    /**
     * Returns the meta object for the attribute list '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBasicWords
     * <em>Basic Words</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Basic Words</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getBasicWords()
     * @see #getPendingTicketDetail()
     * @generated
     */
    EAttribute getPendingTicketDetail_BasicWords( );

    /**
     * Returns the meta object for the attribute list '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getTripleWords
     * <em>Triple Words</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Triple Words</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#getTripleWords()
     * @see #getPendingTicketDetail()
     * @generated
     */
    EAttribute getPendingTicketDetail_TripleWords( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#isPublishable
     * <em>Publishable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Publishable</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail#isPublishable()
     * @see #getPendingTicketDetail()
     * @generated
     */
    EAttribute getPendingTicketDetail_Publishable( );

    /**
     * Returns the meta object for class '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail
     * <em>Published Ticket Detail</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Published Ticket Detail</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail
     * @generated
     */
    EClass getPublishedTicketDetail( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#getBoardContent
     * <em>Board Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Board Content</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#getBoardContent()
     * @see #getPublishedTicketDetail()
     * @generated
     */
    EAttribute getPublishedTicketDetail_BoardContent( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#getBonusWord
     * <em>Bonus Word</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Bonus Word</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#getBonusWord()
     * @see #getPublishedTicketDetail()
     * @generated
     */
    EAttribute getPublishedTicketDetail_BonusWord( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#isTwentySpotIncluded
     * <em>Twenty Spot Included</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Twenty Spot Included</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail#isTwentySpotIncluded()
     * @see #getPublishedTicketDetail()
     * @generated
     */
    EAttribute getPublishedTicketDetail_TwentySpotIncluded( );

    /**
     * Returns the meta object for class '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage <em>Board Coverage</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Board Coverage</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage
     * @generated
     */
    EClass getBoardCoverage( );

    /**
     * Returns the meta object for the containment reference '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getBonusWord <em>Bonus Word</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Bonus Word</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getBonusWord()
     * @see #getBoardCoverage()
     * @generated
     */
    EReference getBoardCoverage_BonusWord( );

    /**
     * Returns the meta object for the containment reference list '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getAllWords <em>All Words</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>All Words</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getAllWords()
     * @see #getBoardCoverage()
     * @generated
     */
    EReference getBoardCoverage_AllWords( );

    /**
     * Returns the meta object for the containment reference list '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getBasicWords
     * <em>Basic Words</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Basic Words</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getBasicWords()
     * @see #getBoardCoverage()
     * @generated
     */
    EReference getBoardCoverage_BasicWords( );

    /**
     * Returns the meta object for the containment reference list '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getTripleWords
     * <em>Triple Words</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Triple Words</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getTripleWords()
     * @see #getBoardCoverage()
     * @generated
     */
    EReference getBoardCoverage_TripleWords( );

    /**
     * Returns the meta object for class '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage
     * <em>Abstract Word Coverage</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract Word Coverage</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage
     * @generated
     */
    EClass getAbstractWordCoverage( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getRawWord
     * <em>Raw Word</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Raw Word</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getRawWord()
     * @see #getAbstractWordCoverage()
     * @generated
     */
    EAttribute getAbstractWordCoverage_RawWord( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getDisplayWord
     * <em>Display Word</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Display Word</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getDisplayWord()
     * @see #getAbstractWordCoverage()
     * @generated
     */
    EAttribute getAbstractWordCoverage_DisplayWord( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getUncoveredLetters
     * <em>Uncovered Letters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Uncovered Letters</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getUncoveredLetters()
     * @see #getAbstractWordCoverage()
     * @generated
     */
    EAttribute getAbstractWordCoverage_UncoveredLetters( );

    /**
     * Returns the meta object for class '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage <em>Bonus Word Coverage</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Bonus Word Coverage</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage
     * @generated
     */
    EClass getBonusWordCoverage( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage#isBonusPaid
     * <em>Bonus Paid</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Bonus Paid</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage#isBonusPaid()
     * @see #getBonusWordCoverage()
     * @generated
     */
    EAttribute getBonusWordCoverage_BonusPaid( );

    /**
     * Returns the meta object for class '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage <em>Grid Word Coverage</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Grid Word Coverage</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage
     * @generated
     */
    EClass getGridWordCoverage( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage#isWordCovered
     * <em>Word Covered</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Word Covered</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage#isWordCovered()
     * @see #getGridWordCoverage()
     * @generated
     */
    EAttribute getGridWordCoverage_WordCovered( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage#isTripling <em>Tripling</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Tripling</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage#isTripling()
     * @see #getGridWordCoverage()
     * @generated
     */
    EAttribute getGridWordCoverage_Tripling( );

    /**
     * Returns the meta object for class '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation
     * <em>Revealed Information</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Revealed Information</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation
     * @generated
     */
    EClass getRevealedInformation( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getLetterPool
     * <em>Letter Pool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Letter Pool</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getLetterPool()
     * @see #getRevealedInformation()
     * @generated
     */
    EAttribute getRevealedInformation_LetterPool( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#isTwentySpotPaid
     * <em>Twenty Spot Paid</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Twenty Spot Paid</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#isTwentySpotPaid()
     * @see #getRevealedInformation()
     * @generated
     */
    EAttribute getRevealedInformation_TwentySpotPaid( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getBonusValue
     * <em>Bonus Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Bonus Value</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation#getBonusValue()
     * @see #getRevealedInformation()
     * @generated
     */
    EAttribute getRevealedInformation_BonusValue( );

    /**
     * Returns the meta object for class '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis <em>Prize Analysis</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Prize Analysis</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis
     * @generated
     */
    EClass getPrizeAnalysis( );

    /**
     * Returns the meta object for the attribute list '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis#getPrizeDistribution
     * <em>Prize Distribution</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Prize Distribution</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis#getPrizeDistribution()
     * @see #getPrizeAnalysis()
     * @generated
     */
    EAttribute getPrizeAnalysis_PrizeDistribution( );

    /**
     * Returns the meta object for the containment reference '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis#getDynamicInput
     * <em>Dynamic Input</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Dynamic Input</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis#getDynamicInput()
     * @see #getPrizeAnalysis()
     * @generated
     */
    EReference getPrizeAnalysis_DynamicInput( );

    /**
     * Returns the meta object for class '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket <em>Playable Ticket</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Playable Ticket</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket
     * @generated
     */
    EClass getPlayableTicket( );

    /**
     * Returns the meta object for the containment reference '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getFixedDetails
     * <em>Fixed Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Fixed Details</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getFixedDetails()
     * @see #getPlayableTicket()
     * @generated
     */
    EReference getPlayableTicket_FixedDetails( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getBoardContent
     * <em>Board Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Board Content</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getBoardContent()
     * @see #getPlayableTicket()
     * @generated
     */
    EAttribute getPlayableTicket_BoardContent( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getBonusWord
     * <em>Bonus Word</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Bonus Word</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getBonusWord()
     * @see #getPlayableTicket()
     * @generated
     */
    EAttribute getPlayableTicket_BonusWord( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#isTwentySpotIncluded
     * <em>Twenty Spot Included</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Twenty Spot Included</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#isTwentySpotIncluded()
     * @see #getPlayableTicket()
     * @generated
     */
    EAttribute getPlayableTicket_TwentySpotIncluded( );

    /**
     * Returns the meta object for the containment reference '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getDynamicInput
     * <em>Dynamic Input</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Dynamic Input</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getDynamicInput()
     * @see #getPlayableTicket()
     * @generated
     */
    EReference getPlayableTicket_DynamicInput( );

    /**
     * Returns the meta object for the containment reference '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getLatestAnalysis
     * <em>Latest Analysis</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Latest Analysis</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getLatestAnalysis()
     * @see #getPlayableTicket()
     * @generated
     */
    EReference getPlayableTicket_LatestAnalysis( );

    /**
     * Returns the meta object for the containment reference '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getBoardCoverage
     * <em>Board Coverage</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Board Coverage</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#getBoardCoverage()
     * @see #getPlayableTicket()
     * @generated
     */
    EReference getPlayableTicket_BoardCoverage( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#isResolved <em>Resolved</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Resolved</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket#isResolved()
     * @see #getPlayableTicket()
     * @generated
     */
    EAttribute getPlayableTicket_Resolved( );

    /**
     * Returns the meta object for class '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket <em>Resolved Ticket</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Resolved Ticket</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket
     * @generated
     */
    EClass getResolvedTicket( );

    /**
     * Returns the meta object for the containment reference '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getFixedDetails
     * <em>Fixed Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Fixed Details</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getFixedDetails()
     * @see #getResolvedTicket()
     * @generated
     */
    EReference getResolvedTicket_FixedDetails( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBoardContent
     * <em>Board Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Board Content</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBoardContent()
     * @see #getResolvedTicket()
     * @generated
     */
    EAttribute getResolvedTicket_BoardContent( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBonusWord
     * <em>Bonus Word</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Bonus Word</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBonusWord()
     * @see #getResolvedTicket()
     * @generated
     */
    EAttribute getResolvedTicket_BonusWord( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#isTwentySpotIncluded
     * <em>Twenty Spot Included</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Twenty Spot Included</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#isTwentySpotIncluded()
     * @see #getResolvedTicket()
     * @generated
     */
    EAttribute getResolvedTicket_TwentySpotIncluded( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getLetterPool
     * <em>Letter Pool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Letter Pool</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getLetterPool()
     * @see #getResolvedTicket()
     * @generated
     */
    EAttribute getResolvedTicket_LetterPool( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#isTwentySpotPaid
     * <em>Twenty Spot Paid</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Twenty Spot Paid</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#isTwentySpotPaid()
     * @see #getResolvedTicket()
     * @generated
     */
    EAttribute getResolvedTicket_TwentySpotPaid( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBonusValue
     * <em>Bonus Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Bonus Value</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBonusValue()
     * @see #getResolvedTicket()
     * @generated
     */
    EAttribute getResolvedTicket_BonusValue( );

    /**
     * Returns the meta object for the attribute '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getPayoutTier
     * <em>Payout Tier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Payout Tier</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getPayoutTier()
     * @see #getResolvedTicket()
     * @generated
     */
    EAttribute getResolvedTicket_PayoutTier( );

    /**
     * Returns the meta object for the containment reference '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBoardCoverage
     * <em>Board Coverage</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Board Coverage</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket#getBoardCoverage()
     * @see #getResolvedTicket()
     * @generated
     */
    EReference getResolvedTicket_BoardCoverage( );

    /**
     * Returns the meta object for enum '{@link info.jchein.sandbox.app.crossword.resources.xw.BonusValue
     * <em>Bonus Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Bonus Value</em>'.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BonusValue
     * @generated
     */
    EEnum getBonusValue( );

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    XwFactory getXwFactory( );


    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl
         * <em>Pending Ticket Detail</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getPendingTicketDetail()
         * @generated
         */
        EClass PENDING_TICKET_DETAIL = eINSTANCE.getPendingTicketDetail();

        /**
         * The meta object literal for the '<em><b>Board Content</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PENDING_TICKET_DETAIL__BOARD_CONTENT = eINSTANCE.getPendingTicketDetail_BoardContent();

        /**
         * The meta object literal for the '<em><b>Bonus Word</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PENDING_TICKET_DETAIL__BONUS_WORD = eINSTANCE.getPendingTicketDetail_BonusWord();

        /**
         * The meta object literal for the '<em><b>Twenty Spot Included</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PENDING_TICKET_DETAIL__TWENTY_SPOT_INCLUDED =
            eINSTANCE.getPendingTicketDetail_TwentySpotIncluded();

        /**
         * The meta object literal for the '<em><b>All Words</b></em>' attribute list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PENDING_TICKET_DETAIL__ALL_WORDS = eINSTANCE.getPendingTicketDetail_AllWords();

        /**
         * The meta object literal for the '<em><b>Basic Words</b></em>' attribute list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PENDING_TICKET_DETAIL__BASIC_WORDS = eINSTANCE.getPendingTicketDetail_BasicWords();

        /**
         * The meta object literal for the '<em><b>Triple Words</b></em>' attribute list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PENDING_TICKET_DETAIL__TRIPLE_WORDS = eINSTANCE.getPendingTicketDetail_TripleWords();

        /**
         * The meta object literal for the '<em><b>Publishable</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PENDING_TICKET_DETAIL__PUBLISHABLE = eINSTANCE.getPendingTicketDetail_Publishable();

        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.PublishedTicketDetailImpl
         * <em>Published Ticket Detail</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.PublishedTicketDetailImpl
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getPublishedTicketDetail()
         * @generated
         */
        EClass PUBLISHED_TICKET_DETAIL = eINSTANCE.getPublishedTicketDetail();

        /**
         * The meta object literal for the '<em><b>Board Content</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PUBLISHED_TICKET_DETAIL__BOARD_CONTENT = eINSTANCE.getPublishedTicketDetail_BoardContent();

        /**
         * The meta object literal for the '<em><b>Bonus Word</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PUBLISHED_TICKET_DETAIL__BONUS_WORD = eINSTANCE.getPublishedTicketDetail_BonusWord();

        /**
         * The meta object literal for the '<em><b>Twenty Spot Included</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PUBLISHED_TICKET_DETAIL__TWENTY_SPOT_INCLUDED =
            eINSTANCE.getPublishedTicketDetail_TwentySpotIncluded();

        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.BoardCoverageImpl
         * <em>Board Coverage</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.BoardCoverageImpl
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getBoardCoverage()
         * @generated
         */
        EClass BOARD_COVERAGE = eINSTANCE.getBoardCoverage();

        /**
         * The meta object literal for the '<em><b>Bonus Word</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BOARD_COVERAGE__BONUS_WORD = eINSTANCE.getBoardCoverage_BonusWord();

        /**
         * The meta object literal for the '<em><b>All Words</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BOARD_COVERAGE__ALL_WORDS = eINSTANCE.getBoardCoverage_AllWords();

        /**
         * The meta object literal for the '<em><b>Basic Words</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BOARD_COVERAGE__BASIC_WORDS = eINSTANCE.getBoardCoverage_BasicWords();

        /**
         * The meta object literal for the '<em><b>Triple Words</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BOARD_COVERAGE__TRIPLE_WORDS = eINSTANCE.getBoardCoverage_TripleWords();

        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.AbstractWordCoverageImpl
         * <em>Abstract Word Coverage</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.AbstractWordCoverageImpl
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getAbstractWordCoverage()
         * @generated
         */
        EClass ABSTRACT_WORD_COVERAGE = eINSTANCE.getAbstractWordCoverage();

        /**
         * The meta object literal for the '<em><b>Raw Word</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_WORD_COVERAGE__RAW_WORD = eINSTANCE.getAbstractWordCoverage_RawWord();

        /**
         * The meta object literal for the '<em><b>Display Word</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_WORD_COVERAGE__DISPLAY_WORD = eINSTANCE.getAbstractWordCoverage_DisplayWord();

        /**
         * The meta object literal for the '<em><b>Uncovered Letters</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_WORD_COVERAGE__UNCOVERED_LETTERS =
            eINSTANCE.getAbstractWordCoverage_UncoveredLetters();

        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.BonusWordCoverageImpl
         * <em>Bonus Word Coverage</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.BonusWordCoverageImpl
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getBonusWordCoverage()
         * @generated
         */
        EClass BONUS_WORD_COVERAGE = eINSTANCE.getBonusWordCoverage();

        /**
         * The meta object literal for the '<em><b>Bonus Paid</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BONUS_WORD_COVERAGE__BONUS_PAID = eINSTANCE.getBonusWordCoverage_BonusPaid();

        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.GridWordCoverageImpl
         * <em>Grid Word Coverage</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.GridWordCoverageImpl
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getGridWordCoverage()
         * @generated
         */
        EClass GRID_WORD_COVERAGE = eINSTANCE.getGridWordCoverage();

        /**
         * The meta object literal for the '<em><b>Word Covered</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GRID_WORD_COVERAGE__WORD_COVERED = eINSTANCE.getGridWordCoverage_WordCovered();

        /**
         * The meta object literal for the '<em><b>Tripling</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GRID_WORD_COVERAGE__TRIPLING = eINSTANCE.getGridWordCoverage_Tripling();

        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.RevealedInformationImpl
         * <em>Revealed Information</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.RevealedInformationImpl
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getRevealedInformation()
         * @generated
         */
        EClass REVEALED_INFORMATION = eINSTANCE.getRevealedInformation();

        /**
         * The meta object literal for the '<em><b>Letter Pool</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REVEALED_INFORMATION__LETTER_POOL = eINSTANCE.getRevealedInformation_LetterPool();

        /**
         * The meta object literal for the '<em><b>Twenty Spot Paid</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REVEALED_INFORMATION__TWENTY_SPOT_PAID = eINSTANCE.getRevealedInformation_TwentySpotPaid();

        /**
         * The meta object literal for the '<em><b>Bonus Value</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REVEALED_INFORMATION__BONUS_VALUE = eINSTANCE.getRevealedInformation_BonusValue();

        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.PrizeAnalysisImpl
         * <em>Prize Analysis</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.PrizeAnalysisImpl
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getPrizeAnalysis()
         * @generated
         */
        EClass PRIZE_ANALYSIS = eINSTANCE.getPrizeAnalysis();

        /**
         * The meta object literal for the '<em><b>Prize Distribution</b></em>' attribute list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PRIZE_ANALYSIS__PRIZE_DISTRIBUTION = eINSTANCE.getPrizeAnalysis_PrizeDistribution();

        /**
         * The meta object literal for the '<em><b>Dynamic Input</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PRIZE_ANALYSIS__DYNAMIC_INPUT = eINSTANCE.getPrizeAnalysis_DynamicInput();

        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl
         * <em>Playable Ticket</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getPlayableTicket()
         * @generated
         */
        EClass PLAYABLE_TICKET = eINSTANCE.getPlayableTicket();

        /**
         * The meta object literal for the '<em><b>Fixed Details</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PLAYABLE_TICKET__FIXED_DETAILS = eINSTANCE.getPlayableTicket_FixedDetails();

        /**
         * The meta object literal for the '<em><b>Board Content</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PLAYABLE_TICKET__BOARD_CONTENT = eINSTANCE.getPlayableTicket_BoardContent();

        /**
         * The meta object literal for the '<em><b>Bonus Word</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PLAYABLE_TICKET__BONUS_WORD = eINSTANCE.getPlayableTicket_BonusWord();

        /**
         * The meta object literal for the '<em><b>Twenty Spot Included</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PLAYABLE_TICKET__TWENTY_SPOT_INCLUDED = eINSTANCE.getPlayableTicket_TwentySpotIncluded();

        /**
         * The meta object literal for the '<em><b>Dynamic Input</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PLAYABLE_TICKET__DYNAMIC_INPUT = eINSTANCE.getPlayableTicket_DynamicInput();

        /**
         * The meta object literal for the '<em><b>Latest Analysis</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PLAYABLE_TICKET__LATEST_ANALYSIS = eINSTANCE.getPlayableTicket_LatestAnalysis();

        /**
         * The meta object literal for the '<em><b>Board Coverage</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PLAYABLE_TICKET__BOARD_COVERAGE = eINSTANCE.getPlayableTicket_BoardCoverage();

        /**
         * The meta object literal for the '<em><b>Resolved</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PLAYABLE_TICKET__RESOLVED = eINSTANCE.getPlayableTicket_Resolved();

        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl
         * <em>Resolved Ticket</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getResolvedTicket()
         * @generated
         */
        EClass RESOLVED_TICKET = eINSTANCE.getResolvedTicket();

        /**
         * The meta object literal for the '<em><b>Fixed Details</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference RESOLVED_TICKET__FIXED_DETAILS = eINSTANCE.getResolvedTicket_FixedDetails();

        /**
         * The meta object literal for the '<em><b>Board Content</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RESOLVED_TICKET__BOARD_CONTENT = eINSTANCE.getResolvedTicket_BoardContent();

        /**
         * The meta object literal for the '<em><b>Bonus Word</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RESOLVED_TICKET__BONUS_WORD = eINSTANCE.getResolvedTicket_BonusWord();

        /**
         * The meta object literal for the '<em><b>Twenty Spot Included</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RESOLVED_TICKET__TWENTY_SPOT_INCLUDED = eINSTANCE.getResolvedTicket_TwentySpotIncluded();

        /**
         * The meta object literal for the '<em><b>Letter Pool</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RESOLVED_TICKET__LETTER_POOL = eINSTANCE.getResolvedTicket_LetterPool();

        /**
         * The meta object literal for the '<em><b>Twenty Spot Paid</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RESOLVED_TICKET__TWENTY_SPOT_PAID = eINSTANCE.getResolvedTicket_TwentySpotPaid();

        /**
         * The meta object literal for the '<em><b>Bonus Value</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RESOLVED_TICKET__BONUS_VALUE = eINSTANCE.getResolvedTicket_BonusValue();

        /**
         * The meta object literal for the '<em><b>Payout Tier</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RESOLVED_TICKET__PAYOUT_TIER = eINSTANCE.getResolvedTicket_PayoutTier();

        /**
         * The meta object literal for the '<em><b>Board Coverage</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference RESOLVED_TICKET__BOARD_COVERAGE = eINSTANCE.getResolvedTicket_BoardCoverage();

        /**
         * The meta object literal for the '
         * {@link info.jchein.sandbox.app.crossword.resources.xw.BonusValue <em>Bonus Value</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see info.jchein.sandbox.app.crossword.resources.xw.BonusValue
         * @see info.jchein.sandbox.app.crossword.resources.xw.impl.XwPackageImpl#getBonusValue()
         * @generated
         */
        EEnum BONUS_VALUE = eINSTANCE.getBonusValue();

    }

} // XwPackage
