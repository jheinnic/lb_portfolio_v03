/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage
 * @generated
 */
public class PrizecalcSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static PrizecalcPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PrizecalcSwitch() {
        if (modelPackage == null) {
            modelPackage = PrizecalcPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_REQUEST: {
                AsyncPrizeAnalysisRequest asyncPrizeAnalysisRequest = (AsyncPrizeAnalysisRequest)theEObject;
                T result = caseAsyncPrizeAnalysisRequest(asyncPrizeAnalysisRequest);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrizecalcPackage.ASYNC_PRIZE_ANALYSIS_RESPONSE: {
                AsyncPrizeAnalysisResponse asyncPrizeAnalysisResponse = (AsyncPrizeAnalysisResponse)theEObject;
                T result = caseAsyncPrizeAnalysisResponse(asyncPrizeAnalysisResponse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrizecalcPackage.REPORT_PRIZE_ANALYSIS_REQUEST: {
                ReportPrizeAnalysisRequest reportPrizeAnalysisRequest = (ReportPrizeAnalysisRequest)theEObject;
                T result = caseReportPrizeAnalysisRequest(reportPrizeAnalysisRequest);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrizecalcPackage.REPORT_PRIZE_ANALYSIS_RESPONSE: {
                ReportPrizeAnalysisResponse reportPrizeAnalysisResponse = (ReportPrizeAnalysisResponse)theEObject;
                T result = caseReportPrizeAnalysisResponse(reportPrizeAnalysisResponse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrizecalcPackage.AVAILABLE_LETTER: {
                AvailableLetter availableLetter = (AvailableLetter)theEObject;
                T result = caseAvailableLetter(availableLetter);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrizecalcPackage.TICKET_STATE: {
                TicketState ticketState = (TicketState)theEObject;
                T result = caseTicketState(ticketState);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrizecalcPackage.BEGIN_CALCULATION: {
                BeginCalculation beginCalculation = (BeginCalculation)theEObject;
                T result = caseBeginCalculation(beginCalculation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrizecalcPackage.SUBTASK_REQUEST: {
                SubtaskRequest subtaskRequest = (SubtaskRequest)theEObject;
                T result = caseSubtaskRequest(subtaskRequest);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrizecalcPackage.SUBTASK_RESPONSE: {
                SubtaskResponse subtaskResponse = (SubtaskResponse)theEObject;
                T result = caseSubtaskResponse(subtaskResponse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrizecalcPackage.TASK_RESULT: {
                TaskResult taskResult = (TaskResult)theEObject;
                T result = caseTaskResult(taskResult);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Async Prize Analysis Request</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Async Prize Analysis Request</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAsyncPrizeAnalysisRequest(AsyncPrizeAnalysisRequest object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Async Prize Analysis Response</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Async Prize Analysis Response</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAsyncPrizeAnalysisResponse(AsyncPrizeAnalysisResponse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Report Prize Analysis Request</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Report Prize Analysis Request</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReportPrizeAnalysisRequest(ReportPrizeAnalysisRequest object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Report Prize Analysis Response</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Report Prize Analysis Response</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReportPrizeAnalysisResponse(ReportPrizeAnalysisResponse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Available Letter</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Available Letter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAvailableLetter(AvailableLetter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ticket State</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ticket State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTicketState(TicketState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Begin Calculation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Begin Calculation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBeginCalculation(BeginCalculation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Subtask Request</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Subtask Request</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSubtaskRequest(SubtaskRequest object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Subtask Response</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Subtask Response</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSubtaskResponse(SubtaskResponse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Task Result</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Task Result</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTaskResult(TaskResult object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //PrizecalcSwitch
