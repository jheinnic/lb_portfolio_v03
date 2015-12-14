/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Prize Analysis</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis#getPrizeDistribution <em>Prize
 * Distribution</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis#getDynamicInput <em>Dynamic Input
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPrizeAnalysis()
 * @model
 * @generated
 */
public interface PrizeAnalysis extends EObject {
    /**
     * Returns the value of the '<em><b>Prize Distribution</b></em>' attribute list. The list contents are
     * of type {@link java.lang.Integer}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Prize Distribution</em>' attribute list.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPrizeAnalysis_PrizeDistribution()
     * @model lower="18" upper="18"
     * @generated
     */
    EList<Integer> getPrizeDistribution( );

    /**
     * Returns the value of the '<em><b>Dynamic Input</b></em>' containment reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Dynamic Input</em>' containment reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Dynamic Input</em>' containment reference.
     * @see #setDynamicInput(RevealedInformation)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getPrizeAnalysis_DynamicInput()
     * @model containment="true"
     * @generated
     */
    RevealedInformation getDynamicInput( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis#getDynamicInput
     * <em>Dynamic Input</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Dynamic Input</em>' containment reference.
     * @see #getDynamicInput()
     * @generated
     */
    void setDynamicInput( RevealedInformation value );

} // PrizeAnalysis
