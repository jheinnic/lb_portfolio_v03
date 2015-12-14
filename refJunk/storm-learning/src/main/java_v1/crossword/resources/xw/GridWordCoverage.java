/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grid Word Coverage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage#isWordCovered <em>Word Covered</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage#isTripling <em>Tripling</em>}</li>
 * </ul>
 * </p>
 *
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getGridWordCoverage()
 * @model
 * @generated
 */
public interface GridWordCoverage extends AbstractWordCoverage {
    /**
     * Returns the value of the '<em><b>Word Covered</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Word Covered</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Word Covered</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getGridWordCoverage_WordCovered()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    boolean isWordCovered();

    /**
     * Returns the value of the '<em><b>Tripling</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tripling</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tripling</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getGridWordCoverage_Tripling()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    boolean isTripling();

} // GridWordCoverage
