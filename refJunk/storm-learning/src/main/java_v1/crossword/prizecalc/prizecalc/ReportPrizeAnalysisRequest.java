/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Report Prize Analysis Request</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest#getPrizeAnalysis <em>Prize Analysis</em>}</li>
 * </ul>
 * </p>
 *
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getReportPrizeAnalysisRequest()
 * @model
 * @generated
 */
public interface ReportPrizeAnalysisRequest extends EObject {
    /**
     * Returns the value of the '<em><b>Prize Analysis</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Prize Analysis</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Prize Analysis</em>' reference.
     * @see #setPrizeAnalysis(PrizeAnalysis)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getReportPrizeAnalysisRequest_PrizeAnalysis()
     * @model
     * @generated
     */
    PrizeAnalysis getPrizeAnalysis();

    /**
     * Sets the value of the '{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.ReportPrizeAnalysisRequest#getPrizeAnalysis <em>Prize Analysis</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Prize Analysis</em>' reference.
     * @see #getPrizeAnalysis()
     * @generated
     */
    void setPrizeAnalysis(PrizeAnalysis value);

} // ReportPrizeAnalysisRequest
