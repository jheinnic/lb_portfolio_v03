/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 * 
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage
 * @generated
 */
public class PrizecalcAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static PrizecalcPackage modelPackage;


    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrizecalcAdapterFactory( ) {
        if (modelPackage == null) {
            modelPackage = PrizecalcPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an
     * instance object of the model. <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType( Object object ) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }


    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected PrizecalcSwitch<Adapter> modelSwitch = new PrizecalcSwitch<Adapter>() {
        @Override
        public Adapter caseAsyncPrizeAnalysisRequest( AsyncPrizeAnalysisRequest object ) {
            return createAsyncPrizeAnalysisRequestAdapter();
        }

        @Override
        public Adapter caseAsyncPrizeAnalysisResponse( AsyncPrizeAnalysisResponse object ) {
            return createAsyncPrizeAnalysisResponseAdapter();
        }

        @Override
        public Adapter caseReportPrizeAnalysisRequest( ReportPrizeAnalysisRequest object ) {
            return createReportPrizeAnalysisRequestAdapter();
        }

        @Override
        public Adapter caseReportPrizeAnalysisResponse( ReportPrizeAnalysisResponse object ) {
            return createReportPrizeAnalysisResponseAdapter();
        }

        @Override
        public Adapter caseAvailableLetter( AvailableLetter object ) {
            return createAvailableLetterAdapter();
        }

        @Override
        public Adapter caseTicketState( TicketState object ) {
            return createTicketStateAdapter();
        }

        @Override
        public Adapter caseBeginCalculation( BeginCalculation object ) {
            return createBeginCalculationAdapter();
        }

        @Override
        public Adapter caseSubtaskRequest( SubtaskRequest object ) {
            return createSubtaskRequestAdapter();
        }

        @Override
        public Adapter caseSubtaskResponse( SubtaskResponse object ) {
            return createSubtaskResponseAdapter();
        }

        @Override
        public Adapter caseTaskResult( TaskResult object ) {
            return createTaskResultAdapter();
        }

        @Override
        public Adapter defaultCase( EObject object ) {
            return createEObjectAdapter();
        }
    };


    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter( Notifier target ) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest
     * <em>Async Prize Analysis Request</em>}'. <!-- begin-user-doc --> This default implementation returns
     * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all
     * the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisRequest
     * @generated
     */
    public Adapter createAsyncPrizeAnalysisRequestAdapter( ) {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse
     * <em>Async Prize Analysis Response</em>}'. <!-- begin-user-doc --> This default implementation returns
     * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all
     * the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AsyncPrizeAnalysisResponse
     * @generated
     */
    public Adapter createAsyncPrizeAnalysisResponseAdapter( ) {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest
     * <em>Report Prize Analysis Request</em>}'. <!-- begin-user-doc --> This default implementation returns
     * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all
     * the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest
     * @generated
     */
    public Adapter createReportPrizeAnalysisRequestAdapter( ) {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse
     * <em>Report Prize Analysis Response</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will
     * catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisResponse
     * @generated
     */
    public Adapter createReportPrizeAnalysisResponseAdapter( ) {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter
     * <em>Available Letter</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter
     * @generated
     */
    public Adapter createAvailableLetterAdapter( ) {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState <em>Ticket State</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState
     * @generated
     */
    public Adapter createTicketStateAdapter( ) {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation
     * <em>Begin Calculation</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation
     * @generated
     */
    public Adapter createBeginCalculationAdapter( ) {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest <em>Subtask Request</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     * 
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskRequest
     * @generated
     */
    public Adapter createSubtaskRequestAdapter( ) {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse
     * <em>Subtask Response</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.SubtaskResponse
     * @generated
     */
    public Adapter createSubtaskResponseAdapter( ) {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult <em>Task Result</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TaskResult
     * @generated
     */
    public Adapter createTaskResultAdapter( ) {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation
     * returns null. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter( ) {
        return null;
    }

} // PrizecalcAdapterFactory
