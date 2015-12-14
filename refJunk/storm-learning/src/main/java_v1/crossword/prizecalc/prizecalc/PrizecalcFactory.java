/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import java.util.BitSet;
import java.util.List;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage
 * @generated
 */
public interface PrizecalcFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    PrizecalcFactory eINSTANCE =
        info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.PrizecalcFactoryImpl.init();


    /**
     * Returns a new object of class '<em>Async Prize Analysis Request</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return a new object of class '<em>Async Prize Analysis Request</em>'.
     * @generated
     */
    AsyncPrizeAnalysisRequest createAsyncPrizeAnalysisRequest( );

    /**
     * Returns a new object of class '<em>Async Prize Analysis Response</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return a new object of class '<em>Async Prize Analysis Response</em>'.
     * @generated
     */
    AsyncPrizeAnalysisResponse createAsyncPrizeAnalysisResponse( );

    /**
     * Returns a new object of class '<em>Report Prize Analysis Request</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return a new object of class '<em>Report Prize Analysis Request</em>'.
     * @generated
     */
    ReportPrizeAnalysisRequest createReportPrizeAnalysisRequest( );

    /**
     * Returns a new object of class '<em>Report Prize Analysis Response</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return a new object of class '<em>Report Prize Analysis Response</em>'.
     * @generated
     */
    ReportPrizeAnalysisResponse createReportPrizeAnalysisResponse( );

    /**
     * Returns a new object of class '<em>Available Letter</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Available Letter</em>'.
     * @generated
     */
    AvailableLetter createAvailableLetter( );

    /**
     * Returns a new object of class '<em>Available Letter</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Available Letter</em>'.
     */
    AvailableLetter createAvailableLetter( char letter, BitSet wordHitMask );

    /**
     * Returns a new object of class '<em>Ticket State</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Ticket State</em>'.
     * @generated
     */
    TicketState createTicketState(
        int wordsCompleted,
        boolean hasTripleMatch,
        int bonusWordIndex,
        int[] incompleteLetterCounts,
        List<AvailableLetter> availableLetters );

    /**
     * Returns a new object of class '<em>Begin Calculation</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Begin Calculation</em>'.
     * @generated
     */
    BeginCalculation createBeginCalculation( );

    /**
     * Returns a new object of class '<em>Begin Calculation</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Begin Calculation</em>'.
     */
    BeginCalculation createBeginCalculation( String ticketHref, TicketState ticketState );

    /**
     * Returns a new object of class '<em>Subtask Request</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Subtask Request</em>'.
     * @generated
     */
    SubtaskRequest createSubtaskRequest( );

    /**
     * Returns a new object of class '<em>Subtask Response</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Subtask Response</em>'.
     * @generated
     */
    SubtaskResponse createSubtaskResponse( );

    /**
     * Returns a new object of class '<em>Task Result</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Task Result</em>'.
     * @generated
     */
    TaskResult createTaskResult( );

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    PrizecalcPackage getPrizecalcPackage( );

} // PrizecalcFactory
