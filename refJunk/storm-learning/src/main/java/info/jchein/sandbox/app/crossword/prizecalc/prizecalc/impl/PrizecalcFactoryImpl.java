/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.*;

import java.util.BitSet;

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
public class PrizecalcFactoryImpl extends EFactoryImpl implements PrizecalcFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static PrizecalcFactory init( ) {
        try {
            PrizecalcFactory thePrizecalcFactory =
                (PrizecalcFactory) EPackage.Registry.INSTANCE.getEFactory(PrizecalcPackage.eNS_URI);
            if (thePrizecalcFactory != null) {
                return thePrizecalcFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new PrizecalcFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrizecalcFactoryImpl( ) {
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
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST :
                return createAsyncPrizeAnalysisRequest();
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_RESPONSE :
                return createAsyncPrizeAnalysisResponse();
            case PrizecalcPackage.REPORT_PRIZE_ANALYSIS_REQUEST :
                return createReportPrizeAnalysisRequest();
            case PrizecalcPackage.REPORT_PRIZE_ANALYSIS_RESPONSE :
                return createReportPrizeAnalysisResponse();
            case PrizecalcPackage.AVAILABLE_LETTER :
                return createAvailableLetter();
            case PrizecalcPackage.TICKET_STATE :
                return createTicketState();
            case PrizecalcPackage.BEGIN_CALCULATION :
                return createBeginCalculation();
            case PrizecalcPackage.SUBTASK_REQUEST :
                return createSubtaskRequest();
            case PrizecalcPackage.SUBTASK_RESPONSE :
                return createSubtaskResponse();
            case PrizecalcPackage.TASK_RESULT :
                return createTaskResult();
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
            case PrizecalcPackage.BIT_SET :
                return createBitSetFromString(
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
            case PrizecalcPackage.BIT_SET :
                return convertBitSetToString(
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
    public AsyncPrizeAnalysisRequest createAsyncPrizeAnalysisRequest( ) {
        AsyncPrizeAnalysisRequestImpl asyncPrizeAnalysisRequest = new AsyncPrizeAnalysisRequestImpl();
        return asyncPrizeAnalysisRequest;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AsyncPrizeAnalysisResponse createAsyncPrizeAnalysisResponse( ) {
        AsyncPrizeAnalysisResponseImpl asyncPrizeAnalysisResponse = new AsyncPrizeAnalysisResponseImpl();
        return asyncPrizeAnalysisResponse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ReportPrizeAnalysisRequest createReportPrizeAnalysisRequest( ) {
        ReportPrizeAnalysisRequestImpl reportPrizeAnalysisRequest = new ReportPrizeAnalysisRequestImpl();
        return reportPrizeAnalysisRequest;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ReportPrizeAnalysisResponse createReportPrizeAnalysisResponse( ) {
        ReportPrizeAnalysisResponseImpl reportPrizeAnalysisResponse = new ReportPrizeAnalysisResponseImpl();
        return reportPrizeAnalysisResponse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AvailableLetter createAvailableLetter( ) {
        AvailableLetterImpl availableLetter = new AvailableLetterImpl();
        return availableLetter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TicketState createTicketState( ) {
        TicketStateImpl ticketState = new TicketStateImpl();
        return ticketState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BeginCalculation createBeginCalculation( ) {
        BeginCalculationImpl beginCalculation = new BeginCalculationImpl();
        return beginCalculation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SubtaskRequest createSubtaskRequest( ) {
        SubtaskRequestImpl subtaskRequest = new SubtaskRequestImpl();
        return subtaskRequest;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SubtaskResponse createSubtaskResponse( ) {
        SubtaskResponseImpl subtaskResponse = new SubtaskResponseImpl();
        return subtaskResponse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TaskResult createTaskResult( ) {
        TaskResultImpl taskResult = new TaskResultImpl();
        return taskResult;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BitSet createBitSetFromString( EDataType eDataType, String initialValue ) {
        return (BitSet) super.createFromString(
            eDataType,
            initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertBitSetToString( EDataType eDataType, Object instanceValue ) {
        return super.convertToString(
            eDataType,
            instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrizecalcPackage getPrizecalcPackage( ) {
        return (PrizecalcPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static PrizecalcPackage getPackage( ) {
        return PrizecalcPackage.eINSTANCE;
    }

} // PrizecalcFactoryImpl
