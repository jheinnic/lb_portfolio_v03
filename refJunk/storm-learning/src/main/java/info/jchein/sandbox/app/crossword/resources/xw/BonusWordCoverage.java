/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Bonus Word Coverage</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage#isBonusPaid <em>Bonus Paid
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getBonusWordCoverage()
 * @model
 * @generated
 */
public interface BonusWordCoverage extends AbstractWordCoverage {
    /**
     * Returns the value of the '<em><b>Bonus Paid</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bonus Paid</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Bonus Paid</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getBonusWordCoverage_BonusPaid()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    boolean isBonusPaid( );

} // BonusWordCoverage
